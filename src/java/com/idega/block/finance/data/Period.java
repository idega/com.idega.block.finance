package com.idega.block.finance.data;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Collection;

import com.idega.data.IDOAddRelationshipException;
import com.idega.data.IDORemoveRelationshipException;
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
	 public Timestamp getConfirmationDate();
	 public void setConfirmationDate(Timestamp confirmationDate);
	 public boolean getControlsMembership();
	 public void setControlsMembership(boolean p0);
	 public Timestamp getGeneratedPaymentsDate();
 	 public void setGeneratedPaymentsDate(Timestamp generatedPaymentsDate);
 	 public String getMemberEmailContent();
 	 public void setMemberEmailContent(InputStream memberEmailContent);
 	 public String getCertificateAdditionalText();
 	 public void setCertificateAdditionalText(String certificateAdditionalText);
 	 public String getCertificateColor();
 	 public void setCertificateColor(String certificateColor);


	 public void addGroupToExclude(Group group) throws IDOAddRelationshipException;
	 public void removeExcludedGroup(Group group) throws IDORemoveRelationshipException;
	 public void removeAllExcludedGroups() throws IDORemoveRelationshipException;
	 public Collection<Group> getAllExcludedGroups();

}
