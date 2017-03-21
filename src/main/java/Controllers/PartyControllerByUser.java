package main.java.Controllers;

import com.google.gson.Gson;
import main.java.Factories.PartyDAOFactory;
import main.java.Factories.PrimeTimeDAOFactory;
import main.java.Factories.SlotDAOFactory;
import main.java.Repositories.CharacterRepository;
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

@Controller
@RequestMapping("/getParties")
public class PartyControllerByUser {
    private PartyRepository repository;
    private SlotRepository slotRepository;
    private PrimeTimeRepository primeTimeRepository;
    private CharacterRepository characterRepository;
    private Gson gson;
    private PartyDAOFactory partyDAOFactory;
    private SlotDAOFactory slotDAOFactory;
    private PrimeTimeDAOFactory primeTimeDAOFactory;

    @Autowired
    public PartyControllerByUser(
            PartyRepository repository,
            SlotRepository slotRepository,
            PrimeTimeRepository primeTimeRepository,
            CharacterRepository characterRepository,
            PartyDAOFactory partyDAOFactory,
            SlotDAOFactory slotDAOFactory,
            PrimeTimeDAOFactory primeTimeDAOFactory) {
        this.repository = repository;
        this.slotRepository = slotRepository;
        this.primeTimeRepository = primeTimeRepository;
        this.characterRepository = characterRepository;
        this.partyDAOFactory = partyDAOFactory;
        this.slotDAOFactory = slotDAOFactory;
        this.primeTimeDAOFactory = primeTimeDAOFactory;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String createParty(@RequestBody String input, HttpServletResponse response) {
        String result = "";


        return result;
    }


}
