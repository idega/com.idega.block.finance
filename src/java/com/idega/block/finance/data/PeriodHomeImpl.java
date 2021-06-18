package com.idega.block.finance.data;

import java.sql.Timestamp;

public class PeriodHomeImpl extends com.idega.data.IDOFactory implements PeriodHome
{
	@Override
	protected Class getEntityInterfaceClass(){
	  return Period.class;
	}

	@Override
	public Period create() throws javax.ejb.CreateException{
	  return (Period) super.createIDO();
	}


	@Override
	public Period findByPrimaryKey(Object pk) throws javax.ejb.FinderException{
	  return (Period) super.findByPrimaryKeyIDO(pk);
    }

	@Override
	public Period findByGroupAndDate(Integer groupId, Timestamp timestamp, Boolean controlsMembership) throws javax.ejb.FinderException{
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		Object id = ((PeriodBMPBean)entity).ejbFindByGroupAndDate(groupId, timestamp, controlsMembership);
		this.idoCheckInPooledEntity(entity);
		return this.findByPrimaryKey(id);
	}

	@Override
	public java.util.Collection findByGroup(java.lang.Integer groupId) throws javax.ejb.FinderException{
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		java.util.Collection ids = ((PeriodBMPBean)entity).ejbFindByGroup(groupId);
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}

	@Override
	public java.util.Collection findAllByGroupAndDate(java.lang.Integer groupId, Timestamp timestamp) throws javax.ejb.FinderException{
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		java.util.Collection ids = ((PeriodBMPBean)entity).ejbFindAllByGroupAndDate(groupId, timestamp);
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}


	@Override
	public java.util.Collection findAllByGroupAndControlsMembership(java.lang.Integer groupId, Boolean controlsMembership) throws javax.ejb.FinderException {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		java.util.Collection ids = ((PeriodBMPBean)entity).ejbFindAllByGroupAndControlsMembership(groupId, controlsMembership);
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}

	@Override
	public java.util.Collection findAllByGroupAndDatesAndControlsMembershipAndOlderThanGivenPeriodId(java.lang.Integer groupId, Timestamp timestampFrom, Timestamp timestampTo, Boolean controlsMembership, Integer periodId) throws javax.ejb.FinderException {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		java.util.Collection ids = ((PeriodBMPBean)entity).ejbFindAllByGroupAndDatesAndControlsMembershipAndOlderThanGivenPeriodId(groupId, timestampFrom, timestampTo, controlsMembership, periodId);
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}



}