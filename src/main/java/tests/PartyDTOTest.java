package main.java.tests;

import main.java.DAOs.PartyDAO;
import main.java.DAOs.PrimeTimeDAO;
import main.java.DAOs.SlotDAO;
import main.java.DTOs.PartyDTO;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Tatka on 02.12.2016.
 */
public class PartyDTOTest {

    @Test
    public void constructorPartyDTO_DAOs_CorrectPartyDTO() {
        // Arrange
        PartyDAO partyDAO = new PartyDAO();
        partyDAO.setName("Penguins");
        partyDAO.setAge(18);
        partyDAO.setChatListening(true);
        partyDAO.setChatSpeaking(true);
        partyDAO.setChatType("skype");
        partyDAO.setLanguage("eng");
        partyDAO.setPve(false);
        partyDAO.setPvp(true);
        partyDAO.setServersGroup("First");
        partyDAO.setServerName("Peace");
        partyDAO.setStrongLanguage(false);
        partyDAO.setCreateDate(new Date(2016, 11, 11));
        partyDAO.setUpdateDate(new Date(2016, 11, 11));
        partyDAO.setId(1l);

        List<SlotDAO> slots = new ArrayList<>();

        SlotDAO slotDAO1 = new SlotDAO();
        slotDAO1.setClassType("gunslinger");
        slotDAO1.setRole("DD");
        slotDAO1.setExisting(false);
        slotDAO1.setLinkCharacter("someLink");
        slotDAO1.setSex("male");
        slotDAO1.setId(1l);
        slotDAO1.setFkParty(1l);

        SlotDAO slotDAO2 = new SlotDAO();
        slotDAO2.setClassType("occultist");
        slotDAO2.setRole("healer");
        slotDAO2.setExisting(false);
        slotDAO2.setLinkCharacter("someLink");
        slotDAO2.setSex("male");
        slotDAO2.setId(2l);
        slotDAO2.setFkParty(1l);

        SlotDAO slotDAO3 = new SlotDAO();
        slotDAO3.setClassType("swordmage");
        slotDAO3.setRole("DD");
        slotDAO3.setExisting(false);
        slotDAO3.setLinkCharacter("someLink");
        slotDAO3.setSex("female");
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
        primeTimeDAO1.setDay("mon");
        primeTimeDAO1.setTimeZone(1);
        primeTimeDAO1.setFkParty(1l);
        primeTimeDAO1.setId(1l);

        PrimeTimeDAO primeTimeDAO2 = new PrimeTimeDAO();
        primeTimeDAO2.setStartHour(20);
        primeTimeDAO2.setStartMinute(0);
        primeTimeDAO2.setEndHour(0);
        primeTimeDAO2.setEndMinute(30);
        primeTimeDAO2.setDay("wed");
        primeTimeDAO2.setTimeZone(1);
        primeTimeDAO2.setFkParty(1l);
        primeTimeDAO2.setId(2l);

        PrimeTimeDAO primeTimeDAO3 = new PrimeTimeDAO();
        primeTimeDAO3.setStartHour(20);
        primeTimeDAO3.setStartMinute(0);
        primeTimeDAO3.setEndHour(0);
        primeTimeDAO3.setEndMinute(30);
        primeTimeDAO3.setDay("fri");
        primeTimeDAO3.setTimeZone(1);
        primeTimeDAO3.setFkParty(2l);
        primeTimeDAO3.setId(3l);

        primeTimes.add(primeTimeDAO1);
        primeTimes.add(primeTimeDAO2);
        primeTimes.add(primeTimeDAO3);

        // Act
        PartyDTO partyDTO = new PartyDTO(partyDAO,slots,primeTimes);

        // Assert
        Assert.assertEquals(partyDAO.getName(), partyDTO.getName());
    }

}