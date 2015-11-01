package org.imogene.epicam.client.dataprovider;

import java.util.ArrayList;
import java.util.List;

import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.constants.EpicamEnumConstants;
import org.imogene.epicam.shared.proxy.RavitaillementProxy;
import org.imogene.epicam.shared.request.RavitaillementRequest;
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
 * Data provider to feed the Ravitaillement entry Table and Selection List
 * @author MEDES-IMPS
 */
public class RavitaillementDataProvider
		extends
			ImogBeanDataProvider<RavitaillementProxy> {

	private final EpicamRequestFactory requestFactory;
	private boolean nonAffected = false;
	private boolean searchInReverse = false;
	private String property = null;

	/**
	 * For Relation Fields
	 * Provides instances of entity Ravitaillement with pagination
	 */
	public RavitaillementDataProvider(EpicamRequestFactory requestFactory) {
		this.requestFactory = requestFactory;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides instances of entity Ravitaillement that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 */
	public RavitaillementDataProvider(EpicamRequestFactory requestFactory,
			String pProperty) {
		this.requestFactory = requestFactory;
		nonAffected = true;
		property = pProperty;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides filtered instances of entity Ravitaillement that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 * @param searchInReverse true for 1:1 relations, if the property for which non affected values are searched shall be looked in reverse relation
	 */
	public RavitaillementDataProvider(EpicamRequestFactory requestFactory,
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
	public Request<List<RavitaillementProxy>> getList(int start, int numRows) {

		RavitaillementRequest request = (RavitaillementRequest) getContext();
		Request<List<RavitaillementProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedRavitaillement(
									start, numRows, "dateDepart", false,
									searchCriterions, property);
						else
							result = request
									.listNonAffectedRavitaillementReverse(
											start, numRows, "dateDepart",
											false, searchCriterions, property);
					} else
						result = request.listRavitaillement(start, numRows,
								"dateDepart", false, searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedRavitaillement(
									start, numRows, "dateDepart", false,
									filterCriteria, property);
						else
							result = request
									.listNonAffectedRavitaillementReverse(
											start, numRows, "dateDepart",
											false, filterCriteria, property);
					} else
						result = request.listRavitaillement(start, numRows,
								"dateDepart", false, filterCriteria);
				}

			} else
				result = request.getRavitaillementEmptyList();
		} else {
			if (searchCriterions != null) {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedRavitaillement(start,
								numRows, "dateDepart", false, searchCriterions,
								property);
					else
						result = request.listNonAffectedRavitaillementReverse(
								start, numRows, "dateDepart", false,
								searchCriterions, property);
				} else
					result = request.listRavitaillement(start, numRows,
							"dateDepart", false, searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedRavitaillement(start,
								numRows, "dateDepart", false, property);
					else
						result = request.listNonAffectedRavitaillementReverse(
								start, numRows, "dateDepart", false, property);
				} else
					result = request.listRavitaillement(start, numRows,
							"dateDepart", false);
			}
		}

		if (isFiltered) {
			result.with("CDTDepart");
			result.with("CDTArrivee");
		}

		else {
			result.with("CDTDepart");
			result.with("CDTArrivee");
		}

		return result;
	}

	/**
	 * Called by Dynatable
	 */
	@Override
	public Request<List<RavitaillementProxy>> getList(String property,
			int start, int numRows, boolean asc) {

		RavitaillementRequest request = (RavitaillementRequest) getContext();
		Request<List<RavitaillementProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null)
					/* permanent filter added to search criterion */
					result = request.listRavitaillement(start, numRows,
							property, asc, searchCriterions);
				else
					/* permanent filter only */
					result = request.listRavitaillement(start, numRows,
							property, asc, filterCriteria);

			} else
				result = request.getRavitaillementEmptyList();
		} else {
			if (searchCriterions != null)
				result = request.listRavitaillement(start, numRows, property,
						asc, searchCriterions);
			else
				result = request.listRavitaillement(start, numRows, property,
						asc);
		}

		result.with("CDTDepart");
		result.with("CDTArrivee");

		return result;
	}

	@Override
	public Request<Long> getTotalRowCount() {
		RavitaillementRequest request = (RavitaillementRequest) getContext();

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedRavitaillement(
									property, searchCriterions);
						else
							return request
									.countNonAffectedRavitaillementReverse(
											property, searchCriterions);
					} else
						return request.countRavitaillement(searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedRavitaillement(
									property, filterCriteria);
						else
							return request
									.countNonAffectedRavitaillementReverse(
											property, filterCriteria);
					} else
						return request.countRavitaillement(filterCriteria);
				}

			} else
				return request.countNonAffectedRavitaillement("id");
		} else {

			if (searchCriterions != null) {
				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedRavitaillement(property,
								searchCriterions);
					else
						return request.countNonAffectedRavitaillementReverse(
								property, searchCriterions);
				} else
					return request.countRavitaillement(searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedRavitaillement(property);
					else
						return request
								.countNonAffectedRavitaillementReverse(property);
				} else
					return request.countRavitaillement();
			}
		}

	}

	@Override
	public RequestContext getEntityContext() {
		return requestFactory.ravitaillementRequest();
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

			RavitaillementRequest request = (RavitaillementRequest) getContext();
			newRequest = false;

			ImogJunctionProxy junction = request
					.create(ImogConjunctionProxy.class);
			List<ImogCriterionProxy> criterias = new ArrayList<ImogCriterionProxy>();

			ImogJunctionProxy disJunction = request
					.create(ImogDisjunctionProxy.class);
			List<ImogCriterionProxy> criterionList = new ArrayList<ImogCriterionProxy>();

			// Search field Nom
			BasicCriteriaProxy cDTDepart_nomCrit = request
					.create(BasicCriteriaProxy.class);
			cDTDepart_nomCrit.setField("cDTDepart.nom");
			cDTDepart_nomCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			cDTDepart_nomCrit.setValue(text);
			buffer.append("(" + NLS.constants().centreDiagTrait_field_nom()
					+ ": " + text + ") " + SYMBOL_OR + " ");
			criterionList.add(cDTDepart_nomCrit);

			// Search field DateDepart
			try {
				DateUtil.parseDate(text);
				BasicCriteriaProxy dateDepartCrit = request
						.create(BasicCriteriaProxy.class);
				dateDepartCrit.setField("dateDepart");
				dateDepartCrit
						.setOperation(CriteriaConstants.DATE_OPERATOR_EQUAL);
				dateDepartCrit.setValue(text);
				buffer.append("("
						+ NLS.constants().ravitaillement_field_dateDepart()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(dateDepartCrit);
			} catch (Exception ex) {/*criteria not added*/
			}

			// Search field Nom
			BasicCriteriaProxy cDTArrivee_nomCrit = request
					.create(BasicCriteriaProxy.class);
			cDTArrivee_nomCrit.setField("cDTArrivee.nom");
			cDTArrivee_nomCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			cDTArrivee_nomCrit.setValue(text);
			buffer.append("(" + NLS.constants().centreDiagTrait_field_nom()
					+ ": " + text + ") " + SYMBOL_OR + " ");
			criterionList.add(cDTArrivee_nomCrit);

			// Search field DateArrivee
			try {
				DateUtil.parseDate(text);
				BasicCriteriaProxy dateArriveeCrit = request
						.create(BasicCriteriaProxy.class);
				dateArriveeCrit.setField("dateArrivee");
				dateArriveeCrit
						.setOperation(CriteriaConstants.DATE_OPERATOR_EQUAL);
				dateArriveeCrit.setValue(text);
				buffer.append("("
						+ NLS.constants().ravitaillement_field_dateArrivee()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(dateArriveeCrit);
			} catch (Exception ex) {/*criteria not added*/
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
