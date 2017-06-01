package main.java.Factories;

import main.java.Const.VocabularyTypes;
import main.java.DAOs.PartyDAO;
import main.java.DTOs.PartyDTO;
import main.java.Managers.VocabularyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Tatka on 05.12.2016.
 */
@Service
public class PartyDAOFactory {
private VocabularyManager vocabulary;
    @Autowired
    public PartyDAOFactory(VocabularyManager vocabulary) {
       this.vocabulary = vocabulary;
    }


    public PartyDAO create(PartyDTO partyDTO) {
        PartyDAO result = new PartyDAO();
        result.setName(partyDTO.getName());
        result.setAge(partyDTO.getAge());
        result.setPve(partyDTO.getPve());
        result.setPvp(partyDTO.getPvp());
        result.setChatType(this.vocabulary.getIdByTypeAndValue(VocabularyTypes.chatType, partyDTO.getChatType()));
        result.setChatListening(partyDTO.getChatListening());
        result.setChatSpeaking(partyDTO.getChatSpeaking());
        result.setStrongLanguage(partyDTO.getStrongLanguage());
        result.setLanguage(this.vocabulary.getIdByTypeAndValue(VocabularyTypes.lang, partyDTO.getLanguage()));
        result.setServersGroup(this.vocabulary.getIdByTypeAndValue(VocabularyTypes.serversGroup, partyDTO.getServersGroup()));
        result.setServerName(this.vocabulary.getIdByTypeAndValue(VocabularyTypes.serverName, partyDTO.getServerName()));
        result.setCreateDate(new Date());
        result.setUpdateDate(new Date());
        result.setUser(partyDTO.getUser());
		result.setImage(partyDTO.getImage());
        return result;
    }


}
