package com.idega.block.finance.presentation;


import com.idega.util.text.Edit;

import com.idega.block.finance.data.*;
import com.idega.block.building.data.*;
import com.idega.block.finance.business.*;
import com.idega.block.building.business.BuildingFinder;
import com.idega.block.building.business.BuildingCacher;
import com.idega.block.finance.presentation.KeyEditor;
import com.idega.data.GenericEntity;
import com.idega.presentation.IWContext;
import com.idega.presentation.ui.*;
import com.idega.presentation.Table;
import com.idega.presentation.PresentationObject;
import com.idega.presentation.Block;
import com.idega.presentation.text.*;
import com.idega.presentation.Image;
import com.idega.util.idegaTimestamp;
import java.sql.SQLException;
import java.util.StringTokenizer;
import java.util.List;
import java.util.Vector;
import java.util.Iterator;
import java.util.Hashtable;
import java.util.Map;
import com.idega.idegaweb.IWBundle;
import com.idega.idegaweb.IWResourceBundle;
import com.idega.util.idegaTimestamp;
import com.idega.core.user.data.User;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2000-2001 idega.is All Rights Reserved
 * Company:      idega
  *@author <a href="mailto:aron@idega.is">Aron Birkir</a>
 * @version 1.1
 */

public class Accounts extends Block {

  protected final int ACT1 = 1,ACT2 = 2, ACT3 = 3,ACT4  = 4,ACT5 = 5;
  public  String strAction = "tt_action";
  protected boolean isAdmin = false;

  private int iCashierId = 1;
  private static String prmGroup = "taccs_grp";
  private static String prmNewAccount = "taccs_newaccount";
  private static String prmAccountUserId = "taccs_accuser_id";
  private List accountUsers = null;
  private List accounts = null;

  private final static String IW_BUNDLE_IDENTIFIER="com.idega.block.finance";
  protected IWResourceBundle iwrb;
  protected IWBundle iwb,core;

  public Accounts() {

  }

  public String getLocalizedNameKey(){
    return "accounts";
  }

  public String getLocalizedNameValue(){
    return "Accounts";
  }


  protected void control(IWContext iwc){
    /*
    java.util.Enumeration E = iwc.getParameterNames();
    while(E.hasMoreElements()){
      String prm = (String) E.nextElement();
      System.err.println("prm "+prm+" : "+iwc.getParameter(prm));
    }
    */
    if(isAdmin){
      int iCategoryId = Finance.parseCategoryId(iwc);
      Table T = new Table();
      T.setCellpadding(0);
      T.setCellspacing(0);
      T.setWidth("100%");
      if(iwc.isParameterSet(AccountViewer.prmAccountId)){
        add(new AccountViewer());
      }
      else if(iwc.isParameterSet(prmNewAccount)){
        int iUserId = Integer.parseInt(iwc.getParameter(prmNewAccount));
        getNewAccountForm(iUserId,iCategoryId);
      }
      else if(iwc.isParameterSet("sf_search")){
        performSearch(iwc,iCategoryId);
        T.add(getSearchForm(iwc,iCategoryId),1,1);
        T.add(new HorizontalRule(),1,2);
        T.add(getAccountListTable(iCategoryId),1,3);
      }
      else{
        T.add(getSearchForm(iwc,iCategoryId),1,1);
        T.add(new HorizontalRule(),1,2);
      }
      add(T);

    }
    else
      add(iwrb.getLocalizedString("access_denied","Access denies"));
  }

