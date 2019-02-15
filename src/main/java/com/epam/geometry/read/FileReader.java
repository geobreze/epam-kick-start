package com.epam.geometry.read;

import com.epam.geometry.exceptions.WrongDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader implements Reader {
    private static final Logger LOGGER = LogManager.getLogger(FileReader.class);


    public List<String> read(String source) throws WrongDataException {
        List<String> lines = new ArrayList<>();
        File inputFile = new File(source);
        try (Scanner scanner = new Scanner(inputFile)) {
            LOGGER.info("Begin reading file");
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            WrongDataException wrongDataException = new WrongDataException("File was not found", e);
            LOGGER.error(wrongDataException.getMessage(), wrongDataException);
            throw wrongDataException;
        }
        if (lines.isEmpty()) {
            LOGGER.warn("File has no valid lines");
        }
        return lines;
    }
}
