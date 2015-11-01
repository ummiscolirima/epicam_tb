package org.imogene.epicam.client.ui.panel;

import java.util.ArrayList;
import java.util.List;

import org.imogene.admin.client.AdminIconConstants;
import org.imogene.admin.client.event.create.CreateCardEntityEvent;
import org.imogene.admin.client.event.create.CreateFieldGroupEvent;
import org.imogene.admin.client.event.create.CreateNotificationEvent;
import org.imogene.admin.client.event.create.CreateProfileEvent;
import org.imogene.admin.client.event.list.ListCardEntityEvent;
import org.imogene.admin.client.event.list.ListFieldGroupEvent;
import org.imogene.admin.client.event.list.ListNotificationEvent;
import org.imogene.admin.client.event.list.ListProfileEvent;
import org.imogene.admin.client.i18n.AdminNLS;
import org.imogene.web.client.dynamicfields.i18n.DynFieldsNLS;
import org.imogene.web.client.event.CreateDynamicFieldTemplateEvent;
import org.imogene.web.client.event.GoHomeEvent;
import org.imogene.web.client.event.ListDynamicFieldTemplateEvent;
import org.imogene.web.client.i18n.BaseNLS;
import org.imogene.web.client.ui.field.group.FieldGroupPanel;
import org.imogene.web.client.ui.panel.EntityPanel;
import org.imogene.web.client.util.ProfileUtil;
import org.imogene.web.client.util.LocalSession;
import org.imogene.web.client.util.TokenHelper;
import org.imogene.web.client.util.TokenHelper.EntityToken;
import org.imogene.epicam.client.ui.panel.SmsPanel;
import org.imogene.epicam.client.ui.panel.RapportPanel;
import org.imogene.epicam.client.event.view.ViewEpicamMapEvent;
import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.EpicamIconConstants;
import org.imogene.epicam.client.i18n.NLS;
import org.imogene.epicam.client.event.create.*;
import org.imogene.epicam.client.event.list.*;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * Home panel of the application
 * @author Medes-IMPS
 */
public class HomePanel extends Composite {

	private static int PATIENT_HLK = 0;
	private static int EXAM_HLK = 1;
	private static int MAP_HLK = 2;
	private static int ACMS_HLK = 3;
	private static int FORMATION_HLK = 4;
	private static int STOCK_HLK = 5;
	private static int ADMINISTRATION_HLK = 6;
	private static int MEDICAMENTS_HLK = 7;
	private static int RESRCHUM_HLK = 8;
	private static int HELP_HLK = 9;

