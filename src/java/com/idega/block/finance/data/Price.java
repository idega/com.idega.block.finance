package com.idega.block.finance.data;


public interface Price extends com.idega.data.IDOEntity
{
	 public int getPeriodId();
	 public String getName();
	 public Integer getAgeFrom();
	 public Integer getAgeTo();
	 public Float getPrice();
	 public void setPeriodId(Integer periodId);
	 public void setName(String name);
	 public void setAgeFrom(Integer ageFrom);
	 public void setAgeTo(Integer ageTo);
	 public void setPrice(Float price);
	 public String getType();
	 public void setType(String type);
	 public String getExtraType();
	 public void setExtraType(String extraType);

}
