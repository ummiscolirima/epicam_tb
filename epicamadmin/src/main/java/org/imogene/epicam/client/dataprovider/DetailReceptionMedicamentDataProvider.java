package org.imogene.epicam.client.dataprovider;

import java.util.ArrayList;
import java.util.List;

import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.constants.EpicamEnumConstants;
import org.imogene.epicam.shared.proxy.DetailReceptionMedicamentProxy;
import org.imogene.epicam.shared.request.DetailReceptionMedicamentRequest;
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
 * Data provider to feed the DetailReceptionMedicament entry Table and Selection List
 * @author MEDES-IMPS
 */
public class DetailReceptionMedicamentDataProvider
		extends
			ImogBeanDataProvider<DetailReceptionMedicamentProxy> {

	private final EpicamRequestFactory requestFactory;
	private boolean nonAffected = false;
	private boolean searchInReverse = false;
	private String property = null;

	/**
	 * For Relation Fields
	 * Provides instances of entity DetailReceptionMedicament with pagination
	 */
	public DetailReceptionMedicamentDataProvider(
			EpicamRequestFactory requestFactory) {
		this.requestFactory = requestFactory;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides instances of entity DetailReceptionMedicament that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 */
	public DetailReceptionMedicamentDataProvider(
			EpicamRequestFactory requestFactory, String pProperty) {
		this.requestFactory = requestFactory;
		nonAffected = true;
		property = pProperty;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides filtered instances of entity DetailReceptionMedicament that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 * @param searchInReverse true for 1:1 relations, if the property for which non affected values are searched shall be looked in reverse relation
	 */
	public DetailReceptionMedicamentDataProvider(
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
	public Request<List<DetailReceptionMedicamentProxy>> getList(int start,
			int numRows) {

		DetailReceptionMedicamentRequest request = (DetailReceptionMedicamentRequest) getContext();
		Request<List<DetailReceptionMedicamentProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							result = request
									.listNonAffectedDetailReceptionMedicament(
											start, numRows, "modified", false,
											searchCriterions, property);
						else
							result = request
									.listNonAffectedDetailReceptionMedicamentReverse(
											start, numRows, "modified", false,
											searchCriterions, property);
					} else
						result = request.listDetailReceptionMedicament(start,
								numRows, "modified", false, searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							result = request
									.listNonAffectedDetailReceptionMedicament(
											start, numRows, "modified", false,
											filterCriteria, property);
						else
							result = request
									.listNonAffectedDetailReceptionMedicamentReverse(
											start, numRows, "modified", false,
											filterCriteria, property);
					} else
						result = request.listDetailReceptionMedicament(start,
								numRows, "modified", false, filterCriteria);
				}

			} else
				result = request.getDetailReceptionMedicamentEmptyList();
		} else {
			if (searchCriterions != null) {

				if (nonAffected) {
					if (!searchInReverse)
						result = request
								.listNonAffectedDetailReceptionMedicament(
										start, numRows, "modified", false,
										searchCriterions, property);
					else
						result = request
								.listNonAffectedDetailReceptionMedicamentReverse(
										start, numRows, "modified", false,
										searchCriterions, property);
				} else
					result = request.listDetailReceptionMedicament(start,
							numRows, "modified", false, searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						result = request
								.listNonAffectedDetailReceptionMedicament(
										start, numRows, "modified", false,
										property);
					else
						result = request
								.listNonAffectedDetailReceptionMedicamentReverse(
										start, numRows, "modified", false,
										property);
				} else
					result = request.listDetailReceptionMedicament(start,
							numRows, "modified", false);
			}
		}

		if (isFiltered) {
			result.with("commande");
			result.with("commande.CDT");
			result.with("detailCommande");
			result.with("detailCommande.medicament");
		}

		else {
			result.with("detailCommande");
			result.with("detailCommande.medicament");
		}

		return result;
	}

	/**
	 * Called by Dynatable
	 */
	@Override
	public Request<List<DetailReceptionMedicamentProxy>> getList(
			String property, int start, int numRows, boolean asc) {

		DetailReceptionMedicamentRequest request = (DetailReceptionMedicamentRequest) getContext();
		Request<List<DetailReceptionMedicamentProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null)
					/* permanent filter added to search criterion */
					result = request.listDetailReceptionMedicament(start,
							numRows, property, asc, searchCriterions);
				else
					/* permanent filter only */
					result = request.listDetailReceptionMedicament(start,
							numRows, property, asc, filterCriteria);

			} else
				result = request.getDetailReceptionMedicamentEmptyList();
		} else {
			if (searchCriterions != null)
				result = request.listDetailReceptionMedicament(start, numRows,
						property, asc, searchCriterions);
			else
				result = request.listDetailReceptionMedicament(start, numRows,
						property, asc);
		}

		result.with("commande");
		result.with("commande.CDT");
		result.with("detailCommande");
		result.with("detailCommande.medicament");

		return result;
	}

	@Override
	public Request<Long> getTotalRowCount() {
		DetailReceptionMedicamentRequest request = (DetailReceptionMedicamentRequest) getContext();

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							return request
									.countNonAffectedDetailReceptionMedicament(
											property, searchCriterions);
						else
							return request
									.countNonAffectedDetailReceptionMedicamentReverse(
											property, searchCriterions);
					} else
						return request
								.countDetailReceptionMedicament(searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							return request
									.countNonAffectedDetailReceptionMedicament(
											property, filterCriteria);
						else
							return request
									.countNonAffectedDetailReceptionMedicamentReverse(
											property, filterCriteria);
					} else
						return request
								.countDetailReceptionMedicament(filterCriteria);
				}

			} else
				return request.countNonAffectedDetailReceptionMedicament("id");
		} else {

			if (searchCriterions != null) {
				if (nonAffected) {
					if (!searchInReverse)
						return request
								.countNonAffectedDetailReceptionMedicament(
										property, searchCriterions);
					else
						return request
								.countNonAffectedDetailReceptionMedicamentReverse(
										property, searchCriterions);
				} else
					return request
							.countDetailReceptionMedicament(searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						return request
								.countNonAffectedDetailReceptionMedicament(property);
					else
						return request
								.countNonAffectedDetailReceptionMedicamentReverse(property);
				} else
					return request.countDetailReceptionMedicament();
			}
		}

	}

	@Override
	public RequestContext getEntityContext() {
		return requestFactory.detailReceptionMedicamentRequest();
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

			DetailReceptionMedicamentRequest request = (DetailReceptionMedicamentRequest) getContext();
			newRequest = false;

			ImogJunctionProxy junction = request
					.create(ImogConjunctionProxy.class);
			List<ImogCriterionProxy> criterias = new ArrayList<ImogCriterionProxy>();

			ImogJunctionProxy disJunction = request
					.create(ImogDisjunctionProxy.class);
			List<ImogCriterionProxy> criterionList = new ArrayList<ImogCriterionProxy>();

			// Search field Nom
			BasicCriteriaProxy commande_cDT_nomCrit = request
					.create(BasicCriteriaProxy.class);
			commande_cDT_nomCrit.setField("commande.cDT.nom");
			commande_cDT_nomCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			commande_cDT_nomCrit.setValue(text);
			buffer.append("(" + NLS.constants().centreDiagTrait_field_nom()
					+ ": " + text + ") " + SYMBOL_OR + " ");
			criterionList.add(commande_cDT_nomCrit);
			// Search field Date
			try {
				DateUtil.parseDate(text);
				BasicCriteriaProxy commande_dateCrit = request
						.create(BasicCriteriaProxy.class);
				commande_dateCrit.setField("commande.date");
				commande_dateCrit
						.setOperation(CriteriaConstants.DATE_OPERATOR_EQUAL);
				commande_dateCrit.setValue(text);
				buffer.append("(" + NLS.constants().commande_field_date()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(commande_dateCrit);
			} catch (Exception ex) {/*criteria not added*/
			}

			// Search field Designation
			BasicCriteriaProxy detailCommande_medicament_designationCrit = request
					.create(BasicCriteriaProxy.class);
			detailCommande_medicament_designationCrit
					.setField("detailCommande.medicament.designation");
			detailCommande_medicament_designationCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			detailCommande_medicament_designationCrit.setValue(text);
			buffer.append("(" + NLS.constants().medicament_field_designation()
					+ ": " + text + ") " + SYMBOL_OR + " ");
			criterionList.add(detailCommande_medicament_designationCrit);
			// Search field QuantiteRequise
			try {
				Integer.valueOf(text);
				BasicCriteriaProxy detailCommande_quantiteRequiseCrit = request
						.create(BasicCriteriaProxy.class);
				detailCommande_quantiteRequiseCrit
						.setField("detailCommande.quantiteRequise");
				detailCommande_quantiteRequiseCrit
						.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
				detailCommande_quantiteRequiseCrit.setValue(text);
				buffer.append("("
						+ NLS.constants()
								.detailCommandeMedicament_field_quantiteRequise()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(detailCommande_quantiteRequiseCrit);
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
