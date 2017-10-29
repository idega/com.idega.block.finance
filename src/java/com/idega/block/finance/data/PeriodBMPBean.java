package com.idega.block.finance.data;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;

import javax.ejb.FinderException;

import com.idega.data.IDOQuery;
import com.idega.user.data.Group;
import com.idega.util.CoreConstants;

public class PeriodBMPBean extends com.idega.data.GenericEntity implements com.idega.block.finance.data.Period {
	private static final long serialVersionUID = 9081605647262701182L;


	public PeriodBMPBean() {
	}

	public PeriodBMPBean(int id) throws SQLException {
		super(id);
	}

	@Override
	public void initializeAttributes() {
	    addAttribute(getIDColumnName());
	    addAttribute(getColumnGroup(),"Group",true,true,Integer.class,"many-to-one",Group.class);
	    addAttribute(getColumnDivision(),"Division",true,true,Integer.class,"many-to-one",Group.class);
	    addAttribute(getColumnClub(),"Club",true,true,Integer.class,"many-to-one",Group.class);
	    addAttribute(getColumnName(),"Name",true,true,java.lang.String.class);
	    addAttribute(getColumnVirtualGroup(),"Virtual group",true,true,java.lang.String.class);
	    addAttribute(getColumnFromDate(),"From date",true,true,java.sql.Timestamp.class);
	    addAttribute(getColumnToDate(),"To date",true,true,java.sql.Timestamp.class);
    }

	public static String getPeriodEntityName() {return "FIN_PERIOD";}
	public static String getColumnGroup() {return "GROUP_ID";}
	public static String getColumnDivision() {return "DIVISION_ID";}
	public static String getColumnClub() {return "CLUB_ID";}
	public static String getColumnName(){return "NAME";}
	public static String getColumnVirtualGroup(){return "VIRTUAL_GROUP";}
    public static String getColumnFromDate(){return "FROM_DATE";}
    public static String getColumnToDate(){return "TO_DATE";}

  @Override
  public String getEntityName() {
    return getPeriodEntityName();
  }

  @Override
  public String getName(){
    return getStringColumnValue(getColumnName());
  }

  @Override
  public void setName(String name){
    setColumn(getColumnName(), name);
  }


  @Override
  public String getVirtualGroup(){
    return getStringColumnValue(getColumnVirtualGroup());
  }

  @Override
  public void setVirtualGroup(String virtualGroup){
    setColumn(getColumnVirtualGroup(), virtualGroup);
  }

  @Override
  public int getGroupId(){
    return getIntColumnValue(getColumnGroup());
  }

  @Override
  public void setGroupId(Integer groupId){
    setColumn(getColumnGroup(), groupId);
  }

  @Override
  public int getDivisionId(){
    return getIntColumnValue(getColumnDivision());
  }

  @Override
  public Group getDivision() {
	return (Group) getColumnValue(getColumnDivision());
  }

  @Override
  public void setDivisionId(Integer divisionId){
    setColumn(getColumnDivision(), divisionId);
  }

  @Override
  public void setDivision(Group group) {
	setColumn(getColumnDivision(), group);
  }

  @Override
  public int getClubId(){
    return getIntColumnValue(getColumnClub());
  }

  @Override
  public Group getClub() {
	return (Group) getColumnValue(getColumnClub());
  }

  @Override
  public void setClubId(Integer clubId){
    setColumn(getColumnClub(), clubId);
  }

  @Override
  public void setClub(Group group) {
	setColumn(getColumnClub(), group);
  }

  @Override
  public Timestamp getFromDate(){
    return (Timestamp) getColumnValue(getColumnFromDate());
  }

  @Override
  public void setFromDate(Timestamp fromDate){
    setColumn(getColumnFromDate(),fromDate);
  }

   @Override
   public Timestamp getToDate(){
    return (Timestamp) getColumnValue(getColumnToDate());
  }

  @Override
  public void setToDate(Timestamp toDate){
    setColumn(getColumnToDate(),toDate);
  }

  public Object ejbFindByGroupAndDate(Integer groupId, Timestamp timestamp) throws javax.ejb.FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this);

		sql.appendWhere();
		sql.append(1);
		sql.appendEqualSign();
		sql.append(1);

		sql.appendAndIsNotNull(getColumnGroup());
		sql.appendAndEquals(getColumnGroup(), groupId);

		sql.appendAnd();
		sql.append(CoreConstants.SPACE);
		sql.append(timestamp);
		sql.appendGreaterThanOrEqualsSign();
		sql.append(getColumnFromDate());
		sql.appendAnd();
		sql.append(CoreConstants.SPACE);
		sql.append(timestamp);
		sql.appendLessThanOrEqualsSign();
		sql.append(getColumnToDate());

	  return idoFindOnePKByQuery(sql);
  }


  public Collection ejbFindByGroup(Integer groupId)throws FinderException{
  	return super.idoFindPKsByQuery(super.idoQueryGetSelect().appendWhereEquals(getColumnGroup(), groupId).appendOrderBy(getColumnName()));
  }

}
