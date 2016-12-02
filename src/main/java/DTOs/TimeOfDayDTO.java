package main.java.DTOs;

import java.util.TimeZone;

/**
 * Created by Tatka on 17.11.2016.
 */
public class TimeOfDayDTO {
    private int hour;
    private int minute;

    public TimeOfDayDTO(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }
}
