package main.java.tests;

import com.google.gson.Gson;
import main.java.Controllers.PartyController2;
import main.java.DTOs.FilterDTO;
import main.java.Repositories.PartyRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Tatka on 01.12.2016.
 */
public class PartyController2Test {

    private PartyController2 target;
    private PartyRepository repositoryMock;


    @Before
    public void setUp() {

        this.repositoryMock = mock(PartyRepository.class);
        this.target = new PartyController2(repositoryMock);

    }

    @Test
    public void findParty_ListFilter_CorrectClause() {

        List<FilterDTO> list = new ArrayList<>();
        FilterDTO filter1 = new FilterDTO("age", "18");
        FilterDTO filter2 = new FilterDTO("pve", "no");
        FilterDTO filter3 = new FilterDTO("pvp", "yes");
        list.add(filter1);
        list.add(filter2);
        list.add(filter3);
        Gson gson = new Gson();
        String s = gson.toJson(list);

        this.target.findParty(s);

        verify(repositoryMock).getPartiesByFilters("age LIKE '%18%' AND pve LIKE '%no%' AND pvp LIKE '%yes%'");

    }

}