/*
 * Created on Dec 6, 2004
 * 
 */
package com.idega.block.finance.business;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.FinderException;

import com.idega.block.finance.data.AccountEntry;
import com.idega.block.finance.data.AccountEntryHome;
import com.idega.block.finance.data.BankInfo;
import com.idega.block.finance.data.BankInfoHome;
import com.idega.block.finance.data.Batch;
import com.idega.block.finance.data.BatchHome;
import com.idega.data.IDOLookup;
import com.idega.data.IDOLookupException;
import com.idega.user.data.User;
import com.idega.user.data.UserHome;
import com.idega.util.IWTimestamp;

/**
 * @author birna
 * 
 */
public class BankInvoiceFileManager implements BankFileManager {

	// private int groupID = -1;

	private BankInfo bankInfo = null;

	public BankInvoiceFileManager(int groupID) {
		// this.groupID = groupID;
		this.bankInfo = getBankInfo(groupID);
	}

	public BankInvoiceFileManager(BankInfo info) {
		this.bankInfo = info;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getAccountBook(int)
	 */
	public int getAccountBook() {
		if (this.bankInfo != null) {
			return this.bankInfo.getAccountBook();
		}
		else {
			return 66;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getAmount(int)
	 */
	public String getAmount(int invoiceNumber) {
		AccountEntry ae = getAccountEntry(invoiceNumber);
		if (ae != null) {
			return String.valueOf((int) ae.getTotal());
		}
		else {
			return "";
		}

	}

	public String getAmount100(int invoiceNumber) {
		AccountEntry ae = getAccountEntry(invoiceNumber);
		if (ae != null) {
			return String.valueOf((int) ae.getTotal() * 100);
		}
		else {
			return "";
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getBankId(int)
	 */
	public String getBankBranchNumber() {
		if (this.bankInfo != null) {
			return this.bankInfo.getClaimantsBankBranch().getBankBranchNumber();
		}
		else {
			return "";
		}
	}

	public String getBookkeepingType(int invoiceNumber) {
		// TODO: K er stofnun krafa - ath med ad geyma thetta ser - eda
		// eitthvad!!
		return "K";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getClaimantsAccountId(int)
	 */
	public String getClaimantsAccountId() {
		if (this.bankInfo != null) {
			return this.bankInfo.getAccountId();
		}
		else {
			return "";
		}
	}

	public String getClaimantSSN() {
		if (this.bankInfo != null) {
			return this.bankInfo.getClaimantsSSN();
		}
		else {
			return "";
		}
	}

	public String getClaimantName() {
		if (this.bankInfo != null) {
			return this.bankInfo.getClaimantsName();
		}
		else {
			return "";
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getClaimNumber(int)
	 */
	public String getClaimNumber(int invoiceNumber) {
		return null;
	}

	public Integer[] getInvoiceNumbers(int batchNumber) {
		Collection accountEntries = null;
		AccountEntryHome aeh = null;
		try {
			aeh = (AccountEntryHome) IDOLookup.getHome(AccountEntry.class);
		}
		catch (IDOLookupException e) {
			e.printStackTrace();
		}
		if (aeh != null) {
			try {
				accountEntries = aeh.findByBatchNumber(batchNumber);
			}
			catch (FinderException e1) {
				e1.printStackTrace();
			}
			if (accountEntries != null) {
				Integer[] invoiceNumbers = new Integer[accountEntries.size()];
				Iterator i = accountEntries.iterator();
				int j = 0;
				while (i.hasNext()) {
					AccountEntry accEntry = (AccountEntry) i.next();
					invoiceNumbers[j++] = accEntry.getInvoiceNumber();
				}
				return invoiceNumbers;
			}
		}
		return null;
	}

	public int[] getInvoiceNumbers(String claimantsSSN, Timestamp dueDate) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getDepositRule(int)
	 */
	public String getDepositRule(int invoiceNumber) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getDisallowanceDate(int)
	 */
	public String getDisallowanceDate(int invoiceNumber) {
		AccountEntry ae = getAccountEntry(invoiceNumber);
		if (ae != null) {
			return ae.getDisallowanceDate().toString();
		}
		else {
			return "";
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getDiscount(int)
	 */
	public String getDiscount(int invoiceNumber) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getDiscount1(int)
	 */
	public String getDiscount1(int invoiceNumber) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getDiscount2(int)
	 */
	public String getDiscount2(int invoiceNumber) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getDisplay(int)
	 */
	public String getDisplay(int invoiceNumber) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getDisplayForm(int)
	 */
	public String getDisplayForm(int invoiceNumber) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getDisplayFormType(int)
	 */
	public String getDisplayFormType(int invoiceNumber) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getDisplayFormURL(int)
	 */
	public String getDisplayFormURL(int invoiceNumber) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getDueDate(int)
	 */
	public Calendar getDueDate(int invoiceNumber) {
		Calendar cal = Calendar.getInstance();
		AccountEntry ae = getAccountEntry(invoiceNumber);
		if (ae != null) {
			Date date = ae.getDueDate();
			if (date != null) {
				cal.setTime(date);
			}
			return cal;
		}
		else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getDueDateFrom(int)
	 */
	public String getDueDateFrom(int invoiceNumber) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getDueDateTo(int)
	 */
	public String getDueDateTo(int invoiceNumber) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getExchangeBank(int)
	 */
	public String getExchangeBank(int invoiceNumber) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getExchangeClaim(int)
	 */
	public String getExchangeClaim(int invoiceNumber) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getExchangeCurrency(int)
	 */
	public String getExchangeCurrency(int invoiceNumber) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getExchangeRule(int)
	 */
	public String getExchangeRule(int invoiceNumber) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getExchangeType(int)
	 */
	public String getExchangeType(int claimNumber) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getFinalDueDate(int)
	 */
	public Calendar getFinalDueDate(int invoiceNumber) {
		Calendar cal = Calendar.getInstance();
		AccountEntry ae = getAccountEntry(invoiceNumber);
		if (ae != null) {
			IWTimestamp t = new IWTimestamp(ae.getFinalDueDate());
			Date date = t.getDate();
			if (date != null) {
				cal.setTime(date);
			}
			return cal;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getLoginName()
	 */
	public String getUsername() {
		if (this.bankInfo != null) {
			return this.bankInfo.getUsername();
		}
		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getLoginPsw()
	 */
	public String getPassword() {
		if (this.bankInfo != null) {
			return this.bankInfo.getPassword();
		}
		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getNoteNumber(int)
	 */
	public String getNoteNumber(int invoiceNumber) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getNotificationAndPaymentFee1(int)
	 */
	public double getNotificationAndPaymentFee1(int invoiceNumber) {
		AccountEntry ae = getAccountEntry(invoiceNumber);
		if (ae != null) {
			return ae.getNotificationAndPaymentFee1();
		}
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getNotificationAndPaymentFee2(int)
	 */
	public double getNotificationAndPaymentFee2(int invoiceNumber) {
		AccountEntry ae = getAccountEntry(invoiceNumber);
		if (ae != null) {
			return ae.getNotificationAndPaymentFee2();
		}
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getOtherCost(int)
	 */
	public double getOtherCost(int invoiceNumber) {
		AccountEntry ae = getAccountEntry(invoiceNumber);
		if (ae != null) {
			return ae.getOtherCost();
		}
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getOtherOverdueCost(int)
	 */
	public String getOtherOverdueCost(int invoiceNumber) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getOverdueFee(int)
	 */
	public String getOverdueFee(int invoiceNumber) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getOverdueFee1(int)
	 */
	public String getOverdueFee1(int invoiceNumber) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getOverdueFee2(int)
	 */
	public String getOverdueFee2(int invoiceNumber) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getPayersSSN(int)
	 */
	public String getPayersSSN(int invoiceNumber) {
		AccountEntry ae = getAccountEntry(invoiceNumber);
		if (ae != null) {
			User user = getUserByUserId(ae.getUserId());
			return user.getPersonalID();
		}
		else {
			return "";
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getPaymentRule(int)
	 */
	public String getPaymentCode(int invoiceNumber) {
		AccountEntry ae = getAccountEntry(invoiceNumber);
		if (ae != null) {
			return ae.getPaymentCode();
		}
		return "1";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getPenalIntrest(int)
	 */
	public String getPenalIntrest(int invoiceNumber) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getPenalIntrestBankRate(int)
	 */
	public String getPenalIntrestCode(int invoiceNumber) {
		AccountEntry ae = getAccountEntry(invoiceNumber);
		if (ae != null) {
			return ae.getPenalIntrestCode();
		}
		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getPenalIntrestProsent(int)
	 */
	public double getPenalIntrestProsent(int invoiceNumber) {
		AccountEntry ae = getAccountEntry(invoiceNumber);
		if (ae != null) {
			return ae.getPenalIntrestProsent();
		}
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getPenalIntrestRule(int)
	 */
	public String getPenalIntrestRule(int invoiceNumber) {
		AccountEntry ae = getAccountEntry(invoiceNumber);
		if (ae != null) {
			return ae.getPenalIntrestRule();
		}
		return "1";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getReferenceNumber(int)
	 */
	public int getBatchNumber(int invoiceNumber) {
		AccountEntry ae = getAccountEntry(invoiceNumber);
		if (ae != null) {
			return ae.getBatchNumber();
		}
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getSessionId()
	 */
	public String getSessionId() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getStatus(int)
	 */
	public String getStatus(int invoiceNumber) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getTradeNumber(int)
	 */
	public String getTradeNumber(int invoiceNumber) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getTransactionDayFrom(int)
	 */
	public String getTransactionDayFrom(int invoiceNumber) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idega.block.finance.business.BankFileManager#getTransactionDayTo(int)
	 */
	public String getTransactionDayTo(int invoiceNumber) {
		return null;
	}

	/**
	 * returns the status of the invoice. The status can be: NOT PAYED - ÓGREIDD PAYED - GREIDD CREATED - STOFNUD SENT - SEND ERROR - VILLA
	 */
	public String getInvoiceStatus(int invoiceNumber) {
		AccountEntry ae = getAccountEntry(invoiceNumber);
		if (ae != null) {
			return ae.getInvoiceStatus();
		}
		return "STOFNUD";

	}

	public void setInvoiceStatus(String status, int invoiceNumber) {
		AccountEntry ae = getAccountEntry(invoiceNumber);
		if (ae != null) {
			ae.setInvoiceStatus(status);
		}

	}

	private AccountEntry getAccountEntry(int invoiceNumber) {
		AccountEntry ae = null;
		AccountEntryHome aeh = null;
		try {
			aeh = (AccountEntryHome) IDOLookup.getHome(AccountEntry.class);
		}
		catch (IDOLookupException e) {
			e.printStackTrace();
		}
		if (aeh != null) {
			try {
				ae = aeh.findByInvoiceNumber(invoiceNumber);
			}
			catch (FinderException e1) {
				e1.printStackTrace();
			}
		}
		return ae;
	}

	private BankInfo getBankInfo(int groupId) {
		BankInfo bi = null;
		BankInfoHome bih = null;
		try {
			bih = (BankInfoHome) IDOLookup.getHome(BankInfo.class);
		}
		catch (IDOLookupException e) {
			e.printStackTrace();
		}
		if (bih != null) {
			try {
				bi = bih.findByGroupId(groupId);
			}
			catch (FinderException e1) {
				e1.printStackTrace();
			}
		}
		return bi;
	}

	private User getUserByUserId(int userId) {
		User u = null;
		UserHome uh = null;
		try {
			uh = (UserHome) IDOLookup.getHome(User.class);
		}
		catch (IDOLookupException e) {
			e.printStackTrace();
		}
		if (uh != null) {
			try {
				u = uh.findByPrimaryKey(new Integer(userId));
			}
			catch (FinderException e1) {
				e1.printStackTrace();
			}
		}
		return u;
	}

	/*
	 * private Group getGroupByGroupId(int groupId) { Group g = null; GroupHome gh = null; try { gh = (GroupHome) IDOLookup.getHome(Group.class); }
	 * catch (IDOLookupException e) { e.printStackTrace(); } if (gh != null) { try { g = gh.findByPrimaryKey(new Integer(groupId)); } catch
	 * (FinderException e1) { e1.printStackTrace(); } } return g; }
	 */

	public Batch getBatch(int batchNumber) {
		Batch b = null;

		try {
			b = ((BatchHome) IDOLookup.getHome(Batch.class)).findByPrimaryKey(new Integer(batchNumber));
		}
		catch (IDOLookupException e) {
			e.printStackTrace();
		}
		catch (FinderException e) {
			e.printStackTrace();
		}

		return b;
	}
}
