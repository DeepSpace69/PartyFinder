package main.java.Controllers;

import main.java.DAOs.PartyDAO;
import main.java.Repositories.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Tatka on 24.11.2016.
 */
@Controller
@RequestMapping("/findParty")
public class PartyController {
    private PartyRepository repository;

    @Autowired
    public PartyController(PartyRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String findParty(ModelMap model) {
        List<PartyDAO> records = repository.findAll();
        model.addAttribute("records", records);
        //model.addAttribute("insertRecord", new Record());
        return "Hello!";
    }

}
