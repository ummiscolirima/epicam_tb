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
 * Filter panel to filter the Personnel entries
 * @author MEDES-IMPS
 */
public class PersonnelFilterPanel extends ImogFilterPanel {

	/* filter widgets */
	private TextBox nomBox;
	private ImogFilterBox nomFilterBox;
	private DateBox dateNaissanceBeforeBox;
	private ImogFilterBox dateNaissanceBeforeFilterBox;
	private DateBox dateNaissanceAfterBox;
	private ImogFilterBox dateNaissanceAfterFilterBox;
	private DateBox dateArriveeBeforeBox;
	private ImogFilterBox dateArriveeBeforeFilterBox;
	private DateBox dateArriveeAfterBox;
	private ImogFilterBox dateArriveeAfterFilterBox;
	private DateBox dateDepartBeforeBox;
	private ImogFilterBox dateDepartBeforeFilterBox;
	private DateBox dateDepartAfterBox;
	private ImogFilterBox dateDepartAfterFilterBox;
	private ListBox niveauBox;
	private ImogFilterBox niveauFilterBox;
	private ImogBooleanAsList actifBox;
	private ImogFilterBox actifFilterBox;

	private ImogBooleanAsRadio deletedEntityBox;
	private ImogFilterBox deletedEntityBoxFilterBox;

	public PersonnelFilterPanel() {
		super();
		setFieldReadAccess();
	}

	@Override
	public void resetFilterWidgets() {

		nomBox.setValue(null);
		dateNaissanceBeforeBox.setValue(null);
		dateNaissanceAfterBox.setValue(null);
		dateArriveeBeforeBox.setValue(null);
		dateArriveeAfterBox.setValue(null);
		dateDepartBeforeBox.setValue(null);
		dateDepartAfterBox.setValue(null);
		niveauBox.setSelectedIndex(0);
		actifBox.setValue(null);

		deletedEntityBox.setValue(false);

	}

