package main.java.tests;

import main.java.DAOs.SlotDAO;
import main.java.DTOs.SlotDTO;
import main.java.Factories.SlotDAOFactory;
import main.java.Managers.VocabularyManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Tatka on 07.12.2016.
 */
public class SlotDAOFactoryTest {

    private SlotDAOFactory target;

    @Before
    public void setUp() throws Exception {
        VocabularyManager vocabularyManager = mock(VocabularyManager.class);
        when(vocabularyManager.getIdByTypeAndValue("sex", "Female")).thenReturn("sex_Female");
        when(vocabularyManager.getIdByTypeAndValue("classType", "Swordmage")).thenReturn("classType_Swordmage");
        when(vocabularyManager.getIdByTypeAndValue("role", "DD")).thenReturn("role_DD");
        this.target = new SlotDAOFactory(vocabularyManager);
    }

    @Test
    public void create_DTO_CorrectSlotDAO() {
        // Arrange
        SlotDTO slotDTO = new SlotDTO();
        slotDTO.setClassType("Swordmage");
        slotDTO.setRole("DD");
        slotDTO.setSex("Female");
        slotDTO.setLinkCharacter("someLink");
        slotDTO.setIsFree(false);
        slotDTO.setIsPartyLeader(false);
        Long partyId = 1l;

        // Act
        SlotDAO actual = this.target.create(slotDTO, partyId);

        // Assert
        Assert.assertEquals("role_DD", actual.getRole());
        Assert.assertEquals("classType_Swordmage", actual.getClassType());
        Assert.assertEquals("sex_Female", actual.getSex());
        Assert.assertEquals("someLink", actual.getLinkCharacter());
        Assert.assertEquals(false, actual.isExisting());
        Assert.assertEquals((Long)1l, actual.getFkParty());
        Assert.assertEquals(false, actual.getPartyLeader());
    }
}