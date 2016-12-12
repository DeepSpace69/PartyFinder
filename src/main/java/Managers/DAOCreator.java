package main.java.Managers;

/**
 * Created by Tatka on 05.12.2016.
 */

// todo delete this class. Implement DTO <-> DAO converters
// todo rename to Factory
    // todo Done избавиться от ANY
public abstract class DAOCreator {
    protected VocabularyManager vocabulary;
    private final String any = "Any";
    protected String nullHandler(String candidate) {
        String result = candidate;
        if (candidate.isEmpty()) {
            result = this.any;
        }
        return result;
    }
}
