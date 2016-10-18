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
	public List<Price> getAllByPeriodId(Long periodId) {
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

}
