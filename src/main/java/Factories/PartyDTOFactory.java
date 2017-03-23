package main.java.Factories;

import main.java.DAOs.PartyDAO;
import main.java.DAOs.PrimeTimeDAO;
import main.java.DAOs.SlotDAO;
import main.java.DTOs.PartyDTO;
import main.java.DTOs.PrimeTimeDTO;
import main.java.DTOs.SlotDTO;
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
public class PartyDTOFactory {
    private VocabularyManager vocabulary;
    private SlotDTOFactory slotDTOFactory;
    private PrimeTimeDTOFactory primeTimeDTOFactory;

    @Autowired
    public PartyDTOFactory(VocabularyManager vocabularyManager, SlotDTOFactory slotDTOFactory, PrimeTimeDTOFactory primeTimeDTOFactory) {
        this.vocabulary = vocabularyManager;
        this.slotDTOFactory = slotDTOFactory;
        this.primeTimeDTOFactory = primeTimeDTOFactory;
    }

    public PartyDTO create(PartyDAO party) {
        PartyDTO result = new PartyDTO();
        result.setId(party.getId());
        result.setName(party.getName());
        result.setAge(party.getAge());
        result.setPve(party.getPve());
        result.setPvp(party.getPvp());
        result.setChatType(this.vocabulary.getValueById(party.getChatType()));
        result.setChatListening(party.getChatListening());
        result.setChatSpeaking(party.getChatSpeaking());
        result.setStrongLanguage(party.getStrongLanguage());
        result.setLanguage(this.vocabulary.getValueById(party.getLanguage()));
        result.setServersGroup(this.vocabulary.getValueById(party.getServersGroup()));
        result.setServerName(this.vocabulary.getValueById(party.getServerName()));
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        result.setCreateDate(df.format(party.getCreateDate()));
        result.setUpdateDate(df.format(party.getUpdateDate()));
        result.setUser(party.getFkUser());
        if (party.getSlotDAOs() == null) {
            result.setSlots(null);
        } else {
            ArrayList<SlotDTO> resultSlots = new ArrayList<>();
            for (SlotDAO slot : party.getSlotDAOs()) {
                if (Objects.equals(slot.getFkParty(), party.getId())) {
                    resultSlots.add(this.slotDTOFactory.create(slot));
                }
            }
            result.setSlots(resultSlots);
        }
        if (party.getPrimeTimeDAOs() == null) {
            result.setPrimeTimes(null);
        } else {
            ArrayList<PrimeTimeDTO> resultPrimeTimes = new ArrayList<>();
            for (PrimeTimeDAO primeTime : party.getPrimeTimeDAOs()) {
                if (Objects.equals(primeTime.getFkParty(), party.getId())) {
                    resultPrimeTimes.add(this.primeTimeDTOFactory.create(primeTime));
                }
            }
            result.setPrimeTimes(resultPrimeTimes);
        }
        return result;
    }

}
