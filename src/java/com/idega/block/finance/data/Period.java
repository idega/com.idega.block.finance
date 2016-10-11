package com.idega.block.finance.data;


public interface Period extends com.idega.data.IDOEntity
{
	 public int getGroupId();
	 public java.lang.String getName();
	 public java.sql.Timestamp getFromDate();
	 public java.sql.Timestamp getToDate();
	 public void setGroupId(java.lang.Integer groupId);
	 public void setName(java.lang.String name);
	 public void setFromDate(java.sql.Timestamp fromDate);
	 public void setToDate(java.sql.Timestamp toDate);

}
