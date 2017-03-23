package main.java.Factories;

import main.java.DAOs.CharacterDAO;
import main.java.DAOs.PrimeTimeDAO;
import main.java.DTOs.CharacterDTO;
import main.java.DTOs.PrimeTimeDTO;
import main.java.Managers.VocabularyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tatka on 05.12.2016.
 */
@Service
public class CharacterDTOFactory {
    private VocabularyManager vocabulary;
    private PrimeTimeDTOFactory primeTimeDTOFactory;

    @Autowired
    public CharacterDTOFactory(VocabularyManager vocabularyManager, PrimeTimeDTOFactory primeTimeDTOFactory) {
        this.vocabulary = vocabularyManager;
        this.primeTimeDTOFactory = primeTimeDTOFactory;
    }

    public CharacterDTO create(CharacterDAO characterDAO) {
        CharacterDTO result = new CharacterDTO();
        result.setId(characterDAO.getId());
        result.setName(characterDAO.getName());
        result.setRole(characterDAO.getRole());
        result.setClassType(characterDAO.getClassType());
        result.setLevel(characterDAO.getLevel());
        result.setServerName(characterDAO.getServerName());
        result.setUser(characterDAO.getFkUser());
        if (characterDAO.getPrimeTimeDAOs()== null) {
            result.setPrimeTimes(null);
        } else {
            List<PrimeTimeDTO> primeTimeDTOs = new ArrayList<>();
            for (PrimeTimeDAO primeTimeDAO : characterDAO.getPrimeTimeDAOs()) {
                if (characterDAO.getId().equals(primeTimeDAO.getFkCharacter())) {
                    primeTimeDTOs.add(this.primeTimeDTOFactory.create(primeTimeDAO));
                }
            }
            result.setPrimeTimes(primeTimeDTOs);
            result.setUser(characterDAO.getFkUser());

        }
        return result;
    }
}
