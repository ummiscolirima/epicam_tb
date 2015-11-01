package org.imogene.epicam.server.handler;

import org.imogene.lib.common.model.CardEntity;
import org.imogene.lib.common.model.CardEntityDao;
import org.imogene.lib.common.model.FieldGroup;
import org.imogene.lib.common.model.FieldGroupDao;
import org.imogene.lib.common.profile.EntityProfile;
import org.imogene.lib.common.profile.FieldGroupProfile;
import org.imogene.lib.common.profile.Profile;
import org.imogene.web.server.handler.ProfileHelper;

public class ProfileHelperImpl implements ProfileHelper {

	protected CardEntityDao cardEntityDao;
	protected FieldGroupDao fieldGroupDao;

	public void setCardEntityDao(CardEntityDao cardEntityDao) {
		this.cardEntityDao = cardEntityDao;
	}

	public void setFieldGroupDao(FieldGroupDao fieldGroupDao) {
		this.fieldGroupDao = fieldGroupDao;
	}

	@Override
	public Profile createProfile() {
		Profile profile = new Profile();

		CardEntity entity;
		FieldGroup fieldGroup;
		EntityProfile entityProfile;
		FieldGroupProfile fieldGroupProfile;

		entity = cardEntityDao.load("patient");
		entityProfile = new EntityProfile();
		entityProfile.setProfile(profile);
		entityProfile.setEntity(entity);
		profile.addToEntityProfiles(entityProfile);

		fieldGroup = fieldGroupDao.load("patient.identification");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		fieldGroup = fieldGroupDao.load("patient.contact");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		fieldGroup = fieldGroupDao.load("patient.personnecontact");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		fieldGroup = fieldGroupDao.load("patient.tuberculose");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		fieldGroup = fieldGroupDao.load("patient.examens");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		entity = cardEntityDao.load("casindex");
		entityProfile = new EntityProfile();
		entityProfile.setProfile(profile);
		entityProfile.setEntity(entity);
		profile.addToEntityProfiles(entityProfile);

		fieldGroup = fieldGroupDao.load("casindex.description");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		entity = cardEntityDao.load("castuberculose");
		entityProfile = new EntityProfile();
		entityProfile.setProfile(profile);
		entityProfile.setEntity(entity);
		profile.addToEntityProfiles(entityProfile);

		fieldGroup = fieldGroupDao.load("castuberculose.informations");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		fieldGroup = fieldGroupDao.load("castuberculose.examen");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		fieldGroup = fieldGroupDao.load("castuberculose.traitement");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		fieldGroup = fieldGroupDao.load("castuberculose.fintraitement");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		entity = cardEntityDao.load("examenserologie");
		entityProfile = new EntityProfile();
		entityProfile.setProfile(profile);
		entityProfile.setEntity(entity);
		profile.addToEntityProfiles(entityProfile);

		fieldGroup = fieldGroupDao.load("examenserologie.description");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		entity = cardEntityDao.load("examenbiologique");
		entityProfile = new EntityProfile();
		entityProfile.setProfile(profile);
		entityProfile.setEntity(entity);
		profile.addToEntityProfiles(entityProfile);

		fieldGroup = fieldGroupDao.load("examenbiologique.description");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		entity = cardEntityDao.load("examenmicroscopie");
		entityProfile = new EntityProfile();
		entityProfile.setProfile(profile);
		entityProfile.setEntity(entity);
		profile.addToEntityProfiles(entityProfile);

		fieldGroup = fieldGroupDao.load("examenmicroscopie.centreexamen");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		fieldGroup = fieldGroupDao.load("examenmicroscopie.examen");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		entity = cardEntityDao.load("examenatb");
		entityProfile = new EntityProfile();
		entityProfile.setProfile(profile);
		entityProfile.setEntity(entity);
		profile.addToEntityProfiles(entityProfile);

		fieldGroup = fieldGroupDao.load("examenatb.centreexamen");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		fieldGroup = fieldGroupDao.load("examenatb.examen");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		entity = cardEntityDao.load("prisemedicamenteuse");
		entityProfile = new EntityProfile();
		entityProfile.setProfile(profile);
		entityProfile.setEntity(entity);
		profile.addToEntityProfiles(entityProfile);

		fieldGroup = fieldGroupDao.load("prisemedicamenteuse.description");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		entity = cardEntityDao.load("rendezvous");
		entityProfile = new EntityProfile();
		entityProfile.setProfile(profile);
		entityProfile.setEntity(entity);
		profile.addToEntityProfiles(entityProfile);

		fieldGroup = fieldGroupDao.load("rendezvous.description");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		entity = cardEntityDao.load("transfertreference");
		entityProfile = new EntityProfile();
		entityProfile.setProfile(profile);
		entityProfile.setEntity(entity);
		profile.addToEntityProfiles(entityProfile);

		fieldGroup = fieldGroupDao
				.load("transfertreference.informationsdepart");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		fieldGroup = fieldGroupDao
				.load("transfertreference.informationarrivee");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		entity = cardEntityDao.load("lot");
		entityProfile = new EntityProfile();
		entityProfile.setProfile(profile);
		entityProfile.setEntity(entity);
		profile.addToEntityProfiles(entityProfile);

		fieldGroup = fieldGroupDao.load("lot.description");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		entity = cardEntityDao.load("horsusage");
		entityProfile = new EntityProfile();
		entityProfile.setProfile(profile);
		entityProfile.setEntity(entity);
		profile.addToEntityProfiles(entityProfile);

		fieldGroup = fieldGroupDao.load("horsusage.description");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		entity = cardEntityDao.load("entreelot");
		entityProfile = new EntityProfile();
		entityProfile.setProfile(profile);
		entityProfile.setEntity(entity);
		profile.addToEntityProfiles(entityProfile);

		fieldGroup = fieldGroupDao.load("entreelot.description");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		entity = cardEntityDao.load("sortielot");
		entityProfile = new EntityProfile();
		entityProfile.setProfile(profile);
		entityProfile.setEntity(entity);
		profile.addToEntityProfiles(entityProfile);

		fieldGroup = fieldGroupDao.load("sortielot.description");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		entity = cardEntityDao.load("commande");
		entityProfile = new EntityProfile();
		entityProfile.setProfile(profile);
		entityProfile.setEntity(entity);
		profile.addToEntityProfiles(entityProfile);

		fieldGroup = fieldGroupDao.load("commande.informationsdepart");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		fieldGroup = fieldGroupDao.load("commande.regionapprobation");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		fieldGroup = fieldGroupDao.load("commande.gtcapprobation");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		entity = cardEntityDao.load("detailcommandemedicament");
		entityProfile = new EntityProfile();
		entityProfile.setProfile(profile);
		entityProfile.setEntity(entity);
		profile.addToEntityProfiles(entityProfile);

		fieldGroup = fieldGroupDao.load("detailcommandemedicament.description");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		entity = cardEntityDao.load("detailcommandeintrant");
		entityProfile = new EntityProfile();
		entityProfile.setProfile(profile);
		entityProfile.setEntity(entity);
		profile.addToEntityProfiles(entityProfile);

		fieldGroup = fieldGroupDao.load("detailcommandeintrant.description");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		entity = cardEntityDao.load("reception");
		entityProfile = new EntityProfile();
		entityProfile.setProfile(profile);
		entityProfile.setEntity(entity);
		profile.addToEntityProfiles(entityProfile);

		fieldGroup = fieldGroupDao.load("reception.description");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		entity = cardEntityDao.load("detailreceptionmedicament");
		entityProfile = new EntityProfile();
		entityProfile.setProfile(profile);
		entityProfile.setEntity(entity);
		profile.addToEntityProfiles(entityProfile);

		fieldGroup = fieldGroupDao
				.load("detailreceptionmedicament.description");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		entity = cardEntityDao.load("detailreceptionintrant");
		entityProfile = new EntityProfile();
		entityProfile.setProfile(profile);
		entityProfile.setEntity(entity);
		profile.addToEntityProfiles(entityProfile);

		fieldGroup = fieldGroupDao.load("detailreceptionintrant.description");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		entity = cardEntityDao.load("ravitaillement");
		entityProfile = new EntityProfile();
		entityProfile.setProfile(profile);
		entityProfile.setEntity(entity);
		profile.addToEntityProfiles(entityProfile);

		fieldGroup = fieldGroupDao.load("ravitaillement.informationsdepart");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		fieldGroup = fieldGroupDao.load("ravitaillement.informationarrivee");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		fieldGroup = fieldGroupDao.load("ravitaillement.detail");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		entity = cardEntityDao.load("detailravitaillement");
		entityProfile = new EntityProfile();
		entityProfile.setProfile(profile);
		entityProfile.setEntity(entity);
		profile.addToEntityProfiles(entityProfile);

		fieldGroup = fieldGroupDao.load("detailravitaillement.description");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		fieldGroup = fieldGroupDao.load("detailravitaillement.sortie");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		fieldGroup = fieldGroupDao.load("detailravitaillement.entree");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		entity = cardEntityDao.load("inventaire");
		entityProfile = new EntityProfile();
		entityProfile.setProfile(profile);
		entityProfile.setEntity(entity);
		profile.addToEntityProfiles(entityProfile);

		fieldGroup = fieldGroupDao.load("inventaire.informationsdepart");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		entity = cardEntityDao.load("detailinventaire");
		entityProfile = new EntityProfile();
		entityProfile.setProfile(profile);
		entityProfile.setEntity(entity);
		profile.addToEntityProfiles(entityProfile);

		fieldGroup = fieldGroupDao.load("detailinventaire.description");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		entity = cardEntityDao.load("personnel");
		entityProfile = new EntityProfile();
		entityProfile.setProfile(profile);
		entityProfile.setEntity(entity);
		profile.addToEntityProfiles(entityProfile);

		fieldGroup = fieldGroupDao.load("personnel.identification");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		fieldGroup = fieldGroupDao.load("personnel.contact");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		fieldGroup = fieldGroupDao.load("personnel.situation");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		fieldGroup = fieldGroupDao.load("personnel.niveauaccess");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		entity = cardEntityDao.load("departpersonnel");
		entityProfile = new EntityProfile();
		entityProfile.setProfile(profile);
		entityProfile.setEntity(entity);
		profile.addToEntityProfiles(entityProfile);

		fieldGroup = fieldGroupDao.load("departpersonnel.personnel");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		fieldGroup = fieldGroupDao.load("departpersonnel.description");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		entity = cardEntityDao.load("arriveepersonnel");
		entityProfile = new EntityProfile();
		entityProfile.setProfile(profile);
		entityProfile.setEntity(entity);
		profile.addToEntityProfiles(entityProfile);

		fieldGroup = fieldGroupDao.load("arriveepersonnel.description");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		entity = cardEntityDao.load("region");
		entityProfile = new EntityProfile();
		entityProfile.setProfile(profile);
		entityProfile.setEntity(entity);
		profile.addToEntityProfiles(entityProfile);

		fieldGroup = fieldGroupDao.load("region.description");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		fieldGroup = fieldGroupDao.load("region.adresse");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		entity = cardEntityDao.load("districtsante");
		entityProfile = new EntityProfile();
		entityProfile.setProfile(profile);
		entityProfile.setEntity(entity);
		profile.addToEntityProfiles(entityProfile);

		fieldGroup = fieldGroupDao.load("districtsante.description");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		fieldGroup = fieldGroupDao.load("districtsante.adresse");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		entity = cardEntityDao.load("centrediagtrait");
		entityProfile = new EntityProfile();
		entityProfile.setProfile(profile);
		entityProfile.setEntity(entity);
		profile.addToEntityProfiles(entityProfile);

		fieldGroup = fieldGroupDao.load("centrediagtrait.description");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		fieldGroup = fieldGroupDao.load("centrediagtrait.adresse");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		entity = cardEntityDao.load("laboratoirereference");
		entityProfile = new EntityProfile();
		entityProfile.setProfile(profile);
		entityProfile.setEntity(entity);
		profile.addToEntityProfiles(entityProfile);

		fieldGroup = fieldGroupDao.load("laboratoirereference.description");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		fieldGroup = fieldGroupDao.load("laboratoirereference.adresse");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		entity = cardEntityDao.load("lieudit");
		entityProfile = new EntityProfile();
		entityProfile.setProfile(profile);
		entityProfile.setEntity(entity);
		profile.addToEntityProfiles(entityProfile);

		fieldGroup = fieldGroupDao.load("lieudit.description");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		fieldGroup = fieldGroupDao.load("lieudit.adresse");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		entity = cardEntityDao.load("regime");
		entityProfile = new EntityProfile();
		entityProfile.setProfile(profile);
		entityProfile.setEntity(entity);
		profile.addToEntityProfiles(entityProfile);

		fieldGroup = fieldGroupDao.load("regime.description");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		entity = cardEntityDao.load("prisemedicamentregime");
		entityProfile = new EntityProfile();
		entityProfile.setProfile(profile);
		entityProfile.setEntity(entity);
		profile.addToEntityProfiles(entityProfile);

		fieldGroup = fieldGroupDao.load("prisemedicamentregime.description");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		entity = cardEntityDao.load("medicament");
		entityProfile = new EntityProfile();
		entityProfile.setProfile(profile);
		entityProfile.setEntity(entity);
		profile.addToEntityProfiles(entityProfile);

		fieldGroup = fieldGroupDao.load("medicament.description");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		entity = cardEntityDao.load("intrant");
		entityProfile = new EntityProfile();
		entityProfile.setProfile(profile);
		entityProfile.setEntity(entity);
		profile.addToEntityProfiles(entityProfile);

		fieldGroup = fieldGroupDao.load("intrant.description");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		entity = cardEntityDao.load("formation");
		entityProfile = new EntityProfile();
		entityProfile.setProfile(profile);
		entityProfile.setEntity(entity);
		profile.addToEntityProfiles(entityProfile);

		fieldGroup = fieldGroupDao.load("formation.description");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		entity = cardEntityDao.load("candidatureformation");
		entityProfile = new EntityProfile();
		entityProfile.setProfile(profile);
		entityProfile.setEntity(entity);
		profile.addToEntityProfiles(entityProfile);

		fieldGroup = fieldGroupDao.load("candidatureformation.description");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		fieldGroup = fieldGroupDao
				.load("candidatureformation.regionapprobation");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		fieldGroup = fieldGroupDao.load("candidatureformation.gtcapprobation");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		entity = cardEntityDao.load("qualification");
		entityProfile = new EntityProfile();
		entityProfile.setProfile(profile);
		entityProfile.setEntity(entity);
		profile.addToEntityProfiles(entityProfile);

		fieldGroup = fieldGroupDao.load("qualification.description");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		entity = cardEntityDao.load("tutoriel");
		entityProfile = new EntityProfile();
		entityProfile.setProfile(profile);
		entityProfile.setEntity(entity);
		profile.addToEntityProfiles(entityProfile);

		fieldGroup = fieldGroupDao.load("tutoriel.description");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		entity = cardEntityDao.load("smspredefini");
		entityProfile = new EntityProfile();
		entityProfile.setProfile(profile);
		entityProfile.setEntity(entity);
		profile.addToEntityProfiles(entityProfile);

		fieldGroup = fieldGroupDao.load("smspredefini.description");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		entity = cardEntityDao.load("outbox");
		entityProfile = new EntityProfile();
		entityProfile.setProfile(profile);
		entityProfile.setEntity(entity);
		profile.addToEntityProfiles(entityProfile);

		fieldGroup = fieldGroupDao.load("outbox.adresse");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		fieldGroup = fieldGroupDao.load("outbox.messageinformation");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		entity = cardEntityDao.load("utilisateur");
		entityProfile = new EntityProfile();
		entityProfile.setProfile(profile);
		entityProfile.setEntity(entity);
		profile.addToEntityProfiles(entityProfile);

		fieldGroup = fieldGroupDao.load("utilisateur.identification");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);
		fieldGroup = fieldGroupDao.load("utilisateur.contact");
		fieldGroupProfile = new FieldGroupProfile();
		fieldGroupProfile.setCardEntity(fieldGroup.getEntity());
		fieldGroupProfile.setFieldGroup(fieldGroup);
		fieldGroupProfile.setProfile(profile);

		profile.addTofieldGroupProfiles(fieldGroupProfile);

		return profile;
	}

}
