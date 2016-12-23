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
@RequestMapping("/getCharacters")
public class CharacterControllerDemo {
    private Gson gson;
    private CharacterRepository repository;
    private PrimeTimeRepository primeTimeRepository;
    private CharacterDTOFactory characterDTOFactory;

    @Autowired
    public CharacterControllerDemo(CharacterRepository repository, CharacterDTOFactory characterDTOFactory, PrimeTimeRepository primeTimeRepository) {
        this.repository = repository;
        this.characterDTOFactory = characterDTOFactory;
        this.primeTimeRepository = primeTimeRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String getCharacters() {
        this.gson = new Gson();
        List<CharacterDTO> result = new ArrayList<>();

        List<CharacterDAO> characterDAOs = repository.findAll();
        // find characters' ids
        List<Long> ids = new ArrayList<>();
        for (CharacterDAO characterDAO : characterDAOs) {
            ids.add(characterDAO.getId());
        }
        //find primeTimes for all characters
        List<PrimeTimeDAO> primeTimeDAOs = primeTimeRepository.getPrimeTimesByCharactersIds(ids);
        // create DTOs
        for (CharacterDAO characterDAO : characterDAOs) {
            result.add(this.characterDTOFactory.create(characterDAO, primeTimeDAOs));
        }
        return this.gson.toJson(result);
    }

}
