/*
 * Created on Mar 9, 2004
 *
 */
package com.idega.block.finance.business;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;

import com.idega.block.finance.dao.PeriodDAO;
import com.idega.block.finance.data.Account;
import com.idega.block.finance.data.AccountBMPBean;
import com.idega.block.finance.data.AccountEntry;
import com.idega.block.finance.data.AccountEntryHome;
import com.idega.block.finance.data.AccountHome;
import com.idega.block.finance.data.AccountInfo;
import com.idega.block.finance.data.AccountInfoHome;
import com.idega.block.finance.data.AccountKey;
import com.idega.block.finance.data.AccountKeyHome;
import com.idega.block.finance.data.AccountPhoneEntry;
import com.idega.block.finance.data.AccountPhoneEntryHome;
import com.idega.block.finance.data.AccountType;
import com.idega.block.finance.data.AccountTypeHome;
import com.idega.block.finance.data.AccountUser;
import com.idega.block.finance.data.AccountUserHome;
import com.idega.block.finance.data.AssessmentRound;
import com.idega.block.finance.data.AssessmentRoundHome;
import com.idega.block.finance.data.AssessmentStatus;
import com.idega.block.finance.data.EntryGroup;
import com.idega.block.finance.data.EntryGroupHome;
import com.idega.block.finance.data.FinanceHandlerInfo;
import com.idega.block.finance.data.FinanceHandlerInfoHome;
import com.idega.block.finance.data.PaymentType;
import com.idega.block.finance.data.PaymentTypeHome;
import com.idega.block.finance.data.Period;
import com.idega.block.finance.data.PeriodHome;
import com.idega.block.finance.data.Price;
import com.idega.block.finance.data.PriceHome;
import com.idega.block.finance.data.RoundInfo;
import com.idega.block.finance.data.RoundInfoHome;
import com.idega.block.finance.data.Tariff;
import com.idega.block.finance.data.TariffGroup;
import com.idega.block.finance.data.TariffGroupHome;
import com.idega.block.finance.data.TariffHome;
import com.idega.block.finance.data.TariffIndex;
import com.idega.block.finance.data.TariffIndexHome;
import com.idega.block.finance.data.TariffKey;
import com.idega.block.finance.data.TariffKeyHome;
import com.idega.business.IBOServiceBean;
import com.idega.data.IDOException;
import com.idega.util.IWTimestamp;
import com.idega.util.ListUtil;
import com.idega.util.expression.ELUtil;
/**
 * FinanceServiceBean
 *
 * @author aron
 * @version 1.0
 */
public class FinanceServiceBean extends IBOServiceBean implements FinanceService {
	@Override
	public AccountHome getAccountHome() throws RemoteException {
		return (AccountHome) getIDOHome(Account.class);
	}
	@Override
	public AccountEntryHome getAccountEntryHome() throws RemoteException {
		return (AccountEntryHome) getIDOHome(AccountEntry.class);
	}
	@Override
	public AccountKeyHome getAccountKeyHome() throws RemoteException {
		return (AccountKeyHome) getIDOHome(AccountKey.class);
	}
	@Override
	public AccountInfoHome getAccountInfoHome() throws RemoteException {
		return (AccountInfoHome) getIDOHome(AccountInfo.class);
	}
	@Override
	public AccountPhoneEntryHome getAccountPhoneEntryHome() throws RemoteException {
		return (AccountPhoneEntryHome) getIDOHome(AccountPhoneEntry.class);
	}
	@Override
	public AccountTypeHome getAccountTypeHome() throws RemoteException {
		return (AccountTypeHome) getIDOHome(AccountType.class);
	}
	@Override
	public AssessmentRoundHome getAssessmentRoundHome() throws RemoteException {
		return (AssessmentRoundHome) getIDOHome(AssessmentRound.class);
	}
	@Override
	public RoundInfoHome getRoundInfoHome() throws RemoteException {
		return (RoundInfoHome) getIDOHome(RoundInfo.class);
	}
	@Override
	public TariffHome getTariffHome() throws RemoteException {
		return (TariffHome) getIDOHome(Tariff.class);
	}
	@Override
	public TariffKeyHome getTariffKeyHome() throws RemoteException {
		return (TariffKeyHome) getIDOHome(TariffKey.class);
	}
	@Override
	public TariffGroupHome getTariffGroupHome() throws RemoteException {
		return (TariffGroupHome) getIDOHome(TariffGroup.class);
	}
	@Override
	public EntryGroupHome getEntryGroupHome() throws RemoteException {
		return (EntryGroupHome) getIDOHome(EntryGroup.class);
	}
	@Override
	public AccountUserHome getAccountUserHome() throws RemoteException {
		return (AccountUserHome) getIDOHome(AccountUser.class);
	}
	@Override
	public TariffIndexHome getTariffIndexHome() throws RemoteException {
		return (TariffIndexHome) getIDOHome(TariffIndex.class);
	}
	@Override
	public PaymentTypeHome getPaymentTypeHome() throws RemoteException {
		return (PaymentTypeHome) getIDOHome(PaymentType.class);
	}
	@Override
	public FinanceHandlerInfoHome getFinanceHandlerInfoHome() throws RemoteException {
		return (FinanceHandlerInfoHome) getIDOHome(FinanceHandlerInfo.class);
	}
	@Override
	public PeriodHome getPeriodHome() throws RemoteException {
		return (PeriodHome) getIDOHome(Period.class);
	}
	@Override
	public PriceHome getPriceHome() throws RemoteException {
		return (PriceHome) getIDOHome(Price.class);
	}

