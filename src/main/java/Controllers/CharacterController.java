package main.java.Controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import main.java.DAOs.CharacterDAO;
import main.java.DTOs.CharacterDTO;
import main.java.DTOs.FilterDTO;
import main.java.Factories.CharacterDTOFactory;
import main.java.Managers.FinderByFilters;
import main.java.Repositories.CharacterRepository;
import main.java.Repositories.PrimeTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tatka on 24.11.2016.
 */
@Controller
@RequestMapping("/findCharacters")
public class CharacterController {
    private Gson gson;
    private CharacterRepository repository;
    private PrimeTimeRepository primeTimeRepository;
    private CharacterDTOFactory characterDTOFactory;
    private FinderByFilters<CharacterDAO> finderByFilters;

    @Autowired
    public CharacterController(
            CharacterRepository repository,
            CharacterDTOFactory characterDTOFactory,
            PrimeTimeRepository primeTimeRepository,
            FinderByFilters<CharacterDAO> finderByFilters) {
        this.repository = repository;
        this.characterDTOFactory = characterDTOFactory;
        this.primeTimeRepository = primeTimeRepository;
        this.finderByFilters = finderByFilters;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String getCharacters(@RequestBody String input) {

        this.gson = new Gson();
        Type jsonType = new TypeToken<List<FilterDTO>>() {
        }.getType();
        List<FilterDTO> filters = gson.fromJson(input, jsonType);
        if (filters.isEmpty()) {
            List<CharacterDAO> characters = repository.findAll();
            List<CharacterDTO> result = this.makeDTOs(characters);
            return this.gson.toJson(result);
        }

        List<CharacterDAO> characterDAOs = this.finderByFilters.getByFilters(filters, CharacterDAO.class);
        List<CharacterDTO> characterDTOs = this.makeDTOs(characterDAOs);
        String result = this.gson.toJson(characterDTOs);
        return result;
//
//        // find characters' ids
//        List<Long> ids = new ArrayList<>();
//        for (CharacterDAO characterDAO : characterDAOs) {
//            ids.add(characterDAO.getId());
//        }
//        //find primeTimes for all characters
//        List<PrimeTimeDAO> primeTimeDAOs = primeTimeRepository.getPrimeTimesByCharactersIds(ids);
//        // create DTOs
//        for (CharacterDAO characterDAO : characterDAOs) {
//            result.add(this.characterDTOFactory.create(characterDAO, primeTimeDAOs));
//        }
//        return this.gson.toJson(result);
    }

    private List<CharacterDTO> makeDTOs(List<CharacterDAO> characters) {
        List<CharacterDTO> result = new ArrayList<>();
        for (CharacterDAO characterDAO : characters) {
            result.add(this.characterDTOFactory.create(characterDAO,null));
        }
        return result;
    }

}
