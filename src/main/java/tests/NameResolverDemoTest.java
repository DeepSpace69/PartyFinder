package main.java.tests;

import main.java.Managers.NameResolverDemo;
import main.java.Managers.VocabularyManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NameResolverDemoTest {
    private NameResolverDemo target;

    @Before
    public void setUp() throws Exception {
        VocabularyManager vocabularyManager = mock(VocabularyManager.class);
        when(vocabularyManager.getIdByTypeAndValue("language", "RU")).thenReturn("language_RU");
        when(vocabularyManager.isInVocabulary("language")).thenReturn(true);
        this.target = new NameResolverDemo(vocabularyManager);
    }

    @Test
    public void testGetDAOValueName_SomeTypeAndValue_CorrectId() throws Exception {
        // Arrange
        String type = "language";
        String value = "RU";
        String expected = "language_RU";

        // Act
        String actual = this.target.getDAOValueName(type, value);

        // Assert
        Assert.assertEquals(expected, actual);

    }
}