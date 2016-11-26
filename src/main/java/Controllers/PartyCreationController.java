package main.java.Controllers;

import com.google.gson.Gson;
import main.java.DAOs.PartyDAO;
import main.java.DAOs.SlotDAO;
import main.java.DTOs.PartyDTO;
import main.java.DTOs.SlotDTO;
import main.java.Repositories.PartyRepository;
import main.java.Repositories.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tatka on 24.11.2016.
 */
@Controller
@RequestMapping("/createParty")
public class PartyCreationController {
    private PartyRepository repository;
    private SlotRepository slotRepository;
    private Gson gson;

    @Autowired
    public PartyCreationController(PartyRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String createParty(@RequestBody String input, HttpServletResponse response) {

        String result = "";
        this.gson = new Gson();
        PartyDTO partyDTO = gson.fromJson(input, PartyDTO.class);
        if (partyDTO == null) {
            response.setStatus(400);
            return null;
        }
        PartyDAO partyDAO = new PartyDAO(partyDTO);
        // ArrayList<SlotDTO> slots = new ArrayList<>();
        this.repository.save(partyDAO);
        List<SlotDAO> slotDAOs = new ArrayList<>();
        for (SlotDTO slot : partyDTO.getSlots()) {
            slotDAOs.add(new SlotDAO(slot));
        }
        this.slotRepository.save(slotDAOs);
        return result;
    }

}
