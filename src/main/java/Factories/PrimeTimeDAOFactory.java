package main.java.Factories;

import main.java.Const.VocabularyTypes;
import main.java.DAOs.PrimeTimeDAO;
import main.java.DTOs.PrimeTimeDTO;
import main.java.Managers.VocabularyManager;
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


    public PrimeTimeDAO createForPaty(PrimeTimeDTO primeTime, Long partyId) {
        PrimeTimeDAO result =this.create(primeTime);
        result.setFkParty(partyId);
        return result;
    }

    public PrimeTimeDAO createForCharacter(PrimeTimeDTO primeTime, Long caharacterId) {
        PrimeTimeDAO result =this.create(primeTime);
        result.setFkCharacter(caharacterId);
        return result;
    }

    private PrimeTimeDAO create(PrimeTimeDTO primeTime) {
        PrimeTimeDAO result = new PrimeTimeDAO();
        result.setStartHour(primeTime.getStart().getHour());
        result.setStartMinute(primeTime.getStart().getMinute());
        result.setEndHour(primeTime.getEnd().getHour());
        result.setEndMinute(primeTime.getEnd().getMinute());
        result.setDay(this.vocabulary.getIdByTypeAndValue(VocabularyTypes.day, primeTime.getDay()));
        result.setTimeZone(primeTime.getTimeZone());
        return result;
    }


}
