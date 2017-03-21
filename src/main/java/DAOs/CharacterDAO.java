package main.java.DAOs;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by Tatka on 22.11.2016.
 */
@Entity()
@Table(name = "characters")
public class CharacterDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String sex;
    @Column
    private String role;
    @Column
    private String classType;
    @Column
    private Integer level;
    @Column
    private String serverName;
    @Column
    private Long fkUser;
    @OneToMany(mappedBy="character")
    private List<PrimeTimeDAO> primeTimeDAOs;

    public CharacterDAO() {
    }

    public Long getFkUser() {
        return fkUser;
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

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public List<PrimeTimeDAO> getPrimeTimeDAOs() {
        return primeTimeDAOs;
    }

    public String getSex() {
        return sex;
    }

    public void setFkUser(Long fkUser) {
        this.fkUser = fkUser;
    public void setSex(String sex) {
        this.sex = sex;
    }
}
