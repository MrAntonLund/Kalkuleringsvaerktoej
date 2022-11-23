package com.examprojectsummer2021.utilities;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author Carsten
 */

public class HolidaysCalculationUtility {

    Calendar calendar = new GregorianCalendar();

    public void getDateOfEasterSunday(){
        int currentYear = calendar.get(Calendar.YEAR);
        System.out.println(currentYear);
    }
    public void easter() {
        int currentYear = calendar.get(Calendar.YEAR);          //year

        //<editor-fold desc="Calculations for easter sunday - thanks wikipedia">
        int a = currentYear % 19;
        int b = (int) Math.floor(currentYear / 100);
        int c = currentYear % 100;
        int d = (int) Math.floor(b / 4);
        int e = b % 4;
        int f = (int) Math.floor((b + 8) / 25);
        int g = (int) Math.floor((b - f + 1) / 3);
        int h = (19 * a + b - d - g + 15) % 30;
        int i = (int) Math.floor(c / 4);
        int k = c % 4;
        int l = (32 + 2 * e + 2 * i - h - k) % 7;
        int m = (int) Math.floor((a + 11 * h + 22 * l) / 451);
        //</editor-fold>

        int n = (int) Math.floor((h + l - 7 * m + 114) / 31);   //month
        int p = (h + l - 7 * m + 114) % 31 + 1;                 //day


    }
}
