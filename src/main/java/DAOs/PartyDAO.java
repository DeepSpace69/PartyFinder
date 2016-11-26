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
    private String strongLanguage;

    @Column
    private String language;

    @Column
    private String serversGroup;

    @Column
    private String serverName;

    @Column
    private String voiceChat;

    @Column
    private String chatListening;

    @Column
    private String chatSpeaking;

    @Column
    private String chatType;

    @Column
    private String pvp;

    @Column
    private String pve;

    @Column
    private Integer timeZone;

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
        this.voiceChat  = partyDTO.getVoiceChat();
        this.chatListening = partyDTO.getChatListening();
        this.chatSpeaking = partyDTO.getChatSpeaking();
        this.strongLanguage = partyDTO.getStrongLanguage();
        this.language = partyDTO.getLanguage();
        this.serversGroup = partyDTO.getServersGroup();
        this.serverName = partyDTO.getServerName();
        this.timeZone = partyDTO.getTimeZone();
        this.createDate = new Date();
        this.updateDate = new Date();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getStrongLanguage() {
        return strongLanguage;
    }

    public String getLanguage() {
        return language;
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

    public Integer getTimeZone() {
        return timeZone;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }
}
