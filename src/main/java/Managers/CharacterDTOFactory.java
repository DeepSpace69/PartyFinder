package main.java.Managers;

import main.java.DAOs.CharacterDAO;
import main.java.DAOs.PartyDAO;
import main.java.DAOs.PrimeTimeDAO;
import main.java.DAOs.SlotDAO;
import main.java.DTOs.CharacterDTO;
import main.java.DTOs.PartyDTO;
import main.java.DTOs.PrimeTimeDTO;
import main.java.DTOs.SlotDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public CharacterDTO create(CharacterDAO characterDAO, List<PrimeTimeDAO> primeTimeDAOs) {
        CharacterDTO result = new CharacterDTO();
        result.setName(characterDAO.getName());
        result.setRole(characterDAO.getRole());
        result.setClassType(characterDAO.getClassType());
        result.setLevel(characterDAO.getLevel());
        result.setServerName(characterDAO.getServerName());
        List<PrimeTimeDTO> primeTimeDTOs = new ArrayList<>();
        for (PrimeTimeDAO primeTimeDAO : primeTimeDAOs) {
            if (characterDAO.getId().equals(primeTimeDAO.getFkCharacter())) {
                primeTimeDTOs.add(this.primeTimeDTOFactory.create(primeTimeDAO));
            }
        }
        result.setPrimeTimes(primeTimeDTOs);
        return result;
    }

}
