package org.imogene.epicam.handler;

import org.imogene.epicam.domain.entity.*;
import org.imogene.lib.common.binary.file.BinaryFile;
import org.imogene.lib.common.dynamicfields.DynamicFieldInstance;
import org.imogene.lib.common.dynamicfields.DynamicFieldTemplate;
import org.imogene.lib.common.entity.ImogBean;
import org.imogene.lib.common.filter.ClientFilter;
import org.imogene.lib.common.model.CardEntity;
import org.imogene.lib.common.model.FieldGroup;
import org.imogene.lib.common.profile.EntityProfile;
import org.imogene.lib.common.profile.FieldGroupProfile;
import org.imogene.lib.common.profile.Profile;
import org.imogene.lib.common.user.DefaultUser;
import org.imogene.lib.sync.binary.file.BinaryFileHandler;
import org.imogene.lib.sync.clientfilter.ClientFilterHandler;
import org.imogene.lib.sync.dynamicfields.DynamicFieldInstanceHandler;
import org.imogene.lib.sync.dynamicfields.DynamicFieldTemplateHandler;
import org.imogene.lib.sync.handler.CardEntityHandler;
import org.imogene.lib.sync.handler.DataHandlerManager;
import org.imogene.lib.sync.handler.DefaultUserHandler;
import org.imogene.lib.sync.handler.EntityProfileHandler;
import org.imogene.lib.sync.handler.FieldGroupHandler;
import org.imogene.lib.sync.handler.FieldGroupProfileHandler;
import org.imogene.lib.sync.handler.ImogBeanHandler;
import org.imogene.lib.sync.handler.ProfileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class DataHandlerManagerImpl implements DataHandlerManager {

	@Autowired
	@Qualifier(value = "patientHandler")
	private PatientHandler patientHandler;

	@Autowired
	@Qualifier(value = "casIndexHandler")
	private CasIndexHandler casIndexHandler;

	@Autowired
	@Qualifier(value = "casTuberculoseHandler")
	private CasTuberculoseHandler casTuberculoseHandler;

	@Autowired
	@Qualifier(value = "examenSerologieHandler")
	private ExamenSerologieHandler examenSerologieHandler;

	@Autowired
	@Qualifier(value = "examenBiologiqueHandler")
	private ExamenBiologiqueHandler examenBiologiqueHandler;

	@Autowired
	@Qualifier(value = "examenMicroscopieHandler")
	private ExamenMicroscopieHandler examenMicroscopieHandler;

	@Autowired
	@Qualifier(value = "examenATBHandler")
	private ExamenATBHandler examenATBHandler;

	@Autowired
	@Qualifier(value = "priseMedicamenteuseHandler")
	private PriseMedicamenteuseHandler priseMedicamenteuseHandler;

	@Autowired
	@Qualifier(value = "rendezVousHandler")
	private RendezVousHandler rendezVousHandler;

	@Autowired
	@Qualifier(value = "transfertReferenceHandler")
	private TransfertReferenceHandler transfertReferenceHandler;

	@Autowired
	@Qualifier(value = "lotHandler")
	private LotHandler lotHandler;

	@Autowired
	@Qualifier(value = "horsUsageHandler")
	private HorsUsageHandler horsUsageHandler;

	@Autowired
	@Qualifier(value = "entreeLotHandler")
	private EntreeLotHandler entreeLotHandler;

	@Autowired
	@Qualifier(value = "sortieLotHandler")
	private SortieLotHandler sortieLotHandler;

	@Autowired
	@Qualifier(value = "commandeHandler")
	private CommandeHandler commandeHandler;

	@Autowired
	@Qualifier(value = "detailCommandeMedicamentHandler")
	private DetailCommandeMedicamentHandler detailCommandeMedicamentHandler;

	@Autowired
	@Qualifier(value = "detailCommandeIntrantHandler")
	private DetailCommandeIntrantHandler detailCommandeIntrantHandler;

	@Autowired
	@Qualifier(value = "receptionHandler")
	private ReceptionHandler receptionHandler;

	@Autowired
	@Qualifier(value = "detailReceptionMedicamentHandler")
	private DetailReceptionMedicamentHandler detailReceptionMedicamentHandler;

	@Autowired
	@Qualifier(value = "detailReceptionIntrantHandler")
	private DetailReceptionIntrantHandler detailReceptionIntrantHandler;

	@Autowired
	@Qualifier(value = "ravitaillementHandler")
	private RavitaillementHandler ravitaillementHandler;

	@Autowired
	@Qualifier(value = "detailRavitaillementHandler")
	private DetailRavitaillementHandler detailRavitaillementHandler;

	@Autowired
	@Qualifier(value = "inventaireHandler")
	private InventaireHandler inventaireHandler;

	@Autowired
	@Qualifier(value = "detailInventaireHandler")
	private DetailInventaireHandler detailInventaireHandler;

	@Autowired
	@Qualifier(value = "personnelHandler")
	private PersonnelHandler personnelHandler;

	@Autowired
	@Qualifier(value = "departPersonnelHandler")
	private DepartPersonnelHandler departPersonnelHandler;

	@Autowired
	@Qualifier(value = "arriveePersonnelHandler")
	private ArriveePersonnelHandler arriveePersonnelHandler;

	@Autowired
	@Qualifier(value = "regionHandler")
	private RegionHandler regionHandler;

	@Autowired
	@Qualifier(value = "districtSanteHandler")
	private DistrictSanteHandler districtSanteHandler;

	@Autowired
	@Qualifier(value = "centreDiagTraitHandler")
	private CentreDiagTraitHandler centreDiagTraitHandler;

	@Autowired
	@Qualifier(value = "laboratoireReferenceHandler")
	private LaboratoireReferenceHandler laboratoireReferenceHandler;

	@Autowired
	@Qualifier(value = "lieuDitHandler")
	private LieuDitHandler lieuDitHandler;

	@Autowired
	@Qualifier(value = "regimeHandler")
	private RegimeHandler regimeHandler;

	@Autowired
	@Qualifier(value = "priseMedicamentRegimeHandler")
	private PriseMedicamentRegimeHandler priseMedicamentRegimeHandler;

	@Autowired
	@Qualifier(value = "medicamentHandler")
	private MedicamentHandler medicamentHandler;

	@Autowired
	@Qualifier(value = "intrantHandler")
	private IntrantHandler intrantHandler;

	@Autowired
	@Qualifier(value = "formationHandler")
	private FormationHandler formationHandler;

	@Autowired
	@Qualifier(value = "candidatureFormationHandler")
	private CandidatureFormationHandler candidatureFormationHandler;

	@Autowired
	@Qualifier(value = "qualificationHandler")
	private QualificationHandler qualificationHandler;

	@Autowired
	@Qualifier(value = "tutorielHandler")
	private TutorielHandler tutorielHandler;

	@Autowired
	@Qualifier(value = "smsPredefiniHandler")
	private SmsPredefiniHandler smsPredefiniHandler;

	@Autowired
	@Qualifier(value = "outBoxHandler")
	private OutBoxHandler outBoxHandler;

	@Autowired
	@Qualifier(value = "utilisateurHandler")
	private UtilisateurHandler utilisateurHandler;

	@Autowired
	@Qualifier(value = "defaultUserHandler")
	private DefaultUserHandler defaultUserHandler;

	@Autowired
	@Qualifier(value = "binaryHandler")
	private BinaryFileHandler binaryHandler;

	@Autowired
	@Qualifier(value = "clientFilterHandler")
	private ClientFilterHandler clientFilterHandler;

	@Autowired
	@Qualifier(value = "dynamicFieldInstanceHandler")
	private DynamicFieldInstanceHandler dynamicFieldInstanceHandler;

	@Autowired
	@Qualifier(value = "dynamicFieldTemplateHandler")
	private DynamicFieldTemplateHandler dynamicFieldTemplateHandler;

	@Autowired
	@Qualifier(value = "profileHandler")
	private ProfileHandler profileHandler;

	@Autowired
	@Qualifier(value = "entityProfileHandler")
	private EntityProfileHandler entityProfileHandler;

	@Autowired
	@Qualifier(value = "fieldGroupProfileHandler")
	private FieldGroupProfileHandler fieldGroupProfileHandler;

	@Autowired
	@Qualifier(value = "cardEntityHandler")
	private CardEntityHandler cardEntityHandler;

	@Autowired
	@Qualifier(value = "fieldGroupHandler")
	private FieldGroupHandler fieldGroupHandler;

	@Override
	public ImogBeanHandler<? extends ImogBean> getHandler(Class<?> clazz) {
		if (DefaultUser.class.equals(clazz)) {
			return defaultUserHandler;
		} else if (BinaryFile.class.equals(clazz)) {
			return binaryHandler;
		} else if (ClientFilter.class.equals(clazz)) {
			return clientFilterHandler;
		} else if (DynamicFieldInstance.class.equals(clazz)) {
			return dynamicFieldInstanceHandler;
		} else if (DynamicFieldTemplate.class.equals(clazz)) {
			return dynamicFieldTemplateHandler;
		} else if (Profile.class.equals(clazz)) {
			return profileHandler;
		} else if (EntityProfile.class.equals(clazz)) {
			return entityProfileHandler;
		} else if (FieldGroupProfile.class.equals(clazz)) {
			return fieldGroupProfileHandler;
		} else if (CardEntity.class.equals(clazz)) {
			return cardEntityHandler;
		} else if (FieldGroup.class.equals(clazz)) {
			return fieldGroupHandler;
		} else if (Patient.class.equals(clazz)) {
			return patientHandler;
		} else if (CasIndex.class.equals(clazz)) {
			return casIndexHandler;
		} else if (CasTuberculose.class.equals(clazz)) {
			return casTuberculoseHandler;
		} else if (ExamenSerologie.class.equals(clazz)) {
			return examenSerologieHandler;
		} else if (ExamenBiologique.class.equals(clazz)) {
			return examenBiologiqueHandler;
		} else if (ExamenMicroscopie.class.equals(clazz)) {
			return examenMicroscopieHandler;
		} else if (ExamenATB.class.equals(clazz)) {
			return examenATBHandler;
		} else if (PriseMedicamenteuse.class.equals(clazz)) {
			return priseMedicamenteuseHandler;
		} else if (RendezVous.class.equals(clazz)) {
			return rendezVousHandler;
		} else if (TransfertReference.class.equals(clazz)) {
			return transfertReferenceHandler;
		} else if (Lot.class.equals(clazz)) {
			return lotHandler;
		} else if (HorsUsage.class.equals(clazz)) {
			return horsUsageHandler;
		} else if (EntreeLot.class.equals(clazz)) {
			return entreeLotHandler;
		} else if (SortieLot.class.equals(clazz)) {
			return sortieLotHandler;
		} else if (Commande.class.equals(clazz)) {
			return commandeHandler;
		} else if (DetailCommandeMedicament.class.equals(clazz)) {
			return detailCommandeMedicamentHandler;
		} else if (DetailCommandeIntrant.class.equals(clazz)) {
			return detailCommandeIntrantHandler;
		} else if (Reception.class.equals(clazz)) {
			return receptionHandler;
		} else if (DetailReceptionMedicament.class.equals(clazz)) {
			return detailReceptionMedicamentHandler;
		} else if (DetailReceptionIntrant.class.equals(clazz)) {
			return detailReceptionIntrantHandler;
		} else if (Ravitaillement.class.equals(clazz)) {
			return ravitaillementHandler;
		} else if (DetailRavitaillement.class.equals(clazz)) {
			return detailRavitaillementHandler;
		} else if (Inventaire.class.equals(clazz)) {
			return inventaireHandler;
		} else if (DetailInventaire.class.equals(clazz)) {
			return detailInventaireHandler;
		} else if (Personnel.class.equals(clazz)) {
			return personnelHandler;
		} else if (DepartPersonnel.class.equals(clazz)) {
			return departPersonnelHandler;
		} else if (ArriveePersonnel.class.equals(clazz)) {
			return arriveePersonnelHandler;
		} else if (Region.class.equals(clazz)) {
			return regionHandler;
		} else if (DistrictSante.class.equals(clazz)) {
			return districtSanteHandler;
		} else if (CentreDiagTrait.class.equals(clazz)) {
			return centreDiagTraitHandler;
		} else if (LaboratoireReference.class.equals(clazz)) {
			return laboratoireReferenceHandler;
		} else if (LieuDit.class.equals(clazz)) {
			return lieuDitHandler;
		} else if (Regime.class.equals(clazz)) {
			return regimeHandler;
		} else if (PriseMedicamentRegime.class.equals(clazz)) {
			return priseMedicamentRegimeHandler;
		} else if (Medicament.class.equals(clazz)) {
			return medicamentHandler;
		} else if (Intrant.class.equals(clazz)) {
			return intrantHandler;
		} else if (Formation.class.equals(clazz)) {
			return formationHandler;
		} else if (CandidatureFormation.class.equals(clazz)) {
			return candidatureFormationHandler;
		} else if (Qualification.class.equals(clazz)) {
			return qualificationHandler;
		} else if (Tutoriel.class.equals(clazz)) {
			return tutorielHandler;
		} else if (SmsPredefini.class.equals(clazz)) {
			return smsPredefiniHandler;
		} else if (OutBox.class.equals(clazz)) {
			return outBoxHandler;
		} else if (Utilisateur.class.equals(clazz)) {
			return utilisateurHandler;
		}
		return null;
	}

	@Override
	public ImogBeanHandler<? extends ImogBean> getHandler(String className) {
		try {
			Class<?> clazz = Class.forName(className);
			return getHandler(clazz);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
