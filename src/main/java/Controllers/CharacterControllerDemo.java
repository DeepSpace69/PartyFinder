package main.java.Controllers;

import com.google.gson.Gson;
import main.java.DAOs.CharacterDAO;
import main.java.DAOs.PrimeTimeDAO;
import main.java.DTOs.CharacterDTO;
import main.java.Factories.CharacterDTOFactory;
import main.java.Repositories.CharacterRepository;
import main.java.Repositories.PrimeTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tatka on 24.11.2016.
 */
@Controller
@RequestMapping("/getCharacters2")
public class CharacterControllerDemo {
    private Gson gson;
    private CharacterRepository repository;
    private CharacterDTOFactory characterDTOFactory;

    @Autowired
    public CharacterControllerDemo(CharacterRepository repository, CharacterDTOFactory characterDTOFactory) {
        this.repository = repository;
        this.characterDTOFactory = characterDTOFactory;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String getCharacters() {
        this.gson = new Gson();
        List<CharacterDTO> result = new ArrayList<>();

        List<CharacterDAO> characterDAOs = repository.findAll();
        // create DTOs
        for (CharacterDAO characterDAO : characterDAOs) {
            result.add(this.characterDTOFactory.create(characterDAO));
        }
        return this.gson.toJson(result);
    }

}
