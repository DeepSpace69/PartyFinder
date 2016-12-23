package main.java.Controllers;

import com.google.gson.Gson;
import main.java.DAOs.PartyDAO;
import main.java.Factories.PartyDTOFactory;
import main.java.Repositories.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

/**
 * Created by Tatka on 24.11.2016.
 */
@Controller
@RequestMapping("/testSpringQuery")
public class TestSpringQuery {
    private PartyRepository repository;
    private Gson gson;
    private PartyDTOFactory partyDTOFactory;
    private EntityManager entityManager;

    @Autowired
    public TestSpringQuery(PartyRepository repository, PartyDTOFactory partyDTOFactory, EntityManager entityManager) {
        this.repository = repository;
        this.partyDTOFactory = partyDTOFactory;
        this.entityManager = entityManager;
        CriteriaBuilder qb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PartyDAO> c = qb.createQuery(PartyDAO.class);
        Root<PartyDAO> p = c.from(PartyDAO.class);
        Predicate condition1 = qb.gt(p.get("age"), 18);
        Predicate condition2 = qb.lt(p.get("age"), 20);
        String lang = "lang_eng";
        Predicate condition3 = qb.like(p.get("language"),String.format("%%%1s%%",lang));
        List<Predicate> conditions = new ArrayList<>();
        conditions.add(condition1);
        conditions.add(condition2);
        Predicate orCondition = qb.and(conditions.toArray(new Predicate[conditions.size()]));
        //Predicate condition3 = qb.gt(p.get("age"), 22);
        Predicate andCondition = qb.or(orCondition, condition3);
        c.where(andCondition);
        //(andPredicates.toArray(new Predicate[andPredicates.size()]));
        //cb.like(userRoot.<String> get(colIndex), "%" + colValue + "%")
        TypedQuery<PartyDAO> q = entityManager.createQuery(c);
        List<PartyDAO> result = q.getResultList();
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String findParty() {

        //this.gson = new Gson();
        //return this.gson.toJson(outputParties);
        return "";
    }

}
