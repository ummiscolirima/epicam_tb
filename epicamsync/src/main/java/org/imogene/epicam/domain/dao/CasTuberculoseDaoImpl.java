package org.imogene.epicam.domain.dao;

import java.util.List;
import java.util.Vector;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.imogene.lib.common.criteria.DaoUtil;
import org.imogene.lib.common.criteria.ImogJunction;
import org.imogene.lib.common.dao.ImogBeanDaoImpl;
import org.imogene.lib.common.entity.ImogActor;
import org.imogene.lib.sync.server.util.HttpSessionUtil;
import org.imogene.epicam.domain.entity.*;
/**
 * Manage persistence for CasTuberculose
 */
public class CasTuberculoseDaoImpl extends ImogBeanDaoImpl<CasTuberculose>
		implements
			CasTuberculoseDao {

	protected CasTuberculoseDaoImpl() {
		super(CasTuberculose.class);
	}

	@Override
	public void delete() {
		//TODO
	}

	/* relation dependencies */

	/**
	 * List associated ExamenMicroscopie, 
	 * on the field examensMiscrocopies
	 * @param parent the parent entity
	 * @return the list of the associated entities
	 */
	@Override
	public List<ExamenMicroscopie> loadExamensMiscrocopies(CasTuberculose parent) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ExamenMicroscopie> query = builder
				.createQuery(ExamenMicroscopie.class);
		Root<ExamenMicroscopie> root = query.from(ExamenMicroscopie.class);
		query.select(root);
		query.where(builder.equal(root.<CasTuberculose> get("casTb"), parent));
		return em.createQuery(query).getResultList();
	}

	/**
	 * List associated ExamenATB, 
	 * on the field examensATB
	 * @param parent the parent entity
	 * @return the list of the associated entities
	 */
	@Override
	public List<ExamenATB> loadExamensATB(CasTuberculose parent) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ExamenATB> query = builder.createQuery(ExamenATB.class);
		Root<ExamenATB> root = query.from(ExamenATB.class);
		query.select(root);
		query.where(builder.equal(root.<CasTuberculose> get("casTb"), parent));
		return em.createQuery(query).getResultList();
	}

	/**
	 * List associated PriseMedicamenteuse, 
	 * on the field priseMedicamenteusePhaseInitiale
	 * @param parent the parent entity
	 * @return the list of the associated entities
	 */
	@Override
	public List<PriseMedicamenteuse> loadPriseMedicamenteusePhaseInitiale(
			CasTuberculose parent) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<PriseMedicamenteuse> query = builder
				.createQuery(PriseMedicamenteuse.class);
		Root<PriseMedicamenteuse> root = query.from(PriseMedicamenteuse.class);
		query.select(root);
		query.where(builder.equal(root.<CasTuberculose> get("phaseIntensive"),
				parent));
		return em.createQuery(query).getResultList();
	}

	/**
	 * List associated PriseMedicamenteuse, 
	 * on the field priseMedicamenteusePhaseContinuation
	 * @param parent the parent entity
	 * @return the list of the associated entities
	 */
	@Override
	public List<PriseMedicamenteuse> loadPriseMedicamenteusePhaseContinuation(
			CasTuberculose parent) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<PriseMedicamenteuse> query = builder
				.createQuery(PriseMedicamenteuse.class);
		Root<PriseMedicamenteuse> root = query.from(PriseMedicamenteuse.class);
		query.select(root);
		query.where(builder.equal(
				root.<CasTuberculose> get("phaseContinuation"), parent));
		return em.createQuery(query).getResultList();
	}

	/**
	 * List associated RendezVous, 
	 * on the field rendezVous
	 * @param parent the parent entity
	 * @return the list of the associated entities
	 */
	@Override
	public List<RendezVous> loadRendezVous(CasTuberculose parent) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<RendezVous> query = builder.createQuery(RendezVous.class);
		Root<RendezVous> root = query.from(RendezVous.class);
		query.select(root);
		query.where(builder.equal(root.<CasTuberculose> get("casTb"), parent));
		return em.createQuery(query).getResultList();
	}

	@Override
	protected Predicate getFilter(Root<CasTuberculose> root,
			CriteriaBuilder builder) {
		ImogActor actor = HttpSessionUtil.getCurrentUser();
		if (actor instanceof Personnel) {
			Personnel personnel = (Personnel) actor;
			String niveau = personnel.getNiveau();
			if ("1".equals(niveau)) {
				Path<?> join = DaoUtil.getCascadeRoot(root.join("patient")
						.join("centres", JoinType.LEFT), "region.id");
				return builder.equal(join, personnel.getRegion().getId());
			} else if ("2".equals(niveau)) {
				Path<?> join = DaoUtil.getCascadeRoot(root.join("patient")
						.join("centres", JoinType.LEFT), "districtSante.id");
				return builder
						.equal(join, personnel.getDistrictSante().getId());
			} else if ("3".equals(niveau)) {
				Path<?> join = root.join("patient")
						.join("centres", JoinType.LEFT).get("id");
				return builder.equal(join, personnel.getCDT().getId());
			}
		}
		return null;
	}

}
