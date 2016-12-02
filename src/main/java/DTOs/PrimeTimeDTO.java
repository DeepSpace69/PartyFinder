package main.java.DTOs;

import main.java.DAOs.PrimeTimeDAO;

/**
 * Created by Tatka on 17.11.2016.
 */
public class PrimeTimeDTO extends PartyDetail{
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

    public PrimeTimeDTO(TimeOfDayDTO start, TimeOfDayDTO end, String  day, int GMT) {
        this.start = start;
        this.end = end;
        this.day = day;
        this.timeZone = GMT;
    }

    public PrimeTimeDTO(PrimeTimeDAO primeTime) {
        this.start= new TimeOfDayDTO(primeTime.getStartHour(),primeTime.getStartMinute());
        this.end = new TimeOfDayDTO(primeTime.getEndHour(), primeTime.getEndMinute());
        this.day = primeTime.getDay();
        this.timeZone= primeTime.getTimeZone();
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
}
