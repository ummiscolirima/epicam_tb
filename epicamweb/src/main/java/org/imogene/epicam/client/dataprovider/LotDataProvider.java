package org.imogene.epicam.client.dataprovider;

import java.util.ArrayList;
import java.util.List;

import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.constants.EpicamEnumConstants;
import org.imogene.epicam.shared.proxy.LotProxy;
import org.imogene.epicam.shared.request.LotRequest;
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
 * Data provider to feed the Lot entry Table and Selection List
 * @author MEDES-IMPS
 */
public class LotDataProvider extends ImogBeanDataProvider<LotProxy> {

	private final EpicamRequestFactory requestFactory;
	private boolean nonAffected = false;
	private boolean searchInReverse = false;
	private String property = null;

	/**
	 * For Relation Fields
	 * Provides instances of entity Lot with pagination
	 */
	public LotDataProvider(EpicamRequestFactory requestFactory) {
		this.requestFactory = requestFactory;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides instances of entity Lot that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 */
	public LotDataProvider(EpicamRequestFactory requestFactory, String pProperty) {
		this.requestFactory = requestFactory;
		nonAffected = true;
		property = pProperty;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides filtered instances of entity Lot that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 * @param searchInReverse true for 1:1 relations, if the property for which non affected values are searched shall be looked in reverse relation
	 */
	public LotDataProvider(EpicamRequestFactory requestFactory,
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
	public Request<List<LotProxy>> getList(int start, int numRows) {

		LotRequest request = (LotRequest) getContext();
		Request<List<LotProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedLot(start, numRows,
									"datePeremption", false, searchCriterions,
									property);
						else
							result = request.listNonAffectedLotReverse(start,
									numRows, "datePeremption", false,
									searchCriterions, property);
					} else
						result = request.listLot(start, numRows,
								"datePeremption", false, searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedLot(start, numRows,
									"datePeremption", false, filterCriteria,
									property);
						else
							result = request.listNonAffectedLotReverse(start,
									numRows, "datePeremption", false,
									filterCriteria, property);
					} else
						result = request.listLot(start, numRows,
								"datePeremption", false, filterCriteria);
				}

			} else
				result = request.getLotEmptyList();
		} else {
			if (searchCriterions != null) {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedLot(start, numRows,
								"datePeremption", false, searchCriterions,
								property);
					else
						result = request.listNonAffectedLotReverse(start,
								numRows, "datePeremption", false,
								searchCriterions, property);
				} else
					result = request.listLot(start, numRows, "datePeremption",
							false, searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedLot(start, numRows,
								"datePeremption", false, property);
					else
						result = request.listNonAffectedLotReverse(start,
								numRows, "datePeremption", false, property);
				} else
					result = request.listLot(start, numRows, "datePeremption",
							false);
			}
		}

		if (isFiltered) {
			result.with("CDT");
			result.with("intrant");
			result.with("medicament");
		}

		else {
			result.with("intrant");
			result.with("medicament");
		}

		return result;
	}

	/**
	 * Called by Dynatable
	 */
	@Override
	public Request<List<LotProxy>> getList(String property, int start,
			int numRows, boolean asc) {

		LotRequest request = (LotRequest) getContext();
		Request<List<LotProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null)
					/* permanent filter added to search criterion */
					result = request.listLot(start, numRows, property, asc,
							searchCriterions);
				else
					/* permanent filter only */
					result = request.listLot(start, numRows, property, asc,
							filterCriteria);

			} else
				result = request.getLotEmptyList();
		} else {
			if (searchCriterions != null)
				result = request.listLot(start, numRows, property, asc,
						searchCriterions);
			else
				result = request.listLot(start, numRows, property, asc);
		}

		result.with("CDT");
		result.with("intrant");
		result.with("medicament");

		return result;
	}

	@Override
	public Request<Long> getTotalRowCount() {
		LotRequest request = (LotRequest) getContext();

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedLot(property,
									searchCriterions);
						else
							return request.countNonAffectedLotReverse(property,
									searchCriterions);
					} else
						return request.countLot(searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedLot(property,
									filterCriteria);
						else
							return request.countNonAffectedLotReverse(property,
									filterCriteria);
					} else
						return request.countLot(filterCriteria);
				}

			} else
				return request.countNonAffectedLot("id");
		} else {

			if (searchCriterions != null) {
				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedLot(property,
								searchCriterions);
					else
						return request.countNonAffectedLotReverse(property,
								searchCriterions);
				} else
					return request.countLot(searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedLot(property);
					else
						return request.countNonAffectedLotReverse(property);
				} else
					return request.countLot();
			}
		}

	}

	@Override
	public RequestContext getEntityContext() {
		return requestFactory.lotRequest();
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

			LotRequest request = (LotRequest) getContext();
			newRequest = false;

			ImogJunctionProxy junction = request
					.create(ImogConjunctionProxy.class);
			List<ImogCriterionProxy> criterias = new ArrayList<ImogCriterionProxy>();

			ImogJunctionProxy disJunction = request
					.create(ImogDisjunctionProxy.class);
			List<ImogCriterionProxy> criterionList = new ArrayList<ImogCriterionProxy>();

			// Search field Nom
			BasicCriteriaProxy cDT_nomCrit = request
					.create(BasicCriteriaProxy.class);
			cDT_nomCrit.setField("cDT.nom");
			cDT_nomCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			cDT_nomCrit.setValue(text);
			buffer.append("(" + NLS.constants().centreDiagTrait_field_nom()
					+ ": " + text + ") " + SYMBOL_OR + " ");
			criterionList.add(cDT_nomCrit);

			// Search field Numero
			BasicCriteriaProxy numeroCrit = request
					.create(BasicCriteriaProxy.class);
			numeroCrit.setField("numero");
			numeroCrit.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			numeroCrit.setValue(text);
			buffer.append("(" + NLS.constants().lot_field_numero() + ": "
					+ text + ") " + SYMBOL_OR + " ");
			criterionList.add(numeroCrit);

			// Search field Quantite
			try {
				Integer.valueOf(text);
				BasicCriteriaProxy quantiteCrit = request
						.create(BasicCriteriaProxy.class);
				quantiteCrit.setField("quantite");
				quantiteCrit.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
				quantiteCrit.setValue(text);
				buffer.append("(" + NLS.constants().lot_field_quantite() + ": "
						+ text + ") " + SYMBOL_OR + " ");
				criterionList.add(quantiteCrit);
			} catch (Exception ex) {/*criteria not added*/
			}

			// Search field DatePeremption
			try {
				DateUtil.parseDate(text);
				BasicCriteriaProxy datePeremptionCrit = request
						.create(BasicCriteriaProxy.class);
				datePeremptionCrit.setField("datePeremption");
				datePeremptionCrit
						.setOperation(CriteriaConstants.DATE_OPERATOR_EQUAL);
				datePeremptionCrit.setValue(text);
				buffer.append("(" + NLS.constants().lot_field_datePeremption()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(datePeremptionCrit);
			} catch (Exception ex) {/*criteria not added*/
			}

			// Search field Identifiant
			BasicCriteriaProxy intrant_identifiantCrit = request
					.create(BasicCriteriaProxy.class);
			intrant_identifiantCrit.setField("intrant.identifiant");
			intrant_identifiantCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			intrant_identifiantCrit.setValue(text);
			buffer.append("(" + NLS.constants().intrant_field_identifiant()
					+ ": " + text + ") " + SYMBOL_OR + " ");
			criterionList.add(intrant_identifiantCrit);

			// Search field Designation
			BasicCriteriaProxy medicament_designationCrit = request
					.create(BasicCriteriaProxy.class);
			medicament_designationCrit.setField("medicament.designation");
			medicament_designationCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			medicament_designationCrit.setValue(text);
			buffer.append("(" + NLS.constants().medicament_field_designation()
					+ ": " + text + ") " + SYMBOL_OR + " ");
			criterionList.add(medicament_designationCrit);

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
