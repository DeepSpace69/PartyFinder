package main.java.Controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import main.java.DTOs.PartyDTO;
import main.java.DTOs.FilterDTO;
import main.java.DAOs.PartyDAO;
import main.java.DAOs.SlotDAO;
import main.java.Intefaces.INameResolver;
import main.java.Intefaces.ITypeResolver;
import main.java.Factories.PartyDTOFactory;
import main.java.Managers.TypeResolverDemo;
import main.java.Managers.VocabularyManager;
import main.java.Repositories.PartyRepository;
import main.java.Repositories.SlotRepository;
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
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

/**
 * Created by Tatka on 24.11.2016.
 */
@Controller
@RequestMapping("/findParties")
public class PartyController {
    private final CriteriaBuilder criteriaBuilder;
    private final CriteriaQuery<PartyDAO> criteriaQuery;
    private final Root<PartyDAO> partyRoot;
    private PartyRepository repository;
	private SlotRepository slotRepository;
    private EntityManager entityManager;
    private PartyDTOFactory factory;
    private INameResolver nameResolver;
    //private VocabularyManager vocabularyManager;
    private ITypeResolver typeResolver;
    private Gson gson;

    @Autowired
    public PartyController(
            PartyRepository repository,
			SlotRepository slotRepository,
            EntityManager entityManager,
            PartyDTOFactory factory,
            VocabularyManager vocabularyManager,
            INameResolver nameResolver) {
        this.repository = repository;
		this.slotRepository = slotRepository;
        this.entityManager = entityManager;
        this.factory = factory;
        this.criteriaBuilder = this.entityManager.getCriteriaBuilder();
        this.criteriaQuery = criteriaBuilder.createQuery(PartyDAO.class);
        this.partyRoot = criteriaQuery.from(PartyDAO.class);
        this.nameResolver = nameResolver;
        this.typeResolver = new TypeResolverDemo(vocabularyManager);
        //this.filterFactory = filterFactory;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String findParty(@RequestBody String input, HttpServletResponse response) {

        this.gson = new Gson();
        Type jsonType = new TypeToken<List<FilterDTO>>() {
        }.getType();
        List<FilterDTO> filters = gson.fromJson(input, jsonType);

        if (filters.isEmpty()) {
            List<PartyDAO> parties = repository.findAll();
            List<PartyDTO> result = new ArrayList<>();
			List<Long> ids = parties.stream().map((s) -> s.getId()).collect(Collectors.toList());
			List<SlotDAO> slots = this.slotRepository.getSlotsByPartyIds(ids);

            for (PartyDAO party : parties) {
                result.add(this.factory.create(party, slots, null));
            }

            return this.gson.toJson(result);
        }
        // todo: integration test query
        // todo: MY check client
        // todo DONE make methods static
        Predicate clause = this.createClause(filters);
        List<PartyDTO> partyDTOs = this.getParties(clause);
        String result = this.gson.toJson(partyDTOs);
        return result;
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
                filters.stream().collect(Collectors.groupingBy(p -> p.getKey(), mapping(x -> x.getValue(), toList())));
        List<Predicate> orClauses = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : groupedFilters.entrySet()) {
            if (entry.getValue().contains(null)) {
            } else {
                orClauses.add(this.createOrClause(entry.getKey(), entry.getValue()));
            }
        }

        return this.criteriaBuilder.and(orClauses.toArray(new Predicate[orClauses.size()]));
    }

    private Predicate createOrClause(String type, List<String> values) {
        Predicate result;
        if (values.size() == 1) {
            result = this.createSimpleClause(type, values.get(0));
        } else {
            List<Predicate> likeClauses = new ArrayList<>();
            for (String value : values) {
                Predicate likeClause = this.createSimpleClause(type, value);
                likeClauses.add(likeClause);
            }
            result = this.criteriaBuilder.or(likeClauses.toArray(new Predicate[likeClauses.size()]));
        }
        return result;
    }

    private Predicate createSimpleClause(String type, String value) {
        Predicate result = null;
        if (this.typeResolver.getType(type) == String.class) {
            String valueDAO = this.nameResolver.getDAOValueName(type, value);
            result = this.criteriaBuilder.like(this.partyRoot.get(type), String.format("%%%1s%%", valueDAO));
        }
        if (this.typeResolver.getType(type) == Boolean.class) {
            result = this.criteriaBuilder.equal(this.partyRoot.get(type), Boolean.parseBoolean(value));
        }
        if (Objects.equals(type, "age")) {
            result = this.criteriaBuilder.gt(this.partyRoot.get(type), Integer.parseInt(value));
        }
//        if(Objects.equals(type, "createDate")||Objects.equals(type, "updateDate")){
//            result = this.criteriaBuilder.equal(this.partyRoot.get(type), Date.parse(value));
//        }
        return result;
    }

//    private Predicate createLikeClause(String type, String value) {
//        Predicate result;
//        //String typeDAO = this.nameResolver.getDAOTypeName(type);
//        String valueDAO = this.nameResolver.getDAOValueName(type, value);
//        result = this.criteriaBuilder.like(this.partyRoot.get(type), String.format("%%%1s%%", valueDAO));
//        return result;
//    }


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
//        filters = filterDTOs.stream().collect(Collectors.groupingBy(p -> p.getKey(), mapping(x->x.getValue(),toList())));
//        return filters;
//    }

}
