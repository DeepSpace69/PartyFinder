package main.java.DAOs;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Tatka on 22.11.2016.
 */
public class PartyDAO {
    private int id;
    private String name;
    private int age;
    private boolean strongLanguage;
    private String groupServer;
    private String severName;
    private boolean voiceChat;
    private boolean chatListening;
    private boolean chatSpeaking;
    private boolean pvp;
    private boolean pve;
    private Date createDate;
    private Date updateDate;

    public PartyDAO(ResultSet rs) throws SQLException, ParseException {

        this.id = rs.getInt("id");
        this.name = rs.getString("name");
        this.age = Integer.parseInt(rs.getString("age"));
        this.strongLanguage = Boolean.parseBoolean(rs.getString("strongLanguage"));
        this.groupServer = rs.getString("groupServer");
        this.severName = rs.getString("severName");
        this.voiceChat = Boolean.parseBoolean(rs.getString("voiceChat"));
        this.chatListening = Boolean.parseBoolean(rs.getString("chatListening"));
        this.chatSpeaking = Boolean.parseBoolean(rs.getString("chatSpeaking"));
        this.pvp = Boolean.parseBoolean(rs.getString("pvp"));
        this.pve = Boolean.parseBoolean(rs.getString("pve"));
        DateFormat format = new SimpleDateFormat("MMMM d, yyyy");
        this.createDate = format.parse(rs.getString("createDate"));
        this.updateDate = format.parse(rs.getString("updateDate"));
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

    public String getGroupServer() {
        return groupServer;
    }

    public String getSeverName() {
        return severName;
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

    public Date getUpdateDate() {
        return updateDate;
    }
}
