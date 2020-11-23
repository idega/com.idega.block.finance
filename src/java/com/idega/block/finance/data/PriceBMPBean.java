package com.idega.block.finance.data;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.ejb.FinderException;

import com.idega.data.IDOQuery;
import com.idega.util.CoreConstants;

public class PriceBMPBean extends com.idega.data.GenericEntity implements com.idega.block.finance.data.Price {
	private static final long serialVersionUID = -8068967341991727717L;


	public PriceBMPBean() {
	}

	public PriceBMPBean(int id) throws SQLException {
		super(id);
	}

	@Override
	public void initializeAttributes() {
	    addAttribute(getIDColumnName());
	    addAttribute(getColumnPeriod(),"Period",true,true,Integer.class,"many-to-one",Period.class);
	    addAttribute(getColumnName(),"Name",true,true,java.lang.String.class);
	    addAttribute(getColumnAgeFrom(),"Age from",true,true,Integer.class);
	    addAttribute(getColumnAgeTo(),"Age to",true,true,Integer.class);
	    addAttribute(getColumnPrice(),"Price",true,true,Float.class);
	    addAttribute(getColumnType(),"Type",true,true,java.lang.String.class);
    }

	public static String getPriceEntityName() {return "FIN_PRICE";}
	public static String getColumnPeriod() {return "PERIOD_ID";}
	public static String getColumnName(){return "NAME";}
    public static String getColumnAgeFrom() {return "AGE_FROM";}
    public static String getColumnAgeTo() {return "AGE_TO";}
	public static String getColumnPrice() {return "PRICE";}
	public static String getColumnType() {return "TYPE";}

  @Override
  public String getEntityName() {
    return getPriceEntityName();
  }

  @Override
  public int getPeriodId() {
    return getIntColumnValue(getColumnPeriod());
  }

  @Override
  public void setPeriodId(Integer periodId) {
    setColumn(getColumnPeriod(), periodId);
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
  public Integer getAgeFrom() {
    return (Integer) getColumnValue(getColumnAgeFrom());
  }

  @Override
  public void setAgeFrom(Integer ageFrom) {
    setColumn(getColumnAgeFrom(), ageFrom);
  }

   @Override
   public Integer getAgeTo() {
    return (Integer) getColumnValue(getColumnAgeTo());
  }

  @Override
  public void setAgeTo(Integer ageTo) {
    setColumn(getColumnAgeTo(), ageTo);
  }

  @Override
  public Float getPrice() {
    return getFloatColumnValue(getColumnPrice(), -1);
  }

  @Override
  public void setPrice(Float price){
    setColumn(getColumnPrice(), price);
  }

  @Override
  public String getType(){
    return getStringColumnValue(getColumnType());
  }

  @Override
  public void setType(String type){
    setColumn(getColumnType(), type);
  }

  public Object ejbFindByPeriodAndAge(Integer periodId, Integer age) throws javax.ejb.FinderException {
		IDOQuery sql = idoQuery();
		sql.appendSelectAllFrom(this);

		sql.appendWhere();
		sql.append(1);
		sql.appendEqualSign();
		sql.append(1);

		sql.appendAndIsNotNull(getColumnPeriod());
		sql.appendAndEquals(getColumnPeriod(), periodId);

		if (age != null && age >= 0) {
			sql.appendAnd();
			sql.append(CoreConstants.SPACE);
			sql.append(age);
			sql.appendGreaterThanOrEqualsSign();
			sql.append(getColumnAgeFrom());
			sql.appendAnd();
			sql.append(CoreConstants.SPACE);
			sql.append(age);
			sql.appendLessThanOrEqualsSign();
			sql.append(getColumnAgeTo());
		}

		return idoFindOnePKByQuery(sql);
  }


  public Collection ejbFindByPeriod(Integer periodId)throws FinderException{
	List<String> ordering = Arrays.asList(getColumnName(), getColumnAgeFrom());
  	return super.idoFindPKsByQuery(super.idoQueryGetSelect().appendWhereEquals(getColumnPeriod(), periodId).appendOrderBy(ordering.toArray(new String[ordering.size()])));
  }

}
