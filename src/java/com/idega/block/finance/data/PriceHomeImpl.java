package com.idega.block.finance.data;

public class PriceHomeImpl extends com.idega.data.IDOFactory implements PriceHome
{
	@Override
	protected Class getEntityInterfaceClass(){
	  return Price.class;
	}

	@Override
	public Price create() throws javax.ejb.CreateException{
	  return (Price) super.createIDO();
	}


	@Override
	public Price findByPrimaryKey(Object pk) throws javax.ejb.FinderException{
	  return (Price) super.findByPrimaryKeyIDO(pk);
    }

	@Override
	public Price findByPeriodAndAge(Integer periodId, Integer age) throws javax.ejb.FinderException{
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		Object id = ((PriceBMPBean)entity).ejbFindByPeriodAndAge(periodId, age);
		this.idoCheckInPooledEntity(entity);
		return this.findByPrimaryKey(id);
	}

	@Override
	public java.util.Collection findByPeriod(java.lang.Integer periodId) throws javax.ejb.FinderException{
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		java.util.Collection ids = ((PriceBMPBean)entity).ejbFindByPeriod(periodId);
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}



}