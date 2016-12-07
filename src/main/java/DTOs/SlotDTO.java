package main.java.DTOs;

import main.java.DAOs.SlotDAO;

/**
 * Created by Tatka on 23.11.2016.
 */
public class SlotDTO {

    private String role;
    private String classType;
    private String sex;
    private String linkCharacter;
    private Boolean free;
    private Boolean partyLeader;

    public SlotDTO() {
    }

    public SlotDTO(SlotDAO slot) {
        this.role = slot.getRole();
        this.classType = slot.getClassType();
        this.sex = slot.getSex();
        this.linkCharacter = slot.getLinkCharacter();
        this.free = slot.isExisting();
    }

    public String getLinkCharacter() {
        return linkCharacter;
    }

    public Boolean isFree() {
        return free;
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

    public Boolean getPartyLeader() {
        return partyLeader;
    }

    public void setPartyLeader(Boolean partyLeader) {
        this.partyLeader = partyLeader;
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

    public void setLinkCharacter(String linkCharacter) {
        this.linkCharacter = linkCharacter;
    }

    public void setFree(Boolean isFree) {
        this.free = isFree;
    }
}
