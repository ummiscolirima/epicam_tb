package org.imogene.epicam.client.dataprovider;

import java.util.ArrayList;
import java.util.List;

import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.constants.EpicamEnumConstants;
import org.imogene.epicam.shared.proxy.ArriveePersonnelProxy;
import org.imogene.epicam.shared.request.ArriveePersonnelRequest;
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
 * Data provider to feed the ArriveePersonnel entry Table and Selection List
 * @author MEDES-IMPS
 */
public class ArriveePersonnelDataProvider
		extends
			ImogBeanDataProvider<ArriveePersonnelProxy> {

	private final EpicamRequestFactory requestFactory;
	private boolean nonAffected = false;
	private boolean searchInReverse = false;
	private String property = null;

	/**
	 * For Relation Fields
	 * Provides instances of entity ArriveePersonnel with pagination
	 */
	public ArriveePersonnelDataProvider(EpicamRequestFactory requestFactory) {
		this.requestFactory = requestFactory;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides instances of entity ArriveePersonnel that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 */
	public ArriveePersonnelDataProvider(EpicamRequestFactory requestFactory,
			String pProperty) {
		this.requestFactory = requestFactory;
		nonAffected = true;
		property = pProperty;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides filtered instances of entity ArriveePersonnel that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 * @param searchInReverse true for 1:1 relations, if the property for which non affected values are searched shall be looked in reverse relation
	 */
	public ArriveePersonnelDataProvider(EpicamRequestFactory requestFactory,
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
	public Request<List<ArriveePersonnelProxy>> getList(int start, int numRows) {

		ArriveePersonnelRequest request = (ArriveePersonnelRequest) getContext();
		Request<List<ArriveePersonnelProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedArriveePersonnel(
									start, numRows, "dateArrivee", false,
									searchCriterions, property);
						else
							result = request
									.listNonAffectedArriveePersonnelReverse(
											start, numRows, "dateArrivee",
											false, searchCriterions, property);
					} else
						result = request.listArriveePersonnel(start, numRows,
								"dateArrivee", false, searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedArriveePersonnel(
									start, numRows, "dateArrivee", false,
									filterCriteria, property);
						else
							result = request
									.listNonAffectedArriveePersonnelReverse(
											start, numRows, "dateArrivee",
											false, filterCriteria, property);
					} else
						result = request.listArriveePersonnel(start, numRows,
								"dateArrivee", false, filterCriteria);
				}

			} else
				result = request.getArriveePersonnelEmptyList();
		} else {
			if (searchCriterions != null) {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedArriveePersonnel(start,
								numRows, "dateArrivee", false,
								searchCriterions, property);
					else
						result = request
								.listNonAffectedArriveePersonnelReverse(start,
										numRows, "dateArrivee", false,
										searchCriterions, property);
				} else
					result = request.listArriveePersonnel(start, numRows,
							"dateArrivee", false, searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedArriveePersonnel(start,
								numRows, "dateArrivee", false, property);
					else
						result = request
								.listNonAffectedArriveePersonnelReverse(start,
										numRows, "dateArrivee", false, property);
				} else
					result = request.listArriveePersonnel(start, numRows,
							"dateArrivee", false);
			}
		}

		if (isFiltered) {
			result.with("region");
			result.with("region.nom");
			result.with("districtSante");
			result.with("districtSante.nom");
			result.with("CDT");
			result.with("personnel");
		}

		else {
			result.with("personnel");
		}

		return result;
	}

	/**
	 * Called by Dynatable
	 */
	@Override
	public Request<List<ArriveePersonnelProxy>> getList(String property,
			int start, int numRows, boolean asc) {

		ArriveePersonnelRequest request = (ArriveePersonnelRequest) getContext();
		Request<List<ArriveePersonnelProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null)
					/* permanent filter added to search criterion */
					result = request.listArriveePersonnel(start, numRows,
							property, asc, searchCriterions);
				else
					/* permanent filter only */
					result = request.listArriveePersonnel(start, numRows,
							property, asc, filterCriteria);

			} else
				result = request.getArriveePersonnelEmptyList();
		} else {
			if (searchCriterions != null)
				result = request.listArriveePersonnel(start, numRows, property,
						asc, searchCriterions);
			else
				result = request.listArriveePersonnel(start, numRows, property,
						asc);
		}

		result.with("region");
		result.with("region.nom");
		result.with("districtSante");
		result.with("districtSante.nom");
		result.with("CDT");
		result.with("personnel");

		return result;
	}

	@Override
	public Request<Long> getTotalRowCount() {
		ArriveePersonnelRequest request = (ArriveePersonnelRequest) getContext();

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedArriveePersonnel(
									property, searchCriterions);
						else
							return request
									.countNonAffectedArriveePersonnelReverse(
											property, searchCriterions);
					} else
						return request.countArriveePersonnel(searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedArriveePersonnel(
									property, filterCriteria);
						else
							return request
									.countNonAffectedArriveePersonnelReverse(
											property, filterCriteria);
					} else
						return request.countArriveePersonnel(filterCriteria);
				}

			} else
				return request.countNonAffectedArriveePersonnel("id");
		} else {

			if (searchCriterions != null) {
				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedArriveePersonnel(
								property, searchCriterions);
					else
						return request.countNonAffectedArriveePersonnelReverse(
								property, searchCriterions);
				} else
					return request.countArriveePersonnel(searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						return request
								.countNonAffectedArriveePersonnel(property);
					else
						return request
								.countNonAffectedArriveePersonnelReverse(property);
				} else
					return request.countArriveePersonnel();
			}
		}

	}

	@Override
	public RequestContext getEntityContext() {
		return requestFactory.arriveePersonnelRequest();
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

			ArriveePersonnelRequest request = (ArriveePersonnelRequest) getContext();
			newRequest = false;

			ImogJunctionProxy junction = request
					.create(ImogConjunctionProxy.class);
			List<ImogCriterionProxy> criterias = new ArrayList<ImogCriterionProxy>();

			ImogJunctionProxy disJunction = request
					.create(ImogDisjunctionProxy.class);
			List<ImogCriterionProxy> criterionList = new ArrayList<ImogCriterionProxy>();

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
						+ NLS.constants().arriveePersonnel_field_dateArrivee()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(dateArriveeCrit);
			} catch (Exception ex) {/*criteria not added*/
			}

			// Search field Nom
			BasicCriteriaProxy region_nomCrit = request
					.create(BasicCriteriaProxy.class);
			if (locale.equals("fr"))
				region_nomCrit.setField("region.nom.francais");
			if (locale.equals("en"))
				region_nomCrit.setField("region.nom.english");
			region_nomCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			region_nomCrit.setValue(text);
			buffer.append("(" + NLS.constants().region_field_nom() + ": "
					+ text + ") " + SYMBOL_OR + " ");
			criterionList.add(region_nomCrit);

			// Search field Nom
			BasicCriteriaProxy districtSante_nomCrit = request
					.create(BasicCriteriaProxy.class);
			if (locale.equals("fr"))
				districtSante_nomCrit.setField("districtSante.nom.francais");
			if (locale.equals("en"))
				districtSante_nomCrit.setField("districtSante.nom.english");
			districtSante_nomCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			districtSante_nomCrit.setValue(text);
			buffer.append("(" + NLS.constants().districtSante_field_nom()
					+ ": " + text + ") " + SYMBOL_OR + " ");
			criterionList.add(districtSante_nomCrit);

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

			// Search field Nom
			BasicCriteriaProxy personnel_nomCrit = request
					.create(BasicCriteriaProxy.class);
			personnel_nomCrit.setField("personnel.nom");
			personnel_nomCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			personnel_nomCrit.setValue(text);
			buffer.append("(" + NLS.constants().personnel_field_nom() + ": "
					+ text + ") " + SYMBOL_OR + " ");
			criterionList.add(personnel_nomCrit);

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