  private PresentationObject getSearchForm(IWContext iwc,int iCategoryId){
    Form F = new Form();
    Table T = new Table(3,4);
    T.add(Edit.formatText(iwrb.getLocalizedString("account_id","Account id")),1,1);
    T.add(Edit.formatText(iwrb.getLocalizedString("first_name","First name")),1,3);
    T.add(Edit.formatText(iwrb.getLocalizedString("middle_name","Middle name")),2,3);
    T.add(Edit.formatText(iwrb.getLocalizedString("last_name","Last name")),3,3);

    String id = iwc.getParameter("sf_id");
    String first = iwc.getParameter("sf_firstname");
    String middle = iwc.getParameter("sf_middlename");
    String last = iwc.getParameter("sf_lastname");

    TextInput accountid = new TextInput("sf_id");
    TextInput firstname = new TextInput("sf_firstname");
    TextInput middlename = new TextInput("sf_middlename");
    TextInput lastname = new TextInput("sf_lastname");
    String drpsel = iwc.isParameterSet("sf_type")?iwc.getParameter("sf_type"):"";
    DropdownMenu drpTypes = getAccountTypes("sf_type",drpsel,null);


    if(id!=null)
       accountid.setContent(id);

    if(first !=null)
      firstname.setContent(first);

    if(middle!=null)
      middlename.setContent(middle);

    if(last!=null)
      lastname.setContent(last);


    int len = 30;
    accountid.setLength(len);
    firstname.setLength(len);
    middlename.setLength(len);
    lastname.setLength(len);

    Edit.setStyle(accountid);
    Edit.setStyle(firstname);
    Edit.setStyle(middlename);
    Edit.setStyle(lastname);

    T.add(accountid,1,2);
    T.add(drpTypes,2,2);
    T.add(firstname,1,4);
    T.add(middlename,2,4);
    T.add(lastname,3,4);

    T.add(Finance.getCategoryParameter(iCategoryId));
    SubmitButton search = new SubmitButton("sf_search",iwrb.getLocalizedString("search","Search"));
    T.add(search,3,2);

    F.add(T);

    return F;
  }

   private PresentationObject getNewAccountForm(int iUserId,int iCategoryId){
    Form F = new Form();
    Table T = new Table(3,4);
    T.add(Edit.formatText(iwrb.getLocalizedString("account_id","Account id")),1,1);
    T.add(Edit.formatText(iwrb.getLocalizedString("first_name","First name")),1,3);
    T.add(Edit.formatText(iwrb.getLocalizedString("middle_name","Middle name")),2,3);
    T.add(Edit.formatText(iwrb.getLocalizedString("last_name","Last name")),3,3);

    TextInput accountid = new TextInput("sf_id");
    TextInput firstname = new TextInput("sf_firstname");
    TextInput middlename = new TextInput("sf_middlename");
    TextInput lastname = new TextInput("sf_lastname");

    int len = 30;
    accountid.setLength(len);
    firstname.setLength(len);
    middlename.setLength(len);
    lastname.setLength(len);

    T.add(accountid,1,2);
    T.add(getAccountTypes("sf_type","",null),2,2);
    T.add(firstname,1,4);
    T.add(middlename,2,4);
    T.add(lastname,3,4);

    T.add(Finance.getCategoryParameter(iCategoryId));
    SubmitButton search = new SubmitButton("sf_search",iwrb.getLocalizedString("search","Search"));
    T.add(search,3,2);

    F.add(T);

    return F;
  }

  private void performSearch(IWContext iwc,int iCategoryId){
    String id = null,first = null,middle = null,last = null,type = null;
    // See if we have an account id
    if(iwc.isParameterSet("sf_type"))
      type = iwc.getParameter("sf_type");
    boolean hasSomething = false;
    if(iwc.isParameterSet("sf_id"))
      id = iwc.getParameter("sf_id");
    if(!"".equals(id)){
      accounts = FinanceFinder.searchAccounts(id,first,middle,last,type,iCategoryId);
    }
    // Else we try to lookup by name
    else{
      if(iwc.isParameterSet("sf_firstname")){
        first = iwc.getParameter("sf_firstname");
        hasSomething = true;
      }
      if(iwc.isParameterSet("sf_middlename")){
        middle = iwc.getParameter("sf_middlename");
        hasSomething = true;
      }
      if(iwc.isParameterSet("sf_lastname")){
        last = iwc.getParameter("sf_lastname");
        hasSomething = true;
      }
      if(hasSomething){
        accounts = FinanceFinder.searchAccounts(id,first,middle,last,type,iCategoryId);
        accountUsers = FinanceFinder.searchAccountUsers(first,middle,last);
      }
    }

  }

