package com.idega.block.finance.hibernate.data;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

import com.idega.core.file.data.bean.ICFile;
import com.idega.user.data.bean.Group;
import com.idega.util.CoreConstants;
import com.idega.util.StringHandler;

@Entity
@Cacheable
@Table(name = Period.TABLE_NAME)
@NamedQueries({
	@NamedQuery(
			name = Period.GET_ALL,
			query = "from Period p order by p.id"
	),
	@NamedQuery(
			name = Period.GET_BY_CONFIRMATION_DATE,
			query = "from Period p where p.confirmationDate > :confirmationDate order by p.id"
	),
	@NamedQuery(
			name = Period.QUERY_FIND_VALID_FOR_CLUB,
			query = "SELECT p FROM Period p JOIN p.club c WHERE CURRENT_TIMESTAMP >= p.fromDate AND (CURRENT_TIMESTAMP <= p.toDate or p.toDate is null) AND c.groupID = :club " +
			"AND p.controlsMembership = :" + Period.PARAM_CONTROLS_MEMBERSHIP + " order by p.toDate desc"
	),
	@NamedQuery(
			name = Period.GET_ALL_INFINITE,
			query = "SELECT p FROM Period p WHERE p.toDate IS NULL order by p.id asc"
	),
	@NamedQuery(
			name = Period.QUERY_FIND_ALL_NOT_ENDED,
			query = "SELECT p FROM Period p WHERE p.toDate IS NULL OR CURRENT_TIMESTAMP <= p.toDate order by p.fromDate desc"
	)
})
@XmlTransient
public class Period implements Serializable {
	private static final long serialVersionUID = -836514393694719755L;

	public static final String	TABLE_NAME = "FIN_PERIOD",
								COLUMN_ID = "FIN_PERIOD_ID";

	private static final String COLUMN_GROUP_ID = "GROUP_ID",
								COLUMN_DIVISION_ID = "DIVISION_ID",
								COLUMN_CLUB_ID = "CLUB_ID",
								COLUMN_NAME = "NAME",
								COLUMN_FROM_DATE = "FROM_DATE",
								COLUMN_TO_DATE = "TO_DATE",
								COLUMN_VIRTUAL_GROUP = "VIRTUAL_GROUP",  //1. OPTION Putting the real groups hidden under the virtual group separated by COMMA //2. OPTION <club id or division id or union id>;<group type as: iwme_club>;<virtual group id as: general-members>
								COLUMN_CONFIRMATION_DATE = "CONFIRMATION_DATE",
								COLUMN_CONTROLS_MEMBERSHIP = "controls_membership",
								COLUMN_GENERATED_PAYMENTS_DATE = "GENERATED_PAYMENTS_DATE",
								COLUMN_MEMBER_EMAIL_CONTENT = "MEMBER_EMAIL_CONTENT",
								COLUMN_CERTIFICATE_ADD_TEXT = "CERTIFICATE_ADD_TEXT",
								COLUMN_CERTIFICATE_COLOR = "CERTIFICATE_COLOR",
								COLUMN_OLD = "OLD",
								COLUMN_ATTACHMENT = "ATTACHMENT",
								COLUMN_DAY_OF_WEEK = "day_of_week",
								COLUMN_DAY_OF_WEEK_START = "day_of_week_start",
								COLUMN_DAY_OF_WEEK_END = "day_of_week_end";

	public static final String	GET_ALL = "getAll",
								GET_BY_CONFIRMATION_DATE = "getByConfirmationDate",
								QUERY_FIND_VALID_FOR_CLUB = "findValidForClub",
								QUERY_FIND_ALL_NOT_ENDED = "Period.findAllNotEnded",
								GET_ALL_INFINITE = "Period.getAllInfinite",

								PARAM_CONFIRMATION_DATE = "confirmationDate",
								PARAM_CONTROLS_MEMBERSHIP = "controlsMembership",

								COLUMN_MAX_DURATION = "max_duration";

