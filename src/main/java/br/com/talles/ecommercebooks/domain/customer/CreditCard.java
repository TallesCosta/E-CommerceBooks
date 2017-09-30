package br.com.talles.ecommercebooks.domain.customer;

import br.com.talles.ecommercebooks.domain.Entity;
import java.util.Date;

public class CreditCard extends Entity {

	private String number;
	private String printedName;
	private String securityCode;
	private Date expirationDate;
	private CardCompany cardCompany;

	public CreditCard() {
		super(true);
	}

	public CreditCard(String number, String printedName, String securityCode, Date expirationDate, CardCompany cardCompany, long id) {
		super(id, true);
		this.number = number;
		this.printedName = printedName;
		this.securityCode = securityCode;
		this.expirationDate = expirationDate;
		this.cardCompany = cardCompany;
	}

	public CreditCard(String number, String printedName, String securityCode, Date expirationDate, CardCompany cardCompany) {
		super(true);
		this.number = number;
		this.printedName = printedName;
		this.securityCode = securityCode;
		this.expirationDate = expirationDate;
		this.cardCompany = cardCompany;
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
	
}
