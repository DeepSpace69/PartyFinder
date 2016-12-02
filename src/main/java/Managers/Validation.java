package main.java.Managers;

import main.java.Core.VocabularyLoader;
import main.java.DTOs.PartyDTO;

import java.util.List;

/**
 * Created by Tatka on 27.11.2016.
 */
public class Validation {

    private List<String> lang;
    private List<String> pvp;

    public Validation(VocabularyLoader loading) {
//        this.lang = loading.getLang();
//        this.pvp = loading.getPvp();
    }

    public void checkParty(PartyDTO partyDTO) throws Exception {
        if (partyDTO == null) {
            Exception exp = new Exception("Empty request");
            throw exp;
        }
        isValid(this.lang, partyDTO.getLanguage(), "lang");
        isValid(this.pvp, partyDTO.getPvp(), "pvp field");

    }

    private void isValid(List<String> list, String candidate, String message) throws Exception {
        if (!list.contains(candidate)) {
            Exception exp = new Exception("Invalid " + message);
            throw exp;
        }

    }

}
