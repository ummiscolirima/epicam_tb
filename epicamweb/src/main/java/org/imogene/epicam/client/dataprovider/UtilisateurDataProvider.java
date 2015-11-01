package org.imogene.epicam.client.dataprovider;

import java.util.ArrayList;
import java.util.List;

import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.constants.EpicamEnumConstants;
import org.imogene.epicam.shared.proxy.UtilisateurProxy;
import org.imogene.epicam.shared.request.UtilisateurRequest;
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
 * Data provider to feed the Utilisateur entry Table and Selection List
 * @author MEDES-IMPS
 */
public class UtilisateurDataProvider
		extends
			ImogBeanDataProvider<UtilisateurProxy> {

	private final EpicamRequestFactory requestFactory;
	private boolean nonAffected = false;
	private boolean searchInReverse = false;
	private String property = null;

	/**
	 * For Relation Fields
	 * Provides instances of entity Utilisateur with pagination
	 */
	public UtilisateurDataProvider(EpicamRequestFactory requestFactory) {
		this.requestFactory = requestFactory;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides instances of entity Utilisateur that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 */
	public UtilisateurDataProvider(EpicamRequestFactory requestFactory,
			String pProperty) {
		this.requestFactory = requestFactory;
		nonAffected = true;
		property = pProperty;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides filtered instances of entity Utilisateur that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 * @param searchInReverse true for 1:1 relations, if the property for which non affected values are searched shall be looked in reverse relation
	 */
	public UtilisateurDataProvider(EpicamRequestFactory requestFactory,
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
	public Request<List<UtilisateurProxy>> getList(int start, int numRows) {

		UtilisateurRequest request = (UtilisateurRequest) getContext();
		Request<List<UtilisateurProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedUtilisateur(start,
									numRows, "modified", false,
									searchCriterions, property);
						else
							result = request.listNonAffectedUtilisateurReverse(
									start, numRows, "modified", false,
									searchCriterions, property);
					} else
						result = request.listUtilisateur(start, numRows,
								"modified", false, searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedUtilisateur(start,
									numRows, "modified", false, filterCriteria,
									property);
						else
							result = request.listNonAffectedUtilisateurReverse(
									start, numRows, "modified", false,
									filterCriteria, property);
					} else
						result = request.listUtilisateur(start, numRows,
								"modified", false, filterCriteria);
				}

			} else
				result = request.getUtilisateurEmptyList();
		} else {
			if (searchCriterions != null) {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedUtilisateur(start,
								numRows, "modified", false, searchCriterions,
								property);
					else
						result = request.listNonAffectedUtilisateurReverse(
								start, numRows, "modified", false,
								searchCriterions, property);
				} else
					result = request.listUtilisateur(start, numRows,
							"modified", false, searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedUtilisateur(start,
								numRows, "modified", false, property);
					else
						result = request.listNonAffectedUtilisateurReverse(
								start, numRows, "modified", false, property);
				} else
					result = request.listUtilisateur(start, numRows,
							"modified", false);
			}
		}

		if (isFiltered) {
		}

		else {
		}

		return result;
	}

	/**
	 * Called by Dynatable
	 */
	@Override
	public Request<List<UtilisateurProxy>> getList(String property, int start,
			int numRows, boolean asc) {

		UtilisateurRequest request = (UtilisateurRequest) getContext();
		Request<List<UtilisateurProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null)
					/* permanent filter added to search criterion */
					result = request.listUtilisateur(start, numRows, property,
							asc, searchCriterions);
				else
					/* permanent filter only */
					result = request.listUtilisateur(start, numRows, property,
							asc, filterCriteria);

			} else
				result = request.getUtilisateurEmptyList();
		} else {
			if (searchCriterions != null)
				result = request.listUtilisateur(start, numRows, property, asc,
						searchCriterions);
			else
				result = request.listUtilisateur(start, numRows, property, asc);
		}

		return result;
	}

	@Override
	public Request<Long> getTotalRowCount() {
		UtilisateurRequest request = (UtilisateurRequest) getContext();

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedUtilisateur(
									property, searchCriterions);
						else
							return request.countNonAffectedUtilisateurReverse(
									property, searchCriterions);
					} else
						return request.countUtilisateur(searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedUtilisateur(
									property, filterCriteria);
						else
							return request.countNonAffectedUtilisateurReverse(
									property, filterCriteria);
					} else
						return request.countUtilisateur(filterCriteria);
				}

			} else
				return request.countNonAffectedUtilisateur("id");
		} else {

			if (searchCriterions != null) {
				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedUtilisateur(property,
								searchCriterions);
					else
						return request.countNonAffectedUtilisateurReverse(
								property, searchCriterions);
				} else
					return request.countUtilisateur(searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedUtilisateur(property);
					else
						return request
								.countNonAffectedUtilisateurReverse(property);
				} else
					return request.countUtilisateur();
			}
		}

	}

	@Override
	public RequestContext getEntityContext() {
		return requestFactory.utilisateurRequest();
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

			UtilisateurRequest request = (UtilisateurRequest) getContext();
			newRequest = false;

			ImogJunctionProxy junction = request
					.create(ImogConjunctionProxy.class);
			List<ImogCriterionProxy> criterias = new ArrayList<ImogCriterionProxy>();

			ImogJunctionProxy disJunction = request
					.create(ImogDisjunctionProxy.class);
			List<ImogCriterionProxy> criterionList = new ArrayList<ImogCriterionProxy>();

			// Search field Nom
			BasicCriteriaProxy nomCrit = request
					.create(BasicCriteriaProxy.class);
			nomCrit.setField("nom");
			nomCrit.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			nomCrit.setValue(text);
			buffer.append("(" + NLS.constants().utilisateur_field_nom() + ": "
					+ text + ") " + SYMBOL_OR + " ");
			criterionList.add(nomCrit);

			// Search field Profession
			BasicCriteriaProxy professionCrit = request
					.create(BasicCriteriaProxy.class);
			professionCrit.setField("profession");
			professionCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			professionCrit.setValue(text);
			buffer.append("(" + NLS.constants().utilisateur_field_profession()
					+ ": " + text + ") " + SYMBOL_OR + " ");
			criterionList.add(professionCrit);

			// Search field DateNaissance
			try {
				DateUtil.parseDate(text);
				BasicCriteriaProxy dateNaissanceCrit = request
						.create(BasicCriteriaProxy.class);
				dateNaissanceCrit.setField("dateNaissance");
				dateNaissanceCrit
						.setOperation(CriteriaConstants.DATE_OPERATOR_EQUAL);
				dateNaissanceCrit.setValue(text);
				buffer.append("("
						+ NLS.constants().utilisateur_field_dateNaissance()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(dateNaissanceCrit);
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
