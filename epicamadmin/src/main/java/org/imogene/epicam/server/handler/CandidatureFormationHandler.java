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
 * A data handler for the CandidatureFormation beans 
 * @author Medes-IMPS
 */
public class CandidatureFormationHandler {

	private CandidatureFormationDao dao;

	private ImogBeanFilter filter;
	private HandlerHelper handlerHelper;

	/**
	 * Loads the entity with the specified id
	 * @param entityId the entity id
	 * @return the entity or null
	 */
	@Transactional(readOnly = true)
	public CandidatureFormation findById(String entityId) {
		return dao.load(entityId);
	}

	/**
	 * Loads the entity with the specified id
	 * @param entityId the entity id
	 * @return the entity or null
	 */
	@Transactional(readOnly = true)
	public CandidatureFormation getById(String entityId) {
		return dao.getById(entityId);
	}

	/**
	 * Saves or updates the entity
	 * @param entity the entity to be saved or updated
	 * @param isNew true if it is a new entity added for the first time.
	 */
	@Transactional
	public void save(CandidatureFormation entity, boolean isNew) {

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
	 * Lists the entities of type CandidatureFormation
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @return list of candidatureFormation
	 */
	@Transactional(readOnly = true)
	public List<CandidatureFormation> listCandidatureFormation(
			String sortProperty, boolean sortOrder) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		List<CandidatureFormation> beans = dao.load(sortProperty, sortOrder,
				junction);

		return beans;
	}

	/**
	 * Lists the entities of type CandidatureFormation
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @return list of candidatureFormation
	 */
	@Transactional(readOnly = true)
	public List<CandidatureFormation> listCandidatureFormation(
			String sortProperty, boolean sortOrder, ImogJunction junction) {

		List<CandidatureFormation> beans = dao.load(sortProperty, sortOrder,
				junction);

		return beans;
	}

	/**
	 * Lists the entities of type CandidatureFormation
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @return list of candidatureFormation
	 */
	@Transactional(readOnly = true)
	public List<CandidatureFormation> listCandidatureFormation(int i, int j,
			String sortProperty, boolean sortOrder) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		List<CandidatureFormation> beans = dao.load(i, j, sortProperty,
				sortOrder, junction);

