package com.atharva.pwise.utility;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class Utility {

    public static final String ASIA_ZONE = "Asia/Kolkata";

    public static long getMilliSeconds(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.of(ASIA_ZONE)).toInstant().toEpochMilli();
    }
}
