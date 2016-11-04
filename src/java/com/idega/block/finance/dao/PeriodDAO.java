package com.idega.block.finance.dao;

import java.util.List;

import com.idega.block.finance.hibernate.data.Period;
import com.idega.business.SpringBeanName;
import com.idega.core.persistence.GenericDao;

@SpringBeanName(PeriodDAO.BEAN_NAME)
public interface PeriodDAO extends GenericDao {

	public static final String BEAN_NAME = "periodDao";

	public Period getById(Long periodId);

	public List<Period> getAllPeriods();
}