	interface Binder extends UiBinder<Widget, HomePanel> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);
	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();

	private EventBus eventBus;

	@UiField
	StackPanel stackPanel;
	@UiField
	ScrollPanel scrollPanel;

	/* thema  Help */
	@UiField(provided = true)
	FieldGroupPanel temaHelp;
	@UiField(provided = true)
	HTML helpMessage;

	/* thema  Malade */
	@UiField(provided = true)
	FieldGroupPanel temaMalade;
	@UiField(provided = true)
	EntityPanel patient;
	@UiField(provided = true)
	EntityPanel casTuberculose;
	@UiField(provided = true)
	EntityPanel transfertReference;
	@UiField(provided = true)
	EntityPanel regime;

	/* thema  Examens */
	@UiField(provided = true)
	FieldGroupPanel temaExamens;
	@UiField(provided = true)
	EntityPanel examenATB;
	@UiField(provided = true)
	EntityPanel examenBiologique;
	@UiField(provided = true)
	EntityPanel examenMicroscopie;
	@UiField(provided = true)
	EntityPanel examenSerologie;

	/* thema  Stock */
	@UiField(provided = true)
	FieldGroupPanel temaStock;
	@UiField(provided = true)
	EntityPanel lot;
	@UiField(provided = true)
	EntityPanel commande;
	@UiField(provided = true)
	EntityPanel reception;
	@UiField(provided = true)
	EntityPanel ravitaillement;
	@UiField(provided = true)
	EntityPanel inventaire;
	@UiField(provided = true)
	EntityPanel horsUsage;
	@UiField(provided = true)
	EntityPanel entreeLot;
	@UiField(provided = true)
	EntityPanel sortieLot;

	/* thema  ACMS */
	@UiField(provided = true)
	FieldGroupPanel temaACMS;
	@UiField(provided = true)
	EntityPanel smsPredefini;
	@UiField(provided = true)
	EntityPanel outBox;

	@UiField(provided = false)
	SmsPanel envoiSms;

	/* thema  FormationContinue */
	@UiField(provided = true)
	FieldGroupPanel temaFormationContinue;
	@UiField(provided = true)
	EntityPanel formation;
	@UiField(provided = true)
	EntityPanel tutoriel;

	/* thema  Administration */
	@UiField(provided = true)
	FieldGroupPanel temaAdministration;
	@UiField(provided = true)
	EntityPanel region;
	@UiField(provided = true)
	EntityPanel districtSante;
	@UiField(provided = true)
	EntityPanel centreDiagTrait;
	@UiField(provided = true)
	EntityPanel laboratoireReference;
	@UiField(provided = true)
	EntityPanel lieuDit;

	@UiField(provided = false)
	RapportPanel rapport;

	/* thema  AdministrationMedicament */
	@UiField(provided = true)
	FieldGroupPanel temaAdministrationMedicament;
	@UiField(provided = true)
	EntityPanel medicament;
	@UiField(provided = true)
	EntityPanel intrant;

	/* thema  RH */
	@UiField(provided = true)
	FieldGroupPanel temaRH;
	@UiField(provided = true)
	EntityPanel personnel;
	@UiField(provided = true)
	EntityPanel departPersonnel;
	@UiField(provided = true)
	EntityPanel arriveePersonnel;
	@UiField(provided = true)
	EntityPanel utilisateur;
	@UiField(provided = true)
	EntityPanel qualification;

	@UiField(provided = true)
	HTML homeMessage;

	/**
	 * 
	 * @param eventBus
	 */
	public HomePanel(EventBus eventBus) {

		this.eventBus = eventBus;

		homeMessage = new HTML(BaseNLS.messages().homePanel_message());

		helpMessage = new HTML(BaseNLS.messages().help_content());
		//Specific help for each component
		//		patientHelp = new HTML(BaseNLS.messages().patient_help());
		//		examHelp = new HTML(BaseNLS.messages().exam_help());
		//		smsHelp = new HTML(BaseNLS.messages().sms_help());
		//		trainingHelp = new HTML(BaseNLS.messages().training_help());
		//		stockHelp = new HTML(BaseNLS.messages().stock_help());
		//		adminHelp = new HTML(BaseNLS.messages().admin_help());
		//		drugAdminHelp = new HTML(BaseNLS.messages().drug_admin_help());
		//		reportHelp = new HTML(BaseNLS.messages().report_help());
		//		userAdminHelp = new HTML(BaseNLS.messages().user_admin_help());

		/* thema  Malade */
		temaMalade = new FieldGroupPanel();
		temaMalade.setGroupTitle(NLS.constants().thema_malade());
		if (EpicamIconConstants.PATIENT_ICON != null)
			patient = new EntityPanel(NLS.constants().patient_name(),
					EpicamIconConstants.PATIENT_ICON);
		else
			patient = new EntityPanel(NLS.constants().patient_name());

		if (EpicamIconConstants.CASTUBERCULOSE_ICON != null)
			casTuberculose = new EntityPanel(NLS.constants()
					.casTuberculose_name(),
					EpicamIconConstants.CASTUBERCULOSE_ICON);
		else
			casTuberculose = new EntityPanel(NLS.constants()
					.casTuberculose_name());

		if (EpicamIconConstants.TRANSFERTREFERENCE_ICON != null)
			transfertReference = new EntityPanel(NLS.constants()
					.transfertReference_name(),
					EpicamIconConstants.TRANSFERTREFERENCE_ICON);
		else
			transfertReference = new EntityPanel(NLS.constants()
					.transfertReference_name());

		if (EpicamIconConstants.REGIME_ICON != null)
			regime = new EntityPanel(NLS.constants().regime_name(),
					EpicamIconConstants.REGIME_ICON);
		else
			regime = new EntityPanel(NLS.constants().regime_name());

		/* thema  Examens */
		temaExamens = new FieldGroupPanel();
		temaExamens.setGroupTitle(NLS.constants().thema_examens());
		if (EpicamIconConstants.EXAMENATB_ICON != null)
			examenATB = new EntityPanel(NLS.constants().examenATB_name(),
					EpicamIconConstants.EXAMENATB_ICON);
		else
			examenATB = new EntityPanel(NLS.constants().examenATB_name());

		if (EpicamIconConstants.EXAMENBIOLOGIQUE_ICON != null)
			examenBiologique = new EntityPanel(NLS.constants()
					.examenBiologique_name(),
					EpicamIconConstants.EXAMENBIOLOGIQUE_ICON);
		else
			examenBiologique = new EntityPanel(NLS.constants()
					.examenBiologique_name());

		if (EpicamIconConstants.EXAMENMICROSCOPIE_ICON != null)
			examenMicroscopie = new EntityPanel(NLS.constants()
					.examenMicroscopie_name(),
					EpicamIconConstants.EXAMENMICROSCOPIE_ICON);
		else
			examenMicroscopie = new EntityPanel(NLS.constants()
					.examenMicroscopie_name());

		if (EpicamIconConstants.EXAMENSEROLOGIE_ICON != null)
			examenSerologie = new EntityPanel(NLS.constants()
					.examenSerologie_name(),
					EpicamIconConstants.EXAMENSEROLOGIE_ICON);
		else
			examenSerologie = new EntityPanel(NLS.constants()
					.examenSerologie_name());

		/* thema  Stock */
		temaStock = new FieldGroupPanel();
		temaStock.setGroupTitle(NLS.constants().thema_stock());
		if (EpicamIconConstants.LOT_ICON != null)
			lot = new EntityPanel(NLS.constants().lot_name(),
					EpicamIconConstants.LOT_ICON);
		else
			lot = new EntityPanel(NLS.constants().lot_name());

		if (EpicamIconConstants.COMMANDE_ICON != null)
			commande = new EntityPanel(NLS.constants().commande_name(),
					EpicamIconConstants.COMMANDE_ICON);
		else
			commande = new EntityPanel(NLS.constants().commande_name());

		if (EpicamIconConstants.RECEPTION_ICON != null)
			reception = new EntityPanel(NLS.constants().reception_name(),
					EpicamIconConstants.RECEPTION_ICON);
		else
			reception = new EntityPanel(NLS.constants().reception_name());

		if (EpicamIconConstants.RAVITAILLEMENT_ICON != null)
			ravitaillement = new EntityPanel(NLS.constants()
					.ravitaillement_name(),
					EpicamIconConstants.RAVITAILLEMENT_ICON);
		else
			ravitaillement = new EntityPanel(NLS.constants()
					.ravitaillement_name());

		if (EpicamIconConstants.INVENTAIRE_ICON != null)
			inventaire = new EntityPanel(NLS.constants().inventaire_name(),
					EpicamIconConstants.INVENTAIRE_ICON);
		else
			inventaire = new EntityPanel(NLS.constants().inventaire_name());

		if (EpicamIconConstants.HORSUSAGE_ICON != null)
			horsUsage = new EntityPanel(NLS.constants().horsUsage_name(),
					EpicamIconConstants.HORSUSAGE_ICON);
		else
			horsUsage = new EntityPanel(NLS.constants().horsUsage_name());

		if (EpicamIconConstants.ENTREELOT_ICON != null)
			entreeLot = new EntityPanel(NLS.constants().entreeLot_name(),
					EpicamIconConstants.ENTREELOT_ICON);
		else
			entreeLot = new EntityPanel(NLS.constants().entreeLot_name());

		if (EpicamIconConstants.SORTIELOT_ICON != null)
			sortieLot = new EntityPanel(NLS.constants().sortieLot_name(),
					EpicamIconConstants.SORTIELOT_ICON);
		else
			sortieLot = new EntityPanel(NLS.constants().sortieLot_name());

		/* thema  ACMS */
		temaACMS = new FieldGroupPanel();
		temaACMS.setGroupTitle(NLS.constants().thema_aCMS());
		if (EpicamIconConstants.SMSPREDEFINI_ICON != null)
			smsPredefini = new EntityPanel(NLS.constants().smsPredefini_name(),
					EpicamIconConstants.SMSPREDEFINI_ICON);
		else
			smsPredefini = new EntityPanel(NLS.constants().smsPredefini_name());

		if (EpicamIconConstants.OUTBOX_ICON != null)
			outBox = new EntityPanel(NLS.constants().outBox_name(),
					EpicamIconConstants.OUTBOX_ICON);
		else
			outBox = new EntityPanel(NLS.constants().outBox_name());

		/* thema  FormationContinue */
		temaFormationContinue = new FieldGroupPanel();
		temaFormationContinue.setGroupTitle(NLS.constants()
				.thema_formationContinue());
		if (EpicamIconConstants.FORMATION_ICON != null)
			formation = new EntityPanel(NLS.constants().formation_name(),
					EpicamIconConstants.FORMATION_ICON);
		else
			formation = new EntityPanel(NLS.constants().formation_name());

		if (EpicamIconConstants.TUTORIEL_ICON != null)
			tutoriel = new EntityPanel(NLS.constants().tutoriel_name(),
					EpicamIconConstants.TUTORIEL_ICON);
		else
			tutoriel = new EntityPanel(NLS.constants().tutoriel_name());

		/* thema  Administration */
		temaAdministration = new FieldGroupPanel();
		temaAdministration
				.setGroupTitle(NLS.constants().thema_administration());
		if (EpicamIconConstants.REGION_ICON != null)
			region = new EntityPanel(NLS.constants().region_name(),
					EpicamIconConstants.REGION_ICON);
		else
			region = new EntityPanel(NLS.constants().region_name());

		if (EpicamIconConstants.DISTRICTSANTE_ICON != null)
			districtSante = new EntityPanel(NLS.constants()
					.districtSante_name(),
					EpicamIconConstants.DISTRICTSANTE_ICON);
		else
			districtSante = new EntityPanel(NLS.constants()
					.districtSante_name());

		if (EpicamIconConstants.CENTREDIAGTRAIT_ICON != null)
			centreDiagTrait = new EntityPanel(NLS.constants()
					.centreDiagTrait_name(),
					EpicamIconConstants.CENTREDIAGTRAIT_ICON);
		else
			centreDiagTrait = new EntityPanel(NLS.constants()
					.centreDiagTrait_name());

		if (EpicamIconConstants.LABORATOIREREFERENCE_ICON != null)
			laboratoireReference = new EntityPanel(NLS.constants()
					.laboratoireReference_name(),
					EpicamIconConstants.LABORATOIREREFERENCE_ICON);
		else
			laboratoireReference = new EntityPanel(NLS.constants()
					.laboratoireReference_name());

		if (EpicamIconConstants.LIEUDIT_ICON != null)
			lieuDit = new EntityPanel(NLS.constants().lieuDit_name(),
					EpicamIconConstants.LIEUDIT_ICON);
		else
			lieuDit = new EntityPanel(NLS.constants().lieuDit_name());

		/* thema  AdministrationMedicament */
		temaAdministrationMedicament = new FieldGroupPanel();
		temaAdministrationMedicament.setGroupTitle(NLS.constants()
				.thema_administrationMedicament());
		if (EpicamIconConstants.MEDICAMENT_ICON != null)
			medicament = new EntityPanel(NLS.constants().medicament_name(),
					EpicamIconConstants.MEDICAMENT_ICON);
		else
			medicament = new EntityPanel(NLS.constants().medicament_name());

		if (EpicamIconConstants.INTRANT_ICON != null)
			intrant = new EntityPanel(NLS.constants().intrant_name(),
					EpicamIconConstants.INTRANT_ICON);
		else
			intrant = new EntityPanel(NLS.constants().intrant_name());

		/* thema  RH */
		temaRH = new FieldGroupPanel();
		temaRH.setGroupTitle(NLS.constants().thema_rH());
		if (EpicamIconConstants.PERSONNEL_ICON != null)
			personnel = new EntityPanel(NLS.constants().personnel_name(),
					EpicamIconConstants.PERSONNEL_ICON);
		else
			personnel = new EntityPanel(NLS.constants().personnel_name());

		if (EpicamIconConstants.DEPARTPERSONNEL_ICON != null)
			departPersonnel = new EntityPanel(NLS.constants()
					.departPersonnel_name(),
					EpicamIconConstants.DEPARTPERSONNEL_ICON);
		else
			departPersonnel = new EntityPanel(NLS.constants()
					.departPersonnel_name());

		if (EpicamIconConstants.ARRIVEEPERSONNEL_ICON != null)
			arriveePersonnel = new EntityPanel(NLS.constants()
					.arriveePersonnel_name(),
					EpicamIconConstants.ARRIVEEPERSONNEL_ICON);
		else
			arriveePersonnel = new EntityPanel(NLS.constants()
					.arriveePersonnel_name());

		if (EpicamIconConstants.UTILISATEUR_ICON != null)
			utilisateur = new EntityPanel(NLS.constants().utilisateur_name(),
					EpicamIconConstants.UTILISATEUR_ICON);
		else
			utilisateur = new EntityPanel(NLS.constants().utilisateur_name());

		if (EpicamIconConstants.QUALIFICATION_ICON != null)
			qualification = new EntityPanel(NLS.constants()
					.qualification_name(),
					EpicamIconConstants.QUALIFICATION_ICON);
		else
			qualification = new EntityPanel(NLS.constants()
					.qualification_name());

		/* thema  Help */
		temaHelp = new FieldGroupPanel();
		temaHelp.setGroupTitle(BaseNLS.messages().thema_Help());
		initWidget(BINDER.createAndBindUi(this));

		configMenus();
		setVisibility();

		int height = Window.getClientHeight();
		setPanelContentHeight(height - height / 5);
		openStackContent(LocalSession.get().getSelectedHlk());
	}

	/**
	 * 
	 * @param selection
	 */
	private void openStackContent(int selection) {

		if (selection == EXAM_HLK) {
			onLinkExamen(null);
			stackPanel.showStack(0);
		} else if (selection == MAP_HLK) {
			onLinkMap(null);
			stackPanel.showStack(0);
		} else if (selection == ACMS_HLK) {
			onLinkAcms(null);
			stackPanel.showStack(1);
		} else if (selection == FORMATION_HLK) {
			onLinkFormation(null);
			stackPanel.showStack(1);
		} else if (selection == STOCK_HLK) {
			onLinkStock(null);
			stackPanel.showStack(2);
		} else if (selection == ADMINISTRATION_HLK) {
			onLinkAdministration(null);
			stackPanel.showStack(3);
		} else if (selection == MEDICAMENTS_HLK) {
			onLinkAdministrationMedicament(null);
			stackPanel.showStack(3);
		} else if (selection == RESRCHUM_HLK) {
			onLinkResourcesHumaines(null);
			stackPanel.showStack(3);
		}//Show help stack
		else if (selection == HELP_HLK) {
			onLinkHelp(null);
			stackPanel.showStack(9);
		} else {
			onLinkPatient(null);
			stackPanel.showStack(0);
		}
	}

	/**
	 * Configures the menu buttons
	 */
	private void configMenus() {

		/* rapport menu */
		if (AccessManager.canCreateRapport()) {
			rapport.setCreateClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					History.newItem(TokenHelper.TK_NEW + "/rapport/", true);
				}
			});
		} else
			rapport.setCreateClickHandler(null);

		/* EnvoiSMS menu */
		if (AccessManager.canCreateEnvoiSMS()) {
			envoiSms.setCreateClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					History.newItem(TokenHelper.TK_NEW + "/envoiSms/", true);
				}
			});
		} else {
			envoiSms.setCreateClickHandler(null);
		}

		if (AccessManager.canCreateEnvoiSMS()) {
			envoiSms.setListClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/outbox/", true);
				}
			});
		} else {
			envoiSms.setListClickHandler(null);
		}
		/* Patient Menu */

		if (AccessManager.canCreatePatient() && AccessManager.canEditPatient()) {
			patient.setCreateClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					History.newItem(TokenHelper.TK_NEW + "/patient/", true);
				}
			});
		} else
			patient.setCreateClickHandler(null);

		if (AccessManager.canDirectAccessPatient()
				&& AccessManager.canReadPatient()) {
			patient.setListClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/patient/", true);
				}
			});
			patient.setSearchClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					String searchValue = patient.getSearchValue();
					if (searchValue != null && !searchValue.isEmpty())
						History.newItem(TokenHelper.TK_LIST + "/patient/"
								+ searchValue, true);
				}
			});
		} else {
			patient.setListClickHandler(null);
			patient.setSearchClickHandler(null);
		}

		/* CasTuberculose Menu */

		if (AccessManager.canCreateCasTuberculose()
				&& AccessManager.canEditCasTuberculose()) {
			casTuberculose.setCreateClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					History.newItem(TokenHelper.TK_NEW + "/castuberculose/",
							true);
				}
			});
		} else
			casTuberculose.setCreateClickHandler(null);

		if (AccessManager.canDirectAccessCasTuberculose()
				&& AccessManager.canReadCasTuberculose()) {
			casTuberculose.setListClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/castuberculose/",
							true);
				}
			});
			casTuberculose.setSearchClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					String searchValue = casTuberculose.getSearchValue();
					if (searchValue != null && !searchValue.isEmpty())
						History.newItem(TokenHelper.TK_LIST
								+ "/castuberculose/" + searchValue, true);
				}
			});
		} else {
			casTuberculose.setListClickHandler(null);
			casTuberculose.setSearchClickHandler(null);
		}

		/* TransfertReference Menu */

		if (AccessManager.canCreateTransfertReference()
				&& AccessManager.canEditTransfertReference()) {
			transfertReference.setCreateClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					History.newItem(
							TokenHelper.TK_NEW + "/transfertreference/", true);
				}
			});
		} else
			transfertReference.setCreateClickHandler(null);

		if (AccessManager.canDirectAccessTransfertReference()
				&& AccessManager.canReadTransfertReference()) {
			transfertReference.setListClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST
							+ "/transfertreference/", true);
				}
			});
			transfertReference.setSearchClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					String searchValue = transfertReference.getSearchValue();
					if (searchValue != null && !searchValue.isEmpty())
						History.newItem(TokenHelper.TK_LIST
								+ "/transfertreference/" + searchValue, true);
				}
			});
		} else {
			transfertReference.setListClickHandler(null);
			transfertReference.setSearchClickHandler(null);
		}

		/* Regime Menu */

		if (AccessManager.canCreateRegime() && AccessManager.canEditRegime()) {
			regime.setCreateClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					History.newItem(TokenHelper.TK_NEW + "/regime/", true);
				}
			});
		} else
			regime.setCreateClickHandler(null);

		if (AccessManager.canDirectAccessRegime()
				&& AccessManager.canReadRegime()) {
			regime.setListClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/regime/", true);
				}
			});
			regime.setSearchClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					String searchValue = regime.getSearchValue();
					if (searchValue != null && !searchValue.isEmpty())
						History.newItem(TokenHelper.TK_LIST + "/regime/"
								+ searchValue, true);
				}
			});
		} else {
			regime.setListClickHandler(null);
			regime.setSearchClickHandler(null);
		}

		/* ExamenATB Menu */

		if (AccessManager.canCreateExamenATB()
				&& AccessManager.canEditExamenATB()) {
			examenATB.setCreateClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					History.newItem(TokenHelper.TK_NEW + "/examenatb/", true);
				}
			});
		} else
			examenATB.setCreateClickHandler(null);

		if (AccessManager.canDirectAccessExamenATB()
				&& AccessManager.canReadExamenATB()) {
			examenATB.setListClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/examenatb/", true);
				}
			});
			examenATB.setSearchClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					String searchValue = examenATB.getSearchValue();
					if (searchValue != null && !searchValue.isEmpty())
						History.newItem(TokenHelper.TK_LIST + "/examenatb/"
								+ searchValue, true);
				}
			});
		} else {
			examenATB.setListClickHandler(null);
			examenATB.setSearchClickHandler(null);
		}

		/* ExamenBiologique Menu */

		if (AccessManager.canCreateExamenBiologique()
				&& AccessManager.canEditExamenBiologique()) {
			examenBiologique.setCreateClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					History.newItem(TokenHelper.TK_NEW + "/examenbiologique/",
							true);
				}
			});
		} else
			examenBiologique.setCreateClickHandler(null);

		if (AccessManager.canDirectAccessExamenBiologique()
				&& AccessManager.canReadExamenBiologique()) {
			examenBiologique.setListClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/examenbiologique/",
							true);
				}
			});
			examenBiologique.setSearchClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					String searchValue = examenBiologique.getSearchValue();
					if (searchValue != null && !searchValue.isEmpty())
						History.newItem(TokenHelper.TK_LIST
								+ "/examenbiologique/" + searchValue, true);
				}
			});
		} else {
			examenBiologique.setListClickHandler(null);
			examenBiologique.setSearchClickHandler(null);
		}

		/* ExamenMicroscopie Menu */

		if (AccessManager.canCreateExamenMicroscopie()
				&& AccessManager.canEditExamenMicroscopie()) {
			examenMicroscopie.setCreateClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					History.newItem(TokenHelper.TK_NEW + "/examenmicroscopie/",
							true);
				}
			});
		} else
			examenMicroscopie.setCreateClickHandler(null);

		if (AccessManager.canDirectAccessExamenMicroscopie()
				&& AccessManager.canReadExamenMicroscopie()) {
			examenMicroscopie.setListClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(
							TokenHelper.TK_LIST + "/examenmicroscopie/", true);
				}
			});
			examenMicroscopie.setSearchClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					String searchValue = examenMicroscopie.getSearchValue();
					if (searchValue != null && !searchValue.isEmpty())
						History.newItem(TokenHelper.TK_LIST
								+ "/examenmicroscopie/" + searchValue, true);
				}
			});
		} else {
			examenMicroscopie.setListClickHandler(null);
			examenMicroscopie.setSearchClickHandler(null);
		}

		/* ExamenSerologie Menu */

		if (AccessManager.canCreateExamenSerologie()
				&& AccessManager.canEditExamenSerologie()) {
			examenSerologie.setCreateClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					History.newItem(TokenHelper.TK_NEW + "/examenserologie/",
							true);
				}
			});
		} else
			examenSerologie.setCreateClickHandler(null);

		if (AccessManager.canDirectAccessExamenSerologie()
				&& AccessManager.canReadExamenSerologie()) {
			examenSerologie.setListClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/examenserologie/",
							true);
				}
			});
			examenSerologie.setSearchClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					String searchValue = examenSerologie.getSearchValue();
					if (searchValue != null && !searchValue.isEmpty())
						History.newItem(TokenHelper.TK_LIST
								+ "/examenserologie/" + searchValue, true);
				}
			});
		} else {
			examenSerologie.setListClickHandler(null);
			examenSerologie.setSearchClickHandler(null);
		}

		/* Lot Menu */

		if (AccessManager.canCreateLot() && AccessManager.canEditLot()) {
			lot.setCreateClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					History.newItem(TokenHelper.TK_NEW + "/lot/", true);
				}
			});
		} else
			lot.setCreateClickHandler(null);

		if (AccessManager.canDirectAccessLot() && AccessManager.canReadLot()) {
			lot.setListClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/lot/", true);
				}
			});
			lot.setSearchClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					String searchValue = lot.getSearchValue();
					if (searchValue != null && !searchValue.isEmpty())
						History.newItem(TokenHelper.TK_LIST + "/lot/"
								+ searchValue, true);
				}
			});
		} else {
			lot.setListClickHandler(null);
			lot.setSearchClickHandler(null);
		}

		/* Commande Menu */

		if (AccessManager.canCreateCommande()
				&& AccessManager.canEditCommande()) {
			commande.setCreateClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					History.newItem(TokenHelper.TK_NEW + "/commande/", true);
				}
			});
		} else
			commande.setCreateClickHandler(null);

		if (AccessManager.canDirectAccessCommande()
				&& AccessManager.canReadCommande()) {
			commande.setListClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/commande/", true);
				}
			});
			commande.setSearchClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					String searchValue = commande.getSearchValue();
					if (searchValue != null && !searchValue.isEmpty())
						History.newItem(TokenHelper.TK_LIST + "/commande/"
								+ searchValue, true);
				}
			});
		} else {
			commande.setListClickHandler(null);
			commande.setSearchClickHandler(null);
		}

		/* Reception Menu */

		if (AccessManager.canCreateReception()
				&& AccessManager.canEditReception()) {
			reception.setCreateClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					History.newItem(TokenHelper.TK_NEW + "/reception/", true);
				}
			});
		} else
			reception.setCreateClickHandler(null);

		if (AccessManager.canDirectAccessReception()
				&& AccessManager.canReadReception()) {
			reception.setListClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/reception/", true);
				}
			});
			reception.setSearchClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					String searchValue = reception.getSearchValue();
					if (searchValue != null && !searchValue.isEmpty())
						History.newItem(TokenHelper.TK_LIST + "/reception/"
								+ searchValue, true);
				}
			});
		} else {
			reception.setListClickHandler(null);
			reception.setSearchClickHandler(null);
		}

		/* Ravitaillement Menu */

		if (AccessManager.canCreateRavitaillement()
				&& AccessManager.canEditRavitaillement()) {
			ravitaillement.setCreateClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					History.newItem(TokenHelper.TK_NEW + "/ravitaillement/",
							true);
				}
			});
		} else
			ravitaillement.setCreateClickHandler(null);

		if (AccessManager.canDirectAccessRavitaillement()
				&& AccessManager.canReadRavitaillement()) {
			ravitaillement.setListClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/ravitaillement/",
							true);
				}
			});
			ravitaillement.setSearchClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					String searchValue = ravitaillement.getSearchValue();
					if (searchValue != null && !searchValue.isEmpty())
						History.newItem(TokenHelper.TK_LIST
								+ "/ravitaillement/" + searchValue, true);
				}
			});
		} else {
			ravitaillement.setListClickHandler(null);
			ravitaillement.setSearchClickHandler(null);
		}

		/* Inventaire Menu */

		if (AccessManager.canCreateInventaire()
				&& AccessManager.canEditInventaire()) {
			inventaire.setCreateClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					History.newItem(TokenHelper.TK_NEW + "/inventaire/", true);
				}
			});
		} else
			inventaire.setCreateClickHandler(null);

		if (AccessManager.canDirectAccessInventaire()
				&& AccessManager.canReadInventaire()) {
			inventaire.setListClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/inventaire/", true);
				}
			});
			inventaire.setSearchClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					String searchValue = inventaire.getSearchValue();
					if (searchValue != null && !searchValue.isEmpty())
						History.newItem(TokenHelper.TK_LIST + "/inventaire/"
								+ searchValue, true);
				}
			});
		} else {
			inventaire.setListClickHandler(null);
			inventaire.setSearchClickHandler(null);
		}

		/* HorsUsage Menu */

		if (AccessManager.canCreateHorsUsage()
				&& AccessManager.canEditHorsUsage()) {
			horsUsage.setCreateClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					History.newItem(TokenHelper.TK_NEW + "/horsusage/", true);
				}
			});
		} else
			horsUsage.setCreateClickHandler(null);

		if (AccessManager.canDirectAccessHorsUsage()
				&& AccessManager.canReadHorsUsage()) {
			horsUsage.setListClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/horsusage/", true);
				}
			});
			horsUsage.setSearchClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					String searchValue = horsUsage.getSearchValue();
					if (searchValue != null && !searchValue.isEmpty())
						History.newItem(TokenHelper.TK_LIST + "/horsusage/"
								+ searchValue, true);
				}
			});
		} else {
			horsUsage.setListClickHandler(null);
			horsUsage.setSearchClickHandler(null);
		}

		/* EntreeLot Menu */

		if (AccessManager.canCreateEntreeLot()
				&& AccessManager.canEditEntreeLot()) {
			entreeLot.setCreateClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					History.newItem(TokenHelper.TK_NEW + "/entreelot/", true);
				}
			});
		} else
			entreeLot.setCreateClickHandler(null);

		if (AccessManager.canDirectAccessEntreeLot()
				&& AccessManager.canReadEntreeLot()) {
			entreeLot.setListClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/entreelot/", true);
				}
			});
			entreeLot.setSearchClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					String searchValue = entreeLot.getSearchValue();
					if (searchValue != null && !searchValue.isEmpty())
						History.newItem(TokenHelper.TK_LIST + "/entreelot/"
								+ searchValue, true);
				}
			});
		} else {
			entreeLot.setListClickHandler(null);
			entreeLot.setSearchClickHandler(null);
		}

		/* SortieLot Menu */

		if (AccessManager.canCreateSortieLot()
				&& AccessManager.canEditSortieLot()) {
			sortieLot.setCreateClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					History.newItem(TokenHelper.TK_NEW + "/sortielot/", true);
				}
			});
		} else
			sortieLot.setCreateClickHandler(null);

		if (AccessManager.canDirectAccessSortieLot()
				&& AccessManager.canReadSortieLot()) {
			sortieLot.setListClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/sortielot/", true);
				}
			});
			sortieLot.setSearchClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					String searchValue = sortieLot.getSearchValue();
					if (searchValue != null && !searchValue.isEmpty())
						History.newItem(TokenHelper.TK_LIST + "/sortielot/"
								+ searchValue, true);
				}
			});
		} else {
			sortieLot.setListClickHandler(null);
			sortieLot.setSearchClickHandler(null);
		}

		/* SmsPredefini Menu */

		if (AccessManager.canCreateSmsPredefini()
				&& AccessManager.canEditSmsPredefini()) {
			smsPredefini.setCreateClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					History.newItem(TokenHelper.TK_NEW + "/smspredefini/", true);
				}
			});
		} else
			smsPredefini.setCreateClickHandler(null);

		if (AccessManager.canDirectAccessSmsPredefini()
				&& AccessManager.canReadSmsPredefini()) {
			smsPredefini.setListClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/smspredefini/",
							true);
				}
			});
			smsPredefini.setSearchClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					String searchValue = smsPredefini.getSearchValue();
					if (searchValue != null && !searchValue.isEmpty())
						History.newItem(TokenHelper.TK_LIST + "/smspredefini/"
								+ searchValue, true);
				}
			});
		} else {
			smsPredefini.setListClickHandler(null);
			smsPredefini.setSearchClickHandler(null);
		}

		/* OutBox Menu */

		if (AccessManager.canCreateOutBox() && AccessManager.canEditOutBox()) {
			outBox.setCreateClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					History.newItem(TokenHelper.TK_NEW + "/outbox/", true);
				}
			});
		} else
			outBox.setCreateClickHandler(null);

		if (AccessManager.canDirectAccessOutBox()
				&& AccessManager.canReadOutBox()) {
			outBox.setListClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/outbox/", true);
				}
			});
			outBox.setSearchClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					String searchValue = outBox.getSearchValue();
					if (searchValue != null && !searchValue.isEmpty())
						History.newItem(TokenHelper.TK_LIST + "/outbox/"
								+ searchValue, true);
				}
			});
		} else {
			outBox.setListClickHandler(null);
			outBox.setSearchClickHandler(null);
		}

		/* Formation Menu */

		if (AccessManager.canCreateFormation()
				&& AccessManager.canEditFormation()) {
			formation.setCreateClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					History.newItem(TokenHelper.TK_NEW + "/formation/", true);
				}
			});
		} else
			formation.setCreateClickHandler(null);

		if (AccessManager.canDirectAccessFormation()
				&& AccessManager.canReadFormation()) {
			formation.setListClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/formation/", true);
				}
			});
			formation.setSearchClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					String searchValue = formation.getSearchValue();
					if (searchValue != null && !searchValue.isEmpty())
						History.newItem(TokenHelper.TK_LIST + "/formation/"
								+ searchValue, true);
				}
			});
		} else {
			formation.setListClickHandler(null);
			formation.setSearchClickHandler(null);
		}

		/* Tutoriel Menu */

		if (AccessManager.canCreateTutoriel()
				&& AccessManager.canEditTutoriel()) {
			tutoriel.setCreateClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					History.newItem(TokenHelper.TK_NEW + "/tutoriel/", true);
				}
			});
		} else
			tutoriel.setCreateClickHandler(null);

		if (AccessManager.canDirectAccessTutoriel()
				&& AccessManager.canReadTutoriel()) {
			tutoriel.setListClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/tutoriel/", true);
				}
			});
			tutoriel.setSearchClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					String searchValue = tutoriel.getSearchValue();
					if (searchValue != null && !searchValue.isEmpty())
						History.newItem(TokenHelper.TK_LIST + "/tutoriel/"
								+ searchValue, true);
				}
			});
		} else {
			tutoriel.setListClickHandler(null);
			tutoriel.setSearchClickHandler(null);
		}

		/* Region Menu */

		if (AccessManager.canCreateRegion() && AccessManager.canEditRegion()) {
			region.setCreateClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					History.newItem(TokenHelper.TK_NEW + "/region/", true);
				}
			});
		} else
			region.setCreateClickHandler(null);

		if (AccessManager.canDirectAccessRegion()
				&& AccessManager.canReadRegion()) {
			region.setListClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/region/", true);
				}
			});
			region.setSearchClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					String searchValue = region.getSearchValue();
					if (searchValue != null && !searchValue.isEmpty())
						History.newItem(TokenHelper.TK_LIST + "/region/"
								+ searchValue, true);
				}
			});
		} else {
			region.setListClickHandler(null);
			region.setSearchClickHandler(null);
		}

		/* DistrictSante Menu */

		if (AccessManager.canCreateDistrictSante()
				&& AccessManager.canEditDistrictSante()) {
			districtSante.setCreateClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					History.newItem(TokenHelper.TK_NEW + "/districtsante/",
							true);
				}
			});
		} else
			districtSante.setCreateClickHandler(null);

		if (AccessManager.canDirectAccessDistrictSante()
				&& AccessManager.canReadDistrictSante()) {
			districtSante.setListClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/districtsante/",
							true);
				}
			});
			districtSante.setSearchClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					String searchValue = districtSante.getSearchValue();
					if (searchValue != null && !searchValue.isEmpty())
						History.newItem(TokenHelper.TK_LIST + "/districtsante/"
								+ searchValue, true);
				}
			});
		} else {
			districtSante.setListClickHandler(null);
			districtSante.setSearchClickHandler(null);
		}

		/* CentreDiagTrait Menu */

		if (AccessManager.canCreateCentreDiagTrait()
				&& AccessManager.canEditCentreDiagTrait()) {
			centreDiagTrait.setCreateClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					History.newItem(TokenHelper.TK_NEW + "/centrediagtrait/",
							true);
				}
			});
		} else
			centreDiagTrait.setCreateClickHandler(null);

		if (AccessManager.canDirectAccessCentreDiagTrait()
				&& AccessManager.canReadCentreDiagTrait()) {
			centreDiagTrait.setListClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/centrediagtrait/",
							true);
				}
			});
			centreDiagTrait.setSearchClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					String searchValue = centreDiagTrait.getSearchValue();
					if (searchValue != null && !searchValue.isEmpty())
						History.newItem(TokenHelper.TK_LIST
								+ "/centrediagtrait/" + searchValue, true);
				}
			});
		} else {
			centreDiagTrait.setListClickHandler(null);
			centreDiagTrait.setSearchClickHandler(null);
		}

		/* LaboratoireReference Menu */

		if (AccessManager.canCreateLaboratoireReference()
				&& AccessManager.canEditLaboratoireReference()) {
			laboratoireReference.setCreateClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					History.newItem(TokenHelper.TK_NEW
							+ "/laboratoirereference/", true);
				}
			});
		} else
			laboratoireReference.setCreateClickHandler(null);

		if (AccessManager.canDirectAccessLaboratoireReference()
				&& AccessManager.canReadLaboratoireReference()) {
			laboratoireReference.setListClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST
							+ "/laboratoirereference/", true);
				}
			});
			laboratoireReference.setSearchClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					String searchValue = laboratoireReference.getSearchValue();
					if (searchValue != null && !searchValue.isEmpty())
						History.newItem(TokenHelper.TK_LIST
								+ "/laboratoirereference/" + searchValue, true);
				}
			});
		} else {
			laboratoireReference.setListClickHandler(null);
			laboratoireReference.setSearchClickHandler(null);
		}

		/* LieuDit Menu */

		if (AccessManager.canCreateLieuDit() && AccessManager.canEditLieuDit()) {
			lieuDit.setCreateClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					History.newItem(TokenHelper.TK_NEW + "/lieudit/", true);
				}
			});
		} else
			lieuDit.setCreateClickHandler(null);

		if (AccessManager.canDirectAccessLieuDit()
				&& AccessManager.canReadLieuDit()) {
			lieuDit.setListClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/lieudit/", true);
				}
			});
			lieuDit.setSearchClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					String searchValue = lieuDit.getSearchValue();
					if (searchValue != null && !searchValue.isEmpty())
						History.newItem(TokenHelper.TK_LIST + "/lieudit/"
								+ searchValue, true);
				}
			});
		} else {
			lieuDit.setListClickHandler(null);
			lieuDit.setSearchClickHandler(null);
		}

		/* Medicament Menu */

		if (AccessManager.canCreateMedicament()
				&& AccessManager.canEditMedicament()) {
			medicament.setCreateClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					History.newItem(TokenHelper.TK_NEW + "/medicament/", true);
				}
			});
		} else
			medicament.setCreateClickHandler(null);

		if (AccessManager.canDirectAccessMedicament()
				&& AccessManager.canReadMedicament()) {
			medicament.setListClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/medicament/", true);
				}
			});
			medicament.setSearchClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					String searchValue = medicament.getSearchValue();
					if (searchValue != null && !searchValue.isEmpty())
						History.newItem(TokenHelper.TK_LIST + "/medicament/"
								+ searchValue, true);
				}
			});
		} else {
			medicament.setListClickHandler(null);
			medicament.setSearchClickHandler(null);
		}

		/* Intrant Menu */

		if (AccessManager.canCreateIntrant() && AccessManager.canEditIntrant()) {
			intrant.setCreateClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					History.newItem(TokenHelper.TK_NEW + "/intrant/", true);
				}
			});
		} else
			intrant.setCreateClickHandler(null);

		if (AccessManager.canDirectAccessIntrant()
				&& AccessManager.canReadIntrant()) {
			intrant.setListClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/intrant/", true);
				}
			});
			intrant.setSearchClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					String searchValue = intrant.getSearchValue();
					if (searchValue != null && !searchValue.isEmpty())
						History.newItem(TokenHelper.TK_LIST + "/intrant/"
								+ searchValue, true);
				}
			});
		} else {
			intrant.setListClickHandler(null);
			intrant.setSearchClickHandler(null);
		}

		/* Personnel Menu */

		if (AccessManager.canCreatePersonnel()
				&& AccessManager.canEditPersonnel()) {
			personnel.setCreateClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					History.newItem(TokenHelper.TK_NEW + "/personnel/", true);
				}
			});
		} else
			personnel.setCreateClickHandler(null);

		if (AccessManager.canDirectAccessPersonnel()
				&& AccessManager.canReadPersonnel()) {
			personnel.setListClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/personnel/", true);
				}
			});
			personnel.setSearchClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					String searchValue = personnel.getSearchValue();
					if (searchValue != null && !searchValue.isEmpty())
						History.newItem(TokenHelper.TK_LIST + "/personnel/"
								+ searchValue, true);
				}
			});
		} else {
			personnel.setListClickHandler(null);
			personnel.setSearchClickHandler(null);
		}

		/* DepartPersonnel Menu */

		if (AccessManager.canCreateDepartPersonnel()
				&& AccessManager.canEditDepartPersonnel()) {
			departPersonnel.setCreateClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					History.newItem(TokenHelper.TK_NEW + "/departpersonnel/",
							true);
				}
			});
		} else
			departPersonnel.setCreateClickHandler(null);

		if (AccessManager.canDirectAccessDepartPersonnel()
				&& AccessManager.canReadDepartPersonnel()) {
			departPersonnel.setListClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/departpersonnel/",
							true);
				}
			});
			departPersonnel.setSearchClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					String searchValue = departPersonnel.getSearchValue();
					if (searchValue != null && !searchValue.isEmpty())
						History.newItem(TokenHelper.TK_LIST
								+ "/departpersonnel/" + searchValue, true);
				}
			});
		} else {
			departPersonnel.setListClickHandler(null);
			departPersonnel.setSearchClickHandler(null);
		}

		/* ArriveePersonnel Menu */

		if (AccessManager.canCreateArriveePersonnel()
				&& AccessManager.canEditArriveePersonnel()) {
			arriveePersonnel.setCreateClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					History.newItem(TokenHelper.TK_NEW + "/arriveepersonnel/",
							true);
				}
			});
		} else
			arriveePersonnel.setCreateClickHandler(null);

		if (AccessManager.canDirectAccessArriveePersonnel()
				&& AccessManager.canReadArriveePersonnel()) {
			arriveePersonnel.setListClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/arriveepersonnel/",
							true);
				}
			});
			arriveePersonnel.setSearchClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					String searchValue = arriveePersonnel.getSearchValue();
					if (searchValue != null && !searchValue.isEmpty())
						History.newItem(TokenHelper.TK_LIST
								+ "/arriveepersonnel/" + searchValue, true);
				}
			});
		} else {
			arriveePersonnel.setListClickHandler(null);
			arriveePersonnel.setSearchClickHandler(null);
		}

		/* Utilisateur Menu */

		if (AccessManager.canCreateUtilisateur()
				&& AccessManager.canEditUtilisateur()) {
			utilisateur.setCreateClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					History.newItem(TokenHelper.TK_NEW + "/utilisateur/", true);
				}
			});
		} else
			utilisateur.setCreateClickHandler(null);

		if (AccessManager.canDirectAccessUtilisateur()
				&& AccessManager.canReadUtilisateur()) {
			utilisateur.setListClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/utilisateur/", true);
				}
			});
			utilisateur.setSearchClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					String searchValue = utilisateur.getSearchValue();
					if (searchValue != null && !searchValue.isEmpty())
						History.newItem(TokenHelper.TK_LIST + "/utilisateur/"
								+ searchValue, true);
				}
			});
		} else {
			utilisateur.setListClickHandler(null);
			utilisateur.setSearchClickHandler(null);
		}

		/* Qualification Menu */

		if (AccessManager.canCreateQualification()
				&& AccessManager.canEditQualification()) {
			qualification.setCreateClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					History.newItem(TokenHelper.TK_NEW + "/qualification/",
							true);
				}
			});
		} else
			qualification.setCreateClickHandler(null);

		if (AccessManager.canDirectAccessQualification()
				&& AccessManager.canReadQualification()) {
			qualification.setListClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/qualification/",
							true);
				}
			});
			qualification.setSearchClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					String searchValue = qualification.getSearchValue();
					if (searchValue != null && !searchValue.isEmpty())
						History.newItem(TokenHelper.TK_LIST + "/qualification/"
								+ searchValue, true);
				}
			});
		} else {
			qualification.setListClickHandler(null);
			qualification.setSearchClickHandler(null);
		}

	}

	/**
	 * Set the menu visibility
	 */
	private void setVisibility() {

		/* thema  Malade */
		boolean showMaladeTema = false;
		if (!AccessManager.canDirectAccessPatient()
				|| !AccessManager.canReadPatient())
			patient.setVisible(false);
		else
			showMaladeTema = true;
		if (!AccessManager.canDirectAccessCasTuberculose()
				|| !AccessManager.canReadCasTuberculose())
			casTuberculose.setVisible(false);
		else
			showMaladeTema = true;
		if (!AccessManager.canDirectAccessTransfertReference()
				|| !AccessManager.canReadTransfertReference())
			transfertReference.setVisible(false);
		else
			showMaladeTema = true;
		if (!AccessManager.canDirectAccessRegime()
				|| !AccessManager.canReadRegime())
			regime.setVisible(false);
		else
			showMaladeTema = true;
		if (!showMaladeTema)
			temaMalade.setVisible(false);

		/* thema  Examens */
		boolean showExamensTema = false;
		if (!AccessManager.canDirectAccessExamenATB()
				|| !AccessManager.canReadExamenATB())
			examenATB.setVisible(false);
		else
			showExamensTema = true;
		if (!AccessManager.canDirectAccessExamenBiologique()
				|| !AccessManager.canReadExamenBiologique())
			examenBiologique.setVisible(false);
		else
			showExamensTema = true;
		if (!AccessManager.canDirectAccessExamenMicroscopie()
				|| !AccessManager.canReadExamenMicroscopie())
			examenMicroscopie.setVisible(false);
		else
			showExamensTema = true;
		if (!AccessManager.canDirectAccessExamenSerologie()
				|| !AccessManager.canReadExamenSerologie())
			examenSerologie.setVisible(false);
		else
			showExamensTema = true;
		if (!showExamensTema)
			temaExamens.setVisible(false);

		/* thema  Stock */
		boolean showStockTema = false;
		if (!AccessManager.canDirectAccessLot() || !AccessManager.canReadLot())
			lot.setVisible(false);
		else
			showStockTema = true;
		if (!AccessManager.canDirectAccessCommande()
				|| !AccessManager.canReadCommande())
			commande.setVisible(false);
		else
			showStockTema = true;
		if (!AccessManager.canDirectAccessReception()
				|| !AccessManager.canReadReception())
			reception.setVisible(false);
		else
			showStockTema = true;
		if (!AccessManager.canDirectAccessRavitaillement()
				|| !AccessManager.canReadRavitaillement())
			ravitaillement.setVisible(false);
		else
			showStockTema = true;
		if (!AccessManager.canDirectAccessInventaire()
				|| !AccessManager.canReadInventaire())
			inventaire.setVisible(false);
		else
			showStockTema = true;
		if (!AccessManager.canDirectAccessHorsUsage()
				|| !AccessManager.canReadHorsUsage())
			horsUsage.setVisible(false);
		else
			showStockTema = true;
		if (!AccessManager.canDirectAccessEntreeLot()
				|| !AccessManager.canReadEntreeLot())
			entreeLot.setVisible(false);
		else
			showStockTema = true;
		if (!AccessManager.canDirectAccessSortieLot()
				|| !AccessManager.canReadSortieLot())
			sortieLot.setVisible(false);
		else
			showStockTema = true;
		if (!showStockTema)
			temaStock.setVisible(false);

		/* thema  ACMS */
		boolean showACMSTema = false;
		if (!AccessManager.canDirectAccessSmsPredefini()
				|| !AccessManager.canReadSmsPredefini())
			smsPredefini.setVisible(false);
		else
			showACMSTema = true;
		if (!AccessManager.canDirectAccessOutBox()
				|| !AccessManager.canReadOutBox())
			outBox.setVisible(false);
		else
			showACMSTema = true;
		if (!showACMSTema)
			temaACMS.setVisible(false);

		/* thema  FormationContinue */
		boolean showFormationContinueTema = false;
		if (!AccessManager.canDirectAccessFormation()
				|| !AccessManager.canReadFormation())
			formation.setVisible(false);
		else
			showFormationContinueTema = true;
		if (!AccessManager.canDirectAccessTutoriel()
				|| !AccessManager.canReadTutoriel())
			tutoriel.setVisible(false);
		else
			showFormationContinueTema = true;
		if (!showFormationContinueTema)
			temaFormationContinue.setVisible(false);

		/* thema  Administration */
		boolean showAdministrationTema = false;
		if (!AccessManager.canDirectAccessRegion()
				|| !AccessManager.canReadRegion())
			region.setVisible(false);
		else
			showAdministrationTema = true;
		if (!AccessManager.canDirectAccessDistrictSante()
				|| !AccessManager.canReadDistrictSante())
			districtSante.setVisible(false);
		else
			showAdministrationTema = true;
		if (!AccessManager.canDirectAccessCentreDiagTrait()
				|| !AccessManager.canReadCentreDiagTrait())
			centreDiagTrait.setVisible(false);
		else
			showAdministrationTema = true;
		if (!AccessManager.canDirectAccessLaboratoireReference()
				|| !AccessManager.canReadLaboratoireReference())
			laboratoireReference.setVisible(false);
		else
			showAdministrationTema = true;
		if (!AccessManager.canDirectAccessLieuDit()
				|| !AccessManager.canReadLieuDit())
			lieuDit.setVisible(false);
		else
			showAdministrationTema = true;
		if (!showAdministrationTema)
			temaAdministration.setVisible(false);

		/* thema  AdministrationMedicament */
		boolean showAdministrationMedicamentTema = false;
		if (!AccessManager.canDirectAccessMedicament()
				|| !AccessManager.canReadMedicament())
			medicament.setVisible(false);
		else
			showAdministrationMedicamentTema = true;
		if (!AccessManager.canDirectAccessIntrant()
				|| !AccessManager.canReadIntrant())
			intrant.setVisible(false);
		else
			showAdministrationMedicamentTema = true;
		if (!showAdministrationMedicamentTema)
			temaAdministrationMedicament.setVisible(false);

		/* thema  RH */
		boolean showRHTema = false;
		if (!AccessManager.canDirectAccessPersonnel()
				|| !AccessManager.canReadPersonnel())
			personnel.setVisible(false);
		else
			showRHTema = true;
		if (!AccessManager.canDirectAccessDepartPersonnel()
				|| !AccessManager.canReadDepartPersonnel())
			departPersonnel.setVisible(false);
		else
			showRHTema = true;
		if (!AccessManager.canDirectAccessArriveePersonnel()
				|| !AccessManager.canReadArriveePersonnel())
			arriveePersonnel.setVisible(false);
		else
			showRHTema = true;
		if (!AccessManager.canDirectAccessUtilisateur()
				|| !AccessManager.canReadUtilisateur())
			utilisateur.setVisible(false);
		else
			showRHTema = true;
		if (!AccessManager.canDirectAccessQualification()
				|| !AccessManager.canReadQualification())
			qualification.setVisible(false);
		else
			showRHTema = true;
		if (!showRHTema)
			temaRH.setVisible(false);

	}

	/**
	 * Sets the height of the panel that contents the entity menus
	 * @param height
	 */
	public void setPanelContentHeight(int height) {
		scrollPanel.getElement().getStyle().clearHeight();
		scrollPanel.getElement().getStyle()
				.setProperty("height", height + "px");
	}

	/**
	 * 
	 */
	private void setWindowHandler() {
		registrations.add(Window.addResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent arg0) {
				int height = Window.getClientHeight();
				setPanelContentHeight(height - height / 5);
			}
		}));
	}

	@Override
	protected void onLoad() {
		setWindowHandler();
		super.onLoad();
	}

	@Override
	protected void onUnload() {
		for (HandlerRegistration r : registrations)
			r.removeHandler();
		registrations.clear();
		super.onUnload();
	}

	@UiHandler("linkPatient")
	public void onLinkPatient(ClickEvent event) {
		temaMalade.setVisible(true);
		temaACMS.setVisible(false);
		temaAdministration.setVisible(false);
		temaAdministrationMedicament.setVisible(false);
		temaExamens.setVisible(false);
		temaFormationContinue.setVisible(false);
		temaRH.setVisible(false);
		temaStock.setVisible(false);
		temaHelp.setVisible(false);
		//Set help
		//		temaHelp.setvi
		//		helpMessage.setHTML((SafeHtml) new HTML(""));
		//		homeMessage.setHTML((SafeHtml) new HTML(""));
		//		patientHelp.setHTML((SafeHtml) new HTML(BaseNLS.messages().patient_help()));
		//		examHelp.setHTML((SafeHtml) new HTML(""));
		//		smsHelp.setHTML((SafeHtml) new HTML(""));
		//		trainingHelp.setHTML((SafeHtml) new HTML(""));
		//		stockHelp.setHTML((SafeHtml) new HTML(""));
		//		adminHelp.setHTML((SafeHtml) new HTML(""));
		//		drugAdminHelp.setHTML((SafeHtml) new HTML(""));
		//		reportHelp.setHTML((SafeHtml) new HTML(""));
		//		userAdminHelp.setHTML((SafeHtml) new HTML(""));
		LocalSession.get().setSelectedHlk(PATIENT_HLK);
	}

	@UiHandler("linkExamen")
	public void onLinkExamen(ClickEvent event) {
		temaMalade.setVisible(false);
		temaACMS.setVisible(false);
		temaAdministration.setVisible(false);
		temaAdministrationMedicament.setVisible(false);
		temaExamens.setVisible(true);
		temaFormationContinue.setVisible(false);
		temaRH.setVisible(false);
		temaStock.setVisible(false);
		temaHelp.setVisible(false);
		//Set help
		//		helpMessage.setHTML((SafeHtml) new HTML(""));
		//		homeMessage.setHTML((SafeHtml) new HTML(""));
		//		patientHelp.setHTML((SafeHtml) new HTML(""));
		//		examHelp.setHTML((SafeHtml) new HTML(BaseNLS.messages().exam_help()));
		//		smsHelp.setHTML((SafeHtml) new HTML(""));
		//		trainingHelp.setHTML((SafeHtml) new HTML(""));
		//		stockHelp.setHTML((SafeHtml) new HTML(""));
		//		adminHelp.setHTML((SafeHtml) new HTML(""));
		//		drugAdminHelp.setHTML((SafeHtml) new HTML(""));
		//		reportHelp.setHTML((SafeHtml) new HTML(""));
		//		userAdminHelp.setHTML((SafeHtml) new HTML(""));
		LocalSession.get().setSelectedHlk(EXAM_HLK);
	}

	@UiHandler("linkAcms")
	public void onLinkAcms(ClickEvent event) {
		temaMalade.setVisible(false);
		temaACMS.setVisible(true);
		temaAdministration.setVisible(false);
		temaAdministrationMedicament.setVisible(false);
		temaExamens.setVisible(false);
		temaFormationContinue.setVisible(false);
		temaRH.setVisible(false);
		temaStock.setVisible(false);
		temaHelp.setVisible(false);
		//Set help
		//		helpMessage.setHTML((SafeHtml) new HTML(BaseNLS.messages().help_content()));
		//		homeMessage.setHTML((SafeHtml) new HTML(""));
		//		patientHelp.setHTML((SafeHtml) new HTML(""));
		//		examHelp.setHTML((SafeHtml) new HTML(""));
		//		smsHelp.setHTML((SafeHtml) new HTML(BaseNLS.messages().sms_help()));
		//		trainingHelp.setHTML((SafeHtml) new HTML(""));
		//		stockHelp.setHTML((SafeHtml) new HTML(""));
		//		adminHelp.setHTML((SafeHtml) new HTML(""));
		//		drugAdminHelp.setHTML((SafeHtml) new HTML(""));
		//		reportHelp.setHTML((SafeHtml) new HTML(""));
		//		userAdminHelp.setHTML((SafeHtml) new HTML(""));
		LocalSession.get().setSelectedHlk(ACMS_HLK);
	}

	@UiHandler("linkFormation")
	public void onLinkFormation(ClickEvent event) {
		temaMalade.setVisible(false);
		temaACMS.setVisible(false);
		temaAdministration.setVisible(false);
		temaAdministrationMedicament.setVisible(false);
		temaExamens.setVisible(false);
		temaFormationContinue.setVisible(true);
		temaRH.setVisible(false);
		temaStock.setVisible(false);
		temaHelp.setVisible(false);
		//Set help
		//		helpMessage.setHTML((SafeHtml) new HTML(""));
		//		homeMessage.setHTML((SafeHtml) new HTML(""));
		//		patientHelp.setHTML((SafeHtml) new HTML(""));
		//		examHelp.setHTML((SafeHtml) new HTML(""));
		//		smsHelp.setHTML((SafeHtml) new HTML(""));
		//		trainingHelp.setHTML((SafeHtml) new HTML(BaseNLS.messages().training_help()));
		//		stockHelp.setHTML((SafeHtml) new HTML(""));
		//		adminHelp.setHTML((SafeHtml) new HTML(""));
		//		drugAdminHelp.setHTML((SafeHtml) new HTML(""));
		//		reportHelp.setHTML((SafeHtml) new HTML(""));
		//		userAdminHelp.setHTML((SafeHtml) new HTML(""));
		LocalSession.get().setSelectedHlk(FORMATION_HLK);
	}

	@UiHandler("linkStock")
	public void onLinkStock(ClickEvent event) {
		temaMalade.setVisible(false);
		temaACMS.setVisible(false);
		temaAdministration.setVisible(false);
		temaAdministrationMedicament.setVisible(false);
		temaExamens.setVisible(false);
		temaFormationContinue.setVisible(false);
		temaRH.setVisible(false);
		temaStock.setVisible(true);
		temaHelp.setVisible(false);
		//Set help
		//		helpMessage.setHTML((SafeHtml) new HTML(""));
		//		homeMessage.setHTML((SafeHtml) new HTML(""));
		//		patientHelp.setHTML((SafeHtml) new HTML(""));
		//		examHelp.setHTML((SafeHtml) new HTML(""));
		//		smsHelp.setHTML((SafeHtml) new HTML(""));
		//		trainingHelp.setHTML((SafeHtml) new HTML(""));
		//		stockHelp.setHTML((SafeHtml) new HTML(BaseNLS.messages().stock_help()));
		//		adminHelp.setHTML((SafeHtml) new HTML(""));
		//		drugAdminHelp.setHTML((SafeHtml) new HTML(""));
		//		reportHelp.setHTML((SafeHtml) new HTML(""));
		//		userAdminHelp.setHTML((SafeHtml) new HTML(""));
		LocalSession.get().setSelectedHlk(STOCK_HLK);
	}

	@UiHandler("linkAdministration")
	public void onLinkAdministration(ClickEvent event) {
		temaMalade.setVisible(false);
		temaACMS.setVisible(false);
		temaAdministration.setVisible(true);
		temaAdministrationMedicament.setVisible(false);
		temaExamens.setVisible(false);
		temaFormationContinue.setVisible(false);
		temaRH.setVisible(false);
		temaStock.setVisible(false);
		temaHelp.setVisible(false);
		//Set help
		//		helpMessage.setHTML((SafeHtml) new HTML(""));
		//		homeMessage.setHTML((SafeHtml) new HTML(""));
		//		patientHelp.setHTML((SafeHtml) new HTML(""));
		//		examHelp.setHTML((SafeHtml) new HTML(""));
		//		smsHelp.setHTML((SafeHtml) new HTML(""));
		//		trainingHelp.setHTML((SafeHtml) new HTML(""));
		//		stockHelp.setHTML((SafeHtml) new HTML(""));
		//		adminHelp.setHTML((SafeHtml) new HTML(BaseNLS.messages().admin_help()));
		//		drugAdminHelp.setHTML((SafeHtml) new HTML(""));
		//		reportHelp.setHTML((SafeHtml) new HTML(""));
		//		userAdminHelp.setHTML((SafeHtml) new HTML(""));
		LocalSession.get().setSelectedHlk(ADMINISTRATION_HLK);
	}

	@UiHandler("linkAdministrationMedicament")
	public void onLinkAdministrationMedicament(ClickEvent event) {
		temaMalade.setVisible(false);
		temaACMS.setVisible(false);
		temaAdministration.setVisible(false);
		temaAdministrationMedicament.setVisible(true);
		temaExamens.setVisible(false);
		temaFormationContinue.setVisible(false);
		temaRH.setVisible(false);
		temaStock.setVisible(false);
		temaHelp.setVisible(false);
		//Set help
		//		helpMessage.setHTML((SafeHtml) new HTML(""));
		//		homeMessage.setHTML((SafeHtml) new HTML(""));
		//		patientHelp.setHTML((SafeHtml) new HTML(""));
		//		examHelp.setHTML((SafeHtml) new HTML(""));
		//		smsHelp.setHTML((SafeHtml) new HTML(""));
		//		trainingHelp.setHTML((SafeHtml) new HTML(""));
		//		stockHelp.setHTML((SafeHtml) new HTML(""));
		//		adminHelp.setHTML((SafeHtml) new HTML(""));
		//		drugAdminHelp.setHTML((SafeHtml) new HTML(BaseNLS.messages().drug_admin_help()));
		//		reportHelp.setHTML((SafeHtml) new HTML(""));
		//		userAdminHelp.setHTML((SafeHtml) new HTML(""));
		LocalSession.get().setSelectedHlk(MEDICAMENTS_HLK);
	}

	@UiHandler("linkResourcesHumaines")
	public void onLinkResourcesHumaines(ClickEvent event) {
		temaMalade.setVisible(false);
		temaACMS.setVisible(false);
		temaAdministration.setVisible(false);
		temaAdministrationMedicament.setVisible(false);
		temaExamens.setVisible(false);
		temaFormationContinue.setVisible(false);
		temaRH.setVisible(true);
		temaStock.setVisible(false);
		temaHelp.setVisible(false);
		//Set help
		//		helpMessage.setHTML((SafeHtml) new HTML(""));
		//		homeMessage.setHTML((SafeHtml) new HTML(""));
		//		patientHelp.setHTML((SafeHtml) new HTML(""));
		//		examHelp.setHTML((SafeHtml) new HTML(""));
		//		smsHelp.setHTML((SafeHtml) new HTML(""));
		//		trainingHelp.setHTML((SafeHtml) new HTML(""));
		//		stockHelp.setHTML((SafeHtml) new HTML(""));
		//		adminHelp.setHTML((SafeHtml) new HTML(""));
		//		drugAdminHelp.setHTML((SafeHtml) new HTML(""));
		//		reportHelp.setHTML((SafeHtml) new HTML(BaseNLS.messages().report_help()));
		//		userAdminHelp.setHTML((SafeHtml) new HTML(""));
		LocalSession.get().setSelectedHlk(RESRCHUM_HLK);
	}

	@UiHandler("linkMap")
	public void onLinkMap(ClickEvent event) {
		eventBus.fireEvent(new ViewEpicamMapEvent());
		LocalSession.get().setSelectedHlk(MAP_HLK);
	}
	@UiHandler("linkHelp")
	public void onLinkHelp(ClickEvent event) {
		temaMalade.setVisible(false);
		temaACMS.setVisible(false);
		temaAdministration.setVisible(false);
		temaAdministrationMedicament.setVisible(false);
		temaExamens.setVisible(false);
		temaFormationContinue.setVisible(false);
		temaRH.setVisible(false);
		temaStock.setVisible(false);
		temaHelp.setVisible(true);
		//Set help
		//		homeMessage = new HTML(BaseNLS.messages().homePanel_message());
		//		helpMessage.setHTML((SafeHtml) new HTML(BaseNLS.messages().help_content()));
		//		homeMessage.setHTML((SafeHtml) new HTML(""));
		//		patientHelp.setHTML((SafeHtml) new HTML(""));
		//		examHelp.setHTML((SafeHtml) new HTML(""));
		//		smsHelp.setHTML((SafeHtml) new HTML(""));
		//		trainingHelp.setHTML((SafeHtml) new HTML(""));
		//		stockHelp.setHTML((SafeHtml) new HTML(""));
		//		adminHelp.setHTML((SafeHtml) new HTML(""));
		//		drugAdminHelp.setHTML((SafeHtml) new HTML(""));
		//		reportHelp.setHTML((SafeHtml) new HTML(""));
		//		userAdminHelp.setHTML((SafeHtml) new HTML(""));
		LocalSession.get().setSelectedHlk(HELP_HLK);

	}

}
