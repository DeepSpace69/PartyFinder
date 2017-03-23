package main.java.tests;

import main.java.Const.VocabularyTypes;
import main.java.Managers.VocabularyManager;
import main.java.DAOs.VocabularyDAO;
import main.java.Repositories.VocabularyRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Tatka on 28.11.2016.
 */
public class VocabularyManagerTest {

    private VocabularyManager target;

    @Before
    public void setUp() throws Exception {
        VocabularyRepository repository = mock(VocabularyRepository.class);
        List<VocabularyDAO> vocs = new ArrayList<>();
        vocs.add(new VocabularyDAO("lang_ru", "language", "ru"));
        vocs.add(new VocabularyDAO("lang_eng", "language", "eng"));
        vocs.add(new VocabularyDAO("sex_female", "sex", "female"));
        vocs.add(new VocabularyDAO("sex_male", "sex", "male"));
        when(repository.findAll()).thenReturn(vocs);
        this.target = new VocabularyManager(repository);
    }

    @Test
    public void testGetVocabulary_ByLang_ReturnsRuEng() throws Exception {
        // Act
        Set<String> actual = target.getVocabulary(VocabularyTypes.lang);
        String[] expected = {"ru", "eng"};

        // Assert
        Assert.assertArrayEquals(expected, actual.toArray());
    }

    @Test
    public void test_GetValueById_SomeId_CorrectValue() {
        // Act
        String actual = this.target.getValueById("lang_eng");
        String expected = "eng";

        // Assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_getIdByValueAndType_SomeValueAndType_CorrectId() {
        // Act
        String actual = this.target.getIdByTypeAndValue("sex", "female");
        String expected = "sex_female";

        // Assert
        Assert.assertEquals(expected, actual);
    }
}