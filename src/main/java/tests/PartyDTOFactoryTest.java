package main.java.tests;

import main.java.DAOs.PartyDAO;
import main.java.DAOs.PrimeTimeDAO;
import main.java.DAOs.SlotDAO;
import main.java.DTOs.PartyDTO;
import main.java.Managers.PartyDTOFactory;
import main.java.Managers.PrimeTimeDTOFactory;
import main.java.Managers.SlotDTOFactory;
import main.java.Managers.VocabularyManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Tatka on 02.12.2016.
 */
public class PartyDTOFactoryTest {
    private PartyDTOFactory target;
    @Before
    public void setUp() throws Exception {
        VocabularyManager vocabularyManager = mock(VocabularyManager.class);
        when(vocabularyManager.getValueById("lang_eng")).thenReturn("eng");
        when(vocabularyManager.getValueById("sex_Male")).thenReturn("Male");
        when(vocabularyManager.getValueById("classType_Occultist")).thenReturn("Occultist");
        when(vocabularyManager.getValueById("classType_Swordmage")).thenReturn("Swordmage");
        when(vocabularyManager.getValueById("classType_Gunslinger")).thenReturn("Gunslinger");
        when(vocabularyManager.getValueById("day_Monday")).thenReturn("Monday");
        when(vocabularyManager.getValueById("day_Wednesday")).thenReturn("Wednesday");
        when(vocabularyManager.getValueById("day_Friday")).thenReturn("Friday");
        when(vocabularyManager.getValueById("role_Healer")).thenReturn("Healer");
        when(vocabularyManager.getValueById("role_DD")).thenReturn("DD");
        when(vocabularyManager.getValueById("chatType_Skype")).thenReturn("Skype");
        when(vocabularyManager.getValueById("serverName_null")).thenReturn(null);
        when(vocabularyManager.getValueById("serversGroup_null")).thenReturn(null);
        SlotDTOFactory slotDTOFactory= new SlotDTOFactory(vocabularyManager);
        PrimeTimeDTOFactory primeTimeDTOFactory = new PrimeTimeDTOFactory(vocabularyManager);
        this.target = new PartyDTOFactory(vocabularyManager,slotDTOFactory,primeTimeDTOFactory);
    }


    @Test
    public void create_DAOs_CorrectPartyDTO() {
        // Arrange
        PartyDAO partyDAO = new PartyDAO();
        partyDAO.setName("Penguins");
        partyDAO.setAge(18);
        partyDAO.setVoiceChat(true);
        partyDAO.setChatListening(true);
        partyDAO.setChatSpeaking(true);
        partyDAO.setChatType("chatType_Skype");
        partyDAO.setLanguage("lang_eng");
        partyDAO.setPve(false);
        partyDAO.setPvp(true);
        partyDAO.setServersGroup("serversGroup_null");
        partyDAO.setServerName("serverName_null");
        partyDAO.setStrongLanguage(false);
        partyDAO.setCreateDate(new Date(2016, 11, 11));
        partyDAO.setUpdateDate(new Date(2016, 11, 11));
        partyDAO.setId(1l);

        List<SlotDAO> slots = new ArrayList<>();

        SlotDAO slotDAO1 = new SlotDAO();
        slotDAO1.setClassType("classType_Gunslinger");
        slotDAO1.setRole("role_DD");
        slotDAO1.setExisting(false);
        slotDAO1.setLinkCharacter("someLink");
        slotDAO1.setSex("sex_Male");
        slotDAO1.setId(1l);
        slotDAO1.setFkParty(1l);

        SlotDAO slotDAO2 = new SlotDAO();
        slotDAO2.setClassType("classType_Occultist");
        slotDAO2.setRole("role_Healer");
        slotDAO2.setExisting(false);
        slotDAO2.setLinkCharacter("someLink");
        slotDAO2.setSex("sex_Male");
        slotDAO2.setId(2l);
        slotDAO2.setFkParty(1l);

        SlotDAO slotDAO3 = new SlotDAO();
        slotDAO3.setClassType("classType_Swordmage");
        slotDAO3.setRole("role_DD");
        slotDAO3.setExisting(false);
        slotDAO3.setLinkCharacter("someLink");
        slotDAO3.setSex("sex_Male");
        slotDAO3.setId(3l);
        slotDAO3.setFkParty(2l);

        slots.add(slotDAO1);
        slots.add(slotDAO2);
        slots.add(slotDAO3);

        List<PrimeTimeDAO> primeTimes = new ArrayList<>();

        PrimeTimeDAO primeTimeDAO1 = new PrimeTimeDAO();
        primeTimeDAO1.setStartHour(18);
        primeTimeDAO1.setStartMinute(0);
        primeTimeDAO1.setEndHour(23);
        primeTimeDAO1.setEndMinute(30);
        primeTimeDAO1.setDay("day_Monday");
        primeTimeDAO1.setTimeZone(1);
        primeTimeDAO1.setFkParty(1l);
        primeTimeDAO1.setId(1l);

        PrimeTimeDAO primeTimeDAO2 = new PrimeTimeDAO();
        primeTimeDAO2.setStartHour(20);
        primeTimeDAO2.setStartMinute(0);
        primeTimeDAO2.setEndHour(0);
        primeTimeDAO2.setEndMinute(30);
        primeTimeDAO2.setDay("day_Wednesday");
        primeTimeDAO2.setTimeZone(1);
        primeTimeDAO2.setFkParty(1l);
        primeTimeDAO2.setId(2l);

        PrimeTimeDAO primeTimeDAO3 = new PrimeTimeDAO();
        primeTimeDAO3.setStartHour(20);
        primeTimeDAO3.setStartMinute(0);
        primeTimeDAO3.setEndHour(0);
        primeTimeDAO3.setEndMinute(30);
        primeTimeDAO3.setDay("day_Friday");
        primeTimeDAO3.setTimeZone(1);
        primeTimeDAO3.setFkParty(2l);
        primeTimeDAO3.setId(3l);

        primeTimes.add(primeTimeDAO1);
        primeTimes.add(primeTimeDAO2);
        primeTimes.add(primeTimeDAO3);

        PartyDTO expected = new PartyDTO();


        expected.setAge(18);
        expected.setLanguage("eng");
        expected.setStrongLanguage(false);
        expected.setServersGroup(null);
        expected.setServerName(null);
        expected.setVoiceChat(true);


        // Act
        PartyDTO actual =target.create(partyDAO,slots,primeTimes);

        // Assert
        Assert.assertEquals("Penguins", actual.getName());
        Assert.assertEquals((Integer)18, actual.getAge());
        Assert.assertEquals("eng", actual.getLanguage());
        Assert.assertEquals(false, actual.getStrongLanguage());
        Assert.assertEquals(null, actual.getServersGroup());
        Assert.assertEquals(null, actual.getServerName());
        Assert.assertEquals(true, actual.getVoiceChat());
        Assert.assertEquals(true, actual.getChatListening());
        Assert.assertEquals(true, actual.getChatSpeaking());
        Assert.assertEquals("Skype", actual.getChatType());
        Assert.assertEquals(true, actual.getPvp());
        Assert.assertEquals(false, actual.getPve());
        Assert.assertEquals("3916-12-11T00:00:00.000Z", actual.getUpdateDate());
        Assert.assertEquals("3916-12-11T00:00:00.000Z", actual.getCreateDate());
        Assert.assertEquals(2, actual.getSlots().size());
        Assert.assertEquals(2,actual.getPrimeTimes().size());
    }

}