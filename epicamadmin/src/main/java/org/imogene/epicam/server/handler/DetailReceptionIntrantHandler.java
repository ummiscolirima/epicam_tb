package org.imogene.epicam.server.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.imogene.epicam.domain.dao.*;
import org.imogene.epicam.domain.entity.*;
import org.imogene.lib.common.binary.Binary;
import org.imogene.lib.common.constants.CriteriaConstants;
import org.imogene.lib.common.criteria.BasicCriteria;
import org.imogene.lib.common.criteria.ImogConjunction;
import org.imogene.lib.common.criteria.ImogDisjunction;
import org.imogene.lib.common.criteria.ImogJunction;
import org.imogene.lib.common.entity.ImogActor;
import org.imogene.lib.common.entity.ImogBean;
import org.imogene.lib.common.security.ImogBeanFilter;
import org.imogene.web.server.util.FilterFieldsHelper;
import org.imogene.web.server.util.HttpSessionUtil;
import org.imogene.web.server.util.ProfileUtil;
import org.imogene.web.server.handler.HandlerHelper;

import org.springframework.transaction.annotation.Transactional;

/**
 * A data handler for the DetailReceptionIntrant beans 
 * @author Medes-IMPS
 */
public class DetailReceptionIntrantHandler {

	private DetailReceptionIntrantDao dao;

	private ImogBeanFilter filter;
	private HandlerHelper handlerHelper;

	/**
	 * Loads the entity with the specified id
	 * @param entityId the entity id
	 * @return the entity or null
	 */
	@Transactional(readOnly = true)
	public DetailReceptionIntrant findById(String entityId) {
		return dao.load(entityId);
	}

	/**
	 * Loads the entity with the specified id
	 * @param entityId the entity id
	 * @return the entity or null
	 */
	@Transactional(readOnly = true)
	public DetailReceptionIntrant getById(String entityId) {
		return dao.getById(entityId);
	}

	/**
	 * Saves or updates the entity
	 * @param entity the entity to be saved or updated
	 * @param isNew true if it is a new entity added for the first time.
	 */
	@Transactional
	public void save(DetailReceptionIntrant entity, boolean isNew) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();

