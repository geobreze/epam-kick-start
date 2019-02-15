package com.epam.geometry;

import com.epam.geometry.exceptions.IllegalParseException;
import com.epam.geometry.exceptions.WrongDataException;
import com.epam.geometry.generation.IdGenerator;
import com.epam.geometry.model.Figure;
import com.epam.geometry.model.Point2D;
import com.epam.geometry.model.Quadrilateral;
import com.epam.geometry.parse.FigureParser;
import com.epam.geometry.read.Reader;
import com.epam.geometry.validation.FigureValidator;
import com.epam.geometry.validation.FormatValidator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QuadrilateralDirectorTest {
    private static final IdGenerator ID_GENERATOR = mock(IdGenerator.class);
    private static final String FILE_PATH = "src/test/resources/DirectorMixedTestData.txt";
    private static final FormatValidator FORMAT_VALIDATOR = mock(FormatValidator.class);
    private static final FigureValidator FIGURE_VALIDATOR = mock(FigureValidator.class);
    private static final FigureParser PARSER = mock(FigureParser.class);
    private static final Reader READER = mock(Reader.class);
    private static final List<Figure> EXPECTED = Arrays.asList(new Quadrilateral(
            ID_GENERATOR,
            new Point2D(-2,2),
            new Point2D(3,2),
            new Point2D(1,-1),
            new Point2D(-2,-1)));
    private final QuadrilateralDirector director = new QuadrilateralDirector(FORMAT_VALIDATOR, FIGURE_VALIDATOR, READER, PARSER);

    @BeforeClass
    public static void initMockitos() throws WrongDataException {
        List<String> data = Arrays.asList(
                "-10.0,1.0a 1.0,1.0 3.0,1.0 1.0,-1.0",
                "-2.0,2.0 3.0,2.0 1.0,-1.0 -2.0,-1.0",
                "-1.0,1.0 1.0,-1.0 1.0,1.0",
                "-1.0,1.0 3.0,1.0 0.0,1.0 -1.0, 2.0"
        );
        IllegalParseException parseException = new IllegalParseException("Invalid string was supplied");
        when(READER.read(FILE_PATH)).thenReturn(data);
        when(FORMAT_VALIDATOR.validate(anyString())).thenReturn(false).thenReturn(true).thenReturn(false).thenReturn(true);

        when(FIGURE_VALIDATOR.validate(data.get(0))).thenThrow(parseException);
        when(FIGURE_VALIDATOR.validate(data.get(1))).thenReturn(true);
        when(FIGURE_VALIDATOR.validate(data.get(2))).thenReturn(false);
        when(FIGURE_VALIDATOR.validate(data.get(3))).thenReturn(false);

        when(PARSER.parse(data.get(0))).thenThrow(parseException);
        when(PARSER.parse(data.get(2))).thenThrow(parseException);
        when(PARSER.parse(data.get(3))).thenThrow(parseException);

        when(PARSER.parse(data.get(1))).thenReturn(EXPECTED.get(0));
    }

    @Test
    public void processShouldReturnCorrectListWhenMixedDataSupplied() {
        List<Figure> actual = director.process(FILE_PATH);
        Assert.assertEquals(EXPECTED, actual);
    }
}
