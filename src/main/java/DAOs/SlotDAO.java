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
    private Integer role;
    @Column
    private Integer classType;
    @Column
    private Boolean sex;
    @Column
    private Long fkParty;


    public SlotDAO(ResultSet rs) {

    }

    public SlotDAO(SlotDTO slot) {

    }
}
