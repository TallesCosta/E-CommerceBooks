package br.com.talles.ecommercebooks.business;

import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Serializator implements IStrategy {

	@Override
	public Result process(Entity entity, Result result) {
		IStrategy definesPathSerializable = new DefinesPathSerializable();
		definesPathSerializable.process(entity, result);
		
		FileOutputStream fileOutputStream;
		try {
			// If file not found, create this
			fileOutputStream = new FileOutputStream(entity.getHistory().getPath());
			// Abstracs the complexity of file manipulation, enabling to make operation with objects
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(entity);
			objectOutputStream.close();
		} catch (FileNotFoundException ex) {
			Logger.getLogger(Serializator.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(Serializator.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return result;
	}
	
}
