package com.websocket.chyatt.Services;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TimeConverter {
    public LocalDateTime stringToDate(String time){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss");
        LocalDateTime datetime = LocalDateTime.parse(time,format);
        return datetime;
    }

    public String dateToString(LocalDateTime datetime){
        DateTimeFormatter format= DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss");
        return datetime.format(format);

    }
}
