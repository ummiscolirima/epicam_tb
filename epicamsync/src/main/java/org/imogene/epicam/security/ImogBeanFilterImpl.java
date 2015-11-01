package org.imogene.epicam.security;

import java.util.List;
import java.util.Vector;

import org.imogene.epicam.domain.entity.*;
import org.imogene.lib.common.dao.GenericDao;
import org.imogene.lib.common.entity.ImogBean;
import org.imogene.lib.common.security.ImogBeanFilter;
import org.imogene.lib.sync.server.util.HttpSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Class to filter beans so that only 
 * allowed bean data is accessible in read/write mode
 * @author Medes-IMPS
 */
public class ImogBeanFilterImpl implements ImogBeanFilter {

	/* generic DAO access */
	@Autowired
	@Qualifier(value = "genericDao")
	private GenericDao dao;

	@Override
	@SuppressWarnings("unchecked")
	public <T extends ImogBean> T toHibernate(T bean) {
		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();

		if (bean instanceof Patient)
			return (T) toUnsecurePatient((Patient) bean, policy);

		if (bean instanceof CasIndex)
			return (T) toUnsecureCasIndex((CasIndex) bean, policy);

		if (bean instanceof CasTuberculose)
			return (T) toUnsecureCasTuberculose((CasTuberculose) bean, policy);

		if (bean instanceof ExamenSerologie)
			return (T) toUnsecureExamenSerologie((ExamenSerologie) bean, policy);

		if (bean instanceof ExamenBiologique)
			return (T) toUnsecureExamenBiologique((ExamenBiologique) bean,
					policy);

		if (bean instanceof ExamenMicroscopie)
			return (T) toUnsecureExamenMicroscopie((ExamenMicroscopie) bean,
					policy);

		if (bean instanceof ExamenATB)
			return (T) toUnsecureExamenATB((ExamenATB) bean, policy);

		if (bean instanceof PriseMedicamenteuse)
			return (T) toUnsecurePriseMedicamenteuse(
					(PriseMedicamenteuse) bean, policy);

		if (bean instanceof RendezVous)
			return (T) toUnsecureRendezVous((RendezVous) bean, policy);

		if (bean instanceof TransfertReference)
			return (T) toUnsecureTransfertReference((TransfertReference) bean,
					policy);

		if (bean instanceof Lot)
			return (T) toUnsecureLot((Lot) bean, policy);

		if (bean instanceof HorsUsage)
			return (T) toUnsecureHorsUsage((HorsUsage) bean, policy);

		if (bean instanceof EntreeLot)
			return (T) toUnsecureEntreeLot((EntreeLot) bean, policy);

		if (bean instanceof SortieLot)
			return (T) toUnsecureSortieLot((SortieLot) bean, policy);

		if (bean instanceof Commande)
			return (T) toUnsecureCommande((Commande) bean, policy);

		if (bean instanceof DetailCommandeMedicament)
			return (T) toUnsecureDetailCommandeMedicament(
					(DetailCommandeMedicament) bean, policy);

		if (bean instanceof DetailCommandeIntrant)
			return (T) toUnsecureDetailCommandeIntrant(
					(DetailCommandeIntrant) bean, policy);

		if (bean instanceof Reception)
			return (T) toUnsecureReception((Reception) bean, policy);

		if (bean instanceof DetailReceptionMedicament)
			return (T) toUnsecureDetailReceptionMedicament(
					(DetailReceptionMedicament) bean, policy);

		if (bean instanceof DetailReceptionIntrant)
			return (T) toUnsecureDetailReceptionIntrant(
					(DetailReceptionIntrant) bean, policy);

		if (bean instanceof Ravitaillement)
			return (T) toUnsecureRavitaillement((Ravitaillement) bean, policy);

		if (bean instanceof DetailRavitaillement)
			return (T) toUnsecureDetailRavitaillement(
					(DetailRavitaillement) bean, policy);

		if (bean instanceof Inventaire)
			return (T) toUnsecureInventaire((Inventaire) bean, policy);

		if (bean instanceof DetailInventaire)
			return (T) toUnsecureDetailInventaire((DetailInventaire) bean,
					policy);

		if (bean instanceof Personnel)
			return (T) toUnsecurePersonnel((Personnel) bean, policy);

		if (bean instanceof DepartPersonnel)
			return (T) toUnsecureDepartPersonnel((DepartPersonnel) bean, policy);

		if (bean instanceof ArriveePersonnel)
			return (T) toUnsecureArriveePersonnel((ArriveePersonnel) bean,
					policy);

		if (bean instanceof Region)
			return (T) toUnsecureRegion((Region) bean, policy);

		if (bean instanceof DistrictSante)
			return (T) toUnsecureDistrictSante((DistrictSante) bean, policy);

		if (bean instanceof CentreDiagTrait)
			return (T) toUnsecureCentreDiagTrait((CentreDiagTrait) bean, policy);

		if (bean instanceof LaboratoireReference)
			return (T) toUnsecureLaboratoireReference(
					(LaboratoireReference) bean, policy);

		if (bean instanceof LieuDit)
			return (T) toUnsecureLieuDit((LieuDit) bean, policy);

		if (bean instanceof Regime)
			return (T) toUnsecureRegime((Regime) bean, policy);

		if (bean instanceof PriseMedicamentRegime)
			return (T) toUnsecurePriseMedicamentRegime(
					(PriseMedicamentRegime) bean, policy);

		if (bean instanceof Medicament)
			return (T) toUnsecureMedicament((Medicament) bean, policy);

		if (bean instanceof Intrant)
			return (T) toUnsecureIntrant((Intrant) bean, policy);

		if (bean instanceof Formation)
			return (T) toUnsecureFormation((Formation) bean, policy);

		if (bean instanceof CandidatureFormation)
			return (T) toUnsecureCandidatureFormation(
					(CandidatureFormation) bean, policy);

		if (bean instanceof Qualification)
			return (T) toUnsecureQualification((Qualification) bean, policy);

		if (bean instanceof Tutoriel)
			return (T) toUnsecureTutoriel((Tutoriel) bean, policy);

		if (bean instanceof SmsPredefini)
			return (T) toUnsecureSmsPredefini((SmsPredefini) bean, policy);

		if (bean instanceof OutBox)
			return (T) toUnsecureOutBox((OutBox) bean, policy);

		if (bean instanceof Utilisateur)
			return (T) toUnsecureUtilisateur((Utilisateur) bean, policy);

		return bean;
	}

	@Override
	public <T extends ImogBean> List<T> toHibernate(List<T> beans) {
		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		List<T> unsecuredList = new Vector<T>();
		for (T bean : beans) {
			T toHibernate = toHibernate(bean, policy);
			if (toHibernate != null) {
				unsecuredList.add(toHibernate);
			}
		}
		return unsecuredList;
	}

