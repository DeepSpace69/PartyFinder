package main.java.Controllers;

import com.google.gson.Gson;
import main.java.DAOs.CharacterDAO;
import main.java.DTOs.CharacterDTO;
import main.java.Factories.CharacterDTOFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/getCharacters")
public class CharacterControllerByUser {

    private Gson gson;
    private CharacterDTOFactory characterDTOFactory;
    private EntityManager entityManager;

    @Autowired
    public CharacterControllerByUser(CharacterDTOFactory characterDTOFactory, EntityManager entityManager) {
        this.characterDTOFactory = characterDTOFactory;
        this.entityManager = entityManager;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String getCharacters(@RequestBody String input) {
        this.gson = new Gson();
        Integer userId = Integer.parseInt(input);
        List<CharacterDAO> characterDAOs = this.getByUser(userId);
        List<CharacterDTO> characterDTOs = this.makeDTOs(characterDAOs);
        return this.gson.toJson(characterDTOs);
    }

    private List<CharacterDTO> makeDTOs(List<CharacterDAO> characters) {
        List<CharacterDTO> result = new ArrayList<>();
        for (CharacterDAO characterDAO : characters) {
            result.add(this.characterDTOFactory.create(characterDAO));
        }
        return result;
    }

    private List<CharacterDAO> getByUser(Integer userId) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<CharacterDAO> criteriaQuery = criteriaBuilder.createQuery(CharacterDAO.class);
        Root<CharacterDAO> root = criteriaQuery.from(CharacterDAO.class);
        Predicate clause = criteriaBuilder.equal(root.get("fkUser"), userId);
        criteriaQuery.where(clause);
        TypedQuery<CharacterDAO> query = entityManager.createQuery(criteriaQuery);
        List<CharacterDAO> result = query.getResultList();
        return result;
    }

}


