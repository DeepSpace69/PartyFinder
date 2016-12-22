package main.java.Managers;

import main.java.Const.VocabularyTypes;
import main.java.Core.ValidationResult;
import main.java.DTOs.CharacterDTO;
import main.java.DTOs.PartyDTO;
import main.java.DTOs.PrimeTimeDTO;
import main.java.DTOs.SlotDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class Validator {

    private VocabularyManager vocabularyManager;

    @Autowired
    public Validator(VocabularyManager vocabularyManager) {
        this.vocabularyManager = vocabularyManager;
    }

    public ValidationResult validate(PartyDTO party) {

        Set<String> setMessages = new HashSet<>();
        checkValid(VocabularyTypes.lang, party.getLanguage(), setMessages);
        checkValid(VocabularyTypes.serversGroup, party.getServersGroup(), setMessages);
        checkValid(VocabularyTypes.serverName, party.getServerName(), setMessages);
        checkValid(VocabularyTypes.chatType, party.getChatType(), setMessages);
        Integer age = party.getAge();
        if (age <= 0 || age > 100) {
            setMessages.add(String.format("Invalid age: %1s", age));
        }
        if (alreadyExist(party.getName())) {
            setMessages.add(String.format("Name already exists: %1s", party.getName()));

        }
        for (SlotDTO slot : party.getSlots()) {
            checkValid(VocabularyTypes.role, slot.getRole(), setMessages);
            checkValid(VocabularyTypes.classType, slot.getClassType(), setMessages);
            checkValid(VocabularyTypes.sex, slot.getSex(), setMessages);
        }
        for (PrimeTimeDTO primeTime : party.getPrimeTimes()) {
            checkValid(VocabularyTypes.day, primeTime.getDay(), setMessages);
        }
        return createValidationResult(setMessages);
    }

    public ValidationResult validate(CharacterDTO character) {
        Set<String> setMessages = new HashSet<>();
        checkValid(VocabularyTypes.serverName, character.getServerName(), setMessages);
        checkValid(VocabularyTypes.role, character.getRole(), setMessages);
        checkValid(VocabularyTypes.classType, character.getClassType(), setMessages);
        //checkValid(VocabularyTypes.sex, character.getSex(), setMessages);
        for (PrimeTimeDTO primeTime : character.getPrimeTimes()) {
            checkValid(VocabularyTypes.day, primeTime.getDay(), setMessages);
        }
        return createValidationResult(setMessages);
    }

    private ValidationResult createValidationResult(Set<String> setMessages) {
        ValidationResult result = new ValidationResult();
        if (!setMessages.isEmpty()) {
            String message = String.join(", ", setMessages);
            result.setErrors(true, message);
        }
        return result;
    }

    private void checkValid(String type, String candidate, Set<String> setMessages) {
        if (!(candidate == null)) {
            candidate = candidate.toUpperCase();
        }
        if (!this.vocabularyManager.getVocabulary(type).contains(candidate)) {
            setMessages.add(String.format("Invalid value for vocabulary - %1s: %2s", type, candidate));
        }
    }

    private boolean alreadyExist(String name) {
        // todo NOT DEMO implement
        return false;
    }
}
