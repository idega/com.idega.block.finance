package com.idega.block.finance.dao.impl;

import java.util.List;
import java.util.logging.Level;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.idega.block.finance.dao.PriceDAO;
import com.idega.block.finance.hibernate.data.Price;
import com.idega.core.persistence.Param;
import com.idega.core.persistence.impl.GenericDaoImpl;
import com.idega.util.ListUtil;
import com.idega.util.StringUtil;

@Repository(PriceDAO.BEAN_NAME)
@Scope(BeanDefinition.SCOPE_SINGLETON)
@Transactional(readOnly = true)
public class PriceDAOImpl extends GenericDaoImpl implements PriceDAO {

	@Override
	public Price getById(Long periodId) {
		try {
			return find(Price.class, periodId);
		} catch (Exception e) {
			getLogger().log(Level.WARNING, "Error getting period by ID: " + periodId, e);
		}
		return null;
	}



	@Override
	public List<Price> getAllByPeriodId(Integer periodId) {
		if (periodId == null) {
			return null;
		}

		try {
			return getResultList(Price.GET_PRICES_BY_PERIOD_ID,
							     Price.class,
							     new Param(Price.PARAM_PERIOD_ID, periodId));
		} catch (Exception e) {
			getLogger().log(Level.WARNING, "Error getting prices by period id: " + periodId, e);
		}
		return null;
	}


	@Override
	public List<Price> getAllByIds(List<Long> priceIds) {
		if (ListUtil.isEmpty(priceIds)) {
			return null;
		}

		try {
			return getResultList(Price.GET_PRICES_BY_IDS,
							     Price.class,
							     new Param(Price.PARAM_PRICE_IDS, priceIds));
		} catch (Exception e) {
			getLogger().log(Level.WARNING, "Error getting prices by price ids: " + priceIds, e);
		}
		return null;
	}


	@Override
	public List<Price> getAllByPeriodIdAndExtraType(Integer periodId, String extraType) {
		if (periodId == null) {
			return null;
		}

		if (StringUtil.isEmpty(extraType)) {
			return getAllByPeriodId(periodId);
		}

		try {
			return getResultList(
					Price.GET_PRICES_BY_PERIOD_ID_AND_EXTRA_TYPE,
					Price.class,
					new Param(Price.PARAM_PERIOD_ID, periodId),
					new Param(Price.PARAM_EXTRA_TYPE, extraType)
			);
		} catch (Exception e) {
			getLogger().log(Level.WARNING, "Error getting prices by period id: " + periodId + " and extra type: " + extraType, e);
		}
		return null;
	}

	@Override
	public List<Price> getAllByPeriodIdAndDefault(Integer periodId, Boolean isDefault) {
		if (periodId == null || isDefault == null) {
			return null;
		}

		try {
			if (isDefault == Boolean.TRUE) {
				return getResultList(
						Price.GET_PRICES_BY_PERIOD_ID_AND_IS_DEFAULT,
						Price.class,
						new Param(Price.PARAM_PERIOD_ID, periodId)
				);
			} else {
				return getResultList(
						Price.GET_PRICES_BY_PERIOD_ID_AND_IS_OPTIONAL,
						Price.class,
						new Param(Price.PARAM_PERIOD_ID, periodId)
				);
			}
		} catch (Exception e) {
			getLogger().log(Level.WARNING, "Error getting prices by period id: " + periodId + " and default: " + isDefault, e);
		}
		return null;
	}


}
