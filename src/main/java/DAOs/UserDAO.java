package main.java.DAOs;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Tatka on 17.11.2016.
 */
public class UserDAO {
    private String name;
    private String password;
    private String login;

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
