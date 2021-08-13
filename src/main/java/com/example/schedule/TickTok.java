package com.example.schedule;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TickTok {
    private static final DateFormat df = new SimpleDateFormat("mm:ss");

//    @Scheduled(initialDelay = 1000, fixedDelay = 1000)
    public void tick() {
        Date now = new Date();
        String nowString = df.format(now);
        Logger.getLogger(TickTok.class).info(nowString);
    }
}
