package main.java.Managers;

import main.java.Const.VocabularyTypes;
import main.java.DAOs.PrimeTimeDAO;
import main.java.DTOs.PrimeTimeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Tatka on 05.12.2016.
 */
@Service
public class PrimeTimeDAOFactory {
    private VocabularyManager vocabulary;

    @Autowired
    public PrimeTimeDAOFactory(VocabularyManager vocabulary) {
        this.vocabulary = vocabulary;
    }


    public PrimeTimeDAO create(PrimeTimeDTO primeTime, Long partyId) {
        PrimeTimeDAO result = new PrimeTimeDAO();
        result.setStartHour(primeTime.getStart().getHour());
        result.setStartMinute(primeTime.getStart().getMinute());
        result.setEndHour(primeTime.getEnd().getHour());
        result.setEndMinute(primeTime.getEnd().getMinute());
        result.setDay(this.vocabulary.getIdByTypeAndValue(VocabularyTypes.day, primeTime.getDay()));
        result.setTimeZone(primeTime.getTimeZone());
        result.setFkParty(partyId);
        return result;
    }


}
