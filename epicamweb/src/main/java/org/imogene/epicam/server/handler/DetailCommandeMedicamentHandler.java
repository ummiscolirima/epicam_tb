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
 * A data handler for the DetailCommandeMedicament beans 
 * @author Medes-IMPS
 */
public class DetailCommandeMedicamentHandler {

	private DetailCommandeMedicamentDao dao;

	private ImogBeanFilter filter;
	private HandlerHelper handlerHelper;

	/**
	 * Loads the entity with the specified id
	 * @param entityId the entity id
	 * @return the entity or null
	 */
	@Transactional(readOnly = true)
	public DetailCommandeMedicament findById(String entityId) {
		return dao.load(entityId);
	}

	/**
	 * Loads the entity with the specified id
	 * @param entityId the entity id
	 * @return the entity or null
	 */
	@Transactional(readOnly = true)
	public DetailCommandeMedicament getById(String entityId) {
		return dao.getById(entityId);
	}

	/**
	 * Saves or updates the entity
	 * @param entity the entity to be saved or updated
	 * @param isNew true if it is a new entity added for the first time.
	 */
	@Transactional
	public void save(DetailCommandeMedicament entity, boolean isNew) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();

		if (entity != null) {

			handlerHelper.prepare(entity);
			if (entity.getDeleted() != null)
				entity.setDeleted(null);

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
	 * Lists the entities of type DetailCommandeMedicament
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @return list of detailCommandeMedicament
	 */
	@Transactional(readOnly = true)
	public List<DetailCommandeMedicament> listDetailCommandeMedicament(
			String sortProperty, boolean sortOrder) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		List<DetailCommandeMedicament> beans = dao.load(sortProperty,
				sortOrder, junction);

		return beans;
	}

	/**
	 * Lists the entities of type DetailCommandeMedicament
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @return list of detailCommandeMedicament
	 */
	@Transactional(readOnly = true)
	public List<DetailCommandeMedicament> listDetailCommandeMedicament(
			String sortProperty, boolean sortOrder, ImogJunction junction) {

		List<DetailCommandeMedicament> beans = dao.load(sortProperty,
				sortOrder, junction);

		return beans;
	}

	/**
	 * Lists the entities of type DetailCommandeMedicament
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @return list of detailCommandeMedicament
	 */
	@Transactional(readOnly = true)
	public List<DetailCommandeMedicament> listDetailCommandeMedicament(int i,
			int j, String sortProperty, boolean sortOrder) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		List<DetailCommandeMedicament> beans = dao.load(i, j, sortProperty,
				sortOrder, junction);