		if (entity != null) {

			handlerHelper.prepare(entity);
			if (entity.getDeleted() != null)
				entity.setDeleted(null);

			// manage related entreeLot
			EntreeLot det_rec_intad = entity.getEntreeLot();
			if (det_rec_intad != null) {
				handlerHelper.prepare(det_rec_intad);
			}

			dao.saveOrUpdate(entity, isNew);

		}
	}

	/**
	 * Saves or updates the bean
	 * @param entity the bean to be saved or updated
	 * @param isNew true if it is a new entity added for the first time.
	 */
	@Transactional
	public void save(ImogBean entity, boolean isNew) {
		handlerHelper.save(entity, isNew);
	}

	/**
	 * Lists the entities of type DetailReceptionIntrant
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @return list of detailReceptionIntrant
	 */
	@Transactional(readOnly = true)
	public List<DetailReceptionIntrant> listDetailReceptionIntrant(
			String sortProperty, boolean sortOrder) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		List<DetailReceptionIntrant> beans = dao.load(sortProperty, sortOrder,
				junction);

		return beans;
	}

	/**
	 * Lists the entities of type DetailReceptionIntrant
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @return list of detailReceptionIntrant
	 */
	@Transactional(readOnly = true)
	public List<DetailReceptionIntrant> listDetailReceptionIntrant(
			String sortProperty, boolean sortOrder, ImogJunction junction) {

		List<DetailReceptionIntrant> beans = dao.load(sortProperty, sortOrder,
				junction);

		return beans;
	}

	/**
	 * Lists the entities of type DetailReceptionIntrant
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @return list of detailReceptionIntrant
	 */
	@Transactional(readOnly = true)
	public List<DetailReceptionIntrant> listDetailReceptionIntrant(int i,
			int j, String sortProperty, boolean sortOrder) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		List<DetailReceptionIntrant> beans = dao.load(i, j, sortProperty,
				sortOrder, junction);

		return beans;
	}

	/**
	 * Lists the entities of type DetailReceptionIntrant
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param criterions request criteria	 
	 * @return list of detailReceptionIntrant
	 */
	@Transactional(readOnly = true)
	public List<DetailReceptionIntrant> listDetailReceptionIntrant(int i,
			int j, String sortProperty, boolean sortOrder,
			ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		List<DetailReceptionIntrant> beans = dao.load(i, j, sortProperty,
				sortOrder, junction);

		return beans;
	}

	/**
	 * Lists the entities of type DetailReceptionIntrant
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param criterions request criteria	 
	 * @return list of detailReceptionIntrant
	 */
	@Transactional(readOnly = true)
	public List<DetailReceptionIntrant> listDetailReceptionIntrant(int i,
			int j, String sortProperty, boolean sortOrder,
			List<BasicCriteria> criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		ImogJunction junctionForCrit = new ImogConjunction();
		if (criterions != null) {
			for (BasicCriteria crit : criterions)
				junctionForCrit.add(crit);
		}
		junction.add(junctionForCrit);

		List<DetailReceptionIntrant> beans = dao.load(i, j, sortProperty,
				sortOrder, junction);

		return beans;
	}

	/**
	 * Lists the non affected entities of type DetailReceptionIntrant	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param criterion request criteria	 
	 * @param property the property which is not affected
	 * @return list of detailReceptionIntrant
	 */
	@Transactional(readOnly = true)
	public List<DetailReceptionIntrant> listNonAffectedDetailReceptionIntrant(
			int i, int j, String sortProperty, boolean sortOrder,
			ImogJunction criterions, String property) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		List<DetailReceptionIntrant> beans = dao.loadNonAffected(i, j,
				sortProperty, sortOrder, property, junction);

		return beans;
	}

	/**
	 * Lists the non affected entities of type DetailReceptionIntrant	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param property the property which is not affected
	 * @return list of detailReceptionIntrant
	 */
	@Transactional(readOnly = true)
	public List<DetailReceptionIntrant> listNonAffectedDetailReceptionIntrant(
			int i, int j, String sortProperty, boolean sortOrder,
			String property) {
		return listNonAffectedDetailReceptionIntrant(i, j, sortProperty,
				sortOrder, null, property);
	}

	/**
	 * Used when DetailReceptionIntrant is involved in a Relation 1 <-> 1 
	 * Association and is the ReverseRelationField of the Relation
	 * Return all instance of DetailReceptionIntrant non affected
	 * regarding specified property.	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param ImogJunction request criteria
	 * @param property the property which is not affected	 
	 * @return list of detailReceptionIntrant
	 */
	@Transactional(readOnly = true)
	public List<DetailReceptionIntrant> listNonAffectedDetailReceptionIntrantReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			ImogJunction criterions, String property) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		List<DetailReceptionIntrant> beans = dao.loadNonAffectedReverse(i, j,
				sortProperty, sortOrder, property, junction);

		return beans;
	}

	/**
	 * Used when DetailReceptionIntrant is involved in a Relation 1 <-> 1 
	 * Association and is the ReverseRelationField of the Relation
	 * Return all instance of DetailReceptionIntrant non affected
	 * regarding specified property.	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param property the property which is not affected	 
	 * @return list of detailReceptionIntrant
	 */
	@Transactional(readOnly = true)
	public List<DetailReceptionIntrant> listNonAffectedDetailReceptionIntrantReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			String property) {
		return listNonAffectedDetailReceptionIntrantReverse(i, j, sortProperty,
				sortOrder, null, property);
	}

	/**
	 * Gets an empty list of DetailReceptionIntrant	
	 * @return an empty list of DetailReceptionIntrant
	 */
	@Transactional(readOnly = true)
	public List<DetailReceptionIntrant> getDetailReceptionIntrantEmptyList() {
		return new ArrayList<DetailReceptionIntrant>();
	}

	/**
	 * Counts the number of detailReceptionIntrant in the database
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countDetailReceptionIntrant() {
		return countDetailReceptionIntrant(null);
	}

	/**
	 * Counts the number of detailReceptionIntrant in the database, 
	 * that match the criteria
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countDetailReceptionIntrant(ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		return dao.count(junction);
	}

	/**
	 * Counts the number of non affected detailReceptionIntrant entities in the database
	 * @param property the property which is not affected
	 * @param criterion request criteria
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedDetailReceptionIntrant(String property,
			ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		return dao.countNonAffected(property, junction);
	}

	/**
	 * Counts the number of non affected detailReceptionIntrant entities in the database
	 * @param property the property which is not affected
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedDetailReceptionIntrant(String property) {
		return countNonAffectedDetailReceptionIntrant(property, null);
	}

	/**
	 * Counts the number of non affected detailReceptionIntrant entities in the database
	 * @param property the property which is not affected
	 * @param criterion request criteria
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedDetailReceptionIntrantReverse(String property,
			ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);
		return dao.countNonAffectedReverse(property, junction);
	}

	/**
	 * Counts the number of non affected detailReceptionIntrant entities in the database
	 * @param property the property which is not affected
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedDetailReceptionIntrantReverse(String property) {
		return countNonAffectedDetailReceptionIntrantReverse(property, null);
	}

	/**
	 * Deletes a group of entities identified by their IDs
	 * @param ids Entities to delete IDs
	 * @return The list of deleted entities IDs
	 */
	@Transactional
	public void delete(Set<DetailReceptionIntrant> entities) {
		if (entities != null) {
			for (DetailReceptionIntrant entity : entities)
				delete(entity);
		}
	}

	/**
	 * Removes the specified entity from the database 
	 * @param entity The entity to be deleted
	 */
	@Transactional
	public void delete(DetailReceptionIntrant entity) {

		handlerHelper.prepareForDelete(entity);
		dao.saveOrUpdate(entity, false);
	}

	/**
	 * Removes the specified bean from the database 
	 * @param entity The bean to be deleted
	 */
	@Transactional
	public void delete(ImogBean entity) {
		handlerHelper.delete(entity);
	}

	/**
	 * Lists the entities of type DetailReceptionIntrant for the CSV export  
	 */
	@Transactional(readOnly = true)
	public List<DetailReceptionIntrant> listForCsv(String sortProperty,
			boolean sortOrder, String commande_cDT_nom,
			String commande_dateBefore, String commande_dateAfter,
			String detailCommande_intrant_identifiant,
			String detailCommande_quantiteRequise) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		if (commande_cDT_nom != null && !commande_cDT_nom.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("commande.cDT.nom");
			criteria.setValue(commande_cDT_nom);
			junction.add(criteria);
		}
		if (commande_dateBefore != null && !commande_dateBefore.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.DATE_OPERATOR_BEFORE);
			criteria.setField("commande.date");
			criteria.setValue(commande_dateBefore);
			junction.add(criteria);
		}
		if (commande_dateAfter != null && !commande_dateAfter.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.DATE_OPERATOR_AFTER);
			criteria.setField("commande.date");
			criteria.setValue(commande_dateAfter);
			junction.add(criteria);
		}
		if (detailCommande_intrant_identifiant != null
				&& !detailCommande_intrant_identifiant.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("detailCommande.intrant.identifiant");
			criteria.setValue(detailCommande_intrant_identifiant);
			junction.add(criteria);
		}
		if (detailCommande_quantiteRequise != null
				&& !detailCommande_quantiteRequise.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
			criteria.setField("detailCommande.quantiteRequise");
			criteria.setValue(detailCommande_quantiteRequise);
			junction.add(criteria);
		}

		List<DetailReceptionIntrant> beans = dao.load(sortProperty, sortOrder,
				junction);
		List<DetailReceptionIntrant> securedBeans = filter
				.<DetailReceptionIntrant> toSecure(beans);
		return securedBeans;
	}

	/**
	 * Creates a junction based on the filter field declarations, for the current actor.
	 * @param actor the current actor
	 */
	private ImogJunction createFilterJuntion(ImogActor actor) {
		ImogConjunction filterConjunction = new ImogConjunction();
		if (!ProfileUtil.isAdmin(actor))
			filterConjunction.add(handlerHelper.getNotDeletedFilterCriteria());
		return filterConjunction;
	}

	/**
	 * Setter for bean injection
	 * @param dao the DetailReceptionIntrant Dao
	 */
	public void setDao(DetailReceptionIntrantDao dao) {
		this.dao = dao;
	}

	/**
	 * Setter for bean injection
	 * @param imogBeanFilter
	 */
	public void setFilter(ImogBeanFilter filter) {
		this.filter = filter;
	}

	/**
	 * Setter for bean injection.
	 * @param helper
	 */
	public void setHandlerHelper(HandlerHelper helper) {
		this.handlerHelper = helper;
	}

}
