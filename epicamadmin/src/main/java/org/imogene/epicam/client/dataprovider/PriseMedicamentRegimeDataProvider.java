package org.imogene.epicam.client.dataprovider;

import java.util.ArrayList;
import java.util.List;

import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.constants.EpicamEnumConstants;
import org.imogene.epicam.shared.proxy.PriseMedicamentRegimeProxy;
import org.imogene.epicam.shared.request.PriseMedicamentRegimeRequest;
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
 * Data provider to feed the PriseMedicamentRegime entry Table and Selection List
 * @author MEDES-IMPS
 */
public class PriseMedicamentRegimeDataProvider
		extends
			ImogBeanDataProvider<PriseMedicamentRegimeProxy> {

	private final EpicamRequestFactory requestFactory;
	private boolean nonAffected = false;
	private boolean searchInReverse = false;
	private String property = null;

	/**
	 * For Relation Fields
	 * Provides instances of entity PriseMedicamentRegime with pagination
	 */
	public PriseMedicamentRegimeDataProvider(EpicamRequestFactory requestFactory) {
		this.requestFactory = requestFactory;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides instances of entity PriseMedicamentRegime that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 */
	public PriseMedicamentRegimeDataProvider(
			EpicamRequestFactory requestFactory, String pProperty) {
		this.requestFactory = requestFactory;
		nonAffected = true;
		property = pProperty;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides filtered instances of entity PriseMedicamentRegime that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 * @param searchInReverse true for 1:1 relations, if the property for which non affected values are searched shall be looked in reverse relation
	 */
	public PriseMedicamentRegimeDataProvider(
			EpicamRequestFactory requestFactory, String pProperty,
			boolean searchInReverse) {
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
	public Request<List<PriseMedicamentRegimeProxy>> getList(int start,
			int numRows) {

		PriseMedicamentRegimeRequest request = (PriseMedicamentRegimeRequest) getContext();
		Request<List<PriseMedicamentRegimeProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							result = request
									.listNonAffectedPriseMedicamentRegime(
											start, numRows, "medicament", true,
											searchCriterions, property);
						else
							result = request
									.listNonAffectedPriseMedicamentRegimeReverse(
											start, numRows, "medicament", true,
											searchCriterions, property);
					} else
						result = request.listPriseMedicamentRegime(start,
								numRows, "medicament", true, searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							result = request
									.listNonAffectedPriseMedicamentRegime(
											start, numRows, "medicament", true,
											filterCriteria, property);
						else
							result = request
									.listNonAffectedPriseMedicamentRegimeReverse(
											start, numRows, "medicament", true,
											filterCriteria, property);
					} else
						result = request.listPriseMedicamentRegime(start,
								numRows, "medicament", true, filterCriteria);
				}

			} else
				result = request.getPriseMedicamentRegimeEmptyList();
		} else {
			if (searchCriterions != null) {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedPriseMedicamentRegime(
								start, numRows, "medicament", true,
								searchCriterions, property);
					else
						result = request
								.listNonAffectedPriseMedicamentRegimeReverse(
										start, numRows, "medicament", true,
										searchCriterions, property);
				} else
					result = request.listPriseMedicamentRegime(start, numRows,
							"medicament", true, searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedPriseMedicamentRegime(
								start, numRows, "medicament", true, property);
					else
						result = request
								.listNonAffectedPriseMedicamentRegimeReverse(
										start, numRows, "medicament", true,
										property);
				} else
					result = request.listPriseMedicamentRegime(start, numRows,
							"medicament", true);
			}
		}

		if (isFiltered) {
			result.with("medicament");
		}

		else {
			result.with("medicament");
		}

		return result;
	}

	/**
	 * Called by Dynatable
	 */
	@Override
	public Request<List<PriseMedicamentRegimeProxy>> getList(String property,
			int start, int numRows, boolean asc) {

		PriseMedicamentRegimeRequest request = (PriseMedicamentRegimeRequest) getContext();
		Request<List<PriseMedicamentRegimeProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null)
					/* permanent filter added to search criterion */
					result = request.listPriseMedicamentRegime(start, numRows,
							property, asc, searchCriterions);
				else
					/* permanent filter only */
					result = request.listPriseMedicamentRegime(start, numRows,
							property, asc, filterCriteria);

			} else
				result = request.getPriseMedicamentRegimeEmptyList();
		} else {
			if (searchCriterions != null)
				result = request.listPriseMedicamentRegime(start, numRows,
						property, asc, searchCriterions);
			else
				result = request.listPriseMedicamentRegime(start, numRows,
						property, asc);
		}

		result.with("medicament");

		return result;
	}

	@Override
	public Request<Long> getTotalRowCount() {
		PriseMedicamentRegimeRequest request = (PriseMedicamentRegimeRequest) getContext();

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							return request
									.countNonAffectedPriseMedicamentRegime(
											property, searchCriterions);
						else
							return request
									.countNonAffectedPriseMedicamentRegimeReverse(
											property, searchCriterions);
					} else
						return request
								.countPriseMedicamentRegime(searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							return request
									.countNonAffectedPriseMedicamentRegime(
											property, filterCriteria);
						else
							return request
									.countNonAffectedPriseMedicamentRegimeReverse(
											property, filterCriteria);
					} else
						return request
								.countPriseMedicamentRegime(filterCriteria);
				}

			} else
				return request.countNonAffectedPriseMedicamentRegime("id");
		} else {

			if (searchCriterions != null) {
				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedPriseMedicamentRegime(
								property, searchCriterions);
					else
						return request
								.countNonAffectedPriseMedicamentRegimeReverse(
										property, searchCriterions);
				} else
					return request.countPriseMedicamentRegime(searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						return request
								.countNonAffectedPriseMedicamentRegime(property);
					else
						return request
								.countNonAffectedPriseMedicamentRegimeReverse(property);
				} else
					return request.countPriseMedicamentRegime();
			}
		}

	}

	@Override
	public RequestContext getEntityContext() {
		return requestFactory.priseMedicamentRegimeRequest();
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

			PriseMedicamentRegimeRequest request = (PriseMedicamentRegimeRequest) getContext();
			newRequest = false;

			ImogJunctionProxy junction = request
					.create(ImogConjunctionProxy.class);
			List<ImogCriterionProxy> criterias = new ArrayList<ImogCriterionProxy>();

			ImogJunctionProxy disJunction = request
					.create(ImogDisjunctionProxy.class);
			List<ImogCriterionProxy> criterionList = new ArrayList<ImogCriterionProxy>();

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
