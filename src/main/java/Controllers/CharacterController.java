//package main.java.Controllers;
//
//import com.google.gson.Gson;
//import main.java.DAOs.CharacterDAO;
//import main.java.DAOs.UserDAO;
//import main.java.DTOs.CharacterDTO;
//import main.java.Managers.CharacterDTOFactory;
//import main.java.Repositories.CharacterRepository;
//import main.java.Repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletResponse;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by Tatka on 24.11.2016.
// */
//@Controller
//@RequestMapping("/character")
//public class CharacterController {
//    private CharacterRepository repository;
//    private Gson gson;
//    private CharacterDTOFactory characterDTOFactory;
//
//    @Autowired
//    public CharacterController(CharacterRepository repository, CharacterDTOFactory characterDTOFactory) {
//        this.repository = repository;
//        this.characterDTOFactory = characterDTOFactory;
//    }
//
//    @RequestMapping(method = RequestMethod.POST)
//    @ResponseBody
//    public String auth(@RequestBody String input, HttpServletResponse response) {
//
//        CharacterDTO result = null;
//        this.gson = new Gson();
//        CharacterDTO characterDTO = gson.fromJson(input, CharacterDTO.class);
//        if (characterDTO == null) {
//            response.setStatus(400);
//            return null;
//        }
//        List<CharacterDAO> characterDAOs = repository.getCharacters(characterDTO.getOwner().toString());
//        List<CharacterDTO> characterDTOs = new ArrayList<>();
//        for (CharacterDAO characterDAO : characterDAOs) {
//            //characterDTOs.add(this.characterDTOFactory.create(characterDAO,));
//        }
//        return this.gson.toJson(characterDTOs);
//    }
//
//}
