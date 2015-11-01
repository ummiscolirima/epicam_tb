package org.imogene.epicam.domain.entity;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.imogene.epicam.domain.entity.*;
import org.imogene.lib.common.binary.Binary;
import org.imogene.lib.common.entity.ImogBean;
import org.imogene.lib.common.dynamicfields.DynamicFieldInstance;
import org.imogene.lib.sync.binary.file.BinaryFileHandler;
import org.imogene.lib.sync.server.EntityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * EPICAM Bean Implementation
 * @author Medes-IMPS
 */
public class EntityHelperImpl implements EntityHelper {

	@Autowired
	@Qualifier(value = "binaryHandler")
	private BinaryFileHandler binaryHandler;

	@Override
	public List<ImogBean> getAssociatedEntitiesIds(ImogBean entity) {
		if (entity instanceof Patient) {
			return getForPatient((Patient) entity);
		} else if (entity instanceof CasIndex) {
			return getForCasIndex((CasIndex) entity);
		} else if (entity instanceof CasTuberculose) {
			return getForCasTuberculose((CasTuberculose) entity);
		} else if (entity instanceof ExamenSerologie) {
			return getForExamenSerologie((ExamenSerologie) entity);
		} else if (entity instanceof ExamenBiologique) {
			return getForExamenBiologique((ExamenBiologique) entity);
		} else if (entity instanceof ExamenMicroscopie) {
			return getForExamenMicroscopie((ExamenMicroscopie) entity);
		} else if (entity instanceof ExamenATB) {
			return getForExamenATB((ExamenATB) entity);
		} else if (entity instanceof PriseMedicamenteuse) {
			return getForPriseMedicamenteuse((PriseMedicamenteuse) entity);
		} else if (entity instanceof RendezVous) {
			return getForRendezVous((RendezVous) entity);
		} else if (entity instanceof TransfertReference) {
			return getForTransfertReference((TransfertReference) entity);
		} else if (entity instanceof Lot) {
			return getForLot((Lot) entity);
		} else if (entity instanceof HorsUsage) {
			return getForHorsUsage((HorsUsage) entity);
		} else if (entity instanceof EntreeLot) {
			return getForEntreeLot((EntreeLot) entity);
		} else if (entity instanceof SortieLot) {
			return getForSortieLot((SortieLot) entity);
		} else if (entity instanceof Commande) {
			return getForCommande((Commande) entity);
		} else if (entity instanceof DetailCommandeMedicament) {
			return getForDetailCommandeMedicament((DetailCommandeMedicament) entity);
		} else if (entity instanceof DetailCommandeIntrant) {
			return getForDetailCommandeIntrant((DetailCommandeIntrant) entity);
		} else if (entity instanceof Reception) {
			return getForReception((Reception) entity);
		} else if (entity instanceof DetailReceptionMedicament) {
			return getForDetailReceptionMedicament((DetailReceptionMedicament) entity);
		} else if (entity instanceof DetailReceptionIntrant) {
			return getForDetailReceptionIntrant((DetailReceptionIntrant) entity);
		} else if (entity instanceof Ravitaillement) {
			return getForRavitaillement((Ravitaillement) entity);
		} else if (entity instanceof DetailRavitaillement) {
			return getForDetailRavitaillement((DetailRavitaillement) entity);
		} else if (entity instanceof Inventaire) {
			return getForInventaire((Inventaire) entity);
		} else if (entity instanceof DetailInventaire) {
			return getForDetailInventaire((DetailInventaire) entity);
		} else if (entity instanceof Personnel) {
			return getForPersonnel((Personnel) entity);
		} else if (entity instanceof DepartPersonnel) {
			return getForDepartPersonnel((DepartPersonnel) entity);
		} else if (entity instanceof ArriveePersonnel) {
			return getForArriveePersonnel((ArriveePersonnel) entity);
		} else if (entity instanceof Region) {
			return getForRegion((Region) entity);
		} else if (entity instanceof DistrictSante) {
			return getForDistrictSante((DistrictSante) entity);
		} else if (entity instanceof CentreDiagTrait) {
			return getForCentreDiagTrait((CentreDiagTrait) entity);
		} else if (entity instanceof LaboratoireReference) {
			return getForLaboratoireReference((LaboratoireReference) entity);
		} else if (entity instanceof LieuDit) {
			return getForLieuDit((LieuDit) entity);
		} else if (entity instanceof Regime) {
			return getForRegime((Regime) entity);
		} else if (entity instanceof PriseMedicamentRegime) {
			return getForPriseMedicamentRegime((PriseMedicamentRegime) entity);
		} else if (entity instanceof Medicament) {
			return getForMedicament((Medicament) entity);
		} else if (entity instanceof Intrant) {
			return getForIntrant((Intrant) entity);
		} else if (entity instanceof Formation) {
			return getForFormation((Formation) entity);
		} else if (entity instanceof CandidatureFormation) {
			return getForCandidatureFormation((CandidatureFormation) entity);
		} else if (entity instanceof Qualification) {
			return getForQualification((Qualification) entity);
		} else if (entity instanceof Tutoriel) {
			return getForTutoriel((Tutoriel) entity);
		} else if (entity instanceof SmsPredefini) {
			return getForSmsPredefini((SmsPredefini) entity);
		} else if (entity instanceof OutBox) {
			return getForOutBox((OutBox) entity);
		} else if (entity instanceof Utilisateur) {
			return getForUtilisateur((Utilisateur) entity);
		}
		return new Vector<ImogBean>();
	}

