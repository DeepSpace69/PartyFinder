package main.java.Core;

import main.java.DAOs.VocabularyDAO;
import main.java.Repositories.VocabularyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Tatka on 28.11.2016.
 */

// todo rename to VocabularyManager; implement getElementById, getIdByElementAndType; getAllElementsByType; optimize storage algorithm
@Service
public class VocabularyLoader {
    private Map<String, List<String>> vocabularies;
    private VocabularyRepository repository;

    @Autowired
    public VocabularyLoader(VocabularyRepository repository) {
        this.repository = repository;
        this.init();
    }

    private void init() {
        this.vocabularies = new HashMap<>();
        List<VocabularyDAO> list = this.repository.findAll();

        for (VocabularyDAO element : list) {
            this.saveElement(element);
        }

    }

    private void saveElement(VocabularyDAO element) {
        List<String> targetList = this.getList(element);
        targetList.add(element.getValue());
    }

    private List<String> getList(VocabularyDAO element) {
        List<String> result;
        if (vocabularies.containsKey(element.getType())) {
            result = vocabularies.get(element.getType());
        } else {
            result = new ArrayList<>();
            vocabularies.put(element.getType(), result);
        }
        return result;
    }

    public  List<String> getVocabulary(String type) {
        return this.vocabularies.get(type);
    }

}
