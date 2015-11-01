package org.imogene.epicam.server.security;

import org.imogene.lib.common.entity.ImogActor;
import org.imogene.lib.common.profile.FieldGroupProfile;
import org.imogene.lib.common.profile.Profile;
import org.imogene.lib.common.security.AccessPolicy;
import org.imogene.epicam.domain.entity.Patient;
import org.imogene.epicam.domain.entity.CasIndex;
import org.imogene.epicam.domain.entity.CasTuberculose;
import org.imogene.epicam.domain.entity.ExamenSerologie;
import org.imogene.epicam.domain.entity.ExamenBiologique;
import org.imogene.epicam.domain.entity.ExamenMicroscopie;
import org.imogene.epicam.domain.entity.ExamenATB;
import org.imogene.epicam.domain.entity.PriseMedicamenteuse;
import org.imogene.epicam.domain.entity.RendezVous;
import org.imogene.epicam.domain.entity.TransfertReference;
import org.imogene.epicam.domain.entity.Lot;
import org.imogene.epicam.domain.entity.HorsUsage;
import org.imogene.epicam.domain.entity.EntreeLot;
import org.imogene.epicam.domain.entity.SortieLot;
import org.imogene.epicam.domain.entity.Commande;
import org.imogene.epicam.domain.entity.DetailCommandeMedicament;
import org.imogene.epicam.domain.entity.DetailCommandeIntrant;
import org.imogene.epicam.domain.entity.Reception;
import org.imogene.epicam.domain.entity.DetailReceptionMedicament;
import org.imogene.epicam.domain.entity.DetailReceptionIntrant;
import org.imogene.epicam.domain.entity.Ravitaillement;
import org.imogene.epicam.domain.entity.DetailRavitaillement;
import org.imogene.epicam.domain.entity.Inventaire;
import org.imogene.epicam.domain.entity.DetailInventaire;
import org.imogene.epicam.domain.entity.Personnel;
import org.imogene.epicam.domain.entity.DepartPersonnel;
import org.imogene.epicam.domain.entity.ArriveePersonnel;
import org.imogene.epicam.domain.entity.Region;
import org.imogene.epicam.domain.entity.DistrictSante;
import org.imogene.epicam.domain.entity.CentreDiagTrait;
import org.imogene.epicam.domain.entity.LaboratoireReference;
import org.imogene.epicam.domain.entity.LieuDit;
import org.imogene.epicam.domain.entity.Regime;
import org.imogene.epicam.domain.entity.PriseMedicamentRegime;
import org.imogene.epicam.domain.entity.Medicament;
import org.imogene.epicam.domain.entity.Intrant;
import org.imogene.epicam.domain.entity.Formation;
import org.imogene.epicam.domain.entity.CandidatureFormation;
import org.imogene.epicam.domain.entity.Qualification;
import org.imogene.epicam.domain.entity.Tutoriel;
import org.imogene.epicam.domain.entity.SmsPredefini;
import org.imogene.epicam.domain.entity.OutBox;
import org.imogene.epicam.domain.entity.Utilisateur;

/**
 * This class tells if the current user can access the properties of the model
 * objects in read and in update mode 
 * @author MEDES-IMPS
 */
public class AccessPolicyImpl implements AccessPolicy {

	private boolean isAdmin = false;
	private boolean canReadPatientIdentification = true;
	private boolean canEditPatientIdentification = true;
	private boolean canReadPatientContact = true;
	private boolean canEditPatientContact = true;
	private boolean canReadPatientPersonneContact = true;
	private boolean canEditPatientPersonneContact = true;
	private boolean canReadPatientTuberculose = true;
	private boolean canEditPatientTuberculose = true;
	private boolean canReadPatientExamens = true;
	private boolean canEditPatientExamens = true;
	private boolean canReadCasIndexDescription = true;
	private boolean canEditCasIndexDescription = true;
	private boolean canReadCasTuberculoseInformations = true;
	private boolean canEditCasTuberculoseInformations = true;
	private boolean canReadCasTuberculoseExamen = true;
	private boolean canEditCasTuberculoseExamen = true;
	private boolean canReadCasTuberculoseTraitement = true;
	private boolean canEditCasTuberculoseTraitement = true;
	private boolean canReadCasTuberculoseFinTraitement = true;
	private boolean canEditCasTuberculoseFinTraitement = true;
	private boolean canReadExamenSerologieDescription = true;
	private boolean canEditExamenSerologieDescription = true;
	private boolean canReadExamenBiologiqueDescription = true;
	private boolean canEditExamenBiologiqueDescription = true;
	private boolean canReadExamenMicroscopieCentreExamen = true;
	private boolean canEditExamenMicroscopieCentreExamen = true;
	private boolean canReadExamenMicroscopieExamen = true;
	private boolean canEditExamenMicroscopieExamen = true;
	private boolean canReadExamenATBCentreExamen = true;
	private boolean canEditExamenATBCentreExamen = true;
	private boolean canReadExamenATBExamen = true;
	private boolean canEditExamenATBExamen = true;
	private boolean canReadPriseMedicamenteuseDescription = true;
	private boolean canEditPriseMedicamenteuseDescription = true;
	private boolean canReadRendezVousDescription = true;
	private boolean canEditRendezVousDescription = true;
	private boolean canReadTransfertReferenceInformationsDepart = true;
	private boolean canEditTransfertReferenceInformationsDepart = true;
	private boolean canReadTransfertReferenceInformationArrivee = true;
	private boolean canEditTransfertReferenceInformationArrivee = true;
	private boolean canReadLotDescription = true;
	private boolean canEditLotDescription = true;
	private boolean canReadHorsUsageDescription = true;
	private boolean canEditHorsUsageDescription = true;
	private boolean canReadEntreeLotDescription = true;
	private boolean canEditEntreeLotDescription = true;
	private boolean canReadSortieLotDescription = true;
	private boolean canEditSortieLotDescription = true;
	private boolean canReadCommandeInformationsDepart = true;
	private boolean canEditCommandeInformationsDepart = true;
	private boolean canReadCommandeRegionApprobation = true;
	private boolean canEditCommandeRegionApprobation = true;
	private boolean canReadCommandeGtcApprobation = true;
	private boolean canEditCommandeGtcApprobation = true;
	private boolean canReadDetailCommandeMedicamentDescription = true;
	private boolean canEditDetailCommandeMedicamentDescription = true;
	private boolean canReadDetailCommandeIntrantDescription = true;
	private boolean canEditDetailCommandeIntrantDescription = true;
	private boolean canReadReceptionDescription = true;
	private boolean canEditReceptionDescription = true;
	private boolean canReadDetailReceptionMedicamentDescription = true;
	private boolean canEditDetailReceptionMedicamentDescription = true;
	private boolean canReadDetailReceptionIntrantDescription = true;
	private boolean canEditDetailReceptionIntrantDescription = true;
	private boolean canReadRavitaillementInformationsDepart = true;
	private boolean canEditRavitaillementInformationsDepart = true;
	private boolean canReadRavitaillementInformationArrivee = true;
	private boolean canEditRavitaillementInformationArrivee = true;
	private boolean canReadRavitaillementDetail = true;
	private boolean canEditRavitaillementDetail = true;
	private boolean canReadDetailRavitaillementDescription = true;
	private boolean canEditDetailRavitaillementDescription = true;
	private boolean canReadDetailRavitaillementSortie = true;
	private boolean canEditDetailRavitaillementSortie = true;
	private boolean canReadDetailRavitaillementEntree = true;
	private boolean canEditDetailRavitaillementEntree = true;
	private boolean canReadInventaireInformationsDepart = true;
	private boolean canEditInventaireInformationsDepart = true;
	private boolean canReadDetailInventaireDescription = true;
	private boolean canEditDetailInventaireDescription = true;
	private boolean canReadPersonnelIdentification = true;
	private boolean canEditPersonnelIdentification = true;
	private boolean canReadPersonnelContact = true;
	private boolean canEditPersonnelContact = true;
	private boolean canReadPersonnelSituation = true;
	private boolean canEditPersonnelSituation = true;
	private boolean canReadPersonnelNiveauAccess = true;
	private boolean canEditPersonnelNiveauAccess = true;
	private boolean canReadDepartPersonnelPersonnel = true;
	private boolean canEditDepartPersonnelPersonnel = true;
	private boolean canReadDepartPersonnelDescription = true;
	private boolean canEditDepartPersonnelDescription = true;
	private boolean canReadArriveePersonnelDescription = true;
	private boolean canEditArriveePersonnelDescription = true;
	private boolean canReadRegionDescription = true;
	private boolean canEditRegionDescription = true;
	private boolean canReadRegionAdresse = true;
	private boolean canEditRegionAdresse = true;
	private boolean canReadDistrictSanteDescription = true;
	private boolean canEditDistrictSanteDescription = true;
	private boolean canReadDistrictSanteAdresse = true;
	private boolean canEditDistrictSanteAdresse = true;
	private boolean canReadCentreDiagTraitDescription = true;
	private boolean canEditCentreDiagTraitDescription = true;
	private boolean canReadCentreDiagTraitAdresse = true;
	private boolean canEditCentreDiagTraitAdresse = true;
	private boolean canReadLaboratoireReferenceDescription = true;
	private boolean canEditLaboratoireReferenceDescription = true;
	private boolean canReadLaboratoireReferenceAdresse = true;
	private boolean canEditLaboratoireReferenceAdresse = true;
	private boolean canReadLieuDitDescription = true;
	private boolean canEditLieuDitDescription = true;
	private boolean canReadLieuDitAdresse = true;
	private boolean canEditLieuDitAdresse = true;
	private boolean canReadRegimeDescription = true;
	private boolean canEditRegimeDescription = true;
	private boolean canReadPriseMedicamentRegimeDescription = true;
	private boolean canEditPriseMedicamentRegimeDescription = true;
	private boolean canReadMedicamentDescription = true;
	private boolean canEditMedicamentDescription = true;
	private boolean canReadIntrantDescription = true;
	private boolean canEditIntrantDescription = true;
	private boolean canReadFormationDescription = true;
	private boolean canEditFormationDescription = true;
	private boolean canReadCandidatureFormationDescription = true;
	private boolean canEditCandidatureFormationDescription = true;
	private boolean canReadCandidatureFormationRegionApprobation = true;
	private boolean canEditCandidatureFormationRegionApprobation = true;
	private boolean canReadCandidatureFormationGtcApprobation = true;
	private boolean canEditCandidatureFormationGtcApprobation = true;
	private boolean canReadQualificationDescription = true;
	private boolean canEditQualificationDescription = true;
	private boolean canReadTutorielDescription = true;
	private boolean canEditTutorielDescription = true;
	private boolean canReadSmsPredefiniDescription = true;
	private boolean canEditSmsPredefiniDescription = true;
	private boolean canReadOutBoxAdresse = true;
	private boolean canEditOutBoxAdresse = true;
	private boolean canReadOutBoxMessageInformation = true;
	private boolean canEditOutBoxMessageInformation = true;
	private boolean canReadUtilisateurIdentification = true;
	private boolean canEditUtilisateurIdentification = true;
	private boolean canReadUtilisateurContact = true;
	private boolean canEditUtilisateurContact = true;

