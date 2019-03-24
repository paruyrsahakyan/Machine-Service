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
//        SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy");
//        fmt.setCalendar(calendar);
//        String dateFormatted = fmt.format(calendar.getTime());
//        return dateFormatted;

    return  "111.111..111";

    }
}
