package org.imogene.epicam.security;

import java.util.List;

import org.apache.log4j.Logger;
import org.imogene.epicam.domain.entity.*;
import org.imogene.epicam.handler.*;
import org.imogene.lib.common.entity.ImogActor;
import org.imogene.lib.common.user.DefaultUser;
import org.imogene.lib.sync.handler.DefaultUserHandler;
import org.imogene.lib.sync.security.UserAccessControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

/**
 * Class to manage the application user access
 * @author Medes-IMPS
 */
public class UserAccessControlImpl implements UserAccessControl {

	private Logger logger = Logger.getLogger("org.imogene.epicam.uao");

	@Autowired
	@Qualifier(value = "defaultUserHandler")
	private DefaultUserHandler defaultUserHandler;

	@Autowired
	@Qualifier(value = "personnelHandler")
	private PersonnelHandler personnelHandler;
	@Autowired
	@Qualifier(value = "utilisateurHandler")
	private UtilisateurHandler utilisateurHandler;

	@Override
	public ImogActor authenticate(String user, String password) {
		return validateLogin(user, password);
	}

	/**
	 * Validate an account (login/password)
	 * @param login user login
	 * @param passwd user password
	 * @return valid user (existing login/password)
	 */
	private ImogActor validateLogin(String login, String passwd) {

		logger.debug("Validating authentification for login : " + login);

		ImogActor current = null;

		/* PersonnelUser */
		current = validateForPersonnel(login);
		if (current != null && validatePasswd(current, passwd)) {
			return current;
		}
		/* UtilisateurUser */
		current = validateForUtilisateur(login);
		if (current != null && validatePasswd(current, passwd)) {
			return current;
		}

		/* Default User */
		current = validateForDefaultUser(login);
		if (current != null && validatePasswd(current, passwd)) {
			return current;
		}

		return null;
	}

	/**
	 * Validate login for PersonnelUser
	 * @return the PersonnelUser if it exist, null otherwise
	 */
	public Personnel validateForPersonnel(String login) {
		List<Personnel> actors = personnelHandler.getUserFromLogin(login);
		return validateActor(actors, login);
	}
	/**
	 * Validate login for UtilisateurUser
	 * @return the UtilisateurUser if it exist, null otherwise
	 */
	public Utilisateur validateForUtilisateur(String login) {
		List<Utilisateur> actors = utilisateurHandler.getUserFromLogin(login);
		return validateActor(actors, login);
	}

	/**
	 * 
	 * @return the default user if it exist, null otherwise
	 */
	public DefaultUser validateForDefaultUser(String login) {
		List<DefaultUser> actors = defaultUserHandler.getUserFromLogin(login);
		return (DefaultUser) validateActor(actors, login);
	}

	/**
	 * Checks that that the login entered belongs to one of the actors
	 * 
	 * @param actors a list of actors
	 * @param login the login to be validated
	 * @return the ImogActor to which the login belongs to, null otherwise
	 */
	private <T extends ImogActor> T validateActor(List<T> actors, String login) {
		if (actors.size() > 1)
			logger.error("Can't process authentification, several users use the same login !");
		if (actors.size() == 1) {
			T actor = actors.get(0);
			if (actor.getLogin().equals(login))
				return actor;
		}
		return null;
	}

	/**
	 * Checks that the password entered belongs to the actor
	 * @param actor an actor
	 * @return true if the password is the one of the actor
	 */
	private boolean validatePasswd(ImogActor actor, String passwd) {
		String dbHash = actor.getPassword();
		String computedHash = encodePassword(actor.getLogin(), passwd);
		if (dbHash.equals(computedHash))
			return true;
		else
			return false;
	}

	private String encodePassword(String login, String password) {
		ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
		encoder.setEncodeHashAsBase64(true);
		return encoder.encodePassword(password, login);
	}

	@Override
	public List<String> getSynchronizable(String userId) {
		// TODO implement getSynchronizable method (no need for the moment because user stored in session)
		return null;
	}

	@Override
	public Object getRestriction(String userId, String entity) {
		// TODO implement getRestriction method (no need for the moment because restriction managed in handler)
		return null;
	}

}
