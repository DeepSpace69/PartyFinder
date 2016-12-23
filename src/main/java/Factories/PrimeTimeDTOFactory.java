package main.java.Factories;

import main.java.DAOs.PartyDAO;
import main.java.DAOs.PrimeTimeDAO;
import main.java.DAOs.SlotDAO;
import main.java.DTOs.PartyDTO;
import main.java.DTOs.PrimeTimeDTO;
import main.java.DTOs.SlotDTO;
import main.java.DTOs.TimeOfDayDTO;
import main.java.Managers.VocabularyManager;
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
public class PrimeTimeDTOFactory {
    private VocabularyManager vocabulary;

    @Autowired
    public PrimeTimeDTOFactory(VocabularyManager vocabularyManager) {
        this.vocabulary = vocabularyManager;
    }

    // todo DONE вынести в отдельный класс
    public PrimeTimeDTO create(PrimeTimeDAO primeTime) {
        PrimeTimeDTO result = new PrimeTimeDTO();
        result.setStart(new TimeOfDayDTO(primeTime.getStartHour(), primeTime.getStartMinute()));
        result.setEnd(new TimeOfDayDTO(primeTime.getEndHour(), primeTime.getEndMinute()));
        result.setDay(this.vocabulary.getValueById(primeTime.getDay()));
        result.setTimeZone(primeTime.getTimeZone());

        return result;
    }


}
