package org.imogene.epicam.client.ui.editor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.google.web.bindery.requestfactory.shared.Receiver;

import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.EpicamRenderer;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.client.i18n.NLS;
import org.imogene.epicam.client.ui.field.ImogLocalizedTextBox;
import org.imogene.epicam.client.ui.field.ImogLocalizedTextAreaBox;
import org.imogene.epicam.shared.constants.EpicamEnumConstants;
import org.imogene.epicam.shared.proxy.UtilisateurProxy;
import org.imogene.epicam.shared.request.UtilisateurRequest;

import org.imogene.epicam.shared.request.PatientRequest;
import org.imogene.web.shared.request.ImogEntityRequest;
import com.google.web.bindery.requestfactory.shared.Request;

import org.imogene.web.client.event.FieldValueChangeEvent;
import org.imogene.web.client.i18n.BaseNLS;
import org.imogene.web.client.ui.field.*;
import org.imogene.web.client.ui.field.binary.*;
import org.imogene.web.client.ui.field.group.FieldGroupPanel;
import org.imogene.web.client.ui.field.relation.multi.ImogMultiRelationBox;
import org.imogene.web.client.ui.field.relation.single.ImogSingleRelationBox;
import org.imogene.web.client.ui.panel.RelationPopupPanel;
import org.imogene.web.client.ui.panel.WrapperPanel;
import org.imogene.web.client.util.NumericUtil;
import org.imogene.web.client.util.DateUtil;
import org.imogene.web.shared.request.ImogEntityRequest;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorDelegate;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.editor.client.HasEditorDelegate;
import com.google.gwt.editor.client.HasEditorErrors;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * Editor that provides the UI components that allow a UtilisateurProxy to be viewed and edited
 * @author MEDES-IMPS
 */