	@Override
	public FinanceHandler getFinanceHandler(Integer handlerInfoID) {
		try {
			FinanceHandlerInfo handlerInfo = getFinanceHandlerInfoHome().findByPrimaryKey(handlerInfoID);
			if (handlerInfo.getClassName() != null) {
				try {
					return (FinanceHandler) Class.forName(handlerInfo.getClassName()).newInstance();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	@Override
	public AssessmentBusiness getFinanceBusiness() throws RemoteException {
		return getServiceInstance(AssessmentBusiness.class);
	}
	@Override
	public AccountBusiness getAccountBusiness() throws RemoteException {
		return getServiceInstance(AccountBusiness.class);
	}
	@Override
	public void removeAccountKey(Integer keyID) throws FinderException, RemoteException, RemoveException {
		getAccountKeyHome().findByPrimaryKey(keyID).remove();
	}
	@Override
	public AccountKey createOrUpdateAccountKey(Integer ID, String name, String info, Integer tariffKeyID,Integer ordinal,
			Integer categoryID) throws CreateException, RemoteException, FinderException {
		AccountKey key = getAccountKeyHome().create();
		if (ID != null && ID.intValue() > 0) {
			key = getAccountKeyHome().findByPrimaryKey(ID);
		}
		key.setName(name);
		key.setInfo(info);
		key.setTariffKeyId(tariffKeyID.intValue());
		key.setCategoryId(categoryID.intValue());
		if(ordinal!=null) {
			key.setOrdinal(ordinal);
		}
		key.store();
		return key;
	}
	@Override
	public TariffKey createOrUpdateTariffKey(Integer ID, String name, String info, Integer categoryID)
			throws FinderException, RemoteException, CreateException {
		TariffKey key = getTariffKeyHome().create();
		if (ID != null && ID.intValue() > 0) {
			key = getTariffKeyHome().findByPrimaryKey(ID);
		}
		key.setName(name);
		key.setInfo(info);
		key.setCategoryId(categoryID.intValue());
		key.store();
		return key;
	}
	@Override
	public Tariff createOrUpdateTariff(Integer ID, String name, String info, String attribute, String index,
			boolean useIndex, Timestamp indexStamp, float Price, Integer accountKeyID, Integer tariffGroupID)
			throws FinderException, RemoteException, CreateException {
		Tariff tariff = getTariffHome().create();
		if (ID != null && ID.intValue() > 0) {
			tariff = getTariffHome().findByPrimaryKey(ID);
		}
		tariff.setName(name);
		tariff.setInfo(info);
		tariff.setTariffAttribute(attribute);
		tariff.setAccountKeyId(accountKeyID);
		tariff.setTariffGroupId(tariffGroupID);
		tariff.setPrice(Price);
		tariff.setUseFromDate(IWTimestamp.getTimestampRightNow());
		tariff.setUseToDate(IWTimestamp.getTimestampRightNow());
		tariff.setIndexType(index);
		tariff.setUseIndex(useIndex);
		if (indexStamp != null) {
			tariff.setIndexUpdated(indexStamp);
		}
		tariff.store();
		return tariff;
	}
	@Override
	public Tariff updateTariffPrice(Integer ID, Float Price, Timestamp indexStamp) throws RemoteException,
			FinderException {
		Tariff tariff = getTariffHome().findByPrimaryKey(ID);
		tariff.setPrice(Price.floatValue());
		if (indexStamp != null) {
			tariff.setIndexUpdated(indexStamp);
		}
		tariff.store();
		return tariff;
	}
	@Override
	public void removeTariff(Integer ID) throws FinderException, RemoteException, RemoveException {
		getTariffHome().findByPrimaryKey(ID).remove();
	}
	@Override
	public void removeTariffKey(Integer ID) throws FinderException, RemoteException, RemoveException {
		getTariffKeyHome().findByPrimaryKey(ID).remove();
	}
	@Override
	public void removeTariffIndex(Integer ID) throws FinderException, RemoteException, RemoveException {
		getTariffIndexHome().findByPrimaryKey(ID).remove();
	}
	@Override
	public Map mapOfTariffIndicesByTypes() throws RemoteException, FinderException {
		Collection coll = getTariffIndexHome().findLastTypeGrouped();
		if (coll != null) {
			Hashtable T = new Hashtable(coll.size());
			TariffIndex ti;
			for (Iterator iter = coll.iterator(); iter.hasNext();) {
				ti = (TariffIndex) iter.next();
				T.put(ti.getType(), ti);
			}
			return T;
		}
		else {
			return null;
		}
	}
	@Override
	public TariffGroup createOrUpdateTariffGroup(Integer ID, String name, String info, Integer handlerId,
			boolean useIndex, Integer categoryId) throws CreateException, FinderException, RemoteException {
		TariffGroup tariff = getTariffGroupHome().create();
		if (ID != null && ID.intValue() > 0) {
			tariff = getTariffGroupHome().findByPrimaryKey(ID);
		}
		tariff.setName(name);
		tariff.setInfo(info);
		tariff.setCategoryId(categoryId.intValue());
		tariff.setUseIndex(useIndex);
		if (handlerId != null && handlerId.intValue() > 0) {
			tariff.setHandlerId(handlerId.intValue());
		}
		tariff.store();
		return tariff;
	}
	@Override
	public TariffIndex createOrUpdateTariffIndex(Integer ID, String name, String info, String type, double newvalue,
			double oldvalue, Timestamp stamp, Integer categoryId) throws RemoteException, CreateException {
		TariffIndex ti = getTariffIndexHome().create();
		if (categoryId.intValue() > 0) {
			/*
			 * if(ID!=null && ID.intValue() > 0){ ti =
			 * getTariffIndexHome().findByPrimaryKey(ID); }
			 */
			ti.setName(name);
			ti.setInfo(info);
			ti.setOldValue(oldvalue);
			ti.setIndex(newvalue);
			ti.setDate(stamp);
			ti.setType(type);
			ti.setCategoryId(categoryId.intValue());
			ti.setNewValue(newvalue);
			ti.store();
			return ti;
		}
		throw new CreateException("Category missing");
	}

	@Override
	public String getAccountTypeFinance(){
		return AccountBMPBean.typeFinancial;
	}
	@Override
	public String getAccountTypePhone(){
		return AccountBMPBean.typePhone;
	}

	  @Override
	public  Collection getKeySortedTariffsByAttribute(String attribute) throws FinderException,RemoteException{
	    Hashtable tar = null;
	    Map AccKeyMap = mapOfAccountKeys();
	    Map TarKeyMap = mapOfTariffKeys();
	    Collection tariffs = getTariffHome().findByAttribute(attribute);
	    if(tariffs != null ){
	      tar = new Hashtable();
	      java.util.Iterator iter = tariffs.iterator();
	      Tariff t;
	      Integer acckey;
	      Integer tarkey;
	      while(iter.hasNext()){
	        t = (Tariff) iter.next();
	        try{
	        acckey = new Integer(t.getAccountKeyId());
	        if(AccKeyMap.containsKey(acckey)){
	          AccountKey AK = (AccountKey) AccKeyMap.get(acckey);
	          tarkey = new Integer(AK.getTariffKeyId());
	          if(TarKeyMap.containsKey(tarkey)){
	            TariffKey TK = (TariffKey) TarKeyMap.get(tarkey);
	            if(tar.containsKey(tarkey)){
	              Tariff a = (Tariff)tar.get(tarkey);
	              a.setPrice(a.getPrice()+t.getPrice());
	            }
	            else{
	              t.setName(TK.getName());
	              t.setInfo(TK.getInfo());
	              tar.put(tarkey,t)  ;
	            }
	          }
	        }
	        }
	        catch(Exception ex)
	        {}
	      }
	      return tar.values();
	    }
	    return null;
	  }

	  @Override
	public Map mapOfAccountKeys() throws RemoteException, FinderException {
		Collection coll = getAccountKeyHome().findAll();
		if (coll != null) {
			Hashtable T = new Hashtable(coll.size());
			AccountKey ti;
			for (Iterator iter = coll.iterator(); iter.hasNext();) {
				ti = (AccountKey) iter.next();
				T.put(ti.getPrimaryKey(), ti);
			}
			return T;
		}
		else {
			return null;
		}
	}

	  @Override
	public Map mapOfTariffKeys() throws RemoteException, FinderException {
		Collection coll = getTariffKeyHome().findAll();
		if (coll != null) {
			Hashtable T = new Hashtable(coll.size());
			TariffKey ti;
			for (Iterator iter = coll.iterator(); iter.hasNext();) {
				ti = (TariffKey) iter.next();
				T.put(ti.getPrimaryKey(), ti);
			}
			return T;
		}
		else {
			return null;
		}
	}

	/**
	 * Returns calculated account balance from account entries in published assessments
	 */
	@Override
	public double getAccountBalancePublished(Integer accountID){
		return getAccountBalance(accountID,AssessmentStatus.PUBLISHED);
	}

	/**
	 * Returns calculated account balance from account entries
	 */
	@Override
	public double getAccountBalance(Integer accountID){
		return getAccountBalance(accountID,null);
	}
	/**
	 * Returns calculated account balance from account entries with given assessment status flag
	 * See AssessmentStatus for available flags
	 */
	@Override
	public double getAccountBalance(Integer accountID,String roundStatus){
		try {
			return getAccountEntryHome().getTotalSumByAccount(accountID,roundStatus);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	/* (non-Javadoc)
	 * @see com.idega.block.finance.business.FinanceService#getAccountLastUpdate(java.lang.Integer)
	 */
	@Override
	public Date getAccountLastUpdate(Integer accountID) {
		if(accountID!=null){
			try {
				return getAccountEntryHome().getMaxDateByAccount(accountID);
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (IDOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public void removePeriod(Integer periodId) throws FinderException, RemoteException, RemoveException {
		getPeriodHome().findByPrimaryKey(periodId).remove();
	}

	@Override
	public Period getPeriodById(Integer periodId) {
		try {
			return getPeriodHome().findByPrimaryKey(periodId);
		} catch (Exception e) {
			getLogger().log(Level.WARNING, "Could not get the period by periodId: " + periodId , e);
		}
		return null;
	}

	@Override
	public Period getPeriodByGroupAndDate(Integer groupId, Timestamp timestamp) {
		try {
			return getPeriodHome().findByGroupAndDate(groupId, timestamp);
		} catch (Exception e) {
			getLogger().log(Level.WARNING, "Could not get the period by groupId: " + groupId + " and timestamp: " + timestamp, e);
		}
		return null;
	}

	@Override
	public Collection<Period> getAllPeriodsByGroupId(Integer groupId) {
		try {
			return getPeriodHome().findByGroup(groupId);
		} catch (Exception e) {
			getLogger().log(Level.WARNING, "Could not get the periods by groupId: " + groupId, e);
		}
		return null;
	}

	@Override
	public Collection<Period> getAllPeriodsByGroupAndDate(Integer groupId, Timestamp timestamp) {
		try {
			return getPeriodHome().findAllByGroupAndDate(groupId, timestamp);
		} catch (Exception e) {
			getLogger().log(Level.WARNING, "Could not get the periods by groupId: " + groupId + " and timestamp: " + timestamp, e);
		}
		return null;
	}

	@Override
	public Period getCurrentPeriod(Integer clubId) {
		if (clubId == null) {
			getLogger().warning("Club ID is not provided");
			return null;
		}

		try {
			Collection<Period> periods = getAllPeriodsByGroupAndDate(clubId, new Timestamp(System.currentTimeMillis()));
			if (!ListUtil.isEmpty(periods)) {
				for (Period period: periods) {
					if (period != null && period.getControlsMembership()) {
						return period;
					}
				}
			}

			PeriodDAO periodDAO = ELUtil.getInstance().getBean(PeriodDAO.class);
			com.idega.block.finance.hibernate.data.Period period = periodDAO.getCurrentPeriodForClub(clubId);
			if (period != null) {
				return getPeriodHome().findByPrimaryKey(period.getId().intValue());
			}

			getLogger().warning("Failed to find period that controls membership for club " + clubId + ". Club's periods: " + periods);
		} catch (Exception e) {
			getLogger().log(Level.WARNING, "Error getting current period for club " + clubId, e);
		}

		return null;
	}

	@Override
	public Period updatePeriod(
			Integer periodId,
			Integer groupId,
			Integer divisionId,
			Integer clubId,
			String name,
			Timestamp fromDate,
			Timestamp toDate,
			String virtualGroup,
			Boolean controlsMembership
	) {
		Period period = null;
		try {
			if (periodId != null && periodId > 0) {
				try {
					period = getPeriodHome().findByPrimaryKey(periodId);
				} catch (Exception e) {}
			}
			if (period == null) {
				period = getPeriodHome().create();
			}

			period.setGroupId(groupId);
			period.setDivisionId(divisionId);
			period.setClubId(clubId);
			period.setName(name);
			period.setFromDate(fromDate);
			period.setToDate(toDate);
			period.setVirtualGroup(virtualGroup);
			if (controlsMembership != null) {
				period.setControlsMembership(controlsMembership);
			}

			period.store();

			return period;
		} catch (Exception e) {
			getLogger().log(Level.WARNING, "Could not update/create the period " + period + ", club: " + clubId, e);
		}
		return null;
	}

	@Override
	public void removePrice(Integer priceId) throws FinderException, RemoteException, RemoveException {
		getPriceHome().findByPrimaryKey(priceId).remove();
	}


	@Override
	public Price getPriceById(Integer priceId) {
		try {
			return getPriceHome().findByPrimaryKey(priceId);
		} catch (Exception e) {
			getLogger().log(Level.WARNING, "Could not get the price by priceId: " + priceId , e);
		}
		return null;
	}

	@Override
	public Price getPriceByPeriodAndAge(Integer periodId, Integer age) {
		try {
			return getPriceHome().findByPeriodAndAge(periodId, age);
		} catch (Exception e) {
			getLogger().log(Level.WARNING, "Could not get the price by periodId: " + periodId + " and age: " + age, e);
		}
		return null;
	}

	@Override
	public Collection<Price> getAllPricesByPeriodId(Integer periodId) {
		try {
			return getPriceHome().findByPeriod(periodId);
		} catch (Exception e) {
			getLogger().log(Level.WARNING, "Could not get the prices by periodId: " + periodId, e);
		}
		return null;
	}

	@Override
	public Price updatePrice(Integer priceId, Integer periodId, Integer ageFrom, Integer ageTo, Float price, String name) {
		return updatePrice(priceId, periodId, ageFrom, ageTo, price, name, null);
	}

	@Override
	public Price updatePrice(Integer priceId, Integer periodId, Integer ageFrom, Integer ageTo, Float price, String name, String type) {
		try {
			Price periodPrice = null;
			if (priceId != null && priceId > 0) {
				periodPrice = getPriceHome().findByPrimaryKey(priceId);
			} else {
				periodPrice = getPriceHome().create();
			}

			periodPrice.setPeriodId(periodId);
			periodPrice.setName(name);
			periodPrice.setAgeFrom(ageFrom);
			periodPrice.setAgeTo(ageTo);
			periodPrice.setPrice(price);
			periodPrice.setType(type);

			periodPrice.store();

			return periodPrice;
		} catch (Exception e) {
			getLogger().log(Level.WARNING, "Could not update/create the price: " , e);
		}
		return null;
	}


}