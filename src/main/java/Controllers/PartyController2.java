package main.java.Controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import main.java.DTOs.PartyDTO;
import main.java.DTOs.FilterDTO;
import main.java.DAOs.PartyDAO;
import main.java.Intefaces.INameResolver;
import main.java.Managers.PartyDTOFactory;
import main.java.Managers.SimpleNameResolver;
import main.java.Managers.VocabularyManager;
import main.java.Repositories.PartyRepository;
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
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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
    private final CriteriaBuilder criteriaBuilder;
    private final CriteriaQuery<PartyDAO> criteriaQuery;
    private final Root<PartyDAO> partyRoot;
    private PartyRepository repository;
    private EntityManager entityManager;
    private PartyDTOFactory factory;
    private INameResolver nameResolver;
    //private VocabularyManager vocabularyManager;
    //private FilterFactory filterFactory;
    private Gson gson;

    @Autowired
    public PartyController2(PartyRepository repository, EntityManager entityManager, PartyDTOFactory factory, VocabularyManager vocabularyManager) {
        this.repository = repository;
        this.entityManager = entityManager;
        this.factory = factory;
        this.criteriaBuilder = this.entityManager.getCriteriaBuilder();
        this.criteriaQuery = criteriaBuilder.createQuery(PartyDAO.class);
        this.partyRoot = criteriaQuery.from(PartyDAO.class);
        this.nameResolver = new SimpleNameResolver(vocabularyManager);
        //this.filterFactory = filterFactory;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String findParty(@RequestBody String input, HttpServletResponse response) {

        this.gson = new Gson();
        List<FilterDTO> filters = gson.fromJson(input, new TypeToken<List<FilterDTO>>() {
        }.getType());
        if (filters.isEmpty()) {
            List<PartyDAO> parties = repository.findAll();
            return this.gson.toJson(parties);
        }
        // todo: integration test query
        // todo: MY check client
        // todo DONE make methods static
        Predicate clause = this.createClause(filters);
        List<PartyDTO> partyDTOs = this.getParties(clause);
        return this.gson.toJson(partyDTOs);
    }

    private List<PartyDTO> getParties(Predicate clause) {
        List<PartyDTO> result = new ArrayList<>();
        this.criteriaQuery.where(clause);
        TypedQuery<PartyDAO> query = entityManager.createQuery(this.criteriaQuery);
        List<PartyDAO> partyDAOs = query.getResultList();
        for (PartyDAO partyDAO : partyDAOs) {
            result.add(this.factory.create(partyDAO, null, null));
        }
        return result;
    }

    private Predicate createClause(List<FilterDTO> filters) {
        Predicate result;
        Map<String, List<String>> groupedFilters =
                filters.stream().collect(Collectors.groupingBy(p -> p.getType(), mapping(x -> x.getValue(), toList())));
        List<Predicate> orClauses = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : groupedFilters.entrySet()) {
            orClauses.add(this.createOrClause(entry.getKey(), entry.getValue()));
        }

        return this.criteriaBuilder.and(orClauses.toArray(new Predicate[orClauses.size()]));
    }

    private Predicate createOrClause(String type, List<String> values) {
        Predicate result;
        if (values.size() == 1){
            result = this.createLikeClause(type, values.get(0));
        }else{
            List<Predicate> likeClauses = new ArrayList<>();
            for (String value : values) {
                Predicate likeClause = this.createLikeClause(type, value);
                likeClauses.add(likeClause);
            }
            result = this.criteriaBuilder.or(likeClauses.toArray(new Predicate[likeClauses.size()]));
        }
        return result;
    }

    private Predicate createLikeClause(String type, String value) {
        Predicate result;
        String typeDAO = this.nameResolver.getDAOTypeName(type);
        String valueDAO = this.nameResolver.getDAOValueName(typeDAO, value);
        result = this.criteriaBuilder.like(this.partyRoot.get(type), String.format("%%%1s%%", valueDAO));
        return result;
    }


//    private String getClause(List<FilterDTO> filterDTOs) {
//        String result = null;
//        Map<String, List<String>> filters = this.makeFilters(filterDTOs);
//        List<String> listForAND = getListForAND(filters);
//        result = String.join(" AND ", listForAND);
//        return result;
//    }
//
//    private static List<String> getListForAND(Map<String, List<String>> filters) {
//        List<String> result = new ArrayList<>();
//        for (Map.Entry<String, List<String>> entry : filters.entrySet()) {
//            List<String> listForOR = getListForOR(entry.getKey(), entry.getValue());
//            String temp = String.join(" OR ", listForOR);
//            result.add(String.format("(%1s)", temp));
//        }
//        return result;
//    }
//
//
//    private static List<String> getListForOR(String type, List<String> values) {
//        List<String> result = new ArrayList<>();
//        for (String element : values) {
//            String temp = String.format("%1s LIKE '%%%2s%%'", type, element);
//            result.add(temp);
//        }
//        return result;
//    }
//
//
//    private Map<String, List<String>> makeFilters(List<FilterDTO> filterDTOs) {
//       // List<FilterDTO> filters = this.filterFactory.create(filterDTOs);
//        Map<String, List<String>> filters = new HashMap<>();
//        filters = filterDTOs.stream().collect(Collectors.groupingBy(p -> p.getType(), mapping(x->x.getValue(),toList())));
//        return filters;
//    }

}