public class UtilisateurEditor extends Composite
		implements
			Editor<UtilisateurProxy>,
			HasEditorDelegate<UtilisateurProxy>,
			HasEditorErrors<UtilisateurProxy> {

	interface Binder extends UiBinder<Widget, UtilisateurEditor> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);

	protected final EpicamRequestFactory requestFactory;
	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();
	private EditorDelegate<UtilisateurProxy> delegate;

	private UtilisateurProxy editedValue; //Not used by the editor
	private boolean hideButtons = false;

	/* Identification section widgets */
	@UiField
	@Ignore
	FieldGroupPanel identificationSection;
	@UiField
	ImogTextBox nom;
	@UiField
	ImogDateBox dateNaissance;
	@UiField
	ImogTextBox profession;

	/* Contact section widgets */
	@UiField
	@Ignore
	FieldGroupPanel contactSection;
	@UiField
	ImogTextBox telephoneUn;
	@UiField
	ImogTextBox telephoneDeux;
	@UiField
	ImogTextBox telephoneTrois;
	@UiField
	ImogEmailBox email;
	@UiField
	ImogSingleEnumBox libelle;
	@UiField
	ImogTextAreaBox complementAdresse;
	@UiField
	ImogTextBox quartier;
	@UiField
	ImogTextBox ville;
	@UiField
	ImogTextBox codePostal;

	/**
	 * Constructor
	 * @param factory the application request factory
	 * @param hideButtons true if the relation field buttons shall be hidden
	 */
	public UtilisateurEditor(EpicamRequestFactory factory, boolean hideButtons) {

		this.requestFactory = factory;
		this.hideButtons = hideButtons;

		initWidget(BINDER.createAndBindUi(this));

		properties();
	}

	/**
	 * Constructor
	 * @param factory the application request factory
	 */
	public UtilisateurEditor(EpicamRequestFactory factory) {
		this(factory, false);
	}

	/**
	 * Sets the properties of the fields
	 */
	private void properties() {

		/* Identification section widgets */
		identificationSection.setGroupTitle(NLS.constants()
				.utilisateur_group_identification());
		nom.setLabel(NLS.constants().utilisateur_field_nom());
		dateNaissance.setLabel(NLS.constants()
				.utilisateur_field_dateNaissance());
		profession.setLabel(NLS.constants().utilisateur_field_profession());

		/* Contact section widgets */
		contactSection.setGroupTitle(NLS.constants()
				.utilisateur_group_contact());
		telephoneUn.setLabel(NLS.constants().utilisateur_field_telephoneUn());
		telephoneDeux.setLabel(NLS.constants()
				.utilisateur_field_telephoneDeux());
		telephoneTrois.setLabel(NLS.constants()
				.utilisateur_field_telephoneTrois());
		email.setLabel(NLS.constants().utilisateur_field_email());
		libelle.setLabel(NLS.constants().utilisateur_field_libelle());
		libelle.addItem(EpicamEnumConstants.UTILISATEUR_LIBELLE_DOMICILE, NLS
				.constants().utilisateur_libelle_domicile_option());
		libelle.addItem(EpicamEnumConstants.UTILISATEUR_LIBELLE_BUREAU, NLS
				.constants().utilisateur_libelle_bureau_option());
		libelle.addItem(EpicamEnumConstants.UTILISATEUR_LIBELLE_AUTRE, NLS
				.constants().utilisateur_libelle_autre_option());
		complementAdresse.setLabel(NLS.constants()
				.utilisateur_field_complementAdresse());
		quartier.setLabel(NLS.constants().utilisateur_field_quartier());
		ville.setLabel(NLS.constants().utilisateur_field_ville());
		codePostal.setLabel(NLS.constants().utilisateur_field_codePostal());

	}

	/**
	 * Sets the edition mode
	 * @param isEdited true to enable the edition of the form
	 */
	public void setEdited(boolean isEdited) {

		if (isEdited)
			setFieldEditAccess();
		else
			setFieldReadAccess();

		/* Identification section widgets */
		nom.setEdited(isEdited);
		dateNaissance.setEdited(isEdited);
		profession.setEdited(isEdited);

		/* Contact section widgets */
		telephoneUn.setEdited(isEdited);
		telephoneDeux.setEdited(isEdited);
		telephoneTrois.setEdited(isEdited);
		email.setEdited(isEdited);
		libelle.setEdited(isEdited);
		complementAdresse.setEdited(isEdited);
		quartier.setEdited(isEdited);
		ville.setEdited(isEdited);
		codePostal.setEdited(isEdited);

	}

	/**
	 * Configures the visibility of the fields 
	 * in view mode depending on the user privileges
	 */
	private void setFieldReadAccess() {

		/* Identification section widgets visibility */
		if (!AccessManager.canReadUtilisateurIdentification())
			identificationSection.setVisible(false);

		/* Contact section widgets visibility */
		if (!AccessManager.canReadUtilisateurContact())
			contactSection.setVisible(false);

	}

	/**
	 * Configures the visibility of the fields 
	 * in edit mode depending on the user privileges
	 */
	private void setFieldEditAccess() {

		/* Identification section widgets visibility */
		if (!AccessManager.canEditUtilisateurIdentification())
			identificationSection.setVisible(false);

		/* Contact section widgets visibility */
		if (!AccessManager.canEditUtilisateurContact())
			contactSection.setVisible(false);

	}

	/**
	 * Sets the Request Context for the List Editors.
	 */
	public void setRequestContextForListEditors(UtilisateurRequest ctx) {
	}

	/**
	 * Manages editor updates when a field value changes
	 */
	private void setFieldValueChangeHandler() {

		registrations.add(requestFactory.getEventBus().addHandler(
				FieldValueChangeEvent.TYPE,
				new FieldValueChangeEvent.Handler() {
					@Override
					public void onValueChange(ImogField<?> field) {

						// field dependent visibility management
						computeVisibility(field, false);

					}
				}));
	}

	/**
	 * Computes the field visibility
	 */
	public void computeVisibility(ImogField<?> source, boolean allValidation) {

	}

	/**
	 * Gets the UtilisateurProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities
	 * @return
	 */
	public UtilisateurProxy getEditedValue() {
		return editedValue;
	}

	/**
	 * Sets the UtilisateurProxy that is edited in the Workflow
	 * Not used by the editor
	 * Temporary storage used to transmit the proxy to related entities	 
	 * @param editedValue 
	 */
	public void setEditedValue(UtilisateurProxy editedValue) {
		this.editedValue = editedValue;

	}

	/**
	 *
	 */
	public void raiseNotUniqueError(String field) {
		delegate.recordError(BaseNLS.messages().error_not_unique(), null, field);
	}

	/**
	 * Validate fields values
	 */
	public void validateFields() {

		// nom is a required field
		if (nom.getValue() == null)
			delegate.recordError(BaseNLS.messages().error_required(), null,
					"nom");
	}

	/**
	 */
	private void setAllLabelWith(String width) {

		/* Identification field group */
		nom.setLabelWidth(width);
		dateNaissance.setLabelWidth(width);
		profession.setLabelWidth(width);

		/* Contact field group */
		telephoneUn.setLabelWidth(width);
		telephoneDeux.setLabelWidth(width);
		telephoneTrois.setLabelWidth(width);
		email.setLabelWidth(width);
		libelle.setLabelWidth(width);
		complementAdresse.setLabelWidth(width);
		quartier.setLabelWidth(width);
		ville.setLabelWidth(width);
		codePostal.setLabelWidth(width);

	}

	/**
	 */
	private void setAllBoxWith(int width) {

		/* Identification field group */
		nom.setBoxWidth(width);
		profession.setBoxWidth(width);

		/* Contact field group */
		telephoneUn.setBoxWidth(width);
		telephoneDeux.setBoxWidth(width);
		telephoneTrois.setBoxWidth(width);
		email.setBoxWidth(width);
		libelle.setBoxWidth(width);
		complementAdresse.setBoxWidth(width);
		quartier.setBoxWidth(width);
		ville.setBoxWidth(width);
		codePostal.setBoxWidth(width);

	}

	@Override
	public void setDelegate(EditorDelegate<UtilisateurProxy> delegate) {
		this.delegate = delegate;
	}

	@Override
	public void showErrors(List<EditorError> errors) {
		if (errors != null && errors.size() > 0) {

			List<EditorError> nomFieldErrors = new ArrayList<EditorError>();

			for (EditorError error : errors) {
				Object userData = error.getUserData();
				if (userData != null && userData instanceof String) {
					String field = (String) userData;

					if (field.equals("nom"))
						nomFieldErrors.add(error);

				}
			}
			if (nomFieldErrors.size() > 0)
				nom.showErrors(nomFieldErrors);
		}
	}

	@Override
	protected void onUnload() {
		for (HandlerRegistration r : registrations)
			r.removeHandler();
		registrations.clear();
		super.onUnload();
	}

	@Override
	protected void onLoad() {
		setFieldValueChangeHandler();
		super.onLoad();
	}

}
