package com.idega.block.finance.data;

import java.sql.Timestamp;

public interface PeriodHome extends com.idega.data.IDOHome {

	public Period create() throws javax.ejb.CreateException;
 	public Period findByPrimaryKey(Object pk) throws javax.ejb.FinderException;

 	public Period findByGroupAndDate(Integer groupId, Timestamp timestamp) throws javax.ejb.FinderException;
 	public java.util.Collection findByGroup(Integer groupId) throws javax.ejb.FinderException;
 	public java.util.Collection findAllByGroupAndDate(java.lang.Integer groupId, Timestamp timestamp) throws javax.ejb.FinderException;
 	public java.util.Collection findAllByGroupAndControlsMembership(java.lang.Integer groupId, Boolean controlsMembership) throws javax.ejb.FinderException;
}