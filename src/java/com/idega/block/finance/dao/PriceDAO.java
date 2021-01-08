package com.idega.block.finance.dao;

import java.util.List;

import com.idega.block.finance.hibernate.data.Price;
import com.idega.business.SpringBeanName;
import com.idega.core.persistence.GenericDao;

@SpringBeanName(PriceDAO.BEAN_NAME)
public interface PriceDAO extends GenericDao {

	public static final String BEAN_NAME = "priceDao";

	public Price getById(Long priceId);
	public List<Price> getAllByPeriodId(Integer periodId);
	public List<Price> getAllByIds(List<Long> priceIds);

	public List<Price> getAllByPeriodIdAndExtraType(Integer periodId, String extraType);
}