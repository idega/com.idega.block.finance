package com.idega.block.finance.hibernate.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

import com.idega.user.data.bean.Group;

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
			query = "SELECT p FROM Period p JOIN p.club c WHERE CURRENT_TIMESTAMP > p.fromDate AND CURRENT_TIMESTAMP <= p.toDate AND c.groupID = :club " +
			"AND p.controlsMembership = :controlsMembership order by p.toDate desc"
	)
})
@XmlTransient
public class Period implements Serializable {
	private static final long serialVersionUID = -836514393694719755L;

	public static final String TABLE_NAME = "FIN_PERIOD";

	private static final String COLUMN_ID = "FIN_PERIOD_ID";
	private static final String COLUMN_GROUP_ID = "GROUP_ID";
	private static final String COLUMN_DIVISION_ID = "DIVISION_ID";
	private static final String COLUMN_CLUB_ID = "CLUB_ID";
	private static final String COLUMN_NAME = "NAME";
	private static final String COLUMN_FROM_DATE = "FROM_DATE";
	private static final String COLUMN_TO_DATE = "TO_DATE";
	private static final String COLUMN_VIRTUAL_GROUP = "VIRTUAL_GROUP";  //1. OPTION Putting the real groups hidden under the virtual group separated by COMMA //2. OPTION <club id or division id or union id>;<group type as: iwme_club>;<virtual group id as: general-members>
	private static final String COLUMN_CONFIRMATION_DATE = "CONFIRMATION_DATE";
	private static final String COLUMN_CONTROLS_MEMBERSHIP = "controls_membership";

	public static final String GET_ALL = "getAll";
	public static final String GET_BY_CONFIRMATION_DATE = "getByConfirmationDate";
	public static final String QUERY_FIND_VALID_FOR_CLUB = "findValidForClub";

	public static final String PARAM_CONFIRMATION_DATE = "confirmationDate";

	@Id
	@Column(name = COLUMN_ID)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

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

	@Column(name = COLUMN_CONTROLS_MEMBERSHIP)
	private Boolean controlsMembership;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
		return controlsMembership;
	}

	public void setControlsMembership(Boolean controlsMembership) {
		this.controlsMembership = controlsMembership;
	}




}
