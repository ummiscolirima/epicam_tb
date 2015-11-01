package org.imogene.epicam.client.dataprovider;

import java.util.ArrayList;
import java.util.List;

import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.constants.EpicamEnumConstants;
import org.imogene.epicam.shared.proxy.ExamenSerologieProxy;
import org.imogene.epicam.shared.request.ExamenSerologieRequest;
import org.imogene.epicam.client.i18n.NLS;
import org.imogene.lib.common.constants.CriteriaConstants;
import org.imogene.web.client.i18n.BaseNLS;
import org.imogene.web.client.ui.table.ImogBeanDataProvider;
import org.imogene.web.shared.proxy.criteria.BasicCriteriaProxy;
import org.imogene.web.shared.proxy.criteria.ImogConjunctionProxy;
import org.imogene.web.shared.proxy.criteria.ImogCriterionProxy;
import org.imogene.web.shared.proxy.criteria.ImogDisjunctionProxy;
import org.imogene.web.shared.proxy.criteria.ImogJunctionProxy;
import org.imogene.web.client.util.DateUtil;
import org.imogene.web.client.util.LocalSession;
import org.imogene.web.client.util.ProfileUtil;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;

/**
 * Data provider to feed the ExamenSerologie entry Table and Selection List
 * @author MEDES-IMPS
 */
