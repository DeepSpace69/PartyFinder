package main.java;

import main.java.DTOs.PrimeTime;

import java.util.ArrayList;

/**
 * Created by Tatka on 17.11.2016.
 */
public class Party {


    private String name;

    /**
     * List days and times when party can play
     */
    private ArrayList<PrimeTime> primeTimes = new ArrayList<>();

    /**
     * Minimum member age
     */
    private int memberAge;


    public Party(String name, ArrayList<PrimeTime> primeTimes, int memberAge) {
        this.name = name;
        this.primeTimes = primeTimes;
        this.memberAge = memberAge;
    }


}
