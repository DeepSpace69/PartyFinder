package main.java.Controllers;

import javax.validation.Valid;
import java.util.List;

import main.java.DAOs.PartyDAO;
import main.java.Repositories.PartyRepository;
import main.java.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class HomeController {

    private PartyRepository repository;

    @Autowired
    public HomeController(PartyRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String home(ModelMap model) {
        List<PartyDAO> records = repository.findAll();
        model.addAttribute("records", records);
        model.addAttribute("insertRecord", new Record());
        return "home";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String insertData(ModelMap model, 
                             @ModelAttribute("insertRecord") @Valid Record record,
                             BindingResult result) {
//        if (!result.hasErrors()) {
//            repository.save(record);
//        }
        return home(model);
    }
}
