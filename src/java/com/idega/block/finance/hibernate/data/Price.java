package com.idega.block.finance.hibernate.data;

import java.io.Serializable;

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

@Entity
@Cacheable
@Table(name = Price.TABLE_NAME)
@NamedQueries({
	@NamedQuery(name = Price.GET_PRICES_BY_PERIOD_ID, query = "FROM Price price WHERE price.period.id = :" + Price.PARAM_PERIOD_ID)
})
public class Price implements Serializable {
	private static final long serialVersionUID = 2050014558123898958L;

	public static final String TABLE_NAME = "FIN_PRICE";

	private static final String COLUMN_ID = "FIN_PRICE_ID";
	private static final String COLUMN_PERIOD_ID = "PERIOD_ID";
	private static final String COLUMN_NAME = "NAME";
	private static final String COLUMN_AGE_FROM = "AGE_FROM";
	private static final String COLUMN_AGE_TO = "AGE_TO";
	private static final String COLUMN_PRICE = "PRICE";

	public static final String GET_PRICES_BY_PERIOD_ID = "getPricesByPeriodId";
	public static final String PARAM_PERIOD_ID = "periodId";

	@Id
	@Column(name = COLUMN_ID)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = COLUMN_PERIOD_ID)
	private Period period;

	@Column(name = COLUMN_NAME)
	private String name;

	@Column(name = COLUMN_AGE_FROM)
	private Integer ageFrom;

	@Column(name = COLUMN_AGE_TO)
	private Integer ageTo;

	@Column(name = COLUMN_PRICE)
	private Double price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAgeFrom() {
		return ageFrom;
	}

	public void setAgeFrom(Integer ageFrom) {
		this.ageFrom = ageFrom;
	}

	public Integer getAgeTo() {
		return ageTo;
	}

	public void setAgeTo(Integer ageTo) {
		this.ageTo = ageTo;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}




}
