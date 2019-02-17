package group.service.iko;

import group.service.iko.calendarAdapter.CalendarAdapter;
import group.service.iko.entities.Machine;
import group.service.iko.entities.WorkOrder;
import group.service.iko.service.ExcelReaderWriter;

import java.io.File;
import java.io.IOException;
import java.util.GregorianCalendar;

public class Test {
    public static void main(String[] args) throws IOException {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        String date = CalendarAdapter.getStringFormat(gregorianCalendar);
        System.out.println(date);
    }
}
