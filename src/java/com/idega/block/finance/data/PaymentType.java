package com.idega.block.finance.data;

import javax.ejb.*;

public interface PaymentType extends com.idega.data.IDOLegacyEntity
{
 public int getAmountCost();
 public int getCategoryId();
 public java.lang.String getInfo();
 public java.lang.String getName();
 public int getPayments();
 public int getPercentCost();
 public int getTariffKeyId();
 public void setAmountCost(float p0);
 public void setCategoryId(int p0);
 public void setInfo(java.lang.String p0);
 public void setName(java.lang.String p0);
 public void setPayments(int p0);
 public void setPercentCost(float p0);
 public void setTariffKeyId(int p0);
}
