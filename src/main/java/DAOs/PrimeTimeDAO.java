package main.java.DAOs;

import main.java.DTOs.PrimeTimeDTO;

import javax.persistence.*;

/**
 * Created by Tatka on 23.11.2016.
 */
@Entity
@Table(name = "prime_times")
public class PrimeTimeDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Integer startHour;
    @Column
    private Integer startMinute;
    @Column
    private Integer endHour;
    @Column
    private Integer endMinute;
    @Column
    private String day;
    @Column
    private Integer timeZone;
    @Column
    private Long fkParty;
    @Column
    private Long fkCharacter;

    public PrimeTimeDAO() {
    }

    public Long getId() {
        return id;
    }

    public Integer getStartHour() {
        return startHour;
    }

    public Integer getStartMinute() {
        return startMinute;
    }

    public Integer getEndHour() {
        return endHour;
    }

    public Integer getEndMinute() {
        return endMinute;
    }

    public String getDay() {
        return day;
    }

    public Integer getTimeZone() {
        return timeZone;
    }

    public Long getFkParty() {
        return fkParty;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStartHour(Integer startHour) {
        this.startHour = startHour;
    }

    public void setStartMinute(Integer startMinute) {
        this.startMinute = startMinute;
    }

    public void setEndHour(Integer endHour) {
        this.endHour = endHour;
    }

    public void setEndMinute(Integer endMinute) {
        this.endMinute = endMinute;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setTimeZone(Integer timeZone) {
        this.timeZone = timeZone;
    }

    public void setFkParty(Long fkParty) {
        this.fkParty = fkParty;
    }

    public Long getFkCharacter() {
        return fkCharacter;
    }

    public void setFkCharacter(Long fkCharacter) {
        this.fkCharacter = fkCharacter;
    }
}