	@Override
	public void createFilterWidgets() {

		nomBox = new TextBox();
		nomFilterBox = new ImogFilterBox(nomBox);
		nomFilterBox.setFilterLabel(NLS.constants().personnel_field_nom());
		addTableFilter(nomFilterBox);

		dateNaissanceAfterBox = new DateBox();
		dateNaissanceAfterBox.setFormat(new SimpleImogDateFormat(DateUtil
				.getDateFormater()));
		dateNaissanceAfterFilterBox = new ImogFilterBox(dateNaissanceAfterBox);
		dateNaissanceAfterFilterBox.setFilterLabel(NLS.constants()
				.personnel_field_dateNaissance()
				+ BaseNLS.constants().search_operator_superior());
		addTableFilter(dateNaissanceAfterFilterBox);

		dateNaissanceBeforeBox = new DateBox();
		dateNaissanceBeforeBox.setFormat(new SimpleImogDateFormat(DateUtil
				.getDateFormater()));
		dateNaissanceBeforeFilterBox = new ImogFilterBox(dateNaissanceBeforeBox);
		dateNaissanceBeforeFilterBox.setFilterLabel(NLS.constants()
				.personnel_field_dateNaissance()
				+ BaseNLS.constants().search_operator_inferior());
		addTableFilter(dateNaissanceBeforeFilterBox);

		dateArriveeAfterBox = new DateBox();
		dateArriveeAfterBox.setFormat(new SimpleImogDateFormat(DateUtil
				.getDateFormater()));
		dateArriveeAfterFilterBox = new ImogFilterBox(dateArriveeAfterBox);
		dateArriveeAfterFilterBox.setFilterLabel(NLS.constants()
				.personnel_field_dateArrivee()
				+ BaseNLS.constants().search_operator_superior());
		addTableFilter(dateArriveeAfterFilterBox);

		dateArriveeBeforeBox = new DateBox();
		dateArriveeBeforeBox.setFormat(new SimpleImogDateFormat(DateUtil
				.getDateFormater()));
		dateArriveeBeforeFilterBox = new ImogFilterBox(dateArriveeBeforeBox);
		dateArriveeBeforeFilterBox.setFilterLabel(NLS.constants()
				.personnel_field_dateArrivee()
				+ BaseNLS.constants().search_operator_inferior());
		addTableFilter(dateArriveeBeforeFilterBox);

		dateDepartAfterBox = new DateBox();
		dateDepartAfterBox.setFormat(new SimpleImogDateFormat(DateUtil
				.getDateFormater()));
		dateDepartAfterFilterBox = new ImogFilterBox(dateDepartAfterBox);
		dateDepartAfterFilterBox.setFilterLabel(NLS.constants()
				.personnel_field_dateDepart()
				+ BaseNLS.constants().search_operator_superior());
		addTableFilter(dateDepartAfterFilterBox);

		dateDepartBeforeBox = new DateBox();
		dateDepartBeforeBox.setFormat(new SimpleImogDateFormat(DateUtil
				.getDateFormater()));
		dateDepartBeforeFilterBox = new ImogFilterBox(dateDepartBeforeBox);
		dateDepartBeforeFilterBox.setFilterLabel(NLS.constants()
				.personnel_field_dateDepart()
				+ BaseNLS.constants().search_operator_inferior());
		addTableFilter(dateDepartBeforeFilterBox);

		niveauBox = new ListBox();
		niveauBox.addItem("", BaseNLS.constants().enumeration_unknown());
		niveauBox.setSelectedIndex(0);
		niveauBox.addItem(NLS.constants().personnel_niveau_central_option(),
				EpicamEnumConstants.PERSONNEL_NIVEAU_CENTRAL);
		niveauBox.addItem(NLS.constants().personnel_niveau_region_option(),
				EpicamEnumConstants.PERSONNEL_NIVEAU_REGION);
		niveauBox.addItem(NLS.constants()
				.personnel_niveau_districtSante_option(),
				EpicamEnumConstants.PERSONNEL_NIVEAU_DISTRICTSANTE);
		niveauBox.addItem(NLS.constants().personnel_niveau_cDT_option(),
				EpicamEnumConstants.PERSONNEL_NIVEAU_CDT);
		niveauFilterBox = new ImogFilterBox(niveauBox);
		niveauFilterBox
				.setFilterLabel(NLS.constants().personnel_field_niveau());
		addTableFilter(niveauFilterBox);

		actifBox = new ImogBooleanAsList();
		actifFilterBox = new ImogFilterBox(actifBox);
		actifFilterBox.setFilterLabel(NLS.constants().personnel_field_actif());
		addTableFilter(actifFilterBox);

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
		nomCrit.setField("nom");
		nomCrit.setFieldDisplayName(NLS.constants().personnel_field_nom());
		nomCrit.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		nomCrit.setValue(nomBox.getValue());
		nomCrit.setValueDisplayName(nomBox.getValue());
		criteria.add(nomCrit);

		if (dateNaissanceBeforeBox.getValue() != null) {
			FilterCriteria dateNaissanceBeforeCrit = new FilterCriteria();
			dateNaissanceBeforeCrit.setField("dateNaissance");
			dateNaissanceBeforeCrit.setFieldDisplayName(NLS.constants()
					.personnel_field_dateNaissance()
					+ BaseNLS.constants().search_operator_inferior());
			dateNaissanceBeforeCrit
					.setOperation(CriteriaConstants.DATE_OPERATOR_BEFORE);
			dateNaissanceBeforeCrit.setValue(DateUtil
					.getDate(dateNaissanceBeforeBox.getValue()));
			dateNaissanceBeforeCrit.setValueDisplayName(DateUtil
					.getDate(dateNaissanceBeforeBox.getValue()));
			criteria.add(dateNaissanceBeforeCrit);
		}

		if (dateNaissanceAfterBox.getValue() != null) {
			FilterCriteria dateNaissanceAfterCrit = new FilterCriteria();
			dateNaissanceAfterCrit.setField("dateNaissance");
			dateNaissanceAfterCrit.setFieldDisplayName(NLS.constants()
					.personnel_field_dateNaissance()
					+ BaseNLS.constants().search_operator_superior());
			dateNaissanceAfterCrit
					.setOperation(CriteriaConstants.DATE_OPERATOR_AFTER);
			dateNaissanceAfterCrit.setValue(DateUtil
					.getDate(dateNaissanceAfterBox.getValue()));
			dateNaissanceAfterCrit.setValueDisplayName(DateUtil
					.getDate(dateNaissanceAfterBox.getValue()));
			criteria.add(dateNaissanceAfterCrit);
		}

		if (dateArriveeBeforeBox.getValue() != null) {
			FilterCriteria dateArriveeBeforeCrit = new FilterCriteria();
			dateArriveeBeforeCrit.setField("dateArrivee");
			dateArriveeBeforeCrit.setFieldDisplayName(NLS.constants()
					.personnel_field_dateArrivee()
					+ BaseNLS.constants().search_operator_inferior());
			dateArriveeBeforeCrit
					.setOperation(CriteriaConstants.DATE_OPERATOR_BEFORE);
			dateArriveeBeforeCrit.setValue(DateUtil
					.getDate(dateArriveeBeforeBox.getValue()));
			dateArriveeBeforeCrit.setValueDisplayName(DateUtil
					.getDate(dateArriveeBeforeBox.getValue()));
			criteria.add(dateArriveeBeforeCrit);
		}

		if (dateArriveeAfterBox.getValue() != null) {
			FilterCriteria dateArriveeAfterCrit = new FilterCriteria();
			dateArriveeAfterCrit.setField("dateArrivee");
			dateArriveeAfterCrit.setFieldDisplayName(NLS.constants()
					.personnel_field_dateArrivee()
					+ BaseNLS.constants().search_operator_superior());
			dateArriveeAfterCrit
					.setOperation(CriteriaConstants.DATE_OPERATOR_AFTER);
			dateArriveeAfterCrit.setValue(DateUtil.getDate(dateArriveeAfterBox
					.getValue()));
			dateArriveeAfterCrit.setValueDisplayName(DateUtil
					.getDate(dateArriveeAfterBox.getValue()));
			criteria.add(dateArriveeAfterCrit);
		}

		if (dateDepartBeforeBox.getValue() != null) {
			FilterCriteria dateDepartBeforeCrit = new FilterCriteria();
			dateDepartBeforeCrit.setField("dateDepart");
			dateDepartBeforeCrit.setFieldDisplayName(NLS.constants()
					.personnel_field_dateDepart()
					+ BaseNLS.constants().search_operator_inferior());
			dateDepartBeforeCrit
					.setOperation(CriteriaConstants.DATE_OPERATOR_BEFORE);
			dateDepartBeforeCrit.setValue(DateUtil.getDate(dateDepartBeforeBox
					.getValue()));
			dateDepartBeforeCrit.setValueDisplayName(DateUtil
					.getDate(dateDepartBeforeBox.getValue()));
			criteria.add(dateDepartBeforeCrit);
		}

		if (dateDepartAfterBox.getValue() != null) {
			FilterCriteria dateDepartAfterCrit = new FilterCriteria();
			dateDepartAfterCrit.setField("dateDepart");
			dateDepartAfterCrit.setFieldDisplayName(NLS.constants()
					.personnel_field_dateDepart()
					+ BaseNLS.constants().search_operator_superior());
			dateDepartAfterCrit
					.setOperation(CriteriaConstants.DATE_OPERATOR_AFTER);
			dateDepartAfterCrit.setValue(DateUtil.getDate(dateDepartAfterBox
					.getValue()));
			dateDepartAfterCrit.setValueDisplayName(DateUtil
					.getDate(dateDepartAfterBox.getValue()));
			criteria.add(dateDepartAfterCrit);
		}

		FilterCriteria niveauCrit = new FilterCriteria();
		niveauCrit.setField("niveau");
		niveauCrit
				.setFieldDisplayName(NLS.constants().personnel_field_niveau());
		niveauCrit.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
		niveauCrit.setValue(niveauBox.getValue(niveauBox.getSelectedIndex()));
		niveauCrit.setValueDisplayName(EpicamRenderer.get()
				.getEnumDisplayValue(PersonnelProxy.class, "niveau",
						niveauBox.getValue(niveauBox.getSelectedIndex())));
		criteria.add(niveauCrit);

		FilterCriteria actifCrit = new FilterCriteria();
		actifCrit.setField("actif");
		actifCrit.setFieldDisplayName(NLS.constants().personnel_field_actif());
		actifCrit.setOperation(CriteriaConstants.BOOLEAN_OPERATOR_EQUAL);
		if (actifBox.getValue() == null)
			actifCrit.setValue(null);
		else
			actifCrit.setValue(String.valueOf(actifBox.getValue()));
		actifCrit.setValueDisplayName(BooleanUtil
				.getBooleanDisplayValue(actifBox.getValue()));
		criteria.add(actifCrit);

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

		if (!AccessManager.canReadPersonnelIdentification())
			nomFilterBox.setVisible(false);

		if (!AccessManager.canReadPersonnelIdentification()) {
			dateNaissanceBeforeFilterBox.setVisible(false);
			dateNaissanceAfterFilterBox.setVisible(false);
		}

		if (!AccessManager.canReadPersonnelSituation()) {
			dateArriveeBeforeFilterBox.setVisible(false);
			dateArriveeAfterFilterBox.setVisible(false);
		}

		if (!AccessManager.canReadPersonnelSituation()) {
			dateDepartBeforeFilterBox.setVisible(false);
			dateDepartAfterFilterBox.setVisible(false);
		}

		if (!AccessManager.canReadPersonnelNiveauAccess())
			niveauFilterBox.setVisible(false);

		if (!AccessManager.canReadPersonnelNiveauAccess())
			actifFilterBox.setVisible(false);

		if (!AccessManager.isAdmin())
			deletedEntityBoxFilterBox.setVisible(false);
	}
}
