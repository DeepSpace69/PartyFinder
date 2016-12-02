package main.java.Controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import main.java.DTOs.FilterDTO;
import main.java.DAOs.PartyDAO;
import main.java.Repositories.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tatka on 24.11.2016.
 */
@Controller
@RequestMapping("/findParties2")
public class PartyController2 {
    private PartyRepository repository;
    private Gson gson;

    @Autowired
    public PartyController2(PartyRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String findParty(@RequestBody String input) {

        this.gson = new Gson();
        List<FilterDTO> filters = gson.fromJson(input, new TypeToken<List<FilterDTO>>() {
        }.getType());

        // TODO: integration test query
        String clause = getClause(filters);
        List<PartyDAO> records = repository.getPartiesByFilters(clause);
        return this.gson.toJson(records);
    }

    private String getClause(List<FilterDTO> filters) {
        List<String> list = new ArrayList<>();
        for (FilterDTO filter : filters) {
            list.add(filter.getType() + " LIKE '%" + filter.getValue() + "%'");

        }
        String result = String.join(" AND ", list);
        return result;
    }

}