	@Id
	@Column(name = COLUMN_ID)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = COLUMN_GROUP_ID)
	private Group group;

	@ManyToOne
	@JoinColumn(name = COLUMN_DIVISION_ID)
	private Group division;

	@ManyToOne
	@JoinColumn(name = COLUMN_CLUB_ID)
	private Group club;

	@Column(name = COLUMN_NAME)
	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = COLUMN_FROM_DATE)
	private Date fromDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = COLUMN_TO_DATE)
	private Date toDate;

	@Column(name = COLUMN_VIRTUAL_GROUP)
	private String virtualGroup;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = COLUMN_CONFIRMATION_DATE)
	private Date confirmationDate;

	@Column(name = COLUMN_CONTROLS_MEMBERSHIP, length = 1)
	private Character controlsMembership;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = COLUMN_GENERATED_PAYMENTS_DATE)
	private Date generatedPaymentsDate;

	@Lob
	@Column(name = COLUMN_MEMBER_EMAIL_CONTENT)
	private String memberEmailContent;

	@Column(name = COLUMN_CERTIFICATE_ADD_TEXT)
	private String certificateAdditionalText;

	@Column(name = COLUMN_CERTIFICATE_COLOR)
	private String certificateColor;

	@Column(name = COLUMN_OLD, length = 1)
	private Character old;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = COLUMN_ATTACHMENT)
	private ICFile attachment;

	@Column(name = COLUMN_DAY_OF_WEEK)
	private Integer dayOfWeek;

	@Column(name = COLUMN_DAY_OF_WEEK_START)
	private String dayOfWeekStart;

	@Column(name = COLUMN_DAY_OF_WEEK_END)
	private String dayOfWeekEnd;

	@Column(name = COLUMN_MAX_DURATION)
	private Integer maxDuration;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Group getDivision() {
		return division;
	}

	public void setDivision(Group division) {
		this.division = division;
	}

	public Group getClub() {
		return club;
	}

	public void setClub(Group club) {
		this.club = club;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getVirtualGroup() {
		return virtualGroup;
	}

	public void setVirtualGroup(String virtualGroup) {
		this.virtualGroup = virtualGroup;
	}

	public Date getConfirmationDate() {
		return confirmationDate;
	}

	public void setConfirmationDate(Date confirmationDate) {
		this.confirmationDate = confirmationDate;
	}

	public Boolean getControlsMembership() {
		if (this.controlsMembership == null) {
			return false;
		}
		return this.controlsMembership == CoreConstants.CHAR_Y;
	}

	public void setControlsMembership(Boolean controlsMembership) {
		this.controlsMembership = controlsMembership ? CoreConstants.CHAR_Y : CoreConstants.CHAR_N;
	}

	public Date getGeneratedPaymentsDate() {
		return generatedPaymentsDate;
	}

	public void setGeneratedPaymentsDate(Date generatedPaymentsDate) {
		this.generatedPaymentsDate = generatedPaymentsDate;
	}

	public String getCertificateAdditionalText() {
		return certificateAdditionalText;
	}

	public void setCertificateAdditionalText(String certificateAdditionalText) {
		this.certificateAdditionalText = certificateAdditionalText;
	}

	public String getCertificateColor() {
		return certificateColor;
	}

	public void setCertificateColor(String certificateColor) {
		this.certificateColor = certificateColor;
	}

	public String getMemberEmailContent() {
		 String emailContent = null;
		 try {
			 if (memberEmailContent != null) {
				 InputStream stream = new ByteArrayInputStream(memberEmailContent.getBytes());
			     if (stream != null) {
			    	 emailContent = StringHandler.getContentFromInputStream(stream);
			     }
			 }
		 } catch (Exception e) {
			 e.printStackTrace();
		 }

		 return emailContent;
	}

	public void setMemberEmailContent(String memberEmailContent) {
		this.memberEmailContent = memberEmailContent;
	}

	public Boolean getOld() {
		if (this.old == null) {
			return false;
		}
		return this.old == CoreConstants.CHAR_Y;
	}

	public void setOld(Boolean old) {
		this.old = old ? CoreConstants.CHAR_Y : CoreConstants.CHAR_N;
	}

	public ICFile getAttachment() {
		return attachment;
	}

	public void setAttachment(ICFile attachment) {
		this.attachment = attachment;
	}

	public Integer getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(Integer dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public String getDayOfWeekStart() {
		return dayOfWeekStart;
	}

	public void setDayOfWeekStart(String dayOfWeekStart) {
		this.dayOfWeekStart = dayOfWeekStart;
	}

	public String getDayOfWeekEnd() {
		return dayOfWeekEnd;
	}

	public void setDayOfWeekEnd(String dayOfWeekEnd) {
		this.dayOfWeekEnd = dayOfWeekEnd;
	}

	public Integer getMaxDuration() {
		return maxDuration;
	}

	public void setMaxDuration(Integer maxDuration) {
		this.maxDuration = maxDuration;
	}

	@Override
	public String toString() {
		return "ID: " + getId() + ", controls membership: " + getControlsMembership() + ", from: " + getFromDate() + ", to: " + getToDate();
	}

}