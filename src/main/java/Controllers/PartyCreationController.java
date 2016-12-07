package main.java.Controllers;

import com.google.gson.Gson;
import main.java.Const.VocabularyTypes;
import main.java.Core.ValidationResult;
import main.java.DAOs.PartyDAO;
import main.java.DAOs.PrimeTimeDAO;
import main.java.DAOs.SlotDAO;
import main.java.DTOs.PartyDTO;
import main.java.DTOs.PrimeTimeDTO;
import main.java.DTOs.SlotDTO;
import main.java.Managers.PartyDAOFactory;
import main.java.Managers.PrimeTimeDAOFactory;
import main.java.Managers.SlotDAOFactory;
import main.java.Managers.VocabularyManager;
import main.java.Repositories.PartyRepository;
import main.java.Repositories.PrimeTimeRepository;
import main.java.Repositories.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by Tatka on 24.11.2016.
 */
@Controller
@RequestMapping("/createParty")
public class PartyCreationController {
    private PartyRepository repository;
    private SlotRepository slotRepository;
    private PrimeTimeRepository primeTimeRepository;
    private Gson gson;
    private VocabularyManager vocabularyManager;
    private PartyDAOFactory partyDAOFactory;
    private SlotDAOFactory slotDAOFactory;
    private PrimeTimeDAOFactory primeTimeDAOFactory;

    @Autowired
    public PartyCreationController(
            PartyRepository repository,
            SlotRepository slotRepository,
            PrimeTimeRepository primeTimeRepository,
            VocabularyManager vocabularyManager,
            PartyDAOFactory partyDAOFactory,
            SlotDAOFactory slotDAOFactory,
            PrimeTimeDAOFactory primeTimeDAOFactory) {
        this.repository = repository;
        this.slotRepository = slotRepository;
        this.primeTimeRepository = primeTimeRepository;
        this.vocabularyManager = vocabularyManager;
        this.partyDAOFactory = partyDAOFactory;
        this.slotDAOFactory = slotDAOFactory;
        this.primeTimeDAOFactory = primeTimeDAOFactory;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String createParty(@RequestBody String input, HttpServletResponse response) {

        String result = "";
        this.gson = new Gson();
        PartyDTO partyDTO = gson.fromJson(input, PartyDTO.class);
        ValidationResult validationParty = this.validate(partyDTO);
        if (validationParty.hasErrors()) {
            response.setStatus(400);
            return validationParty.getErrorMessage();
        }

        PartyDAO partyDAO = this.partyDAOFactory.create(partyDTO);
        PartyDAO savedParty = this.repository.save(partyDAO);
        this.saveSlots(partyDTO.getSlots(), savedParty.getId());
        this.savePrimeTimes(partyDTO.getPrimeTimes(), savedParty.getId());
        return result;
    }


    private void savePrimeTimes(ArrayList<PrimeTimeDTO> primeTimes, Long id) {
        List<PrimeTimeDAO> primeTimeDAOs = new ArrayList<>();
        for (PrimeTimeDTO primeTime : primeTimes) {
            primeTimeDAOs.add(this.primeTimeDAOFactory.create(primeTime, id));
        }
        this.primeTimeRepository.save(primeTimeDAOs);
    }

    private void saveSlots(ArrayList<SlotDTO> slots, Long id) {
        List<SlotDAO> slotDAOs = new ArrayList<>();
        for (SlotDTO slot : slots) {
            slotDAOs.add(this.slotDAOFactory.create(slot, id));
        }
        this.slotRepository.save(slotDAOs);
    }


    private ValidationResult validate(PartyDTO party) {

        Set<String> setMessages = new HashSet<>();
        checkValid(VocabularyTypes.lang, party.getLanguage(), setMessages);
        checkValid(VocabularyTypes.serversGroup, party.getServersGroup(), setMessages);
        checkValid(VocabularyTypes.serverName, party.getServerName(), setMessages);
        checkValid(VocabularyTypes.chatType, party.getChatType(), setMessages);
        Integer age = party.getAge();
        if (age <= 0 || age > 100) {
            setMessages.add(String.format("Invalid age: %1s", age));
        }
        if (alreadyExist(party.getName())) {
            setMessages.add(String.format("Name already exists: %1s", party.getName()));

        }
        for (SlotDTO slot : party.getSlots()) {
            checkValid(VocabularyTypes.role, slot.getRole(), setMessages);
            checkValid(VocabularyTypes.classType, slot.getClassType(), setMessages);
            checkValid(VocabularyTypes.sex, slot.getSex(), setMessages);
        }
        for (PrimeTimeDTO primeTime : party.getPrimeTimes()) {
            checkValid(VocabularyTypes.day, primeTime.getDay(), setMessages);
        }

        return createValidationResult(setMessages);
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