		return beans;
	}

	/**
	 * Lists the entities of type DetailCommandeMedicament
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param criterions request criteria	 
	 * @return list of detailCommandeMedicament
	 */
	@Transactional(readOnly = true)
	public List<DetailCommandeMedicament> listDetailCommandeMedicament(int i,
			int j, String sortProperty, boolean sortOrder,
			ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		List<DetailCommandeMedicament> beans = dao.load(i, j, sortProperty,
				sortOrder, junction);

		return beans;
	}

	/**
	 * Lists the entities of type DetailCommandeMedicament
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param criterions request criteria	 
	 * @return list of detailCommandeMedicament
	 */
	@Transactional(readOnly = true)
	public List<DetailCommandeMedicament> listDetailCommandeMedicament(int i,
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

		List<DetailCommandeMedicament> beans = dao.load(i, j, sortProperty,
				sortOrder, junction);

		return beans;
	}

	/**
	 * Lists the non affected entities of type DetailCommandeMedicament	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param criterion request criteria	 
	 * @param property the property which is not affected
	 * @return list of detailCommandeMedicament
	 */
	@Transactional(readOnly = true)
	public List<DetailCommandeMedicament> listNonAffectedDetailCommandeMedicament(
			int i, int j, String sortProperty, boolean sortOrder,
			ImogJunction criterions, String property) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		List<DetailCommandeMedicament> beans = dao.loadNonAffected(i, j,
				sortProperty, sortOrder, property, junction);

		return beans;
	}

	/**
	 * Lists the non affected entities of type DetailCommandeMedicament	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param property the property which is not affected
	 * @return list of detailCommandeMedicament
	 */
	@Transactional(readOnly = true)
	public List<DetailCommandeMedicament> listNonAffectedDetailCommandeMedicament(
			int i, int j, String sortProperty, boolean sortOrder,
			String property) {
		return listNonAffectedDetailCommandeMedicament(i, j, sortProperty,
				sortOrder, null, property);
	}

	/**
	 * Used when DetailCommandeMedicament is involved in a Relation 1 <-> 1 
	 * Association and is the ReverseRelationField of the Relation
	 * Return all instance of DetailCommandeMedicament non affected
	 * regarding specified property.	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param ImogJunction request criteria
	 * @param property the property which is not affected	 
	 * @return list of detailCommandeMedicament
	 */
	@Transactional(readOnly = true)
	public List<DetailCommandeMedicament> listNonAffectedDetailCommandeMedicamentReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			ImogJunction criterions, String property) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		List<DetailCommandeMedicament> beans = dao.loadNonAffectedReverse(i, j,
				sortProperty, sortOrder, property, junction);

		return beans;
	}

	/**
	 * Used when DetailCommandeMedicament is involved in a Relation 1 <-> 1 
	 * Association and is the ReverseRelationField of the Relation
	 * Return all instance of DetailCommandeMedicament non affected
	 * regarding specified property.	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param property the property which is not affected	 
	 * @return list of detailCommandeMedicament
	 */
	@Transactional(readOnly = true)
	public List<DetailCommandeMedicament> listNonAffectedDetailCommandeMedicamentReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			String property) {
		return listNonAffectedDetailCommandeMedicamentReverse(i, j,
				sortProperty, sortOrder, null, property);
	}

	/**
	 * Gets an empty list of DetailCommandeMedicament	
	 * @return an empty list of DetailCommandeMedicament
	 */
	@Transactional(readOnly = true)
	public List<DetailCommandeMedicament> getDetailCommandeMedicamentEmptyList() {
		return new ArrayList<DetailCommandeMedicament>();
	}

	/**
	 * Counts the number of detailCommandeMedicament in the database
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countDetailCommandeMedicament() {
		return countDetailCommandeMedicament(null);
	}

	/**
	 * Counts the number of detailCommandeMedicament in the database, 
	 * that match the criteria
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countDetailCommandeMedicament(ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		return dao.count(junction);
	}

	/**
	 * Counts the number of non affected detailCommandeMedicament entities in the database
	 * @param property the property which is not affected
	 * @param criterion request criteria
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedDetailCommandeMedicament(String property,
			ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		return dao.countNonAffected(property, junction);
	}

	/**
	 * Counts the number of non affected detailCommandeMedicament entities in the database
	 * @param property the property which is not affected
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedDetailCommandeMedicament(String property) {
		return countNonAffectedDetailCommandeMedicament(property, null);
	}

	/**
	 * Counts the number of non affected detailCommandeMedicament entities in the database
	 * @param property the property which is not affected
	 * @param criterion request criteria
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedDetailCommandeMedicamentReverse(
			String property, ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);
		return dao.countNonAffectedReverse(property, junction);
	}

	/**
	 * Counts the number of non affected detailCommandeMedicament entities in the database
	 * @param property the property which is not affected
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedDetailCommandeMedicamentReverse(String property) {
		return countNonAffectedDetailCommandeMedicamentReverse(property, null);
	}

	/**
	 * Deletes a group of entities identified by their IDs
	 * @param ids Entities to delete IDs
	 * @return The list of deleted entities IDs
	 */
	@Transactional
	public void delete(Set<DetailCommandeMedicament> entities) {
		if (entities != null) {
			for (DetailCommandeMedicament entity : entities)
				delete(entity);
		}
	}

	/**
	 * Removes the specified entity from the database 
	 * @param entity The entity to be deleted
	 */
	@Transactional
	public void delete(DetailCommandeMedicament entity) {

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
	 * Lists the entities of type DetailCommandeMedicament for the CSV export  
	 */
	@Transactional(readOnly = true)
	public List<DetailCommandeMedicament> listForCsv(String sortProperty,
			boolean sortOrder, String quantiteRequise, String quantiteEnStock,
			String medicament_designation) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		if (quantiteRequise != null && !quantiteRequise.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
			criteria.setField("quantiteRequise");
			criteria.setValue(quantiteRequise);
			junction.add(criteria);
		}
		if (quantiteEnStock != null && !quantiteEnStock.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.INT_OPERATOR_EQUAL);
			criteria.setField("quantiteEnStock");
			criteria.setValue(quantiteEnStock);
			junction.add(criteria);
		}
		if (medicament_designation != null && !medicament_designation.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("medicament.designation");
			criteria.setValue(medicament_designation);
			junction.add(criteria);
		}

		List<DetailCommandeMedicament> beans = dao.load(sortProperty,
				sortOrder, junction);
		List<DetailCommandeMedicament> securedBeans = filter
				.<DetailCommandeMedicament> toSecure(beans);
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
	 * @param dao the DetailCommandeMedicament Dao
	 */
	public void setDao(DetailCommandeMedicamentDao dao) {
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
