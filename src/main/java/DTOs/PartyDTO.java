package main.java.DTOs;


import main.java.DAOs.PartyDAO;
import main.java.DAOs.PrimeTimeDAO;
import main.java.DAOs.SlotDAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by Tatka on 22.11.2016.
 */
public class PartyDTO {

    private String name;
    private Integer age;
    //private Integer timeZone;
    private String language;
    private String strongLanguage;
    private String serversGroup;
    private String serverName;
    private String voiceChat;
    private String chatListening;
    private String chatSpeaking;
    private String chatType;
    private String pvp;
    private String pve;
    private ArrayList<SlotDTO> slots;
    private ArrayList<PrimeTimeDTO> primeTimes;
    private Date createDate;
    private Date updateDate;


    public PartyDTO(PartyDAO party, List<SlotDAO> slots, List<PrimeTimeDAO> primeTimes) {
        this.name = party.getName();
        this.age = party.getAge();
        this.pve = party.getPve();
        this.pvp = party.getPvp();
        this.chatType = party.getChatType();
        this.voiceChat = party.getVoiceChat();
        this.chatListening = party.getChatListening();
        this.chatSpeaking = party.getChatSpeaking();
        this.strongLanguage = party.getStrongLanguage();
        this.language = party.getLanguage();
        this.serversGroup = party.getServersGroup();
        this.serverName = party.getServerName();
        this.createDate = new Date();
        this.updateDate = new Date();
        this.slots = new ArrayList<>();
        for (SlotDAO slot : slots) {
            if (Objects.equals(slot.getFkParty(), party.getId())) {
                this.slots.add(new SlotDTO(slot));
            }
        }
        this.primeTimes = new ArrayList<>();
        for (PrimeTimeDAO primeTime : primeTimes) {
            if (Objects.equals(primeTime.getFkParty(), party.getId())) {
                this.primeTimes.add(new PrimeTimeDTO(primeTime));
            }
        }
       // todo: test
    }


    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getLanguage() {
        return language;
    }

    public String getStrongLanguage() {
        return strongLanguage;
    }

    public String getServersGroup() {
        return serversGroup;
    }

    public String getServerName() {
        return serverName;
    }

    public String getVoiceChat() {
        return voiceChat;
    }

    public String getChatListening() {
        return chatListening;
    }

    public String getChatSpeaking() {
        return chatSpeaking;
    }

    public String getChatType() {
        return chatType;
    }

    public String getPvp() {
        return pvp;
    }

    public String getPve() {
        return pve;
    }

    public ArrayList<SlotDTO> getSlots() {
        return slots;
    }

    public ArrayList<PrimeTimeDTO> getPrimeTimes() {
        return primeTimes;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }
}
