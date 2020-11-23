package com.idega.block.finance.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.idega.block.finance.dao.PeriodDAO;
import com.idega.block.finance.hibernate.data.Period;
import com.idega.core.persistence.Param;
import com.idega.core.persistence.impl.GenericDaoImpl;

@Repository(PeriodDAO.BEAN_NAME)
@Scope(BeanDefinition.SCOPE_SINGLETON)
@Transactional(readOnly = true)
public class PeriodDAOImpl extends GenericDaoImpl implements PeriodDAO {

	@Override
	public Period getById(Long periodId) {
		try {
			return find(Period.class, periodId);
		} catch (Exception e) {
			getLogger().log(Level.WARNING, "Error getting period by ID: " + periodId, e);
		}
		return null;
	}

	@Override
	public List<Period> getAllPeriods() {
		try {
			return getResultList(Period.GET_ALL, Period.class);
		} catch (Exception e) {
			getLogger().log(Level.WARNING, "Error getting all periods: ", e);
		}
		return null;
	}

	@Override
	public List<Period> getPeriodsByConfirmationDate(Date confirmationDate) {
		if (confirmationDate == null) {
			getLogger().warning("Confirmation date is not provided");
			return null;
		}

		try {
			return getResultList(
					Period.GET_BY_CONFIRMATION_DATE,
					Period.class, new Param(Period.PARAM_CONFIRMATION_DATE,
					confirmationDate));
		} catch (Exception e) {
			getLogger().log(Level.WARNING, "Error getting periods by confirmation date: " + confirmationDate, e);
		}
		return null;
	}

}