/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eagles.sabor_mel.model;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author dhiego.balthazar
 */
public class DateGenerator {
    
    private static final GregorianCalendar CALENDAR = new GregorianCalendar();

    public static int getDay() {
        return CALENDAR.get(Calendar.DAY_OF_MONTH);
    }
    
    public static int getMonth(){
        return CALENDAR.get(Calendar.MONTH);
    }
    
    public static int getYear(){
        return CALENDAR.get(Calendar.YEAR);
    }
}