public class ExamenSerologieDataProvider
		extends
			ImogBeanDataProvider<ExamenSerologieProxy> {

	private final EpicamRequestFactory requestFactory;
	private boolean nonAffected = false;
	private boolean searchInReverse = false;
	private String property = null;

	/**
	 * For Relation Fields
	 * Provides instances of entity ExamenSerologie with pagination
	 */
	public ExamenSerologieDataProvider(EpicamRequestFactory requestFactory) {
		this.requestFactory = requestFactory;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides instances of entity ExamenSerologie that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 */
	public ExamenSerologieDataProvider(EpicamRequestFactory requestFactory,
			String pProperty) {
		this.requestFactory = requestFactory;
		nonAffected = true;
		property = pProperty;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides filtered instances of entity ExamenSerologie that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 * @param searchInReverse true for 1:1 relations, if the property for which non affected values are searched shall be looked in reverse relation
	 */
	public ExamenSerologieDataProvider(EpicamRequestFactory requestFactory,
			String pProperty, boolean searchInReverse) {
		this.requestFactory = requestFactory;
		nonAffected = true;
		property = pProperty;
		this.searchInReverse = searchInReverse;
		setSearchCriterions(null);
	}

	/**
	 * Sets criterions for which values have to be temporally searched
	 * @param criterions ImogJunctionProxy including the criterions for which the values have to be searched
	 */
	public void setSearchCriterions(ImogJunctionProxy criterions) {
		if (criterions == null) {
			if (ProfileUtil.isAdmin()) {
				filter(getDeletedEntityFilterCriteria(false));
				LocalSession.get().setSearchCriterions(getSearchCriterions(),
						null);
			} else
				searchCriterions = criterions;
		} else
			searchCriterions = criterions;
	}

	/**
	 * Called by Relation Boxes
	 */
	@Override
	public Request<List<ExamenSerologieProxy>> getList(int start, int numRows) {

		ExamenSerologieRequest request = (ExamenSerologieRequest) getContext();
		Request<List<ExamenSerologieProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedExamenSerologie(
									start, numRows, "nature", true,
									searchCriterions, property);
						else
							result = request
									.listNonAffectedExamenSerologieReverse(
											start, numRows, "nature", true,
											searchCriterions, property);
					} else
						result = request.listExamenSerologie(start, numRows,
								"nature", true, searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedExamenSerologie(
									start, numRows, "nature", true,
									filterCriteria, property);
						else
							result = request
									.listNonAffectedExamenSerologieReverse(
											start, numRows, "nature", true,
											filterCriteria, property);
					} else
						result = request.listExamenSerologie(start, numRows,
								"nature", true, filterCriteria);
				}

			} else
				result = request.getExamenSerologieEmptyList();
		} else {
			if (searchCriterions != null) {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedExamenSerologie(start,
								numRows, "nature", true, searchCriterions,
								property);
					else
						result = request.listNonAffectedExamenSerologieReverse(
								start, numRows, "nature", true,
								searchCriterions, property);
				} else
					result = request.listExamenSerologie(start, numRows,
							"nature", true, searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedExamenSerologie(start,
								numRows, "nature", true, property);
					else
						result = request.listNonAffectedExamenSerologieReverse(
								start, numRows, "nature", true, property);
				} else
					result = request.listExamenSerologie(start, numRows,
							"nature", true);
			}
		}

		if (isFiltered) {
			result.with("patient");
		}

		else {
		}

		return result;
	}

	/**
	 * Called by Dynatable
	 */
	@Override
	public Request<List<ExamenSerologieProxy>> getList(String property,
			int start, int numRows, boolean asc) {

		ExamenSerologieRequest request = (ExamenSerologieRequest) getContext();
		Request<List<ExamenSerologieProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null)
					/* permanent filter added to search criterion */
					result = request.listExamenSerologie(start, numRows,
							property, asc, searchCriterions);
				else
					/* permanent filter only */
					result = request.listExamenSerologie(start, numRows,
							property, asc, filterCriteria);

			} else
				result = request.getExamenSerologieEmptyList();
		} else {
			if (searchCriterions != null)
				result = request.listExamenSerologie(start, numRows, property,
						asc, searchCriterions);
			else
				result = request.listExamenSerologie(start, numRows, property,
						asc);
		}

		result.with("patient");

		return result;
	}

	@Override
	public Request<Long> getTotalRowCount() {
		ExamenSerologieRequest request = (ExamenSerologieRequest) getContext();

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedExamenSerologie(
									property, searchCriterions);
						else
							return request
									.countNonAffectedExamenSerologieReverse(
											property, searchCriterions);
					} else
						return request.countExamenSerologie(searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedExamenSerologie(
									property, filterCriteria);
						else
							return request
									.countNonAffectedExamenSerologieReverse(
											property, filterCriteria);
					} else
						return request.countExamenSerologie(filterCriteria);
				}

			} else
				return request.countNonAffectedExamenSerologie("id");
		} else {

			if (searchCriterions != null) {
				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedExamenSerologie(
								property, searchCriterions);
					else
						return request.countNonAffectedExamenSerologieReverse(
								property, searchCriterions);
				} else
					return request.countExamenSerologie(searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						return request
								.countNonAffectedExamenSerologie(property);
					else
						return request
								.countNonAffectedExamenSerologieReverse(property);
				} else
					return request.countExamenSerologie();
			}
		}

	}

	@Override
	public RequestContext getEntityContext() {
		return requestFactory.examenSerologieRequest();
	}

	@Override
	public String fullTextSearch(String text) {

		boolean fullTextSearch = false;
		StringBuffer buffer = new StringBuffer(BaseNLS.constants()
				.label_filtered() + " ");

		if (text == null || (text != null && text.equals(""))) {
			setSearchCriterions(null);
		} else {

			String locale = NLS.constants().locale();

			ExamenSerologieRequest request = (ExamenSerologieRequest) getContext();
			newRequest = false;

			ImogJunctionProxy junction = request
					.create(ImogConjunctionProxy.class);
			List<ImogCriterionProxy> criterias = new ArrayList<ImogCriterionProxy>();

			ImogJunctionProxy disJunction = request
					.create(ImogDisjunctionProxy.class);
			List<ImogCriterionProxy> criterionList = new ArrayList<ImogCriterionProxy>();

			// Search field Identifiant
			BasicCriteriaProxy patient_identifiantCrit = request
					.create(BasicCriteriaProxy.class);
			patient_identifiantCrit.setField("patient.identifiant");
			patient_identifiantCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			patient_identifiantCrit.setValue(text);
			buffer.append("(" + NLS.constants().patient_field_identifiant()
					+ ": " + text + ") " + SYMBOL_OR + " ");
			criterionList.add(patient_identifiantCrit);
			// Search field Nom
			BasicCriteriaProxy patient_nomCrit = request
					.create(BasicCriteriaProxy.class);
			patient_nomCrit.setField("patient.nom");
			patient_nomCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			patient_nomCrit.setValue(text);
			buffer.append("(" + NLS.constants().patient_field_nom() + ": "
					+ text + ") " + SYMBOL_OR + " ");
			criterionList.add(patient_nomCrit);

			// Search field DateTest
			try {
				DateUtil.parseDate(text);
				BasicCriteriaProxy dateTestCrit = request
						.create(BasicCriteriaProxy.class);
				dateTestCrit.setField("dateTest");
				dateTestCrit
						.setOperation(CriteriaConstants.DATE_OPERATOR_EQUAL);
				dateTestCrit.setValue(text);
				buffer.append("("
						+ NLS.constants().examenSerologie_field_dateTest()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(dateTestCrit);
			} catch (Exception ex) {/*criteria not added*/
			}

			// Search field Nature
			if (text.toLowerCase().equals(
					NLS.constants().examenSerologie_nature_vIH_option()
							.toLowerCase())) {
				BasicCriteriaProxy natureCrit = request
						.create(BasicCriteriaProxy.class);
				natureCrit.setField("nature");
				natureCrit
						.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
				natureCrit
						.setValue(EpicamEnumConstants.EXAMENSEROLOGIE_NATURE_VIH);
				buffer.append("("
						+ NLS.constants().examenSerologie_field_nature() + ": "
						+ text + ") " + SYMBOL_OR + " ");
				criterionList.add(natureCrit);
			}
			if (text.toLowerCase().equals(
					NLS.constants().examenSerologie_nature_cD4_option()
							.toLowerCase())) {
				BasicCriteriaProxy natureCrit = request
						.create(BasicCriteriaProxy.class);
				natureCrit.setField("nature");
				natureCrit
						.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
				natureCrit
						.setValue(EpicamEnumConstants.EXAMENSEROLOGIE_NATURE_CD4);
				buffer.append("("
						+ NLS.constants().examenSerologie_field_nature() + ": "
						+ text + ") " + SYMBOL_OR + " ");
				criterionList.add(natureCrit);
			}

			// Search field ResultatCD4
			try {
				Integer.valueOf(text);
				BasicCriteriaProxy resultatCD4Crit = request
						.create(BasicCriteriaProxy.class);
				resultatCD4Crit.setField("resultatCD4");
				resultatCD4Crit
						.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
				resultatCD4Crit.setValue(text);
				buffer.append("("
						+ NLS.constants().examenSerologie_field_resultatCD4()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(resultatCD4Crit);
			} catch (Exception ex) {/*criteria not added*/
			}

			// Search field ResultatVIH
			if (text.toLowerCase().equals(
					NLS.constants()
							.examenSerologie_resultatVIH_positif_option()
							.toLowerCase())) {
				BasicCriteriaProxy resultatVIHCrit = request
						.create(BasicCriteriaProxy.class);
				resultatVIHCrit.setField("resultatVIH");
				resultatVIHCrit
						.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
				resultatVIHCrit
						.setValue(EpicamEnumConstants.EXAMENSEROLOGIE_RESULTATVIH_POSITIF);
				buffer.append("("
						+ NLS.constants().examenSerologie_field_resultatVIH()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(resultatVIHCrit);
			}
			if (text.toLowerCase().equals(
					NLS.constants()
							.examenSerologie_resultatVIH_negatif_option()
							.toLowerCase())) {
				BasicCriteriaProxy resultatVIHCrit = request
						.create(BasicCriteriaProxy.class);
				resultatVIHCrit.setField("resultatVIH");
				resultatVIHCrit
						.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
				resultatVIHCrit
						.setValue(EpicamEnumConstants.EXAMENSEROLOGIE_RESULTATVIH_NEGATIF);
				buffer.append("("
						+ NLS.constants().examenSerologie_field_resultatVIH()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(resultatVIHCrit);
			}

			disJunction.setCriterions(criterionList);
			criterias.add(disJunction);
			fullTextSearch = true;

			if (ProfileUtil.isAdmin()) {
				BasicCriteriaProxy isDeletedCrit = request
						.create(BasicCriteriaProxy.class);
				isDeletedCrit.setField("deleted");
				isDeletedCrit.setOperation(CriteriaConstants.OPERATOR_ISNULL);
				isDeletedCrit.setValue(text);
				criterias.add(isDeletedCrit);
			}
			junction.setCriterions(criterias);

			// add FilterCriteria if exists
			if (isFiltered && filterCriteria != null)
				setSearchCriterions(mergeFilterCriteriaAndFullTextSearchCriterion(
						request, junction));
			else
				setSearchCriterions(junction);

		}
		if (fullTextSearch) {
			String message = buffer.toString();
			int lastSymbolIndex = message.lastIndexOf(SYMBOL_OR);
			return message.substring(0, lastSymbolIndex);
		} else
			return null;
	}

}
