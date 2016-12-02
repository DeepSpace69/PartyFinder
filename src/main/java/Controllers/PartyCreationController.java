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
import main.java.Core.VocabularyLoader;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private VocabularyLoader vocabularyLoader;

    @Autowired
    public PartyCreationController(PartyRepository repository, SlotRepository slotRepository, PrimeTimeRepository primeTimeRepository, VocabularyLoader loader) {
        this.repository = repository;
        this.slotRepository = slotRepository;
        this.primeTimeRepository = primeTimeRepository;
        this.vocabularyLoader = loader;
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

        PartyDAO partyDAO = new PartyDAO(partyDTO);
        PartyDAO savedParty = this.repository.save(partyDAO);
        this.saveSlots(partyDTO.getSlots(), savedParty.getId());
        this.savePrimeTimes(partyDTO.getPrimeTimes(), savedParty.getId());
        return result;
    }


    private void savePrimeTimes(ArrayList<PrimeTimeDTO> primeTimes, Long id) {
        List<PrimeTimeDAO> primeTimeDAOs = new ArrayList<>();
        for (PrimeTimeDTO primeTime : primeTimes) {
            primeTimeDAOs.add(new PrimeTimeDAO(primeTime, id));
        }
        this.primeTimeRepository.save(primeTimeDAOs);
    }

    private void saveSlots(ArrayList<SlotDTO> slots, Long id) {
        List<SlotDAO> slotDAOs = new ArrayList<>();
        for (SlotDTO slot : slots) {
            slotDAOs.add(new SlotDAO(slot, id));
        }
        this.slotRepository.save(slotDAOs);
    }


    private ValidationResult validate(PartyDTO party) {

        Set<String> setMessages = new HashSet<>();
        checkValid(VocabularyTypes.lang, party.getLanguage(), setMessages);
        checkValid(VocabularyTypes.serversGroup, party.getServersGroup(), setMessages);
        checkValid(VocabularyTypes.serverName, party.getServerName(), setMessages);
        checkValid(VocabularyTypes.chatType, party.getChatType(), setMessages);
        if (party.getAge() <= 0 || party.getAge() > 100) {
            setMessages.add("Invalid age");
        }
        if (alreadyExist(party.getName())) {
            setMessages.add("Name already exists");

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
            result.setErrors(true, "Invalid " + message);
        }
        return result;
    }

    private void checkValid(String type, String candidate, Set<String> setMessages) {
        // todo: make vocabl as hashset

        if (!this.vocabularyLoader.getVocabulary(type).contains(candidate)) {
            setMessages.add(type);
            // todo string.format correct error message
        }

    }

    private boolean alreadyExist(String name) {
        // todo implement
        return false;
    }


}
