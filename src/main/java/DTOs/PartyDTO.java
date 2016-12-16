package main.java.DTOs;


import main.java.DAOs.PartyDAO;
import main.java.DAOs.PrimeTimeDAO;
import main.java.DAOs.SlotDAO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    private Boolean strongLanguage;
    private String serversGroup;
    private String serverName;
    private Boolean voiceChat;
    private Boolean chatListening;
    private Boolean chatSpeaking;
    private String chatType;
    private Boolean pvp;
    private Boolean pve;
    private ArrayList<SlotDTO> slots;
    private ArrayList<PrimeTimeDTO> primeTimes;
    private String createDate;
    private String updateDate;
    private Long owner;

    public PartyDTO() {
    }

    public Long getOwner() {
        return owner;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
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

    public Boolean getStrongLanguage() {
        return strongLanguage;
    }

    public String getServersGroup() {
        return serversGroup;
    }

    public String getServerName() {
        return serverName;
    }

    public Boolean getVoiceChat() {
        return voiceChat;
    }

    public Boolean getChatListening() {
        return chatListening;
    }

    public Boolean getChatSpeaking() {
        return chatSpeaking;
    }

    public String getChatType() {
        return chatType;
    }

    public Boolean getPvp() {
        return pvp;
    }

    public Boolean getPve() {
        return pve;
    }

    public ArrayList<SlotDTO> getSlots() {
        return slots;
    }

    public ArrayList<PrimeTimeDTO> getPrimeTimes() {
        return primeTimes;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setStrongLanguage(Boolean strongLanguage) {
        this.strongLanguage = strongLanguage;
    }

    public void setServersGroup(String serversGroup) {
        this.serversGroup = serversGroup;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public void setVoiceChat(Boolean voiceChat) {
        this.voiceChat = voiceChat;
    }

    public void setChatListening(Boolean chatListening) {
        this.chatListening = chatListening;
    }

    public void setChatSpeaking(Boolean chatSpeaking) {
        this.chatSpeaking = chatSpeaking;
    }

    public void setChatType(String chatType) {
        this.chatType = chatType;
    }

    public void setPvp(Boolean pvp) {
        this.pvp = pvp;
    }

    public void setPve(Boolean pve) {
        this.pve = pve;
    }

    public void setSlots(ArrayList<SlotDTO> slots) {
        this.slots = slots;
    }

    public void setPrimeTimes(ArrayList<PrimeTimeDTO> primeTimes) {
        this.primeTimes = primeTimes;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
