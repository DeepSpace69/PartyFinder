package main.java.Managers;

import main.java.Intefaces.ITypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TypeResolverDemo implements ITypeResolver {
    private Set<String> booleanAttributs;
    private List<String> stringAttributs;
    private VocabularyManager vocabularyManager;

    @Autowired
    public TypeResolverDemo(VocabularyManager vocabularyManager) {
        this.vocabularyManager = vocabularyManager;
        this.booleanAttributs = new HashSet<>();
        this.booleanAttributs.add("strongLanguage");
        this.booleanAttributs.add("voiceChat");
        this.booleanAttributs.add("chatListening");
        this.booleanAttributs.add("chatSpeaking");
        this.booleanAttributs.add("pvp");
        this.booleanAttributs.add("pve");
    }

    @Override
    public Class getType(String candidate) {
        Class result = null;
        if (this.vocabularyManager.isInVocabulary(candidate)) {
            result = String.class;
        }
        if (this.booleanAttributs.contains(candidate)) {
            result = Boolean.class;
        }
        return result;
    }
}
