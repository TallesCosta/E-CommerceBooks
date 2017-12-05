/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.talles.ecommercebooks.log;

import java.util.logging.Logger;

import br.com.talles.ecommercebooks.domain.Entity;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;

/**
 *
 * @author Fatec
 */
public class OperationLogger {
    
    static private OperationLogger self;
    
    static private final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    static private FileHandler txtFile;
    
    static public OperationLogger getInstance() {
        if(self == null)
            self = new OperationLogger();
        return self;
    }
    
    private OperationLogger() {
        try {
            String path = System.getProperty("user.home") + File.separatorChar
                    + "ecommercebooks" + File.separatorChar + "logs";
            File tmp = new File(path);
            tmp.mkdirs();
            
            path += File.separatorChar + "log.txt";
            tmp = new File(path);
            tmp.createNewFile();
            
            Formatter formatter = new DBOperationFormatter();
            
            txtFile = new FileHandler(path);
            txtFile.setFormatter(formatter);
            
            LOGGER.addHandler(txtFile);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public void log(Entity entity, String operation) {
        StringBuilder buffer = new StringBuilder();
        try {
            buffer.append("OPERATION:\t")
                  .append(operation)
                  .append("\nENTITY:\t")
                  .append(entity.getClass().getSimpleName())
                  .append("\n");
            
            Field[] fields = entity.getClass().getDeclaredFields();
            for(Field f : fields) {
                buffer.append(f.getName())
                      .append(": ");
                
                String value = "";
                
                for(Method m : entity.getClass().getMethods()) {
                    if(!(m.getName().startsWith("get") && m.getName().length() == f.getName().length() + 3)
                            || !(m.getName().toLowerCase().endsWith(f.getName().toLowerCase())))
                        continue;
                    value = m.invoke(entity).toString();
                }
                
                if(value.equals(""))
                    value = "Wasn't found";
                
                buffer.append(value)
                      .append("\n");
            }
        } catch(IllegalAccessException | InvocationTargetException e) {
            buffer.append("Could not get value of field from ")
                    .append(entity.getClass().getSimpleName());
        } finally {
            LOGGER.info(buffer.toString());
            System.out.println(buffer.toString());
        }
    }
}
