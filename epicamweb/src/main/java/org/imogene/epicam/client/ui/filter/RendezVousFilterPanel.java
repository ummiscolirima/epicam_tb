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
 * Filter panel to filter the RendezVous entries
 * @author MEDES-IMPS
 */
public class RendezVousFilterPanel extends ImogFilterPanel {

	/* filter widgets */
	private DateBox dateRendezVousBeforeBox;
	private ImogFilterBox dateRendezVousBeforeFilterBox;
	private DateBox dateRendezVousAfterBox;
	private ImogFilterBox dateRendezVousAfterFilterBox;
	private ImogBooleanAsList honoreBox;
	private ImogFilterBox honoreFilterBox;

	private ImogBooleanAsRadio deletedEntityBox;
	private ImogFilterBox deletedEntityBoxFilterBox;

	public RendezVousFilterPanel() {
		super();
		setFieldReadAccess();
	}

	@Override
	public void resetFilterWidgets() {

		dateRendezVousBeforeBox.setValue(null);
		dateRendezVousAfterBox.setValue(null);
		honoreBox.setValue(null);

		deletedEntityBox.setValue(false);

	}

	@Override
	public void createFilterWidgets() {

		dateRendezVousAfterBox = new DateBox();
		dateRendezVousAfterBox.setFormat(new SimpleImogDateFormat(DateUtil
				.getDateFormater()));
		dateRendezVousAfterFilterBox = new ImogFilterBox(dateRendezVousAfterBox);
		dateRendezVousAfterFilterBox.setFilterLabel(NLS.constants()
				.rendezVous_field_dateRendezVous()
				+ BaseNLS.constants().search_operator_superior());
		addTableFilter(dateRendezVousAfterFilterBox);

		dateRendezVousBeforeBox = new DateBox();
		dateRendezVousBeforeBox.setFormat(new SimpleImogDateFormat(DateUtil
				.getDateFormater()));
		dateRendezVousBeforeFilterBox = new ImogFilterBox(
				dateRendezVousBeforeBox);
		dateRendezVousBeforeFilterBox.setFilterLabel(NLS.constants()
				.rendezVous_field_dateRendezVous()
				+ BaseNLS.constants().search_operator_inferior());
		addTableFilter(dateRendezVousBeforeFilterBox);

		honoreBox = new ImogBooleanAsList();
		honoreFilterBox = new ImogFilterBox(honoreBox);
		honoreFilterBox.setFilterLabel(NLS.constants()
				.rendezVous_field_honore());
		addTableFilter(honoreFilterBox);

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

		if (dateRendezVousBeforeBox.getValue() != null) {
			FilterCriteria dateRendezVousBeforeCrit = new FilterCriteria();
			dateRendezVousBeforeCrit.setField("dateRendezVous");
			dateRendezVousBeforeCrit.setFieldDisplayName(NLS.constants()
					.rendezVous_field_dateRendezVous()
					+ BaseNLS.constants().search_operator_inferior());
			dateRendezVousBeforeCrit
					.setOperation(CriteriaConstants.DATE_OPERATOR_BEFORE);
			dateRendezVousBeforeCrit.setValue(DateUtil
					.getDate(dateRendezVousBeforeBox.getValue()));
			dateRendezVousBeforeCrit.setValueDisplayName(DateUtil
					.getDate(dateRendezVousBeforeBox.getValue()));
			criteria.add(dateRendezVousBeforeCrit);
		}

		if (dateRendezVousAfterBox.getValue() != null) {
			FilterCriteria dateRendezVousAfterCrit = new FilterCriteria();
			dateRendezVousAfterCrit.setField("dateRendezVous");
			dateRendezVousAfterCrit.setFieldDisplayName(NLS.constants()
					.rendezVous_field_dateRendezVous()
					+ BaseNLS.constants().search_operator_superior());
			dateRendezVousAfterCrit
					.setOperation(CriteriaConstants.DATE_OPERATOR_AFTER);
			dateRendezVousAfterCrit.setValue(DateUtil
					.getDate(dateRendezVousAfterBox.getValue()));
			dateRendezVousAfterCrit.setValueDisplayName(DateUtil
					.getDate(dateRendezVousAfterBox.getValue()));
			criteria.add(dateRendezVousAfterCrit);
		}

		FilterCriteria honoreCrit = new FilterCriteria();
		honoreCrit.setField("honore");
		honoreCrit.setFieldDisplayName(NLS.constants()
				.rendezVous_field_honore());
		honoreCrit.setOperation(CriteriaConstants.BOOLEAN_OPERATOR_EQUAL);
		if (honoreBox.getValue() == null)
			honoreCrit.setValue(null);
		else
			honoreCrit.setValue(String.valueOf(honoreBox.getValue()));
		honoreCrit.setValueDisplayName(BooleanUtil
				.getBooleanDisplayValue(honoreBox.getValue()));
		criteria.add(honoreCrit);

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

		if (!AccessManager.canReadRendezVousDescription()) {
			dateRendezVousBeforeFilterBox.setVisible(false);
			dateRendezVousAfterFilterBox.setVisible(false);
		}

		if (!AccessManager.canReadRendezVousDescription())
			honoreFilterBox.setVisible(false);

		if (!AccessManager.isAdmin())
			deletedEntityBoxFilterBox.setVisible(false);
	}
}