	private List<ImogBean> getForPatient(Patient entity) {
		List<ImogBean> result = new Vector<ImogBean>();
		return result;
	}
	private List<ImogBean> getForCasIndex(CasIndex entity) {
		List<ImogBean> result = new Vector<ImogBean>();
		if (entity.getPatient() != null) {
			result.add(entity.getPatient());
		}
		if (entity.getPatientLie() != null) {
			result.add(entity.getPatientLie());
		}
		return result;
	}
	private List<ImogBean> getForCasTuberculose(CasTuberculose entity) {
		List<ImogBean> result = new Vector<ImogBean>();
		if (entity.getPatient() != null) {
			result.add(entity.getPatient());
		}
		if (entity.getRegimePhaseInitiale() != null) {
			result.add(entity.getRegimePhaseInitiale());
		}
		if (entity.getRegimePhaseContinuation() != null) {
			result.add(entity.getRegimePhaseContinuation());
		}
		return result;
	}
	private List<ImogBean> getForExamenSerologie(ExamenSerologie entity) {
		List<ImogBean> result = new Vector<ImogBean>();
		if (entity.getPatient() != null) {
			result.add(entity.getPatient());
		}
		return result;
	}
	private List<ImogBean> getForExamenBiologique(ExamenBiologique entity) {
		List<ImogBean> result = new Vector<ImogBean>();
		if (entity.getPatient() != null) {
			result.add(entity.getPatient());
		}
		return result;
	}
	private List<ImogBean> getForExamenMicroscopie(ExamenMicroscopie entity) {
		List<ImogBean> result = new Vector<ImogBean>();
		if (entity.getCDT() != null) {
			result.add(entity.getCDT());
		}
		if (entity.getLaboratoireReference() != null) {
			result.add(entity.getLaboratoireReference());
		}
		if (entity.getCasTb() != null) {
			result.add(entity.getCasTb());
		}
		return result;
	}
	private List<ImogBean> getForExamenATB(ExamenATB entity) {
		List<ImogBean> result = new Vector<ImogBean>();
		if (entity.getCDT() != null) {
			result.add(entity.getCDT());
		}
		if (entity.getLaboratoireReference() != null) {
			result.add(entity.getLaboratoireReference());
		}
		if (entity.getCasTb() != null) {
			result.add(entity.getCasTb());
		}
		return result;
	}
	private List<ImogBean> getForPriseMedicamenteuse(PriseMedicamenteuse entity) {
		List<ImogBean> result = new Vector<ImogBean>();
		if (entity.getPhaseIntensive() != null) {
			result.add(entity.getPhaseIntensive());
		}
		if (entity.getPhaseContinuation() != null) {
			result.add(entity.getPhaseContinuation());
		}
		return result;
	}
	private List<ImogBean> getForRendezVous(RendezVous entity) {
		List<ImogBean> result = new Vector<ImogBean>();
		if (entity.getCasTb() != null) {
			result.add(entity.getCasTb());
		}
		return result;
	}
	private List<ImogBean> getForTransfertReference(TransfertReference entity) {
		List<ImogBean> result = new Vector<ImogBean>();
		if (entity.getRegion() != null) {
			result.add(entity.getRegion());
		}
		if (entity.getDistrictSante() != null) {
			result.add(entity.getDistrictSante());
		}
		if (entity.getCDTDepart() != null) {
			result.add(entity.getCDTDepart());
		}
		if (entity.getPatient() != null) {
			result.add(entity.getPatient());
		}
		if (entity.getRegionArrivee() != null) {
			result.add(entity.getRegionArrivee());
		}
		if (entity.getDistrictSanteArrivee() != null) {
			result.add(entity.getDistrictSanteArrivee());
		}
		if (entity.getCDTArrivee() != null) {
			result.add(entity.getCDTArrivee());
		}
		return result;
	}
	private List<ImogBean> getForLot(Lot entity) {
		List<ImogBean> result = new Vector<ImogBean>();
		if (entity.getRegion() != null) {
			result.add(entity.getRegion());
		}
		if (entity.getDistrictSante() != null) {
			result.add(entity.getDistrictSante());
		}
		if (entity.getCDT() != null) {
			result.add(entity.getCDT());
		}
		if (entity.getMedicament() != null) {
			result.add(entity.getMedicament());
		}
		if (entity.getIntrant() != null) {
			result.add(entity.getIntrant());
		}
		return result;
	}
	private List<ImogBean> getForHorsUsage(HorsUsage entity) {
		List<ImogBean> result = new Vector<ImogBean>();
		if (entity.getLot() != null) {
			result.add(entity.getLot());
		}
		return result;
	}
	private List<ImogBean> getForEntreeLot(EntreeLot entity) {
		List<ImogBean> result = new Vector<ImogBean>();
		if (entity.getCDT() != null) {
			result.add(entity.getCDT());
		}
		if (entity.getLot() != null) {
			result.add(entity.getLot());
		}
		return result;
	}
	private List<ImogBean> getForSortieLot(SortieLot entity) {
		List<ImogBean> result = new Vector<ImogBean>();
		if (entity.getCDT() != null) {
			result.add(entity.getCDT());
		}
		if (entity.getLot() != null) {
			result.add(entity.getLot());
		}
		return result;
	}
	private List<ImogBean> getForCommande(Commande entity) {
		List<ImogBean> result = new Vector<ImogBean>();
		if (entity.getRegion() != null) {
			result.add(entity.getRegion());
		}
		if (entity.getDistrictSante() != null) {
			result.add(entity.getDistrictSante());
		}
		if (entity.getCDT() != null) {
			result.add(entity.getCDT());
		}
		return result;
	}
	private List<ImogBean> getForDetailCommandeMedicament(
			DetailCommandeMedicament entity) {
		List<ImogBean> result = new Vector<ImogBean>();
		if (entity.getCommande() != null) {
			result.add(entity.getCommande());
		}
		if (entity.getMedicament() != null) {
			result.add(entity.getMedicament());
		}
		return result;
	}
	private List<ImogBean> getForDetailCommandeIntrant(
			DetailCommandeIntrant entity) {
		List<ImogBean> result = new Vector<ImogBean>();
		if (entity.getCommande() != null) {
			result.add(entity.getCommande());
		}
		if (entity.getIntrant() != null) {
			result.add(entity.getIntrant());
		}
		return result;
	}
	private List<ImogBean> getForReception(Reception entity) {
		List<ImogBean> result = new Vector<ImogBean>();
		if (entity.getRegion() != null) {
			result.add(entity.getRegion());
		}
		if (entity.getDistrictSante() != null) {
			result.add(entity.getDistrictSante());
		}
		if (entity.getCDT() != null) {
			result.add(entity.getCDT());
		}
		if (entity.getCommande() != null) {
			result.add(entity.getCommande());
		}
		return result;
	}
	private List<ImogBean> getForDetailReceptionMedicament(
			DetailReceptionMedicament entity) {
		List<ImogBean> result = new Vector<ImogBean>();
		if (entity.getReception() != null) {
			result.add(entity.getReception());
		}
		if (entity.getCommande() != null) {
			result.add(entity.getCommande());
		}
		if (entity.getDetailCommande() != null) {
			result.add(entity.getDetailCommande());
		}
		if (entity.getEntreeLot() != null) {
			result.add(entity.getEntreeLot());
		}
		return result;
	}
	private List<ImogBean> getForDetailReceptionIntrant(
			DetailReceptionIntrant entity) {
		List<ImogBean> result = new Vector<ImogBean>();
		if (entity.getReception() != null) {
			result.add(entity.getReception());
		}
		if (entity.getCommande() != null) {
			result.add(entity.getCommande());
		}
		if (entity.getDetailCommande() != null) {
			result.add(entity.getDetailCommande());
		}
		if (entity.getEntreeLot() != null) {
			result.add(entity.getEntreeLot());
		}
		return result;
	}
	private List<ImogBean> getForRavitaillement(Ravitaillement entity) {
		List<ImogBean> result = new Vector<ImogBean>();
		if (entity.getRegion() != null) {
			result.add(entity.getRegion());
		}
		if (entity.getDistrictSante() != null) {
			result.add(entity.getDistrictSante());
		}
		if (entity.getCDTDepart() != null) {
			result.add(entity.getCDTDepart());
		}
		if (entity.getRegionArrivee() != null) {
			result.add(entity.getRegionArrivee());
		}
		if (entity.getDistrictSanteArrivee() != null) {
			result.add(entity.getDistrictSanteArrivee());
		}
		if (entity.getCDTArrivee() != null) {
			result.add(entity.getCDTArrivee());
		}
		return result;
	}
	private List<ImogBean> getForDetailRavitaillement(
			DetailRavitaillement entity) {
		List<ImogBean> result = new Vector<ImogBean>();
		if (entity.getRavitaillement() != null) {
			result.add(entity.getRavitaillement());
		}
		if (entity.getSortieLot() != null) {
			result.add(entity.getSortieLot());
		}
		if (entity.getEntreeLot() != null) {
			result.add(entity.getEntreeLot());
		}
		return result;
	}
	private List<ImogBean> getForInventaire(Inventaire entity) {
		List<ImogBean> result = new Vector<ImogBean>();
		if (entity.getRegion() != null) {
			result.add(entity.getRegion());
		}
		if (entity.getDistrictSante() != null) {
			result.add(entity.getDistrictSante());
		}
		if (entity.getCDT() != null) {
			result.add(entity.getCDT());
		}
		return result;
	}
	private List<ImogBean> getForDetailInventaire(DetailInventaire entity) {
		List<ImogBean> result = new Vector<ImogBean>();
		if (entity.getInventaire() != null) {
			result.add(entity.getInventaire());
		}
		if (entity.getCDT() != null) {
			result.add(entity.getCDT());
		}
		if (entity.getLot() != null) {
			result.add(entity.getLot());
		}
		return result;
	}
	private List<ImogBean> getForPersonnel(Personnel entity) {
		List<ImogBean> result = new Vector<ImogBean>();
		if (entity.getRegion() != null) {
			result.add(entity.getRegion());
		}
		if (entity.getDistrictSante() != null) {
			result.add(entity.getDistrictSante());
		}
		if (entity.getCDT() != null) {
			result.add(entity.getCDT());
		}
		return result;
	}
	private List<ImogBean> getForDepartPersonnel(DepartPersonnel entity) {
		List<ImogBean> result = new Vector<ImogBean>();
		if (entity.getRegion() != null) {
			result.add(entity.getRegion());
		}
		if (entity.getDistrictSante() != null) {
			result.add(entity.getDistrictSante());
		}
		if (entity.getCDT() != null) {
			result.add(entity.getCDT());
		}
		if (entity.getPersonnel() != null) {
			result.add(entity.getPersonnel());
		}
		return result;
	}
	private List<ImogBean> getForArriveePersonnel(ArriveePersonnel entity) {
		List<ImogBean> result = new Vector<ImogBean>();
		if (entity.getRegion() != null) {
			result.add(entity.getRegion());
		}
		if (entity.getDistrictSante() != null) {
			result.add(entity.getDistrictSante());
		}
		if (entity.getCDT() != null) {
			result.add(entity.getCDT());
		}
		if (entity.getPersonnel() != null) {
			result.add(entity.getPersonnel());
		}
		return result;
	}
	private List<ImogBean> getForRegion(Region entity) {
		List<ImogBean> result = new Vector<ImogBean>();
		return result;
	}
	private List<ImogBean> getForDistrictSante(DistrictSante entity) {
		List<ImogBean> result = new Vector<ImogBean>();
		if (entity.getRegion() != null) {
			result.add(entity.getRegion());
		}
		return result;
	}
	private List<ImogBean> getForCentreDiagTrait(CentreDiagTrait entity) {
		List<ImogBean> result = new Vector<ImogBean>();
		if (entity.getRegion() != null) {
			result.add(entity.getRegion());
		}
		if (entity.getDistrictSante() != null) {
			result.add(entity.getDistrictSante());
		}
		return result;
	}
	private List<ImogBean> getForLaboratoireReference(
			LaboratoireReference entity) {
		List<ImogBean> result = new Vector<ImogBean>();
		if (entity.getRegion() != null) {
			result.add(entity.getRegion());
		}
		if (entity.getDistrictSante() != null) {
			result.add(entity.getDistrictSante());
		}
		return result;
	}
	private List<ImogBean> getForLieuDit(LieuDit entity) {
		List<ImogBean> result = new Vector<ImogBean>();
		return result;
	}
	private List<ImogBean> getForRegime(Regime entity) {
		List<ImogBean> result = new Vector<ImogBean>();
		return result;
	}
	private List<ImogBean> getForPriseMedicamentRegime(
			PriseMedicamentRegime entity) {
		List<ImogBean> result = new Vector<ImogBean>();
		if (entity.getRegime() != null) {
			result.add(entity.getRegime());
		}
		if (entity.getMedicament() != null) {
			result.add(entity.getMedicament());
		}
		return result;
	}
	private List<ImogBean> getForMedicament(Medicament entity) {
		List<ImogBean> result = new Vector<ImogBean>();
		return result;
	}
	private List<ImogBean> getForIntrant(Intrant entity) {
		List<ImogBean> result = new Vector<ImogBean>();
		return result;
	}
	private List<ImogBean> getForFormation(Formation entity) {
		List<ImogBean> result = new Vector<ImogBean>();
		return result;
	}
	private List<ImogBean> getForCandidatureFormation(
			CandidatureFormation entity) {
		List<ImogBean> result = new Vector<ImogBean>();
		if (entity.getFormation() != null) {
			result.add(entity.getFormation());
		}
		if (entity.getRegion() != null) {
			result.add(entity.getRegion());
		}
		if (entity.getDistrictSante() != null) {
			result.add(entity.getDistrictSante());
		}
		if (entity.getCDT() != null) {
			result.add(entity.getCDT());
		}
		if (entity.getPersonnel() != null) {
			result.add(entity.getPersonnel());
		}
		return result;
	}
	private List<ImogBean> getForQualification(Qualification entity) {
		List<ImogBean> result = new Vector<ImogBean>();
		return result;
	}
	private List<ImogBean> getForTutoriel(Tutoriel entity) {
		List<ImogBean> result = new Vector<ImogBean>();
		return result;
	}
	private List<ImogBean> getForSmsPredefini(SmsPredefini entity) {
		List<ImogBean> result = new Vector<ImogBean>();
		return result;
	}
	private List<ImogBean> getForOutBox(OutBox entity) {
		List<ImogBean> result = new Vector<ImogBean>();
		if (entity.getPatient() != null) {
			result.add(entity.getPatient());
		}
		return result;
	}
	private List<ImogBean> getForUtilisateur(Utilisateur entity) {
		List<ImogBean> result = new Vector<ImogBean>();
		return result;
	}

