package br.com.talles.ecommercebooks.domain;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
        
        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            
            builder.append(getClass().getSimpleName())
                   .append("(");
            
            Field[] fields = getClass().getDeclaredFields();
            for(Field f : fields) {
                builder.append(f.getName())
                       .append("=");
                
                String value = "(xxx)";
                
                for(Method m : getClass().getMethods()) {
                    if(!(m.getName().startsWith("get") && m.getName().length() == f.getName().length() + 3)
                            || !(m.getName().toLowerCase().endsWith(f.getName().toLowerCase())))
                        continue;
                    try {
                        value = m.invoke(this).toString();
                    } catch(InvocationTargetException | IllegalAccessException e) { }
                    break;
                }
                
                builder.append(value)
                      .append(",");
            }
            
            builder.append(");");
            return builder.toString();
        }
	
}
