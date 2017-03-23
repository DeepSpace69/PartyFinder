//package main.java.Controllers;
//
//import com.google.gson.Gson;
//import main.java.DAOs.CharacterDAO;
//import main.java.DAOs.PartyDAO;
//import main.java.DTOs.CharacterDTO;
//import main.java.DTOs.PartyDTO;
//import main.java.Factories.PartyDAOFactory;
//import main.java.Factories.PartyDTOFactory;
//import main.java.Factories.PrimeTimeDAOFactory;
//import main.java.Factories.SlotDAOFactory;
//import main.java.Repositories.CharacterRepository;
//import main.java.Repositories.PartyRepository;
//import main.java.Repositories.PrimeTimeRepository;
//import main.java.Repositories.SlotRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.persistence.EntityManager;
//import javax.persistence.TypedQuery;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//import javax.servlet.http.HttpServletResponse;
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//@RequestMapping("/getParties")
//public class PartyControllerByUser {
//    private PartyRepository repository;
//    private SlotRepository slotRepository;
//    private PrimeTimeRepository primeTimeRepository;
//    private CharacterRepository characterRepository;
//    private Gson gson;
//    private PartyDAOFactory partyDAOFactory;
//    private SlotDAOFactory slotDAOFactory;
//    private PrimeTimeDAOFactory primeTimeDAOFactory;
//    private PartyDTOFactory factory;
//    private EntityManager entityManager;
//
//    @Autowired
//    public PartyControllerByUser(
//            PartyRepository repository,
//            SlotRepository slotRepository,
//            PrimeTimeRepository primeTimeRepository,
//            CharacterRepository characterRepository,
//            PartyDAOFactory partyDAOFactory,
//            SlotDAOFactory slotDAOFactory,
//            PrimeTimeDAOFactory primeTimeDAOFactory,
//            PartyDTOFactory factory,
//            EntityManager entityManager) {
//        this.repository = repository;
//        this.slotRepository = slotRepository;
//        this.primeTimeRepository = primeTimeRepository;
//        this.characterRepository = characterRepository;
//        this.partyDAOFactory = partyDAOFactory;
//        this.slotDAOFactory = slotDAOFactory;
//        this.primeTimeDAOFactory = primeTimeDAOFactory;
//        this.factory = factory;
//        this.entityManager = entityManager;
//    }
//
//    @RequestMapping(method = RequestMethod.GET)
//    @ResponseBody
//    public String createParty(@RequestBody String input, HttpServletResponse response) {
//        this.gson = new Gson();
//        Integer userId = Integer.parseInt(input);
//        List<PartyDAO> partyDAOs = this.getByUser(userId);
//        List<PartyDTO> partyDTOs = this.makeDTOs(partyDAOs);
//        return this.gson.toJson(partyDTOs);
//    }
//
//    private List<PartyDTO> makeDTOs(List<PartyDAO> parties) {
//        List<PartyDTO> result = new ArrayList<>();
//        for (PartyDAO partyDAO : parties) {
//            result.add(this.factory.create(partyDAO));
//        }
//        return result;
//    }
//
//    private List<PartyDAO> getByUser(Integer userId) {
//        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
//        CriteriaQuery<PartyDAO> criteriaQuery = criteriaBuilder.createQuery(PartyDAO.class);
//        Root<CharacterDAO> root = criteriaQuery.from(CharacterDAO.class);
//        Predicate clause = criteriaBuilder.equal(root.get("fkUser"), userId);
//        criteriaQuery.where(clause);
//        TypedQuery<CharacterDAO> query = entityManager.createQuery(criteriaQuery);
//        List<CharacterDAO> result = query.getResultList();
//        return result;
//    }
//
//
//}
