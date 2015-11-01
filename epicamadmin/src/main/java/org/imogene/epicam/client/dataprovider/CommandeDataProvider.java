package org.imogene.epicam.client.dataprovider;

import java.util.ArrayList;
import java.util.List;

import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.constants.EpicamEnumConstants;
import org.imogene.epicam.shared.proxy.CommandeProxy;
import org.imogene.epicam.shared.request.CommandeRequest;
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
 * Data provider to feed the Commande entry Table and Selection List
 * @author MEDES-IMPS
 */
public class CommandeDataProvider extends ImogBeanDataProvider<CommandeProxy> {

	private final EpicamRequestFactory requestFactory;
	private boolean nonAffected = false;
	private boolean searchInReverse = false;
	private String property = null;

	/**
	 * For Relation Fields
	 * Provides instances of entity Commande with pagination
	 */
	public CommandeDataProvider(EpicamRequestFactory requestFactory) {
		this.requestFactory = requestFactory;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides instances of entity Commande that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 */
	public CommandeDataProvider(EpicamRequestFactory requestFactory,
			String pProperty) {
		this.requestFactory = requestFactory;
		nonAffected = true;
		property = pProperty;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides filtered instances of entity Commande that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 * @param searchInReverse true for 1:1 relations, if the property for which non affected values are searched shall be looked in reverse relation
	 */
	public CommandeDataProvider(EpicamRequestFactory requestFactory,
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
	public Request<List<CommandeProxy>> getList(int start, int numRows) {

		CommandeRequest request = (CommandeRequest) getContext();
		Request<List<CommandeProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedCommande(start,
									numRows, "date", false, searchCriterions,
									property);
						else
							result = request.listNonAffectedCommandeReverse(
									start, numRows, "date", false,
									searchCriterions, property);
					} else
						result = request.listCommande(start, numRows, "date",
								false, searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedCommande(start,
									numRows, "date", false, filterCriteria,
									property);
						else
							result = request.listNonAffectedCommandeReverse(
									start, numRows, "date", false,
									filterCriteria, property);
					} else
						result = request.listCommande(start, numRows, "date",
								false, filterCriteria);
				}

			} else
				result = request.getCommandeEmptyList();
		} else {
			if (searchCriterions != null) {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedCommande(start,
								numRows, "date", false, searchCriterions,
								property);
					else
						result = request.listNonAffectedCommandeReverse(start,
								numRows, "date", false, searchCriterions,
								property);
				} else
					result = request.listCommande(start, numRows, "date",
							false, searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedCommande(start,
								numRows, "date", false, property);
					else
						result = request.listNonAffectedCommandeReverse(start,
								numRows, "date", false, property);
				} else
					result = request
							.listCommande(start, numRows, "date", false);
			}
		}

		if (isFiltered) {
			result.with("CDT");
		}

		else {
			result.with("CDT");
		}

		return result;
	}

	/**
	 * Called by Dynatable
	 */
	@Override
	public Request<List<CommandeProxy>> getList(String property, int start,
			int numRows, boolean asc) {

		CommandeRequest request = (CommandeRequest) getContext();
		Request<List<CommandeProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null)
					/* permanent filter added to search criterion */
					result = request.listCommande(start, numRows, property,
							asc, searchCriterions);
				else
					/* permanent filter only */
					result = request.listCommande(start, numRows, property,
							asc, filterCriteria);

			} else
				result = request.getCommandeEmptyList();
		} else {
			if (searchCriterions != null)
				result = request.listCommande(start, numRows, property, asc,
						searchCriterions);
			else
				result = request.listCommande(start, numRows, property, asc);
		}

		result.with("CDT");

		return result;
	}

	@Override
	public Request<Long> getTotalRowCount() {
		CommandeRequest request = (CommandeRequest) getContext();

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedCommande(property,
									searchCriterions);
						else
							return request.countNonAffectedCommandeReverse(
									property, searchCriterions);
					} else
						return request.countCommande(searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedCommande(property,
									filterCriteria);
						else
							return request.countNonAffectedCommandeReverse(
									property, filterCriteria);
					} else
						return request.countCommande(filterCriteria);
				}

			} else
				return request.countNonAffectedCommande("id");
		} else {

			if (searchCriterions != null) {
				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedCommande(property,
								searchCriterions);
					else
						return request.countNonAffectedCommandeReverse(
								property, searchCriterions);
				} else
					return request.countCommande(searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedCommande(property);
					else
						return request
								.countNonAffectedCommandeReverse(property);
				} else
					return request.countCommande();
			}
		}

	}

	@Override
	public RequestContext getEntityContext() {
		return requestFactory.commandeRequest();
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

			CommandeRequest request = (CommandeRequest) getContext();
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

			// Search field Date
			try {
				DateUtil.parseDate(text);
				BasicCriteriaProxy dateCrit = request
						.create(BasicCriteriaProxy.class);
				dateCrit.setField("date");
				dateCrit.setOperation(CriteriaConstants.DATE_OPERATOR_EQUAL);
				dateCrit.setValue(text);
				buffer.append("(" + NLS.constants().commande_field_date()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(dateCrit);
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
