//package main.java;
//
//import main.java.Core.OperationResult;
//import main.java.DTOs.PrimeTime;
//import main.java.DTOs.TimeOfDay;
//import main.java.DTOs.UserDTO;
//
//
//import main.java.Managers.PartyManager;
//import main.java.Managers.UserManager;
//import spark.*;
//import com.google.gson.Gson;
//
//import java.util.ArrayList;
//
//
//public class Manager {
//    private final Gson gson;
//    private UserManager userManager;
//    private PartyManager partyManager;
//
//
//    public Manager() {
//        DBInfoProvider dbInfoProvider = new DBInfoProvider();
//        this.userManager = new UserManager(dbInfoProvider);
//        this.gson = new Gson();
//        this.partyManager = new PartyManager(dbInfoProvider);
//    }
//
//
//    public String auth(Request req, Response res) {
//
//        UserDTO user = this.gson.fromJson(req.body(), UserDTO.class);
//        OperationResult operationResult = this.userManager.auth(user);
//        res.status(operationResult.getCode());
//        return this.gson.toJson(operationResult.getValue());
//    }
//
//    public String findParties2(Request req, Response res) {
//
//        OperationResult operationResult = this.partyManager.findParty();
//        res.status(operationResult.getCode());
//        return this.gson.toJson(operationResult.getValue());
//
//    }
//
//
//    public String findParties(Request req, Response res) {
//
//        String responseBody = "";
//        Gson gson = new Gson();
//
//
//        ArrayList<Party> parties = new ArrayList<>();
//        ArrayList<PrimeTime> primeTimes1 = new ArrayList<PrimeTime>();
//        primeTimes1.add(new PrimeTime(new TimeOfDay(12, 0), new TimeOfDay(20, 0), DaysEnum.Any, 1));
//        ArrayList<PrimeTime> primeTimes2 = new ArrayList<PrimeTime>();
//        primeTimes2.add(new PrimeTime(new TimeOfDay(19, 0), new TimeOfDay(22, 0), DaysEnum.Mon, 3));
//        primeTimes2.add(new PrimeTime(new TimeOfDay(20, 0), new TimeOfDay(23, 0), DaysEnum.Tue, 3));
//        ArrayList<PrimeTime> primeTimes3 = new ArrayList<PrimeTime>();
//        primeTimes3.add(new PrimeTime(new TimeOfDay(18, 0), new TimeOfDay(23, 0), DaysEnum.Any, 0));
//
//        parties.add(new Party("Penguins", primeTimes1, 20));
//        parties.add(new Party("Strawberries", primeTimes2, 15));
//        parties.add(new Party("Dragons", primeTimes3, 18));
//
//        responseBody = gson.toJson(parties);
//
//        return responseBody;
//    }
//}