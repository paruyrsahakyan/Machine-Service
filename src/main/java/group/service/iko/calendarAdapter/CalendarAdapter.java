package group.service.iko.calendarAdapter;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class CalendarAdapter {

    public static GregorianCalendar getGregCalendar(String dateFromHTML){
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        String year = dateFromHTML.substring(0, 4);
        String month = dateFromHTML.substring(5, 7);
        String day = dateFromHTML.substring( 8, 10);
        return  new GregorianCalendar(Integer.parseInt(year), Integer.parseInt(month)-1, Integer.parseInt(day));
    }
    public static String getStringFormat(GregorianCalendar calendar){
        String dateFormatted;
        if (calendar!=null){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");   // formerly  was "dd.MM.yyyy"
        fmt.setCalendar(calendar);
        dateFormatted = fmt.format(calendar.getTime());}
          else { dateFormatted = "not specified";}

        return dateFormatted;
    }
}
