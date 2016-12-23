package main.java.Managers;

import main.java.DTOs.FilterDTO;
import main.java.Intefaces.INameResolver;
import main.java.Intefaces.ITypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;
@Service
public class FinderByFilters<T> {
    private INameResolver nameResolver;
    private ITypeResolver typeResolver;
    private EntityManager entityManager;
    private CriteriaBuilder criteriaBuilder;
    private CriteriaQuery<T> criteriaQuery;
    private Root<T> partyRoot;

    @Autowired
    public FinderByFilters(
            EntityManager entityManager,
            ITypeResolver typeResolver,
            INameResolver nameResolver) {
        this.entityManager = entityManager;
        this.criteriaBuilder = this.entityManager.getCriteriaBuilder();
        this.typeResolver = typeResolver;
        this.nameResolver = nameResolver;
    }

    public List<T> getByFilters(List<FilterDTO> filters, Class<T> type ){
        this.criteriaQuery = this.criteriaBuilder.createQuery(type);
        this.partyRoot =this.criteriaQuery.from(type);
        Predicate clause = this.createClause(filters);
        this.criteriaQuery.where(clause);
        TypedQuery<T> query = entityManager.createQuery(this.criteriaQuery);
        List<T> result = query.getResultList();
        return result;
    }

    private Predicate createClause(List<FilterDTO> filters) {
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
        Predicate result = this.criteriaBuilder.conjunction();
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
        if (Objects.equals(type, "user")) {
            result = this.criteriaBuilder.equal(this.partyRoot.get(type), Integer.parseInt(value));
        }
        return result;
    }
}
