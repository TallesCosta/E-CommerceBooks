
package br.com.talles.ecommercebooks.persistence.dao;

import br.com.talles.ecommercebooks.domain.Entity;
import java.util.List;

public class BookDao extends AbstractDao {

	@Override
	public List<Entity> select() {
		return null;
	}

	@Override
	public boolean save(Entity entity) {
		return false;
	}

	@Override
	public boolean delete(Entity entity) {
		return false;
	}

	@Override
	public Entity find(Entity entity) {
		return null;
	}

	@Override
	public boolean update(Entity entity) {
		return false;
	}	
	
}
