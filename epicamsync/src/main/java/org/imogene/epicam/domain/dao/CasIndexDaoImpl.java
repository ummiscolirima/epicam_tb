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
 * Manage persistence for CasIndex
 */
public class CasIndexDaoImpl extends ImogBeanDaoImpl<CasIndex>
		implements
			CasIndexDao {

	protected CasIndexDaoImpl() {
		super(CasIndex.class);
	}

	@Override
	public void delete() {
		//TODO
	}

	/* relation dependencies */

	@Override
	protected Predicate getFilter(Root<CasIndex> root, CriteriaBuilder builder) {
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