	public List<Binary> getAssociatedBinaries(List<ImogBean> entities) {
		List<Binary> result = new Vector<Binary>();
		for (ImogBean entity : entities) {
			if (entity instanceof Patient) {
			} else if (entity instanceof CasIndex) {
			} else if (entity instanceof CasTuberculose) {
			} else if (entity instanceof ExamenSerologie) {
			} else if (entity instanceof ExamenBiologique) {
			} else if (entity instanceof ExamenMicroscopie) {
			} else if (entity instanceof ExamenATB) {
			} else if (entity instanceof PriseMedicamenteuse) {
			} else if (entity instanceof RendezVous) {
			} else if (entity instanceof TransfertReference) {
			} else if (entity instanceof Lot) {
			} else if (entity instanceof HorsUsage) {
			} else if (entity instanceof EntreeLot) {
			} else if (entity instanceof SortieLot) {
			} else if (entity instanceof Commande) {
			} else if (entity instanceof DetailCommandeMedicament) {
			} else if (entity instanceof DetailCommandeIntrant) {
			} else if (entity instanceof Reception) {
			} else if (entity instanceof DetailReceptionMedicament) {
			} else if (entity instanceof DetailReceptionIntrant) {
			} else if (entity instanceof Ravitaillement) {
			} else if (entity instanceof DetailRavitaillement) {
			} else if (entity instanceof Inventaire) {
			} else if (entity instanceof DetailInventaire) {
			} else if (entity instanceof Personnel) {
			} else if (entity instanceof DepartPersonnel) {
			} else if (entity instanceof ArriveePersonnel) {
			} else if (entity instanceof Region) {
			} else if (entity instanceof DistrictSante) {
			} else if (entity instanceof CentreDiagTrait) {
			} else if (entity instanceof LaboratoireReference) {
			} else if (entity instanceof LieuDit) {
			} else if (entity instanceof Regime) {
			} else if (entity instanceof PriseMedicamentRegime) {
			} else if (entity instanceof Medicament) {
			} else if (entity instanceof Intrant) {
			} else if (entity instanceof Formation) {
			} else if (entity instanceof CandidatureFormation) {
			} else if (entity instanceof Qualification) {
			} else if (entity instanceof Tutoriel) {
				Binary audioT = ((Tutoriel) entity).getAudioT();
				if (audioT != null) {
					result.add(audioT);
				}
				Binary videoT = ((Tutoriel) entity).getVideoT();
				if (videoT != null) {
					result.add(videoT);
				}
				Binary textT = ((Tutoriel) entity).getTextT();
				if (textT != null) {
					result.add(textT);
				}
			} else if (entity instanceof SmsPredefini) {
			} else if (entity instanceof OutBox) {
			} else if (entity instanceof Utilisateur) {
			} else if (entity instanceof DynamicFieldInstance) {
				DynamicFieldInstance instance = (DynamicFieldInstance) entity;
				if (instance.getFieldValue() != null
						&& ("bin".equals(instance.getFieldTemplate()
								.getFieldType()) || "img".equals(instance
								.getFieldTemplate().getFieldType()))) {
					result.add(binaryHandler.loadEntity(instance
							.getFieldValue()));
				}
			}
		}
		return result;
	}