		return beans;
	}

	/**
	 * Lists the entities of type CandidatureFormation
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param criterions request criteria	 
	 * @return list of candidatureFormation
	 */
	@Transactional(readOnly = true)
	public List<CandidatureFormation> listCandidatureFormation(int i, int j,
			String sortProperty, boolean sortOrder, ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		List<CandidatureFormation> beans = dao.load(i, j, sortProperty,
				sortOrder, junction);

		return beans;
	}

	/**
	 * Lists the entities of type CandidatureFormation
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param criterions request criteria	 
	 * @return list of candidatureFormation
	 */
	@Transactional(readOnly = true)
	public List<CandidatureFormation> listCandidatureFormation(int i, int j,
			String sortProperty, boolean sortOrder,
			List<BasicCriteria> criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		ImogJunction junctionForCrit = new ImogConjunction();
		if (criterions != null) {
			for (BasicCriteria crit : criterions)
				junctionForCrit.add(crit);
		}
		junction.add(junctionForCrit);

		List<CandidatureFormation> beans = dao.load(i, j, sortProperty,
				sortOrder, junction);

		return beans;
	}

	/**
	 * Lists the non affected entities of type CandidatureFormation	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param criterion request criteria	 
	 * @param property the property which is not affected
	 * @return list of candidatureFormation
	 */
	@Transactional(readOnly = true)
	public List<CandidatureFormation> listNonAffectedCandidatureFormation(
			int i, int j, String sortProperty, boolean sortOrder,
			ImogJunction criterions, String property) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		List<CandidatureFormation> beans = dao.loadNonAffected(i, j,
				sortProperty, sortOrder, property, junction);

		return beans;
	}

	/**
	 * Lists the non affected entities of type CandidatureFormation	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param property the property which is not affected
	 * @return list of candidatureFormation
	 */
	@Transactional(readOnly = true)
	public List<CandidatureFormation> listNonAffectedCandidatureFormation(
			int i, int j, String sortProperty, boolean sortOrder,
			String property) {
		return listNonAffectedCandidatureFormation(i, j, sortProperty,
				sortOrder, null, property);
	}

	/**
	 * Used when CandidatureFormation is involved in a Relation 1 <-> 1 
	 * Association and is the ReverseRelationField of the Relation
	 * Return all instance of CandidatureFormation non affected
	 * regarding specified property.	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param ImogJunction request criteria
	 * @param property the property which is not affected	 
	 * @return list of candidatureFormation
	 */
	@Transactional(readOnly = true)
	public List<CandidatureFormation> listNonAffectedCandidatureFormationReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			ImogJunction criterions, String property) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		List<CandidatureFormation> beans = dao.loadNonAffectedReverse(i, j,
				sortProperty, sortOrder, property, junction);

		return beans;
	}

	/**
	 * Used when CandidatureFormation is involved in a Relation 1 <-> 1 
	 * Association and is the ReverseRelationField of the Relation
	 * Return all instance of CandidatureFormation non affected
	 * regarding specified property.	
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param property the property which is not affected	 
	 * @return list of candidatureFormation
	 */
	@Transactional(readOnly = true)
	public List<CandidatureFormation> listNonAffectedCandidatureFormationReverse(
			int i, int j, String sortProperty, boolean sortOrder,
			String property) {
		return listNonAffectedCandidatureFormationReverse(i, j, sortProperty,
				sortOrder, null, property);
	}

	/**
	 * Gets an empty list of CandidatureFormation	
	 * @return an empty list of CandidatureFormation
	 */
	@Transactional(readOnly = true)
	public List<CandidatureFormation> getCandidatureFormationEmptyList() {
		return new ArrayList<CandidatureFormation>();
	}

	/**
	 * Counts the number of candidatureFormation in the database
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countCandidatureFormation() {
		return countCandidatureFormation(null);
	}

	/**
	 * Counts the number of candidatureFormation in the database, 
	 * that match the criteria
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countCandidatureFormation(ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		return dao.count(junction);
	}

	/**
	 * Counts the number of non affected candidatureFormation entities in the database
	 * @param property the property which is not affected
	 * @param criterion request criteria
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedCandidatureFormation(String property,
			ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		return dao.countNonAffected(property, junction);
	}

	/**
	 * Counts the number of non affected candidatureFormation entities in the database
	 * @param property the property which is not affected
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedCandidatureFormation(String property) {
		return countNonAffectedCandidatureFormation(property, null);
	}

	/**
	 * Counts the number of non affected candidatureFormation entities in the database
	 * @param property the property which is not affected
	 * @param criterion request criteria
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedCandidatureFormationReverse(String property,
			ImogJunction criterions) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);
		return dao.countNonAffectedReverse(property, junction);
	}

	/**
	 * Counts the number of non affected candidatureFormation entities in the database
	 * @param property the property which is not affected
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedCandidatureFormationReverse(String property) {
		return countNonAffectedCandidatureFormationReverse(property, null);
	}

	/**
	 * Deletes a group of entities identified by their IDs
	 * @param ids Entities to delete IDs
	 * @return The list of deleted entities IDs
	 */
	@Transactional
	public void delete(Set<CandidatureFormation> entities) {
		if (entities != null) {
			for (CandidatureFormation entity : entities)
				delete(entity);
		}
	}

	/**
	 * Removes the specified entity from the database 
	 * @param entity The entity to be deleted
	 */
	@Transactional
	public void delete(CandidatureFormation entity) {

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
	 * Lists the entities of type CandidatureFormation for the CSV export  
	 */
	@Transactional(readOnly = true)
	public List<CandidatureFormation> listForCsv(String sortProperty,
			boolean sortOrder, String personnel_nom, String cDT_nom,
			String approuveeRegion, String approuveeGTC,
			String districtSante_nom_francais, String districtSante_nom_english) {

		ImogActor actor = HttpSessionUtil.getCurrentUser();
		ImogJunction junction = createFilterJuntion(actor);

		if (personnel_nom != null && !personnel_nom.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("personnel.nom");
			criteria.setValue(personnel_nom);
			junction.add(criteria);
		}
		if (cDT_nom != null && !cDT_nom.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("cDT.nom");
			criteria.setValue(cDT_nom);
			junction.add(criteria);
		}
		if (approuveeRegion != null && !approuveeRegion.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.BOOLEAN_OPERATOR_EQUAL);
			criteria.setField("approuveeRegion");
			criteria.setValue(approuveeRegion);
			junction.add(criteria);
		}
		if (approuveeGTC != null && !approuveeGTC.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.BOOLEAN_OPERATOR_EQUAL);
			criteria.setField("approuveeGTC");
			criteria.setValue(approuveeGTC);
			junction.add(criteria);
		}
		if (districtSante_nom_francais != null
				&& !districtSante_nom_francais.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("districtSante.nom.francais");
			criteria.setValue(districtSante_nom_francais);
			junction.add(criteria);
		}
		if (districtSante_nom_english != null
				&& !districtSante_nom_english.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("districtSante.nom.english");
			criteria.setValue(districtSante_nom_english);
			junction.add(criteria);
		}

		List<CandidatureFormation> beans = dao.load(sortProperty, sortOrder,
				junction);
		List<CandidatureFormation> securedBeans = filter
				.<CandidatureFormation> toSecure(beans);
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
	 * @param dao the CandidatureFormation Dao
	 */
	public void setDao(CandidatureFormationDao dao) {
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
