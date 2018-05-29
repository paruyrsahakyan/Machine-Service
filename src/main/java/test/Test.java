package test;


import group.service.iko.calendarAdapter.CalendarAdapter;
import group.service.iko.entities.DetailedLaborHour;
import group.service.iko.entities.HistoryRecord;
import group.service.iko.service.DetailedLaborHourService;
import group.service.iko.service.HistoryRecordService;
import group.service.iko.service.StorageService;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {


      StorageService storageService = new StorageService();
      MultipartFile multipartFile= new MultipartFile() {
        public String getName() {
          return null;
        }

        public String getOriginalFilename() {
          return null;
        }

        public String getContentType() {
          return null;
        }

        public boolean isEmpty() {
          return false;
        }

        public long getSize() {
          return 0;
        }

        public byte[] getBytes() throws IOException {
          return new byte[0];
        }

        public InputStream getInputStream() throws IOException {
          return null;
        }

        public void transferTo(File file) throws IOException, IllegalStateException {

        }
      };
      storageService.storeFile(multipartFile, 57);


        }
          }


