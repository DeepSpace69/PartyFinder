package main.java.tests;

import com.google.gson.Gson;
import main.java.Controllers.PartyController2;
import main.java.DTOs.FilterDTO;
import main.java.Managers.FilterFactory;
import main.java.Repositories.PartyRepository;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Tatka on 01.12.2016.
 */
public class PartyController2Test {
    private PartyController2 target;
    private PartyRepository repositoryMock;
    private HttpServletResponse responseMock;


    @Before
    public void setUp() {
        this.repositoryMock = mock(PartyRepository.class);
        this.responseMock = mock(HttpServletResponse.class);
        this.target = new PartyController2(repositoryMock);
    }

    @Test
    public void findParty_ListFilter_CorrectClause() {
        // Arrange
        List<FilterDTO> list = new ArrayList<>();
        list.add(new FilterDTO("age", "18"));
        //list.add(new FilterDTO("pve", "no"));
        //list.add(new FilterDTO("pvp", "yes"));
        list.add(new FilterDTO("chat_type", "chatType_Skype"));
        list.add(new FilterDTO("chat_type", "chatType_TeamSpeak"));

        Gson gson = new Gson();
        String s = gson.toJson(list);

        // Act
        this.target.findParty(s, responseMock);

        //Assert
        verify(repositoryMock).getPartiesByFilters("(age LIKE '%18%') AND (chat_type = '%chatType_Skype%' OR chat_type ='%chatType_TeamSpeak%')");

    }

    private String makeDaoType(String dtoType)
    {
        // return this.voc.getIdByType(dtoType);
        return null;
    }

}