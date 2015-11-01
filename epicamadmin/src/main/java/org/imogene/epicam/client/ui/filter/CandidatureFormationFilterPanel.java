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
 * Filter panel to filter the CandidatureFormation entries
 * @author MEDES-IMPS
 */
public class CandidatureFormationFilterPanel extends ImogFilterPanel {

	/* filter widgets */
	private TextBox personnel_nomBox;
	private ImogFilterBox personnel_nomFilterBox;
	private TextBox cdt_nomBox;
	private ImogFilterBox cdt_nomFilterBox;
	private ImogBooleanAsList approuveeRegionBox;
	private ImogFilterBox approuveeRegionFilterBox;
	private ImogBooleanAsList approuveeGTCBox;
	private ImogFilterBox approuveeGTCFilterBox;
	private TextBox districtsante_nomBox;
	private ImogFilterBox districtsante_nomFilterBox;

	private ImogBooleanAsRadio deletedEntityBox;
	private ImogFilterBox deletedEntityBoxFilterBox;

	public CandidatureFormationFilterPanel() {
		super();
		setFieldReadAccess();
	}

	@Override
	public void resetFilterWidgets() {

		personnel_nomBox.setValue(null);
		cdt_nomBox.setValue(null);
		approuveeRegionBox.setValue(null);
		approuveeGTCBox.setValue(null);
		districtsante_nomBox.setValue(null);

		deletedEntityBox.setValue(false);

	}

	@Override
	public void createFilterWidgets() {

		personnel_nomBox = new TextBox();
		personnel_nomFilterBox = new ImogFilterBox(personnel_nomBox);
		personnel_nomFilterBox.setFilterLabel(NLS.constants()
				.personnel_field_nom());
		addTableFilter(personnel_nomFilterBox);

		cdt_nomBox = new TextBox();
		cdt_nomFilterBox = new ImogFilterBox(cdt_nomBox);
		cdt_nomFilterBox.setFilterLabel(NLS.constants()
				.centreDiagTrait_field_nom());
		addTableFilter(cdt_nomFilterBox);

		approuveeRegionBox = new ImogBooleanAsList();
		approuveeRegionFilterBox = new ImogFilterBox(approuveeRegionBox);
		approuveeRegionFilterBox.setFilterLabel(NLS.constants()
				.candidatureFormation_field_approuveeRegion());
		addTableFilter(approuveeRegionFilterBox);

		approuveeGTCBox = new ImogBooleanAsList();
		approuveeGTCFilterBox = new ImogFilterBox(approuveeGTCBox);
		approuveeGTCFilterBox.setFilterLabel(NLS.constants()
				.candidatureFormation_field_approuveeGTC());
		addTableFilter(approuveeGTCFilterBox);

		districtsante_nomBox = new TextBox();
		districtsante_nomFilterBox = new ImogFilterBox(districtsante_nomBox);
		districtsante_nomFilterBox.setFilterLabel(NLS.constants()
				.districtSante_field_nom());
		addTableFilter(districtsante_nomFilterBox);

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

		FilterCriteria personnel_nomCrit = new FilterCriteria();
		personnel_nomCrit.setField("personnel.nom");
		personnel_nomCrit.setFieldDisplayName(NLS.constants()
				.personnel_field_nom());
		personnel_nomCrit
				.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		personnel_nomCrit.setValue(personnel_nomBox.getValue());
		personnel_nomCrit.setValueDisplayName(personnel_nomBox.getValue());
		criteria.add(personnel_nomCrit);

		FilterCriteria cdt_nomCrit = new FilterCriteria();
		cdt_nomCrit.setField("cDT.nom");
		cdt_nomCrit.setFieldDisplayName(NLS.constants()
				.centreDiagTrait_field_nom());
		cdt_nomCrit.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		cdt_nomCrit.setValue(cdt_nomBox.getValue());
		cdt_nomCrit.setValueDisplayName(cdt_nomBox.getValue());
		criteria.add(cdt_nomCrit);

		FilterCriteria approuveeRegionCrit = new FilterCriteria();
		approuveeRegionCrit.setField("approuveeRegion");
		approuveeRegionCrit.setFieldDisplayName(NLS.constants()
				.candidatureFormation_field_approuveeRegion());
		approuveeRegionCrit
				.setOperation(CriteriaConstants.BOOLEAN_OPERATOR_EQUAL);
		if (approuveeRegionBox.getValue() == null)
			approuveeRegionCrit.setValue(null);
		else
			approuveeRegionCrit.setValue(String.valueOf(approuveeRegionBox
					.getValue()));
		approuveeRegionCrit.setValueDisplayName(BooleanUtil
				.getBooleanDisplayValue(approuveeRegionBox.getValue()));
		criteria.add(approuveeRegionCrit);

		FilterCriteria approuveeGTCCrit = new FilterCriteria();
		approuveeGTCCrit.setField("approuveeGTC");
		approuveeGTCCrit.setFieldDisplayName(NLS.constants()
				.candidatureFormation_field_approuveeGTC());
		approuveeGTCCrit.setOperation(CriteriaConstants.BOOLEAN_OPERATOR_EQUAL);
		if (approuveeGTCBox.getValue() == null)
			approuveeGTCCrit.setValue(null);
		else
			approuveeGTCCrit
					.setValue(String.valueOf(approuveeGTCBox.getValue()));
		approuveeGTCCrit.setValueDisplayName(BooleanUtil
				.getBooleanDisplayValue(approuveeGTCBox.getValue()));
		criteria.add(approuveeGTCCrit);

		FilterCriteria districtsante_nomCrit = new FilterCriteria();
		if (locale.equals("fr"))
			districtsante_nomCrit.setField("districtSante.nom.francais");
		if (locale.equals("en"))
			districtsante_nomCrit.setField("districtSante.nom.english");
		districtsante_nomCrit.setFieldDisplayName(NLS.constants()
				.districtSante_field_nom());
		districtsante_nomCrit
				.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		districtsante_nomCrit.setValue(districtsante_nomBox.getValue());
		districtsante_nomCrit.setValueDisplayName(districtsante_nomBox
				.getValue());
		criteria.add(districtsante_nomCrit);

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
			personnel_nomFilterBox.setVisible(false);

		if (!AccessManager.canReadCentreDiagTraitDescription())
			cdt_nomFilterBox.setVisible(false);

		if (!AccessManager.canReadCandidatureFormationRegionApprobation())
			approuveeRegionFilterBox.setVisible(false);

		if (!AccessManager.canReadCandidatureFormationGtcApprobation())
			approuveeGTCFilterBox.setVisible(false);

		if (!AccessManager.canReadDistrictSanteDescription())
			districtsante_nomFilterBox.setVisible(false);

		if (!AccessManager.isAdmin())
			deletedEntityBoxFilterBox.setVisible(false);
	}
}
