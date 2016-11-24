//package main.java.Tests;
//
//import junit.framework.TestCase;
//import main.java.Core.OperationResult;
//import main.java.DAOs.UserDAO;
//import main.java.DBInfoProvider;
//import main.java.DTOs.UserDTO;
//import main.java.Managers.UserManager;
//
//import java.net.URISyntaxException;
//import java.sql.SQLException;
//
//import static org.mockito.Mockito.*;
//
///**
// * Created by Tatka on 18.11.2016.
// */
//public class UserManagerTest extends TestCase {
//
//    private UserManager target;
//
//    public void setUp() throws SQLException, URISyntaxException {
//        DBInfoProvider db = mock(DBInfoProvider.class);
//        UserDAO userDAO = new UserDAO();
//        userDAO.setPassword("1");
//        userDAO.setLogin("1");
//        userDAO.setName("Vasya");
//        when(db.getUser("1")).thenReturn(userDAO);
//        when(db.getUser("0")).thenReturn(null);
//
//        this.target = new UserManager(db);
//    }
//
//    public void test_auth_ValidUser_VasyaReturned() throws SQLException {
//
//        // Arrange
//        UserDTO user = new UserDTO();
//        user.setLogin("1");
//        user.setPassword("1");
//
//        // Act
//        OperationResult result = this.target.auth(user);
//
//        // Assert
//        assertEquals(200, result.getCode());
//        UserDTO actual = UserDTO.class.cast(result.getValue());
//        assertEquals("Vasya", actual.getName());
//        assertEquals("1", actual.getLogin());
//    }
//
//    public void test_auth_InvalidUser_ErrorMessageReturned(){
//
//        //Arrange
//        UserDTO user = new UserDTO();
//        user.setLogin("1");
//        user.setPassword("0");
//
//        //Act
//        OperationResult result = this.target.auth(user);
//
//        //Assert
//        assertEquals(401, result.getCode());
//
//    }
//
//    public void test_auth_AbsentUser_ErrorMessageReturned(){
//
//        //Arrange
//        UserDTO user = new UserDTO();
//        user.setLogin("0");
//        user.setPassword("1");
//
//        //Act
//        OperationResult result = this.target.auth(user);
//
//        //Assert
//        assertEquals(401, result.getCode());
//
//    }
//}
