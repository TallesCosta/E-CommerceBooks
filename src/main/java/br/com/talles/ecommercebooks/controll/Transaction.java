
package br.com.talles.ecommercebooks.controll;

import javax.servlet.http.HttpServletRequest;

public class Transaction {
	
	HttpServletRequest request;
	String operation;

	public Transaction() {
	}

	public Transaction(String operation) {
		this.operation = operation;
	}
	
	public Transaction(HttpServletRequest request, String operation) {
		this.request = request;
		this.operation = operation;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public String getOperation() {
		return operation;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	
	
}
