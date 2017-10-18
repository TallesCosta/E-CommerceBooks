package br.com.talles.ecommercebooks.domain;

import br.com.talles.ecommercebooks.domain.customer.User;
import java.util.Date;

public class History extends Entity {
	
	private Date date;
	private User user;
	private Entity entity;

	public History() {
		super(true);
	}

	public History(Date date, User user) {
		super(true);
		this.date = date;
		this.user = user;
	}

	public History(Date date, User user, Entity entity) {
		super(true);
		this.date = date;
		this.user = user;
		this.entity = entity;
	}
	
	public History(Date date, User user, Entity entity, long id) {
		super(id, true);
		this.date = date;
		this.user = user;
		this.entity = entity;
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

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
}
