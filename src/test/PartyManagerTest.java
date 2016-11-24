//package main.java.Tests;
//
//import junit.framework.Assert;
//import junit.framework.TestCase;
//import main.java.Core.OperationResult;
//import main.java.DBInfoProvider;
//import main.java.DTOs.PartyDTO;
//import main.java.Managers.PartyManager;
//
//import java.util.ArrayList;
//
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
///**
// * Created by Tatka on 23.11.2016.
// */
//public class PartyManagerTest extends TestCase {
//    PartyManager manager;
//
//    public void setUp() {
//        DBInfoProvider db = mock(DBInfoProvider.class);
//        ArrayList<PartyDTO> parties = new ArrayList<>();
//        parties.add(new PartyDTO("Dogs", 14, true));
//        parties.add(new PartyDTO("Cats", 21, false));
//        when(db.getParties()).thenReturn(parties);
//        this.manager = new PartyManager(db);
//
//    }
//
//    public void test_findParty_fineDbResponse_code200() {
//        //Arrange
//
//        //Act
//        OperationResult result = manager.findParty();
//        int actual = result.getCode();
//
//        //Assert
//        Assert.assertEquals(200, actual);
//    }
//
//
//
//}