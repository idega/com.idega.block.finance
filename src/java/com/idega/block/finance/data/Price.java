package com.idega.block.finance.data;


public interface Price extends com.idega.data.IDOEntity
{
	 public int getPeriodId();
	 public String getName();
	 public Integer getAgeFrom();
	 public Integer getAgeTo();
	 public Float getPrice();
	 public Integer getLinkedPeriodId();
	 public void setPeriodId(Integer periodId);
	 public void setName(String name);
	 public void setAgeFrom(Integer ageFrom);
	 public void setAgeTo(Integer ageTo);
	 public void setPrice(Float price);
	 public void setLinkedPeriodId(Integer periodId);
	 public String getType();
	 public void setType(String type);
	 public String getExtraType();
	 public void setExtraType(String extraType);
	 public void setIsDefault(boolean isDefault);
	 public boolean getIsDefault();
 	 public String getCertificateAdditionalText();
 	 public void setCertificateAdditionalText(String certificateAdditionalText);

	 public Integer getDateOfMonthlyPayments();
	 public void setDateOfMonthlyPayments(Integer dateOfMonthlyPayments);
}
