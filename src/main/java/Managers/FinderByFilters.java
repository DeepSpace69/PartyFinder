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
public class FinderByFilters {
    private INameResolver nameResolver;
    private ITypeResolver typeResolver;
    private EntityManager entityManager;

    @Autowired
    public FinderByFilters(
            EntityManager entityManager,
            ITypeResolver typeResolver,
            INameResolver nameResolver) {
        this.entityManager = entityManager;
        this.typeResolver = typeResolver;
        this.nameResolver = nameResolver;
    }

    public <T> List<T> getByFilters(List<FilterDTO> filters, Class<T> type) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(type);
        Root<T> root = criteriaQuery.from(type);
        Predicate clause = this.createClause(filters, criteriaBuilder, root);
        criteriaQuery.where(clause);
        TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
        List<T> result = query.getResultList();
        return result;
    }

    private <T> Predicate createClause(List<FilterDTO> filters, CriteriaBuilder criteriaBuilder, Root<T> root) {
        Map<String, List<String>> groupedFilters =
                filters.stream().collect(Collectors.groupingBy(p -> p.getKey(), mapping(x -> x.getValue(), toList())));
        List<Predicate> orClauses = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : groupedFilters.entrySet()) {
            if (entry.getValue().contains(null)) {
            } else {
                orClauses.add(this.createOrClause(entry.getKey(), entry.getValue(), criteriaBuilder, root));
            }
        }

        return criteriaBuilder.and(orClauses.toArray(new Predicate[orClauses.size()]));
    }

    private <T> Predicate createOrClause(String type, List<String> values, CriteriaBuilder criteriaBuilder, Root<T> root) {
        Predicate result;
        if (values.size() == 1) {
            result = this.createSimpleClause(type, values.get(0), criteriaBuilder, root);
        } else {
            List<Predicate> likeClauses = new ArrayList<>();
            for (String value : values) {
                Predicate likeClause = this.createSimpleClause(type, value, criteriaBuilder, root);
                likeClauses.add(likeClause);
            }
            result = criteriaBuilder.or(likeClauses.toArray(new Predicate[likeClauses.size()]));
        }
        return result;
    }

    private <T> Predicate createSimpleClause(String type, String value, CriteriaBuilder criteriaBuilder, Root<T> root) {
        Predicate result = criteriaBuilder.conjunction();
        if (this.typeResolver.getType(type) == String.class) {
            String valueDAO = this.nameResolver.getDAOValueName(type, value);
            result = criteriaBuilder.like(root.get(type), String.format("%%%1s%%", valueDAO));
        }
        if (this.typeResolver.getType(type) == Boolean.class) {
            result = criteriaBuilder.equal(root.get(type), Boolean.parseBoolean(value));
        }
        if (Objects.equals(type, "age")) {
            result = criteriaBuilder.gt(root.get(type), Integer.parseInt(value));
        }
//        if(Objects.equals(type, "createDate")||Objects.equals(type, "updateDate")){
//            result = criteriaBuilder.equal(root.get(type), Date.parse(value));
//        }
        if (Objects.equals(type, "user")) {
            result = criteriaBuilder.equal(root.get(type), Integer.parseInt(value));
        }
        return result;
    }
}
