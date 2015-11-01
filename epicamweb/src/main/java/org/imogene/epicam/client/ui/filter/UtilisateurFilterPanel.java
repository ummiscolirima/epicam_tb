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
 * Filter panel to filter the Utilisateur entries
 * @author MEDES-IMPS
 */
public class UtilisateurFilterPanel extends ImogFilterPanel {

	/* filter widgets */
	private TextBox nomBox;
	private ImogFilterBox nomFilterBox;
	private TextBox professionBox;
	private ImogFilterBox professionFilterBox;
	private DateBox dateNaissanceBeforeBox;
	private ImogFilterBox dateNaissanceBeforeFilterBox;
	private DateBox dateNaissanceAfterBox;
	private ImogFilterBox dateNaissanceAfterFilterBox;

	private ImogBooleanAsRadio deletedEntityBox;
	private ImogFilterBox deletedEntityBoxFilterBox;

	public UtilisateurFilterPanel() {
		super();
		setFieldReadAccess();
	}

	@Override
	public void resetFilterWidgets() {

		nomBox.setValue(null);
		professionBox.setValue(null);
		dateNaissanceBeforeBox.setValue(null);
		dateNaissanceAfterBox.setValue(null);

		deletedEntityBox.setValue(false);

	}

	@Override
	public void createFilterWidgets() {

		nomBox = new TextBox();
		nomFilterBox = new ImogFilterBox(nomBox);
		nomFilterBox.setFilterLabel(NLS.constants().utilisateur_field_nom());
		addTableFilter(nomFilterBox);

		professionBox = new TextBox();
		professionFilterBox = new ImogFilterBox(professionBox);
		professionFilterBox.setFilterLabel(NLS.constants()
				.utilisateur_field_profession());
		addTableFilter(professionFilterBox);

		dateNaissanceAfterBox = new DateBox();
		dateNaissanceAfterBox.setFormat(new SimpleImogDateFormat(DateUtil
				.getDateFormater()));
		dateNaissanceAfterFilterBox = new ImogFilterBox(dateNaissanceAfterBox);
		dateNaissanceAfterFilterBox.setFilterLabel(NLS.constants()
				.utilisateur_field_dateNaissance()
				+ BaseNLS.constants().search_operator_superior());
		addTableFilter(dateNaissanceAfterFilterBox);

		dateNaissanceBeforeBox = new DateBox();
		dateNaissanceBeforeBox.setFormat(new SimpleImogDateFormat(DateUtil
				.getDateFormater()));
		dateNaissanceBeforeFilterBox = new ImogFilterBox(dateNaissanceBeforeBox);
		dateNaissanceBeforeFilterBox.setFilterLabel(NLS.constants()
				.utilisateur_field_dateNaissance()
				+ BaseNLS.constants().search_operator_inferior());
		addTableFilter(dateNaissanceBeforeFilterBox);

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
		nomCrit.setFieldDisplayName(NLS.constants().utilisateur_field_nom());
		nomCrit.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		nomCrit.setValue(nomBox.getValue());
		nomCrit.setValueDisplayName(nomBox.getValue());
		criteria.add(nomCrit);

		FilterCriteria professionCrit = new FilterCriteria();
		professionCrit.setField("profession");
		professionCrit.setFieldDisplayName(NLS.constants()
				.utilisateur_field_profession());
		professionCrit.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
		professionCrit.setValue(professionBox.getValue());
		professionCrit.setValueDisplayName(professionBox.getValue());
		criteria.add(professionCrit);

		if (dateNaissanceBeforeBox.getValue() != null) {
			FilterCriteria dateNaissanceBeforeCrit = new FilterCriteria();
			dateNaissanceBeforeCrit.setField("dateNaissance");
			dateNaissanceBeforeCrit.setFieldDisplayName(NLS.constants()
					.utilisateur_field_dateNaissance()
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
					.utilisateur_field_dateNaissance()
					+ BaseNLS.constants().search_operator_superior());
			dateNaissanceAfterCrit
					.setOperation(CriteriaConstants.DATE_OPERATOR_AFTER);
			dateNaissanceAfterCrit.setValue(DateUtil
					.getDate(dateNaissanceAfterBox.getValue()));
			dateNaissanceAfterCrit.setValueDisplayName(DateUtil
					.getDate(dateNaissanceAfterBox.getValue()));
			criteria.add(dateNaissanceAfterCrit);
		}

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

		if (!AccessManager.canReadUtilisateurIdentification())
			nomFilterBox.setVisible(false);

		if (!AccessManager.canReadUtilisateurIdentification())
			professionFilterBox.setVisible(false);

		if (!AccessManager.canReadUtilisateurIdentification()) {
			dateNaissanceBeforeFilterBox.setVisible(false);
			dateNaissanceAfterFilterBox.setVisible(false);
		}

		if (!AccessManager.isAdmin())
			deletedEntityBoxFilterBox.setVisible(false);
	}
}
