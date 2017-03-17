package main.java.tests;

import main.java.DAOs.SlotDAO;
import main.java.DTOs.SlotDTO;
import main.java.Factories.SlotDTOFactory;
import main.java.Managers.VocabularyManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Tatka on 07.12.2016.
 */
public class SlotDTOFactoryTest {

    private SlotDTOFactory target;

    @Before
    public void setUp() throws Exception {
        VocabularyManager vocabularyManager = mock(VocabularyManager.class);
        when(vocabularyManager.getValueById("sex_Male")).thenReturn("Male");
        when(vocabularyManager.getValueById("classType_Occultist")).thenReturn("Occultist");
        when(vocabularyManager.getValueById("classType_Swordmage")).thenReturn("Swordmage");
        when(vocabularyManager.getValueById("classType_Gunslinger")).thenReturn("Gunslinger");
        when(vocabularyManager.getValueById("role_Healer")).thenReturn("Healer");
        when(vocabularyManager.getValueById("role_DD")).thenReturn("DD");
        this.target = new SlotDTOFactory(vocabularyManager);
    }

    @Test
    public void create_DAOs_CorrectSlotDTO() {
        // Arrange
        SlotDAO slotDAO = new SlotDAO();
        slotDAO.setClassType("classType_Gunslinger");
        slotDAO.setRole("role_DD");
        slotDAO.setExisting(false);
        slotDAO.setLinkCharacter(2);
        slotDAO.setSex("sex_Male");
        slotDAO.setPartyLeader(true);
        slotDAO.setId(1l);
        slotDAO.setFkParty(1l);

        // Act
        SlotDTO actual = this.target.create(slotDAO);

        //Assert
        Assert.assertEquals("DD", actual.getRole());
        Assert.assertEquals("Gunslinger", actual.getClassType());
        Assert.assertEquals("Male", actual.getSex());
        Assert.assertEquals("someLink", actual.getLinkCharacter());
        Assert.assertEquals(false, actual.isFree());
        Assert.assertEquals(true, actual.getIsPartyLeader());
    }


}