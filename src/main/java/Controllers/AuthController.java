package main.java.Controllers;

import com.google.gson.Gson;
import main.java.DAOs.UserDAO;
import main.java.DTOs.UserDTO;
import main.java.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Tatka on 24.11.2016.
 */
@Controller
@RequestMapping("/auth")
public class AuthController {
    private UserRepository repository;
    private Gson gson;

    @Autowired
    public AuthController(UserRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String auth(@RequestBody String input, HttpServletResponse response) {

        UserDTO result = null;
        this.gson = new Gson();
        UserDTO userDTO = gson.fromJson(input, UserDTO.class);
        if (userDTO == null){
            response.setStatus(400);
            return null;
        }
        UserDAO userDAO = repository.auth(userDTO.getLogin(), userDTO.getPassword());

        if (userDAO == null) {
            response.setStatus(401);
        } else {
            result = new UserDTO(userDAO);
        }
        //System.out.print(result);
        return this.gson.toJson(result);
    }

}
