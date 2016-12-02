package main.java.DTOs;

import main.java.DAOs.SlotDAO;

/**
 * Created by Tatka on 23.11.2016.
 */
public class SlotDTO extends PartyDetail{

    private String role;
    private String classType;
    private String sex;
    private String linkCharacter;
    private String existing;

    public SlotDTO(SlotDAO slot) {
        this.role = slot.getRole();
        this.classType = slot.getClassType();
        this.sex = slot.getSex();
        this.linkCharacter = slot.getLinkCharacter();
        this.existing = slot.isExisting();
    }

    public String getLinkCharacter() {
        return linkCharacter;
    }

    public String isExisting() {
        return existing;
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
}
