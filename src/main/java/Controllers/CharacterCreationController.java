package main.java.Controllers;

import com.google.gson.Gson;
import main.java.Const.VocabularyTypes;
import main.java.Core.ValidationResult;
import main.java.DAOs.CharacterDAO;
import main.java.DAOs.PrimeTimeDAO;
import main.java.DTOs.CharacterDTO;
import main.java.DTOs.PrimeTimeDTO;
import main.java.Factories.CharacterDAOFactory;
import main.java.Factories.PrimeTimeDAOFactory;
import main.java.Managers.*;
import main.java.Repositories.CharacterRepository;
import main.java.Repositories.PrimeTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Tatka on 24.11.2016.
 */
@Controller
@RequestMapping("/createCharacter")
public class CharacterCreationController {
    private CharacterRepository repository;
    private PrimeTimeRepository primeTimeRepository;
    private Gson gson;
    private CharacterDAOFactory characterDAOFactory;
    private PrimeTimeDAOFactory primeTimeDAOFactory;
    private Validator validator;

    @Autowired
    public CharacterCreationController(
            CharacterRepository repository,
            PrimeTimeRepository primeTimeRepository,
            CharacterDAOFactory characterDAOFactory,
            PrimeTimeDAOFactory primeTimeDAOFactory,
            Validator validator) {
        this.repository = repository;
        this.primeTimeRepository = primeTimeRepository;
        this.primeTimeRepository = primeTimeRepository;
        this.characterDAOFactory = characterDAOFactory;
        this.primeTimeDAOFactory = primeTimeDAOFactory;
        this.validator = validator;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String createParty(@RequestBody String input, HttpServletResponse response) {

        String result = "";
        this.gson = new Gson();
        CharacterDTO characterDTO = gson.fromJson(input, CharacterDTO.class);
        ValidationResult validationResult = this.validator.validate(characterDTO);
        if (validationResult.hasErrors()) {
            response.setStatus(400);
            return validationResult.getErrorMessage();
        }

        CharacterDAO characterDAO = this.characterDAOFactory.create(characterDTO);
        CharacterDAO savedCharacter = this.repository.save(characterDAO);
        this.savePrimeTimes(characterDTO.getPrimeTimes(), savedCharacter.getId());
        return result;
    }


    private void savePrimeTimes(List<PrimeTimeDTO> primeTimes, Long characterId) {
        List<PrimeTimeDAO> primeTimeDAOs = new ArrayList<>();
        for (PrimeTimeDTO primeTime : primeTimes) {
            primeTimeDAOs.add(this.primeTimeDAOFactory.createForCharacter(primeTime, characterId));
        }
        this.primeTimeRepository.save(primeTimeDAOs);
    }
}
