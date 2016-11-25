package main.java.DAOs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Tatka on 17.11.2016.
 */
@Entity
@Table(name = "users")
public class UserDAO {
    @Id
    private Long id;
    @Column
    private String name;
    @Column
    private String password;
    @Column
    private String login;
    @Column
    private String email;


    public UserDAO(ResultSet resultSet) throws SQLException {
        this.login = resultSet.getString("login");
        this.password = resultSet.getString("password");
        this.name = resultSet.getString("name");
    }

    public UserDAO() {

    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
