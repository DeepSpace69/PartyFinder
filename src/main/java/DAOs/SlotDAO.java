package main.java.DAOs;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "fkParty")
    private PartyDAO party;

    @Column(insertable = false, updatable = false)
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

    public void setLinkCharacter(Integer linkCharacter) {
        this.linkCharacter = linkCharacter;
    }

    public Boolean isExisting() {
        return existing;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Long getFkParty() {
        return fkParty;
    }

    public void setFkParty(Long fkParty) {
        this.fkParty = fkParty;
    }

    public Boolean getExisting() {
        return existing;
    }

    public void setExisting(Boolean existing) {
        this.existing = existing;
    }

    public Boolean getPartyLeader() {
        return partyLeader;
    }

    public void setPartyLeader(Boolean partyLeader) {
        this.partyLeader = partyLeader;
    }
}
