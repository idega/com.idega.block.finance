package com.idega.block.finance.data;

import com.idega.user.data.Group;

public interface Period extends com.idega.data.IDOEntity
{
	 public int getGroupId();
	 public int getDivisionId();
	 public Group getDivision();
	 public int getClubId();
	 public Group getClub();
	 public java.lang.String getName();
	 public java.lang.String getVirtualGroup();
	 public java.sql.Timestamp getFromDate();
	 public java.sql.Timestamp getToDate();
	 public void setGroupId(java.lang.Integer groupId);
	 public void setDivisionId(Integer divisionId);
	 public void setDivision(Group group);
     public void setClubId(Integer clubId);
     public void setClub(Group group);
	 public void setName(java.lang.String name);
	 public void setVirtualGroup(java.lang.String virtualGroup);
	 public void setFromDate(java.sql.Timestamp fromDate);
	 public void setToDate(java.sql.Timestamp toDate);

}
