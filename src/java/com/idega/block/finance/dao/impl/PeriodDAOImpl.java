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
import com.idega.util.CoreConstants;
import com.idega.util.ListUtil;

@Repository(PeriodDAO.BEAN_NAME)
@Scope(BeanDefinition.SCOPE_SINGLETON)
@Transactional(readOnly = true)
public class PeriodDAOImpl extends GenericDaoImpl implements PeriodDAO {

	@Override
	public Period getById(Integer periodId) {
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

	@Override
	public Period getCurrentPeriodForClub(Integer clubId) {
		if (clubId == null) {
			getLogger().warning("Club ID is not provided");
			return null;
		}

		try {
			List<Period> seasons = getResultList(
					Period.QUERY_FIND_VALID_FOR_CLUB,
					Period.class,
					new Param("club", clubId),
					new Param("controlsMembership", CoreConstants.Y)
			);
			if (ListUtil.isEmpty(seasons)) {
				getLogger().log(Level.WARNING, "Club (" + clubId + ") has no financial period that controls membership for current date");
				return null;
			}

			return seasons.iterator().next();
		} catch (Exception e) {
			getLogger().log(Level.WARNING, "Error getting current fiscal season for club " + clubId, e);
		}

		return null;
	}

	@Override
	public List<Period> getAllInfinitePeriods() {
		try {
			return getResultList(Period.GET_ALL_INFINITE, Period.class);
		} catch (Exception e) {
			getLogger().log(Level.WARNING, "Error getting all infinite periods: ", e);
		}
		return null;
	}


	@Override
	@Transactional(readOnly = false)
	public Period createUpdatePeriod(Period period) {
		if (period == null) {
			getLogger().warning("Period is not provided");
			return period;
		}

		try {
			if (period.getId() == null) {
				persist(period);
			} else {
				merge(period);
			}
		} catch (Exception e) {
			getLogger().log(Level.WARNING, "Could not create/update the period with ID " + period.getId() + ", name: " + period.getName() + ". Error message was: " + e.getLocalizedMessage(), e);
		}
		return period;

	}


}