  private Map getMapOfUsers(List listOfUsers){
    if(listOfUsers !=null){
      Hashtable H = new Hashtable(listOfUsers.size());
      Iterator I = listOfUsers.iterator();
      while(I.hasNext()){
        User u = (User) I.next();
        H.put(new Integer(u.getID()),u);
      }
      return H;
    }
    return null;
  }

  private PresentationObject getAccountListTable(int iCategoryId){
    Table T = new Table();
    int row = 1;
    Map M = getMapOfUsers(accountUsers);
    User U;
    if(accounts !=null){
      T.mergeCells(1,row,4,row);
      T.add(Edit.formatText(iwrb.getLocalizedString("users_with_accounts","Users with accounts")),1,row);
      row++;
      T.add(Edit.formatText(iwrb.getLocalizedString("account_id","Account id")),1,row);
      T.add(Edit.formatText(iwrb.getLocalizedString("user_name","User name")),2,row);
      T.add(Edit.formatText(iwrb.getLocalizedString("balance","Balance")),3,row);
      T.add(Edit.formatText(iwrb.getLocalizedString("last_updated","Last updated")),4,row);
      row++;
      Iterator I = accounts.iterator();
      Account A ;

      Integer uid;
      Link accountLink;
      while (I.hasNext()) {
        A = (Account) I.next();
        uid = new Integer(A.getUserId());
        if(M !=null && M.containsKey(uid)){
          U = (User) M.get(uid);
          M.remove(uid);
        }
        else
          U = FinanceFinder.getUser(uid.intValue());
        accountLink = new Link(Edit.formatText(A.getName()));
        accountLink.addParameter(AccountViewer.prmAccountId,A.getID());
        T.add(accountLink,1,row);
        T.add(Edit.formatText(U.getName()),2,row);
        T.add(Edit.formatText(Float.toString(A.getBalance())),3,row);
        T.add(Edit.formatText(A.getLastUpdated().toString()),4,row);
        row++;
      }
    }
    if(M != null && M.size() > 0){
        Image newImage = core.getImage("/shared/create.gif");
        T.mergeCells(1,row,4,row);
        T.add(Edit.formatText(iwrb.getLocalizedString("users_without_accounts","Users without accounts")),1,row);
        row++;
        T.mergeCells(1,row,2,row);
        T.mergeCells(3,row,4,row);
        row++;
        T.add(Edit.formatText(iwrb.getLocalizedString("user_name","")));
        Iterator I2 = M.values().iterator();
        while(I2.hasNext()){
          U = (User) I2.next();
          T.mergeCells(1,row,2,row);
          T.mergeCells(3,row,4,row);
          T.add(Edit.formatText(U.getName()),1,row);
          T.add(getNewAccountLink(newImage,U.getID(),iCategoryId),3,row);
          row++;
        }
      }
    return T;
  }

  private DropdownMenu getAccountTypes(String name,String selected,String display){
    DropdownMenu drp = new DropdownMenu(name);
    if(display != null)
      drp.addMenuElementFirst("",display);
    drp.addMenuElement(Account.typeFinancial);
    drp.addMenuElement(Account.typePhone);
    drp.setSelectedElement(selected);

    return drp;
  }

  private Link getNewAccountLink(PresentationObject obj,int iUserId,int iCategoryId){
    Link L = new Link(obj);
    L.addParameter(Finance.getCategoryParameter(iCategoryId));
    L.addParameter(prmNewAccount,iUserId);
    return L;
  }

  private PresentationObject doMainTable(IWContext iwc){
    Form F = new Form();

    return F;
  }

  public String getBundleIdentifier(){
    return IW_BUNDLE_IDENTIFIER;
  }

  public void main(IWContext iwc){
    iwrb = getResourceBundle(iwc);
    iwb = getBundle(iwc);
    core = iwc.getApplication().getCoreBundle();
    isAdmin = iwc.hasEditPermission(this);
    control(iwc);
  }
}