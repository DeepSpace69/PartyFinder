//package main.java;
//
//import java.net.URISyntaxException;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.text.ParseException;
//import java.util.ArrayList;
//import java.util.Collection;
//
//
//import com.heroku.sdk.jdbc.DatabaseUrl;
//import main.java.DAOs.PartyDAO;
//import main.java.DAOs.SlotDAO;
//import main.java.DAOs.UserDAO;
//import main.java.DTOs.PartyDTO;
//
//
///**
// * Created by Tatka on 18.11.2016.
// */
//public class DBInfoProvider {
//
//
//    public DBInfoProvider() {
//
//    }
//
//
//    public UserDAO getUser(String login) throws SQLException, URISyntaxException {
//
//        UserDAO result = null;
//        Connection connection = DatabaseUrl.extract().getConnection();
//        Statement stmt = connection.createStatement();
//        ResultSet rs = stmt.executeQuery("SELECT * FROM users u WHERE u.login = '" + login + "'");
//        if (rs.next()) {
//            result = new UserDAO(rs);
//        }
//        return result;
//    }
//
//    public Collection<UserDAO> getUsers() throws SQLException, URISyntaxException {
//
//        ArrayList<UserDAO> result = new ArrayList<>();
//        Connection connection = DatabaseUrl.extract().getConnection();
//        Statement stmt = connection.createStatement();
//        ResultSet rs = stmt.executeQuery("SELECT * FROM users;");
//
//        while (rs.next()) {
//            result.add(new UserDAO(rs));
//        }
//
//        return result;
//    }
//
//
//    public ArrayList<PartyDAO> getParties() throws SQLException, URISyntaxException, ParseException {
//
//        ArrayList<PartyDAO> parties = new ArrayList<>();
//        Connection connection = DatabaseUrl.extract().getConnection();
//        Statement stmt = connection.createStatement();
//        ResultSet rs = stmt.executeQuery("SELECT * FROM parties;");
//
//        while (rs.next()) {
//            parties.add(new PartyDAO(rs));
//        }
//        return parties;
//    }
//
//    public ArrayList<SlotDAO> getSlots(int idParty) throws SQLException, URISyntaxException {
//
//        ArrayList<SlotDAO> slots = new ArrayList<>();
//        Connection connection = DatabaseUrl.extract().getConnection();
//        Statement stmt = connection.createStatement();
//        ResultSet rs = stmt.executeQuery("SELECT * FROM slots WHERE fk_party =" + idParty + ";");
//
//        while (rs.next()) {
//            slots.add(new SlotDAO(rs));
//        }
//        return slots;
//    }
//    public ArrayList<SlotDAO> getSlots(String ids) throws SQLException, URISyntaxException {
//
//        ArrayList<main.java.DAOs.SlotDAO> slots = new ArrayList<>();
//        Connection connection = DatabaseUrl.extract().getConnection();
//        Statement stmt = connection.createStatement();
//        ResultSet rs = stmt.executeQuery("SELECT * FROM slots WHERE fk_party in (" + idParty + ";");
//
//        while (rs.next()) {
//            slots.add(new main.java.DAOs.SlotDAO(rs));
//        }
//        return slots;
//    }
//
//
//
//
//}
