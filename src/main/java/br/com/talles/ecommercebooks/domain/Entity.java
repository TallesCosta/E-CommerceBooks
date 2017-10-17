package br.com.talles.ecommercebooks.domain;

import java.io.Serializable;

public class Entity implements Serializable {

	private long id;
	private boolean enabled;
	private History history;

	public Entity() {
	}

	public Entity(long id) {
		this.id = id;
	}

	public Entity(boolean enabled) {
		this.enabled = enabled;
	}
	
	public Entity(long id, boolean enabled) {
		this.id = id;
		this.enabled = enabled;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public History getHistory() {
		return history;
	}

	public void setHistory(History history) {
		this.history = history;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		
		final Entity other = (Entity) obj;
		return this.id != other.id;
	}
	
}
