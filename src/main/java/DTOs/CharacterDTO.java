package main.java.DTOs;

import main.java.DAOs.CharacterDAO;
import main.java.DAOs.PrimeTimeDAO;

import java.util.ArrayList;
import java.util.List;

public class CharacterDTO {
    private Long id;
    private String name;
    private String sex;
    private String role;
    private String classType;
    private Integer level;
    private String serverName;
    private Long user;
    private List<PrimeTimeDTO> primeTimes;

    public CharacterDTO(CharacterDAO characterDAO) {
        this.id = characterDAO.getId();
        this.name = characterDAO.getName();
        this.sex = characterDAO.getSex();
        this.role = characterDAO.getRole();
        this.classType = characterDAO.getClassType();
        this.level = characterDAO.getLevel();
        this.serverName = characterDAO.getServerName();
        this.user = characterDAO.getUser();
        this.primeTimes = new ArrayList<>();
        for (PrimeTimeDAO element : characterDAO.getPrimeTimeDAOs()) {
            this.primeTimes.add(new PrimeTimeDTO(element));
        }
    }

    public CharacterDTO() {

    }


    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public List<PrimeTimeDTO> getPrimeTimes() {
        return primeTimes;
    }

    public void setPrimeTimes(List<PrimeTimeDTO> primeTimes) {
        this.primeTimes = primeTimes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