	/**
	 * Constructor
	 */
	public AccessPolicyImpl(ImogActor actor) {
		if (actor.getProfiles() == null) {
			return;
		}
		isAdmin = isAdmin(actor);
		if (isAdmin) {
			return;
		}
		for (Profile profile : actor.getProfiles()) {
			if (profile.getFieldGroupProfiles() == null) {
				continue;
			}
			for (FieldGroupProfile group : profile.getFieldGroupProfiles()) {
				String fieldGroupId = group.getFieldGroup().getId();
				if ("patient.identification".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadPatientIdentification &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditPatientIdentification &= group.getWrite();
					}
				} else if ("patient.contact".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadPatientContact &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditPatientContact &= group.getWrite();
					}
				} else if ("patient.personnecontact".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadPatientPersonneContact &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditPatientPersonneContact &= group.getWrite();
					}
				} else if ("patient.tuberculose".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadPatientTuberculose &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditPatientTuberculose &= group.getWrite();
					}
				} else if ("patient.examens".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadPatientExamens &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditPatientExamens &= group.getWrite();
					}
				} else if ("casindex.description".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadCasIndexDescription &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditCasIndexDescription &= group.getWrite();
					}
				} else if ("castuberculose.informations".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadCasTuberculoseInformations &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditCasTuberculoseInformations &= group.getWrite();
					}
				} else if ("castuberculose.examen".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadCasTuberculoseExamen &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditCasTuberculoseExamen &= group.getWrite();
					}
				} else if ("castuberculose.traitement".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadCasTuberculoseTraitement &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditCasTuberculoseTraitement &= group.getWrite();
					}
				} else if ("castuberculose.fintraitement".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadCasTuberculoseFinTraitement &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditCasTuberculoseFinTraitement &= group.getWrite();
					}
				} else if ("examenserologie.description".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadExamenSerologieDescription &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditExamenSerologieDescription &= group.getWrite();
					}
				} else if ("examenbiologique.description".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadExamenBiologiqueDescription &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditExamenBiologiqueDescription &= group.getWrite();
					}
				} else if ("examenmicroscopie.centreexamen"
						.equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadExamenMicroscopieCentreExamen &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditExamenMicroscopieCentreExamen &= group
								.getWrite();
					}
				} else if ("examenmicroscopie.examen".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadExamenMicroscopieExamen &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditExamenMicroscopieExamen &= group.getWrite();
					}
				} else if ("examenatb.centreexamen".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadExamenATBCentreExamen &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditExamenATBCentreExamen &= group.getWrite();
					}
				} else if ("examenatb.examen".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadExamenATBExamen &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditExamenATBExamen &= group.getWrite();
					}
				} else if ("prisemedicamenteuse.description"
						.equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadPriseMedicamenteuseDescription &= group
								.getRead();
					}
					if (group.getWrite() != null) {
						canEditPriseMedicamenteuseDescription &= group
								.getWrite();
					}
				} else if ("rendezvous.description".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadRendezVousDescription &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditRendezVousDescription &= group.getWrite();
					}
				} else if ("transfertreference.informationsdepart"
						.equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadTransfertReferenceInformationsDepart &= group
								.getRead();
					}
					if (group.getWrite() != null) {
						canEditTransfertReferenceInformationsDepart &= group
								.getWrite();
					}
				} else if ("transfertreference.informationarrivee"
						.equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadTransfertReferenceInformationArrivee &= group
								.getRead();
					}
					if (group.getWrite() != null) {
						canEditTransfertReferenceInformationArrivee &= group
								.getWrite();
					}
				} else if ("lot.description".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadLotDescription &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditLotDescription &= group.getWrite();
					}
				} else if ("horsusage.description".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadHorsUsageDescription &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditHorsUsageDescription &= group.getWrite();
					}
				} else if ("entreelot.description".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadEntreeLotDescription &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditEntreeLotDescription &= group.getWrite();
					}
				} else if ("sortielot.description".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadSortieLotDescription &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditSortieLotDescription &= group.getWrite();
					}
				} else if ("commande.informationsdepart".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadCommandeInformationsDepart &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditCommandeInformationsDepart &= group.getWrite();
					}
				} else if ("commande.regionapprobation".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadCommandeRegionApprobation &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditCommandeRegionApprobation &= group.getWrite();
					}
				} else if ("commande.gtcapprobation".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadCommandeGtcApprobation &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditCommandeGtcApprobation &= group.getWrite();
					}
				} else if ("detailcommandemedicament.description"
						.equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadDetailCommandeMedicamentDescription &= group
								.getRead();
					}
					if (group.getWrite() != null) {
						canEditDetailCommandeMedicamentDescription &= group
								.getWrite();
					}
				} else if ("detailcommandeintrant.description"
						.equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadDetailCommandeIntrantDescription &= group
								.getRead();
					}
					if (group.getWrite() != null) {
						canEditDetailCommandeIntrantDescription &= group
								.getWrite();
					}
				} else if ("reception.description".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadReceptionDescription &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditReceptionDescription &= group.getWrite();
					}
				} else if ("detailreceptionmedicament.description"
						.equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadDetailReceptionMedicamentDescription &= group
								.getRead();
					}
					if (group.getWrite() != null) {
						canEditDetailReceptionMedicamentDescription &= group
								.getWrite();
					}
				} else if ("detailreceptionintrant.description"
						.equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadDetailReceptionIntrantDescription &= group
								.getRead();
					}
					if (group.getWrite() != null) {
						canEditDetailReceptionIntrantDescription &= group
								.getWrite();
					}
				} else if ("ravitaillement.informationsdepart"
						.equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadRavitaillementInformationsDepart &= group
								.getRead();
					}
					if (group.getWrite() != null) {
						canEditRavitaillementInformationsDepart &= group
								.getWrite();
					}
				} else if ("ravitaillement.informationarrivee"
						.equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadRavitaillementInformationArrivee &= group
								.getRead();
					}
					if (group.getWrite() != null) {
						canEditRavitaillementInformationArrivee &= group
								.getWrite();
					}
				} else if ("ravitaillement.detail".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadRavitaillementDetail &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditRavitaillementDetail &= group.getWrite();
					}
				} else if ("detailravitaillement.description"
						.equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadDetailRavitaillementDescription &= group
								.getRead();
					}
					if (group.getWrite() != null) {
						canEditDetailRavitaillementDescription &= group
								.getWrite();
					}
				} else if ("detailravitaillement.sortie".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadDetailRavitaillementSortie &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditDetailRavitaillementSortie &= group.getWrite();
					}
				} else if ("detailravitaillement.entree".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadDetailRavitaillementEntree &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditDetailRavitaillementEntree &= group.getWrite();
					}
				} else if ("inventaire.informationsdepart".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadInventaireInformationsDepart &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditInventaireInformationsDepart &= group.getWrite();
					}
				} else if ("detailinventaire.description".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadDetailInventaireDescription &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditDetailInventaireDescription &= group.getWrite();
					}
				} else if ("personnel.identification".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadPersonnelIdentification &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditPersonnelIdentification &= group.getWrite();
					}
				} else if ("personnel.contact".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadPersonnelContact &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditPersonnelContact &= group.getWrite();
					}
				} else if ("personnel.situation".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadPersonnelSituation &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditPersonnelSituation &= group.getWrite();
					}
				} else if ("personnel.niveauaccess".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadPersonnelNiveauAccess &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditPersonnelNiveauAccess &= group.getWrite();
					}
				} else if ("departpersonnel.personnel".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadDepartPersonnelPersonnel &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditDepartPersonnelPersonnel &= group.getWrite();
					}
				} else if ("departpersonnel.description".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadDepartPersonnelDescription &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditDepartPersonnelDescription &= group.getWrite();
					}
				} else if ("arriveepersonnel.description".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadArriveePersonnelDescription &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditArriveePersonnelDescription &= group.getWrite();
					}
				} else if ("region.description".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadRegionDescription &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditRegionDescription &= group.getWrite();
					}
				} else if ("region.adresse".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadRegionAdresse &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditRegionAdresse &= group.getWrite();
					}
				} else if ("districtsante.description".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadDistrictSanteDescription &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditDistrictSanteDescription &= group.getWrite();
					}
				} else if ("districtsante.adresse".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadDistrictSanteAdresse &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditDistrictSanteAdresse &= group.getWrite();
					}
				} else if ("centrediagtrait.description".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadCentreDiagTraitDescription &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditCentreDiagTraitDescription &= group.getWrite();
					}
				} else if ("centrediagtrait.adresse".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadCentreDiagTraitAdresse &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditCentreDiagTraitAdresse &= group.getWrite();
					}
				} else if ("laboratoirereference.description"
						.equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadLaboratoireReferenceDescription &= group
								.getRead();
					}
					if (group.getWrite() != null) {
						canEditLaboratoireReferenceDescription &= group
								.getWrite();
					}
				} else if ("laboratoirereference.adresse".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadLaboratoireReferenceAdresse &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditLaboratoireReferenceAdresse &= group.getWrite();
					}
				} else if ("lieudit.description".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadLieuDitDescription &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditLieuDitDescription &= group.getWrite();
					}
				} else if ("lieudit.adresse".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadLieuDitAdresse &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditLieuDitAdresse &= group.getWrite();
					}
				} else if ("regime.description".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadRegimeDescription &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditRegimeDescription &= group.getWrite();
					}
				} else if ("prisemedicamentregime.description"
						.equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadPriseMedicamentRegimeDescription &= group
								.getRead();
					}
					if (group.getWrite() != null) {
						canEditPriseMedicamentRegimeDescription &= group
								.getWrite();
					}
				} else if ("medicament.description".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadMedicamentDescription &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditMedicamentDescription &= group.getWrite();
					}
				} else if ("intrant.description".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadIntrantDescription &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditIntrantDescription &= group.getWrite();
					}
				} else if ("formation.description".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadFormationDescription &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditFormationDescription &= group.getWrite();
					}
				} else if ("candidatureformation.description"
						.equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadCandidatureFormationDescription &= group
								.getRead();
					}
					if (group.getWrite() != null) {
						canEditCandidatureFormationDescription &= group
								.getWrite();
					}
				} else if ("candidatureformation.regionapprobation"
						.equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadCandidatureFormationRegionApprobation &= group
								.getRead();
					}
					if (group.getWrite() != null) {
						canEditCandidatureFormationRegionApprobation &= group
								.getWrite();
					}
				} else if ("candidatureformation.gtcapprobation"
						.equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadCandidatureFormationGtcApprobation &= group
								.getRead();
					}
					if (group.getWrite() != null) {
						canEditCandidatureFormationGtcApprobation &= group
								.getWrite();
					}
				} else if ("qualification.description".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadQualificationDescription &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditQualificationDescription &= group.getWrite();
					}
				} else if ("tutoriel.description".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadTutorielDescription &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditTutorielDescription &= group.getWrite();
					}
				} else if ("smspredefini.description".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadSmsPredefiniDescription &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditSmsPredefiniDescription &= group.getWrite();
					}
				} else if ("outbox.adresse".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadOutBoxAdresse &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditOutBoxAdresse &= group.getWrite();
					}
				} else if ("outbox.messageinformation".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadOutBoxMessageInformation &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditOutBoxMessageInformation &= group.getWrite();
					}
				} else if ("utilisateur.identification".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadUtilisateurIdentification &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditUtilisateurIdentification &= group.getWrite();
					}
				} else if ("utilisateur.contact".equals(fieldGroupId)) {
					if (group.getRead() != null) {
						canReadUtilisateurContact &= group.getRead();
					}
					if (group.getWrite() != null) {
						canEditUtilisateurContact &= group.getWrite();
					}
				}
			}
		}
	}

	/**
	 * Tells if the current user can access a domain object property
	 * @param bean the object whose property access has to be checked
	 * @param property the property whose access has to be checked
	 * @return true if the current user can access an Object property
	 */
	public boolean canAccessProperty(Object bean, String property) {
		if (property.equals("password")) {
			return false;
		}
		if (isAdmin) {
			return true;
		}
		if (bean instanceof Patient) {
			return canAccessPatientProperty(property);
		}
		if (bean instanceof CasIndex) {
			return canAccessCasIndexProperty(property);
		}
		if (bean instanceof CasTuberculose) {
			return canAccessCasTuberculoseProperty(property);
		}
		if (bean instanceof ExamenSerologie) {
			return canAccessExamenSerologieProperty(property);
		}
		if (bean instanceof ExamenBiologique) {
			return canAccessExamenBiologiqueProperty(property);
		}
		if (bean instanceof ExamenMicroscopie) {
			return canAccessExamenMicroscopieProperty(property);
		}
		if (bean instanceof ExamenATB) {
			return canAccessExamenATBProperty(property);
		}
		if (bean instanceof PriseMedicamenteuse) {
			return canAccessPriseMedicamenteuseProperty(property);
		}
		if (bean instanceof RendezVous) {
			return canAccessRendezVousProperty(property);
		}
		if (bean instanceof TransfertReference) {
			return canAccessTransfertReferenceProperty(property);
		}
		if (bean instanceof Lot) {
			return canAccessLotProperty(property);
		}
		if (bean instanceof HorsUsage) {
			return canAccessHorsUsageProperty(property);
		}
		if (bean instanceof EntreeLot) {
			return canAccessEntreeLotProperty(property);
		}
		if (bean instanceof SortieLot) {
			return canAccessSortieLotProperty(property);
		}
		if (bean instanceof Commande) {
			return canAccessCommandeProperty(property);
		}
		if (bean instanceof DetailCommandeMedicament) {
			return canAccessDetailCommandeMedicamentProperty(property);
		}
		if (bean instanceof DetailCommandeIntrant) {
			return canAccessDetailCommandeIntrantProperty(property);
		}
		if (bean instanceof Reception) {
			return canAccessReceptionProperty(property);
		}
		if (bean instanceof DetailReceptionMedicament) {
			return canAccessDetailReceptionMedicamentProperty(property);
		}
		if (bean instanceof DetailReceptionIntrant) {
			return canAccessDetailReceptionIntrantProperty(property);
		}
		if (bean instanceof Ravitaillement) {
			return canAccessRavitaillementProperty(property);
		}
		if (bean instanceof DetailRavitaillement) {
			return canAccessDetailRavitaillementProperty(property);
		}
		if (bean instanceof Inventaire) {
			return canAccessInventaireProperty(property);
		}
		if (bean instanceof DetailInventaire) {
			return canAccessDetailInventaireProperty(property);
		}
		if (bean instanceof Personnel) {
			return canAccessPersonnelProperty(property);
		}
		if (bean instanceof DepartPersonnel) {
			return canAccessDepartPersonnelProperty(property);
		}
		if (bean instanceof ArriveePersonnel) {
			return canAccessArriveePersonnelProperty(property);
		}
		if (bean instanceof Region) {
			return canAccessRegionProperty(property);
		}
		if (bean instanceof DistrictSante) {
			return canAccessDistrictSanteProperty(property);
		}
		if (bean instanceof CentreDiagTrait) {
			return canAccessCentreDiagTraitProperty(property);
		}
		if (bean instanceof LaboratoireReference) {
			return canAccessLaboratoireReferenceProperty(property);
		}
		if (bean instanceof LieuDit) {
			return canAccessLieuDitProperty(property);
		}
		if (bean instanceof Regime) {
			return canAccessRegimeProperty(property);
		}
		if (bean instanceof PriseMedicamentRegime) {
			return canAccessPriseMedicamentRegimeProperty(property);
		}
		if (bean instanceof Medicament) {
			return canAccessMedicamentProperty(property);
		}
		if (bean instanceof Intrant) {
			return canAccessIntrantProperty(property);
		}
		if (bean instanceof Formation) {
			return canAccessFormationProperty(property);
		}
		if (bean instanceof CandidatureFormation) {
			return canAccessCandidatureFormationProperty(property);
		}
		if (bean instanceof Qualification) {
			return canAccessQualificationProperty(property);
		}
		if (bean instanceof Tutoriel) {
			return canAccessTutorielProperty(property);
		}
		if (bean instanceof SmsPredefini) {
			return canAccessSmsPredefiniProperty(property);
		}
		if (bean instanceof OutBox) {
			return canAccessOutBoxProperty(property);
		}
		if (bean instanceof Utilisateur) {
			return canAccessUtilisateurProperty(property);
		}
		return true;
	}

	/**
	 * Tells if the current user can update a domain object property
	 * @param bean the object whose property update privilege has to be checked
	 * @param property the property whose update privilege has to be checked
	 * @return true if the current user can update an Object property
	 */
	public boolean canUpdateProperty(Object bean, String property) {
		if (isAdmin) {
			return true;
		}
		if (bean instanceof Patient) {
			return canUpdatePatientProperty(property);
		}
		if (bean instanceof CasIndex) {
			return canUpdateCasIndexProperty(property);
		}
		if (bean instanceof CasTuberculose) {
			return canUpdateCasTuberculoseProperty(property);
		}
		if (bean instanceof ExamenSerologie) {
			return canUpdateExamenSerologieProperty(property);
		}
		if (bean instanceof ExamenBiologique) {
			return canUpdateExamenBiologiqueProperty(property);
		}
		if (bean instanceof ExamenMicroscopie) {
			return canUpdateExamenMicroscopieProperty(property);
		}
		if (bean instanceof ExamenATB) {
			return canUpdateExamenATBProperty(property);
		}
		if (bean instanceof PriseMedicamenteuse) {
			return canUpdatePriseMedicamenteuseProperty(property);
		}
		if (bean instanceof RendezVous) {
			return canUpdateRendezVousProperty(property);
		}
		if (bean instanceof TransfertReference) {
			return canUpdateTransfertReferenceProperty(property);
		}
		if (bean instanceof Lot) {
			return canUpdateLotProperty(property);
		}
		if (bean instanceof HorsUsage) {
			return canUpdateHorsUsageProperty(property);
		}
		if (bean instanceof EntreeLot) {
			return canUpdateEntreeLotProperty(property);
		}
		if (bean instanceof SortieLot) {
			return canUpdateSortieLotProperty(property);
		}
		if (bean instanceof Commande) {
			return canUpdateCommandeProperty(property);
		}
		if (bean instanceof DetailCommandeMedicament) {
			return canUpdateDetailCommandeMedicamentProperty(property);
		}
		if (bean instanceof DetailCommandeIntrant) {
			return canUpdateDetailCommandeIntrantProperty(property);
		}
		if (bean instanceof Reception) {
			return canUpdateReceptionProperty(property);
		}
		if (bean instanceof DetailReceptionMedicament) {
			return canUpdateDetailReceptionMedicamentProperty(property);
		}
		if (bean instanceof DetailReceptionIntrant) {
			return canUpdateDetailReceptionIntrantProperty(property);
		}
		if (bean instanceof Ravitaillement) {
			return canUpdateRavitaillementProperty(property);
		}
		if (bean instanceof DetailRavitaillement) {
			return canUpdateDetailRavitaillementProperty(property);
		}
		if (bean instanceof Inventaire) {
			return canUpdateInventaireProperty(property);
		}
		if (bean instanceof DetailInventaire) {
			return canUpdateDetailInventaireProperty(property);
		}
		if (bean instanceof Personnel) {
			return canUpdatePersonnelProperty(property);
		}
		if (bean instanceof DepartPersonnel) {
			return canUpdateDepartPersonnelProperty(property);
		}
		if (bean instanceof ArriveePersonnel) {
			return canUpdateArriveePersonnelProperty(property);
		}
		if (bean instanceof Region) {
			return canUpdateRegionProperty(property);
		}
		if (bean instanceof DistrictSante) {
			return canUpdateDistrictSanteProperty(property);
		}
		if (bean instanceof CentreDiagTrait) {
			return canUpdateCentreDiagTraitProperty(property);
		}
		if (bean instanceof LaboratoireReference) {
			return canUpdateLaboratoireReferenceProperty(property);
		}
		if (bean instanceof LieuDit) {
			return canUpdateLieuDitProperty(property);
		}
		if (bean instanceof Regime) {
			return canUpdateRegimeProperty(property);
		}
		if (bean instanceof PriseMedicamentRegime) {
			return canUpdatePriseMedicamentRegimeProperty(property);
		}
		if (bean instanceof Medicament) {
			return canUpdateMedicamentProperty(property);
		}
		if (bean instanceof Intrant) {
			return canUpdateIntrantProperty(property);
		}
		if (bean instanceof Formation) {
			return canUpdateFormationProperty(property);
		}
		if (bean instanceof CandidatureFormation) {
			return canUpdateCandidatureFormationProperty(property);
		}
		if (bean instanceof Qualification) {
			return canUpdateQualificationProperty(property);
		}
		if (bean instanceof Tutoriel) {
			return canUpdateTutorielProperty(property);
		}
		if (bean instanceof SmsPredefini) {
			return canUpdateSmsPredefiniProperty(property);
		}
		if (bean instanceof OutBox) {
			return canUpdateOutBoxProperty(property);
		}
		if (bean instanceof Utilisateur) {
			return canUpdateUtilisateurProperty(property);
		}
		return true;
	}

	public boolean canReadPatientIdentification() {
		return canReadPatientIdentification;
	}
	public boolean canEditPatientIdentification() {
		return canEditPatientIdentification;
	}
	public boolean canReadPatientContact() {
		return canReadPatientContact;
	}
	public boolean canEditPatientContact() {
		return canEditPatientContact;
	}
	public boolean canReadPatientPersonneContact() {
		return canReadPatientPersonneContact;
	}
	public boolean canEditPatientPersonneContact() {
		return canEditPatientPersonneContact;
	}
	public boolean canReadPatientTuberculose() {
		return canReadPatientTuberculose;
	}
	public boolean canEditPatientTuberculose() {
		return canEditPatientTuberculose;
	}
	public boolean canReadPatientExamens() {
		return canReadPatientExamens;
	}
	public boolean canEditPatientExamens() {
		return canEditPatientExamens;
	}
	public boolean canReadCasIndexDescription() {
		return canReadCasIndexDescription;
	}
	public boolean canEditCasIndexDescription() {
		return canEditCasIndexDescription;
	}
	public boolean canReadCasTuberculoseInformations() {
		return canReadCasTuberculoseInformations;
	}
	public boolean canEditCasTuberculoseInformations() {
		return canEditCasTuberculoseInformations;
	}
	public boolean canReadCasTuberculoseExamen() {
		return canReadCasTuberculoseExamen;
	}
	public boolean canEditCasTuberculoseExamen() {
		return canEditCasTuberculoseExamen;
	}
	public boolean canReadCasTuberculoseTraitement() {
		return canReadCasTuberculoseTraitement;
	}
	public boolean canEditCasTuberculoseTraitement() {
		return canEditCasTuberculoseTraitement;
	}
	public boolean canReadCasTuberculoseFinTraitement() {
		return canReadCasTuberculoseFinTraitement;
	}
	public boolean canEditCasTuberculoseFinTraitement() {
		return canEditCasTuberculoseFinTraitement;
	}
	public boolean canReadExamenSerologieDescription() {
		return canReadExamenSerologieDescription;
	}
	public boolean canEditExamenSerologieDescription() {
		return canEditExamenSerologieDescription;
	}
	public boolean canReadExamenBiologiqueDescription() {
		return canReadExamenBiologiqueDescription;
	}
	public boolean canEditExamenBiologiqueDescription() {
		return canEditExamenBiologiqueDescription;
	}
	public boolean canReadExamenMicroscopieCentreExamen() {
		return canReadExamenMicroscopieCentreExamen;
	}
	public boolean canEditExamenMicroscopieCentreExamen() {
		return canEditExamenMicroscopieCentreExamen;
	}
	public boolean canReadExamenMicroscopieExamen() {
		return canReadExamenMicroscopieExamen;
	}
	public boolean canEditExamenMicroscopieExamen() {
		return canEditExamenMicroscopieExamen;
	}
	public boolean canReadExamenATBCentreExamen() {
		return canReadExamenATBCentreExamen;
	}
	public boolean canEditExamenATBCentreExamen() {
		return canEditExamenATBCentreExamen;
	}
	public boolean canReadExamenATBExamen() {
		return canReadExamenATBExamen;
	}
	public boolean canEditExamenATBExamen() {
		return canEditExamenATBExamen;
	}
	public boolean canReadPriseMedicamenteuseDescription() {
		return canReadPriseMedicamenteuseDescription;
	}
	public boolean canEditPriseMedicamenteuseDescription() {
		return canEditPriseMedicamenteuseDescription;
	}
	public boolean canReadRendezVousDescription() {
		return canReadRendezVousDescription;
	}
	public boolean canEditRendezVousDescription() {
		return canEditRendezVousDescription;
	}
	public boolean canReadTransfertReferenceInformationsDepart() {
		return canReadTransfertReferenceInformationsDepart;
	}
	public boolean canEditTransfertReferenceInformationsDepart() {
		return canEditTransfertReferenceInformationsDepart;
	}
	public boolean canReadTransfertReferenceInformationArrivee() {
		return canReadTransfertReferenceInformationArrivee;
	}
	public boolean canEditTransfertReferenceInformationArrivee() {
		return canEditTransfertReferenceInformationArrivee;
	}
	public boolean canReadLotDescription() {
		return canReadLotDescription;
	}
	public boolean canEditLotDescription() {
		return canEditLotDescription;
	}
	public boolean canReadHorsUsageDescription() {
		return canReadHorsUsageDescription;
	}
	public boolean canEditHorsUsageDescription() {
		return canEditHorsUsageDescription;
	}
	public boolean canReadEntreeLotDescription() {
		return canReadEntreeLotDescription;
	}
	public boolean canEditEntreeLotDescription() {
		return canEditEntreeLotDescription;
	}
	public boolean canReadSortieLotDescription() {
		return canReadSortieLotDescription;
	}
	public boolean canEditSortieLotDescription() {
		return canEditSortieLotDescription;
	}
	public boolean canReadCommandeInformationsDepart() {
		return canReadCommandeInformationsDepart;
	}
	public boolean canEditCommandeInformationsDepart() {
		return canEditCommandeInformationsDepart;
	}
	public boolean canReadCommandeRegionApprobation() {
		return canReadCommandeRegionApprobation;
	}
	public boolean canEditCommandeRegionApprobation() {
		return canEditCommandeRegionApprobation;
	}
	public boolean canReadCommandeGtcApprobation() {
		return canReadCommandeGtcApprobation;
	}
	public boolean canEditCommandeGtcApprobation() {
		return canEditCommandeGtcApprobation;
	}
	public boolean canReadDetailCommandeMedicamentDescription() {
		return canReadDetailCommandeMedicamentDescription;
	}
	public boolean canEditDetailCommandeMedicamentDescription() {
		return canEditDetailCommandeMedicamentDescription;
	}
	public boolean canReadDetailCommandeIntrantDescription() {
		return canReadDetailCommandeIntrantDescription;
	}
	public boolean canEditDetailCommandeIntrantDescription() {
		return canEditDetailCommandeIntrantDescription;
	}
	public boolean canReadReceptionDescription() {
		return canReadReceptionDescription;
	}
	public boolean canEditReceptionDescription() {
		return canEditReceptionDescription;
	}
	public boolean canReadDetailReceptionMedicamentDescription() {
		return canReadDetailReceptionMedicamentDescription;
	}
	public boolean canEditDetailReceptionMedicamentDescription() {
		return canEditDetailReceptionMedicamentDescription;
	}
	public boolean canReadDetailReceptionIntrantDescription() {
		return canReadDetailReceptionIntrantDescription;
	}
	public boolean canEditDetailReceptionIntrantDescription() {
		return canEditDetailReceptionIntrantDescription;
	}
	public boolean canReadRavitaillementInformationsDepart() {
		return canReadRavitaillementInformationsDepart;
	}
	public boolean canEditRavitaillementInformationsDepart() {
		return canEditRavitaillementInformationsDepart;
	}
	public boolean canReadRavitaillementInformationArrivee() {
		return canReadRavitaillementInformationArrivee;
	}
	public boolean canEditRavitaillementInformationArrivee() {
		return canEditRavitaillementInformationArrivee;
	}
	public boolean canReadRavitaillementDetail() {
		return canReadRavitaillementDetail;
	}
	public boolean canEditRavitaillementDetail() {
		return canEditRavitaillementDetail;
	}
	public boolean canReadDetailRavitaillementDescription() {
		return canReadDetailRavitaillementDescription;
	}
	public boolean canEditDetailRavitaillementDescription() {
		return canEditDetailRavitaillementDescription;
	}
	public boolean canReadDetailRavitaillementSortie() {
		return canReadDetailRavitaillementSortie;
	}
	public boolean canEditDetailRavitaillementSortie() {
		return canEditDetailRavitaillementSortie;
	}
	public boolean canReadDetailRavitaillementEntree() {
		return canReadDetailRavitaillementEntree;
	}
	public boolean canEditDetailRavitaillementEntree() {
		return canEditDetailRavitaillementEntree;
	}
	public boolean canReadInventaireInformationsDepart() {
		return canReadInventaireInformationsDepart;
	}
	public boolean canEditInventaireInformationsDepart() {
		return canEditInventaireInformationsDepart;
	}
	public boolean canReadDetailInventaireDescription() {
		return canReadDetailInventaireDescription;
	}
	public boolean canEditDetailInventaireDescription() {
		return canEditDetailInventaireDescription;
	}
	public boolean canReadPersonnelIdentification() {
		return canReadPersonnelIdentification;
	}
	public boolean canEditPersonnelIdentification() {
		return canEditPersonnelIdentification;
	}
	public boolean canReadPersonnelContact() {
		return canReadPersonnelContact;
	}
	public boolean canEditPersonnelContact() {
		return canEditPersonnelContact;
	}
	public boolean canReadPersonnelSituation() {
		return canReadPersonnelSituation;
	}
	public boolean canEditPersonnelSituation() {
		return canEditPersonnelSituation;
	}
	public boolean canReadPersonnelNiveauAccess() {
		return canReadPersonnelNiveauAccess;
	}
	public boolean canEditPersonnelNiveauAccess() {
		return canEditPersonnelNiveauAccess;
	}
	public boolean canReadDepartPersonnelPersonnel() {
		return canReadDepartPersonnelPersonnel;
	}
	public boolean canEditDepartPersonnelPersonnel() {
		return canEditDepartPersonnelPersonnel;
	}
	public boolean canReadDepartPersonnelDescription() {
		return canReadDepartPersonnelDescription;
	}
	public boolean canEditDepartPersonnelDescription() {
		return canEditDepartPersonnelDescription;
	}
	public boolean canReadArriveePersonnelDescription() {
		return canReadArriveePersonnelDescription;
	}
	public boolean canEditArriveePersonnelDescription() {
		return canEditArriveePersonnelDescription;
	}
	public boolean canReadRegionDescription() {
		return canReadRegionDescription;
	}
	public boolean canEditRegionDescription() {
		return canEditRegionDescription;
	}
	public boolean canReadRegionAdresse() {
		return canReadRegionAdresse;
	}
	public boolean canEditRegionAdresse() {
		return canEditRegionAdresse;
	}
	public boolean canReadDistrictSanteDescription() {
		return canReadDistrictSanteDescription;
	}
	public boolean canEditDistrictSanteDescription() {
		return canEditDistrictSanteDescription;
	}
	public boolean canReadDistrictSanteAdresse() {
		return canReadDistrictSanteAdresse;
	}
	public boolean canEditDistrictSanteAdresse() {
		return canEditDistrictSanteAdresse;
	}
	public boolean canReadCentreDiagTraitDescription() {
		return canReadCentreDiagTraitDescription;
	}
	public boolean canEditCentreDiagTraitDescription() {
		return canEditCentreDiagTraitDescription;
	}
	public boolean canReadCentreDiagTraitAdresse() {
		return canReadCentreDiagTraitAdresse;
	}
	public boolean canEditCentreDiagTraitAdresse() {
		return canEditCentreDiagTraitAdresse;
	}
	public boolean canReadLaboratoireReferenceDescription() {
		return canReadLaboratoireReferenceDescription;
	}
	public boolean canEditLaboratoireReferenceDescription() {
		return canEditLaboratoireReferenceDescription;
	}
	public boolean canReadLaboratoireReferenceAdresse() {
		return canReadLaboratoireReferenceAdresse;
	}
	public boolean canEditLaboratoireReferenceAdresse() {
		return canEditLaboratoireReferenceAdresse;
	}
	public boolean canReadLieuDitDescription() {
		return canReadLieuDitDescription;
	}
	public boolean canEditLieuDitDescription() {
		return canEditLieuDitDescription;
	}
	public boolean canReadLieuDitAdresse() {
		return canReadLieuDitAdresse;
	}
	public boolean canEditLieuDitAdresse() {
		return canEditLieuDitAdresse;
	}
	public boolean canReadRegimeDescription() {
		return canReadRegimeDescription;
	}
	public boolean canEditRegimeDescription() {
		return canEditRegimeDescription;
	}
	public boolean canReadPriseMedicamentRegimeDescription() {
		return canReadPriseMedicamentRegimeDescription;
	}
	public boolean canEditPriseMedicamentRegimeDescription() {
		return canEditPriseMedicamentRegimeDescription;
	}
	public boolean canReadMedicamentDescription() {
		return canReadMedicamentDescription;
	}
	public boolean canEditMedicamentDescription() {
		return canEditMedicamentDescription;
	}
	public boolean canReadIntrantDescription() {
		return canReadIntrantDescription;
	}
	public boolean canEditIntrantDescription() {
		return canEditIntrantDescription;
	}
	public boolean canReadFormationDescription() {
		return canReadFormationDescription;
	}
	public boolean canEditFormationDescription() {
		return canEditFormationDescription;
	}
	public boolean canReadCandidatureFormationDescription() {
		return canReadCandidatureFormationDescription;
	}
	public boolean canEditCandidatureFormationDescription() {
		return canEditCandidatureFormationDescription;
	}
	public boolean canReadCandidatureFormationRegionApprobation() {
		return canReadCandidatureFormationRegionApprobation;
	}
	public boolean canEditCandidatureFormationRegionApprobation() {
		return canEditCandidatureFormationRegionApprobation;
	}
	public boolean canReadCandidatureFormationGtcApprobation() {
		return canReadCandidatureFormationGtcApprobation;
	}
	public boolean canEditCandidatureFormationGtcApprobation() {
		return canEditCandidatureFormationGtcApprobation;
	}
	public boolean canReadQualificationDescription() {
		return canReadQualificationDescription;
	}
	public boolean canEditQualificationDescription() {
		return canEditQualificationDescription;
	}
	public boolean canReadTutorielDescription() {
		return canReadTutorielDescription;
	}
	public boolean canEditTutorielDescription() {
		return canEditTutorielDescription;
	}
	public boolean canReadSmsPredefiniDescription() {
		return canReadSmsPredefiniDescription;
	}
	public boolean canEditSmsPredefiniDescription() {
		return canEditSmsPredefiniDescription;
	}
	public boolean canReadOutBoxAdresse() {
		return canReadOutBoxAdresse;
	}
	public boolean canEditOutBoxAdresse() {
		return canEditOutBoxAdresse;
	}
	public boolean canReadOutBoxMessageInformation() {
		return canReadOutBoxMessageInformation;
	}
	public boolean canEditOutBoxMessageInformation() {
		return canEditOutBoxMessageInformation;
	}
	public boolean canReadUtilisateurIdentification() {
		return canReadUtilisateurIdentification;
	}
	public boolean canEditUtilisateurIdentification() {
		return canEditUtilisateurIdentification;
	}
	public boolean canReadUtilisateurContact() {
		return canReadUtilisateurContact;
	}
	public boolean canEditUtilisateurContact() {
		return canEditUtilisateurContact;
	}

	/* Privileges check for entity Patient */

	/**
	 * Tells if the current user can access a Patient property
	 * @param property the property whose access has to be checked
	 * @return true if the current user can access a Patient property
	 */
	private boolean canAccessPatientProperty(String property) {
		if ("identifiant".equals(property)) {
			return canReadPatientIdentification;
		}
		if ("nom".equals(property)) {
			return canReadPatientIdentification;
		}
		if ("sexe".equals(property)) {
			return canReadPatientIdentification;
		}
		if ("dateNaissance".equals(property)) {
			return canReadPatientIdentification;
		}
		if ("age".equals(property)) {
			return canReadPatientIdentification;
		}
		if ("profession".equals(property)) {
			return canReadPatientIdentification;
		}
		if ("centres".equals(property)) {
			return canReadPatientIdentification;
		}
		if ("nationalite".equals(property)) {
			return canReadPatientIdentification;
		}
		if ("precisionSurNationalite".equals(property)) {
			return canReadPatientIdentification;
		}
		if ("recevoirCarnetTelPortable".equals(property)) {
			return canReadPatientIdentification;
		}
		if ("telephoneUn".equals(property)) {
			return canReadPatientContact;
		}
		if ("telephoneDeux".equals(property)) {
			return canReadPatientContact;
		}
		if ("telephoneTrois".equals(property)) {
			return canReadPatientContact;
		}
		if ("email".equals(property)) {
			return canReadPatientContact;
		}
		if ("libelle".equals(property)) {
			return canReadPatientContact;
		}
		if ("complementAdresse".equals(property)) {
			return canReadPatientContact;
		}
		if ("quartier".equals(property)) {
			return canReadPatientContact;
		}
		if ("ville".equals(property)) {
			return canReadPatientContact;
		}
		if ("codePostal".equals(property)) {
			return canReadPatientContact;
		}
		if ("lieuxDits".equals(property)) {
			return canReadPatientContact;
		}
		if ("pacNom".equals(property)) {
			return canReadPatientPersonneContact;
		}
		if ("pacTelephoneUn".equals(property)) {
			return canReadPatientPersonneContact;
		}
		if ("pacTelephoneDeux".equals(property)) {
			return canReadPatientPersonneContact;
		}
		if ("pacTelephoneTrois".equals(property)) {
			return canReadPatientPersonneContact;
		}
		if ("pacEmail".equals(property)) {
			return canReadPatientPersonneContact;
		}
		if ("pacLibelle".equals(property)) {
			return canReadPatientPersonneContact;
		}
		if ("pacComplementAdresse".equals(property)) {
			return canReadPatientPersonneContact;
		}
		if ("pacQuartier".equals(property)) {
			return canReadPatientPersonneContact;
		}
		if ("pacVille".equals(property)) {
			return canReadPatientPersonneContact;
		}
		if ("pacCodePostal".equals(property)) {
			return canReadPatientPersonneContact;
		}
		if ("casTuberculose".equals(property)) {
			return canReadPatientTuberculose;
		}
		if ("casIndex".equals(property)) {
			return canReadPatientTuberculose;
		}
		if ("examensBiologiques".equals(property)) {
			return canReadPatientExamens;
		}
		if ("serologies".equals(property)) {
			return canReadPatientExamens;
		}
		return true;
	}

	/**
	 * Tells if the current user can update a Patient property
	 * @param property the property whose update privilege has to be checked
	 * @return true if the current user can update a Patient property
	 */
	private boolean canUpdatePatientProperty(String property) {
		if ("identifiant".equals(property)) {
			return canEditPatientIdentification;
		}
		if ("nom".equals(property)) {
			return canEditPatientIdentification;
		}
		if ("sexe".equals(property)) {
			return canEditPatientIdentification;
		}
		if ("dateNaissance".equals(property)) {
			return canEditPatientIdentification;
		}
		if ("age".equals(property)) {
			return canEditPatientIdentification;
		}
		if ("profession".equals(property)) {
			return canEditPatientIdentification;
		}
		if ("centres".equals(property)) {
			return canEditPatientIdentification;
		}
		if ("nationalite".equals(property)) {
			return canEditPatientIdentification;
		}
		if ("precisionSurNationalite".equals(property)) {
			return canEditPatientIdentification;
		}
		if ("recevoirCarnetTelPortable".equals(property)) {
			return canEditPatientIdentification;
		}
		if ("telephoneUn".equals(property)) {
			return canEditPatientContact;
		}
		if ("telephoneDeux".equals(property)) {
			return canEditPatientContact;
		}
		if ("telephoneTrois".equals(property)) {
			return canEditPatientContact;
		}
		if ("email".equals(property)) {
			return canEditPatientContact;
		}
		if ("libelle".equals(property)) {
			return canEditPatientContact;
		}
		if ("complementAdresse".equals(property)) {
			return canEditPatientContact;
		}
		if ("quartier".equals(property)) {
			return canEditPatientContact;
		}
		if ("ville".equals(property)) {
			return canEditPatientContact;
		}
		if ("codePostal".equals(property)) {
			return canEditPatientContact;
		}
		if ("lieuxDits".equals(property)) {
			return canEditPatientContact;
		}
		if ("pacNom".equals(property)) {
			return canEditPatientPersonneContact;
		}
		if ("pacTelephoneUn".equals(property)) {
			return canEditPatientPersonneContact;
		}
		if ("pacTelephoneDeux".equals(property)) {
			return canEditPatientPersonneContact;
		}
		if ("pacTelephoneTrois".equals(property)) {
			return canEditPatientPersonneContact;
		}
		if ("pacEmail".equals(property)) {
			return canEditPatientPersonneContact;
		}
		if ("pacLibelle".equals(property)) {
			return canEditPatientPersonneContact;
		}
		if ("pacComplementAdresse".equals(property)) {
			return canEditPatientPersonneContact;
		}
		if ("pacQuartier".equals(property)) {
			return canEditPatientPersonneContact;
		}
		if ("pacVille".equals(property)) {
			return canEditPatientPersonneContact;
		}
		if ("pacCodePostal".equals(property)) {
			return canEditPatientPersonneContact;
		}
		if ("casTuberculose".equals(property)) {
			return canEditPatientTuberculose;
		}
		if ("casIndex".equals(property)) {
			return canEditPatientTuberculose;
		}
		if ("examensBiologiques".equals(property)) {
			return canEditPatientExamens;
		}
		if ("serologies".equals(property)) {
			return canEditPatientExamens;
		}
		return true;
	}
	/* Privileges check for entity CasIndex */

	/**
	 * Tells if the current user can access a CasIndex property
	 * @param property the property whose access has to be checked
	 * @return true if the current user can access a CasIndex property
	 */
	private boolean canAccessCasIndexProperty(String property) {
		if ("patient".equals(property)) {
			return canReadCasIndexDescription;
		}
		if ("patientLie".equals(property)) {
			return canReadCasIndexDescription;
		}
		if ("typeRelation".equals(property)) {
			return canReadCasIndexDescription;
		}
		return true;
	}

	/**
	 * Tells if the current user can update a CasIndex property
	 * @param property the property whose update privilege has to be checked
	 * @return true if the current user can update a CasIndex property
	 */
	private boolean canUpdateCasIndexProperty(String property) {
		if ("patient".equals(property)) {
			return canEditCasIndexDescription;
		}
		if ("patientLie".equals(property)) {
			return canEditCasIndexDescription;
		}
		if ("typeRelation".equals(property)) {
			return canEditCasIndexDescription;
		}
		return true;
	}
	/* Privileges check for entity CasTuberculose */

	/**
	 * Tells if the current user can access a CasTuberculose property
	 * @param property the property whose access has to be checked
	 * @return true if the current user can access a CasTuberculose property
	 */
	private boolean canAccessCasTuberculoseProperty(String property) {
		if ("identifiant".equals(property)) {
			return canReadCasTuberculoseInformations;
		}
		if ("numRegTB".equals(property)) {
			return canReadCasTuberculoseInformations;
		}
		if ("patient".equals(property)) {
			return canReadCasTuberculoseInformations;
		}
		if ("dateDebutTraitement".equals(property)) {
			return canReadCasTuberculoseInformations;
		}
		if ("typePatient".equals(property)) {
			return canReadCasTuberculoseInformations;
		}
		if ("typePatientPrecisions".equals(property)) {
			return canReadCasTuberculoseInformations;
		}
		if ("formeMaladie".equals(property)) {
			return canReadCasTuberculoseInformations;
		}
		if ("extraPulmonairePrecisions".equals(property)) {
			return canReadCasTuberculoseInformations;
		}
		if ("cotrimoxazole".equals(property)) {
			return canReadCasTuberculoseInformations;
		}
		if ("antiRetroViraux".equals(property)) {
			return canReadCasTuberculoseInformations;
		}
		if ("fumeur".equals(property)) {
			return canReadCasTuberculoseInformations;
		}
		if ("fumeurArreter".equals(property)) {
			return canReadCasTuberculoseInformations;
		}
		if ("examensMiscrocopies".equals(property)) {
			return canReadCasTuberculoseExamen;
		}
		if ("examensATB".equals(property)) {
			return canReadCasTuberculoseExamen;
		}
		if ("regimePhaseInitiale".equals(property)) {
			return canReadCasTuberculoseTraitement;
		}
		if ("regimePhaseContinuation".equals(property)) {
			return canReadCasTuberculoseTraitement;
		}
		if ("priseMedicamenteusePhaseInitiale".equals(property)) {
			return canReadCasTuberculoseTraitement;
		}
		if ("priseMedicamenteusePhaseContinuation".equals(property)) {
			return canReadCasTuberculoseTraitement;
		}
		if ("rendezVous".equals(property)) {
			return canReadCasTuberculoseTraitement;
		}
		if ("dateFinTraitement".equals(property)) {
			return canReadCasTuberculoseFinTraitement;
		}
		if ("devenirMalade".equals(property)) {
			return canReadCasTuberculoseFinTraitement;
		}
		if ("observation".equals(property)) {
			return canReadCasTuberculoseFinTraitement;
		}
		return true;
	}

	/**
	 * Tells if the current user can update a CasTuberculose property
	 * @param property the property whose update privilege has to be checked
	 * @return true if the current user can update a CasTuberculose property
	 */
	private boolean canUpdateCasTuberculoseProperty(String property) {
		if ("identifiant".equals(property)) {
			return canEditCasTuberculoseInformations;
		}
		if ("numRegTB".equals(property)) {
			return canEditCasTuberculoseInformations;
		}
		if ("patient".equals(property)) {
			return canEditCasTuberculoseInformations;
		}
		if ("dateDebutTraitement".equals(property)) {
			return canEditCasTuberculoseInformations;
		}
		if ("typePatient".equals(property)) {
			return canEditCasTuberculoseInformations;
		}
		if ("typePatientPrecisions".equals(property)) {
			return canEditCasTuberculoseInformations;
		}
		if ("formeMaladie".equals(property)) {
			return canEditCasTuberculoseInformations;
		}
		if ("extraPulmonairePrecisions".equals(property)) {
			return canEditCasTuberculoseInformations;
		}
		if ("cotrimoxazole".equals(property)) {
			return canEditCasTuberculoseInformations;
		}
		if ("antiRetroViraux".equals(property)) {
			return canEditCasTuberculoseInformations;
		}
		if ("fumeur".equals(property)) {
			return canEditCasTuberculoseInformations;
		}
		if ("fumeurArreter".equals(property)) {
			return canEditCasTuberculoseInformations;
		}
		if ("examensMiscrocopies".equals(property)) {
			return canEditCasTuberculoseExamen;
		}
		if ("examensATB".equals(property)) {
			return canEditCasTuberculoseExamen;
		}
		if ("regimePhaseInitiale".equals(property)) {
			return canEditCasTuberculoseTraitement;
		}
		if ("regimePhaseContinuation".equals(property)) {
			return canEditCasTuberculoseTraitement;
		}
		if ("priseMedicamenteusePhaseInitiale".equals(property)) {
			return canEditCasTuberculoseTraitement;
		}
		if ("priseMedicamenteusePhaseContinuation".equals(property)) {
			return canEditCasTuberculoseTraitement;
		}
		if ("rendezVous".equals(property)) {
			return canEditCasTuberculoseTraitement;
		}
		if ("dateFinTraitement".equals(property)) {
			return canEditCasTuberculoseFinTraitement;
		}
		if ("devenirMalade".equals(property)) {
			return canEditCasTuberculoseFinTraitement;
		}
		if ("observation".equals(property)) {
			return canEditCasTuberculoseFinTraitement;
		}
		return true;
	}
	/* Privileges check for entity ExamenSerologie */

	/**
	 * Tells if the current user can access a ExamenSerologie property
	 * @param property the property whose access has to be checked
	 * @return true if the current user can access a ExamenSerologie property
	 */
	private boolean canAccessExamenSerologieProperty(String property) {
		if ("patient".equals(property)) {
			return canReadExamenSerologieDescription;
		}
		if ("dateTest".equals(property)) {
			return canReadExamenSerologieDescription;
		}
		if ("nature".equals(property)) {
			return canReadExamenSerologieDescription;
		}
		if ("resultatVIH".equals(property)) {
			return canReadExamenSerologieDescription;
		}
		if ("resultatCD4".equals(property)) {
			return canReadExamenSerologieDescription;
		}
		return true;
	}

	/**
	 * Tells if the current user can update a ExamenSerologie property
	 * @param property the property whose update privilege has to be checked
	 * @return true if the current user can update a ExamenSerologie property
	 */
	private boolean canUpdateExamenSerologieProperty(String property) {
		if ("patient".equals(property)) {
			return canEditExamenSerologieDescription;
		}
		if ("dateTest".equals(property)) {
			return canEditExamenSerologieDescription;
		}
		if ("nature".equals(property)) {
			return canEditExamenSerologieDescription;
		}
		if ("resultatVIH".equals(property)) {
			return canEditExamenSerologieDescription;
		}
		if ("resultatCD4".equals(property)) {
			return canEditExamenSerologieDescription;
		}
		return true;
	}
	/* Privileges check for entity ExamenBiologique */

	/**
	 * Tells if the current user can access a ExamenBiologique property
	 * @param property the property whose access has to be checked
	 * @return true if the current user can access a ExamenBiologique property
	 */
	private boolean canAccessExamenBiologiqueProperty(String property) {
		if ("patient".equals(property)) {
			return canReadExamenBiologiqueDescription;
		}
		if ("date".equals(property)) {
			return canReadExamenBiologiqueDescription;
		}
		if ("poids".equals(property)) {
			return canReadExamenBiologiqueDescription;
		}
		if ("observations".equals(property)) {
			return canReadExamenBiologiqueDescription;
		}
		return true;
	}

	/**
	 * Tells if the current user can update a ExamenBiologique property
	 * @param property the property whose update privilege has to be checked
	 * @return true if the current user can update a ExamenBiologique property
	 */
	private boolean canUpdateExamenBiologiqueProperty(String property) {
		if ("patient".equals(property)) {
			return canEditExamenBiologiqueDescription;
		}
		if ("date".equals(property)) {
			return canEditExamenBiologiqueDescription;
		}
		if ("poids".equals(property)) {
			return canEditExamenBiologiqueDescription;
		}
		if ("observations".equals(property)) {
			return canEditExamenBiologiqueDescription;
		}
		return true;
	}
	/* Privileges check for entity ExamenMicroscopie */

	/**
	 * Tells if the current user can access a ExamenMicroscopie property
	 * @param property the property whose access has to be checked
	 * @return true if the current user can access a ExamenMicroscopie property
	 */
	private boolean canAccessExamenMicroscopieProperty(String property) {
		if ("CDT".equals(property)) {
			return canReadExamenMicroscopieCentreExamen;
		}
		if ("laboratoireReference".equals(property)) {
			return canReadExamenMicroscopieCentreExamen;
		}
		if ("casTb".equals(property)) {
			return canReadExamenMicroscopieExamen;
		}
		if ("date".equals(property)) {
			return canReadExamenMicroscopieExamen;
		}
		if ("raisonDepistage".equals(property)) {
			return canReadExamenMicroscopieExamen;
		}
		if ("resultat".equals(property)) {
			return canReadExamenMicroscopieExamen;
		}
		if ("observations".equals(property)) {
			return canReadExamenMicroscopieExamen;
		}
		return true;
	}

	/**
	 * Tells if the current user can update a ExamenMicroscopie property
	 * @param property the property whose update privilege has to be checked
	 * @return true if the current user can update a ExamenMicroscopie property
	 */
	private boolean canUpdateExamenMicroscopieProperty(String property) {
		if ("CDT".equals(property)) {
			return canEditExamenMicroscopieCentreExamen;
		}
		if ("laboratoireReference".equals(property)) {
			return canEditExamenMicroscopieCentreExamen;
		}
		if ("casTb".equals(property)) {
			return canEditExamenMicroscopieExamen;
		}
		if ("date".equals(property)) {
			return canEditExamenMicroscopieExamen;
		}
		if ("raisonDepistage".equals(property)) {
			return canEditExamenMicroscopieExamen;
		}
		if ("resultat".equals(property)) {
			return canEditExamenMicroscopieExamen;
		}
		if ("observations".equals(property)) {
			return canEditExamenMicroscopieExamen;
		}
		return true;
	}
	/* Privileges check for entity ExamenATB */

	/**
	 * Tells if the current user can access a ExamenATB property
	 * @param property the property whose access has to be checked
	 * @return true if the current user can access a ExamenATB property
	 */
	private boolean canAccessExamenATBProperty(String property) {
		if ("CDT".equals(property)) {
			return canReadExamenATBCentreExamen;
		}
		if ("laboratoireReference".equals(property)) {
			return canReadExamenATBCentreExamen;
		}
		if ("casTb".equals(property)) {
			return canReadExamenATBExamen;
		}
		if ("dateExamen".equals(property)) {
			return canReadExamenATBExamen;
		}
		if ("raisonDepistage".equals(property)) {
			return canReadExamenATBExamen;
		}
		if ("resultat".equals(property)) {
			return canReadExamenATBExamen;
		}
		if ("observations".equals(property)) {
			return canReadExamenATBExamen;
		}
		return true;
	}

	/**
	 * Tells if the current user can update a ExamenATB property
	 * @param property the property whose update privilege has to be checked
	 * @return true if the current user can update a ExamenATB property
	 */
	private boolean canUpdateExamenATBProperty(String property) {
		if ("CDT".equals(property)) {
			return canEditExamenATBCentreExamen;
		}
		if ("laboratoireReference".equals(property)) {
			return canEditExamenATBCentreExamen;
		}
		if ("casTb".equals(property)) {
			return canEditExamenATBExamen;
		}
		if ("dateExamen".equals(property)) {
			return canEditExamenATBExamen;
		}
		if ("raisonDepistage".equals(property)) {
			return canEditExamenATBExamen;
		}
		if ("resultat".equals(property)) {
			return canEditExamenATBExamen;
		}
		if ("observations".equals(property)) {
			return canEditExamenATBExamen;
		}
		return true;
	}
	/* Privileges check for entity PriseMedicamenteuse */

	/**
	 * Tells if the current user can access a PriseMedicamenteuse property
	 * @param property the property whose access has to be checked
	 * @return true if the current user can access a PriseMedicamenteuse property
	 */
	private boolean canAccessPriseMedicamenteuseProperty(String property) {
		if ("phaseIntensive".equals(property)) {
			return canReadPriseMedicamenteuseDescription;
		}
		if ("phaseContinuation".equals(property)) {
			return canReadPriseMedicamenteuseDescription;
		}
		if ("dateEffective".equals(property)) {
			return canReadPriseMedicamenteuseDescription;
		}
		if ("prise".equals(property)) {
			return canReadPriseMedicamenteuseDescription;
		}
		if ("cotrimoxazole".equals(property)) {
			return canReadPriseMedicamenteuseDescription;
		}
		return true;
	}

	/**
	 * Tells if the current user can update a PriseMedicamenteuse property
	 * @param property the property whose update privilege has to be checked
	 * @return true if the current user can update a PriseMedicamenteuse property
	 */
	private boolean canUpdatePriseMedicamenteuseProperty(String property) {
		if ("phaseIntensive".equals(property)) {
			return canEditPriseMedicamenteuseDescription;
		}
		if ("phaseContinuation".equals(property)) {
			return canEditPriseMedicamenteuseDescription;
		}
		if ("dateEffective".equals(property)) {
			return canEditPriseMedicamenteuseDescription;
		}
		if ("prise".equals(property)) {
			return canEditPriseMedicamenteuseDescription;
		}
		if ("cotrimoxazole".equals(property)) {
			return canEditPriseMedicamenteuseDescription;
		}
		return true;
	}
	/* Privileges check for entity RendezVous */

	/**
	 * Tells if the current user can access a RendezVous property
	 * @param property the property whose access has to be checked
	 * @return true if the current user can access a RendezVous property
	 */
	private boolean canAccessRendezVousProperty(String property) {
		if ("casTb".equals(property)) {
			return canReadRendezVousDescription;
		}
		if ("dateRendezVous".equals(property)) {
			return canReadRendezVousDescription;
		}
		if ("honore".equals(property)) {
			return canReadRendezVousDescription;
		}
		if ("nombreSMSEnvoye".equals(property)) {
			return canReadRendezVousDescription;
		}
		if ("commentaire".equals(property)) {
			return canReadRendezVousDescription;
		}
		return true;
	}

	/**
	 * Tells if the current user can update a RendezVous property
	 * @param property the property whose update privilege has to be checked
	 * @return true if the current user can update a RendezVous property
	 */
	private boolean canUpdateRendezVousProperty(String property) {
		if ("casTb".equals(property)) {
			return canEditRendezVousDescription;
		}
		if ("dateRendezVous".equals(property)) {
			return canEditRendezVousDescription;
		}
		if ("honore".equals(property)) {
			return canEditRendezVousDescription;
		}
		if ("nombreSMSEnvoye".equals(property)) {
			return canEditRendezVousDescription;
		}
		if ("commentaire".equals(property)) {
			return canEditRendezVousDescription;
		}
		return true;
	}
	/* Privileges check for entity TransfertReference */

	/**
	 * Tells if the current user can access a TransfertReference property
	 * @param property the property whose access has to be checked
	 * @return true if the current user can access a TransfertReference property
	 */
	private boolean canAccessTransfertReferenceProperty(String property) {
		if ("nature".equals(property)) {
			return canReadTransfertReferenceInformationsDepart;
		}
		if ("region".equals(property)) {
			return canReadTransfertReferenceInformationsDepart;
		}
		if ("districtSante".equals(property)) {
			return canReadTransfertReferenceInformationsDepart;
		}
		if ("CDTDepart".equals(property)) {
			return canReadTransfertReferenceInformationsDepart;
		}
		if ("patient".equals(property)) {
			return canReadTransfertReferenceInformationsDepart;
		}
		if ("dateDepart".equals(property)) {
			return canReadTransfertReferenceInformationsDepart;
		}
		if ("motifDepart".equals(property)) {
			return canReadTransfertReferenceInformationsDepart;
		}
		if ("regionArrivee".equals(property)) {
			return canReadTransfertReferenceInformationArrivee;
		}
		if ("districtSanteArrivee".equals(property)) {
			return canReadTransfertReferenceInformationArrivee;
		}
		if ("CDTArrivee".equals(property)) {
			return canReadTransfertReferenceInformationArrivee;
		}
		if ("dateArrivee".equals(property)) {
			return canReadTransfertReferenceInformationArrivee;
		}
		return true;
	}

	/**
	 * Tells if the current user can update a TransfertReference property
	 * @param property the property whose update privilege has to be checked
	 * @return true if the current user can update a TransfertReference property
	 */
	private boolean canUpdateTransfertReferenceProperty(String property) {
		if ("nature".equals(property)) {
			return canEditTransfertReferenceInformationsDepart;
		}
		if ("region".equals(property)) {
			return canEditTransfertReferenceInformationsDepart;
		}
		if ("districtSante".equals(property)) {
			return canEditTransfertReferenceInformationsDepart;
		}
		if ("CDTDepart".equals(property)) {
			return canEditTransfertReferenceInformationsDepart;
		}
		if ("patient".equals(property)) {
			return canEditTransfertReferenceInformationsDepart;
		}
		if ("dateDepart".equals(property)) {
			return canEditTransfertReferenceInformationsDepart;
		}
		if ("motifDepart".equals(property)) {
			return canEditTransfertReferenceInformationsDepart;
		}
		if ("regionArrivee".equals(property)) {
			return canEditTransfertReferenceInformationArrivee;
		}
		if ("districtSanteArrivee".equals(property)) {
			return canEditTransfertReferenceInformationArrivee;
		}
		if ("CDTArrivee".equals(property)) {
			return canEditTransfertReferenceInformationArrivee;
		}
		if ("dateArrivee".equals(property)) {
			return canEditTransfertReferenceInformationArrivee;
		}
		return true;
	}
	/* Privileges check for entity Lot */

	/**
	 * Tells if the current user can access a Lot property
	 * @param property the property whose access has to be checked
	 * @return true if the current user can access a Lot property
	 */
	private boolean canAccessLotProperty(String property) {
		if ("region".equals(property)) {
			return canReadLotDescription;
		}
		if ("districtSante".equals(property)) {
			return canReadLotDescription;
		}
		if ("CDT".equals(property)) {
			return canReadLotDescription;
		}
		if ("numero".equals(property)) {
			return canReadLotDescription;
		}
		if ("type".equals(property)) {
			return canReadLotDescription;
		}
		if ("medicament".equals(property)) {
			return canReadLotDescription;
		}
		if ("intrant".equals(property)) {
			return canReadLotDescription;
		}
		if ("quantiteInitiale".equals(property)) {
			return canReadLotDescription;
		}
		if ("quantite".equals(property)) {
			return canReadLotDescription;
		}
		if ("datePeremption".equals(property)) {
			return canReadLotDescription;
		}
		return true;
	}

	/**
	 * Tells if the current user can update a Lot property
	 * @param property the property whose update privilege has to be checked
	 * @return true if the current user can update a Lot property
	 */
	private boolean canUpdateLotProperty(String property) {
		if ("region".equals(property)) {
			return canEditLotDescription;
		}
		if ("districtSante".equals(property)) {
			return canEditLotDescription;
		}
		if ("CDT".equals(property)) {
			return canEditLotDescription;
		}
		if ("numero".equals(property)) {
			return canEditLotDescription;
		}
		if ("type".equals(property)) {
			return canEditLotDescription;
		}
		if ("medicament".equals(property)) {
			return canEditLotDescription;
		}
		if ("intrant".equals(property)) {
			return canEditLotDescription;
		}
		if ("quantiteInitiale".equals(property)) {
			return canEditLotDescription;
		}
		if ("quantite".equals(property)) {
			return canEditLotDescription;
		}
		if ("datePeremption".equals(property)) {
			return canEditLotDescription;
		}
		return true;
	}
	/* Privileges check for entity HorsUsage */

	/**
	 * Tells if the current user can access a HorsUsage property
	 * @param property the property whose access has to be checked
	 * @return true if the current user can access a HorsUsage property
	 */
	private boolean canAccessHorsUsageProperty(String property) {
		if ("lot".equals(property)) {
			return canReadHorsUsageDescription;
		}
		if ("type".equals(property)) {
			return canReadHorsUsageDescription;
		}
		if ("motif".equals(property)) {
			return canReadHorsUsageDescription;
		}
		return true;
	}

	/**
	 * Tells if the current user can update a HorsUsage property
	 * @param property the property whose update privilege has to be checked
	 * @return true if the current user can update a HorsUsage property
	 */
	private boolean canUpdateHorsUsageProperty(String property) {
		if ("lot".equals(property)) {
			return canEditHorsUsageDescription;
		}
		if ("type".equals(property)) {
			return canEditHorsUsageDescription;
		}
		if ("motif".equals(property)) {
			return canEditHorsUsageDescription;
		}
		return true;
	}
	/* Privileges check for entity EntreeLot */

	/**
	 * Tells if the current user can access a EntreeLot property
	 * @param property the property whose access has to be checked
	 * @return true if the current user can access a EntreeLot property
	 */
	private boolean canAccessEntreeLotProperty(String property) {
		if ("CDT".equals(property)) {
			return canReadEntreeLotDescription;
		}
		if ("lot".equals(property)) {
			return canReadEntreeLotDescription;
		}
		if ("quantite".equals(property)) {
			return canReadEntreeLotDescription;
		}
		return true;
	}

	/**
	 * Tells if the current user can update a EntreeLot property
	 * @param property the property whose update privilege has to be checked
	 * @return true if the current user can update a EntreeLot property
	 */
	private boolean canUpdateEntreeLotProperty(String property) {
		if ("CDT".equals(property)) {
			return canEditEntreeLotDescription;
		}
		if ("lot".equals(property)) {
			return canEditEntreeLotDescription;
		}
		if ("quantite".equals(property)) {
			return canEditEntreeLotDescription;
		}
		return true;
	}
	/* Privileges check for entity SortieLot */

	/**
	 * Tells if the current user can access a SortieLot property
	 * @param property the property whose access has to be checked
	 * @return true if the current user can access a SortieLot property
	 */
	private boolean canAccessSortieLotProperty(String property) {
		if ("CDT".equals(property)) {
			return canReadSortieLotDescription;
		}
		if ("lot".equals(property)) {
			return canReadSortieLotDescription;
		}
		if ("quantite".equals(property)) {
			return canReadSortieLotDescription;
		}
		return true;
	}

	/**
	 * Tells if the current user can update a SortieLot property
	 * @param property the property whose update privilege has to be checked
	 * @return true if the current user can update a SortieLot property
	 */
	private boolean canUpdateSortieLotProperty(String property) {
		if ("CDT".equals(property)) {
			return canEditSortieLotDescription;
		}
		if ("lot".equals(property)) {
			return canEditSortieLotDescription;
		}
		if ("quantite".equals(property)) {
			return canEditSortieLotDescription;
		}
		return true;
	}
	/* Privileges check for entity Commande */

	/**
	 * Tells if the current user can access a Commande property
	 * @param property the property whose access has to be checked
	 * @return true if the current user can access a Commande property
	 */
	private boolean canAccessCommandeProperty(String property) {
		if ("date".equals(property)) {
			return canReadCommandeInformationsDepart;
		}
		if ("region".equals(property)) {
			return canReadCommandeInformationsDepart;
		}
		if ("districtSante".equals(property)) {
			return canReadCommandeInformationsDepart;
		}
		if ("CDT".equals(property)) {
			return canReadCommandeInformationsDepart;
		}
		if ("medicaments".equals(property)) {
			return canReadCommandeInformationsDepart;
		}
		if ("intrants".equals(property)) {
			return canReadCommandeInformationsDepart;
		}
		if ("approuveeRegion".equals(property)) {
			return canReadCommandeRegionApprobation;
		}
		if ("motifRejetRegion".equals(property)) {
			return canReadCommandeRegionApprobation;
		}
		if ("approuveeGTC".equals(property)) {
			return canReadCommandeGtcApprobation;
		}
		if ("motifRejetGTC".equals(property)) {
			return canReadCommandeGtcApprobation;
		}
		return true;
	}

	/**
	 * Tells if the current user can update a Commande property
	 * @param property the property whose update privilege has to be checked
	 * @return true if the current user can update a Commande property
	 */
	private boolean canUpdateCommandeProperty(String property) {
		if ("date".equals(property)) {
			return canEditCommandeInformationsDepart;
		}
		if ("region".equals(property)) {
			return canEditCommandeInformationsDepart;
		}
		if ("districtSante".equals(property)) {
			return canEditCommandeInformationsDepart;
		}
		if ("CDT".equals(property)) {
			return canEditCommandeInformationsDepart;
		}
		if ("medicaments".equals(property)) {
			return canEditCommandeInformationsDepart;
		}
		if ("intrants".equals(property)) {
			return canEditCommandeInformationsDepart;
		}
		if ("approuveeRegion".equals(property)) {
			return canEditCommandeRegionApprobation;
		}
		if ("motifRejetRegion".equals(property)) {
			return canEditCommandeRegionApprobation;
		}
		if ("approuveeGTC".equals(property)) {
			return canEditCommandeGtcApprobation;
		}
		if ("motifRejetGTC".equals(property)) {
			return canEditCommandeGtcApprobation;
		}
		return true;
	}
	/* Privileges check for entity DetailCommandeMedicament */

	/**
	 * Tells if the current user can access a DetailCommandeMedicament property
	 * @param property the property whose access has to be checked
	 * @return true if the current user can access a DetailCommandeMedicament property
	 */
	private boolean canAccessDetailCommandeMedicamentProperty(String property) {
		if ("commande".equals(property)) {
			return canReadDetailCommandeMedicamentDescription;
		}
		if ("medicament".equals(property)) {
			return canReadDetailCommandeMedicamentDescription;
		}
		if ("quantiteRequise".equals(property)) {
			return canReadDetailCommandeMedicamentDescription;
		}
		if ("quantiteEnStock".equals(property)) {
			return canReadDetailCommandeMedicamentDescription;
		}
		return true;
	}

	/**
	 * Tells if the current user can update a DetailCommandeMedicament property
	 * @param property the property whose update privilege has to be checked
	 * @return true if the current user can update a DetailCommandeMedicament property
	 */
	private boolean canUpdateDetailCommandeMedicamentProperty(String property) {
		if ("commande".equals(property)) {
			return canEditDetailCommandeMedicamentDescription;
		}
		if ("medicament".equals(property)) {
			return canEditDetailCommandeMedicamentDescription;
		}
		if ("quantiteRequise".equals(property)) {
			return canEditDetailCommandeMedicamentDescription;
		}
		if ("quantiteEnStock".equals(property)) {
			return canEditDetailCommandeMedicamentDescription;
		}
		return true;
	}
	/* Privileges check for entity DetailCommandeIntrant */

	/**
	 * Tells if the current user can access a DetailCommandeIntrant property
	 * @param property the property whose access has to be checked
	 * @return true if the current user can access a DetailCommandeIntrant property
	 */
	private boolean canAccessDetailCommandeIntrantProperty(String property) {
		if ("commande".equals(property)) {
			return canReadDetailCommandeIntrantDescription;
		}
		if ("intrant".equals(property)) {
			return canReadDetailCommandeIntrantDescription;
		}
		if ("quantiteRequise".equals(property)) {
			return canReadDetailCommandeIntrantDescription;
		}
		if ("quantiteEnStock".equals(property)) {
			return canReadDetailCommandeIntrantDescription;
		}
		return true;
	}

	/**
	 * Tells if the current user can update a DetailCommandeIntrant property
	 * @param property the property whose update privilege has to be checked
	 * @return true if the current user can update a DetailCommandeIntrant property
	 */
	private boolean canUpdateDetailCommandeIntrantProperty(String property) {
		if ("commande".equals(property)) {
			return canEditDetailCommandeIntrantDescription;
		}
		if ("intrant".equals(property)) {
			return canEditDetailCommandeIntrantDescription;
		}
		if ("quantiteRequise".equals(property)) {
			return canEditDetailCommandeIntrantDescription;
		}
		if ("quantiteEnStock".equals(property)) {
			return canEditDetailCommandeIntrantDescription;
		}
		return true;
	}
	/* Privileges check for entity Reception */

	/**
	 * Tells if the current user can access a Reception property
	 * @param property the property whose access has to be checked
	 * @return true if the current user can access a Reception property
	 */
	private boolean canAccessReceptionProperty(String property) {
		if ("region".equals(property)) {
			return canReadReceptionDescription;
		}
		if ("districtSante".equals(property)) {
			return canReadReceptionDescription;
		}
		if ("CDT".equals(property)) {
			return canReadReceptionDescription;
		}
		if ("commande".equals(property)) {
			return canReadReceptionDescription;
		}
		if ("dateReception".equals(property)) {
			return canReadReceptionDescription;
		}
		if ("medicaments".equals(property)) {
			return canReadReceptionDescription;
		}
		if ("intrants".equals(property)) {
			return canReadReceptionDescription;
		}
		return true;
	}

	/**
	 * Tells if the current user can update a Reception property
	 * @param property the property whose update privilege has to be checked
	 * @return true if the current user can update a Reception property
	 */
	private boolean canUpdateReceptionProperty(String property) {
		if ("region".equals(property)) {
			return canEditReceptionDescription;
		}
		if ("districtSante".equals(property)) {
			return canEditReceptionDescription;
		}
		if ("CDT".equals(property)) {
			return canEditReceptionDescription;
		}
		if ("commande".equals(property)) {
			return canEditReceptionDescription;
		}
		if ("dateReception".equals(property)) {
			return canEditReceptionDescription;
		}
		if ("medicaments".equals(property)) {
			return canEditReceptionDescription;
		}
		if ("intrants".equals(property)) {
			return canEditReceptionDescription;
		}
		return true;
	}
	/* Privileges check for entity DetailReceptionMedicament */

	/**
	 * Tells if the current user can access a DetailReceptionMedicament property
	 * @param property the property whose access has to be checked
	 * @return true if the current user can access a DetailReceptionMedicament property
	 */
	private boolean canAccessDetailReceptionMedicamentProperty(String property) {
		if ("reception".equals(property)) {
			return canReadDetailReceptionMedicamentDescription;
		}
		if ("commande".equals(property)) {
			return canReadDetailReceptionMedicamentDescription;
		}
		if ("detailCommande".equals(property)) {
			return canReadDetailReceptionMedicamentDescription;
		}
		if ("entreeLot".equals(property)) {
			return canReadDetailReceptionMedicamentDescription;
		}
		return true;
	}

	/**
	 * Tells if the current user can update a DetailReceptionMedicament property
	 * @param property the property whose update privilege has to be checked
	 * @return true if the current user can update a DetailReceptionMedicament property
	 */
	private boolean canUpdateDetailReceptionMedicamentProperty(String property) {
		if ("reception".equals(property)) {
			return canEditDetailReceptionMedicamentDescription;
		}
		if ("commande".equals(property)) {
			return canEditDetailReceptionMedicamentDescription;
		}
		if ("detailCommande".equals(property)) {
			return canEditDetailReceptionMedicamentDescription;
		}
		if ("entreeLot".equals(property)) {
			return canEditDetailReceptionMedicamentDescription;
		}
		return true;
	}
	/* Privileges check for entity DetailReceptionIntrant */

	/**
	 * Tells if the current user can access a DetailReceptionIntrant property
	 * @param property the property whose access has to be checked
	 * @return true if the current user can access a DetailReceptionIntrant property
	 */
	private boolean canAccessDetailReceptionIntrantProperty(String property) {
		if ("reception".equals(property)) {
			return canReadDetailReceptionIntrantDescription;
		}
		if ("commande".equals(property)) {
			return canReadDetailReceptionIntrantDescription;
		}
		if ("detailCommande".equals(property)) {
			return canReadDetailReceptionIntrantDescription;
		}
		if ("entreeLot".equals(property)) {
			return canReadDetailReceptionIntrantDescription;
		}
		return true;
	}

	/**
	 * Tells if the current user can update a DetailReceptionIntrant property
	 * @param property the property whose update privilege has to be checked
	 * @return true if the current user can update a DetailReceptionIntrant property
	 */
	private boolean canUpdateDetailReceptionIntrantProperty(String property) {
		if ("reception".equals(property)) {
			return canEditDetailReceptionIntrantDescription;
		}
		if ("commande".equals(property)) {
			return canEditDetailReceptionIntrantDescription;
		}
		if ("detailCommande".equals(property)) {
			return canEditDetailReceptionIntrantDescription;
		}
		if ("entreeLot".equals(property)) {
			return canEditDetailReceptionIntrantDescription;
		}
		return true;
	}
	/* Privileges check for entity Ravitaillement */

	/**
	 * Tells if the current user can access a Ravitaillement property
	 * @param property the property whose access has to be checked
	 * @return true if the current user can access a Ravitaillement property
	 */
	private boolean canAccessRavitaillementProperty(String property) {
		if ("region".equals(property)) {
			return canReadRavitaillementInformationsDepart;
		}
		if ("districtSante".equals(property)) {
			return canReadRavitaillementInformationsDepart;
		}
		if ("CDTDepart".equals(property)) {
			return canReadRavitaillementInformationsDepart;
		}
		if ("dateDepart".equals(property)) {
			return canReadRavitaillementInformationsDepart;
		}
		if ("regionArrivee".equals(property)) {
			return canReadRavitaillementInformationArrivee;
		}
		if ("districtSanteArrivee".equals(property)) {
			return canReadRavitaillementInformationArrivee;
		}
		if ("CDTArrivee".equals(property)) {
			return canReadRavitaillementInformationArrivee;
		}
		if ("dateArrivee".equals(property)) {
			return canReadRavitaillementInformationArrivee;
		}
		if ("details".equals(property)) {
			return canReadRavitaillementDetail;
		}
		return true;
	}

	/**
	 * Tells if the current user can update a Ravitaillement property
	 * @param property the property whose update privilege has to be checked
	 * @return true if the current user can update a Ravitaillement property
	 */
	private boolean canUpdateRavitaillementProperty(String property) {
		if ("region".equals(property)) {
			return canEditRavitaillementInformationsDepart;
		}
		if ("districtSante".equals(property)) {
			return canEditRavitaillementInformationsDepart;
		}
		if ("CDTDepart".equals(property)) {
			return canEditRavitaillementInformationsDepart;
		}
		if ("dateDepart".equals(property)) {
			return canEditRavitaillementInformationsDepart;
		}
		if ("regionArrivee".equals(property)) {
			return canEditRavitaillementInformationArrivee;
		}
		if ("districtSanteArrivee".equals(property)) {
			return canEditRavitaillementInformationArrivee;
		}
		if ("CDTArrivee".equals(property)) {
			return canEditRavitaillementInformationArrivee;
		}
		if ("dateArrivee".equals(property)) {
			return canEditRavitaillementInformationArrivee;
		}
		if ("details".equals(property)) {
			return canEditRavitaillementDetail;
		}
		return true;
	}
	/* Privileges check for entity DetailRavitaillement */

	/**
	 * Tells if the current user can access a DetailRavitaillement property
	 * @param property the property whose access has to be checked
	 * @return true if the current user can access a DetailRavitaillement property
	 */
	private boolean canAccessDetailRavitaillementProperty(String property) {
		if ("ravitaillement".equals(property)) {
			return canReadDetailRavitaillementDescription;
		}
		if ("sortieLot".equals(property)) {
			return canReadDetailRavitaillementSortie;
		}
		if ("entreeLot".equals(property)) {
			return canReadDetailRavitaillementEntree;
		}
		return true;
	}

	/**
	 * Tells if the current user can update a DetailRavitaillement property
	 * @param property the property whose update privilege has to be checked
	 * @return true if the current user can update a DetailRavitaillement property
	 */
	private boolean canUpdateDetailRavitaillementProperty(String property) {
		if ("ravitaillement".equals(property)) {
			return canEditDetailRavitaillementDescription;
		}
		if ("sortieLot".equals(property)) {
			return canEditDetailRavitaillementSortie;
		}
		if ("entreeLot".equals(property)) {
			return canEditDetailRavitaillementEntree;
		}
		return true;
	}
	/* Privileges check for entity Inventaire */

	/**
	 * Tells if the current user can access a Inventaire property
	 * @param property the property whose access has to be checked
	 * @return true if the current user can access a Inventaire property
	 */
	private boolean canAccessInventaireProperty(String property) {
		if ("date".equals(property)) {
			return canReadInventaireInformationsDepart;
		}
		if ("region".equals(property)) {
			return canReadInventaireInformationsDepart;
		}
		if ("districtSante".equals(property)) {
			return canReadInventaireInformationsDepart;
		}
		if ("CDT".equals(property)) {
			return canReadInventaireInformationsDepart;
		}
		if ("details".equals(property)) {
			return canReadInventaireInformationsDepart;
		}
		return true;
	}

	/**
	 * Tells if the current user can update a Inventaire property
	 * @param property the property whose update privilege has to be checked
	 * @return true if the current user can update a Inventaire property
	 */
	private boolean canUpdateInventaireProperty(String property) {
		if ("date".equals(property)) {
			return canEditInventaireInformationsDepart;
		}
		if ("region".equals(property)) {
			return canEditInventaireInformationsDepart;
		}
		if ("districtSante".equals(property)) {
			return canEditInventaireInformationsDepart;
		}
		if ("CDT".equals(property)) {
			return canEditInventaireInformationsDepart;
		}
		if ("details".equals(property)) {
			return canEditInventaireInformationsDepart;
		}
		return true;
	}
	/* Privileges check for entity DetailInventaire */

	/**
	 * Tells if the current user can access a DetailInventaire property
	 * @param property the property whose access has to be checked
	 * @return true if the current user can access a DetailInventaire property
	 */
	private boolean canAccessDetailInventaireProperty(String property) {
		if ("inventaire".equals(property)) {
			return canReadDetailInventaireDescription;
		}
		if ("CDT".equals(property)) {
			return canReadDetailInventaireDescription;
		}
		if ("lot".equals(property)) {
			return canReadDetailInventaireDescription;
		}
		if ("quantiteReelle".equals(property)) {
			return canReadDetailInventaireDescription;
		}
		if ("quantiteTheorique".equals(property)) {
			return canReadDetailInventaireDescription;
		}
		return true;
	}

	/**
	 * Tells if the current user can update a DetailInventaire property
	 * @param property the property whose update privilege has to be checked
	 * @return true if the current user can update a DetailInventaire property
	 */
	private boolean canUpdateDetailInventaireProperty(String property) {
		if ("inventaire".equals(property)) {
			return canEditDetailInventaireDescription;
		}
		if ("CDT".equals(property)) {
			return canEditDetailInventaireDescription;
		}
		if ("lot".equals(property)) {
			return canEditDetailInventaireDescription;
		}
		if ("quantiteReelle".equals(property)) {
			return canEditDetailInventaireDescription;
		}
		if ("quantiteTheorique".equals(property)) {
			return canEditDetailInventaireDescription;
		}
		return true;
	}
	/* Privileges check for entity Personnel */

	/**
	 * Tells if the current user can access a Personnel property
	 * @param property the property whose access has to be checked
	 * @return true if the current user can access a Personnel property
	 */
	private boolean canAccessPersonnelProperty(String property) {
		if ("nom".equals(property)) {
			return canReadPersonnelIdentification;
		}
		if ("dateNaissance".equals(property)) {
			return canReadPersonnelIdentification;
		}
		if ("profession".equals(property)) {
			return canReadPersonnelIdentification;
		}
		if ("telephoneUn".equals(property)) {
			return canReadPersonnelContact;
		}
		if ("telephoneDeux".equals(property)) {
			return canReadPersonnelContact;
		}
		if ("telephoneTrois".equals(property)) {
			return canReadPersonnelContact;
		}
		if ("email".equals(property)) {
			return canReadPersonnelContact;
		}
		if ("libelle".equals(property)) {
			return canReadPersonnelContact;
		}
		if ("complementAdresse".equals(property)) {
			return canReadPersonnelContact;
		}
		if ("quartier".equals(property)) {
			return canReadPersonnelContact;
		}
		if ("ville".equals(property)) {
			return canReadPersonnelContact;
		}
		if ("codePostal".equals(property)) {
			return canReadPersonnelContact;
		}
		if ("dateDepart".equals(property)) {
			return canReadPersonnelSituation;
		}
		if ("dateArrivee".equals(property)) {
			return canReadPersonnelSituation;
		}
		if ("AEteForme".equals(property)) {
			return canReadPersonnelSituation;
		}
		if ("qualification".equals(property)) {
			return canReadPersonnelSituation;
		}
		if ("niveau".equals(property)) {
			return canReadPersonnelNiveauAccess;
		}
		if ("region".equals(property)) {
			return canReadPersonnelNiveauAccess;
		}
		if ("districtSante".equals(property)) {
			return canReadPersonnelNiveauAccess;
		}
		if ("CDT".equals(property)) {
			return canReadPersonnelNiveauAccess;
		}
		if ("actif".equals(property)) {
			return canReadPersonnelNiveauAccess;
		}
		return true;
	}

	/**
	 * Tells if the current user can update a Personnel property
	 * @param property the property whose update privilege has to be checked
	 * @return true if the current user can update a Personnel property
	 */
	private boolean canUpdatePersonnelProperty(String property) {
		if ("nom".equals(property)) {
			return canEditPersonnelIdentification;
		}
		if ("dateNaissance".equals(property)) {
			return canEditPersonnelIdentification;
		}
		if ("profession".equals(property)) {
			return canEditPersonnelIdentification;
		}
		if ("telephoneUn".equals(property)) {
			return canEditPersonnelContact;
		}
		if ("telephoneDeux".equals(property)) {
			return canEditPersonnelContact;
		}
		if ("telephoneTrois".equals(property)) {
			return canEditPersonnelContact;
		}
		if ("email".equals(property)) {
			return canEditPersonnelContact;
		}
		if ("libelle".equals(property)) {
			return canEditPersonnelContact;
		}
		if ("complementAdresse".equals(property)) {
			return canEditPersonnelContact;
		}
		if ("quartier".equals(property)) {
			return canEditPersonnelContact;
		}
		if ("ville".equals(property)) {
			return canEditPersonnelContact;
		}
		if ("codePostal".equals(property)) {
			return canEditPersonnelContact;
		}
		if ("dateDepart".equals(property)) {
			return canEditPersonnelSituation;
		}
		if ("dateArrivee".equals(property)) {
			return canEditPersonnelSituation;
		}
		if ("AEteForme".equals(property)) {
			return canEditPersonnelSituation;
		}
		if ("qualification".equals(property)) {
			return canEditPersonnelSituation;
		}
		if ("niveau".equals(property)) {
			return canEditPersonnelNiveauAccess;
		}
		if ("region".equals(property)) {
			return canEditPersonnelNiveauAccess;
		}
		if ("districtSante".equals(property)) {
			return canEditPersonnelNiveauAccess;
		}
		if ("CDT".equals(property)) {
			return canEditPersonnelNiveauAccess;
		}
		if ("actif".equals(property)) {
			return canEditPersonnelNiveauAccess;
		}
		return true;
	}
	/* Privileges check for entity DepartPersonnel */

	/**
	 * Tells if the current user can access a DepartPersonnel property
	 * @param property the property whose access has to be checked
	 * @return true if the current user can access a DepartPersonnel property
	 */
	private boolean canAccessDepartPersonnelProperty(String property) {
		if ("region".equals(property)) {
			return canReadDepartPersonnelPersonnel;
		}
		if ("districtSante".equals(property)) {
			return canReadDepartPersonnelPersonnel;
		}
		if ("CDT".equals(property)) {
			return canReadDepartPersonnelPersonnel;
		}
		if ("personnel".equals(property)) {
			return canReadDepartPersonnelPersonnel;
		}
		if ("dateDepart".equals(property)) {
			return canReadDepartPersonnelDescription;
		}
		if ("motifDepart".equals(property)) {
			return canReadDepartPersonnelDescription;
		}
		return true;
	}

	/**
	 * Tells if the current user can update a DepartPersonnel property
	 * @param property the property whose update privilege has to be checked
	 * @return true if the current user can update a DepartPersonnel property
	 */
	private boolean canUpdateDepartPersonnelProperty(String property) {
		if ("region".equals(property)) {
			return canEditDepartPersonnelPersonnel;
		}
		if ("districtSante".equals(property)) {
			return canEditDepartPersonnelPersonnel;
		}
		if ("CDT".equals(property)) {
			return canEditDepartPersonnelPersonnel;
		}
		if ("personnel".equals(property)) {
			return canEditDepartPersonnelPersonnel;
		}
		if ("dateDepart".equals(property)) {
			return canEditDepartPersonnelDescription;
		}
		if ("motifDepart".equals(property)) {
			return canEditDepartPersonnelDescription;
		}
		return true;
	}
	/* Privileges check for entity ArriveePersonnel */

	/**
	 * Tells if the current user can access a ArriveePersonnel property
	 * @param property the property whose access has to be checked
	 * @return true if the current user can access a ArriveePersonnel property
	 */
	private boolean canAccessArriveePersonnelProperty(String property) {
		if ("region".equals(property)) {
			return canReadArriveePersonnelDescription;
		}
		if ("districtSante".equals(property)) {
			return canReadArriveePersonnelDescription;
		}
		if ("CDT".equals(property)) {
			return canReadArriveePersonnelDescription;
		}
		if ("personnel".equals(property)) {
			return canReadArriveePersonnelDescription;
		}
		if ("dateArrivee".equals(property)) {
			return canReadArriveePersonnelDescription;
		}
		return true;
	}

	/**
	 * Tells if the current user can update a ArriveePersonnel property
	 * @param property the property whose update privilege has to be checked
	 * @return true if the current user can update a ArriveePersonnel property
	 */
	private boolean canUpdateArriveePersonnelProperty(String property) {
		if ("region".equals(property)) {
			return canEditArriveePersonnelDescription;
		}
		if ("districtSante".equals(property)) {
			return canEditArriveePersonnelDescription;
		}
		if ("CDT".equals(property)) {
			return canEditArriveePersonnelDescription;
		}
		if ("personnel".equals(property)) {
			return canEditArriveePersonnelDescription;
		}
		if ("dateArrivee".equals(property)) {
			return canEditArriveePersonnelDescription;
		}
		return true;
	}
	/* Privileges check for entity Region */

	/**
	 * Tells if the current user can access a Region property
	 * @param property the property whose access has to be checked
	 * @return true if the current user can access a Region property
	 */
	private boolean canAccessRegionProperty(String property) {
		if ("code".equals(property)) {
			return canReadRegionDescription;
		}
		if ("nom".equals(property)) {
			return canReadRegionDescription;
		}
		if ("description".equals(property)) {
			return canReadRegionDescription;
		}
		if ("districtSantes".equals(property)) {
			return canReadRegionDescription;
		}
		if ("libelle".equals(property)) {
			return canReadRegionAdresse;
		}
		if ("complementAdresse".equals(property)) {
			return canReadRegionAdresse;
		}
		if ("quartier".equals(property)) {
			return canReadRegionAdresse;
		}
		if ("ville".equals(property)) {
			return canReadRegionAdresse;
		}
		if ("codePostal".equals(property)) {
			return canReadRegionAdresse;
		}
		if ("coordonnees".equals(property)) {
			return canReadRegionAdresse;
		}
		return true;
	}

	/**
	 * Tells if the current user can update a Region property
	 * @param property the property whose update privilege has to be checked
	 * @return true if the current user can update a Region property
	 */
	private boolean canUpdateRegionProperty(String property) {
		if ("code".equals(property)) {
			return canEditRegionDescription;
		}
		if ("nom".equals(property)) {
			return canEditRegionDescription;
		}
		if ("description".equals(property)) {
			return canEditRegionDescription;
		}
		if ("districtSantes".equals(property)) {
			return canEditRegionDescription;
		}
		if ("libelle".equals(property)) {
			return canEditRegionAdresse;
		}
		if ("complementAdresse".equals(property)) {
			return canEditRegionAdresse;
		}
		if ("quartier".equals(property)) {
			return canEditRegionAdresse;
		}
		if ("ville".equals(property)) {
			return canEditRegionAdresse;
		}
		if ("codePostal".equals(property)) {
			return canEditRegionAdresse;
		}
		if ("coordonnees".equals(property)) {
			return canEditRegionAdresse;
		}
		return true;
	}
	/* Privileges check for entity DistrictSante */

	/**
	 * Tells if the current user can access a DistrictSante property
	 * @param property the property whose access has to be checked
	 * @return true if the current user can access a DistrictSante property
	 */
	private boolean canAccessDistrictSanteProperty(String property) {
		if ("code".equals(property)) {
			return canReadDistrictSanteDescription;
		}
		if ("nom".equals(property)) {
			return canReadDistrictSanteDescription;
		}
		if ("description".equals(property)) {
			return canReadDistrictSanteDescription;
		}
		if ("region".equals(property)) {
			return canReadDistrictSanteDescription;
		}
		if ("libelle".equals(property)) {
			return canReadDistrictSanteAdresse;
		}
		if ("complementAdresse".equals(property)) {
			return canReadDistrictSanteAdresse;
		}
		if ("quartier".equals(property)) {
			return canReadDistrictSanteAdresse;
		}
		if ("ville".equals(property)) {
			return canReadDistrictSanteAdresse;
		}
		if ("codePostal".equals(property)) {
			return canReadDistrictSanteAdresse;
		}
		if ("coordonnees".equals(property)) {
			return canReadDistrictSanteAdresse;
		}
		return true;
	}

	/**
	 * Tells if the current user can update a DistrictSante property
	 * @param property the property whose update privilege has to be checked
	 * @return true if the current user can update a DistrictSante property
	 */
	private boolean canUpdateDistrictSanteProperty(String property) {
		if ("code".equals(property)) {
			return canEditDistrictSanteDescription;
		}
		if ("nom".equals(property)) {
			return canEditDistrictSanteDescription;
		}
		if ("description".equals(property)) {
			return canEditDistrictSanteDescription;
		}
		if ("region".equals(property)) {
			return canEditDistrictSanteDescription;
		}
		if ("libelle".equals(property)) {
			return canEditDistrictSanteAdresse;
		}
		if ("complementAdresse".equals(property)) {
			return canEditDistrictSanteAdresse;
		}
		if ("quartier".equals(property)) {
			return canEditDistrictSanteAdresse;
		}
		if ("ville".equals(property)) {
			return canEditDistrictSanteAdresse;
		}
		if ("codePostal".equals(property)) {
			return canEditDistrictSanteAdresse;
		}
		if ("coordonnees".equals(property)) {
			return canEditDistrictSanteAdresse;
		}
		return true;
	}
	/* Privileges check for entity CentreDiagTrait */

	/**
	 * Tells if the current user can access a CentreDiagTrait property
	 * @param property the property whose access has to be checked
	 * @return true if the current user can access a CentreDiagTrait property
	 */
	private boolean canAccessCentreDiagTraitProperty(String property) {
		if ("code".equals(property)) {
			return canReadCentreDiagTraitDescription;
		}
		if ("region".equals(property)) {
			return canReadCentreDiagTraitDescription;
		}
		if ("districtSante".equals(property)) {
			return canReadCentreDiagTraitDescription;
		}
		if ("nom".equals(property)) {
			return canReadCentreDiagTraitDescription;
		}
		if ("description".equals(property)) {
			return canReadCentreDiagTraitDescription;
		}
		if ("libelle".equals(property)) {
			return canReadCentreDiagTraitAdresse;
		}
		if ("complementAdresse".equals(property)) {
			return canReadCentreDiagTraitAdresse;
		}
		if ("quartier".equals(property)) {
			return canReadCentreDiagTraitAdresse;
		}
		if ("ville".equals(property)) {
			return canReadCentreDiagTraitAdresse;
		}
		if ("codePostal".equals(property)) {
			return canReadCentreDiagTraitAdresse;
		}
		if ("coordonnees".equals(property)) {
			return canReadCentreDiagTraitAdresse;
		}
		if ("lieuxDits".equals(property)) {
			return canReadCentreDiagTraitAdresse;
		}
		return true;
	}

	/**
	 * Tells if the current user can update a CentreDiagTrait property
	 * @param property the property whose update privilege has to be checked
	 * @return true if the current user can update a CentreDiagTrait property
	 */
	private boolean canUpdateCentreDiagTraitProperty(String property) {
		if ("code".equals(property)) {
			return canEditCentreDiagTraitDescription;
		}
		if ("region".equals(property)) {
			return canEditCentreDiagTraitDescription;
		}
		if ("districtSante".equals(property)) {
			return canEditCentreDiagTraitDescription;
		}
		if ("nom".equals(property)) {
			return canEditCentreDiagTraitDescription;
		}
		if ("description".equals(property)) {
			return canEditCentreDiagTraitDescription;
		}
		if ("libelle".equals(property)) {
			return canEditCentreDiagTraitAdresse;
		}
		if ("complementAdresse".equals(property)) {
			return canEditCentreDiagTraitAdresse;
		}
		if ("quartier".equals(property)) {
			return canEditCentreDiagTraitAdresse;
		}
		if ("ville".equals(property)) {
			return canEditCentreDiagTraitAdresse;
		}
		if ("codePostal".equals(property)) {
			return canEditCentreDiagTraitAdresse;
		}
		if ("coordonnees".equals(property)) {
			return canEditCentreDiagTraitAdresse;
		}
		if ("lieuxDits".equals(property)) {
			return canEditCentreDiagTraitAdresse;
		}
		return true;
	}
	/* Privileges check for entity LaboratoireReference */

	/**
	 * Tells if the current user can access a LaboratoireReference property
	 * @param property the property whose access has to be checked
	 * @return true if the current user can access a LaboratoireReference property
	 */
	private boolean canAccessLaboratoireReferenceProperty(String property) {
		if ("nom".equals(property)) {
			return canReadLaboratoireReferenceDescription;
		}
		if ("nature".equals(property)) {
			return canReadLaboratoireReferenceDescription;
		}
		if ("description".equals(property)) {
			return canReadLaboratoireReferenceDescription;
		}
		if ("region".equals(property)) {
			return canReadLaboratoireReferenceDescription;
		}
		if ("districtSante".equals(property)) {
			return canReadLaboratoireReferenceDescription;
		}
		if ("libelle".equals(property)) {
			return canReadLaboratoireReferenceAdresse;
		}
		if ("complementAdresse".equals(property)) {
			return canReadLaboratoireReferenceAdresse;
		}
		if ("quartier".equals(property)) {
			return canReadLaboratoireReferenceAdresse;
		}
		if ("ville".equals(property)) {
			return canReadLaboratoireReferenceAdresse;
		}
		if ("codePostal".equals(property)) {
			return canReadLaboratoireReferenceAdresse;
		}
		if ("coordonnees".equals(property)) {
			return canReadLaboratoireReferenceAdresse;
		}
		if ("lieuxDits".equals(property)) {
			return canReadLaboratoireReferenceAdresse;
		}
		return true;
	}

	/**
	 * Tells if the current user can update a LaboratoireReference property
	 * @param property the property whose update privilege has to be checked
	 * @return true if the current user can update a LaboratoireReference property
	 */
	private boolean canUpdateLaboratoireReferenceProperty(String property) {
		if ("nom".equals(property)) {
			return canEditLaboratoireReferenceDescription;
		}
		if ("nature".equals(property)) {
			return canEditLaboratoireReferenceDescription;
		}
		if ("description".equals(property)) {
			return canEditLaboratoireReferenceDescription;
		}
		if ("region".equals(property)) {
			return canEditLaboratoireReferenceDescription;
		}
		if ("districtSante".equals(property)) {
			return canEditLaboratoireReferenceDescription;
		}
		if ("libelle".equals(property)) {
			return canEditLaboratoireReferenceAdresse;
		}
		if ("complementAdresse".equals(property)) {
			return canEditLaboratoireReferenceAdresse;
		}
		if ("quartier".equals(property)) {
			return canEditLaboratoireReferenceAdresse;
		}
		if ("ville".equals(property)) {
			return canEditLaboratoireReferenceAdresse;
		}
		if ("codePostal".equals(property)) {
			return canEditLaboratoireReferenceAdresse;
		}
		if ("coordonnees".equals(property)) {
			return canEditLaboratoireReferenceAdresse;
		}
		if ("lieuxDits".equals(property)) {
			return canEditLaboratoireReferenceAdresse;
		}
		return true;
	}
	/* Privileges check for entity LieuDit */

	/**
	 * Tells if the current user can access a LieuDit property
	 * @param property the property whose access has to be checked
	 * @return true if the current user can access a LieuDit property
	 */
	private boolean canAccessLieuDitProperty(String property) {
		if ("code".equals(property)) {
			return canReadLieuDitDescription;
		}
		if ("nom".equals(property)) {
			return canReadLieuDitDescription;
		}
		if ("description".equals(property)) {
			return canReadLieuDitDescription;
		}
		if ("libelle".equals(property)) {
			return canReadLieuDitAdresse;
		}
		if ("complementAdresse".equals(property)) {
			return canReadLieuDitAdresse;
		}
		if ("quartier".equals(property)) {
			return canReadLieuDitAdresse;
		}
		if ("ville".equals(property)) {
			return canReadLieuDitAdresse;
		}
		if ("codePostal".equals(property)) {
			return canReadLieuDitAdresse;
		}
		if ("coordonnees".equals(property)) {
			return canReadLieuDitAdresse;
		}
		return true;
	}

	/**
	 * Tells if the current user can update a LieuDit property
	 * @param property the property whose update privilege has to be checked
	 * @return true if the current user can update a LieuDit property
	 */
	private boolean canUpdateLieuDitProperty(String property) {
		if ("code".equals(property)) {
			return canEditLieuDitDescription;
		}
		if ("nom".equals(property)) {
			return canEditLieuDitDescription;
		}
		if ("description".equals(property)) {
			return canEditLieuDitDescription;
		}
		if ("libelle".equals(property)) {
			return canEditLieuDitAdresse;
		}
		if ("complementAdresse".equals(property)) {
			return canEditLieuDitAdresse;
		}
		if ("quartier".equals(property)) {
			return canEditLieuDitAdresse;
		}
		if ("ville".equals(property)) {
			return canEditLieuDitAdresse;
		}
		if ("codePostal".equals(property)) {
			return canEditLieuDitAdresse;
		}
		if ("coordonnees".equals(property)) {
			return canEditLieuDitAdresse;
		}
		return true;
	}
	/* Privileges check for entity Regime */

	/**
	 * Tells if the current user can access a Regime property
	 * @param property the property whose access has to be checked
	 * @return true if the current user can access a Regime property
	 */
	private boolean canAccessRegimeProperty(String property) {
		if ("nom".equals(property)) {
			return canReadRegimeDescription;
		}
		if ("type".equals(property)) {
			return canReadRegimeDescription;
		}
		if ("dureeTraitement".equals(property)) {
			return canReadRegimeDescription;
		}
		if ("poidsMin".equals(property)) {
			return canReadRegimeDescription;
		}
		if ("poidsMax".equals(property)) {
			return canReadRegimeDescription;
		}
		if ("description".equals(property)) {
			return canReadRegimeDescription;
		}
		if ("prisesMedicamenteuses".equals(property)) {
			return canReadRegimeDescription;
		}
		if ("actif".equals(property)) {
			return canReadRegimeDescription;
		}
		return true;
	}

	/**
	 * Tells if the current user can update a Regime property
	 * @param property the property whose update privilege has to be checked
	 * @return true if the current user can update a Regime property
	 */
	private boolean canUpdateRegimeProperty(String property) {
		if ("nom".equals(property)) {
			return canEditRegimeDescription;
		}
		if ("type".equals(property)) {
			return canEditRegimeDescription;
		}
		if ("dureeTraitement".equals(property)) {
			return canEditRegimeDescription;
		}
		if ("poidsMin".equals(property)) {
			return canEditRegimeDescription;
		}
		if ("poidsMax".equals(property)) {
			return canEditRegimeDescription;
		}
		if ("description".equals(property)) {
			return canEditRegimeDescription;
		}
		if ("prisesMedicamenteuses".equals(property)) {
			return canEditRegimeDescription;
		}
		if ("actif".equals(property)) {
			return canEditRegimeDescription;
		}
		return true;
	}
	/* Privileges check for entity PriseMedicamentRegime */

	/**
	 * Tells if the current user can access a PriseMedicamentRegime property
	 * @param property the property whose access has to be checked
	 * @return true if the current user can access a PriseMedicamentRegime property
	 */
	private boolean canAccessPriseMedicamentRegimeProperty(String property) {
		if ("regime".equals(property)) {
			return canReadPriseMedicamentRegimeDescription;
		}
		if ("medicament".equals(property)) {
			return canReadPriseMedicamentRegimeDescription;
		}
		if ("quantite".equals(property)) {
			return canReadPriseMedicamentRegimeDescription;
		}
		if ("quantiteUnite".equals(property)) {
			return canReadPriseMedicamentRegimeDescription;
		}
		return true;
	}

	/**
	 * Tells if the current user can update a PriseMedicamentRegime property
	 * @param property the property whose update privilege has to be checked
	 * @return true if the current user can update a PriseMedicamentRegime property
	 */
	private boolean canUpdatePriseMedicamentRegimeProperty(String property) {
		if ("regime".equals(property)) {
			return canEditPriseMedicamentRegimeDescription;
		}
		if ("medicament".equals(property)) {
			return canEditPriseMedicamentRegimeDescription;
		}
		if ("quantite".equals(property)) {
			return canEditPriseMedicamentRegimeDescription;
		}
		if ("quantiteUnite".equals(property)) {
			return canEditPriseMedicamentRegimeDescription;
		}
		return true;
	}
	/* Privileges check for entity Medicament */

	/**
	 * Tells if the current user can access a Medicament property
	 * @param property the property whose access has to be checked
	 * @return true if the current user can access a Medicament property
	 */
	private boolean canAccessMedicamentProperty(String property) {
		if ("code".equals(property)) {
			return canReadMedicamentDescription;
		}
		if ("designation".equals(property)) {
			return canReadMedicamentDescription;
		}
		if ("seuilPatient".equals(property)) {
			return canReadMedicamentDescription;
		}
		if ("estMedicamentAntituberculeux".equals(property)) {
			return canReadMedicamentDescription;
		}
		return true;
	}

	/**
	 * Tells if the current user can update a Medicament property
	 * @param property the property whose update privilege has to be checked
	 * @return true if the current user can update a Medicament property
	 */
	private boolean canUpdateMedicamentProperty(String property) {
		if ("code".equals(property)) {
			return canEditMedicamentDescription;
		}
		if ("designation".equals(property)) {
			return canEditMedicamentDescription;
		}
		if ("seuilPatient".equals(property)) {
			return canEditMedicamentDescription;
		}
		if ("estMedicamentAntituberculeux".equals(property)) {
			return canEditMedicamentDescription;
		}
		return true;
	}
	/* Privileges check for entity Intrant */

	/**
	 * Tells if the current user can access a Intrant property
	 * @param property the property whose access has to be checked
	 * @return true if the current user can access a Intrant property
	 */
	private boolean canAccessIntrantProperty(String property) {
		if ("identifiant".equals(property)) {
			return canReadIntrantDescription;
		}
		if ("nom".equals(property)) {
			return canReadIntrantDescription;
		}
		if ("description".equals(property)) {
			return canReadIntrantDescription;
		}
		if ("seuilPatient".equals(property)) {
			return canReadIntrantDescription;
		}
		return true;
	}

	/**
	 * Tells if the current user can update a Intrant property
	 * @param property the property whose update privilege has to be checked
	 * @return true if the current user can update a Intrant property
	 */
	private boolean canUpdateIntrantProperty(String property) {
		if ("identifiant".equals(property)) {
			return canEditIntrantDescription;
		}
		if ("nom".equals(property)) {
			return canEditIntrantDescription;
		}
		if ("description".equals(property)) {
			return canEditIntrantDescription;
		}
		if ("seuilPatient".equals(property)) {
			return canEditIntrantDescription;
		}
		return true;
	}
	/* Privileges check for entity Formation */

	/**
	 * Tells if the current user can access a Formation property
	 * @param property the property whose access has to be checked
	 * @return true if the current user can access a Formation property
	 */
	private boolean canAccessFormationProperty(String property) {
		if ("libelle".equals(property)) {
			return canReadFormationDescription;
		}
		if ("dateDebut".equals(property)) {
			return canReadFormationDescription;
		}
		if ("dateFin".equals(property)) {
			return canReadFormationDescription;
		}
		if ("lieu".equals(property)) {
			return canReadFormationDescription;
		}
		if ("objectifs".equals(property)) {
			return canReadFormationDescription;
		}
		if ("effectuee".equals(property)) {
			return canReadFormationDescription;
		}
		if ("candidatures".equals(property)) {
			return canReadFormationDescription;
		}
		return true;
	}

	/**
	 * Tells if the current user can update a Formation property
	 * @param property the property whose update privilege has to be checked
	 * @return true if the current user can update a Formation property
	 */
	private boolean canUpdateFormationProperty(String property) {
		if ("libelle".equals(property)) {
			return canEditFormationDescription;
		}
		if ("dateDebut".equals(property)) {
			return canEditFormationDescription;
		}
		if ("dateFin".equals(property)) {
			return canEditFormationDescription;
		}
		if ("lieu".equals(property)) {
			return canEditFormationDescription;
		}
		if ("objectifs".equals(property)) {
			return canEditFormationDescription;
		}
		if ("effectuee".equals(property)) {
			return canEditFormationDescription;
		}
		if ("candidatures".equals(property)) {
			return canEditFormationDescription;
		}
		return true;
	}
	/* Privileges check for entity CandidatureFormation */

	/**
	 * Tells if the current user can access a CandidatureFormation property
	 * @param property the property whose access has to be checked
	 * @return true if the current user can access a CandidatureFormation property
	 */
	private boolean canAccessCandidatureFormationProperty(String property) {
		if ("formation".equals(property)) {
			return canReadCandidatureFormationDescription;
		}
		if ("region".equals(property)) {
			return canReadCandidatureFormationDescription;
		}
		if ("districtSante".equals(property)) {
			return canReadCandidatureFormationDescription;
		}
		if ("CDT".equals(property)) {
			return canReadCandidatureFormationDescription;
		}
		if ("personnel".equals(property)) {
			return canReadCandidatureFormationDescription;
		}
		if ("approuveeRegion".equals(property)) {
			return canReadCandidatureFormationRegionApprobation;
		}
		if ("motifRejetRegion".equals(property)) {
			return canReadCandidatureFormationRegionApprobation;
		}
		if ("approuveeGTC".equals(property)) {
			return canReadCandidatureFormationGtcApprobation;
		}
		if ("motifRejetGTC".equals(property)) {
			return canReadCandidatureFormationGtcApprobation;
		}
		return true;
	}

	/**
	 * Tells if the current user can update a CandidatureFormation property
	 * @param property the property whose update privilege has to be checked
	 * @return true if the current user can update a CandidatureFormation property
	 */
	private boolean canUpdateCandidatureFormationProperty(String property) {
		if ("formation".equals(property)) {
			return canEditCandidatureFormationDescription;
		}
		if ("region".equals(property)) {
			return canEditCandidatureFormationDescription;
		}
		if ("districtSante".equals(property)) {
			return canEditCandidatureFormationDescription;
		}
		if ("CDT".equals(property)) {
			return canEditCandidatureFormationDescription;
		}
		if ("personnel".equals(property)) {
			return canEditCandidatureFormationDescription;
		}
		if ("approuveeRegion".equals(property)) {
			return canEditCandidatureFormationRegionApprobation;
		}
		if ("motifRejetRegion".equals(property)) {
			return canEditCandidatureFormationRegionApprobation;
		}
		if ("approuveeGTC".equals(property)) {
			return canEditCandidatureFormationGtcApprobation;
		}
		if ("motifRejetGTC".equals(property)) {
			return canEditCandidatureFormationGtcApprobation;
		}
		return true;
	}
	/* Privileges check for entity Qualification */

	/**
	 * Tells if the current user can access a Qualification property
	 * @param property the property whose access has to be checked
	 * @return true if the current user can access a Qualification property
	 */
	private boolean canAccessQualificationProperty(String property) {
		if ("code".equals(property)) {
			return canReadQualificationDescription;
		}
		if ("nom".equals(property)) {
			return canReadQualificationDescription;
		}
		if ("description".equals(property)) {
			return canReadQualificationDescription;
		}
		return true;
	}

	/**
	 * Tells if the current user can update a Qualification property
	 * @param property the property whose update privilege has to be checked
	 * @return true if the current user can update a Qualification property
	 */
	private boolean canUpdateQualificationProperty(String property) {
		if ("code".equals(property)) {
			return canEditQualificationDescription;
		}
		if ("nom".equals(property)) {
			return canEditQualificationDescription;
		}
		if ("description".equals(property)) {
			return canEditQualificationDescription;
		}
		return true;
	}
	/* Privileges check for entity Tutoriel */

	/**
	 * Tells if the current user can access a Tutoriel property
	 * @param property the property whose access has to be checked
	 * @return true if the current user can access a Tutoriel property
	 */
	private boolean canAccessTutorielProperty(String property) {
		if ("reference".equals(property)) {
			return canReadTutorielDescription;
		}
		if ("nom".equals(property)) {
			return canReadTutorielDescription;
		}
		if ("description".equals(property)) {
			return canReadTutorielDescription;
		}
		if ("type".equals(property)) {
			return canReadTutorielDescription;
		}
		if ("audioT".equals(property)) {
			return canReadTutorielDescription;
		}
		if ("videoT".equals(property)) {
			return canReadTutorielDescription;
		}
		if ("textT".equals(property)) {
			return canReadTutorielDescription;
		}
		return true;
	}

	/**
	 * Tells if the current user can update a Tutoriel property
	 * @param property the property whose update privilege has to be checked
	 * @return true if the current user can update a Tutoriel property
	 */
	private boolean canUpdateTutorielProperty(String property) {
		if ("reference".equals(property)) {
			return canEditTutorielDescription;
		}
		if ("nom".equals(property)) {
			return canEditTutorielDescription;
		}
		if ("description".equals(property)) {
			return canEditTutorielDescription;
		}
		if ("type".equals(property)) {
			return canEditTutorielDescription;
		}
		if ("audioT".equals(property)) {
			return canEditTutorielDescription;
		}
		if ("videoT".equals(property)) {
			return canEditTutorielDescription;
		}
		if ("textT".equals(property)) {
			return canEditTutorielDescription;
		}
		return true;
	}
	/* Privileges check for entity SmsPredefini */

	/**
	 * Tells if the current user can access a SmsPredefini property
	 * @param property the property whose access has to be checked
	 * @return true if the current user can access a SmsPredefini property
	 */
	private boolean canAccessSmsPredefiniProperty(String property) {
		if ("type".equals(property)) {
			return canReadSmsPredefiniDescription;
		}
		if ("objet".equals(property)) {
			return canReadSmsPredefiniDescription;
		}
		if ("periodicite".equals(property)) {
			return canReadSmsPredefiniDescription;
		}
		if ("dateEnvoyeSMSPonctuel".equals(property)) {
			return canReadSmsPredefiniDescription;
		}
		if ("statut".equals(property)) {
			return canReadSmsPredefiniDescription;
		}
		if ("message".equals(property)) {
			return canReadSmsPredefiniDescription;
		}
		if ("reponse1".equals(property)) {
			return canReadSmsPredefiniDescription;
		}
		if ("reponse2".equals(property)) {
			return canReadSmsPredefiniDescription;
		}
		if ("bonneReponse".equals(property)) {
			return canReadSmsPredefiniDescription;
		}
		if ("envoyerAPartirDe".equals(property)) {
			return canReadSmsPredefiniDescription;
		}
		if ("arreterEnvoyerA".equals(property)) {
			return canReadSmsPredefiniDescription;
		}
		return true;
	}

	/**
	 * Tells if the current user can update a SmsPredefini property
	 * @param property the property whose update privilege has to be checked
	 * @return true if the current user can update a SmsPredefini property
	 */
	private boolean canUpdateSmsPredefiniProperty(String property) {
		if ("type".equals(property)) {
			return canEditSmsPredefiniDescription;
		}
		if ("objet".equals(property)) {
			return canEditSmsPredefiniDescription;
		}
		if ("periodicite".equals(property)) {
			return canEditSmsPredefiniDescription;
		}
		if ("dateEnvoyeSMSPonctuel".equals(property)) {
			return canEditSmsPredefiniDescription;
		}
		if ("statut".equals(property)) {
			return canEditSmsPredefiniDescription;
		}
		if ("message".equals(property)) {
			return canEditSmsPredefiniDescription;
		}
		if ("reponse1".equals(property)) {
			return canEditSmsPredefiniDescription;
		}
		if ("reponse2".equals(property)) {
			return canEditSmsPredefiniDescription;
		}
		if ("bonneReponse".equals(property)) {
			return canEditSmsPredefiniDescription;
		}
		if ("envoyerAPartirDe".equals(property)) {
			return canEditSmsPredefiniDescription;
		}
		if ("arreterEnvoyerA".equals(property)) {
			return canEditSmsPredefiniDescription;
		}
		return true;
	}
	/* Privileges check for entity OutBox */

	/**
	 * Tells if the current user can access a OutBox property
	 * @param property the property whose access has to be checked
	 * @return true if the current user can access a OutBox property
	 */
	private boolean canAccessOutBoxProperty(String property) {
		if ("patient".equals(property)) {
			return canReadOutBoxAdresse;
		}
		if ("message".equals(property)) {
			return canReadOutBoxMessageInformation;
		}
		if ("reponse".equals(property)) {
			return canReadOutBoxMessageInformation;
		}
		if ("statut".equals(property)) {
			return canReadOutBoxMessageInformation;
		}
		if ("dateDernierEssai".equals(property)) {
			return canReadOutBoxMessageInformation;
		}
		return true;
	}

	/**
	 * Tells if the current user can update a OutBox property
	 * @param property the property whose update privilege has to be checked
	 * @return true if the current user can update a OutBox property
	 */
	private boolean canUpdateOutBoxProperty(String property) {
		if ("patient".equals(property)) {
			return canEditOutBoxAdresse;
		}
		if ("message".equals(property)) {
			return canEditOutBoxMessageInformation;
		}
		if ("reponse".equals(property)) {
			return canEditOutBoxMessageInformation;
		}
		if ("statut".equals(property)) {
			return canEditOutBoxMessageInformation;
		}
		if ("dateDernierEssai".equals(property)) {
			return canEditOutBoxMessageInformation;
		}
		return true;
	}
	/* Privileges check for entity Utilisateur */

	/**
	 * Tells if the current user can access a Utilisateur property
	 * @param property the property whose access has to be checked
	 * @return true if the current user can access a Utilisateur property
	 */
	private boolean canAccessUtilisateurProperty(String property) {
		if ("nom".equals(property)) {
			return canReadUtilisateurIdentification;
		}
		if ("dateNaissance".equals(property)) {
			return canReadUtilisateurIdentification;
		}
		if ("profession".equals(property)) {
			return canReadUtilisateurIdentification;
		}
		if ("telephoneUn".equals(property)) {
			return canReadUtilisateurContact;
		}
		if ("telephoneDeux".equals(property)) {
			return canReadUtilisateurContact;
		}
		if ("telephoneTrois".equals(property)) {
			return canReadUtilisateurContact;
		}
		if ("email".equals(property)) {
			return canReadUtilisateurContact;
		}
		if ("libelle".equals(property)) {
			return canReadUtilisateurContact;
		}
		if ("complementAdresse".equals(property)) {
			return canReadUtilisateurContact;
		}
		if ("quartier".equals(property)) {
			return canReadUtilisateurContact;
		}
		if ("ville".equals(property)) {
			return canReadUtilisateurContact;
		}
		if ("codePostal".equals(property)) {
			return canReadUtilisateurContact;
		}
		return true;
	}

	/**
	 * Tells if the current user can update a Utilisateur property
	 * @param property the property whose update privilege has to be checked
	 * @return true if the current user can update a Utilisateur property
	 */
	private boolean canUpdateUtilisateurProperty(String property) {
		if ("nom".equals(property)) {
			return canEditUtilisateurIdentification;
		}
		if ("dateNaissance".equals(property)) {
			return canEditUtilisateurIdentification;
		}
		if ("profession".equals(property)) {
			return canEditUtilisateurIdentification;
		}
		if ("telephoneUn".equals(property)) {
			return canEditUtilisateurContact;
		}
		if ("telephoneDeux".equals(property)) {
			return canEditUtilisateurContact;
		}
		if ("telephoneTrois".equals(property)) {
			return canEditUtilisateurContact;
		}
		if ("email".equals(property)) {
			return canEditUtilisateurContact;
		}
		if ("libelle".equals(property)) {
			return canEditUtilisateurContact;
		}
		if ("complementAdresse".equals(property)) {
			return canEditUtilisateurContact;
		}
		if ("quartier".equals(property)) {
			return canEditUtilisateurContact;
		}
		if ("ville".equals(property)) {
			return canEditUtilisateurContact;
		}
		if ("codePostal".equals(property)) {
			return canEditUtilisateurContact;
		}
		return true;
	}

	private boolean isAdmin(ImogActor actor) {
		for (Profile profile : actor.getProfiles()) {
			if (profile.getId().equals(Profile.ADMINISTRATOR)) {
				return true;
			}
		}
		return false;
	}

}
