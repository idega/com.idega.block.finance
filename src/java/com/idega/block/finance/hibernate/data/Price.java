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

import com.idega.util.CoreConstants;

@Entity
@Cacheable
@Table(name = Price.TABLE_NAME)
@NamedQueries({
	@NamedQuery(name = Price.GET_PRICES_BY_PERIOD_ID, query = "FROM Price price WHERE price.period.id = :" + Price.PARAM_PERIOD_ID + " AND price.extraType IS NULL"),
	@NamedQuery(name = Price.GET_PRICES_BY_IDS, query = "FROM Price price WHERE price.id IN (:" + Price.PARAM_PRICE_IDS + ")" + " AND price.extraType IS NULL"),
	@NamedQuery(name = Price.GET_PRICES_BY_PERIOD_ID_AND_EXTRA_TYPE, query = "FROM Price price WHERE price.period.id = :" + Price.PARAM_PERIOD_ID + " AND price.extraType = :" + Price.PARAM_EXTRA_TYPE),
	@NamedQuery(
			name = Price.GET_PRICES_BY_PERIOD_ID_AND_IS_DEFAULT,
			query = "FROM Price price WHERE price.period.id = :" + Price.PARAM_PERIOD_ID
				+ " AND (price.isDefault IS NULL OR price.isDefault = 'Y' OR price.isDefault = '1')"
				+ " AND price.extraType IS NULL"
	),
	@NamedQuery(
			name = Price.GET_PRICES_BY_PERIOD_ID_AND_IS_OPTIONAL,
			query = "FROM Price price WHERE price.period.id = :" + Price.PARAM_PERIOD_ID
				+ " AND (price.isDefault = 'N' OR price.isDefault = '0')"
	)
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
	private static final String COLUMN_TYPE = "TYPE";
	private static final String COLUMN_EXTRA_TYPE = "EXTRA_TYPE";
	private static final String COLUMN_DATE_MONTHLY_PAYMENTS = "DATE_MONTHLY_PAYMENTS";
	private static final String COLUMN_IS_DEFAULT = "IS_DEFAULT";
	private static final String COLUMN_CERTIFICATE_ADD_TEXT = "CERTIFICATE_ADD_TEXT";

	public static final String GET_PRICES_BY_PERIOD_ID = "Price.getPricesByPeriodId";
	public static final String GET_PRICES_BY_IDS = "Price.getPricesByIds";
	public static final String GET_PRICES_BY_PERIOD_ID_AND_EXTRA_TYPE = "Price.getPricesByPeriodIdAndExtraType";
	public static final String GET_PRICES_BY_PERIOD_ID_AND_IS_DEFAULT = "Price.getPricesByPeriodIdAndIsDefault";
	public static final String GET_PRICES_BY_PERIOD_ID_AND_IS_OPTIONAL = "Price.getPricesByPeriodIdAndIsOptional";

	public static final String PARAM_PERIOD_ID = "periodId";
	public static final String PARAM_PRICE_IDS = "priceIds";
	public static final String PARAM_EXTRA_TYPE = "extraType";
	public static final String PARAM_DEFAULT = "isDefault";

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

	@Column(name = COLUMN_TYPE)
	private String type;

	@Column(name = COLUMN_EXTRA_TYPE)
	private String extraType;

	@Column(name = COLUMN_DATE_MONTHLY_PAYMENTS)
	private Integer dateOfMonthlyPayments;

	@Column(name = COLUMN_IS_DEFAULT, length = 1)
	private Character isDefault;

	@Column(name = COLUMN_CERTIFICATE_ADD_TEXT)
	private String certificateAdditionalText;


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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExtraType() {
		return extraType;
	}

	public void setExtraType(String extraType) {
		this.extraType = extraType;
	}

	public Integer getDateOfMonthlyPayments() {
		return dateOfMonthlyPayments;
	}

	public void setDateOfMonthlyPayments(Integer dateOfMonthlyPayments) {
		this.dateOfMonthlyPayments = dateOfMonthlyPayments;
	}

	public Boolean getIsDefault() {
		if (this.isDefault == null) {
			return true;
		}
		return this.isDefault == CoreConstants.CHAR_Y;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault ? CoreConstants.CHAR_Y : CoreConstants.CHAR_N;
	}

	public String getCertificateAdditionalText() {
		return certificateAdditionalText;
	}

	public void setCertificateAdditionalText(String certificateAdditionalText) {
		this.certificateAdditionalText = certificateAdditionalText;
	}




}
