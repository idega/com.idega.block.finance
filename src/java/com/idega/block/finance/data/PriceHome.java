package com.idega.block.finance.data;

public interface PriceHome extends com.idega.data.IDOHome {

	public Price create() throws javax.ejb.CreateException;
 	public Price findByPrimaryKey(Object pk) throws javax.ejb.FinderException;

 	public Price findByPeriodAndAge(Integer periodId, Integer age) throws javax.ejb.FinderException;
 	public java.util.Collection findByPeriod(Integer periodId) throws javax.ejb.FinderException;

}