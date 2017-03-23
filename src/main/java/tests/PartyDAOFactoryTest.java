//package main.java.tests;
//
//import main.java.DAOs.PartyDAO;
//import main.java.DTOs.PartyDTO;
//import main.java.Factories.PartyDAOFactory;
//import main.java.Managers.*;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
///**
// * Created by Tatka on 07.12.2016.
// */
//public class PartyDAOFactoryTest {
//
//    private PartyDAOFactory target;
//
//    @Before
//    public void setUp() throws Exception {
//        VocabularyManager vocabularyManager = mock(VocabularyManager.class);
//        when(vocabularyManager.getIdByTypeAndValue("lang","eng")).thenReturn("lang_eng");
//        when(vocabularyManager.getIdByTypeAndValue("chatType", "Skype")).thenReturn("chatType_Skype");
//        when(vocabularyManager.getIdByTypeAndValue("serverName", null)).thenReturn("serverName_null");
//        when(vocabularyManager.getIdByTypeAndValue("serversGroup", null)).thenReturn("serversGroup_null");
//        this.target = new PartyDAOFactory(vocabularyManager);
//    }
//
//    @Test
//    public void create_DAOs_CorrectPartyDAO() {
//        // Arrange
//        PartyDTO partyDTO = new PartyDTO();
//        partyDTO.setAge(20);
//        partyDTO.setStrongLanguage(false);
//        partyDTO.setLanguage("eng");
//        partyDTO.setServerName(null);
//        partyDTO.setServersGroup(null);
//        partyDTO.setChatListening(true);
//        partyDTO.setChatSpeaking(false);
//        partyDTO.setChatType("Skype");
//        partyDTO.setPvp(true);
//        partyDTO.setPve(true);
//
//        // Act
//        PartyDAO actual = this.target.create(partyDTO);
//
//        // Assert
//        Assert.assertEquals((Integer) 20, actual.getAge());
//        Assert.assertEquals(false, actual.getStrongLanguage());
//        Assert.assertEquals("lang_eng", actual.getLanguage());
//        Assert.assertEquals("serverName_null", actual.getServerName());
//        Assert.assertEquals("serversGroup_null", actual.getServersGroup());
//        Assert.assertEquals(true, actual.getChatListening());
//        Assert.assertEquals(false, actual.getChatSpeaking());
//        Assert.assertEquals("chatType_Skype", actual.getChatType());
//        Assert.assertEquals(true, actual.getPve());
//        Assert.assertEquals(true, actual.getPvp());
//    }
//}