package org.imogene.epicam.client.dataprovider;

import java.util.ArrayList;
import java.util.List;

import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.constants.EpicamEnumConstants;
import org.imogene.epicam.shared.proxy.SmsPredefiniProxy;
import org.imogene.epicam.shared.request.SmsPredefiniRequest;
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
 * Data provider to feed the SmsPredefini entry Table and Selection List
 * @author MEDES-IMPS
 */
public class SmsPredefiniDataProvider
		extends
			ImogBeanDataProvider<SmsPredefiniProxy> {

	private final EpicamRequestFactory requestFactory;
	private boolean nonAffected = false;
	private boolean searchInReverse = false;
	private String property = null;

	/**
	 * For Relation Fields
	 * Provides instances of entity SmsPredefini with pagination
	 */
	public SmsPredefiniDataProvider(EpicamRequestFactory requestFactory) {
		this.requestFactory = requestFactory;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides instances of entity SmsPredefini that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 */
	public SmsPredefiniDataProvider(EpicamRequestFactory requestFactory,
			String pProperty) {
		this.requestFactory = requestFactory;
		nonAffected = true;
		property = pProperty;
		setSearchCriterions(null);
	}

	/**
	 * For Relation Fields
	 * Provides filtered instances of entity SmsPredefini that have non affected values for a given property (RelationField with card==1) with pagination
	 * @param pProperty the property for which non affected values are searched
	 * @param searchInReverse true for 1:1 relations, if the property for which non affected values are searched shall be looked in reverse relation
	 */
	public SmsPredefiniDataProvider(EpicamRequestFactory requestFactory,
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
	public Request<List<SmsPredefiniProxy>> getList(int start, int numRows) {

		SmsPredefiniRequest request = (SmsPredefiniRequest) getContext();
		Request<List<SmsPredefiniProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedSmsPredefini(start,
									numRows, "objet", true, searchCriterions,
									property);
						else
							result = request
									.listNonAffectedSmsPredefiniReverse(start,
											numRows, "objet", true,
											searchCriterions, property);
					} else
						result = request.listSmsPredefini(start, numRows,
								"objet", true, searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							result = request.listNonAffectedSmsPredefini(start,
									numRows, "objet", true, filterCriteria,
									property);
						else
							result = request
									.listNonAffectedSmsPredefiniReverse(start,
											numRows, "objet", true,
											filterCriteria, property);
					} else
						result = request.listSmsPredefini(start, numRows,
								"objet", true, filterCriteria);
				}

			} else
				result = request.getSmsPredefiniEmptyList();
		} else {
			if (searchCriterions != null) {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedSmsPredefini(start,
								numRows, "objet", true, searchCriterions,
								property);
					else
						result = request.listNonAffectedSmsPredefiniReverse(
								start, numRows, "objet", true,
								searchCriterions, property);
				} else
					result = request.listSmsPredefini(start, numRows, "objet",
							true, searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						result = request.listNonAffectedSmsPredefini(start,
								numRows, "objet", true, property);
					else
						result = request.listNonAffectedSmsPredefiniReverse(
								start, numRows, "objet", true, property);
				} else
					result = request.listSmsPredefini(start, numRows, "objet",
							true);
			}
		}

		if (isFiltered) {
			result.with("objet");
			result.with("message");
		}

		else {
			result.with("objet");
			result.with("message");
		}

		return result;
	}

	/**
	 * Called by Dynatable
	 */
	@Override
	public Request<List<SmsPredefiniProxy>> getList(String property, int start,
			int numRows, boolean asc) {

		SmsPredefiniRequest request = (SmsPredefiniRequest) getContext();
		Request<List<SmsPredefiniProxy>> result = null;

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null)
					/* permanent filter added to search criterion */
					result = request.listSmsPredefini(start, numRows, property,
							asc, searchCriterions);
				else
					/* permanent filter only */
					result = request.listSmsPredefini(start, numRows, property,
							asc, filterCriteria);

			} else
				result = request.getSmsPredefiniEmptyList();
		} else {
			if (searchCriterions != null)
				result = request.listSmsPredefini(start, numRows, property,
						asc, searchCriterions);
			else
				result = request
						.listSmsPredefini(start, numRows, property, asc);
		}

		result.with("objet");
		result.with("message");

		return result;
	}

	@Override
	public Request<Long> getTotalRowCount() {
		SmsPredefiniRequest request = (SmsPredefiniRequest) getContext();

		if (isFiltered) {
			/* permanently filtered - hierarchical lists */
			if (filterCriteria != null) {

				if (searchCriterions != null) {
					/* permanent filter added to search criterion */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedSmsPredefini(
									property, searchCriterions);
						else
							return request.countNonAffectedSmsPredefiniReverse(
									property, searchCriterions);
					} else
						return request.countSmsPredefini(searchCriterions);
				} else {
					/* permanent filter only */
					if (nonAffected) {
						if (!searchInReverse)
							return request.countNonAffectedSmsPredefini(
									property, filterCriteria);
						else
							return request.countNonAffectedSmsPredefiniReverse(
									property, filterCriteria);
					} else
						return request.countSmsPredefini(filterCriteria);
				}

			} else
				return request.countNonAffectedSmsPredefini("id");
		} else {

			if (searchCriterions != null) {
				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedSmsPredefini(property,
								searchCriterions);
					else
						return request.countNonAffectedSmsPredefiniReverse(
								property, searchCriterions);
				} else
					return request.countSmsPredefini(searchCriterions);
			} else {

				if (nonAffected) {
					if (!searchInReverse)
						return request.countNonAffectedSmsPredefini(property);
					else
						return request
								.countNonAffectedSmsPredefiniReverse(property);
				} else
					return request.countSmsPredefini();
			}
		}

	}

	@Override
	public RequestContext getEntityContext() {
		return requestFactory.smsPredefiniRequest();
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

			SmsPredefiniRequest request = (SmsPredefiniRequest) getContext();
			newRequest = false;

			ImogJunctionProxy junction = request
					.create(ImogConjunctionProxy.class);
			List<ImogCriterionProxy> criterias = new ArrayList<ImogCriterionProxy>();

			ImogJunctionProxy disJunction = request
					.create(ImogDisjunctionProxy.class);
			List<ImogCriterionProxy> criterionList = new ArrayList<ImogCriterionProxy>();

			// Search field Type
			if (text.toLowerCase().equals(
					NLS.constants().smsPredefini_type_sensibilisation_option()
							.toLowerCase())) {
				BasicCriteriaProxy typeCrit = request
						.create(BasicCriteriaProxy.class);
				typeCrit.setField("type");
				typeCrit.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
				typeCrit.setValue(EpicamEnumConstants.SMSPREDEFINI_TYPE_SENSIBILISATION);
				buffer.append("(" + NLS.constants().smsPredefini_field_type()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(typeCrit);
			}
			if (text.toLowerCase().equals(
					NLS.constants().smsPredefini_type_quizz_option()
							.toLowerCase())) {
				BasicCriteriaProxy typeCrit = request
						.create(BasicCriteriaProxy.class);
				typeCrit.setField("type");
				typeCrit.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
				typeCrit.setValue(EpicamEnumConstants.SMSPREDEFINI_TYPE_QUIZZ);
				buffer.append("(" + NLS.constants().smsPredefini_field_type()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(typeCrit);
			}
			if (text.toLowerCase().equals(
					NLS.constants().smsPredefini_type_rappelRDV_option()
							.toLowerCase())) {
				BasicCriteriaProxy typeCrit = request
						.create(BasicCriteriaProxy.class);
				typeCrit.setField("type");
				typeCrit.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
				typeCrit.setValue(EpicamEnumConstants.SMSPREDEFINI_TYPE_RAPPELRDV);
				buffer.append("(" + NLS.constants().smsPredefini_field_type()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(typeCrit);
			}
			if (text.toLowerCase().equals(
					NLS.constants().smsPredefini_type_medicalRecord_option()
							.toLowerCase())) {
				BasicCriteriaProxy typeCrit = request
						.create(BasicCriteriaProxy.class);
				typeCrit.setField("type");
				typeCrit.setOperation(CriteriaConstants.STRING_OPERATOR_EQUAL);
				typeCrit.setValue(EpicamEnumConstants.SMSPREDEFINI_TYPE_MEDICALRECORD);
				buffer.append("(" + NLS.constants().smsPredefini_field_type()
						+ ": " + text + ") " + SYMBOL_OR + " ");
				criterionList.add(typeCrit);
			}

			// Search field Objet
			BasicCriteriaProxy objetCrit = request
					.create(BasicCriteriaProxy.class);
			if (locale.equals("fr"))
				objetCrit.setField("objet.francais");
			if (locale.equals("en"))
				objetCrit.setField("objet.english");
			objetCrit.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			objetCrit.setValue(text);
			buffer.append("(" + NLS.constants().smsPredefini_field_objet()
					+ ": " + text + ") " + SYMBOL_OR + " ");
			criterionList.add(objetCrit);

			// Search field Message
			BasicCriteriaProxy messageCrit = request
					.create(BasicCriteriaProxy.class);
			if (locale.equals("fr"))
				messageCrit.setField("message.francais");
			if (locale.equals("en"))
				messageCrit.setField("message.english");
			messageCrit
					.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			messageCrit.setValue(text);
			buffer.append("(" + NLS.constants().smsPredefini_field_message()
					+ ": " + text + ") " + SYMBOL_OR + " ");
			criterionList.add(messageCrit);

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
