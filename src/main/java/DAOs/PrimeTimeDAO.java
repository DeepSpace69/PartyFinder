package main.java.DAOs;

import javax.persistence.*;

/**
 * Created by Tatka on 23.11.2016.
 */
@Entity()
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
    @ManyToOne
    @JoinColumn(name = "fkCharacter")
    private CharacterDAO character;
    @Column(insertable = false, updatable = false)
    private Long fkCharacter;
    @ManyToOne
    @JoinColumn(name = "fkParty")
    private PartyDAO party;
    @Column(insertable = false, updatable = false)
    private Long fkParty;

    public PrimeTimeDAO() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStartHour() {
        return startHour;
    }

    public void setStartHour(Integer startHour) {
        this.startHour = startHour;
    }

    public Integer getStartMinute() {
        return startMinute;
    }

    public void setStartMinute(Integer startMinute) {
        this.startMinute = startMinute;
    }

    public Integer getEndHour() {
        return endHour;
    }

    public void setEndHour(Integer endHour) {
        this.endHour = endHour;
    }

    public Integer getEndMinute() {
        return endMinute;
    }

    public void setEndMinute(Integer endMinute) {
        this.endMinute = endMinute;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Integer getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(Integer timeZone) {
        this.timeZone = timeZone;
    }

    public CharacterDAO getCharacter() {
        return character;
    }

    public void setCharacter(CharacterDAO character) {
        this.character = character;
    }

    public Long getFkCharacter() {
        return fkCharacter;
    }

    public void setFkCharacter(Long fkCharacter) {
        this.fkCharacter = fkCharacter;
    }

    public Long getFkParty() {
        return fkParty;
    }

    public void setFkParty(Long fkParty) {
        this.fkParty = fkParty;
    }
}