	public List<Binary> getAssociatedBinariesUploaded(List<ImogBean> entities,
			Date lastDate) {
		List<Binary> result = new Vector<Binary>();
		for (ImogBean entity : entities) {
			if (entity instanceof Patient) {
			} else if (entity instanceof CasIndex) {
			} else if (entity instanceof CasTuberculose) {
			} else if (entity instanceof ExamenSerologie) {
			} else if (entity instanceof ExamenBiologique) {
			} else if (entity instanceof ExamenMicroscopie) {
			} else if (entity instanceof ExamenATB) {
			} else if (entity instanceof PriseMedicamenteuse) {
			} else if (entity instanceof RendezVous) {
			} else if (entity instanceof TransfertReference) {
			} else if (entity instanceof Lot) {
			} else if (entity instanceof HorsUsage) {
			} else if (entity instanceof EntreeLot) {
			} else if (entity instanceof SortieLot) {
			} else if (entity instanceof Commande) {
			} else if (entity instanceof DetailCommandeMedicament) {
			} else if (entity instanceof DetailCommandeIntrant) {
			} else if (entity instanceof Reception) {
			} else if (entity instanceof DetailReceptionMedicament) {
			} else if (entity instanceof DetailReceptionIntrant) {
			} else if (entity instanceof Ravitaillement) {
			} else if (entity instanceof DetailRavitaillement) {
			} else if (entity instanceof Inventaire) {
			} else if (entity instanceof DetailInventaire) {
			} else if (entity instanceof Personnel) {
			} else if (entity instanceof DepartPersonnel) {
			} else if (entity instanceof ArriveePersonnel) {
			} else if (entity instanceof Region) {
			} else if (entity instanceof DistrictSante) {
			} else if (entity instanceof CentreDiagTrait) {
			} else if (entity instanceof LaboratoireReference) {
			} else if (entity instanceof LieuDit) {
			} else if (entity instanceof Regime) {
			} else if (entity instanceof PriseMedicamentRegime) {
			} else if (entity instanceof Medicament) {
			} else if (entity instanceof Intrant) {
			} else if (entity instanceof Formation) {
			} else if (entity instanceof CandidatureFormation) {
			} else if (entity instanceof Qualification) {
			} else if (entity instanceof Tutoriel) {
				Binary audioT = ((Tutoriel) entity).getAudioT();
				if (audioT != null && audioT.getUploadDate().after(lastDate)) {
					result.add(audioT);
				}
				Binary videoT = ((Tutoriel) entity).getVideoT();
				if (videoT != null && videoT.getUploadDate().after(lastDate)) {
					result.add(videoT);
				}
				Binary textT = ((Tutoriel) entity).getTextT();
				if (textT != null && textT.getUploadDate().after(lastDate)) {
					result.add(textT);
				}
			} else if (entity instanceof SmsPredefini) {
			} else if (entity instanceof OutBox) {
			} else if (entity instanceof Utilisateur) {
			} else if (entity instanceof DynamicFieldInstance) {
				DynamicFieldInstance instance = (DynamicFieldInstance) entity;
				if (instance.getFieldValue() != null
						&& ("bin".equals(instance.getFieldTemplate()
								.getFieldType()) || "img".equals(instance
								.getFieldTemplate().getFieldType()))) {
					Binary binary = binaryHandler.loadUploaded(
							instance.getFieldValue(), lastDate, null);
					if (binary != null) {
						result.add(binary);
					}
				}
			}
		}
		return result;
	}

