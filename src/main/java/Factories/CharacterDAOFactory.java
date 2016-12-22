package main.java.Factories;

import main.java.DAOs.CharacterDAO;
import main.java.DTOs.CharacterDTO;
import main.java.Managers.VocabularyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacterDAOFactory {
    private VocabularyManager vocabularyManager;

    @Autowired
    public CharacterDAOFactory(VocabularyManager vocabularyManager) {
        this.vocabularyManager = vocabularyManager;
    }

    public CharacterDAO create(CharacterDTO characterDTO) {
        CharacterDAO result = new CharacterDAO();
        result.setName(characterDTO.getName());
        result.setRole(characterDTO.getRole());
        result.setClassType(characterDTO.getClassType());
        result.setLevel(characterDTO.getLevel());
        result.setServerName(characterDTO.getServerName());
        //result.setOwner();
        return result;
    }
}
