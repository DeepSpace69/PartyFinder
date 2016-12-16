package main.java.Controllers;

import com.google.gson.Gson;
import main.java.Core.ValidationResult;
import main.java.DAOs.CharacterDAO;
import main.java.DAOs.PrimeTimeDAO;
import main.java.DTOs.CharacterDTO;
import main.java.DTOs.PrimeTimeDTO;
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
    private VocabularyManager vocabularyManager;
    private CharacterDAOFactory characterDAOFactory;
    private PrimeTimeDAOFactory primeTimeDAOFactory;

    @Autowired
    public CharacterCreationController(
            CharacterRepository repository,
            PrimeTimeRepository primeTimeRepository,
            VocabularyManager vocabularyManager,
            CharacterDAOFactory characterDAOFactory,
            PrimeTimeDAOFactory primeTimeDAOFactory) {
        this.repository = repository;
        this.primeTimeRepository = primeTimeRepository;
        this.primeTimeRepository = primeTimeRepository;
        this.vocabularyManager = vocabularyManager;
        this.characterDAOFactory = characterDAOFactory;
        this.primeTimeDAOFactory = primeTimeDAOFactory;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String createParty(@RequestBody String input, HttpServletResponse response) {

        String result = "";
        this.gson = new Gson();
        CharacterDTO characterDTO = gson.fromJson(input, CharacterDTO.class);
        ValidationResult validationParty = this.validate(characterDTO);
        if (validationParty.hasErrors()) {
            response.setStatus(400);
            return validationParty.getErrorMessage();
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

    private ValidationResult validate(CharacterDTO party) {
        ValidationResult result = new ValidationResult();
        result.setErrors(false, "");
        return result;

//        Set<String> setMessages = new HashSet<>();
//        checkValid(VocabularyTypes.lang, party.getLanguage(), setMessages);
//        checkValid(VocabularyTypes.serversGroup, party.getServersGroup(), setMessages);
//        checkValid(VocabularyTypes.serverName, party.getServerName(), setMessages);
//        checkValid(VocabularyTypes.chatType, party.getChatType(), setMessages);
//        Integer age = party.getAge();
//        if (age <= 0 || age > 100) {
//            setMessages.add(String.format("Invalid age: %1s", age));
//        }
//        if (alreadyExist(party.getName())) {
//            setMessages.add(String.format("Name already exists: %1s", party.getName()));
//
//        }
//        for (SlotDTO slot : party.getSlots()) {
//            checkValid(VocabularyTypes.role, slot.getRole(), setMessages);
//            checkValid(VocabularyTypes.classType, slot.getClassType(), setMessages);
//            checkValid(VocabularyTypes.sex, slot.getSex(), setMessages);
//        }
//        for (PrimeTimeDTO primeTime : party.getPrimeTimes()) {
//            checkValid(VocabularyTypes.day, primeTime.getDay(), setMessages);
//        }
//
//        return this.createValidationResult(setMessages);
    }

    private ValidationResult createValidationResult(Set<String> setMessages) {
        ValidationResult result = new ValidationResult();
        if (!setMessages.isEmpty()) {
            String message = String.join(", ", setMessages);
            result.setErrors(true, message);
        }
        return result;
    }

    private void checkValid(String type, String candidate, Set<String> setMessages) {
        if (!this.vocabularyManager.getVocabulary(type).contains(candidate)) {
            setMessages.add(String.format("Invalid value for vocabulary - %1s: %2s", type, candidate));
        }
    }

    private boolean alreadyExist(String name) {
        // todo NOT DEMO implement
        return false;
    }


}
