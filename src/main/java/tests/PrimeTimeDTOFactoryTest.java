package main.java.tests;

import main.java.DAOs.PrimeTimeDAO;
import main.java.DTOs.PrimeTimeDTO;
import main.java.Managers.PrimeTimeDTOFactory;
import main.java.Managers.VocabularyManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Tatka on 07.12.2016.
 */
public class PrimeTimeDTOFactoryTest {

    private PrimeTimeDTOFactory target;

    @Before
    public void setUp() throws Exception {
        VocabularyManager vocabularyManager = mock(VocabularyManager.class);
        when(vocabularyManager.getValueById("day_Monday")).thenReturn("Monday");
        this.target = new PrimeTimeDTOFactory(vocabularyManager);
    }

    @Test
    public void create_DAOs_CorrectPrimeTimeDTO() {
        // Arrange
        PrimeTimeDAO primeTimeDAO = new PrimeTimeDAO();
        primeTimeDAO.setStartHour(18);
        primeTimeDAO.setStartMinute(0);
        primeTimeDAO.setEndHour(23);
        primeTimeDAO.setEndMinute(30);
        primeTimeDAO.setDay("day_Monday");
        primeTimeDAO.setTimeZone(1);
        primeTimeDAO.setFkParty(1l);
        primeTimeDAO.setId(1l);

        // Act
        PrimeTimeDTO actual = this.target.create(primeTimeDAO);

        //Assert
        Assert.assertEquals(18, actual.getStart().getHour());
        Assert.assertEquals(0, actual.getStart().getMinute());
        Assert.assertEquals(23, actual.getEnd().getHour());
        Assert.assertEquals(30, actual.getEnd().getMinute());
        Assert.assertEquals("Monday", actual.getDay());
        Assert.assertEquals(1, actual.getTimeZone());
    }
}