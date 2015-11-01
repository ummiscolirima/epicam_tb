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
 * Filter panel to filter the DetailCommandeIntrant entries
 * @author MEDES-IMPS
 */
public class DetailCommandeIntrantFilterPanel extends ImogFilterPanel {

	/* filter widgets */
	private TextBox intrant_identifiantBox;
	private ImogFilterBox intrant_identifiantFilterBox;
	private IntegerBox quantiteRequiseBox;
	private ImogFilterBox quantiteRequiseFilterBox;
	private IntegerBox quantiteEnStockBox;
	private ImogFilterBox quantiteEnStockFilterBox;

	private ImogBooleanAsRadio deletedEntityBox;
	private ImogFilterBox deletedEntityBoxFilterBox;

	public DetailCommandeIntrantFilterPanel() {
		super();
		setFieldReadAccess();
	}

	@Override
	public void resetFilterWidgets() {

		intrant_identifiantBox.setValue(null);
		quantiteRequiseBox.setValue(null);
		quantiteEnStockBox.setValue(null);

		deletedEntityBox.setValue(false);

	}

	@Override
	public void createFilterWidgets() {

		intrant_identifiantBox = new TextBox();
		intrant_identifiantFilterBox = new ImogFilterBox(intrant_identifiantBox);
		intrant_identifiantFilterBox.setFilterLabel(NLS.constants()
				.intrant_field_identifiant());
		addTableFilter(intrant_identifiantFilterBox);

		quantiteRequiseBox = new IntegerBox();
		quantiteRequiseFilterBox = new ImogFilterBox(quantiteRequiseBox);
		quantiteRequiseFilterBox.setFilterLabel(NLS.constants()
				.detailCommandeIntrant_field_quantiteRequise());
		addTableFilter(quantiteRequiseFilterBox);

		quantiteEnStockBox = new IntegerBox();
		quantiteEnStockFilterBox = new ImogFilterBox(quantiteEnStockBox);
		quantiteEnStockFilterBox.setFilterLabel(NLS.constants()
				.detailCommandeIntrant_field_quantiteEnStock());
		addTableFilter(quantiteEnStockFilterBox);

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

		FilterCriteria intrant_identifiantCrit = new FilterCriteria();
		intrant_identifiantCrit.setField("intrant.identifiant");
		intrant_identifiantCrit.setFieldDisplayName(NLS.constants()
				.intrant_field_identifiant());
		intrant_identifiantCrit
				.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		intrant_identifiantCrit.setValue(intrant_identifiantBox.getValue());
		intrant_identifiantCrit.setValueDisplayName(intrant_identifiantBox
				.getValue());
		criteria.add(intrant_identifiantCrit);

		FilterCriteria quantiteRequiseCrit = new FilterCriteria();
		quantiteRequiseCrit.setField("quantiteRequise");
		quantiteRequiseCrit.setFieldDisplayName(NLS.constants()
				.detailCommandeIntrant_field_quantiteRequise());
		quantiteRequiseCrit.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
		if (quantiteRequiseBox.getValue() == null)
			quantiteRequiseCrit.setValue(null);
		else
			quantiteRequiseCrit.setValue(String.valueOf(quantiteRequiseBox
					.getValue()));
		quantiteRequiseCrit.setValueDisplayName(String
				.valueOf(quantiteRequiseBox.getValue()));
		criteria.add(quantiteRequiseCrit);

		FilterCriteria quantiteEnStockCrit = new FilterCriteria();
		quantiteEnStockCrit.setField("quantiteEnStock");
		quantiteEnStockCrit.setFieldDisplayName(NLS.constants()
				.detailCommandeIntrant_field_quantiteEnStock());
		quantiteEnStockCrit.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
		if (quantiteEnStockBox.getValue() == null)
			quantiteEnStockCrit.setValue(null);
		else
			quantiteEnStockCrit.setValue(String.valueOf(quantiteEnStockBox
					.getValue()));
		quantiteEnStockCrit.setValueDisplayName(String
				.valueOf(quantiteEnStockBox.getValue()));
		criteria.add(quantiteEnStockCrit);

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

		if (!AccessManager.canReadIntrantDescription())
			intrant_identifiantFilterBox.setVisible(false);

		if (!AccessManager.canReadDetailCommandeIntrantDescription())
			quantiteRequiseFilterBox.setVisible(false);

		if (!AccessManager.canReadDetailCommandeIntrantDescription())
			quantiteEnStockFilterBox.setVisible(false);

		if (!AccessManager.isAdmin())
			deletedEntityBoxFilterBox.setVisible(false);
	}
}
