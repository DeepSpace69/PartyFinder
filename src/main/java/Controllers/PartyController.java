package main.java.Controllers;

import main.java.DAOs.PartyDAO;
import main.java.DAOs.PrimeTimeDAO;
import main.java.DAOs.SlotDAO;
import main.java.DTOs.PartyDTO;
import main.java.DTOs.PrimeTimeDTO;
import main.java.Repositories.PartyRepository;
import main.java.Repositories.PrimeTimeRepository;
import main.java.Repositories.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tatka on 24.11.2016.
 */
@Controller
@RequestMapping("/findParties")
public class PartyController {
    private PartyRepository repository;
    private SlotRepository slotRepository;
    private PrimeTimeRepository primeTimeRepository;
    private Gson gson;

    @Autowired
    public PartyController(PartyRepository repository, SlotRepository slotRepository, PrimeTimeRepository primeTimeRepository) {
        this.repository = repository;
        this.slotRepository = slotRepository;
        this.primeTimeRepository = primeTimeRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String findParty() {

        List<PartyDAO> records = repository.findAll();

        List<Long> ids = new ArrayList<>();
        for (PartyDAO party : records) {
            ids.add(party.getId());
        }

        List<SlotDAO> slots = this.slotRepository.getSlotsByPartyIds(ids);
        List<PrimeTimeDAO> primeTimes = this.primeTimeRepository.getPrimeTimesByPartyIds(ids);

        List<PartyDTO> outputParties = new ArrayList<>();
        for (PartyDAO party : records) {
            outputParties.add(new PartyDTO(party, slots, primeTimes));
        }
        // todo: тест для конструктора патидто


        this.gson = new Gson();
        return this.gson.toJson(outputParties);
    }

}