	public List<Binary> getAssociatedBinariesModified(List<ImogBean> entities,
			Date lastDate) {
		List<Binary> result = new Vector<Binary>();
		for (ImogBean entity : entities) {
			if (entity instanceof Patient) {
			} else if (entity instanceof CasIndex) {
			} else if (entity instanceof CasTuberculose) {
			} else if (entity instanceof ExamenSerologie) {
			} else if (entity instanceof ExamenBiologique) {
			} else if (entity instanceof ExamenMicroscopie) {
			} else if (entity instanceof ExamenATB) {
			} else if (entity instanceof PriseMedicamenteuse) {
			} else if (entity instanceof RendezVous) {
			} else if (entity instanceof TransfertReference) {
			} else if (entity instanceof Lot) {
			} else if (entity instanceof HorsUsage) {
			} else if (entity instanceof EntreeLot) {
			} else if (entity instanceof SortieLot) {
			} else if (entity instanceof Commande) {
			} else if (entity instanceof DetailCommandeMedicament) {
			} else if (entity instanceof DetailCommandeIntrant) {
			} else if (entity instanceof Reception) {
			} else if (entity instanceof DetailReceptionMedicament) {
			} else if (entity instanceof DetailReceptionIntrant) {
			} else if (entity instanceof Ravitaillement) {
			} else if (entity instanceof DetailRavitaillement) {
			} else if (entity instanceof Inventaire) {
			} else if (entity instanceof DetailInventaire) {
			} else if (entity instanceof Personnel) {
			} else if (entity instanceof DepartPersonnel) {
			} else if (entity instanceof ArriveePersonnel) {
			} else if (entity instanceof Region) {
			} else if (entity instanceof DistrictSante) {
			} else if (entity instanceof CentreDiagTrait) {
			} else if (entity instanceof LaboratoireReference) {
			} else if (entity instanceof LieuDit) {
			} else if (entity instanceof Regime) {
			} else if (entity instanceof PriseMedicamentRegime) {
			} else if (entity instanceof Medicament) {
			} else if (entity instanceof Intrant) {
			} else if (entity instanceof Formation) {
			} else if (entity instanceof CandidatureFormation) {
			} else if (entity instanceof Qualification) {
			} else if (entity instanceof Tutoriel) {
				Binary audioT = ((Tutoriel) entity).getAudioT();
				if (audioT != null && audioT.getModified().after(lastDate)) {
					result.add(audioT);
				}
				Binary videoT = ((Tutoriel) entity).getVideoT();
				if (videoT != null && videoT.getModified().after(lastDate)) {
					result.add(videoT);
				}
				Binary textT = ((Tutoriel) entity).getTextT();
				if (textT != null && textT.getModified().after(lastDate)) {
					result.add(textT);
				}
			} else if (entity instanceof SmsPredefini) {
			} else if (entity instanceof OutBox) {
			} else if (entity instanceof Utilisateur) {
			} else if (entity instanceof DynamicFieldInstance) {
				DynamicFieldInstance instance = (DynamicFieldInstance) entity;
				if (instance.getFieldValue() != null
						&& ("bin".equals(instance.getFieldTemplate()
								.getFieldType()) || "img".equals(instance
								.getFieldTemplate().getFieldType()))) {
					Binary binary = binaryHandler.loadModified(
							instance.getFieldValue(), lastDate, null);
					if (binary != null) {
						result.add(binary);
					}
				}
			}
		}
		return result;
	}

}
