package main.java.tests;

import main.java.Const.VocabularyTypes;
import main.java.Core.VocabularyLoader;
import main.java.DAOs.VocabularyDAO;
import main.java.Repositories.VocabularyRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Tatka on 28.11.2016.
 */
public class VocabularyLoaderTest {

    private main.java.Core.VocabularyLoader target;

    @Before
    public void setUp() throws Exception {
        VocabularyRepository repository = mock(VocabularyRepository.class);
        List<VocabularyDAO> vocs = new ArrayList<>();
        vocs.add(new VocabularyDAO("lang", "ru"));
        vocs.add(new VocabularyDAO("lang", "eng"));
        vocs.add(new VocabularyDAO("pvp", "yes"));
        vocs.add(new VocabularyDAO("pvp", "no"));
        when(repository.findAll()).thenReturn(vocs);
        this.target = new VocabularyLoader(repository);
    }

    @Test
    public void testGetVocabulary_ByLang_ReturnsRuEng() throws Exception {
        // Act
        List<String> actual = target.getVocabulary(VocabularyTypes.lang);
        String[] expected =  {"ru", "eng"};

        // Assert
        Assert.assertArrayEquals(expected, actual.toArray());

    }
}