package org.imogene.epicam.security;

import org.imogene.lib.common.entity.ImogActor;
import org.imogene.lib.common.profile.FieldGroupProfile;
import org.imogene.lib.common.profile.Profile;
import org.imogene.lib.common.security.AccessPolicy;

/**
 * This class tells if the current user can access the properties of the model
 * objects in read and in update mode 
 * @author MEDES-IMPS
 */
public class AccessPolicyImpl implements AccessPolicy {

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
		if (isAdmin(actor)) {
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

	@Override
	public boolean canAccessProperty(Object bean, String property) {
		return false;
	}

	@Override
	public boolean canUpdateProperty(Object bean, String property) {
		return false;
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

	private boolean isAdmin(ImogActor actor) {
		for (Profile profile : actor.getProfiles()) {
			if (profile.getId().equals(Profile.ADMINISTRATOR)) {
				return true;
			}
		}
		return false;
	}

}
