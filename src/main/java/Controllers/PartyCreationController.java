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
import main.java.Factories.PartyDAOFactory;
import main.java.Factories.PrimeTimeDAOFactory;
import main.java.Factories.SlotDAOFactory;
import main.java.Managers.Validator;
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
    private PartyDAOFactory partyDAOFactory;
    private SlotDAOFactory slotDAOFactory;
    private PrimeTimeDAOFactory primeTimeDAOFactory;
    private Validator validator;

    @Autowired
    public PartyCreationController(
            PartyRepository repository,
            SlotRepository slotRepository,
            PrimeTimeRepository primeTimeRepository,
            PartyDAOFactory partyDAOFactory,
            SlotDAOFactory slotDAOFactory,
            PrimeTimeDAOFactory primeTimeDAOFactory,
            Validator validator) {
        this.repository = repository;
        this.slotRepository = slotRepository;
        this.primeTimeRepository = primeTimeRepository;
        this.partyDAOFactory = partyDAOFactory;
        this.slotDAOFactory = slotDAOFactory;
        this.primeTimeDAOFactory = primeTimeDAOFactory;
        this.validator = validator;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String createParty(@RequestBody String input, HttpServletResponse response) {
// todo: DONE MY check Client
        String result = "";
        this.gson = new Gson();
        PartyDTO partyDTO = gson.fromJson(input, PartyDTO.class);
        //ValidationResult validationResult = this.validator.validate(partyDTO);
        //if (validationResult.hasErrors()) {
        //    response.setStatus(400);
        //    return validationResult.getErrorMessage();
        //}

        PartyDAO partyDAO = this.partyDAOFactory.create(partyDTO);
        PartyDAO savedParty = this.repository.save(partyDAO);
        this.saveSlots(partyDTO.getSlots(), savedParty.getId());
        this.savePrimeTimes(partyDTO.getPrimeTimes(), savedParty.getId());
        return result;
    }


    private void savePrimeTimes(List<PrimeTimeDTO> primeTimes, Long partyId) {
        List<PrimeTimeDAO> primeTimeDAOs = new ArrayList<>();
        for (PrimeTimeDTO primeTime : primeTimes) {
            primeTimeDAOs.add(this.primeTimeDAOFactory.createForPaty(primeTime, partyId));
        }
        this.primeTimeRepository.save(primeTimeDAOs);
    }

    private void saveSlots(List<SlotDTO> slots, Long partyId) {
        List<SlotDAO> slotDAOs = new ArrayList<>();
        for (SlotDTO slot : slots) {
            slotDAOs.add(this.slotDAOFactory.create(slot, partyId));
        }
        this.slotRepository.save(slotDAOs);
    }
}
