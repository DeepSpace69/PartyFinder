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

    @Column(name = "strong_language")
    private Boolean strongLanguage;

    @Column(name = "group_servers")
    private String groupServers;

    @Column(name = "server_name")
    private String serverName;

    @Column(name = "voice_chat")
    private Boolean voiceChat;

    @Column(name = "chat_listening")
    private Boolean chatListening;

    @Column(name = "chat_speaking")
    private Boolean chatSpeaking;

    @Column
    private Boolean pvp;

    @Column
    private Boolean pve;

     @Column(name = "create_date", insertable = false, updatable = false)
    private Date createDate;


    @Column(name = "update_date",insertable = false, updatable = false)
    private Date updateDate;

    public PartyDAO() {

    }

    public PartyDAO(PartyDTO partyDTO) {
        this.name = partyDTO.getName();
        this.age = partyDTO.getAge();
        this.pve = partyDTO.isPve();
        this.createDate = new Date();
        this.updateDate = new Date();
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isStrongLanguage() {
        return strongLanguage;
    }

    public String getGroupServers() {
        return groupServers;
    }

    public String getServerName() {
        return serverName;
    }

    public boolean isVoiceChat() {
        return voiceChat;
    }

    public boolean isChatListening() {
        return chatListening;
    }

    public boolean isChatSpeaking() {
        return chatSpeaking;
    }

    public boolean isPvp() {
        return pvp;
    }

    public boolean isPve() {
        return pve;
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
