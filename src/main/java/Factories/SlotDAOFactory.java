package main.java.Factories;

import main.java.Const.VocabularyTypes;
import main.java.DAOs.SlotDAO;
import main.java.DTOs.SlotDTO;
import main.java.Managers.VocabularyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Tatka on 05.12.2016.
 */
@Service
public class SlotDAOFactory {
    private VocabularyManager vocabulary;

    @Autowired
    public SlotDAOFactory(VocabularyManager vocabulary) {
        this.vocabulary = vocabulary;
    }


    public SlotDAO create(SlotDTO slot, Long partyId) {
        SlotDAO result = new SlotDAO();
        result.setRole(this.vocabulary.getIdByTypeAndValue(VocabularyTypes.role, slot.getRole()));
        result.setClassType(this.vocabulary.getIdByTypeAndValue(VocabularyTypes.classType, slot.getClassType()));
        result.setSex(this.vocabulary.getIdByTypeAndValue(VocabularyTypes.sex, slot.getSex()));
        result.setLinkCharacter(slot.getLinkCharacter());
        result.setExisting(slot.isFree());
        result.setPartyLeader(slot.getIsPartyLeader());
        result.setFkParty(partyId);
        return result;
    }


}
