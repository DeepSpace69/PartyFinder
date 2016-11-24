package main.java.DAOs;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
    private int id;

    @Column
    private String name;

    @Column
    private int age;

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

//    @Column(name = "create_date")
//    private Date createDate;
//
//    @Column(name = "update_date")
//    private Date updateDate;

    public PartyDAO()
    {

    }

    public PartyDAO(ResultSet rs) throws SQLException, ParseException {

        this.id = rs.getInt("id");
        this.name = rs.getString("name");
        this.age = Integer.parseInt(rs.getString("age"));
        this.strongLanguage = Boolean.parseBoolean(rs.getString("strongLanguage"));
        this.groupServers = rs.getString("groupServer");
        this.serverName = rs.getString("serverName");
        this.voiceChat = Boolean.parseBoolean(rs.getString("voiceChat"));
        this.chatListening = Boolean.parseBoolean(rs.getString("chatListening"));
        this.chatSpeaking = Boolean.parseBoolean(rs.getString("chatSpeaking"));
        this.pvp = Boolean.parseBoolean(rs.getString("pvp"));
        this.pve = Boolean.parseBoolean(rs.getString("pve"));
        DateFormat format = new SimpleDateFormat("MMMM d, yyyy");
//        this.createDate = format.parse(rs.getString("createDate"));
//        this.updateDate = format.parse(rs.getString("updateDate"));
    }

    public int getId() {
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
//
//    public Date getCreateDate() {
//        return createDate;
//    }
//
//    public Date getUpdateDate() {
//        return updateDate;
//    }
}
