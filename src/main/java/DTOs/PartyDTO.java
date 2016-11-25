package main.java.DTOs;


import main.java.DAOs.PartyDAO;
import main.java.DAOs.SlotDAO;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Tatka on 22.11.2016.
 */
public class PartyDTO {

    private String name;
    private int age;
    private int timeZone; // not done
    private String  languages;
    private boolean strongLanguage;
    private String groupServer;
    private String serverName;
    private boolean voiceChat;
    private boolean chatListening;
    private boolean chatSpeaking;
    private String chatType; // not done
    private boolean pvp;
    private boolean pve;
    private ArrayList<SlotDTO> slots;
    private ArrayList<PrimeTime> primeTimes;
    private Date createDate;
    private Date updateDate;

    public PartyDTO(String name, int age, boolean strongLanguage) {
        this.name = name;
        this.age = age;
        this.strongLanguage = strongLanguage;
    }

    public PartyDTO(PartyDAO party, ArrayList<SlotDAO> slots) {
        this.age = party.getAge();
        this.strongLanguage = party.isStrongLanguage();
        this.groupServer = party.getGroupServers();
        this.serverName = party.getServerName();
        this.voiceChat = party.isVoiceChat();
        this.chatListening = party.isChatListening();
        this.chatSpeaking = party.isChatSpeaking();
        this.pvp = party.isPvp();
        this.pve = party.isPve();
       // this.slots = slots;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(int timeZone) {
        this.timeZone = timeZone;
    }

        public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public boolean isStrongLanguage() {
        return strongLanguage;
    }

    public void setStrongLanguage(boolean strongLanguage) {
        this.strongLanguage = strongLanguage;
    }

    public String getGroupServer() {
        return groupServer;
    }

    public void setGroupServer(String groupServer) {
        this.groupServer = groupServer;
    }

    public String getSeverName() {
        return serverName;
    }

    public void setSeverName(String severName) {
        this.serverName = severName;
    }

    public boolean isVoiceChat() {
        return voiceChat;
    }

    public void setVoiceChat(boolean voiceChat) {
        this.voiceChat = voiceChat;
    }

    public boolean isChatListening() {
        return chatListening;
    }

    public void setChatListening(boolean chatListening) {
        this.chatListening = chatListening;
    }

    public boolean isChatSpeaking() {
        return chatSpeaking;
    }

    public void setChatSpeaking(boolean chatSpeaking) {
        this.chatSpeaking = chatSpeaking;
    }

    public String getChatType() {
        return chatType;
    }

    public void setChatType(String chatType) {
        this.chatType = chatType;
    }

    public boolean isPvp() {
        return pvp;
    }

    public void setPvp(boolean pvp) {
        this.pvp = pvp;
    }

    public boolean isPve() {
        return pve;
    }

    public void setPve(boolean pve) {
        this.pve = pve;
    }

    public ArrayList<SlotDTO> getSlots() {
        return slots;
    }

    public void setSlots(ArrayList<SlotDTO> slots) {
        this.slots = slots;
    }

    public ArrayList<PrimeTime> getPrimeTimes() {
        return primeTimes;
    }

    public void setPrimeTimes(ArrayList<PrimeTime> primeTimes) {
        this.primeTimes = primeTimes;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
