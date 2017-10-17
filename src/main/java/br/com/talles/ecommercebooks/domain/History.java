package br.com.talles.ecommercebooks.domain;

import br.com.talles.ecommercebooks.domain.customer.User;
import java.util.Date;

public class History extends Entity {
	
	private String path;
	private Date date;
	private User user;

	public History() {
		super(true);
	}

	public History(String path, Date date, User user) {
		super(true);
		this.path = path;
		this.date = date;
		this.user = user;
	}

	public History(String path, Date date, User user, long id) {
		super(id, true);
		this.path = path;
		this.date = date;
		this.user = user;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
