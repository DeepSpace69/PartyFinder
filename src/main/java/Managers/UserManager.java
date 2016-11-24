//package main.java.Managers;
//
//import com.google.gson.Gson;
//import main.java.Core.OperationResult;
//import main.java.DAOs.UserDAO;
//import main.java.DBInfoProvider;
//import main.java.DTOs.UserDTO;
//
//import java.util.Objects;
//
///**
// * Created by Tatka on 21.11.2016.
// */
//public class UserManager {
//    private DBInfoProvider db;
//
//    public UserManager(DBInfoProvider dbInfoProvider) {
//
//        this.db = dbInfoProvider;
//    }
//
//    public OperationResult auth(UserDTO userDTO) {
//
//        OperationResult result = new OperationResult();
//        try {
//            UserDAO userDAO = db.getUser(userDTO.getLogin());
//            if (Objects.equals(userDTO.getPassword(), userDAO.getPassword())) {
//                result.setCode(200);
//                result.setValue(new UserDTO(userDAO));
//            } else {
//                result.setCode(401);
//                result.setValue("Invalid password");
//            }
//        } catch (Exception e) {
//            result.setCode(501);
//            result.setValue(e.getMessage());
//        }
//        return result;
//
//    }
//}
