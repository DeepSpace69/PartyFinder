package main.java.DTOs;

import main.java.DAOs.UserDAO;

/**
 * Created by Tatka on 18.11.2016.
 */
public class UserDTO {

    private String name;
    private String password;
    private String login;


    public UserDTO() {
    }

    public UserDTO(UserDAO userDAO) {
        this.name = userDAO.getName();
        this.login = userDAO.getLogin();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

}
