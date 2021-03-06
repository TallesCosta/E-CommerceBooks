package br.com.talles.ecommercebooks.domain.customer;

import br.com.talles.ecommercebooks.domain.Entity;
import java.util.Date;

public class CreditCard extends Entity {

	private String number;
	private String printedName;
	private String securityCode;
	private Date expirationDate;
	private CardCompany cardCompany;
	private Customer customer;
	private double paymentValue;

	public CreditCard() {
		super(true);
	}

	public CreditCard(long id) {
		super(id, true);
	}

	public CreditCard(String number, long id) {
		super(id, true);
		this.number = number;
	}

	public CreditCard(double paymentValue, long id) {
		super(id, true);
		this.paymentValue = paymentValue;
	}

	public CreditCard(String number, String printedName, String securityCode, Date expirationDate,
					  CardCompany cardCompany) {
		super(true);
		this.number = number;
		this.printedName = printedName;
		this.securityCode = securityCode;
		this.expirationDate = expirationDate;
		this.cardCompany = cardCompany;
	}

	public CreditCard(String number, String printedName, String securityCode, Date expirationDate,
					  CardCompany cardCompany, long id) {
		super(id, true);
		this.number = number;
		this.printedName = printedName;
		this.securityCode = securityCode;
		this.expirationDate = expirationDate;
		this.cardCompany = cardCompany;
	}

	public CreditCard(String number, String printedName, String securityCode, Date expirationDate,
					  CardCompany cardCompany, Customer customer, long id) {
		super(id, true);
		this.number = number;
		this.printedName = printedName;
		this.securityCode = securityCode;
		this.expirationDate = expirationDate;
		this.cardCompany = cardCompany;
		this.customer = customer;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPrintedName() {
		return printedName;
	}

	public void setPrintedName(String printedName) {
		this.printedName = printedName;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public CardCompany getCardCompany() {
		return cardCompany;
	}

	public void setCardCompany(CardCompany cardCompany) {
		this.cardCompany = cardCompany;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public double getPaymentValue() {
		return paymentValue;
	}

	public void setPaymentValue(double paymentValue) {
		this.paymentValue = paymentValue;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		CreditCard creditCard = (CreditCard) o;
		return number.equals(creditCard.number) &&
				getId() == creditCard.getId();
	}

}
