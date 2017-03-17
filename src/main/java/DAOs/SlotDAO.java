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
    private String role;
    @Column
    private String classType;
    @Column
    private String sex;
    @Column
    private Long fkParty;
    @Column
    private Integer linkCharacter;
    @Column
    private Boolean existing;
    @Column
    private Boolean partyLeader;


    public SlotDAO() {

    }

    public Integer getLinkCharacter() {
        return linkCharacter;
    }

    public Boolean isExisting() {
        return existing;
    }

    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setFkParty(Long fkParty) {
        this.fkParty = fkParty;
    }

    public void setLinkCharacter(Integer linkCharacter) {
        this.linkCharacter = linkCharacter;
    }

    public void setExisting(Boolean existing) {
        this.existing = existing;
    }

    public Boolean getExisting() {
        return existing;
    }

    public Boolean getPartyLeader() {
        return partyLeader;
    }

    public void setPartyLeader(Boolean partyLeader) {
        this.partyLeader = partyLeader;
    }
}
