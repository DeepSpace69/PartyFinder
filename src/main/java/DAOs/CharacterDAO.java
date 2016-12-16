package main.java.DAOs;


import javax.persistence.*;
import java.util.Date;

/**
 * Created by Tatka on 22.11.2016.
 */
@Entity
@Table(name = "characters")
public class CharacterDAO {
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
    private Integer level;
    @Column
    private String serverName;
    @Column
    private Long owner;

    public CharacterDAO() {
    }

    public Long getOwner() {
        return owner;
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

    public Integer getLevel() {
        return level;
    }

    public String getServerName() {
        return serverName;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setOwner(Long owner) {
        this.owner = owner;
    }
}
