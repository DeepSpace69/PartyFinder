package main.java.Controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import main.java.Managers.FilterFactory;
import main.java.DTOs.FilterDTO;
import main.java.DAOs.PartyDAO;
import main.java.Repositories.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

/**
 * Created by Tatka on 24.11.2016.
 */
@Controller
@RequestMapping("/findParties2")
public class PartyController2 {
    private PartyRepository repository;
    //private FilterFactory filterFactory;
    private Gson gson;

    @Autowired
    public PartyController2(PartyRepository repository) {
        this.repository = repository;
        //this.filterFactory = filterFactory;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String findParty(@RequestBody String input, HttpServletResponse response) {

        this.gson = new Gson();
        List<FilterDTO> filters = gson.fromJson(input, new TypeToken<List<FilterDTO>>() {
        }.getType());
        if (filters.isEmpty()) {
            response.setStatus(400);
            return "Filters are empty";
        }

        // todo: integration test query
        // todo: MY check client
        // todo DONE make methods static
        String clause = this.getClause(filters);
        List<PartyDAO> records = repository.getPartiesByFilters(clause);
        return this.gson.toJson(records);
    }

    private String getClause(List<FilterDTO> filterDTOs) {
        String result = null;
        Map<String, List<String>> filters = this.makeFilters(filterDTOs);
        List<String> listForAND = getListForAND(filters);
        result = String.join(" AND ", listForAND);
        return result;
    }

    private static List<String> getListForAND(Map<String, List<String>> filters) {
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : filters.entrySet()) {
            List<String> listForOR = getListForOR(entry.getKey(), entry.getValue());
            String temp = String.join(" OR ", listForOR);
            result.add(String.format("(%1s)", temp));
        }
        return result;
    }

    //todo DONE  string, list<str> - args
    private static List<String> getListForOR(String type, List<String> values) {
        List<String> result = new ArrayList<>();
        for (String element : values) {
            String temp = String.format("%1s LIKE '%%%2s%%'", type, element);
            result.add(temp);
        }
        return result;
    }

    // todo DONE list string - > list filterDTO, group by
    private Map<String, List<String>> makeFilters(List<FilterDTO> filterDTOs) {
       // List<FilterDTO> filters = this.filterFactory.create(filterDTOs);
        Map<String, List<String>> filters = new HashMap<>();
        filters = filterDTOs.stream().collect(Collectors.groupingBy(p -> p.getType(), mapping(x->x.getValue(),toList())));
        return filters;
    }

}
