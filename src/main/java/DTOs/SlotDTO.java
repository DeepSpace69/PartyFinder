package main.java.DTOs;

/**
 * Created by Tatka on 23.11.2016.
 */
public class SlotDTO {

    private String role;
    private String classType;
    private String sex;
    private Integer linkCharacter;
    private Boolean isFree;
    private Boolean isPartyLeader;

    public SlotDTO() {
    }

    public Integer getLinkCharacter() {
        return linkCharacter;
    }

    public Boolean isFree() {
        return isFree;
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

    public Boolean getIsPartyLeader() {
        return isPartyLeader;
    }

    public void setIsPartyLeader(Boolean isPartyLeader) {
        this.isPartyLeader = isPartyLeader;
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

    public void setLinkCharacter(Integer linkCharacter) {
        this.linkCharacter = linkCharacter;
    }

    public void setIsFree(Boolean isFree) {
        this.isFree = isFree;
    }
}
