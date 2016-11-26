package main.java.DAOs;

import main.java.DTOs.SlotDTO;

import javax.persistence.*;
import java.sql.ResultSet;

/**
 * Created by Tatka on 23.11.2016.
 */
@Entity
@Table(name = "slots")
public class SlotDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String role;
    @Column
    private String classType;
    @Column
    private String sex;
    @Column
    private Long fkParty;


    public SlotDAO(ResultSet rs) {

    }

    public SlotDAO(SlotDTO slot, Long id) {
        this.name = slot.getName();
        this.role = slot.getRole();
        this.classType = slot.getClassType();
        this.sex = slot.getSex();
        this.fkParty = id;


    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getClassType() {
        return classType;
    }

    public String getSex() {
        return sex;
    }

    public Long getFkParty() {
        return fkParty;
    }
}
