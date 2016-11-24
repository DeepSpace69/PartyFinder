//package main.java.Managers;
//
//import main.java.*;
//import main.java.Core.OperationResult;
//import main.java.DAOs.PartyDAO;
//import main.java.DAOs.SlotDAO;
//import main.java.DTOs.PartyDTO;
//
//import java.util.ArrayList;
//
///**
// * Created by Tatka on 22.11.2016.
// */
//public class PartyManager {
//    private DBInfoProvider db;
//
//    public PartyManager(DBInfoProvider db) {
//        this.db = db;
//    }
//
//    public OperationResult findParty() {
//
//        OperationResult result = new OperationResult();
//        try {
//            ArrayList<PartyDAO> partiesFromDB = db.getParties();
//            //ArrayList<PartyDTO> partiesForClient = new ArrayList<>();
//            String ids = null;
//            for (PartyDAO party : partiesFromDB) {
//                ids = ids + "," + party.getId();
//            }
//            ids = ids.substring(1);
//            ArrayList<SlotDAO> slots = db.getSlots(ids);
//            result.setCode(200);
//            result.setValue(partiesForClient);
//        } catch (Exception e) {
//            result.setCode(501);
//            result.setValue(e.getMessage());
//        }
//        return result;
//    }
//}
