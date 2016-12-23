package main.java.Controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import main.java.DTOs.PartyDTO;
import main.java.DTOs.FilterDTO;
import main.java.DAOs.PartyDAO;
import main.java.Factories.PartyDTOFactory;
import main.java.Managers.FinderByFilters;
import main.java.Repositories.PartyRepository;
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
@RequestMapping("/findParties")
public class PartyController {
    private PartyRepository repository;
    private PartyDTOFactory factory;
    private Gson gson;
    private FinderByFilters<PartyDAO> finderByFilters;

    @Autowired
    public PartyController(
            PartyRepository repository,
            PartyDTOFactory factory,
            FinderByFilters<PartyDAO> finderByFilters) {
        this.repository = repository;
        this.factory = factory;
        this.finderByFilters = finderByFilters;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String findParty(@RequestBody String input) {

        this.gson = new Gson();
        Type jsonType = new TypeToken<List<FilterDTO>>() {
        }.getType();
        List<FilterDTO> filters = gson.fromJson(input, jsonType);
        if (filters.isEmpty()) {
            List<PartyDAO> parties = repository.findAll();
            List<PartyDTO> result = this.makeDTOs(parties);
            return this.gson.toJson(result);
        }
        List<PartyDAO> partyDAOs = this.finderByFilters.getByFilters(filters, PartyDAO.class);
        List<PartyDTO> partyDTOs = this.makeDTOs(partyDAOs);
        String result = this.gson.toJson(partyDTOs);
        return result;
    }

    private List<PartyDTO> makeDTOs(List<PartyDAO> partyDAOs) {
        List<PartyDTO> result = new ArrayList<>();
        for (PartyDAO party : partyDAOs) {
            result.add(this.factory.create(party, null, null));
        }
        return result;
    }
}
