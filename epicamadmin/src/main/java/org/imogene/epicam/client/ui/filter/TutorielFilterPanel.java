package org.imogene.epicam.client.ui.filter;

import java.util.ArrayList;
import java.util.List;

import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.EpicamRenderer;
import org.imogene.epicam.shared.constants.EpicamEnumConstants;
import org.imogene.epicam.shared.proxy.*;
import org.imogene.epicam.client.i18n.NLS;
import org.imogene.lib.common.constants.CriteriaConstants;
import org.imogene.web.client.i18n.BaseNLS;
import org.imogene.web.client.ui.field.widget.ImogBooleanAsRadio;
import org.imogene.web.client.i18n.BaseNLS;
import org.imogene.web.client.ui.field.widget.ImogBooleanAsList;
import org.imogene.web.client.ui.table.filter.ImogFilterBox;
import org.imogene.web.client.ui.table.filter.ImogFilterPanel;
import org.imogene.web.client.util.DateUtil;
import org.imogene.web.client.util.BooleanUtil;
import org.imogene.web.client.util.FilterCriteria;
import org.imogene.web.client.util.SimpleImogDateFormat;

import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.DoubleBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;

/**
 * Filter panel to filter the Tutoriel entries
 * @author MEDES-IMPS
 */
public class TutorielFilterPanel extends ImogFilterPanel {

	/* filter widgets */
	private TextBox nomBox;
	private ImogFilterBox nomFilterBox;
	private TextBox referenceBox;
	private ImogFilterBox referenceFilterBox;
	private ListBox typeBox;
	private ImogFilterBox typeFilterBox;

	private ImogBooleanAsRadio deletedEntityBox;
	private ImogFilterBox deletedEntityBoxFilterBox;

	public TutorielFilterPanel() {
		super();
		setFieldReadAccess();
	}

	@Override
	public void resetFilterWidgets() {

		nomBox.setValue(null);
		referenceBox.setValue(null);
		typeBox.setSelectedIndex(0);

		deletedEntityBox.setValue(false);

	}

	@Override
	public void createFilterWidgets() {

		nomBox = new TextBox();
		nomFilterBox = new ImogFilterBox(nomBox);
		nomFilterBox.setFilterLabel(NLS.constants().tutoriel_field_nom());
		addTableFilter(nomFilterBox);

		referenceBox = new TextBox();
		referenceFilterBox = new ImogFilterBox(referenceBox);
		referenceFilterBox.setFilterLabel(NLS.constants()
				.tutoriel_field_reference());
		addTableFilter(referenceFilterBox);

		typeBox = new ListBox();
		typeBox.addItem("", BaseNLS.constants().enumeration_unknown());
		typeBox.setSelectedIndex(0);
		typeBox.addItem(NLS.constants().tutoriel_type_texte_option(),
				EpicamEnumConstants.TUTORIEL_TYPE_TEXTE);
		typeBox.addItem(NLS.constants().tutoriel_type_audio_option(),
				EpicamEnumConstants.TUTORIEL_TYPE_AUDIO);
		typeBox.addItem(NLS.constants().tutoriel_type_video_option(),
				EpicamEnumConstants.TUTORIEL_TYPE_VIDEO);
		typeFilterBox = new ImogFilterBox(typeBox);
		typeFilterBox.setFilterLabel(NLS.constants().tutoriel_field_type());
		addTableFilter(typeFilterBox);

		deletedEntityBox = new ImogBooleanAsRadio();
		deletedEntityBox.isStrict(true);
		deletedEntityBox.setEnabled(true);
		deletedEntityBox.setValue(false);
		deletedEntityBoxFilterBox = new ImogFilterBox(deletedEntityBox);
		deletedEntityBoxFilterBox.setFilterLabel(BaseNLS.constants()
				.entity_field_deleted());
		addTableFilter(deletedEntityBoxFilterBox);
	}

	@Override
	public List<FilterCriteria> getFilterCriteria() {

		String locale = NLS.constants().locale();

		List<FilterCriteria> criteria = new ArrayList<FilterCriteria>();

		FilterCriteria nomCrit = new FilterCriteria();
		if (locale.equals("fr"))
			nomCrit.setField("nom.francais");
		if (locale.equals("en"))
			nomCrit.setField("nom.english");
		nomCrit.setFieldDisplayName(NLS.constants().tutoriel_field_nom());
		nomCrit.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		nomCrit.setValue(nomBox.getValue());
		nomCrit.setValueDisplayName(nomBox.getValue());
		criteria.add(nomCrit);

		FilterCriteria referenceCrit = new FilterCriteria();
		referenceCrit.setField("reference");
		referenceCrit.setFieldDisplayName(NLS.constants()
				.tutoriel_field_reference());
		referenceCrit.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		referenceCrit.setValue(referenceBox.getValue());
		referenceCrit.setValueDisplayName(referenceBox.getValue());
		criteria.add(referenceCrit);

		FilterCriteria typeCrit = new FilterCriteria();
		typeCrit.setField("type");
		typeCrit.setFieldDisplayName(NLS.constants().tutoriel_field_type());
		typeCrit.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
		typeCrit.setValue(typeBox.getValue(typeBox.getSelectedIndex()));
		typeCrit.setValueDisplayName(EpicamRenderer.get().getEnumDisplayValue(
				TutorielProxy.class, "type",
				typeBox.getValue(typeBox.getSelectedIndex())));
		criteria.add(typeCrit);

		FilterCriteria deletedEntityCrit = new FilterCriteria();
		deletedEntityCrit.setField("deleted");
		deletedEntityCrit.setFieldDisplayName(BaseNLS.constants()
				.entity_field_deleted());
		if (deletedEntityBox.getValue()) {
			deletedEntityCrit
					.setOperation(CriteriaConstants.OPERATOR_ISNOTNULL);
			deletedEntityCrit.setValue("true");
			deletedEntityCrit.setValueDisplayName(BaseNLS.constants()
					.boolean_true());
		} else {
			deletedEntityCrit.setOperation(CriteriaConstants.OPERATOR_ISNULL);
			deletedEntityCrit.setValue("false");
			deletedEntityCrit.setValueDisplayName(BaseNLS.constants()
					.boolean_false());
		}
		criteria.add(deletedEntityCrit);

		return criteria;
	}

	/**
	 * Configures the visibility of the fields 
	 * in view mode depending on the user privileges
	 */
	public void setFieldReadAccess() {

		if (!AccessManager.canReadTutorielDescription())
			nomFilterBox.setVisible(false);

		if (!AccessManager.canReadTutorielDescription())
			referenceFilterBox.setVisible(false);

		if (!AccessManager.canReadTutorielDescription())
			typeFilterBox.setVisible(false);

		if (!AccessManager.isAdmin())
			deletedEntityBoxFilterBox.setVisible(false);
	}
}
