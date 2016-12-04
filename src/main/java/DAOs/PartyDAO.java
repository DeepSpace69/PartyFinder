package main.java.DAOs;


import main.java.DTOs.PartyDTO;


import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Tatka on 22.11.2016.
 */
@Entity
@Table(name = "parties")
public class PartyDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Integer age;

    @Column
    private Boolean strongLanguage;

    @Column
    private String language;

    @Column
    private String serversGroup;

    @Column
    private String serverName;

    @Column
    private Boolean voiceChat;

    @Column
    private Boolean chatListening;

    @Column
    private Boolean chatSpeaking;

    @Column
    private String chatType;

    @Column
    private Boolean pvp;

    @Column
    private Boolean pve;

    @Column(name = "create_date", insertable = false, updatable = false)
    private Date createDate;


    @Column(name = "update_date", insertable = false, updatable = false)
    private Date updateDate;

    public PartyDAO() {

    }

    public PartyDAO(PartyDTO partyDTO) {
        this.name = partyDTO.getName();
        this.age = partyDTO.getAge();
        this.pve = partyDTO.getPve();
        this.pvp = partyDTO.getPvp();
        this.chatType = partyDTO.getChatType();
        this.voiceChat = partyDTO.getVoiceChat();
        this.chatListening = partyDTO.getChatListening();
        this.chatSpeaking = partyDTO.getChatSpeaking();
        this.strongLanguage = partyDTO.getStrongLanguage();
        this.language = partyDTO.getLanguage();
        this.serversGroup = partyDTO.getServersGroup();
        this.serverName = partyDTO.getServerName();
        //this.timeZone = partyDTO.getTimeZone();
        this.createDate = new Date();
        this.updateDate = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getStrongLanguage() {
        return strongLanguage;
    }

    public void setStrongLanguage(Boolean strongLanguage) {
        this.strongLanguage = strongLanguage;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getServersGroup() {
        return serversGroup;
    }

    public void setServersGroup(String serversGroup) {
        this.serversGroup = serversGroup;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public Boolean getVoiceChat() {
        return voiceChat;
    }

    public void setVoiceChat(Boolean voiceChat) {
        this.voiceChat = voiceChat;
    }

    public Boolean getChatListening() {
        return chatListening;
    }

    public void setChatListening(Boolean chatListening) {
        this.chatListening = chatListening;
    }

    public Boolean getChatSpeaking() {
        return chatSpeaking;
    }

    public void setChatSpeaking(Boolean chatSpeaking) {
        this.chatSpeaking = chatSpeaking;
    }

    public String getChatType() {
        return chatType;
    }

    public void setChatType(String chatType) {
        this.chatType = chatType;
    }

    public Boolean getPvp() {
        return pvp;
    }

    public void setPvp(Boolean pvp) {
        this.pvp = pvp;
    }

    public Boolean getPve() {
        return pve;
    }

    public void setPve(Boolean pve) {
        this.pve = pve;
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
