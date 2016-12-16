package main.java.tests;

import main.java.DAOs.PrimeTimeDAO;
import main.java.DTOs.PrimeTimeDTO;
import main.java.DTOs.TimeOfDayDTO;
import main.java.Managers.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Tatka on 07.12.2016.
 */
public class PrimeTimeDAOFactoryTest {

    private PrimeTimeDAOFactory target;

    @Before
    public void setUp() throws Exception {
        VocabularyManager vocabularyManager = mock(VocabularyManager.class);
        when(vocabularyManager.getIdByTypeAndValue("day","Friday")).thenReturn("day_Friday");
        this.target = new PrimeTimeDAOFactory(vocabularyManager);
    }

    @Test
    public void create_DTO_CorrectPrimeTimeDAO() {
        // Arrange
        PrimeTimeDTO primeTimeDTO = new PrimeTimeDTO();
        primeTimeDTO.setDay("Friday");
        primeTimeDTO.setStart(new TimeOfDayDTO(18,0));
        primeTimeDTO.setEnd(new TimeOfDayDTO(23,0));
        primeTimeDTO.setTimeZone(2);
        Long partyId = 1l;

        // Act
        PrimeTimeDAO actual = this.target.createForPaty(primeTimeDTO, partyId);

        // Assert
        Assert.assertEquals("day_Friday", actual.getDay());
        Assert.assertEquals((Integer)18, actual.getStartHour());
        Assert.assertEquals((Integer)0, actual.getStartMinute());
        Assert.assertEquals((Integer)23, actual.getEndHour());
        Assert.assertEquals((Integer)0, actual.getEndMinute());
        Assert.assertEquals((Integer)2, actual.getTimeZone());
        Assert.assertEquals((Long)1l, actual.getFkParty());

    }

}