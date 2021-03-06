package main.java.DTOs;

import main.java.DAOs.CharacterDAO;

import java.util.List;

public class CharacterDTO {
    private Long id;
    private String name;
    private String role;
    private String classType;
    private Integer level;
    private String serverName;
    private Long user;
    private List<PrimeTimeDTO> primeTimes;

    public CharacterDTO(CharacterDAO characterDAO) {
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

    public String getRole() {
        return role;
    }

    public String getClassType() {
        return classType;
    }

    public Integer getLevel() {
        return level;
    }

    public String getServerName() {
        return serverName;
    }

    public List<PrimeTimeDTO> getPrimeTimes() {
        return primeTimes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
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
}
