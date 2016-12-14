package main.java.Managers;

import main.java.DAOs.VocabularyDAO;
import main.java.Repositories.VocabularyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Tatka on 28.11.2016.
 */

@Service
public class VocabularyManager {
    private final String format = "%1s_%2s";
    private Map<String, Set<String>> vocabularies;
    private Map<String, String> values;
    private Map<String, String> ids;
    private VocabularyRepository repository;

    @Autowired
    public VocabularyManager(VocabularyRepository repository) {
        this.repository = repository;
        this.init();
    }

    private void init() {
        this.vocabularies = new HashMap<>();
        this.values = new HashMap<>();
        this.ids = new HashMap<>();
        List<VocabularyDAO> list = this.repository.findAll();

        for (VocabularyDAO element : list) {
            this.saveElementForVocabularies(element);
            this.saveElementForValues(element);
            this.saveElementForIds(element);
        }

    }

    private void saveElementForIds(VocabularyDAO element) {
        this.ids.put(String.format(this.format, element.getType(), element.getValue()), element.getId());
    }

    private void saveElementForValues(VocabularyDAO element) {
        this.values.put(element.getId(), element.getValue());
    }

    private void saveElementForVocabularies(VocabularyDAO element) {
        Set<String> targetSet = this.getSet(element);
        targetSet.add(element.getValue());
    }

    private Set<String> getSet(VocabularyDAO element) {
        Set<String> result;
        if (vocabularies.containsKey(element.getType())) {
            result = vocabularies.get(element.getType());
        } else {
            result = new HashSet<>();
            vocabularies.put(element.getType(), result);
        }
        return result;
    }

    public Set<String> getVocabulary(String type) {
        return this.vocabularies.get(type);
    }

    public String getValueById(String id) {
        return this.values.get(id);
    }

    public String getIdByTypeAndValue(String type, String value) {
        return this.ids.get(String.format(this.format, type, value));
    }

    public boolean isInVocabulary(String typeDTO) {
        boolean result = false;
        if (this.vocabularies.containsKey(typeDTO)) {
            result = true;
        }
        return result;
    }

}

