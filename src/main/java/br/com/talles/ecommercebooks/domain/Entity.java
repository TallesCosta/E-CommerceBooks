package br.com.talles.ecommercebooks.domain;

public class Entity {

	private long id;
	private boolean enabled;

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
