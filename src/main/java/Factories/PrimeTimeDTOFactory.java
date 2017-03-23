package main.java.Factories;

import main.java.DAOs.PrimeTimeDAO;
import main.java.DTOs.PrimeTimeDTO;
import main.java.DTOs.TimeOfDayDTO;
import main.java.Managers.VocabularyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