	@SuppressWarnings("unchecked")
	private <T extends ImogBean> T toHibernate(T bean, AccessPolicyImpl policy) {

		if (bean instanceof Patient)
			return (T) toUnsecurePatient((Patient) bean, policy);

		if (bean instanceof CasIndex)
			return (T) toUnsecureCasIndex((CasIndex) bean, policy);

		if (bean instanceof CasTuberculose)
			return (T) toUnsecureCasTuberculose((CasTuberculose) bean, policy);

		if (bean instanceof ExamenSerologie)
			return (T) toUnsecureExamenSerologie((ExamenSerologie) bean, policy);

		if (bean instanceof ExamenBiologique)
			return (T) toUnsecureExamenBiologique((ExamenBiologique) bean,
					policy);

		if (bean instanceof ExamenMicroscopie)
			return (T) toUnsecureExamenMicroscopie((ExamenMicroscopie) bean,
					policy);

		if (bean instanceof ExamenATB)
			return (T) toUnsecureExamenATB((ExamenATB) bean, policy);

		if (bean instanceof PriseMedicamenteuse)
			return (T) toUnsecurePriseMedicamenteuse(
					(PriseMedicamenteuse) bean, policy);

		if (bean instanceof RendezVous)
			return (T) toUnsecureRendezVous((RendezVous) bean, policy);

		if (bean instanceof TransfertReference)
			return (T) toUnsecureTransfertReference((TransfertReference) bean,
					policy);

		if (bean instanceof Lot)
			return (T) toUnsecureLot((Lot) bean, policy);

		if (bean instanceof HorsUsage)
			return (T) toUnsecureHorsUsage((HorsUsage) bean, policy);

		if (bean instanceof EntreeLot)
			return (T) toUnsecureEntreeLot((EntreeLot) bean, policy);

		if (bean instanceof SortieLot)
			return (T) toUnsecureSortieLot((SortieLot) bean, policy);

		if (bean instanceof Commande)
			return (T) toUnsecureCommande((Commande) bean, policy);

		if (bean instanceof DetailCommandeMedicament)
			return (T) toUnsecureDetailCommandeMedicament(
					(DetailCommandeMedicament) bean, policy);

		if (bean instanceof DetailCommandeIntrant)
			return (T) toUnsecureDetailCommandeIntrant(
					(DetailCommandeIntrant) bean, policy);

		if (bean instanceof Reception)
			return (T) toUnsecureReception((Reception) bean, policy);

		if (bean instanceof DetailReceptionMedicament)
			return (T) toUnsecureDetailReceptionMedicament(
					(DetailReceptionMedicament) bean, policy);

		if (bean instanceof DetailReceptionIntrant)
			return (T) toUnsecureDetailReceptionIntrant(
					(DetailReceptionIntrant) bean, policy);

		if (bean instanceof Ravitaillement)
			return (T) toUnsecureRavitaillement((Ravitaillement) bean, policy);

		if (bean instanceof DetailRavitaillement)
			return (T) toUnsecureDetailRavitaillement(
					(DetailRavitaillement) bean, policy);

		if (bean instanceof Inventaire)
			return (T) toUnsecureInventaire((Inventaire) bean, policy);

		if (bean instanceof DetailInventaire)
			return (T) toUnsecureDetailInventaire((DetailInventaire) bean,
					policy);

		if (bean instanceof Personnel)
			return (T) toUnsecurePersonnel((Personnel) bean, policy);

		if (bean instanceof DepartPersonnel)
			return (T) toUnsecureDepartPersonnel((DepartPersonnel) bean, policy);

		if (bean instanceof ArriveePersonnel)
			return (T) toUnsecureArriveePersonnel((ArriveePersonnel) bean,
					policy);

		if (bean instanceof Region)
			return (T) toUnsecureRegion((Region) bean, policy);

		if (bean instanceof DistrictSante)
			return (T) toUnsecureDistrictSante((DistrictSante) bean, policy);

		if (bean instanceof CentreDiagTrait)
			return (T) toUnsecureCentreDiagTrait((CentreDiagTrait) bean, policy);

		if (bean instanceof LaboratoireReference)
			return (T) toUnsecureLaboratoireReference(
					(LaboratoireReference) bean, policy);

		if (bean instanceof LieuDit)
			return (T) toUnsecureLieuDit((LieuDit) bean, policy);

		if (bean instanceof Regime)
			return (T) toUnsecureRegime((Regime) bean, policy);

		if (bean instanceof PriseMedicamentRegime)
			return (T) toUnsecurePriseMedicamentRegime(
					(PriseMedicamentRegime) bean, policy);

		if (bean instanceof Medicament)
			return (T) toUnsecureMedicament((Medicament) bean, policy);

		if (bean instanceof Intrant)
			return (T) toUnsecureIntrant((Intrant) bean, policy);

		if (bean instanceof Formation)
			return (T) toUnsecureFormation((Formation) bean, policy);

		if (bean instanceof CandidatureFormation)
			return (T) toUnsecureCandidatureFormation(
					(CandidatureFormation) bean, policy);

		if (bean instanceof Qualification)
			return (T) toUnsecureQualification((Qualification) bean, policy);

		if (bean instanceof Tutoriel)
			return (T) toUnsecureTutoriel((Tutoriel) bean, policy);

		if (bean instanceof SmsPredefini)
			return (T) toUnsecureSmsPredefini((SmsPredefini) bean, policy);

		if (bean instanceof OutBox)
			return (T) toUnsecureOutBox((OutBox) bean, policy);

		if (bean instanceof Utilisateur)
			return (T) toUnsecureUtilisateur((Utilisateur) bean, policy);

		return bean;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends ImogBean> T toSecure(T bean) {
		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();

		if (bean instanceof Patient)
			return (T) toSecurePatient((Patient) bean, policy);

		if (bean instanceof CasIndex)
			return (T) toSecureCasIndex((CasIndex) bean, policy);

		if (bean instanceof CasTuberculose)
			return (T) toSecureCasTuberculose((CasTuberculose) bean, policy);

		if (bean instanceof ExamenSerologie)
			return (T) toSecureExamenSerologie((ExamenSerologie) bean, policy);

		if (bean instanceof ExamenBiologique)
			return (T) toSecureExamenBiologique((ExamenBiologique) bean, policy);

		if (bean instanceof ExamenMicroscopie)
			return (T) toSecureExamenMicroscopie((ExamenMicroscopie) bean,
					policy);

		if (bean instanceof ExamenATB)
			return (T) toSecureExamenATB((ExamenATB) bean, policy);

		if (bean instanceof PriseMedicamenteuse)
			return (T) toSecurePriseMedicamenteuse((PriseMedicamenteuse) bean,
					policy);

		if (bean instanceof RendezVous)
			return (T) toSecureRendezVous((RendezVous) bean, policy);

		if (bean instanceof TransfertReference)
			return (T) toSecureTransfertReference((TransfertReference) bean,
					policy);

		if (bean instanceof Lot)
			return (T) toSecureLot((Lot) bean, policy);

		if (bean instanceof HorsUsage)
			return (T) toSecureHorsUsage((HorsUsage) bean, policy);

		if (bean instanceof EntreeLot)
			return (T) toSecureEntreeLot((EntreeLot) bean, policy);

		if (bean instanceof SortieLot)
			return (T) toSecureSortieLot((SortieLot) bean, policy);

		if (bean instanceof Commande)
			return (T) toSecureCommande((Commande) bean, policy);

		if (bean instanceof DetailCommandeMedicament)
			return (T) toSecureDetailCommandeMedicament(
					(DetailCommandeMedicament) bean, policy);

		if (bean instanceof DetailCommandeIntrant)
			return (T) toSecureDetailCommandeIntrant(
					(DetailCommandeIntrant) bean, policy);

		if (bean instanceof Reception)
			return (T) toSecureReception((Reception) bean, policy);

		if (bean instanceof DetailReceptionMedicament)
			return (T) toSecureDetailReceptionMedicament(
					(DetailReceptionMedicament) bean, policy);

		if (bean instanceof DetailReceptionIntrant)
			return (T) toSecureDetailReceptionIntrant(
					(DetailReceptionIntrant) bean, policy);

		if (bean instanceof Ravitaillement)
			return (T) toSecureRavitaillement((Ravitaillement) bean, policy);

		if (bean instanceof DetailRavitaillement)
			return (T) toSecureDetailRavitaillement(
					(DetailRavitaillement) bean, policy);

		if (bean instanceof Inventaire)
			return (T) toSecureInventaire((Inventaire) bean, policy);

		if (bean instanceof DetailInventaire)
			return (T) toSecureDetailInventaire((DetailInventaire) bean, policy);

		if (bean instanceof Personnel)
			return (T) toSecurePersonnel((Personnel) bean, policy);

		if (bean instanceof DepartPersonnel)
			return (T) toSecureDepartPersonnel((DepartPersonnel) bean, policy);

		if (bean instanceof ArriveePersonnel)
			return (T) toSecureArriveePersonnel((ArriveePersonnel) bean, policy);

		if (bean instanceof Region)
			return (T) toSecureRegion((Region) bean, policy);

		if (bean instanceof DistrictSante)
			return (T) toSecureDistrictSante((DistrictSante) bean, policy);

		if (bean instanceof CentreDiagTrait)
			return (T) toSecureCentreDiagTrait((CentreDiagTrait) bean, policy);

		if (bean instanceof LaboratoireReference)
			return (T) toSecureLaboratoireReference(
					(LaboratoireReference) bean, policy);

		if (bean instanceof LieuDit)
			return (T) toSecureLieuDit((LieuDit) bean, policy);

		if (bean instanceof Regime)
			return (T) toSecureRegime((Regime) bean, policy);

		if (bean instanceof PriseMedicamentRegime)
			return (T) toSecurePriseMedicamentRegime(
					(PriseMedicamentRegime) bean, policy);

		if (bean instanceof Medicament)
			return (T) toSecureMedicament((Medicament) bean, policy);

		if (bean instanceof Intrant)
			return (T) toSecureIntrant((Intrant) bean, policy);

		if (bean instanceof Formation)
			return (T) toSecureFormation((Formation) bean, policy);

		if (bean instanceof CandidatureFormation)
			return (T) toSecureCandidatureFormation(
					(CandidatureFormation) bean, policy);

		if (bean instanceof Qualification)
			return (T) toSecureQualification((Qualification) bean, policy);

		if (bean instanceof Tutoriel)
			return (T) toSecureTutoriel((Tutoriel) bean, policy);

		if (bean instanceof SmsPredefini)
			return (T) toSecureSmsPredefini((SmsPredefini) bean, policy);

		if (bean instanceof OutBox)
			return (T) toSecureOutBox((OutBox) bean, policy);

		if (bean instanceof Utilisateur)
			return (T) toSecureUtilisateur((Utilisateur) bean, policy);

		return bean;
	}

	@Override
	public <T extends ImogBean> List<T> toSecure(List<T> beans) {
		AccessPolicyImpl policy = (AccessPolicyImpl) HttpSessionUtil
				.getAccessPolicy();
		List<T> securedList = new Vector<T>();
		for (T bean : beans) {
			T isSecured = toSecure(bean, policy);
			if (isSecured != null) {
				securedList.add(isSecured);
			}
		}
		return securedList;
	}

	@SuppressWarnings("unchecked")
	private <T extends ImogBean> T toSecure(T bean, AccessPolicyImpl policy) {

		if (bean instanceof Patient)
			return (T) toSecurePatient((Patient) bean, policy);

		if (bean instanceof CasIndex)
			return (T) toSecureCasIndex((CasIndex) bean, policy);

		if (bean instanceof CasTuberculose)
			return (T) toSecureCasTuberculose((CasTuberculose) bean, policy);

		if (bean instanceof ExamenSerologie)
			return (T) toSecureExamenSerologie((ExamenSerologie) bean, policy);

		if (bean instanceof ExamenBiologique)
			return (T) toSecureExamenBiologique((ExamenBiologique) bean, policy);

		if (bean instanceof ExamenMicroscopie)
			return (T) toSecureExamenMicroscopie((ExamenMicroscopie) bean,
					policy);

		if (bean instanceof ExamenATB)
			return (T) toSecureExamenATB((ExamenATB) bean, policy);

		if (bean instanceof PriseMedicamenteuse)
			return (T) toSecurePriseMedicamenteuse((PriseMedicamenteuse) bean,
					policy);

		if (bean instanceof RendezVous)
			return (T) toSecureRendezVous((RendezVous) bean, policy);

		if (bean instanceof TransfertReference)
			return (T) toSecureTransfertReference((TransfertReference) bean,
					policy);

		if (bean instanceof Lot)
			return (T) toSecureLot((Lot) bean, policy);

		if (bean instanceof HorsUsage)
			return (T) toSecureHorsUsage((HorsUsage) bean, policy);

		if (bean instanceof EntreeLot)
			return (T) toSecureEntreeLot((EntreeLot) bean, policy);

		if (bean instanceof SortieLot)
			return (T) toSecureSortieLot((SortieLot) bean, policy);

		if (bean instanceof Commande)
			return (T) toSecureCommande((Commande) bean, policy);

		if (bean instanceof DetailCommandeMedicament)
			return (T) toSecureDetailCommandeMedicament(
					(DetailCommandeMedicament) bean, policy);

		if (bean instanceof DetailCommandeIntrant)
			return (T) toSecureDetailCommandeIntrant(
					(DetailCommandeIntrant) bean, policy);

		if (bean instanceof Reception)
			return (T) toSecureReception((Reception) bean, policy);

		if (bean instanceof DetailReceptionMedicament)
			return (T) toSecureDetailReceptionMedicament(
					(DetailReceptionMedicament) bean, policy);

		if (bean instanceof DetailReceptionIntrant)
			return (T) toSecureDetailReceptionIntrant(
					(DetailReceptionIntrant) bean, policy);

		if (bean instanceof Ravitaillement)
			return (T) toSecureRavitaillement((Ravitaillement) bean, policy);

		if (bean instanceof DetailRavitaillement)
			return (T) toSecureDetailRavitaillement(
					(DetailRavitaillement) bean, policy);

		if (bean instanceof Inventaire)
			return (T) toSecureInventaire((Inventaire) bean, policy);

		if (bean instanceof DetailInventaire)
			return (T) toSecureDetailInventaire((DetailInventaire) bean, policy);

		if (bean instanceof Personnel)
			return (T) toSecurePersonnel((Personnel) bean, policy);

		if (bean instanceof DepartPersonnel)
			return (T) toSecureDepartPersonnel((DepartPersonnel) bean, policy);

		if (bean instanceof ArriveePersonnel)
			return (T) toSecureArriveePersonnel((ArriveePersonnel) bean, policy);

		if (bean instanceof Region)
			return (T) toSecureRegion((Region) bean, policy);

		if (bean instanceof DistrictSante)
			return (T) toSecureDistrictSante((DistrictSante) bean, policy);

		if (bean instanceof CentreDiagTrait)
			return (T) toSecureCentreDiagTrait((CentreDiagTrait) bean, policy);

		if (bean instanceof LaboratoireReference)
			return (T) toSecureLaboratoireReference(
					(LaboratoireReference) bean, policy);

		if (bean instanceof LieuDit)
			return (T) toSecureLieuDit((LieuDit) bean, policy);

		if (bean instanceof Regime)
			return (T) toSecureRegime((Regime) bean, policy);

		if (bean instanceof PriseMedicamentRegime)
			return (T) toSecurePriseMedicamentRegime(
					(PriseMedicamentRegime) bean, policy);

		if (bean instanceof Medicament)
			return (T) toSecureMedicament((Medicament) bean, policy);

		if (bean instanceof Intrant)
			return (T) toSecureIntrant((Intrant) bean, policy);

		if (bean instanceof Formation)
			return (T) toSecureFormation((Formation) bean, policy);

		if (bean instanceof CandidatureFormation)
			return (T) toSecureCandidatureFormation(
					(CandidatureFormation) bean, policy);

		if (bean instanceof Qualification)
			return (T) toSecureQualification((Qualification) bean, policy);

		if (bean instanceof Tutoriel)
			return (T) toSecureTutoriel((Tutoriel) bean, policy);

		if (bean instanceof SmsPredefini)
			return (T) toSecureSmsPredefini((SmsPredefini) bean, policy);

		if (bean instanceof OutBox)
			return (T) toSecureOutBox((OutBox) bean, policy);

		if (bean instanceof Utilisateur)
			return (T) toSecureUtilisateur((Utilisateur) bean, policy);

		return bean;
	}

	/**
	 * Secure a Patient bean.
	 * 
	 * @param bean The Patient bean to secure
	 * @param roleNames set of role ids for the user 
	 * @return A secured Patient bean
	 */
	private Patient toSecurePatient(Patient bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		Patient transformedBean = new Patient();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canReadPatientIdentification()) {
			isSecured = true;
			transformedBean.setIdentifiant(bean.getIdentifiant());
			transformedBean.setNom(bean.getNom());
			transformedBean.setSexe(bean.getSexe());
			transformedBean.setDateNaissance(bean.getDateNaissance());
			transformedBean.setAge(bean.getAge());
			transformedBean.setProfession(bean.getProfession());
			transformedBean.setCentres(bean.getCentres());
			transformedBean.setNationalite(bean.getNationalite());
			transformedBean.setPrecisionSurNationalite(bean
					.getPrecisionSurNationalite());
			transformedBean.setRecevoirCarnetTelPortable(bean
					.getRecevoirCarnetTelPortable());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 	transformedBean.setIdentifiant(null);
			transformedBean.setNom(null);
			transformedBean.setSexe(null);
			transformedBean.setDateNaissance(null);
			transformedBean.setAge(null);
			transformedBean.setProfession(null);
			transformedBean.setCentres(new Vector<CentreDiagTrait>());
			transformedBean.setNationalite(null);
			transformedBean.setPrecisionSurNationalite(null);
			transformedBean.setRecevoirCarnetTelPortable(null);
			 */
		}
		if (policy.canReadPatientContact()) {
			isSecured = true;
			transformedBean.setTelephoneUn(bean.getTelephoneUn());
			transformedBean.setTelephoneDeux(bean.getTelephoneDeux());
			transformedBean.setTelephoneTrois(bean.getTelephoneTrois());
			transformedBean.setEmail(bean.getEmail());
			transformedBean.setLibelle(bean.getLibelle());
			transformedBean.setComplementAdresse(bean.getComplementAdresse());
			transformedBean.setQuartier(bean.getQuartier());
			transformedBean.setVille(bean.getVille());
			transformedBean.setCodePostal(bean.getCodePostal());
			transformedBean.setLieuxDits(bean.getLieuxDits());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 	transformedBean.setTelephoneUn(null);
			transformedBean.setTelephoneDeux(null);
			transformedBean.setTelephoneTrois(null);
			transformedBean.setEmail(null);
			transformedBean.setLibelle(null);
			transformedBean.setComplementAdresse(null);
			transformedBean.setQuartier(null);
			transformedBean.setVille(null);
			transformedBean.setCodePostal(null);
			transformedBean.setLieuxDits(new Vector<LieuDit>());
			 */
		}
		if (policy.canReadPatientPersonneContact()) {
			isSecured = true;
			transformedBean.setPacNom(bean.getPacNom());
			transformedBean.setPacTelephoneUn(bean.getPacTelephoneUn());
			transformedBean.setPacTelephoneDeux(bean.getPacTelephoneDeux());
			transformedBean.setPacTelephoneTrois(bean.getPacTelephoneTrois());
			transformedBean.setPacEmail(bean.getPacEmail());
			transformedBean.setPacLibelle(bean.getPacLibelle());
			transformedBean.setPacComplementAdresse(bean
					.getPacComplementAdresse());
			transformedBean.setPacQuartier(bean.getPacQuartier());
			transformedBean.setPacVille(bean.getPacVille());
			transformedBean.setPacCodePostal(bean.getPacCodePostal());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 	transformedBean.setPacNom(null);
			transformedBean.setPacTelephoneUn(null);
			transformedBean.setPacTelephoneDeux(null);
			transformedBean.setPacTelephoneTrois(null);
			transformedBean.setPacEmail(null);
			transformedBean.setPacLibelle(null);
			transformedBean.setPacComplementAdresse(null);
			transformedBean.setPacQuartier(null);
			transformedBean.setPacVille(null);
			transformedBean.setPacCodePostal(null);
			 */
		}
		if (policy.canReadPatientTuberculose()) {
			isSecured = true;
			transformedBean.setCasTuberculose(bean.getCasTuberculose());
			transformedBean.setCasIndex(bean.getCasIndex());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 			transformedBean.setCasTuberculose(new Vector<CasTuberculose>());
			transformedBean.setCasIndex(new Vector<CasIndex>());
			 */
		}
		if (policy.canReadPatientExamens()) {
			isSecured = true;
			transformedBean.setExamensBiologiques(bean.getExamensBiologiques());
			transformedBean.setSerologies(bean.getSerologies());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 			transformedBean.setExamensBiologiques(new Vector<ExamenBiologique>());
			transformedBean.setSerologies(new Vector<ExamenSerologie>());
			 */
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Unsecure a Patient bean.
	 * @param bean The Patient bean to unsecure
	 * @param roleNames set of role ids for the user
	 * @return A unsecured Patient bean
	 */
	private Patient toUnsecurePatient(Patient bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		Patient transformedBean = (Patient) dao.load(Patient.class,
				bean.getId());

		if (transformedBean == null) {
			transformedBean = new Patient();
			transformedBean.setId(bean.getId());
		}
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canEditPatientIdentification()) {
			isSecured = true;
			transformedBean.setIdentifiant(bean.getIdentifiant());
			transformedBean.setNom(bean.getNom());
			transformedBean.setSexe(bean.getSexe());
			transformedBean.setDateNaissance(bean.getDateNaissance());
			transformedBean.setAge(bean.getAge());
			transformedBean.setProfession(bean.getProfession());
			transformedBean.setCentres(bean.getCentres());
			transformedBean.setNationalite(bean.getNationalite());
			transformedBean.setPrecisionSurNationalite(bean
					.getPrecisionSurNationalite());
			transformedBean.setRecevoirCarnetTelPortable(bean
					.getRecevoirCarnetTelPortable());
		} else {
			transformedBean.setIdentifiant(null);
			transformedBean.setNom(null);
			transformedBean.setSexe(null);
			transformedBean.setDateNaissance(null);
			transformedBean.setAge(null);
			transformedBean.setProfession(null);
			transformedBean.setCentres(new Vector<CentreDiagTrait>());
			transformedBean.setNationalite(null);
			transformedBean.setPrecisionSurNationalite(null);
			transformedBean.setRecevoirCarnetTelPortable(null);
		}
		if (policy.canEditPatientContact()) {
			isSecured = true;
			transformedBean.setTelephoneUn(bean.getTelephoneUn());
			transformedBean.setTelephoneDeux(bean.getTelephoneDeux());
			transformedBean.setTelephoneTrois(bean.getTelephoneTrois());
			transformedBean.setEmail(bean.getEmail());
			transformedBean.setLibelle(bean.getLibelle());
			transformedBean.setComplementAdresse(bean.getComplementAdresse());
			transformedBean.setQuartier(bean.getQuartier());
			transformedBean.setVille(bean.getVille());
			transformedBean.setCodePostal(bean.getCodePostal());
			transformedBean.setLieuxDits(bean.getLieuxDits());
		} else {
			transformedBean.setTelephoneUn(null);
			transformedBean.setTelephoneDeux(null);
			transformedBean.setTelephoneTrois(null);
			transformedBean.setEmail(null);
			transformedBean.setLibelle(null);
			transformedBean.setComplementAdresse(null);
			transformedBean.setQuartier(null);
			transformedBean.setVille(null);
			transformedBean.setCodePostal(null);
			transformedBean.setLieuxDits(new Vector<LieuDit>());
		}
		if (policy.canEditPatientPersonneContact()) {
			isSecured = true;
			transformedBean.setPacNom(bean.getPacNom());
			transformedBean.setPacTelephoneUn(bean.getPacTelephoneUn());
			transformedBean.setPacTelephoneDeux(bean.getPacTelephoneDeux());
			transformedBean.setPacTelephoneTrois(bean.getPacTelephoneTrois());
			transformedBean.setPacEmail(bean.getPacEmail());
			transformedBean.setPacLibelle(bean.getPacLibelle());
			transformedBean.setPacComplementAdresse(bean
					.getPacComplementAdresse());
			transformedBean.setPacQuartier(bean.getPacQuartier());
			transformedBean.setPacVille(bean.getPacVille());
			transformedBean.setPacCodePostal(bean.getPacCodePostal());
		} else {
			transformedBean.setPacNom(null);
			transformedBean.setPacTelephoneUn(null);
			transformedBean.setPacTelephoneDeux(null);
			transformedBean.setPacTelephoneTrois(null);
			transformedBean.setPacEmail(null);
			transformedBean.setPacLibelle(null);
			transformedBean.setPacComplementAdresse(null);
			transformedBean.setPacQuartier(null);
			transformedBean.setPacVille(null);
			transformedBean.setPacCodePostal(null);
		}
		if (policy.canEditPatientTuberculose()) {
			isSecured = true;
			transformedBean.setCasTuberculose(bean.getCasTuberculose());
			transformedBean.setCasIndex(bean.getCasIndex());
		} else {
			transformedBean.setCasTuberculose(new Vector<CasTuberculose>());
			transformedBean.setCasIndex(new Vector<CasIndex>());
		}
		if (policy.canEditPatientExamens()) {
			isSecured = true;
			transformedBean.setExamensBiologiques(bean.getExamensBiologiques());
			transformedBean.setSerologies(bean.getSerologies());
		} else {
			transformedBean
					.setExamensBiologiques(new Vector<ExamenBiologique>());
			transformedBean.setSerologies(new Vector<ExamenSerologie>());
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Secure a CasIndex bean.
	 * 
	 * @param bean The CasIndex bean to secure
	 * @param roleNames set of role ids for the user 
	 * @return A secured CasIndex bean
	 */
	private CasIndex toSecureCasIndex(CasIndex bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		CasIndex transformedBean = new CasIndex();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canReadCasIndexDescription()) {
			isSecured = true;

			if (transformedBean.getPatient() == null
					|| bean.getPatient() == null
					|| !transformedBean.getPatient().getId()
							.equals(bean.getPatient().getId())) {
				transformedBean.setPatient(bean.getPatient());
			}

			if (transformedBean.getPatientLie() == null
					|| bean.getPatientLie() == null
					|| !transformedBean.getPatientLie().getId()
							.equals(bean.getPatientLie().getId())) {
				transformedBean.setPatientLie(bean.getPatientLie());
			}
			transformedBean.setTypeRelation(bean.getTypeRelation());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 			transformedBean.setPatient(null);
			transformedBean.setPatientLie(null);
			transformedBean.setTypeRelation(null);
			 */
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Unsecure a CasIndex bean.
	 * @param bean The CasIndex bean to unsecure
	 * @param roleNames set of role ids for the user
	 * @return A unsecured CasIndex bean
	 */
	private CasIndex toUnsecureCasIndex(CasIndex bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		CasIndex transformedBean = (CasIndex) dao.load(CasIndex.class,
				bean.getId());

		if (transformedBean == null) {
			transformedBean = new CasIndex();
			transformedBean.setId(bean.getId());
		}
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canEditCasIndexDescription()) {
			isSecured = true;

			if (transformedBean.getPatient() == null
					|| bean.getPatient() == null
					|| !transformedBean.getPatient().getId()
							.equals(bean.getPatient().getId())) {
				transformedBean.setPatient(bean.getPatient());
			}

			if (transformedBean.getPatientLie() == null
					|| bean.getPatientLie() == null
					|| !transformedBean.getPatientLie().getId()
							.equals(bean.getPatientLie().getId())) {
				transformedBean.setPatientLie(bean.getPatientLie());
			}
			transformedBean.setTypeRelation(bean.getTypeRelation());
		} else {
			transformedBean.setPatient(null);
			transformedBean.setPatientLie(null);
			transformedBean.setTypeRelation(null);
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Secure a CasTuberculose bean.
	 * 
	 * @param bean The CasTuberculose bean to secure
	 * @param roleNames set of role ids for the user 
	 * @return A secured CasTuberculose bean
	 */
	private CasTuberculose toSecureCasTuberculose(CasTuberculose bean,
			AccessPolicyImpl policy) {
		boolean isSecured = false;
		CasTuberculose transformedBean = new CasTuberculose();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canReadCasTuberculoseInformations()) {
			isSecured = true;
			transformedBean.setIdentifiant(bean.getIdentifiant());
			transformedBean.setNumRegTB(bean.getNumRegTB());

			if (transformedBean.getPatient() == null
					|| bean.getPatient() == null
					|| !transformedBean.getPatient().getId()
							.equals(bean.getPatient().getId())) {
				transformedBean.setPatient(bean.getPatient());
			}
			transformedBean.setDateDebutTraitement(bean
					.getDateDebutTraitement());
			transformedBean.setTypePatient(bean.getTypePatient());
			transformedBean.setTypePatientPrecisions(bean
					.getTypePatientPrecisions());
			transformedBean.setFormeMaladie(bean.getFormeMaladie());
			transformedBean.setExtraPulmonairePrecisions(bean
					.getExtraPulmonairePrecisions());
			transformedBean.setCotrimoxazole(bean.getCotrimoxazole());
			transformedBean.setAntiRetroViraux(bean.getAntiRetroViraux());
			transformedBean.setFumeur(bean.getFumeur());
			transformedBean.setFumeurArreter(bean.getFumeurArreter());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 	transformedBean.setIdentifiant(null);
			transformedBean.setNumRegTB(null);
			transformedBean.setPatient(null);
			transformedBean.setDateDebutTraitement(null);
			transformedBean.setTypePatient(null);
			transformedBean.setTypePatientPrecisions(null);
			transformedBean.setFormeMaladie(null);
			transformedBean.setExtraPulmonairePrecisions(null);
			transformedBean.setCotrimoxazole(null);
			transformedBean.setAntiRetroViraux(null);
			transformedBean.setFumeur(null);
			transformedBean.setFumeurArreter(null);
			 */
		}
		if (policy.canReadCasTuberculoseExamen()) {
			isSecured = true;
			transformedBean.setExamensMiscrocopies(bean
					.getExamensMiscrocopies());
			transformedBean.setExamensATB(bean.getExamensATB());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 			transformedBean.setExamensMiscrocopies(new Vector<ExamenMicroscopie>());
			transformedBean.setExamensATB(new Vector<ExamenATB>());
			 */
		}
		if (policy.canReadCasTuberculoseTraitement()) {
			isSecured = true;

			if (transformedBean.getRegimePhaseInitiale() == null
					|| bean.getRegimePhaseInitiale() == null
					|| !transformedBean.getRegimePhaseInitiale().getId()
							.equals(bean.getRegimePhaseInitiale().getId())) {
				transformedBean.setRegimePhaseInitiale(bean
						.getRegimePhaseInitiale());
			}

			if (transformedBean.getRegimePhaseContinuation() == null
					|| bean.getRegimePhaseContinuation() == null
					|| !transformedBean.getRegimePhaseContinuation().getId()
							.equals(bean.getRegimePhaseContinuation().getId())) {
				transformedBean.setRegimePhaseContinuation(bean
						.getRegimePhaseContinuation());
			}
			transformedBean.setPriseMedicamenteusePhaseInitiale(bean
					.getPriseMedicamenteusePhaseInitiale());
			transformedBean.setPriseMedicamenteusePhaseContinuation(bean
					.getPriseMedicamenteusePhaseContinuation());
			transformedBean.setRendezVous(bean.getRendezVous());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 			transformedBean.setRegimePhaseInitiale(null);
			transformedBean.setRegimePhaseContinuation(null);
			transformedBean.setPriseMedicamenteusePhaseInitiale(new Vector<PriseMedicamenteuse>());
			transformedBean.setPriseMedicamenteusePhaseContinuation(new Vector<PriseMedicamenteuse>());
			transformedBean.setRendezVous(new Vector<RendezVous>());
			 */
		}
		if (policy.canReadCasTuberculoseFinTraitement()) {
			isSecured = true;
			transformedBean.setDateFinTraitement(bean.getDateFinTraitement());
			transformedBean.setDevenirMalade(bean.getDevenirMalade());
			transformedBean.setObservation(bean.getObservation());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 	transformedBean.setDateFinTraitement(null);
			transformedBean.setDevenirMalade(null);
			transformedBean.setObservation(null);
			 */
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Unsecure a CasTuberculose bean.
	 * @param bean The CasTuberculose bean to unsecure
	 * @param roleNames set of role ids for the user
	 * @return A unsecured CasTuberculose bean
	 */
	private CasTuberculose toUnsecureCasTuberculose(CasTuberculose bean,
			AccessPolicyImpl policy) {
		boolean isSecured = false;
		CasTuberculose transformedBean = (CasTuberculose) dao.load(
				CasTuberculose.class, bean.getId());

		if (transformedBean == null) {
			transformedBean = new CasTuberculose();
			transformedBean.setId(bean.getId());
		}
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canEditCasTuberculoseInformations()) {
			isSecured = true;
			transformedBean.setIdentifiant(bean.getIdentifiant());
			transformedBean.setNumRegTB(bean.getNumRegTB());

			if (transformedBean.getPatient() == null
					|| bean.getPatient() == null
					|| !transformedBean.getPatient().getId()
							.equals(bean.getPatient().getId())) {
				transformedBean.setPatient(bean.getPatient());
			}
			transformedBean.setDateDebutTraitement(bean
					.getDateDebutTraitement());
			transformedBean.setTypePatient(bean.getTypePatient());
			transformedBean.setTypePatientPrecisions(bean
					.getTypePatientPrecisions());
			transformedBean.setFormeMaladie(bean.getFormeMaladie());
			transformedBean.setExtraPulmonairePrecisions(bean
					.getExtraPulmonairePrecisions());
			transformedBean.setCotrimoxazole(bean.getCotrimoxazole());
			transformedBean.setAntiRetroViraux(bean.getAntiRetroViraux());
			transformedBean.setFumeur(bean.getFumeur());
			transformedBean.setFumeurArreter(bean.getFumeurArreter());
		} else {
			transformedBean.setIdentifiant(null);
			transformedBean.setNumRegTB(null);
			transformedBean.setPatient(null);
			transformedBean.setDateDebutTraitement(null);
			transformedBean.setTypePatient(null);
			transformedBean.setTypePatientPrecisions(null);
			transformedBean.setFormeMaladie(null);
			transformedBean.setExtraPulmonairePrecisions(null);
			transformedBean.setCotrimoxazole(null);
			transformedBean.setAntiRetroViraux(null);
			transformedBean.setFumeur(null);
			transformedBean.setFumeurArreter(null);
		}
		if (policy.canEditCasTuberculoseExamen()) {
			isSecured = true;
			transformedBean.setExamensMiscrocopies(bean
					.getExamensMiscrocopies());
			transformedBean.setExamensATB(bean.getExamensATB());
		} else {
			transformedBean
					.setExamensMiscrocopies(new Vector<ExamenMicroscopie>());
			transformedBean.setExamensATB(new Vector<ExamenATB>());
		}
		if (policy.canEditCasTuberculoseTraitement()) {
			isSecured = true;

			if (transformedBean.getRegimePhaseInitiale() == null
					|| bean.getRegimePhaseInitiale() == null
					|| !transformedBean.getRegimePhaseInitiale().getId()
							.equals(bean.getRegimePhaseInitiale().getId())) {
				transformedBean.setRegimePhaseInitiale(bean
						.getRegimePhaseInitiale());
			}

			if (transformedBean.getRegimePhaseContinuation() == null
					|| bean.getRegimePhaseContinuation() == null
					|| !transformedBean.getRegimePhaseContinuation().getId()
							.equals(bean.getRegimePhaseContinuation().getId())) {
				transformedBean.setRegimePhaseContinuation(bean
						.getRegimePhaseContinuation());
			}
			transformedBean.setPriseMedicamenteusePhaseInitiale(bean
					.getPriseMedicamenteusePhaseInitiale());
			transformedBean.setPriseMedicamenteusePhaseContinuation(bean
					.getPriseMedicamenteusePhaseContinuation());
			transformedBean.setRendezVous(bean.getRendezVous());
		} else {
			transformedBean.setRegimePhaseInitiale(null);
			transformedBean.setRegimePhaseContinuation(null);
			transformedBean
					.setPriseMedicamenteusePhaseInitiale(new Vector<PriseMedicamenteuse>());
			transformedBean
					.setPriseMedicamenteusePhaseContinuation(new Vector<PriseMedicamenteuse>());
			transformedBean.setRendezVous(new Vector<RendezVous>());
		}
		if (policy.canEditCasTuberculoseFinTraitement()) {
			isSecured = true;
			transformedBean.setDateFinTraitement(bean.getDateFinTraitement());
			transformedBean.setDevenirMalade(bean.getDevenirMalade());
			transformedBean.setObservation(bean.getObservation());
		} else {
			transformedBean.setDateFinTraitement(null);
			transformedBean.setDevenirMalade(null);
			transformedBean.setObservation(null);
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Secure a ExamenSerologie bean.
	 * 
	 * @param bean The ExamenSerologie bean to secure
	 * @param roleNames set of role ids for the user 
	 * @return A secured ExamenSerologie bean
	 */
	private ExamenSerologie toSecureExamenSerologie(ExamenSerologie bean,
			AccessPolicyImpl policy) {
		boolean isSecured = false;
		ExamenSerologie transformedBean = new ExamenSerologie();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canReadExamenSerologieDescription()) {
			isSecured = true;

			if (transformedBean.getPatient() == null
					|| bean.getPatient() == null
					|| !transformedBean.getPatient().getId()
							.equals(bean.getPatient().getId())) {
				transformedBean.setPatient(bean.getPatient());
			}
			transformedBean.setDateTest(bean.getDateTest());
			transformedBean.setNature(bean.getNature());
			transformedBean.setResultatVIH(bean.getResultatVIH());
			transformedBean.setResultatCD4(bean.getResultatCD4());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 			transformedBean.setPatient(null);
			transformedBean.setDateTest(null);
			transformedBean.setNature(null);
			transformedBean.setResultatVIH(null);
			transformedBean.setResultatCD4(null);
			 */
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Unsecure a ExamenSerologie bean.
	 * @param bean The ExamenSerologie bean to unsecure
	 * @param roleNames set of role ids for the user
	 * @return A unsecured ExamenSerologie bean
	 */
	private ExamenSerologie toUnsecureExamenSerologie(ExamenSerologie bean,
			AccessPolicyImpl policy) {
		boolean isSecured = false;
		ExamenSerologie transformedBean = (ExamenSerologie) dao.load(
				ExamenSerologie.class, bean.getId());

		if (transformedBean == null) {
			transformedBean = new ExamenSerologie();
			transformedBean.setId(bean.getId());
		}
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canEditExamenSerologieDescription()) {
			isSecured = true;

			if (transformedBean.getPatient() == null
					|| bean.getPatient() == null
					|| !transformedBean.getPatient().getId()
							.equals(bean.getPatient().getId())) {
				transformedBean.setPatient(bean.getPatient());
			}
			transformedBean.setDateTest(bean.getDateTest());
			transformedBean.setNature(bean.getNature());
			transformedBean.setResultatVIH(bean.getResultatVIH());
			transformedBean.setResultatCD4(bean.getResultatCD4());
		} else {
			transformedBean.setPatient(null);
			transformedBean.setDateTest(null);
			transformedBean.setNature(null);
			transformedBean.setResultatVIH(null);
			transformedBean.setResultatCD4(null);
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Secure a ExamenBiologique bean.
	 * 
	 * @param bean The ExamenBiologique bean to secure
	 * @param roleNames set of role ids for the user 
	 * @return A secured ExamenBiologique bean
	 */
	private ExamenBiologique toSecureExamenBiologique(ExamenBiologique bean,
			AccessPolicyImpl policy) {
		boolean isSecured = false;
		ExamenBiologique transformedBean = new ExamenBiologique();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canReadExamenBiologiqueDescription()) {
			isSecured = true;

			if (transformedBean.getPatient() == null
					|| bean.getPatient() == null
					|| !transformedBean.getPatient().getId()
							.equals(bean.getPatient().getId())) {
				transformedBean.setPatient(bean.getPatient());
			}
			transformedBean.setDate(bean.getDate());
			transformedBean.setPoids(bean.getPoids());
			transformedBean.setObservations(bean.getObservations());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 			transformedBean.setPatient(null);
			transformedBean.setDate(null);
			transformedBean.setPoids(null);
			transformedBean.setObservations(null);
			 */
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Unsecure a ExamenBiologique bean.
	 * @param bean The ExamenBiologique bean to unsecure
	 * @param roleNames set of role ids for the user
	 * @return A unsecured ExamenBiologique bean
	 */
	private ExamenBiologique toUnsecureExamenBiologique(ExamenBiologique bean,
			AccessPolicyImpl policy) {
		boolean isSecured = false;
		ExamenBiologique transformedBean = (ExamenBiologique) dao.load(
				ExamenBiologique.class, bean.getId());

		if (transformedBean == null) {
			transformedBean = new ExamenBiologique();
			transformedBean.setId(bean.getId());
		}
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canEditExamenBiologiqueDescription()) {
			isSecured = true;

			if (transformedBean.getPatient() == null
					|| bean.getPatient() == null
					|| !transformedBean.getPatient().getId()
							.equals(bean.getPatient().getId())) {
				transformedBean.setPatient(bean.getPatient());
			}
			transformedBean.setDate(bean.getDate());
			transformedBean.setPoids(bean.getPoids());
			transformedBean.setObservations(bean.getObservations());
		} else {
			transformedBean.setPatient(null);
			transformedBean.setDate(null);
			transformedBean.setPoids(null);
			transformedBean.setObservations(null);
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Secure a ExamenMicroscopie bean.
	 * 
	 * @param bean The ExamenMicroscopie bean to secure
	 * @param roleNames set of role ids for the user 
	 * @return A secured ExamenMicroscopie bean
	 */
	private ExamenMicroscopie toSecureExamenMicroscopie(ExamenMicroscopie bean,
			AccessPolicyImpl policy) {
		boolean isSecured = false;
		ExamenMicroscopie transformedBean = new ExamenMicroscopie();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canReadExamenMicroscopieCentreExamen()) {
			isSecured = true;

			if (transformedBean.getCDT() == null
					|| bean.getCDT() == null
					|| !transformedBean.getCDT().getId()
							.equals(bean.getCDT().getId())) {
				transformedBean.setCDT(bean.getCDT());
			}

			if (transformedBean.getLaboratoireReference() == null
					|| bean.getLaboratoireReference() == null
					|| !transformedBean.getLaboratoireReference().getId()
							.equals(bean.getLaboratoireReference().getId())) {
				transformedBean.setLaboratoireReference(bean
						.getLaboratoireReference());
			}
		} else {
			/*Comment for security reason (when synchronizing administrative data) 			transformedBean.setCDT(null);
			transformedBean.setLaboratoireReference(null);
			 */
		}
		if (policy.canReadExamenMicroscopieExamen()) {
			isSecured = true;

			if (transformedBean.getCasTb() == null
					|| bean.getCasTb() == null
					|| !transformedBean.getCasTb().getId()
							.equals(bean.getCasTb().getId())) {
				transformedBean.setCasTb(bean.getCasTb());
			}
			transformedBean.setDate(bean.getDate());
			transformedBean.setRaisonDepistage(bean.getRaisonDepistage());
			transformedBean.setResultat(bean.getResultat());
			transformedBean.setObservations(bean.getObservations());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 			transformedBean.setCasTb(null);
			transformedBean.setDate(null);
			transformedBean.setRaisonDepistage(null);
			transformedBean.setResultat(null);
			transformedBean.setObservations(null);
			 */
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Unsecure a ExamenMicroscopie bean.
	 * @param bean The ExamenMicroscopie bean to unsecure
	 * @param roleNames set of role ids for the user
	 * @return A unsecured ExamenMicroscopie bean
	 */
	private ExamenMicroscopie toUnsecureExamenMicroscopie(
			ExamenMicroscopie bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		ExamenMicroscopie transformedBean = (ExamenMicroscopie) dao.load(
				ExamenMicroscopie.class, bean.getId());

		if (transformedBean == null) {
			transformedBean = new ExamenMicroscopie();
			transformedBean.setId(bean.getId());
		}
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canEditExamenMicroscopieCentreExamen()) {
			isSecured = true;

			if (transformedBean.getCDT() == null
					|| bean.getCDT() == null
					|| !transformedBean.getCDT().getId()
							.equals(bean.getCDT().getId())) {
				transformedBean.setCDT(bean.getCDT());
			}

			if (transformedBean.getLaboratoireReference() == null
					|| bean.getLaboratoireReference() == null
					|| !transformedBean.getLaboratoireReference().getId()
							.equals(bean.getLaboratoireReference().getId())) {
				transformedBean.setLaboratoireReference(bean
						.getLaboratoireReference());
			}
		} else {
			transformedBean.setCDT(null);
			transformedBean.setLaboratoireReference(null);
		}
		if (policy.canEditExamenMicroscopieExamen()) {
			isSecured = true;

			if (transformedBean.getCasTb() == null
					|| bean.getCasTb() == null
					|| !transformedBean.getCasTb().getId()
							.equals(bean.getCasTb().getId())) {
				transformedBean.setCasTb(bean.getCasTb());
			}
			transformedBean.setDate(bean.getDate());
			transformedBean.setRaisonDepistage(bean.getRaisonDepistage());
			transformedBean.setResultat(bean.getResultat());
			transformedBean.setObservations(bean.getObservations());
		} else {
			transformedBean.setCasTb(null);
			transformedBean.setDate(null);
			transformedBean.setRaisonDepistage(null);
			transformedBean.setResultat(null);
			transformedBean.setObservations(null);
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Secure a ExamenATB bean.
	 * 
	 * @param bean The ExamenATB bean to secure
	 * @param roleNames set of role ids for the user 
	 * @return A secured ExamenATB bean
	 */
	private ExamenATB toSecureExamenATB(ExamenATB bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		ExamenATB transformedBean = new ExamenATB();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canReadExamenATBCentreExamen()) {
			isSecured = true;

			if (transformedBean.getCDT() == null
					|| bean.getCDT() == null
					|| !transformedBean.getCDT().getId()
							.equals(bean.getCDT().getId())) {
				transformedBean.setCDT(bean.getCDT());
			}

			if (transformedBean.getLaboratoireReference() == null
					|| bean.getLaboratoireReference() == null
					|| !transformedBean.getLaboratoireReference().getId()
							.equals(bean.getLaboratoireReference().getId())) {
				transformedBean.setLaboratoireReference(bean
						.getLaboratoireReference());
			}
		} else {
			/*Comment for security reason (when synchronizing administrative data) 			transformedBean.setCDT(null);
			transformedBean.setLaboratoireReference(null);
			 */
		}
		if (policy.canReadExamenATBExamen()) {
			isSecured = true;

			if (transformedBean.getCasTb() == null
					|| bean.getCasTb() == null
					|| !transformedBean.getCasTb().getId()
							.equals(bean.getCasTb().getId())) {
				transformedBean.setCasTb(bean.getCasTb());
			}
			transformedBean.setDateExamen(bean.getDateExamen());
			transformedBean.setRaisonDepistage(bean.getRaisonDepistage());
			transformedBean.setResultat(bean.getResultat());
			transformedBean.setObservations(bean.getObservations());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 			transformedBean.setCasTb(null);
			transformedBean.setDateExamen(null);
			transformedBean.setRaisonDepistage(null);
			transformedBean.setResultat(null);
			transformedBean.setObservations(null);
			 */
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Unsecure a ExamenATB bean.
	 * @param bean The ExamenATB bean to unsecure
	 * @param roleNames set of role ids for the user
	 * @return A unsecured ExamenATB bean
	 */
	private ExamenATB toUnsecureExamenATB(ExamenATB bean,
			AccessPolicyImpl policy) {
		boolean isSecured = false;
		ExamenATB transformedBean = (ExamenATB) dao.load(ExamenATB.class,
				bean.getId());

		if (transformedBean == null) {
			transformedBean = new ExamenATB();
			transformedBean.setId(bean.getId());
		}
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canEditExamenATBCentreExamen()) {
			isSecured = true;

			if (transformedBean.getCDT() == null
					|| bean.getCDT() == null
					|| !transformedBean.getCDT().getId()
							.equals(bean.getCDT().getId())) {
				transformedBean.setCDT(bean.getCDT());
			}

			if (transformedBean.getLaboratoireReference() == null
					|| bean.getLaboratoireReference() == null
					|| !transformedBean.getLaboratoireReference().getId()
							.equals(bean.getLaboratoireReference().getId())) {
				transformedBean.setLaboratoireReference(bean
						.getLaboratoireReference());
			}
		} else {
			transformedBean.setCDT(null);
			transformedBean.setLaboratoireReference(null);
		}
		if (policy.canEditExamenATBExamen()) {
			isSecured = true;

			if (transformedBean.getCasTb() == null
					|| bean.getCasTb() == null
					|| !transformedBean.getCasTb().getId()
							.equals(bean.getCasTb().getId())) {
				transformedBean.setCasTb(bean.getCasTb());
			}
			transformedBean.setDateExamen(bean.getDateExamen());
			transformedBean.setRaisonDepistage(bean.getRaisonDepistage());
			transformedBean.setResultat(bean.getResultat());
			transformedBean.setObservations(bean.getObservations());
		} else {
			transformedBean.setCasTb(null);
			transformedBean.setDateExamen(null);
			transformedBean.setRaisonDepistage(null);
			transformedBean.setResultat(null);
			transformedBean.setObservations(null);
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Secure a PriseMedicamenteuse bean.
	 * 
	 * @param bean The PriseMedicamenteuse bean to secure
	 * @param roleNames set of role ids for the user 
	 * @return A secured PriseMedicamenteuse bean
	 */
	private PriseMedicamenteuse toSecurePriseMedicamenteuse(
			PriseMedicamenteuse bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		PriseMedicamenteuse transformedBean = new PriseMedicamenteuse();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canReadPriseMedicamenteuseDescription()) {
			isSecured = true;

			if (transformedBean.getPhaseIntensive() == null
					|| bean.getPhaseIntensive() == null
					|| !transformedBean.getPhaseIntensive().getId()
							.equals(bean.getPhaseIntensive().getId())) {
				transformedBean.setPhaseIntensive(bean.getPhaseIntensive());
			}

			if (transformedBean.getPhaseContinuation() == null
					|| bean.getPhaseContinuation() == null
					|| !transformedBean.getPhaseContinuation().getId()
							.equals(bean.getPhaseContinuation().getId())) {
				transformedBean.setPhaseContinuation(bean
						.getPhaseContinuation());
			}
			transformedBean.setDateEffective(bean.getDateEffective());
			transformedBean.setPrise(bean.getPrise());
			transformedBean.setCotrimoxazole(bean.getCotrimoxazole());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 			transformedBean.setPhaseIntensive(null);
			transformedBean.setPhaseContinuation(null);
			transformedBean.setDateEffective(null);
			transformedBean.setPrise(null);
			transformedBean.setCotrimoxazole(null);
			 */
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Unsecure a PriseMedicamenteuse bean.
	 * @param bean The PriseMedicamenteuse bean to unsecure
	 * @param roleNames set of role ids for the user
	 * @return A unsecured PriseMedicamenteuse bean
	 */
	private PriseMedicamenteuse toUnsecurePriseMedicamenteuse(
			PriseMedicamenteuse bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		PriseMedicamenteuse transformedBean = (PriseMedicamenteuse) dao.load(
				PriseMedicamenteuse.class, bean.getId());

		if (transformedBean == null) {
			transformedBean = new PriseMedicamenteuse();
			transformedBean.setId(bean.getId());
		}
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canEditPriseMedicamenteuseDescription()) {
			isSecured = true;

			if (transformedBean.getPhaseIntensive() == null
					|| bean.getPhaseIntensive() == null
					|| !transformedBean.getPhaseIntensive().getId()
							.equals(bean.getPhaseIntensive().getId())) {
				transformedBean.setPhaseIntensive(bean.getPhaseIntensive());
			}

			if (transformedBean.getPhaseContinuation() == null
					|| bean.getPhaseContinuation() == null
					|| !transformedBean.getPhaseContinuation().getId()
							.equals(bean.getPhaseContinuation().getId())) {
				transformedBean.setPhaseContinuation(bean
						.getPhaseContinuation());
			}
			transformedBean.setDateEffective(bean.getDateEffective());
			transformedBean.setPrise(bean.getPrise());
			transformedBean.setCotrimoxazole(bean.getCotrimoxazole());
		} else {
			transformedBean.setPhaseIntensive(null);
			transformedBean.setPhaseContinuation(null);
			transformedBean.setDateEffective(null);
			transformedBean.setPrise(null);
			transformedBean.setCotrimoxazole(null);
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Secure a RendezVous bean.
	 * 
	 * @param bean The RendezVous bean to secure
	 * @param roleNames set of role ids for the user 
	 * @return A secured RendezVous bean
	 */
	private RendezVous toSecureRendezVous(RendezVous bean,
			AccessPolicyImpl policy) {
		boolean isSecured = false;
		RendezVous transformedBean = new RendezVous();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canReadRendezVousDescription()) {
			isSecured = true;

			if (transformedBean.getCasTb() == null
					|| bean.getCasTb() == null
					|| !transformedBean.getCasTb().getId()
							.equals(bean.getCasTb().getId())) {
				transformedBean.setCasTb(bean.getCasTb());
			}
			transformedBean.setDateRendezVous(bean.getDateRendezVous());
			transformedBean.setHonore(bean.getHonore());
			transformedBean.setNombreSMSEnvoye(bean.getNombreSMSEnvoye());
			transformedBean.setCommentaire(bean.getCommentaire());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 			transformedBean.setCasTb(null);
			transformedBean.setDateRendezVous(null);
			transformedBean.setHonore(null);
			transformedBean.setNombreSMSEnvoye(null);
			transformedBean.setCommentaire(null);
			 */
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Unsecure a RendezVous bean.
	 * @param bean The RendezVous bean to unsecure
	 * @param roleNames set of role ids for the user
	 * @return A unsecured RendezVous bean
	 */
	private RendezVous toUnsecureRendezVous(RendezVous bean,
			AccessPolicyImpl policy) {
		boolean isSecured = false;
		RendezVous transformedBean = (RendezVous) dao.load(RendezVous.class,
				bean.getId());

		if (transformedBean == null) {
			transformedBean = new RendezVous();
			transformedBean.setId(bean.getId());
		}
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canEditRendezVousDescription()) {
			isSecured = true;

			if (transformedBean.getCasTb() == null
					|| bean.getCasTb() == null
					|| !transformedBean.getCasTb().getId()
							.equals(bean.getCasTb().getId())) {
				transformedBean.setCasTb(bean.getCasTb());
			}
			transformedBean.setDateRendezVous(bean.getDateRendezVous());
			transformedBean.setHonore(bean.getHonore());
			transformedBean.setNombreSMSEnvoye(bean.getNombreSMSEnvoye());
			transformedBean.setCommentaire(bean.getCommentaire());
		} else {
			transformedBean.setCasTb(null);
			transformedBean.setDateRendezVous(null);
			transformedBean.setHonore(null);
			transformedBean.setNombreSMSEnvoye(null);
			transformedBean.setCommentaire(null);
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Secure a TransfertReference bean.
	 * 
	 * @param bean The TransfertReference bean to secure
	 * @param roleNames set of role ids for the user 
	 * @return A secured TransfertReference bean
	 */
	private TransfertReference toSecureTransfertReference(
			TransfertReference bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		TransfertReference transformedBean = new TransfertReference();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canReadTransfertReferenceInformationsDepart()) {
			isSecured = true;
			transformedBean.setNature(bean.getNature());

			if (transformedBean.getRegion() == null
					|| bean.getRegion() == null
					|| !transformedBean.getRegion().getId()
							.equals(bean.getRegion().getId())) {
				transformedBean.setRegion(bean.getRegion());
			}

			if (transformedBean.getDistrictSante() == null
					|| bean.getDistrictSante() == null
					|| !transformedBean.getDistrictSante().getId()
							.equals(bean.getDistrictSante().getId())) {
				transformedBean.setDistrictSante(bean.getDistrictSante());
			}

			if (transformedBean.getCDTDepart() == null
					|| bean.getCDTDepart() == null
					|| !transformedBean.getCDTDepart().getId()
							.equals(bean.getCDTDepart().getId())) {
				transformedBean.setCDTDepart(bean.getCDTDepart());
			}

			if (transformedBean.getPatient() == null
					|| bean.getPatient() == null
					|| !transformedBean.getPatient().getId()
							.equals(bean.getPatient().getId())) {
				transformedBean.setPatient(bean.getPatient());
			}
			transformedBean.setDateDepart(bean.getDateDepart());
			transformedBean.setMotifDepart(bean.getMotifDepart());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 	transformedBean.setNature(null);
			transformedBean.setRegion(null);
			transformedBean.setDistrictSante(null);
			transformedBean.setCDTDepart(null);
			transformedBean.setPatient(null);
			transformedBean.setDateDepart(null);
			transformedBean.setMotifDepart(null);
			 */
		}
		if (policy.canReadTransfertReferenceInformationArrivee()) {
			isSecured = true;

			if (transformedBean.getRegionArrivee() == null
					|| bean.getRegionArrivee() == null
					|| !transformedBean.getRegionArrivee().getId()
							.equals(bean.getRegionArrivee().getId())) {
				transformedBean.setRegionArrivee(bean.getRegionArrivee());
			}

			if (transformedBean.getDistrictSanteArrivee() == null
					|| bean.getDistrictSanteArrivee() == null
					|| !transformedBean.getDistrictSanteArrivee().getId()
							.equals(bean.getDistrictSanteArrivee().getId())) {
				transformedBean.setDistrictSanteArrivee(bean
						.getDistrictSanteArrivee());
			}

			if (transformedBean.getCDTArrivee() == null
					|| bean.getCDTArrivee() == null
					|| !transformedBean.getCDTArrivee().getId()
							.equals(bean.getCDTArrivee().getId())) {
				transformedBean.setCDTArrivee(bean.getCDTArrivee());
			}
			transformedBean.setDateArrivee(bean.getDateArrivee());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 			transformedBean.setRegionArrivee(null);
			transformedBean.setDistrictSanteArrivee(null);
			transformedBean.setCDTArrivee(null);
			transformedBean.setDateArrivee(null);
			 */
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Unsecure a TransfertReference bean.
	 * @param bean The TransfertReference bean to unsecure
	 * @param roleNames set of role ids for the user
	 * @return A unsecured TransfertReference bean
	 */
	private TransfertReference toUnsecureTransfertReference(
			TransfertReference bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		TransfertReference transformedBean = (TransfertReference) dao.load(
				TransfertReference.class, bean.getId());

		if (transformedBean == null) {
			transformedBean = new TransfertReference();
			transformedBean.setId(bean.getId());
		}
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canEditTransfertReferenceInformationsDepart()) {
			isSecured = true;
			transformedBean.setNature(bean.getNature());

			if (transformedBean.getRegion() == null
					|| bean.getRegion() == null
					|| !transformedBean.getRegion().getId()
							.equals(bean.getRegion().getId())) {
				transformedBean.setRegion(bean.getRegion());
			}

			if (transformedBean.getDistrictSante() == null
					|| bean.getDistrictSante() == null
					|| !transformedBean.getDistrictSante().getId()
							.equals(bean.getDistrictSante().getId())) {
				transformedBean.setDistrictSante(bean.getDistrictSante());
			}

			if (transformedBean.getCDTDepart() == null
					|| bean.getCDTDepart() == null
					|| !transformedBean.getCDTDepart().getId()
							.equals(bean.getCDTDepart().getId())) {
				transformedBean.setCDTDepart(bean.getCDTDepart());
			}

			if (transformedBean.getPatient() == null
					|| bean.getPatient() == null
					|| !transformedBean.getPatient().getId()
							.equals(bean.getPatient().getId())) {
				transformedBean.setPatient(bean.getPatient());
			}
			transformedBean.setDateDepart(bean.getDateDepart());
			transformedBean.setMotifDepart(bean.getMotifDepart());
		} else {
			transformedBean.setNature(null);
			transformedBean.setRegion(null);
			transformedBean.setDistrictSante(null);
			transformedBean.setCDTDepart(null);
			transformedBean.setPatient(null);
			transformedBean.setDateDepart(null);
			transformedBean.setMotifDepart(null);
		}
		if (policy.canEditTransfertReferenceInformationArrivee()) {
			isSecured = true;

			if (transformedBean.getRegionArrivee() == null
					|| bean.getRegionArrivee() == null
					|| !transformedBean.getRegionArrivee().getId()
							.equals(bean.getRegionArrivee().getId())) {
				transformedBean.setRegionArrivee(bean.getRegionArrivee());
			}

			if (transformedBean.getDistrictSanteArrivee() == null
					|| bean.getDistrictSanteArrivee() == null
					|| !transformedBean.getDistrictSanteArrivee().getId()
							.equals(bean.getDistrictSanteArrivee().getId())) {
				transformedBean.setDistrictSanteArrivee(bean
						.getDistrictSanteArrivee());
			}

			if (transformedBean.getCDTArrivee() == null
					|| bean.getCDTArrivee() == null
					|| !transformedBean.getCDTArrivee().getId()
							.equals(bean.getCDTArrivee().getId())) {
				transformedBean.setCDTArrivee(bean.getCDTArrivee());
			}
			transformedBean.setDateArrivee(bean.getDateArrivee());
		} else {
			transformedBean.setRegionArrivee(null);
			transformedBean.setDistrictSanteArrivee(null);
			transformedBean.setCDTArrivee(null);
			transformedBean.setDateArrivee(null);
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Secure a Lot bean.
	 * 
	 * @param bean The Lot bean to secure
	 * @param roleNames set of role ids for the user 
	 * @return A secured Lot bean
	 */
	private Lot toSecureLot(Lot bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		Lot transformedBean = new Lot();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canReadLotDescription()) {
			isSecured = true;

			if (transformedBean.getRegion() == null
					|| bean.getRegion() == null
					|| !transformedBean.getRegion().getId()
							.equals(bean.getRegion().getId())) {
				transformedBean.setRegion(bean.getRegion());
			}

			if (transformedBean.getDistrictSante() == null
					|| bean.getDistrictSante() == null
					|| !transformedBean.getDistrictSante().getId()
							.equals(bean.getDistrictSante().getId())) {
				transformedBean.setDistrictSante(bean.getDistrictSante());
			}

			if (transformedBean.getCDT() == null
					|| bean.getCDT() == null
					|| !transformedBean.getCDT().getId()
							.equals(bean.getCDT().getId())) {
				transformedBean.setCDT(bean.getCDT());
			}
			transformedBean.setNumero(bean.getNumero());
			transformedBean.setType(bean.getType());

			if (transformedBean.getMedicament() == null
					|| bean.getMedicament() == null
					|| !transformedBean.getMedicament().getId()
							.equals(bean.getMedicament().getId())) {
				transformedBean.setMedicament(bean.getMedicament());
			}

			if (transformedBean.getIntrant() == null
					|| bean.getIntrant() == null
					|| !transformedBean.getIntrant().getId()
							.equals(bean.getIntrant().getId())) {
				transformedBean.setIntrant(bean.getIntrant());
			}
			transformedBean.setQuantiteInitiale(bean.getQuantiteInitiale());
			transformedBean.setQuantite(bean.getQuantite());
			transformedBean.setDatePeremption(bean.getDatePeremption());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 			transformedBean.setRegion(null);
			transformedBean.setDistrictSante(null);
			transformedBean.setCDT(null);
			transformedBean.setNumero(null);
			transformedBean.setType(null);
			transformedBean.setMedicament(null);
			transformedBean.setIntrant(null);
			transformedBean.setQuantiteInitiale(null);
			transformedBean.setQuantite(null);
			transformedBean.setDatePeremption(null);
			 */
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Unsecure a Lot bean.
	 * @param bean The Lot bean to unsecure
	 * @param roleNames set of role ids for the user
	 * @return A unsecured Lot bean
	 */
	private Lot toUnsecureLot(Lot bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		Lot transformedBean = (Lot) dao.load(Lot.class, bean.getId());

		if (transformedBean == null) {
			transformedBean = new Lot();
			transformedBean.setId(bean.getId());
		}
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canEditLotDescription()) {
			isSecured = true;

			if (transformedBean.getRegion() == null
					|| bean.getRegion() == null
					|| !transformedBean.getRegion().getId()
							.equals(bean.getRegion().getId())) {
				transformedBean.setRegion(bean.getRegion());
			}

			if (transformedBean.getDistrictSante() == null
					|| bean.getDistrictSante() == null
					|| !transformedBean.getDistrictSante().getId()
							.equals(bean.getDistrictSante().getId())) {
				transformedBean.setDistrictSante(bean.getDistrictSante());
			}

			if (transformedBean.getCDT() == null
					|| bean.getCDT() == null
					|| !transformedBean.getCDT().getId()
							.equals(bean.getCDT().getId())) {
				transformedBean.setCDT(bean.getCDT());
			}
			transformedBean.setNumero(bean.getNumero());
			transformedBean.setType(bean.getType());

			if (transformedBean.getMedicament() == null
					|| bean.getMedicament() == null
					|| !transformedBean.getMedicament().getId()
							.equals(bean.getMedicament().getId())) {
				transformedBean.setMedicament(bean.getMedicament());
			}

			if (transformedBean.getIntrant() == null
					|| bean.getIntrant() == null
					|| !transformedBean.getIntrant().getId()
							.equals(bean.getIntrant().getId())) {
				transformedBean.setIntrant(bean.getIntrant());
			}
			transformedBean.setQuantiteInitiale(bean.getQuantiteInitiale());
			transformedBean.setQuantite(bean.getQuantite());
			transformedBean.setDatePeremption(bean.getDatePeremption());
		} else {
			transformedBean.setRegion(null);
			transformedBean.setDistrictSante(null);
			transformedBean.setCDT(null);
			transformedBean.setNumero(null);
			transformedBean.setType(null);
			transformedBean.setMedicament(null);
			transformedBean.setIntrant(null);
			transformedBean.setQuantiteInitiale(null);
			transformedBean.setQuantite(null);
			transformedBean.setDatePeremption(null);
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Secure a HorsUsage bean.
	 * 
	 * @param bean The HorsUsage bean to secure
	 * @param roleNames set of role ids for the user 
	 * @return A secured HorsUsage bean
	 */
	private HorsUsage toSecureHorsUsage(HorsUsage bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		HorsUsage transformedBean = new HorsUsage();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canReadHorsUsageDescription()) {
			isSecured = true;

			if (transformedBean.getLot() == null
					|| bean.getLot() == null
					|| !transformedBean.getLot().getId()
							.equals(bean.getLot().getId())) {
				transformedBean.setLot(bean.getLot());
			}
			transformedBean.setType(bean.getType());
			transformedBean.setMotif(bean.getMotif());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 			transformedBean.setLot(null);
			transformedBean.setType(null);
			transformedBean.setMotif(null);
			 */
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Unsecure a HorsUsage bean.
	 * @param bean The HorsUsage bean to unsecure
	 * @param roleNames set of role ids for the user
	 * @return A unsecured HorsUsage bean
	 */
	private HorsUsage toUnsecureHorsUsage(HorsUsage bean,
			AccessPolicyImpl policy) {
		boolean isSecured = false;
		HorsUsage transformedBean = (HorsUsage) dao.load(HorsUsage.class,
				bean.getId());

		if (transformedBean == null) {
			transformedBean = new HorsUsage();
			transformedBean.setId(bean.getId());
		}
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canEditHorsUsageDescription()) {
			isSecured = true;

			if (transformedBean.getLot() == null
					|| bean.getLot() == null
					|| !transformedBean.getLot().getId()
							.equals(bean.getLot().getId())) {
				transformedBean.setLot(bean.getLot());
			}
			transformedBean.setType(bean.getType());
			transformedBean.setMotif(bean.getMotif());
		} else {
			transformedBean.setLot(null);
			transformedBean.setType(null);
			transformedBean.setMotif(null);
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Secure a EntreeLot bean.
	 * 
	 * @param bean The EntreeLot bean to secure
	 * @param roleNames set of role ids for the user 
	 * @return A secured EntreeLot bean
	 */
	private EntreeLot toSecureEntreeLot(EntreeLot bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		EntreeLot transformedBean = new EntreeLot();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canReadEntreeLotDescription()) {
			isSecured = true;

			if (transformedBean.getCDT() == null
					|| bean.getCDT() == null
					|| !transformedBean.getCDT().getId()
							.equals(bean.getCDT().getId())) {
				transformedBean.setCDT(bean.getCDT());
			}

			if (transformedBean.getLot() == null
					|| bean.getLot() == null
					|| !transformedBean.getLot().getId()
							.equals(bean.getLot().getId())) {
				transformedBean.setLot(bean.getLot());
			}
			transformedBean.setQuantite(bean.getQuantite());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 			transformedBean.setCDT(null);
			transformedBean.setLot(null);
			transformedBean.setQuantite(null);
			 */
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Unsecure a EntreeLot bean.
	 * @param bean The EntreeLot bean to unsecure
	 * @param roleNames set of role ids for the user
	 * @return A unsecured EntreeLot bean
	 */
	private EntreeLot toUnsecureEntreeLot(EntreeLot bean,
			AccessPolicyImpl policy) {
		boolean isSecured = false;
		EntreeLot transformedBean = (EntreeLot) dao.load(EntreeLot.class,
				bean.getId());

		if (transformedBean == null) {
			transformedBean = new EntreeLot();
			transformedBean.setId(bean.getId());
		}
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canEditEntreeLotDescription()) {
			isSecured = true;

			if (transformedBean.getCDT() == null
					|| bean.getCDT() == null
					|| !transformedBean.getCDT().getId()
							.equals(bean.getCDT().getId())) {
				transformedBean.setCDT(bean.getCDT());
			}

			if (transformedBean.getLot() == null
					|| bean.getLot() == null
					|| !transformedBean.getLot().getId()
							.equals(bean.getLot().getId())) {
				transformedBean.setLot(bean.getLot());
			}
			transformedBean.setQuantite(bean.getQuantite());
		} else {
			transformedBean.setCDT(null);
			transformedBean.setLot(null);
			transformedBean.setQuantite(null);
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Secure a SortieLot bean.
	 * 
	 * @param bean The SortieLot bean to secure
	 * @param roleNames set of role ids for the user 
	 * @return A secured SortieLot bean
	 */
	private SortieLot toSecureSortieLot(SortieLot bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		SortieLot transformedBean = new SortieLot();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canReadSortieLotDescription()) {
			isSecured = true;

			if (transformedBean.getCDT() == null
					|| bean.getCDT() == null
					|| !transformedBean.getCDT().getId()
							.equals(bean.getCDT().getId())) {
				transformedBean.setCDT(bean.getCDT());
			}

			if (transformedBean.getLot() == null
					|| bean.getLot() == null
					|| !transformedBean.getLot().getId()
							.equals(bean.getLot().getId())) {
				transformedBean.setLot(bean.getLot());
			}
			transformedBean.setQuantite(bean.getQuantite());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 			transformedBean.setCDT(null);
			transformedBean.setLot(null);
			transformedBean.setQuantite(null);
			 */
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Unsecure a SortieLot bean.
	 * @param bean The SortieLot bean to unsecure
	 * @param roleNames set of role ids for the user
	 * @return A unsecured SortieLot bean
	 */
	private SortieLot toUnsecureSortieLot(SortieLot bean,
			AccessPolicyImpl policy) {
		boolean isSecured = false;
		SortieLot transformedBean = (SortieLot) dao.load(SortieLot.class,
				bean.getId());

		if (transformedBean == null) {
			transformedBean = new SortieLot();
			transformedBean.setId(bean.getId());
		}
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canEditSortieLotDescription()) {
			isSecured = true;

			if (transformedBean.getCDT() == null
					|| bean.getCDT() == null
					|| !transformedBean.getCDT().getId()
							.equals(bean.getCDT().getId())) {
				transformedBean.setCDT(bean.getCDT());
			}

			if (transformedBean.getLot() == null
					|| bean.getLot() == null
					|| !transformedBean.getLot().getId()
							.equals(bean.getLot().getId())) {
				transformedBean.setLot(bean.getLot());
			}
			transformedBean.setQuantite(bean.getQuantite());
		} else {
			transformedBean.setCDT(null);
			transformedBean.setLot(null);
			transformedBean.setQuantite(null);
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Secure a Commande bean.
	 * 
	 * @param bean The Commande bean to secure
	 * @param roleNames set of role ids for the user 
	 * @return A secured Commande bean
	 */
	private Commande toSecureCommande(Commande bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		Commande transformedBean = new Commande();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canReadCommandeInformationsDepart()) {
			isSecured = true;
			transformedBean.setDate(bean.getDate());

			if (transformedBean.getRegion() == null
					|| bean.getRegion() == null
					|| !transformedBean.getRegion().getId()
							.equals(bean.getRegion().getId())) {
				transformedBean.setRegion(bean.getRegion());
			}

			if (transformedBean.getDistrictSante() == null
					|| bean.getDistrictSante() == null
					|| !transformedBean.getDistrictSante().getId()
							.equals(bean.getDistrictSante().getId())) {
				transformedBean.setDistrictSante(bean.getDistrictSante());
			}

			if (transformedBean.getCDT() == null
					|| bean.getCDT() == null
					|| !transformedBean.getCDT().getId()
							.equals(bean.getCDT().getId())) {
				transformedBean.setCDT(bean.getCDT());
			}
			transformedBean.setMedicaments(bean.getMedicaments());
			transformedBean.setIntrants(bean.getIntrants());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 	transformedBean.setDate(null);
			transformedBean.setRegion(null);
			transformedBean.setDistrictSante(null);
			transformedBean.setCDT(null);
			transformedBean.setMedicaments(new Vector<DetailCommandeMedicament>());
			transformedBean.setIntrants(new Vector<DetailCommandeIntrant>());
			 */
		}
		if (policy.canReadCommandeRegionApprobation()) {
			isSecured = true;
			transformedBean.setApprouveeRegion(bean.getApprouveeRegion());
			transformedBean.setMotifRejetRegion(bean.getMotifRejetRegion());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 	transformedBean.setApprouveeRegion(null);
			transformedBean.setMotifRejetRegion(null);
			 */
		}
		if (policy.canReadCommandeGtcApprobation()) {
			isSecured = true;
			transformedBean.setApprouveeGTC(bean.getApprouveeGTC());
			transformedBean.setMotifRejetGTC(bean.getMotifRejetGTC());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 	transformedBean.setApprouveeGTC(null);
			transformedBean.setMotifRejetGTC(null);
			 */
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Unsecure a Commande bean.
	 * @param bean The Commande bean to unsecure
	 * @param roleNames set of role ids for the user
	 * @return A unsecured Commande bean
	 */
	private Commande toUnsecureCommande(Commande bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		Commande transformedBean = (Commande) dao.load(Commande.class,
				bean.getId());

		if (transformedBean == null) {
			transformedBean = new Commande();
			transformedBean.setId(bean.getId());
		}
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canEditCommandeInformationsDepart()) {
			isSecured = true;
			transformedBean.setDate(bean.getDate());

			if (transformedBean.getRegion() == null
					|| bean.getRegion() == null
					|| !transformedBean.getRegion().getId()
							.equals(bean.getRegion().getId())) {
				transformedBean.setRegion(bean.getRegion());
			}

			if (transformedBean.getDistrictSante() == null
					|| bean.getDistrictSante() == null
					|| !transformedBean.getDistrictSante().getId()
							.equals(bean.getDistrictSante().getId())) {
				transformedBean.setDistrictSante(bean.getDistrictSante());
			}

			if (transformedBean.getCDT() == null
					|| bean.getCDT() == null
					|| !transformedBean.getCDT().getId()
							.equals(bean.getCDT().getId())) {
				transformedBean.setCDT(bean.getCDT());
			}
			transformedBean.setMedicaments(bean.getMedicaments());
			transformedBean.setIntrants(bean.getIntrants());
		} else {
			transformedBean.setDate(null);
			transformedBean.setRegion(null);
			transformedBean.setDistrictSante(null);
			transformedBean.setCDT(null);
			transformedBean
					.setMedicaments(new Vector<DetailCommandeMedicament>());
			transformedBean.setIntrants(new Vector<DetailCommandeIntrant>());
		}
		if (policy.canEditCommandeRegionApprobation()) {
			isSecured = true;
			transformedBean.setApprouveeRegion(bean.getApprouveeRegion());
			transformedBean.setMotifRejetRegion(bean.getMotifRejetRegion());
		} else {
			transformedBean.setApprouveeRegion(null);
			transformedBean.setMotifRejetRegion(null);
		}
		if (policy.canEditCommandeGtcApprobation()) {
			isSecured = true;
			transformedBean.setApprouveeGTC(bean.getApprouveeGTC());
			transformedBean.setMotifRejetGTC(bean.getMotifRejetGTC());
		} else {
			transformedBean.setApprouveeGTC(null);
			transformedBean.setMotifRejetGTC(null);
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Secure a DetailCommandeMedicament bean.
	 * 
	 * @param bean The DetailCommandeMedicament bean to secure
	 * @param roleNames set of role ids for the user 
	 * @return A secured DetailCommandeMedicament bean
	 */
	private DetailCommandeMedicament toSecureDetailCommandeMedicament(
			DetailCommandeMedicament bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		DetailCommandeMedicament transformedBean = new DetailCommandeMedicament();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canReadDetailCommandeMedicamentDescription()) {
			isSecured = true;

			if (transformedBean.getCommande() == null
					|| bean.getCommande() == null
					|| !transformedBean.getCommande().getId()
							.equals(bean.getCommande().getId())) {
				transformedBean.setCommande(bean.getCommande());
			}

			if (transformedBean.getMedicament() == null
					|| bean.getMedicament() == null
					|| !transformedBean.getMedicament().getId()
							.equals(bean.getMedicament().getId())) {
				transformedBean.setMedicament(bean.getMedicament());
			}
			transformedBean.setQuantiteRequise(bean.getQuantiteRequise());
			transformedBean.setQuantiteEnStock(bean.getQuantiteEnStock());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 			transformedBean.setCommande(null);
			transformedBean.setMedicament(null);
			transformedBean.setQuantiteRequise(null);
			transformedBean.setQuantiteEnStock(null);
			 */
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Unsecure a DetailCommandeMedicament bean.
	 * @param bean The DetailCommandeMedicament bean to unsecure
	 * @param roleNames set of role ids for the user
	 * @return A unsecured DetailCommandeMedicament bean
	 */
	private DetailCommandeMedicament toUnsecureDetailCommandeMedicament(
			DetailCommandeMedicament bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		DetailCommandeMedicament transformedBean = (DetailCommandeMedicament) dao
				.load(DetailCommandeMedicament.class, bean.getId());

		if (transformedBean == null) {
			transformedBean = new DetailCommandeMedicament();
			transformedBean.setId(bean.getId());
		}
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canEditDetailCommandeMedicamentDescription()) {
			isSecured = true;

			if (transformedBean.getCommande() == null
					|| bean.getCommande() == null
					|| !transformedBean.getCommande().getId()
							.equals(bean.getCommande().getId())) {
				transformedBean.setCommande(bean.getCommande());
			}

			if (transformedBean.getMedicament() == null
					|| bean.getMedicament() == null
					|| !transformedBean.getMedicament().getId()
							.equals(bean.getMedicament().getId())) {
				transformedBean.setMedicament(bean.getMedicament());
			}
			transformedBean.setQuantiteRequise(bean.getQuantiteRequise());
			transformedBean.setQuantiteEnStock(bean.getQuantiteEnStock());
		} else {
			transformedBean.setCommande(null);
			transformedBean.setMedicament(null);
			transformedBean.setQuantiteRequise(null);
			transformedBean.setQuantiteEnStock(null);
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Secure a DetailCommandeIntrant bean.
	 * 
	 * @param bean The DetailCommandeIntrant bean to secure
	 * @param roleNames set of role ids for the user 
	 * @return A secured DetailCommandeIntrant bean
	 */
	private DetailCommandeIntrant toSecureDetailCommandeIntrant(
			DetailCommandeIntrant bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		DetailCommandeIntrant transformedBean = new DetailCommandeIntrant();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canReadDetailCommandeIntrantDescription()) {
			isSecured = true;

			if (transformedBean.getCommande() == null
					|| bean.getCommande() == null
					|| !transformedBean.getCommande().getId()
							.equals(bean.getCommande().getId())) {
				transformedBean.setCommande(bean.getCommande());
			}

			if (transformedBean.getIntrant() == null
					|| bean.getIntrant() == null
					|| !transformedBean.getIntrant().getId()
							.equals(bean.getIntrant().getId())) {
				transformedBean.setIntrant(bean.getIntrant());
			}
			transformedBean.setQuantiteRequise(bean.getQuantiteRequise());
			transformedBean.setQuantiteEnStock(bean.getQuantiteEnStock());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 			transformedBean.setCommande(null);
			transformedBean.setIntrant(null);
			transformedBean.setQuantiteRequise(null);
			transformedBean.setQuantiteEnStock(null);
			 */
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Unsecure a DetailCommandeIntrant bean.
	 * @param bean The DetailCommandeIntrant bean to unsecure
	 * @param roleNames set of role ids for the user
	 * @return A unsecured DetailCommandeIntrant bean
	 */
	private DetailCommandeIntrant toUnsecureDetailCommandeIntrant(
			DetailCommandeIntrant bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		DetailCommandeIntrant transformedBean = (DetailCommandeIntrant) dao
				.load(DetailCommandeIntrant.class, bean.getId());

		if (transformedBean == null) {
			transformedBean = new DetailCommandeIntrant();
			transformedBean.setId(bean.getId());
		}
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canEditDetailCommandeIntrantDescription()) {
			isSecured = true;

			if (transformedBean.getCommande() == null
					|| bean.getCommande() == null
					|| !transformedBean.getCommande().getId()
							.equals(bean.getCommande().getId())) {
				transformedBean.setCommande(bean.getCommande());
			}

			if (transformedBean.getIntrant() == null
					|| bean.getIntrant() == null
					|| !transformedBean.getIntrant().getId()
							.equals(bean.getIntrant().getId())) {
				transformedBean.setIntrant(bean.getIntrant());
			}
			transformedBean.setQuantiteRequise(bean.getQuantiteRequise());
			transformedBean.setQuantiteEnStock(bean.getQuantiteEnStock());
		} else {
			transformedBean.setCommande(null);
			transformedBean.setIntrant(null);
			transformedBean.setQuantiteRequise(null);
			transformedBean.setQuantiteEnStock(null);
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Secure a Reception bean.
	 * 
	 * @param bean The Reception bean to secure
	 * @param roleNames set of role ids for the user 
	 * @return A secured Reception bean
	 */
	private Reception toSecureReception(Reception bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		Reception transformedBean = new Reception();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canReadReceptionDescription()) {
			isSecured = true;

			if (transformedBean.getRegion() == null
					|| bean.getRegion() == null
					|| !transformedBean.getRegion().getId()
							.equals(bean.getRegion().getId())) {
				transformedBean.setRegion(bean.getRegion());
			}

			if (transformedBean.getDistrictSante() == null
					|| bean.getDistrictSante() == null
					|| !transformedBean.getDistrictSante().getId()
							.equals(bean.getDistrictSante().getId())) {
				transformedBean.setDistrictSante(bean.getDistrictSante());
			}

			if (transformedBean.getCDT() == null
					|| bean.getCDT() == null
					|| !transformedBean.getCDT().getId()
							.equals(bean.getCDT().getId())) {
				transformedBean.setCDT(bean.getCDT());
			}

			if (transformedBean.getCommande() == null
					|| bean.getCommande() == null
					|| !transformedBean.getCommande().getId()
							.equals(bean.getCommande().getId())) {
				transformedBean.setCommande(bean.getCommande());
			}
			transformedBean.setDateReception(bean.getDateReception());
			transformedBean.setMedicaments(bean.getMedicaments());
			transformedBean.setIntrants(bean.getIntrants());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 			transformedBean.setRegion(null);
			transformedBean.setDistrictSante(null);
			transformedBean.setCDT(null);
			transformedBean.setCommande(null);
			transformedBean.setDateReception(null);
			transformedBean.setMedicaments(new Vector<DetailReceptionMedicament>());
			transformedBean.setIntrants(new Vector<DetailReceptionIntrant>());
			 */
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Unsecure a Reception bean.
	 * @param bean The Reception bean to unsecure
	 * @param roleNames set of role ids for the user
	 * @return A unsecured Reception bean
	 */
	private Reception toUnsecureReception(Reception bean,
			AccessPolicyImpl policy) {
		boolean isSecured = false;
		Reception transformedBean = (Reception) dao.load(Reception.class,
				bean.getId());

		if (transformedBean == null) {
			transformedBean = new Reception();
			transformedBean.setId(bean.getId());
		}
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canEditReceptionDescription()) {
			isSecured = true;

			if (transformedBean.getRegion() == null
					|| bean.getRegion() == null
					|| !transformedBean.getRegion().getId()
							.equals(bean.getRegion().getId())) {
				transformedBean.setRegion(bean.getRegion());
			}

			if (transformedBean.getDistrictSante() == null
					|| bean.getDistrictSante() == null
					|| !transformedBean.getDistrictSante().getId()
							.equals(bean.getDistrictSante().getId())) {
				transformedBean.setDistrictSante(bean.getDistrictSante());
			}

			if (transformedBean.getCDT() == null
					|| bean.getCDT() == null
					|| !transformedBean.getCDT().getId()
							.equals(bean.getCDT().getId())) {
				transformedBean.setCDT(bean.getCDT());
			}

			if (transformedBean.getCommande() == null
					|| bean.getCommande() == null
					|| !transformedBean.getCommande().getId()
							.equals(bean.getCommande().getId())) {
				transformedBean.setCommande(bean.getCommande());
			}
			transformedBean.setDateReception(bean.getDateReception());
			transformedBean.setMedicaments(bean.getMedicaments());
			transformedBean.setIntrants(bean.getIntrants());
		} else {
			transformedBean.setRegion(null);
			transformedBean.setDistrictSante(null);
			transformedBean.setCDT(null);
			transformedBean.setCommande(null);
			transformedBean.setDateReception(null);
			transformedBean
					.setMedicaments(new Vector<DetailReceptionMedicament>());
			transformedBean.setIntrants(new Vector<DetailReceptionIntrant>());
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Secure a DetailReceptionMedicament bean.
	 * 
	 * @param bean The DetailReceptionMedicament bean to secure
	 * @param roleNames set of role ids for the user 
	 * @return A secured DetailReceptionMedicament bean
	 */
	private DetailReceptionMedicament toSecureDetailReceptionMedicament(
			DetailReceptionMedicament bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		DetailReceptionMedicament transformedBean = new DetailReceptionMedicament();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canReadDetailReceptionMedicamentDescription()) {
			isSecured = true;

			if (transformedBean.getReception() == null
					|| bean.getReception() == null
					|| !transformedBean.getReception().getId()
							.equals(bean.getReception().getId())) {
				transformedBean.setReception(bean.getReception());
			}

			if (transformedBean.getCommande() == null
					|| bean.getCommande() == null
					|| !transformedBean.getCommande().getId()
							.equals(bean.getCommande().getId())) {
				transformedBean.setCommande(bean.getCommande());
			}

			if (transformedBean.getDetailCommande() == null
					|| bean.getDetailCommande() == null
					|| !transformedBean.getDetailCommande().getId()
							.equals(bean.getDetailCommande().getId())) {
				transformedBean.setDetailCommande(bean.getDetailCommande());
			}

			if (transformedBean.getEntreeLot() == null
					|| bean.getEntreeLot() == null
					|| !transformedBean.getEntreeLot().getId()
							.equals(bean.getEntreeLot().getId())) {
				transformedBean.setEntreeLot(bean.getEntreeLot());
			}
		} else {
			/*Comment for security reason (when synchronizing administrative data) 			transformedBean.setReception(null);
			transformedBean.setCommande(null);
			transformedBean.setDetailCommande(null);
			transformedBean.setEntreeLot(null);
			 */
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Unsecure a DetailReceptionMedicament bean.
	 * @param bean The DetailReceptionMedicament bean to unsecure
	 * @param roleNames set of role ids for the user
	 * @return A unsecured DetailReceptionMedicament bean
	 */
	private DetailReceptionMedicament toUnsecureDetailReceptionMedicament(
			DetailReceptionMedicament bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		DetailReceptionMedicament transformedBean = (DetailReceptionMedicament) dao
				.load(DetailReceptionMedicament.class, bean.getId());

		if (transformedBean == null) {
			transformedBean = new DetailReceptionMedicament();
			transformedBean.setId(bean.getId());
		}
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canEditDetailReceptionMedicamentDescription()) {
			isSecured = true;

			if (transformedBean.getReception() == null
					|| bean.getReception() == null
					|| !transformedBean.getReception().getId()
							.equals(bean.getReception().getId())) {
				transformedBean.setReception(bean.getReception());
			}

			if (transformedBean.getCommande() == null
					|| bean.getCommande() == null
					|| !transformedBean.getCommande().getId()
							.equals(bean.getCommande().getId())) {
				transformedBean.setCommande(bean.getCommande());
			}

			if (transformedBean.getDetailCommande() == null
					|| bean.getDetailCommande() == null
					|| !transformedBean.getDetailCommande().getId()
							.equals(bean.getDetailCommande().getId())) {
				transformedBean.setDetailCommande(bean.getDetailCommande());
			}

			if (transformedBean.getEntreeLot() == null
					|| bean.getEntreeLot() == null
					|| !transformedBean.getEntreeLot().getId()
							.equals(bean.getEntreeLot().getId())) {
				transformedBean.setEntreeLot(bean.getEntreeLot());
			}
		} else {
			transformedBean.setReception(null);
			transformedBean.setCommande(null);
			transformedBean.setDetailCommande(null);
			transformedBean.setEntreeLot(null);
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Secure a DetailReceptionIntrant bean.
	 * 
	 * @param bean The DetailReceptionIntrant bean to secure
	 * @param roleNames set of role ids for the user 
	 * @return A secured DetailReceptionIntrant bean
	 */
	private DetailReceptionIntrant toSecureDetailReceptionIntrant(
			DetailReceptionIntrant bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		DetailReceptionIntrant transformedBean = new DetailReceptionIntrant();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canReadDetailReceptionIntrantDescription()) {
			isSecured = true;

			if (transformedBean.getReception() == null
					|| bean.getReception() == null
					|| !transformedBean.getReception().getId()
							.equals(bean.getReception().getId())) {
				transformedBean.setReception(bean.getReception());
			}

			if (transformedBean.getCommande() == null
					|| bean.getCommande() == null
					|| !transformedBean.getCommande().getId()
							.equals(bean.getCommande().getId())) {
				transformedBean.setCommande(bean.getCommande());
			}

			if (transformedBean.getDetailCommande() == null
					|| bean.getDetailCommande() == null
					|| !transformedBean.getDetailCommande().getId()
							.equals(bean.getDetailCommande().getId())) {
				transformedBean.setDetailCommande(bean.getDetailCommande());
			}

			if (transformedBean.getEntreeLot() == null
					|| bean.getEntreeLot() == null
					|| !transformedBean.getEntreeLot().getId()
							.equals(bean.getEntreeLot().getId())) {
				transformedBean.setEntreeLot(bean.getEntreeLot());
			}
		} else {
			/*Comment for security reason (when synchronizing administrative data) 			transformedBean.setReception(null);
			transformedBean.setCommande(null);
			transformedBean.setDetailCommande(null);
			transformedBean.setEntreeLot(null);
			 */
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Unsecure a DetailReceptionIntrant bean.
	 * @param bean The DetailReceptionIntrant bean to unsecure
	 * @param roleNames set of role ids for the user
	 * @return A unsecured DetailReceptionIntrant bean
	 */
	private DetailReceptionIntrant toUnsecureDetailReceptionIntrant(
			DetailReceptionIntrant bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		DetailReceptionIntrant transformedBean = (DetailReceptionIntrant) dao
				.load(DetailReceptionIntrant.class, bean.getId());

		if (transformedBean == null) {
			transformedBean = new DetailReceptionIntrant();
			transformedBean.setId(bean.getId());
		}
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canEditDetailReceptionIntrantDescription()) {
			isSecured = true;

			if (transformedBean.getReception() == null
					|| bean.getReception() == null
					|| !transformedBean.getReception().getId()
							.equals(bean.getReception().getId())) {
				transformedBean.setReception(bean.getReception());
			}

			if (transformedBean.getCommande() == null
					|| bean.getCommande() == null
					|| !transformedBean.getCommande().getId()
							.equals(bean.getCommande().getId())) {
				transformedBean.setCommande(bean.getCommande());
			}

			if (transformedBean.getDetailCommande() == null
					|| bean.getDetailCommande() == null
					|| !transformedBean.getDetailCommande().getId()
							.equals(bean.getDetailCommande().getId())) {
				transformedBean.setDetailCommande(bean.getDetailCommande());
			}

			if (transformedBean.getEntreeLot() == null
					|| bean.getEntreeLot() == null
					|| !transformedBean.getEntreeLot().getId()
							.equals(bean.getEntreeLot().getId())) {
				transformedBean.setEntreeLot(bean.getEntreeLot());
			}
		} else {
			transformedBean.setReception(null);
			transformedBean.setCommande(null);
			transformedBean.setDetailCommande(null);
			transformedBean.setEntreeLot(null);
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Secure a Ravitaillement bean.
	 * 
	 * @param bean The Ravitaillement bean to secure
	 * @param roleNames set of role ids for the user 
	 * @return A secured Ravitaillement bean
	 */
	private Ravitaillement toSecureRavitaillement(Ravitaillement bean,
			AccessPolicyImpl policy) {
		boolean isSecured = false;
		Ravitaillement transformedBean = new Ravitaillement();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canReadRavitaillementInformationsDepart()) {
			isSecured = true;

			if (transformedBean.getRegion() == null
					|| bean.getRegion() == null
					|| !transformedBean.getRegion().getId()
							.equals(bean.getRegion().getId())) {
				transformedBean.setRegion(bean.getRegion());
			}

			if (transformedBean.getDistrictSante() == null
					|| bean.getDistrictSante() == null
					|| !transformedBean.getDistrictSante().getId()
							.equals(bean.getDistrictSante().getId())) {
				transformedBean.setDistrictSante(bean.getDistrictSante());
			}

			if (transformedBean.getCDTDepart() == null
					|| bean.getCDTDepart() == null
					|| !transformedBean.getCDTDepart().getId()
							.equals(bean.getCDTDepart().getId())) {
				transformedBean.setCDTDepart(bean.getCDTDepart());
			}
			transformedBean.setDateDepart(bean.getDateDepart());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 			transformedBean.setRegion(null);
			transformedBean.setDistrictSante(null);
			transformedBean.setCDTDepart(null);
			transformedBean.setDateDepart(null);
			 */
		}
		if (policy.canReadRavitaillementInformationArrivee()) {
			isSecured = true;

			if (transformedBean.getRegionArrivee() == null
					|| bean.getRegionArrivee() == null
					|| !transformedBean.getRegionArrivee().getId()
							.equals(bean.getRegionArrivee().getId())) {
				transformedBean.setRegionArrivee(bean.getRegionArrivee());
			}

			if (transformedBean.getDistrictSanteArrivee() == null
					|| bean.getDistrictSanteArrivee() == null
					|| !transformedBean.getDistrictSanteArrivee().getId()
							.equals(bean.getDistrictSanteArrivee().getId())) {
				transformedBean.setDistrictSanteArrivee(bean
						.getDistrictSanteArrivee());
			}

			if (transformedBean.getCDTArrivee() == null
					|| bean.getCDTArrivee() == null
					|| !transformedBean.getCDTArrivee().getId()
							.equals(bean.getCDTArrivee().getId())) {
				transformedBean.setCDTArrivee(bean.getCDTArrivee());
			}
			transformedBean.setDateArrivee(bean.getDateArrivee());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 			transformedBean.setRegionArrivee(null);
			transformedBean.setDistrictSanteArrivee(null);
			transformedBean.setCDTArrivee(null);
			transformedBean.setDateArrivee(null);
			 */
		}
		if (policy.canReadRavitaillementDetail()) {
			isSecured = true;
			transformedBean.setDetails(bean.getDetails());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 			transformedBean.setDetails(new Vector<DetailRavitaillement>());
			 */
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Unsecure a Ravitaillement bean.
	 * @param bean The Ravitaillement bean to unsecure
	 * @param roleNames set of role ids for the user
	 * @return A unsecured Ravitaillement bean
	 */
	private Ravitaillement toUnsecureRavitaillement(Ravitaillement bean,
			AccessPolicyImpl policy) {
		boolean isSecured = false;
		Ravitaillement transformedBean = (Ravitaillement) dao.load(
				Ravitaillement.class, bean.getId());

		if (transformedBean == null) {
			transformedBean = new Ravitaillement();
			transformedBean.setId(bean.getId());
		}
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canEditRavitaillementInformationsDepart()) {
			isSecured = true;

			if (transformedBean.getRegion() == null
					|| bean.getRegion() == null
					|| !transformedBean.getRegion().getId()
							.equals(bean.getRegion().getId())) {
				transformedBean.setRegion(bean.getRegion());
			}

			if (transformedBean.getDistrictSante() == null
					|| bean.getDistrictSante() == null
					|| !transformedBean.getDistrictSante().getId()
							.equals(bean.getDistrictSante().getId())) {
				transformedBean.setDistrictSante(bean.getDistrictSante());
			}

			if (transformedBean.getCDTDepart() == null
					|| bean.getCDTDepart() == null
					|| !transformedBean.getCDTDepart().getId()
							.equals(bean.getCDTDepart().getId())) {
				transformedBean.setCDTDepart(bean.getCDTDepart());
			}
			transformedBean.setDateDepart(bean.getDateDepart());
		} else {
			transformedBean.setRegion(null);
			transformedBean.setDistrictSante(null);
			transformedBean.setCDTDepart(null);
			transformedBean.setDateDepart(null);
		}
		if (policy.canEditRavitaillementInformationArrivee()) {
			isSecured = true;

			if (transformedBean.getRegionArrivee() == null
					|| bean.getRegionArrivee() == null
					|| !transformedBean.getRegionArrivee().getId()
							.equals(bean.getRegionArrivee().getId())) {
				transformedBean.setRegionArrivee(bean.getRegionArrivee());
			}

			if (transformedBean.getDistrictSanteArrivee() == null
					|| bean.getDistrictSanteArrivee() == null
					|| !transformedBean.getDistrictSanteArrivee().getId()
							.equals(bean.getDistrictSanteArrivee().getId())) {
				transformedBean.setDistrictSanteArrivee(bean
						.getDistrictSanteArrivee());
			}

			if (transformedBean.getCDTArrivee() == null
					|| bean.getCDTArrivee() == null
					|| !transformedBean.getCDTArrivee().getId()
							.equals(bean.getCDTArrivee().getId())) {
				transformedBean.setCDTArrivee(bean.getCDTArrivee());
			}
			transformedBean.setDateArrivee(bean.getDateArrivee());
		} else {
			transformedBean.setRegionArrivee(null);
			transformedBean.setDistrictSanteArrivee(null);
			transformedBean.setCDTArrivee(null);
			transformedBean.setDateArrivee(null);
		}
		if (policy.canEditRavitaillementDetail()) {
			isSecured = true;
			transformedBean.setDetails(bean.getDetails());
		} else {
			transformedBean.setDetails(new Vector<DetailRavitaillement>());
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Secure a DetailRavitaillement bean.
	 * 
	 * @param bean The DetailRavitaillement bean to secure
	 * @param roleNames set of role ids for the user 
	 * @return A secured DetailRavitaillement bean
	 */
	private DetailRavitaillement toSecureDetailRavitaillement(
			DetailRavitaillement bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		DetailRavitaillement transformedBean = new DetailRavitaillement();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canReadDetailRavitaillementDescription()) {
			isSecured = true;

			if (transformedBean.getRavitaillement() == null
					|| bean.getRavitaillement() == null
					|| !transformedBean.getRavitaillement().getId()
							.equals(bean.getRavitaillement().getId())) {
				transformedBean.setRavitaillement(bean.getRavitaillement());
			}
		} else {
			/*Comment for security reason (when synchronizing administrative data) 			transformedBean.setRavitaillement(null);
			 */
		}
		if (policy.canReadDetailRavitaillementSortie()) {
			isSecured = true;

			if (transformedBean.getSortieLot() == null
					|| bean.getSortieLot() == null
					|| !transformedBean.getSortieLot().getId()
							.equals(bean.getSortieLot().getId())) {
				transformedBean.setSortieLot(bean.getSortieLot());
			}
		} else {
			/*Comment for security reason (when synchronizing administrative data) 			transformedBean.setSortieLot(null);
			 */
		}
		if (policy.canReadDetailRavitaillementEntree()) {
			isSecured = true;

			if (transformedBean.getEntreeLot() == null
					|| bean.getEntreeLot() == null
					|| !transformedBean.getEntreeLot().getId()
							.equals(bean.getEntreeLot().getId())) {
				transformedBean.setEntreeLot(bean.getEntreeLot());
			}
		} else {
			/*Comment for security reason (when synchronizing administrative data) 			transformedBean.setEntreeLot(null);
			 */
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Unsecure a DetailRavitaillement bean.
	 * @param bean The DetailRavitaillement bean to unsecure
	 * @param roleNames set of role ids for the user
	 * @return A unsecured DetailRavitaillement bean
	 */
	private DetailRavitaillement toUnsecureDetailRavitaillement(
			DetailRavitaillement bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		DetailRavitaillement transformedBean = (DetailRavitaillement) dao.load(
				DetailRavitaillement.class, bean.getId());

		if (transformedBean == null) {
			transformedBean = new DetailRavitaillement();
			transformedBean.setId(bean.getId());
		}
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canEditDetailRavitaillementDescription()) {
			isSecured = true;

			if (transformedBean.getRavitaillement() == null
					|| bean.getRavitaillement() == null
					|| !transformedBean.getRavitaillement().getId()
							.equals(bean.getRavitaillement().getId())) {
				transformedBean.setRavitaillement(bean.getRavitaillement());
			}
		} else {
			transformedBean.setRavitaillement(null);
		}
		if (policy.canEditDetailRavitaillementSortie()) {
			isSecured = true;

			if (transformedBean.getSortieLot() == null
					|| bean.getSortieLot() == null
					|| !transformedBean.getSortieLot().getId()
							.equals(bean.getSortieLot().getId())) {
				transformedBean.setSortieLot(bean.getSortieLot());
			}
		} else {
			transformedBean.setSortieLot(null);
		}
		if (policy.canEditDetailRavitaillementEntree()) {
			isSecured = true;

			if (transformedBean.getEntreeLot() == null
					|| bean.getEntreeLot() == null
					|| !transformedBean.getEntreeLot().getId()
							.equals(bean.getEntreeLot().getId())) {
				transformedBean.setEntreeLot(bean.getEntreeLot());
			}
		} else {
			transformedBean.setEntreeLot(null);
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Secure a Inventaire bean.
	 * 
	 * @param bean The Inventaire bean to secure
	 * @param roleNames set of role ids for the user 
	 * @return A secured Inventaire bean
	 */
	private Inventaire toSecureInventaire(Inventaire bean,
			AccessPolicyImpl policy) {
		boolean isSecured = false;
		Inventaire transformedBean = new Inventaire();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canReadInventaireInformationsDepart()) {
			isSecured = true;
			transformedBean.setDate(bean.getDate());

			if (transformedBean.getRegion() == null
					|| bean.getRegion() == null
					|| !transformedBean.getRegion().getId()
							.equals(bean.getRegion().getId())) {
				transformedBean.setRegion(bean.getRegion());
			}

			if (transformedBean.getDistrictSante() == null
					|| bean.getDistrictSante() == null
					|| !transformedBean.getDistrictSante().getId()
							.equals(bean.getDistrictSante().getId())) {
				transformedBean.setDistrictSante(bean.getDistrictSante());
			}

			if (transformedBean.getCDT() == null
					|| bean.getCDT() == null
					|| !transformedBean.getCDT().getId()
							.equals(bean.getCDT().getId())) {
				transformedBean.setCDT(bean.getCDT());
			}
			transformedBean.setDetails(bean.getDetails());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 	transformedBean.setDate(null);
			transformedBean.setRegion(null);
			transformedBean.setDistrictSante(null);
			transformedBean.setCDT(null);
			transformedBean.setDetails(new Vector<DetailInventaire>());
			 */
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Unsecure a Inventaire bean.
	 * @param bean The Inventaire bean to unsecure
	 * @param roleNames set of role ids for the user
	 * @return A unsecured Inventaire bean
	 */
	private Inventaire toUnsecureInventaire(Inventaire bean,
			AccessPolicyImpl policy) {
		boolean isSecured = false;
		Inventaire transformedBean = (Inventaire) dao.load(Inventaire.class,
				bean.getId());

		if (transformedBean == null) {
			transformedBean = new Inventaire();
			transformedBean.setId(bean.getId());
		}
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canEditInventaireInformationsDepart()) {
			isSecured = true;
			transformedBean.setDate(bean.getDate());

			if (transformedBean.getRegion() == null
					|| bean.getRegion() == null
					|| !transformedBean.getRegion().getId()
							.equals(bean.getRegion().getId())) {
				transformedBean.setRegion(bean.getRegion());
			}

			if (transformedBean.getDistrictSante() == null
					|| bean.getDistrictSante() == null
					|| !transformedBean.getDistrictSante().getId()
							.equals(bean.getDistrictSante().getId())) {
				transformedBean.setDistrictSante(bean.getDistrictSante());
			}

			if (transformedBean.getCDT() == null
					|| bean.getCDT() == null
					|| !transformedBean.getCDT().getId()
							.equals(bean.getCDT().getId())) {
				transformedBean.setCDT(bean.getCDT());
			}
			transformedBean.setDetails(bean.getDetails());
		} else {
			transformedBean.setDate(null);
			transformedBean.setRegion(null);
			transformedBean.setDistrictSante(null);
			transformedBean.setCDT(null);
			transformedBean.setDetails(new Vector<DetailInventaire>());
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Secure a DetailInventaire bean.
	 * 
	 * @param bean The DetailInventaire bean to secure
	 * @param roleNames set of role ids for the user 
	 * @return A secured DetailInventaire bean
	 */
	private DetailInventaire toSecureDetailInventaire(DetailInventaire bean,
			AccessPolicyImpl policy) {
		boolean isSecured = false;
		DetailInventaire transformedBean = new DetailInventaire();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canReadDetailInventaireDescription()) {
			isSecured = true;

			if (transformedBean.getInventaire() == null
					|| bean.getInventaire() == null
					|| !transformedBean.getInventaire().getId()
							.equals(bean.getInventaire().getId())) {
				transformedBean.setInventaire(bean.getInventaire());
			}

			if (transformedBean.getCDT() == null
					|| bean.getCDT() == null
					|| !transformedBean.getCDT().getId()
							.equals(bean.getCDT().getId())) {
				transformedBean.setCDT(bean.getCDT());
			}

			if (transformedBean.getLot() == null
					|| bean.getLot() == null
					|| !transformedBean.getLot().getId()
							.equals(bean.getLot().getId())) {
				transformedBean.setLot(bean.getLot());
			}
			transformedBean.setQuantiteReelle(bean.getQuantiteReelle());
			transformedBean.setQuantiteTheorique(bean.getQuantiteTheorique());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 			transformedBean.setInventaire(null);
			transformedBean.setCDT(null);
			transformedBean.setLot(null);
			transformedBean.setQuantiteReelle(null);
			transformedBean.setQuantiteTheorique(null);
			 */
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Unsecure a DetailInventaire bean.
	 * @param bean The DetailInventaire bean to unsecure
	 * @param roleNames set of role ids for the user
	 * @return A unsecured DetailInventaire bean
	 */
	private DetailInventaire toUnsecureDetailInventaire(DetailInventaire bean,
			AccessPolicyImpl policy) {
		boolean isSecured = false;
		DetailInventaire transformedBean = (DetailInventaire) dao.load(
				DetailInventaire.class, bean.getId());

		if (transformedBean == null) {
			transformedBean = new DetailInventaire();
			transformedBean.setId(bean.getId());
		}
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canEditDetailInventaireDescription()) {
			isSecured = true;

			if (transformedBean.getInventaire() == null
					|| bean.getInventaire() == null
					|| !transformedBean.getInventaire().getId()
							.equals(bean.getInventaire().getId())) {
				transformedBean.setInventaire(bean.getInventaire());
			}

			if (transformedBean.getCDT() == null
					|| bean.getCDT() == null
					|| !transformedBean.getCDT().getId()
							.equals(bean.getCDT().getId())) {
				transformedBean.setCDT(bean.getCDT());
			}

			if (transformedBean.getLot() == null
					|| bean.getLot() == null
					|| !transformedBean.getLot().getId()
							.equals(bean.getLot().getId())) {
				transformedBean.setLot(bean.getLot());
			}
			transformedBean.setQuantiteReelle(bean.getQuantiteReelle());
			transformedBean.setQuantiteTheorique(bean.getQuantiteTheorique());
		} else {
			transformedBean.setInventaire(null);
			transformedBean.setCDT(null);
			transformedBean.setLot(null);
			transformedBean.setQuantiteReelle(null);
			transformedBean.setQuantiteTheorique(null);
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Secure a Personnel bean.
	 * 
	 * @param bean The Personnel bean to secure
	 * @param roleNames set of role ids for the user 
	 * @return A secured Personnel bean
	 */
	private Personnel toSecurePersonnel(Personnel bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		Personnel transformedBean = new Personnel();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());
		transformedBean.setLogin(bean.getLogin());
		transformedBean.setPassword(bean.getPassword());
		transformedBean.setProfiles(bean.getProfiles());
		transformedBean.setSynchronizables(bean.getSynchronizables());

		if (policy.canReadPersonnelIdentification()) {
			isSecured = true;
			transformedBean.setNom(bean.getNom());
			transformedBean.setDateNaissance(bean.getDateNaissance());
			transformedBean.setProfession(bean.getProfession());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 	transformedBean.setNom(null);
			transformedBean.setDateNaissance(null);
			transformedBean.setProfession(null);
			 */
		}
		if (policy.canReadPersonnelContact()) {
			isSecured = true;
			transformedBean.setTelephoneUn(bean.getTelephoneUn());
			transformedBean.setTelephoneDeux(bean.getTelephoneDeux());
			transformedBean.setTelephoneTrois(bean.getTelephoneTrois());
			transformedBean.setEmail(bean.getEmail());
			transformedBean.setLibelle(bean.getLibelle());
			transformedBean.setComplementAdresse(bean.getComplementAdresse());
			transformedBean.setQuartier(bean.getQuartier());
			transformedBean.setVille(bean.getVille());
			transformedBean.setCodePostal(bean.getCodePostal());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 	transformedBean.setTelephoneUn(null);
			transformedBean.setTelephoneDeux(null);
			transformedBean.setTelephoneTrois(null);
			transformedBean.setEmail(null);
			transformedBean.setLibelle(null);
			transformedBean.setComplementAdresse(null);
			transformedBean.setQuartier(null);
			transformedBean.setVille(null);
			transformedBean.setCodePostal(null);
			 */
		}
		if (policy.canReadPersonnelSituation()) {
			isSecured = true;
			transformedBean.setDateDepart(bean.getDateDepart());
			transformedBean.setDateArrivee(bean.getDateArrivee());
			transformedBean.setAEteForme(bean.getAEteForme());
			transformedBean.setQualification(bean.getQualification());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 	transformedBean.setDateDepart(null);
			transformedBean.setDateArrivee(null);
			transformedBean.setAEteForme(null);
			transformedBean.setQualification(new Vector<Qualification>());
			 */
		}
		if (policy.canReadPersonnelNiveauAccess()) {
			isSecured = true;
			transformedBean.setNiveau(bean.getNiveau());

			if (transformedBean.getRegion() == null
					|| bean.getRegion() == null
					|| !transformedBean.getRegion().getId()
							.equals(bean.getRegion().getId())) {
				transformedBean.setRegion(bean.getRegion());
			}

			if (transformedBean.getDistrictSante() == null
					|| bean.getDistrictSante() == null
					|| !transformedBean.getDistrictSante().getId()
							.equals(bean.getDistrictSante().getId())) {
				transformedBean.setDistrictSante(bean.getDistrictSante());
			}

			if (transformedBean.getCDT() == null
					|| bean.getCDT() == null
					|| !transformedBean.getCDT().getId()
							.equals(bean.getCDT().getId())) {
				transformedBean.setCDT(bean.getCDT());
			}
			transformedBean.setActif(bean.getActif());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 	transformedBean.setNiveau(null);
			transformedBean.setRegion(null);
			transformedBean.setDistrictSante(null);
			transformedBean.setCDT(null);
			transformedBean.setActif(null);
			 */
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Unsecure a Personnel bean.
	 * @param bean The Personnel bean to unsecure
	 * @param roleNames set of role ids for the user
	 * @return A unsecured Personnel bean
	 */
	private Personnel toUnsecurePersonnel(Personnel bean,
			AccessPolicyImpl policy) {
		boolean isSecured = false;
		Personnel transformedBean = (Personnel) dao.load(Personnel.class,
				bean.getId());

		if (transformedBean == null) {
			transformedBean = new Personnel();
			transformedBean.setId(bean.getId());
		}
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canEditPersonnelIdentification()) {
			isSecured = true;
			transformedBean.setNom(bean.getNom());
			transformedBean.setDateNaissance(bean.getDateNaissance());
			transformedBean.setProfession(bean.getProfession());
		} else {
			transformedBean.setNom(null);
			transformedBean.setDateNaissance(null);
			transformedBean.setProfession(null);
		}
		if (policy.canEditPersonnelContact()) {
			isSecured = true;
			transformedBean.setTelephoneUn(bean.getTelephoneUn());
			transformedBean.setTelephoneDeux(bean.getTelephoneDeux());
			transformedBean.setTelephoneTrois(bean.getTelephoneTrois());
			transformedBean.setEmail(bean.getEmail());
			transformedBean.setLibelle(bean.getLibelle());
			transformedBean.setComplementAdresse(bean.getComplementAdresse());
			transformedBean.setQuartier(bean.getQuartier());
			transformedBean.setVille(bean.getVille());
			transformedBean.setCodePostal(bean.getCodePostal());
		} else {
			transformedBean.setTelephoneUn(null);
			transformedBean.setTelephoneDeux(null);
			transformedBean.setTelephoneTrois(null);
			transformedBean.setEmail(null);
			transformedBean.setLibelle(null);
			transformedBean.setComplementAdresse(null);
			transformedBean.setQuartier(null);
			transformedBean.setVille(null);
			transformedBean.setCodePostal(null);
		}
		if (policy.canEditPersonnelSituation()) {
			isSecured = true;
			transformedBean.setDateDepart(bean.getDateDepart());
			transformedBean.setDateArrivee(bean.getDateArrivee());
			transformedBean.setAEteForme(bean.getAEteForme());
			transformedBean.setQualification(bean.getQualification());
		} else {
			transformedBean.setDateDepart(null);
			transformedBean.setDateArrivee(null);
			transformedBean.setAEteForme(null);
			transformedBean.setQualification(new Vector<Qualification>());
		}
		if (policy.canEditPersonnelNiveauAccess()) {
			isSecured = true;
			transformedBean.setNiveau(bean.getNiveau());

			if (transformedBean.getRegion() == null
					|| bean.getRegion() == null
					|| !transformedBean.getRegion().getId()
							.equals(bean.getRegion().getId())) {
				transformedBean.setRegion(bean.getRegion());
			}

			if (transformedBean.getDistrictSante() == null
					|| bean.getDistrictSante() == null
					|| !transformedBean.getDistrictSante().getId()
							.equals(bean.getDistrictSante().getId())) {
				transformedBean.setDistrictSante(bean.getDistrictSante());
			}

			if (transformedBean.getCDT() == null
					|| bean.getCDT() == null
					|| !transformedBean.getCDT().getId()
							.equals(bean.getCDT().getId())) {
				transformedBean.setCDT(bean.getCDT());
			}
			transformedBean.setActif(bean.getActif());
		} else {
			transformedBean.setNiveau(null);
			transformedBean.setRegion(null);
			transformedBean.setDistrictSante(null);
			transformedBean.setCDT(null);
			transformedBean.setActif(null);
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Secure a DepartPersonnel bean.
	 * 
	 * @param bean The DepartPersonnel bean to secure
	 * @param roleNames set of role ids for the user 
	 * @return A secured DepartPersonnel bean
	 */
	private DepartPersonnel toSecureDepartPersonnel(DepartPersonnel bean,
			AccessPolicyImpl policy) {
		boolean isSecured = false;
		DepartPersonnel transformedBean = new DepartPersonnel();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canReadDepartPersonnelPersonnel()) {
			isSecured = true;

			if (transformedBean.getRegion() == null
					|| bean.getRegion() == null
					|| !transformedBean.getRegion().getId()
							.equals(bean.getRegion().getId())) {
				transformedBean.setRegion(bean.getRegion());
			}

			if (transformedBean.getDistrictSante() == null
					|| bean.getDistrictSante() == null
					|| !transformedBean.getDistrictSante().getId()
							.equals(bean.getDistrictSante().getId())) {
				transformedBean.setDistrictSante(bean.getDistrictSante());
			}

			if (transformedBean.getCDT() == null
					|| bean.getCDT() == null
					|| !transformedBean.getCDT().getId()
							.equals(bean.getCDT().getId())) {
				transformedBean.setCDT(bean.getCDT());
			}

			if (transformedBean.getPersonnel() == null
					|| bean.getPersonnel() == null
					|| !transformedBean.getPersonnel().getId()
							.equals(bean.getPersonnel().getId())) {
				transformedBean.setPersonnel(bean.getPersonnel());
			}
		} else {
			/*Comment for security reason (when synchronizing administrative data) 			transformedBean.setRegion(null);
			transformedBean.setDistrictSante(null);
			transformedBean.setCDT(null);
			transformedBean.setPersonnel(null);
			 */
		}
		if (policy.canReadDepartPersonnelDescription()) {
			isSecured = true;
			transformedBean.setDateDepart(bean.getDateDepart());
			transformedBean.setMotifDepart(bean.getMotifDepart());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 	transformedBean.setDateDepart(null);
			transformedBean.setMotifDepart(null);
			 */
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Unsecure a DepartPersonnel bean.
	 * @param bean The DepartPersonnel bean to unsecure
	 * @param roleNames set of role ids for the user
	 * @return A unsecured DepartPersonnel bean
	 */
	private DepartPersonnel toUnsecureDepartPersonnel(DepartPersonnel bean,
			AccessPolicyImpl policy) {
		boolean isSecured = false;
		DepartPersonnel transformedBean = (DepartPersonnel) dao.load(
				DepartPersonnel.class, bean.getId());

		if (transformedBean == null) {
			transformedBean = new DepartPersonnel();
			transformedBean.setId(bean.getId());
		}
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canEditDepartPersonnelPersonnel()) {
			isSecured = true;

			if (transformedBean.getRegion() == null
					|| bean.getRegion() == null
					|| !transformedBean.getRegion().getId()
							.equals(bean.getRegion().getId())) {
				transformedBean.setRegion(bean.getRegion());
			}

			if (transformedBean.getDistrictSante() == null
					|| bean.getDistrictSante() == null
					|| !transformedBean.getDistrictSante().getId()
							.equals(bean.getDistrictSante().getId())) {
				transformedBean.setDistrictSante(bean.getDistrictSante());
			}

			if (transformedBean.getCDT() == null
					|| bean.getCDT() == null
					|| !transformedBean.getCDT().getId()
							.equals(bean.getCDT().getId())) {
				transformedBean.setCDT(bean.getCDT());
			}

			if (transformedBean.getPersonnel() == null
					|| bean.getPersonnel() == null
					|| !transformedBean.getPersonnel().getId()
							.equals(bean.getPersonnel().getId())) {
				transformedBean.setPersonnel(bean.getPersonnel());
			}
		} else {
			transformedBean.setRegion(null);
			transformedBean.setDistrictSante(null);
			transformedBean.setCDT(null);
			transformedBean.setPersonnel(null);
		}
		if (policy.canEditDepartPersonnelDescription()) {
			isSecured = true;
			transformedBean.setDateDepart(bean.getDateDepart());
			transformedBean.setMotifDepart(bean.getMotifDepart());
		} else {
			transformedBean.setDateDepart(null);
			transformedBean.setMotifDepart(null);
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Secure a ArriveePersonnel bean.
	 * 
	 * @param bean The ArriveePersonnel bean to secure
	 * @param roleNames set of role ids for the user 
	 * @return A secured ArriveePersonnel bean
	 */
	private ArriveePersonnel toSecureArriveePersonnel(ArriveePersonnel bean,
			AccessPolicyImpl policy) {
		boolean isSecured = false;
		ArriveePersonnel transformedBean = new ArriveePersonnel();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canReadArriveePersonnelDescription()) {
			isSecured = true;

			if (transformedBean.getRegion() == null
					|| bean.getRegion() == null
					|| !transformedBean.getRegion().getId()
							.equals(bean.getRegion().getId())) {
				transformedBean.setRegion(bean.getRegion());
			}

			if (transformedBean.getDistrictSante() == null
					|| bean.getDistrictSante() == null
					|| !transformedBean.getDistrictSante().getId()
							.equals(bean.getDistrictSante().getId())) {
				transformedBean.setDistrictSante(bean.getDistrictSante());
			}

			if (transformedBean.getCDT() == null
					|| bean.getCDT() == null
					|| !transformedBean.getCDT().getId()
							.equals(bean.getCDT().getId())) {
				transformedBean.setCDT(bean.getCDT());
			}

			if (transformedBean.getPersonnel() == null
					|| bean.getPersonnel() == null
					|| !transformedBean.getPersonnel().getId()
							.equals(bean.getPersonnel().getId())) {
				transformedBean.setPersonnel(bean.getPersonnel());
			}
			transformedBean.setDateArrivee(bean.getDateArrivee());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 			transformedBean.setRegion(null);
			transformedBean.setDistrictSante(null);
			transformedBean.setCDT(null);
			transformedBean.setPersonnel(null);
			transformedBean.setDateArrivee(null);
			 */
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Unsecure a ArriveePersonnel bean.
	 * @param bean The ArriveePersonnel bean to unsecure
	 * @param roleNames set of role ids for the user
	 * @return A unsecured ArriveePersonnel bean
	 */
	private ArriveePersonnel toUnsecureArriveePersonnel(ArriveePersonnel bean,
			AccessPolicyImpl policy) {
		boolean isSecured = false;
		ArriveePersonnel transformedBean = (ArriveePersonnel) dao.load(
				ArriveePersonnel.class, bean.getId());

		if (transformedBean == null) {
			transformedBean = new ArriveePersonnel();
			transformedBean.setId(bean.getId());
		}
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canEditArriveePersonnelDescription()) {
			isSecured = true;

			if (transformedBean.getRegion() == null
					|| bean.getRegion() == null
					|| !transformedBean.getRegion().getId()
							.equals(bean.getRegion().getId())) {
				transformedBean.setRegion(bean.getRegion());
			}

			if (transformedBean.getDistrictSante() == null
					|| bean.getDistrictSante() == null
					|| !transformedBean.getDistrictSante().getId()
							.equals(bean.getDistrictSante().getId())) {
				transformedBean.setDistrictSante(bean.getDistrictSante());
			}

			if (transformedBean.getCDT() == null
					|| bean.getCDT() == null
					|| !transformedBean.getCDT().getId()
							.equals(bean.getCDT().getId())) {
				transformedBean.setCDT(bean.getCDT());
			}

			if (transformedBean.getPersonnel() == null
					|| bean.getPersonnel() == null
					|| !transformedBean.getPersonnel().getId()
							.equals(bean.getPersonnel().getId())) {
				transformedBean.setPersonnel(bean.getPersonnel());
			}
			transformedBean.setDateArrivee(bean.getDateArrivee());
		} else {
			transformedBean.setRegion(null);
			transformedBean.setDistrictSante(null);
			transformedBean.setCDT(null);
			transformedBean.setPersonnel(null);
			transformedBean.setDateArrivee(null);
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Secure a Region bean.
	 * 
	 * @param bean The Region bean to secure
	 * @param roleNames set of role ids for the user 
	 * @return A secured Region bean
	 */
	private Region toSecureRegion(Region bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		Region transformedBean = new Region();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canReadRegionDescription()) {
			isSecured = true;
			transformedBean.setCode(bean.getCode());
			transformedBean.setNom(bean.getNom());
			transformedBean.setDescription(bean.getDescription());
			transformedBean.setDistrictSantes(bean.getDistrictSantes());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 	transformedBean.setCode(null);
			transformedBean.setNom(null);
			transformedBean.setDescription(null);
			transformedBean.setDistrictSantes(new Vector<DistrictSante>());
			 */
		}
		if (policy.canReadRegionAdresse()) {
			isSecured = true;
			transformedBean.setLibelle(bean.getLibelle());
			transformedBean.setComplementAdresse(bean.getComplementAdresse());
			transformedBean.setQuartier(bean.getQuartier());
			transformedBean.setVille(bean.getVille());
			transformedBean.setCodePostal(bean.getCodePostal());
			transformedBean.setCoordonnees(bean.getCoordonnees());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 	transformedBean.setLibelle(null);
			transformedBean.setComplementAdresse(null);
			transformedBean.setQuartier(null);
			transformedBean.setVille(null);
			transformedBean.setCodePostal(null);
			transformedBean.setCoordonnees(null);
			 */
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Unsecure a Region bean.
	 * @param bean The Region bean to unsecure
	 * @param roleNames set of role ids for the user
	 * @return A unsecured Region bean
	 */
	private Region toUnsecureRegion(Region bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		Region transformedBean = (Region) dao.load(Region.class, bean.getId());

		if (transformedBean == null) {
			transformedBean = new Region();
			transformedBean.setId(bean.getId());
		}
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canEditRegionDescription()) {
			isSecured = true;
			transformedBean.setCode(bean.getCode());
			transformedBean.setNom(bean.getNom());
			transformedBean.setDescription(bean.getDescription());
			transformedBean.setDistrictSantes(bean.getDistrictSantes());
		} else {
			transformedBean.setCode(null);
			transformedBean.setNom(null);
			transformedBean.setDescription(null);
			transformedBean.setDistrictSantes(new Vector<DistrictSante>());
		}
		if (policy.canEditRegionAdresse()) {
			isSecured = true;
			transformedBean.setLibelle(bean.getLibelle());
			transformedBean.setComplementAdresse(bean.getComplementAdresse());
			transformedBean.setQuartier(bean.getQuartier());
			transformedBean.setVille(bean.getVille());
			transformedBean.setCodePostal(bean.getCodePostal());
			transformedBean.setCoordonnees(bean.getCoordonnees());
		} else {
			transformedBean.setLibelle(null);
			transformedBean.setComplementAdresse(null);
			transformedBean.setQuartier(null);
			transformedBean.setVille(null);
			transformedBean.setCodePostal(null);
			transformedBean.setCoordonnees(null);
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Secure a DistrictSante bean.
	 * 
	 * @param bean The DistrictSante bean to secure
	 * @param roleNames set of role ids for the user 
	 * @return A secured DistrictSante bean
	 */
	private DistrictSante toSecureDistrictSante(DistrictSante bean,
			AccessPolicyImpl policy) {
		boolean isSecured = false;
		DistrictSante transformedBean = new DistrictSante();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canReadDistrictSanteDescription()) {
			isSecured = true;
			transformedBean.setCode(bean.getCode());
			transformedBean.setNom(bean.getNom());
			transformedBean.setDescription(bean.getDescription());

			if (transformedBean.getRegion() == null
					|| bean.getRegion() == null
					|| !transformedBean.getRegion().getId()
							.equals(bean.getRegion().getId())) {
				transformedBean.setRegion(bean.getRegion());
			}
		} else {
			/*Comment for security reason (when synchronizing administrative data) 	transformedBean.setCode(null);
			transformedBean.setNom(null);
			transformedBean.setDescription(null);
			transformedBean.setRegion(null);
			 */
		}
		if (policy.canReadDistrictSanteAdresse()) {
			isSecured = true;
			transformedBean.setLibelle(bean.getLibelle());
			transformedBean.setComplementAdresse(bean.getComplementAdresse());
			transformedBean.setQuartier(bean.getQuartier());
			transformedBean.setVille(bean.getVille());
			transformedBean.setCodePostal(bean.getCodePostal());
			transformedBean.setCoordonnees(bean.getCoordonnees());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 	transformedBean.setLibelle(null);
			transformedBean.setComplementAdresse(null);
			transformedBean.setQuartier(null);
			transformedBean.setVille(null);
			transformedBean.setCodePostal(null);
			transformedBean.setCoordonnees(null);
			 */
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Unsecure a DistrictSante bean.
	 * @param bean The DistrictSante bean to unsecure
	 * @param roleNames set of role ids for the user
	 * @return A unsecured DistrictSante bean
	 */
	private DistrictSante toUnsecureDistrictSante(DistrictSante bean,
			AccessPolicyImpl policy) {
		boolean isSecured = false;
		DistrictSante transformedBean = (DistrictSante) dao.load(
				DistrictSante.class, bean.getId());

		if (transformedBean == null) {
			transformedBean = new DistrictSante();
			transformedBean.setId(bean.getId());
		}
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canEditDistrictSanteDescription()) {
			isSecured = true;
			transformedBean.setCode(bean.getCode());
			transformedBean.setNom(bean.getNom());
			transformedBean.setDescription(bean.getDescription());

			if (transformedBean.getRegion() == null
					|| bean.getRegion() == null
					|| !transformedBean.getRegion().getId()
							.equals(bean.getRegion().getId())) {
				transformedBean.setRegion(bean.getRegion());
			}
		} else {
			transformedBean.setCode(null);
			transformedBean.setNom(null);
			transformedBean.setDescription(null);
			transformedBean.setRegion(null);
		}
		if (policy.canEditDistrictSanteAdresse()) {
			isSecured = true;
			transformedBean.setLibelle(bean.getLibelle());
			transformedBean.setComplementAdresse(bean.getComplementAdresse());
			transformedBean.setQuartier(bean.getQuartier());
			transformedBean.setVille(bean.getVille());
			transformedBean.setCodePostal(bean.getCodePostal());
			transformedBean.setCoordonnees(bean.getCoordonnees());
		} else {
			transformedBean.setLibelle(null);
			transformedBean.setComplementAdresse(null);
			transformedBean.setQuartier(null);
			transformedBean.setVille(null);
			transformedBean.setCodePostal(null);
			transformedBean.setCoordonnees(null);
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Secure a CentreDiagTrait bean.
	 * 
	 * @param bean The CentreDiagTrait bean to secure
	 * @param roleNames set of role ids for the user 
	 * @return A secured CentreDiagTrait bean
	 */
	private CentreDiagTrait toSecureCentreDiagTrait(CentreDiagTrait bean,
			AccessPolicyImpl policy) {
		boolean isSecured = false;
		CentreDiagTrait transformedBean = new CentreDiagTrait();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canReadCentreDiagTraitDescription()) {
			isSecured = true;
			transformedBean.setCode(bean.getCode());

			if (transformedBean.getRegion() == null
					|| bean.getRegion() == null
					|| !transformedBean.getRegion().getId()
							.equals(bean.getRegion().getId())) {
				transformedBean.setRegion(bean.getRegion());
			}

			if (transformedBean.getDistrictSante() == null
					|| bean.getDistrictSante() == null
					|| !transformedBean.getDistrictSante().getId()
							.equals(bean.getDistrictSante().getId())) {
				transformedBean.setDistrictSante(bean.getDistrictSante());
			}
			transformedBean.setNom(bean.getNom());
			transformedBean.setDescription(bean.getDescription());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 	transformedBean.setCode(null);
			transformedBean.setRegion(null);
			transformedBean.setDistrictSante(null);
			transformedBean.setNom(null);
			transformedBean.setDescription(null);
			 */
		}
		if (policy.canReadCentreDiagTraitAdresse()) {
			isSecured = true;
			transformedBean.setLibelle(bean.getLibelle());
			transformedBean.setComplementAdresse(bean.getComplementAdresse());
			transformedBean.setQuartier(bean.getQuartier());
			transformedBean.setVille(bean.getVille());
			transformedBean.setCodePostal(bean.getCodePostal());
			transformedBean.setCoordonnees(bean.getCoordonnees());
			transformedBean.setLieuxDits(bean.getLieuxDits());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 	transformedBean.setLibelle(null);
			transformedBean.setComplementAdresse(null);
			transformedBean.setQuartier(null);
			transformedBean.setVille(null);
			transformedBean.setCodePostal(null);
			transformedBean.setCoordonnees(null);
			transformedBean.setLieuxDits(new Vector<LieuDit>());
			 */
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Unsecure a CentreDiagTrait bean.
	 * @param bean The CentreDiagTrait bean to unsecure
	 * @param roleNames set of role ids for the user
	 * @return A unsecured CentreDiagTrait bean
	 */
	private CentreDiagTrait toUnsecureCentreDiagTrait(CentreDiagTrait bean,
			AccessPolicyImpl policy) {
		boolean isSecured = false;
		CentreDiagTrait transformedBean = (CentreDiagTrait) dao.load(
				CentreDiagTrait.class, bean.getId());

		if (transformedBean == null) {
			transformedBean = new CentreDiagTrait();
			transformedBean.setId(bean.getId());
		}
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canEditCentreDiagTraitDescription()) {
			isSecured = true;
			transformedBean.setCode(bean.getCode());

			if (transformedBean.getRegion() == null
					|| bean.getRegion() == null
					|| !transformedBean.getRegion().getId()
							.equals(bean.getRegion().getId())) {
				transformedBean.setRegion(bean.getRegion());
			}

			if (transformedBean.getDistrictSante() == null
					|| bean.getDistrictSante() == null
					|| !transformedBean.getDistrictSante().getId()
							.equals(bean.getDistrictSante().getId())) {
				transformedBean.setDistrictSante(bean.getDistrictSante());
			}
			transformedBean.setNom(bean.getNom());
			transformedBean.setDescription(bean.getDescription());
		} else {
			transformedBean.setCode(null);
			transformedBean.setRegion(null);
			transformedBean.setDistrictSante(null);
			transformedBean.setNom(null);
			transformedBean.setDescription(null);
		}
		if (policy.canEditCentreDiagTraitAdresse()) {
			isSecured = true;
			transformedBean.setLibelle(bean.getLibelle());
			transformedBean.setComplementAdresse(bean.getComplementAdresse());
			transformedBean.setQuartier(bean.getQuartier());
			transformedBean.setVille(bean.getVille());
			transformedBean.setCodePostal(bean.getCodePostal());
			transformedBean.setCoordonnees(bean.getCoordonnees());
			transformedBean.setLieuxDits(bean.getLieuxDits());
		} else {
			transformedBean.setLibelle(null);
			transformedBean.setComplementAdresse(null);
			transformedBean.setQuartier(null);
			transformedBean.setVille(null);
			transformedBean.setCodePostal(null);
			transformedBean.setCoordonnees(null);
			transformedBean.setLieuxDits(new Vector<LieuDit>());
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Secure a LaboratoireReference bean.
	 * 
	 * @param bean The LaboratoireReference bean to secure
	 * @param roleNames set of role ids for the user 
	 * @return A secured LaboratoireReference bean
	 */
	private LaboratoireReference toSecureLaboratoireReference(
			LaboratoireReference bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		LaboratoireReference transformedBean = new LaboratoireReference();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canReadLaboratoireReferenceDescription()) {
			isSecured = true;
			transformedBean.setNom(bean.getNom());
			transformedBean.setNature(bean.getNature());
			transformedBean.setDescription(bean.getDescription());

			if (transformedBean.getRegion() == null
					|| bean.getRegion() == null
					|| !transformedBean.getRegion().getId()
							.equals(bean.getRegion().getId())) {
				transformedBean.setRegion(bean.getRegion());
			}

			if (transformedBean.getDistrictSante() == null
					|| bean.getDistrictSante() == null
					|| !transformedBean.getDistrictSante().getId()
							.equals(bean.getDistrictSante().getId())) {
				transformedBean.setDistrictSante(bean.getDistrictSante());
			}
		} else {
			/*Comment for security reason (when synchronizing administrative data) 	transformedBean.setNom(null);
			transformedBean.setNature(null);
			transformedBean.setDescription(null);
			transformedBean.setRegion(null);
			transformedBean.setDistrictSante(null);
			 */
		}
		if (policy.canReadLaboratoireReferenceAdresse()) {
			isSecured = true;
			transformedBean.setLibelle(bean.getLibelle());
			transformedBean.setComplementAdresse(bean.getComplementAdresse());
			transformedBean.setQuartier(bean.getQuartier());
			transformedBean.setVille(bean.getVille());
			transformedBean.setCodePostal(bean.getCodePostal());
			transformedBean.setCoordonnees(bean.getCoordonnees());
			transformedBean.setLieuxDits(bean.getLieuxDits());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 	transformedBean.setLibelle(null);
			transformedBean.setComplementAdresse(null);
			transformedBean.setQuartier(null);
			transformedBean.setVille(null);
			transformedBean.setCodePostal(null);
			transformedBean.setCoordonnees(null);
			transformedBean.setLieuxDits(new Vector<LieuDit>());
			 */
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Unsecure a LaboratoireReference bean.
	 * @param bean The LaboratoireReference bean to unsecure
	 * @param roleNames set of role ids for the user
	 * @return A unsecured LaboratoireReference bean
	 */
	private LaboratoireReference toUnsecureLaboratoireReference(
			LaboratoireReference bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		LaboratoireReference transformedBean = (LaboratoireReference) dao.load(
				LaboratoireReference.class, bean.getId());

		if (transformedBean == null) {
			transformedBean = new LaboratoireReference();
			transformedBean.setId(bean.getId());
		}
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canEditLaboratoireReferenceDescription()) {
			isSecured = true;
			transformedBean.setNom(bean.getNom());
			transformedBean.setNature(bean.getNature());
			transformedBean.setDescription(bean.getDescription());

			if (transformedBean.getRegion() == null
					|| bean.getRegion() == null
					|| !transformedBean.getRegion().getId()
							.equals(bean.getRegion().getId())) {
				transformedBean.setRegion(bean.getRegion());
			}

			if (transformedBean.getDistrictSante() == null
					|| bean.getDistrictSante() == null
					|| !transformedBean.getDistrictSante().getId()
							.equals(bean.getDistrictSante().getId())) {
				transformedBean.setDistrictSante(bean.getDistrictSante());
			}
		} else {
			transformedBean.setNom(null);
			transformedBean.setNature(null);
			transformedBean.setDescription(null);
			transformedBean.setRegion(null);
			transformedBean.setDistrictSante(null);
		}
		if (policy.canEditLaboratoireReferenceAdresse()) {
			isSecured = true;
			transformedBean.setLibelle(bean.getLibelle());
			transformedBean.setComplementAdresse(bean.getComplementAdresse());
			transformedBean.setQuartier(bean.getQuartier());
			transformedBean.setVille(bean.getVille());
			transformedBean.setCodePostal(bean.getCodePostal());
			transformedBean.setCoordonnees(bean.getCoordonnees());
			transformedBean.setLieuxDits(bean.getLieuxDits());
		} else {
			transformedBean.setLibelle(null);
			transformedBean.setComplementAdresse(null);
			transformedBean.setQuartier(null);
			transformedBean.setVille(null);
			transformedBean.setCodePostal(null);
			transformedBean.setCoordonnees(null);
			transformedBean.setLieuxDits(new Vector<LieuDit>());
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Secure a LieuDit bean.
	 * 
	 * @param bean The LieuDit bean to secure
	 * @param roleNames set of role ids for the user 
	 * @return A secured LieuDit bean
	 */
	private LieuDit toSecureLieuDit(LieuDit bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		LieuDit transformedBean = new LieuDit();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canReadLieuDitDescription()) {
			isSecured = true;
			transformedBean.setCode(bean.getCode());
			transformedBean.setNom(bean.getNom());
			transformedBean.setDescription(bean.getDescription());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 	transformedBean.setCode(null);
			transformedBean.setNom(null);
			transformedBean.setDescription(null);
			 */
		}
		if (policy.canReadLieuDitAdresse()) {
			isSecured = true;
			transformedBean.setLibelle(bean.getLibelle());
			transformedBean.setComplementAdresse(bean.getComplementAdresse());
			transformedBean.setQuartier(bean.getQuartier());
			transformedBean.setVille(bean.getVille());
			transformedBean.setCodePostal(bean.getCodePostal());
			transformedBean.setCoordonnees(bean.getCoordonnees());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 	transformedBean.setLibelle(null);
			transformedBean.setComplementAdresse(null);
			transformedBean.setQuartier(null);
			transformedBean.setVille(null);
			transformedBean.setCodePostal(null);
			transformedBean.setCoordonnees(null);
			 */
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Unsecure a LieuDit bean.
	 * @param bean The LieuDit bean to unsecure
	 * @param roleNames set of role ids for the user
	 * @return A unsecured LieuDit bean
	 */
	private LieuDit toUnsecureLieuDit(LieuDit bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		LieuDit transformedBean = (LieuDit) dao.load(LieuDit.class,
				bean.getId());

		if (transformedBean == null) {
			transformedBean = new LieuDit();
			transformedBean.setId(bean.getId());
		}
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canEditLieuDitDescription()) {
			isSecured = true;
			transformedBean.setCode(bean.getCode());
			transformedBean.setNom(bean.getNom());
			transformedBean.setDescription(bean.getDescription());
		} else {
			transformedBean.setCode(null);
			transformedBean.setNom(null);
			transformedBean.setDescription(null);
		}
		if (policy.canEditLieuDitAdresse()) {
			isSecured = true;
			transformedBean.setLibelle(bean.getLibelle());
			transformedBean.setComplementAdresse(bean.getComplementAdresse());
			transformedBean.setQuartier(bean.getQuartier());
			transformedBean.setVille(bean.getVille());
			transformedBean.setCodePostal(bean.getCodePostal());
			transformedBean.setCoordonnees(bean.getCoordonnees());
		} else {
			transformedBean.setLibelle(null);
			transformedBean.setComplementAdresse(null);
			transformedBean.setQuartier(null);
			transformedBean.setVille(null);
			transformedBean.setCodePostal(null);
			transformedBean.setCoordonnees(null);
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Secure a Regime bean.
	 * 
	 * @param bean The Regime bean to secure
	 * @param roleNames set of role ids for the user 
	 * @return A secured Regime bean
	 */
	private Regime toSecureRegime(Regime bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		Regime transformedBean = new Regime();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canReadRegimeDescription()) {
			isSecured = true;
			transformedBean.setNom(bean.getNom());
			transformedBean.setType(bean.getType());
			transformedBean.setDureeTraitement(bean.getDureeTraitement());
			transformedBean.setPoidsMin(bean.getPoidsMin());
			transformedBean.setPoidsMax(bean.getPoidsMax());
			transformedBean.setDescription(bean.getDescription());
			transformedBean.setPrisesMedicamenteuses(bean
					.getPrisesMedicamenteuses());
			transformedBean.setActif(bean.getActif());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 	transformedBean.setNom(null);
			transformedBean.setType(null);
			transformedBean.setDureeTraitement(null);
			transformedBean.setPoidsMin(null);
			transformedBean.setPoidsMax(null);
			transformedBean.setDescription(null);
			transformedBean.setPrisesMedicamenteuses(new Vector<PriseMedicamentRegime>());
			transformedBean.setActif(null);
			 */
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Unsecure a Regime bean.
	 * @param bean The Regime bean to unsecure
	 * @param roleNames set of role ids for the user
	 * @return A unsecured Regime bean
	 */
	private Regime toUnsecureRegime(Regime bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		Regime transformedBean = (Regime) dao.load(Regime.class, bean.getId());

		if (transformedBean == null) {
			transformedBean = new Regime();
			transformedBean.setId(bean.getId());
		}
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canEditRegimeDescription()) {
			isSecured = true;
			transformedBean.setNom(bean.getNom());
			transformedBean.setType(bean.getType());
			transformedBean.setDureeTraitement(bean.getDureeTraitement());
			transformedBean.setPoidsMin(bean.getPoidsMin());
			transformedBean.setPoidsMax(bean.getPoidsMax());
			transformedBean.setDescription(bean.getDescription());
			transformedBean.setPrisesMedicamenteuses(bean
					.getPrisesMedicamenteuses());
			transformedBean.setActif(bean.getActif());
		} else {
			transformedBean.setNom(null);
			transformedBean.setType(null);
			transformedBean.setDureeTraitement(null);
			transformedBean.setPoidsMin(null);
			transformedBean.setPoidsMax(null);
			transformedBean.setDescription(null);
			transformedBean
					.setPrisesMedicamenteuses(new Vector<PriseMedicamentRegime>());
			transformedBean.setActif(null);
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Secure a PriseMedicamentRegime bean.
	 * 
	 * @param bean The PriseMedicamentRegime bean to secure
	 * @param roleNames set of role ids for the user 
	 * @return A secured PriseMedicamentRegime bean
	 */
	private PriseMedicamentRegime toSecurePriseMedicamentRegime(
			PriseMedicamentRegime bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		PriseMedicamentRegime transformedBean = new PriseMedicamentRegime();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canReadPriseMedicamentRegimeDescription()) {
			isSecured = true;

			if (transformedBean.getRegime() == null
					|| bean.getRegime() == null
					|| !transformedBean.getRegime().getId()
							.equals(bean.getRegime().getId())) {
				transformedBean.setRegime(bean.getRegime());
			}

			if (transformedBean.getMedicament() == null
					|| bean.getMedicament() == null
					|| !transformedBean.getMedicament().getId()
							.equals(bean.getMedicament().getId())) {
				transformedBean.setMedicament(bean.getMedicament());
			}
			transformedBean.setQuantite(bean.getQuantite());
			transformedBean.setQuantiteUnite(bean.getQuantiteUnite());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 			transformedBean.setRegime(null);
			transformedBean.setMedicament(null);
			transformedBean.setQuantite(null);
			transformedBean.setQuantiteUnite(null);
			 */
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Unsecure a PriseMedicamentRegime bean.
	 * @param bean The PriseMedicamentRegime bean to unsecure
	 * @param roleNames set of role ids for the user
	 * @return A unsecured PriseMedicamentRegime bean
	 */
	private PriseMedicamentRegime toUnsecurePriseMedicamentRegime(
			PriseMedicamentRegime bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		PriseMedicamentRegime transformedBean = (PriseMedicamentRegime) dao
				.load(PriseMedicamentRegime.class, bean.getId());

		if (transformedBean == null) {
			transformedBean = new PriseMedicamentRegime();
			transformedBean.setId(bean.getId());
		}
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canEditPriseMedicamentRegimeDescription()) {
			isSecured = true;

			if (transformedBean.getRegime() == null
					|| bean.getRegime() == null
					|| !transformedBean.getRegime().getId()
							.equals(bean.getRegime().getId())) {
				transformedBean.setRegime(bean.getRegime());
			}

			if (transformedBean.getMedicament() == null
					|| bean.getMedicament() == null
					|| !transformedBean.getMedicament().getId()
							.equals(bean.getMedicament().getId())) {
				transformedBean.setMedicament(bean.getMedicament());
			}
			transformedBean.setQuantite(bean.getQuantite());
			transformedBean.setQuantiteUnite(bean.getQuantiteUnite());
		} else {
			transformedBean.setRegime(null);
			transformedBean.setMedicament(null);
			transformedBean.setQuantite(null);
			transformedBean.setQuantiteUnite(null);
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Secure a Medicament bean.
	 * 
	 * @param bean The Medicament bean to secure
	 * @param roleNames set of role ids for the user 
	 * @return A secured Medicament bean
	 */
	private Medicament toSecureMedicament(Medicament bean,
			AccessPolicyImpl policy) {
		boolean isSecured = false;
		Medicament transformedBean = new Medicament();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canReadMedicamentDescription()) {
			isSecured = true;
			transformedBean.setCode(bean.getCode());
			transformedBean.setDesignation(bean.getDesignation());
			transformedBean.setSeuilPatient(bean.getSeuilPatient());
			transformedBean.setEstMedicamentAntituberculeux(bean
					.getEstMedicamentAntituberculeux());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 	transformedBean.setCode(null);
			transformedBean.setDesignation(null);
			transformedBean.setSeuilPatient(null);
			transformedBean.setEstMedicamentAntituberculeux(null);
			 */
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Unsecure a Medicament bean.
	 * @param bean The Medicament bean to unsecure
	 * @param roleNames set of role ids for the user
	 * @return A unsecured Medicament bean
	 */
	private Medicament toUnsecureMedicament(Medicament bean,
			AccessPolicyImpl policy) {
		boolean isSecured = false;
		Medicament transformedBean = (Medicament) dao.load(Medicament.class,
				bean.getId());

		if (transformedBean == null) {
			transformedBean = new Medicament();
			transformedBean.setId(bean.getId());
		}
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canEditMedicamentDescription()) {
			isSecured = true;
			transformedBean.setCode(bean.getCode());
			transformedBean.setDesignation(bean.getDesignation());
			transformedBean.setSeuilPatient(bean.getSeuilPatient());
			transformedBean.setEstMedicamentAntituberculeux(bean
					.getEstMedicamentAntituberculeux());
		} else {
			transformedBean.setCode(null);
			transformedBean.setDesignation(null);
			transformedBean.setSeuilPatient(null);
			transformedBean.setEstMedicamentAntituberculeux(null);
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Secure a Intrant bean.
	 * 
	 * @param bean The Intrant bean to secure
	 * @param roleNames set of role ids for the user 
	 * @return A secured Intrant bean
	 */
	private Intrant toSecureIntrant(Intrant bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		Intrant transformedBean = new Intrant();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canReadIntrantDescription()) {
			isSecured = true;
			transformedBean.setIdentifiant(bean.getIdentifiant());
			transformedBean.setNom(bean.getNom());
			transformedBean.setDescription(bean.getDescription());
			transformedBean.setSeuilPatient(bean.getSeuilPatient());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 	transformedBean.setIdentifiant(null);
			transformedBean.setNom(null);
			transformedBean.setDescription(null);
			transformedBean.setSeuilPatient(null);
			 */
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Unsecure a Intrant bean.
	 * @param bean The Intrant bean to unsecure
	 * @param roleNames set of role ids for the user
	 * @return A unsecured Intrant bean
	 */
	private Intrant toUnsecureIntrant(Intrant bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		Intrant transformedBean = (Intrant) dao.load(Intrant.class,
				bean.getId());

		if (transformedBean == null) {
			transformedBean = new Intrant();
			transformedBean.setId(bean.getId());
		}
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canEditIntrantDescription()) {
			isSecured = true;
			transformedBean.setIdentifiant(bean.getIdentifiant());
			transformedBean.setNom(bean.getNom());
			transformedBean.setDescription(bean.getDescription());
			transformedBean.setSeuilPatient(bean.getSeuilPatient());
		} else {
			transformedBean.setIdentifiant(null);
			transformedBean.setNom(null);
			transformedBean.setDescription(null);
			transformedBean.setSeuilPatient(null);
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Secure a Formation bean.
	 * 
	 * @param bean The Formation bean to secure
	 * @param roleNames set of role ids for the user 
	 * @return A secured Formation bean
	 */
	private Formation toSecureFormation(Formation bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		Formation transformedBean = new Formation();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canReadFormationDescription()) {
			isSecured = true;
			transformedBean.setLibelle(bean.getLibelle());
			transformedBean.setDateDebut(bean.getDateDebut());
			transformedBean.setDateFin(bean.getDateFin());
			transformedBean.setLieu(bean.getLieu());
			transformedBean.setObjectifs(bean.getObjectifs());
			transformedBean.setEffectuee(bean.getEffectuee());
			transformedBean.setCandidatures(bean.getCandidatures());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 	transformedBean.setLibelle(null);
			transformedBean.setDateDebut(null);
			transformedBean.setDateFin(null);
			transformedBean.setLieu(null);
			transformedBean.setObjectifs(null);
			transformedBean.setEffectuee(null);
			transformedBean.setCandidatures(new Vector<CandidatureFormation>());
			 */
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Unsecure a Formation bean.
	 * @param bean The Formation bean to unsecure
	 * @param roleNames set of role ids for the user
	 * @return A unsecured Formation bean
	 */
	private Formation toUnsecureFormation(Formation bean,
			AccessPolicyImpl policy) {
		boolean isSecured = false;
		Formation transformedBean = (Formation) dao.load(Formation.class,
				bean.getId());

		if (transformedBean == null) {
			transformedBean = new Formation();
			transformedBean.setId(bean.getId());
		}
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canEditFormationDescription()) {
			isSecured = true;
			transformedBean.setLibelle(bean.getLibelle());
			transformedBean.setDateDebut(bean.getDateDebut());
			transformedBean.setDateFin(bean.getDateFin());
			transformedBean.setLieu(bean.getLieu());
			transformedBean.setObjectifs(bean.getObjectifs());
			transformedBean.setEffectuee(bean.getEffectuee());
			transformedBean.setCandidatures(bean.getCandidatures());
		} else {
			transformedBean.setLibelle(null);
			transformedBean.setDateDebut(null);
			transformedBean.setDateFin(null);
			transformedBean.setLieu(null);
			transformedBean.setObjectifs(null);
			transformedBean.setEffectuee(null);
			transformedBean.setCandidatures(new Vector<CandidatureFormation>());
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Secure a CandidatureFormation bean.
	 * 
	 * @param bean The CandidatureFormation bean to secure
	 * @param roleNames set of role ids for the user 
	 * @return A secured CandidatureFormation bean
	 */
	private CandidatureFormation toSecureCandidatureFormation(
			CandidatureFormation bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		CandidatureFormation transformedBean = new CandidatureFormation();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canReadCandidatureFormationDescription()) {
			isSecured = true;

			if (transformedBean.getFormation() == null
					|| bean.getFormation() == null
					|| !transformedBean.getFormation().getId()
							.equals(bean.getFormation().getId())) {
				transformedBean.setFormation(bean.getFormation());
			}

			if (transformedBean.getRegion() == null
					|| bean.getRegion() == null
					|| !transformedBean.getRegion().getId()
							.equals(bean.getRegion().getId())) {
				transformedBean.setRegion(bean.getRegion());
			}

			if (transformedBean.getDistrictSante() == null
					|| bean.getDistrictSante() == null
					|| !transformedBean.getDistrictSante().getId()
							.equals(bean.getDistrictSante().getId())) {
				transformedBean.setDistrictSante(bean.getDistrictSante());
			}

			if (transformedBean.getCDT() == null
					|| bean.getCDT() == null
					|| !transformedBean.getCDT().getId()
							.equals(bean.getCDT().getId())) {
				transformedBean.setCDT(bean.getCDT());
			}

			if (transformedBean.getPersonnel() == null
					|| bean.getPersonnel() == null
					|| !transformedBean.getPersonnel().getId()
							.equals(bean.getPersonnel().getId())) {
				transformedBean.setPersonnel(bean.getPersonnel());
			}
		} else {
			/*Comment for security reason (when synchronizing administrative data) 			transformedBean.setFormation(null);
			transformedBean.setRegion(null);
			transformedBean.setDistrictSante(null);
			transformedBean.setCDT(null);
			transformedBean.setPersonnel(null);
			 */
		}
		if (policy.canReadCandidatureFormationRegionApprobation()) {
			isSecured = true;
			transformedBean.setApprouveeRegion(bean.getApprouveeRegion());
			transformedBean.setMotifRejetRegion(bean.getMotifRejetRegion());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 	transformedBean.setApprouveeRegion(null);
			transformedBean.setMotifRejetRegion(null);
			 */
		}
		if (policy.canReadCandidatureFormationGtcApprobation()) {
			isSecured = true;
			transformedBean.setApprouveeGTC(bean.getApprouveeGTC());
			transformedBean.setMotifRejetGTC(bean.getMotifRejetGTC());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 	transformedBean.setApprouveeGTC(null);
			transformedBean.setMotifRejetGTC(null);
			 */
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Unsecure a CandidatureFormation bean.
	 * @param bean The CandidatureFormation bean to unsecure
	 * @param roleNames set of role ids for the user
	 * @return A unsecured CandidatureFormation bean
	 */
	private CandidatureFormation toUnsecureCandidatureFormation(
			CandidatureFormation bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		CandidatureFormation transformedBean = (CandidatureFormation) dao.load(
				CandidatureFormation.class, bean.getId());

		if (transformedBean == null) {
			transformedBean = new CandidatureFormation();
			transformedBean.setId(bean.getId());
		}
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canEditCandidatureFormationDescription()) {
			isSecured = true;

			if (transformedBean.getFormation() == null
					|| bean.getFormation() == null
					|| !transformedBean.getFormation().getId()
							.equals(bean.getFormation().getId())) {
				transformedBean.setFormation(bean.getFormation());
			}

			if (transformedBean.getRegion() == null
					|| bean.getRegion() == null
					|| !transformedBean.getRegion().getId()
							.equals(bean.getRegion().getId())) {
				transformedBean.setRegion(bean.getRegion());
			}

			if (transformedBean.getDistrictSante() == null
					|| bean.getDistrictSante() == null
					|| !transformedBean.getDistrictSante().getId()
							.equals(bean.getDistrictSante().getId())) {
				transformedBean.setDistrictSante(bean.getDistrictSante());
			}

			if (transformedBean.getCDT() == null
					|| bean.getCDT() == null
					|| !transformedBean.getCDT().getId()
							.equals(bean.getCDT().getId())) {
				transformedBean.setCDT(bean.getCDT());
			}

			if (transformedBean.getPersonnel() == null
					|| bean.getPersonnel() == null
					|| !transformedBean.getPersonnel().getId()
							.equals(bean.getPersonnel().getId())) {
				transformedBean.setPersonnel(bean.getPersonnel());
			}
		} else {
			transformedBean.setFormation(null);
			transformedBean.setRegion(null);
			transformedBean.setDistrictSante(null);
			transformedBean.setCDT(null);
			transformedBean.setPersonnel(null);
		}
		if (policy.canEditCandidatureFormationRegionApprobation()) {
			isSecured = true;
			transformedBean.setApprouveeRegion(bean.getApprouveeRegion());
			transformedBean.setMotifRejetRegion(bean.getMotifRejetRegion());
		} else {
			transformedBean.setApprouveeRegion(null);
			transformedBean.setMotifRejetRegion(null);
		}
		if (policy.canEditCandidatureFormationGtcApprobation()) {
			isSecured = true;
			transformedBean.setApprouveeGTC(bean.getApprouveeGTC());
			transformedBean.setMotifRejetGTC(bean.getMotifRejetGTC());
		} else {
			transformedBean.setApprouveeGTC(null);
			transformedBean.setMotifRejetGTC(null);
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Secure a Qualification bean.
	 * 
	 * @param bean The Qualification bean to secure
	 * @param roleNames set of role ids for the user 
	 * @return A secured Qualification bean
	 */
	private Qualification toSecureQualification(Qualification bean,
			AccessPolicyImpl policy) {
		boolean isSecured = false;
		Qualification transformedBean = new Qualification();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canReadQualificationDescription()) {
			isSecured = true;
			transformedBean.setCode(bean.getCode());
			transformedBean.setNom(bean.getNom());
			transformedBean.setDescription(bean.getDescription());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 	transformedBean.setCode(null);
			transformedBean.setNom(null);
			transformedBean.setDescription(null);
			 */
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Unsecure a Qualification bean.
	 * @param bean The Qualification bean to unsecure
	 * @param roleNames set of role ids for the user
	 * @return A unsecured Qualification bean
	 */
	private Qualification toUnsecureQualification(Qualification bean,
			AccessPolicyImpl policy) {
		boolean isSecured = false;
		Qualification transformedBean = (Qualification) dao.load(
				Qualification.class, bean.getId());

		if (transformedBean == null) {
			transformedBean = new Qualification();
			transformedBean.setId(bean.getId());
		}
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canEditQualificationDescription()) {
			isSecured = true;
			transformedBean.setCode(bean.getCode());
			transformedBean.setNom(bean.getNom());
			transformedBean.setDescription(bean.getDescription());
		} else {
			transformedBean.setCode(null);
			transformedBean.setNom(null);
			transformedBean.setDescription(null);
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Secure a Tutoriel bean.
	 * 
	 * @param bean The Tutoriel bean to secure
	 * @param roleNames set of role ids for the user 
	 * @return A secured Tutoriel bean
	 */
	private Tutoriel toSecureTutoriel(Tutoriel bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		Tutoriel transformedBean = new Tutoriel();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canReadTutorielDescription()) {
			isSecured = true;
			transformedBean.setReference(bean.getReference());
			transformedBean.setNom(bean.getNom());
			transformedBean.setDescription(bean.getDescription());
			transformedBean.setType(bean.getType());
			transformedBean.setAudioT(bean.getAudioT());
			transformedBean.setVideoT(bean.getVideoT());
			transformedBean.setTextT(bean.getTextT());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 	transformedBean.setReference(null);
			transformedBean.setNom(null);
			transformedBean.setDescription(null);
			transformedBean.setType(null);
			transformedBean.setAudioT(null);
			transformedBean.setVideoT(null);
			transformedBean.setTextT(null);
			 */
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Unsecure a Tutoriel bean.
	 * @param bean The Tutoriel bean to unsecure
	 * @param roleNames set of role ids for the user
	 * @return A unsecured Tutoriel bean
	 */
	private Tutoriel toUnsecureTutoriel(Tutoriel bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		Tutoriel transformedBean = (Tutoriel) dao.load(Tutoriel.class,
				bean.getId());

		if (transformedBean == null) {
			transformedBean = new Tutoriel();
			transformedBean.setId(bean.getId());
		}
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canEditTutorielDescription()) {
			isSecured = true;
			transformedBean.setReference(bean.getReference());
			transformedBean.setNom(bean.getNom());
			transformedBean.setDescription(bean.getDescription());
			transformedBean.setType(bean.getType());
			transformedBean.setAudioT(bean.getAudioT());
			transformedBean.setVideoT(bean.getVideoT());
			transformedBean.setTextT(bean.getTextT());
		} else {
			transformedBean.setReference(null);
			transformedBean.setNom(null);
			transformedBean.setDescription(null);
			transformedBean.setType(null);
			transformedBean.setAudioT(null);
			transformedBean.setVideoT(null);
			transformedBean.setTextT(null);
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Secure a SmsPredefini bean.
	 * 
	 * @param bean The SmsPredefini bean to secure
	 * @param roleNames set of role ids for the user 
	 * @return A secured SmsPredefini bean
	 */
	private SmsPredefini toSecureSmsPredefini(SmsPredefini bean,
			AccessPolicyImpl policy) {
		boolean isSecured = false;
		SmsPredefini transformedBean = new SmsPredefini();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canReadSmsPredefiniDescription()) {
			isSecured = true;
			transformedBean.setType(bean.getType());
			transformedBean.setObjet(bean.getObjet());
			transformedBean.setPeriodicite(bean.getPeriodicite());
			transformedBean.setDateEnvoyeSMSPonctuel(bean
					.getDateEnvoyeSMSPonctuel());
			transformedBean.setStatut(bean.getStatut());
			transformedBean.setMessage(bean.getMessage());
			transformedBean.setReponse1(bean.getReponse1());
			transformedBean.setReponse2(bean.getReponse2());
			transformedBean.setBonneReponse(bean.getBonneReponse());
			transformedBean.setEnvoyerAPartirDe(bean.getEnvoyerAPartirDe());
			transformedBean.setArreterEnvoyerA(bean.getArreterEnvoyerA());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 	transformedBean.setType(null);
			transformedBean.setObjet(null);
			transformedBean.setPeriodicite(null);
			transformedBean.setDateEnvoyeSMSPonctuel(null);
			transformedBean.setStatut(null);
			transformedBean.setMessage(null);
			transformedBean.setReponse1(null);
			transformedBean.setReponse2(null);
			transformedBean.setBonneReponse(null);
			transformedBean.setEnvoyerAPartirDe(null);
			transformedBean.setArreterEnvoyerA(null);
			 */
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Unsecure a SmsPredefini bean.
	 * @param bean The SmsPredefini bean to unsecure
	 * @param roleNames set of role ids for the user
	 * @return A unsecured SmsPredefini bean
	 */
	private SmsPredefini toUnsecureSmsPredefini(SmsPredefini bean,
			AccessPolicyImpl policy) {
		boolean isSecured = false;
		SmsPredefini transformedBean = (SmsPredefini) dao.load(
				SmsPredefini.class, bean.getId());

		if (transformedBean == null) {
			transformedBean = new SmsPredefini();
			transformedBean.setId(bean.getId());
		}
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canEditSmsPredefiniDescription()) {
			isSecured = true;
			transformedBean.setType(bean.getType());
			transformedBean.setObjet(bean.getObjet());
			transformedBean.setPeriodicite(bean.getPeriodicite());
			transformedBean.setDateEnvoyeSMSPonctuel(bean
					.getDateEnvoyeSMSPonctuel());
			transformedBean.setStatut(bean.getStatut());
			transformedBean.setMessage(bean.getMessage());
			transformedBean.setReponse1(bean.getReponse1());
			transformedBean.setReponse2(bean.getReponse2());
			transformedBean.setBonneReponse(bean.getBonneReponse());
			transformedBean.setEnvoyerAPartirDe(bean.getEnvoyerAPartirDe());
			transformedBean.setArreterEnvoyerA(bean.getArreterEnvoyerA());
		} else {
			transformedBean.setType(null);
			transformedBean.setObjet(null);
			transformedBean.setPeriodicite(null);
			transformedBean.setDateEnvoyeSMSPonctuel(null);
			transformedBean.setStatut(null);
			transformedBean.setMessage(null);
			transformedBean.setReponse1(null);
			transformedBean.setReponse2(null);
			transformedBean.setBonneReponse(null);
			transformedBean.setEnvoyerAPartirDe(null);
			transformedBean.setArreterEnvoyerA(null);
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Secure a OutBox bean.
	 * 
	 * @param bean The OutBox bean to secure
	 * @param roleNames set of role ids for the user 
	 * @return A secured OutBox bean
	 */
	private OutBox toSecureOutBox(OutBox bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		OutBox transformedBean = new OutBox();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canReadOutBoxAdresse()) {
			isSecured = true;

			if (transformedBean.getPatient() == null
					|| bean.getPatient() == null
					|| !transformedBean.getPatient().getId()
							.equals(bean.getPatient().getId())) {
				transformedBean.setPatient(bean.getPatient());
			}
		} else {
			/*Comment for security reason (when synchronizing administrative data) 			transformedBean.setPatient(null);
			 */
		}
		if (policy.canReadOutBoxMessageInformation()) {
			isSecured = true;
			transformedBean.setMessage(bean.getMessage());
			transformedBean.setReponse(bean.getReponse());
			transformedBean.setStatut(bean.getStatut());
			transformedBean.setDateDernierEssai(bean.getDateDernierEssai());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 	transformedBean.setMessage(null);
			transformedBean.setReponse(null);
			transformedBean.setStatut(null);
			transformedBean.setDateDernierEssai(null);
			 */
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Unsecure a OutBox bean.
	 * @param bean The OutBox bean to unsecure
	 * @param roleNames set of role ids for the user
	 * @return A unsecured OutBox bean
	 */
	private OutBox toUnsecureOutBox(OutBox bean, AccessPolicyImpl policy) {
		boolean isSecured = false;
		OutBox transformedBean = (OutBox) dao.load(OutBox.class, bean.getId());

		if (transformedBean == null) {
			transformedBean = new OutBox();
			transformedBean.setId(bean.getId());
		}
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canEditOutBoxAdresse()) {
			isSecured = true;

			if (transformedBean.getPatient() == null
					|| bean.getPatient() == null
					|| !transformedBean.getPatient().getId()
							.equals(bean.getPatient().getId())) {
				transformedBean.setPatient(bean.getPatient());
			}
		} else {
			transformedBean.setPatient(null);
		}
		if (policy.canEditOutBoxMessageInformation()) {
			isSecured = true;
			transformedBean.setMessage(bean.getMessage());
			transformedBean.setReponse(bean.getReponse());
			transformedBean.setStatut(bean.getStatut());
			transformedBean.setDateDernierEssai(bean.getDateDernierEssai());
		} else {
			transformedBean.setMessage(null);
			transformedBean.setReponse(null);
			transformedBean.setStatut(null);
			transformedBean.setDateDernierEssai(null);
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Secure a Utilisateur bean.
	 * 
	 * @param bean The Utilisateur bean to secure
	 * @param roleNames set of role ids for the user 
	 * @return A secured Utilisateur bean
	 */
	private Utilisateur toSecureUtilisateur(Utilisateur bean,
			AccessPolicyImpl policy) {
		boolean isSecured = false;
		Utilisateur transformedBean = new Utilisateur();

		/* unsecured data */
		transformedBean.setId(bean.getId());
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());
		transformedBean.setLogin(bean.getLogin());
		transformedBean.setPassword(bean.getPassword());
		transformedBean.setProfiles(bean.getProfiles());
		transformedBean.setSynchronizables(bean.getSynchronizables());

		if (policy.canReadUtilisateurIdentification()) {
			isSecured = true;
			transformedBean.setNom(bean.getNom());
			transformedBean.setDateNaissance(bean.getDateNaissance());
			transformedBean.setProfession(bean.getProfession());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 	transformedBean.setNom(null);
			transformedBean.setDateNaissance(null);
			transformedBean.setProfession(null);
			 */
		}
		if (policy.canReadUtilisateurContact()) {
			isSecured = true;
			transformedBean.setTelephoneUn(bean.getTelephoneUn());
			transformedBean.setTelephoneDeux(bean.getTelephoneDeux());
			transformedBean.setTelephoneTrois(bean.getTelephoneTrois());
			transformedBean.setEmail(bean.getEmail());
			transformedBean.setLibelle(bean.getLibelle());
			transformedBean.setComplementAdresse(bean.getComplementAdresse());
			transformedBean.setQuartier(bean.getQuartier());
			transformedBean.setVille(bean.getVille());
			transformedBean.setCodePostal(bean.getCodePostal());
		} else {
			/*Comment for security reason (when synchronizing administrative data) 	transformedBean.setTelephoneUn(null);
			transformedBean.setTelephoneDeux(null);
			transformedBean.setTelephoneTrois(null);
			transformedBean.setEmail(null);
			transformedBean.setLibelle(null);
			transformedBean.setComplementAdresse(null);
			transformedBean.setQuartier(null);
			transformedBean.setVille(null);
			transformedBean.setCodePostal(null);
			 */
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

	/**
	 * Unsecure a Utilisateur bean.
	 * @param bean The Utilisateur bean to unsecure
	 * @param roleNames set of role ids for the user
	 * @return A unsecured Utilisateur bean
	 */
	private Utilisateur toUnsecureUtilisateur(Utilisateur bean,
			AccessPolicyImpl policy) {
		boolean isSecured = false;
		Utilisateur transformedBean = (Utilisateur) dao.load(Utilisateur.class,
				bean.getId());

		if (transformedBean == null) {
			transformedBean = new Utilisateur();
			transformedBean.setId(bean.getId());
		}
		transformedBean.setModifiedBy(bean.getModifiedBy());
		transformedBean.setModifiedFrom(bean.getModifiedFrom());
		transformedBean.setModified(bean.getModified());
		transformedBean.setUploadDate(bean.getUploadDate());
		transformedBean.setCreatedBy(bean.getCreatedBy());
		transformedBean.setCreated(bean.getCreated());
		transformedBean.setDeleted(bean.getDeleted());
		transformedBean.setDynamicFieldValues(bean.getDynamicFieldValues());

		if (policy.canEditUtilisateurIdentification()) {
			isSecured = true;
			transformedBean.setNom(bean.getNom());
			transformedBean.setDateNaissance(bean.getDateNaissance());
			transformedBean.setProfession(bean.getProfession());
		} else {
			transformedBean.setNom(null);
			transformedBean.setDateNaissance(null);
			transformedBean.setProfession(null);
		}
		if (policy.canEditUtilisateurContact()) {
			isSecured = true;
			transformedBean.setTelephoneUn(bean.getTelephoneUn());
			transformedBean.setTelephoneDeux(bean.getTelephoneDeux());
			transformedBean.setTelephoneTrois(bean.getTelephoneTrois());
			transformedBean.setEmail(bean.getEmail());
			transformedBean.setLibelle(bean.getLibelle());
			transformedBean.setComplementAdresse(bean.getComplementAdresse());
			transformedBean.setQuartier(bean.getQuartier());
			transformedBean.setVille(bean.getVille());
			transformedBean.setCodePostal(bean.getCodePostal());
		} else {
			transformedBean.setTelephoneUn(null);
			transformedBean.setTelephoneDeux(null);
			transformedBean.setTelephoneTrois(null);
			transformedBean.setEmail(null);
			transformedBean.setLibelle(null);
			transformedBean.setComplementAdresse(null);
			transformedBean.setQuartier(null);
			transformedBean.setVille(null);
			transformedBean.setCodePostal(null);
		}

		if (isSecured)
			return transformedBean;
		else
			return null;
	}

}
