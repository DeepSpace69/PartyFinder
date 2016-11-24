package main.java.DTOs;

import main.java.DaysEnum;

/**
 * Created by Tatka on 17.11.2016.
 */
public class PrimeTime {
    /**
     * Start time (hours and minutes) of possibility to play
     */
    private TimeOfDay start;

    /**
     * End time (hours and minutes) of possibility to play
     */
    private TimeOfDay end;

    /**
     * Suitable day of week
     */
    private DaysEnum day;

    /**
     * Time zone, for example: Ukraine +2
     */

    private int GMT;

    public PrimeTime(TimeOfDay start, TimeOfDay end, DaysEnum day, int GMT) {
        this.start = start;
        this.end = end;
        this.day = day;
        this.GMT = GMT;
    }
}
