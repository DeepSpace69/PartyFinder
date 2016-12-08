package main.java.Managers;

import main.java.DAOs.SlotDAO;
import main.java.DTOs.SlotDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Tatka on 05.12.2016.
 */
@Service
public class SlotDTOFactory {
    private VocabularyManager vocabulary;

    @Autowired
    public SlotDTOFactory(VocabularyManager vocabularyManager) {
        this.vocabulary = vocabularyManager;
    }

  // todo DONE вынести в отдельный класс
    public SlotDTO create(SlotDAO slot) {
        SlotDTO result = new SlotDTO();
        result.setRole(this.vocabulary.getValueById(slot.getRole()));
        result.setClassType(this.vocabulary.getValueById(slot.getClassType()));
        result.setSex(this.vocabulary.getValueById(slot.getSex()));
        result.setLinkCharacter(slot.getLinkCharacter());
        result.setFree(slot.isExisting());
        result.setPartyLeader(slot.getPartyLeader());
        return result;
    }

}