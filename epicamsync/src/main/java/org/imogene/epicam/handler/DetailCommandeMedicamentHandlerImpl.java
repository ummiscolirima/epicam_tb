package org.imogene.epicam.handler;

import java.util.Date;
import java.util.List;

import org.imogene.epicam.domain.dao.DetailCommandeMedicamentDao;
import org.imogene.epicam.domain.entity.*;
import org.imogene.lib.common.constants.CriteriaConstants;
import org.imogene.lib.common.criteria.BasicCriteria;
import org.imogene.lib.common.criteria.ImogConjunction;
import org.imogene.lib.common.criteria.ImogCriterion;
import org.imogene.lib.common.criteria.ImogDisjunction;
import org.imogene.lib.common.criteria.ImogJunction;
import org.imogene.lib.common.dao.ImogBeanDao;
import org.imogene.lib.common.entity.ImogActor;
import org.imogene.lib.common.filter.ClientFilter;
import org.imogene.lib.common.filter.ClientFilterDao;
import org.imogene.lib.sync.SyncConstants;
import org.imogene.lib.sync.handler.ImogActorHandlerImpl;
import org.imogene.lib.sync.handler.ImogBeanHandlerImpl;
import org.imogene.lib.sync.server.clientfilter.ClientFilterUtil;
import org.imogene.sync.FilterFieldsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Implements a data handler for the DetailCommandeMedicament 
 * @author Medes-IMPS
 */
public class DetailCommandeMedicamentHandlerImpl
		extends
			ImogBeanHandlerImpl<DetailCommandeMedicament>
		implements
			DetailCommandeMedicamentHandler {

	@Autowired
	@Qualifier(value = "detailCommandeMedicamentDao")
	private DetailCommandeMedicamentDao dao;

	@Override
	public DetailCommandeMedicament createNewEntity(String id) {
		//TODO handle  with not null constraint values
		DetailCommandeMedicament entity = new DetailCommandeMedicament();
		entity.setId(id);
		entity.setModified(new Date());
		entity.setCreatedBy(SyncConstants.SYNC_ID_SYS);
		entity.setModifiedBy(SyncConstants.SYNC_ID_SYS);
		entity.setModifiedFrom(SyncConstants.SYNC_ID_SYS);
		return entity;
	}

	@Override
	protected void saveOrUpdate(DetailCommandeMedicament entity, boolean neu) {
		getDao().saveOrUpdate(entity, neu);
	}

	@Override
	protected ImogJunction createFilterJuntion(ImogActor user) {
		ImogConjunction filterConjunction = new ImogConjunction();

		return filterConjunction;
	}

	@Override
	protected ImogJunction createClientFilterJuntion(String userId,
			String terminalId) {
		return null;
	}

	@Override
	protected DetailCommandeMedicamentDao getDao() {
		return dao;
	}

}
