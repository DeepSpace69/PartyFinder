package main.java.DTOs;

import main.java.DAOs.PrimeTimeDAO;

/**
 * Created by Tatka on 17.11.2016.
 */
public class PrimeTimeDTO {
    /**
     * Start time (hours and minutes) of possibility to play
     */
    private TimeOfDayDTO start;

    /**
     * End time (hours and minutes) of possibility to play
     */
    private TimeOfDayDTO end;

    /**
     * Suitable day of week
     */
    private String day;

    /**
     * Time zone, for example: Ukraine +2
     */

    private int timeZone;

    public PrimeTimeDTO() {
    }

    public PrimeTimeDTO(TimeOfDayDTO start, TimeOfDayDTO end, String  day, int GMT) {
        this.start = start;
        this.end = end;
        this.day = day;
        this.timeZone = GMT;
    }

    public TimeOfDayDTO getStart() {
        return start;
    }

    public TimeOfDayDTO getEnd() {
        return end;
    }

    public String getDay() {
        return day;
    }

    public int getTimeZone() {
        return timeZone;
    }

    public void setStart(TimeOfDayDTO start) {
        this.start = start;
    }

    public void setEnd(TimeOfDayDTO end) {
        this.end = end;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setTimeZone(int timeZone) {
        this.timeZone = timeZone;
    }
}
