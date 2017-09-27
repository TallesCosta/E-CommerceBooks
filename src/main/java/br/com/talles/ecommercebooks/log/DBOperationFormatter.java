/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.talles.ecommercebooks.log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 *
 * @author Fatec
 */
final class DBOperationFormatter extends Formatter {
    
    private final String[] nomes = {"João das Neves", "Maria das Graças"};

    @Override
    public String format(LogRecord record) {
        return formatMessage(record);
    }

    @Override
    public String getHead(Handler h) {
        SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss.s");
        Date d = Calendar.getInstance().getTime();
        
        String dono = nomes[new Random().nextInt(nomes.length)];
        
        return "User:\t\t" + dono + "\nTime:\t\t" + df.format(d) + "\n";
    }

    @Override
    public String getTail(Handler h) {
        return "=========================\n";
    }
}
