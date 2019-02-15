package com.epam.geometry.read;

import com.epam.geometry.exceptions.WrongDataException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class FileReaderTest {
    private static final String TEST_DATA_FILE = "src/test/resources/FileReaderTestData.txt";
    private static final String WRONG_DATA_FILE_PATH = "wrong/path";
    private final Reader fileReader = new FileReader();

    @Test
    public void shouldReturnFourElementsListForCorrectFilePath() throws WrongDataException {
        List<String> expectedData = Arrays.asList("-10.0,1.0 1.0,1.0 3.0,1.0 1.0,-1.0",
                "-2.0,2.0 3.0,2.0 1.0,-1.0 -2.0,-1.0",
                "-1.0,1.0 1.0,-1.0 1.0,1.0 -1.0,-1.0",
                "-1.0,1.0 1.0,-1.0 -1.0,1.0 -1.0,-1.0");
        List<String> result = fileReader.read(TEST_DATA_FILE);
        Assert.assertEquals(expectedData, result);
    }

    @Test(expected = WrongDataException.class)
    public void shouldThrowWrongDataExceptionForWrongFilePath() throws WrongDataException {
        List<String> result = fileReader.read(WRONG_DATA_FILE_PATH);
    }
}
