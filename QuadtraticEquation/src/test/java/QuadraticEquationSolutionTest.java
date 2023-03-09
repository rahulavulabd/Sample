package test.java;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class QuadraticEquationSolutionTest {

    @TempDir
    static Path tempDir;

    private final QuadraticEquationSolutionImpl solver = new QuadraticEquationSolutionImpl();

    @Test
    void solveFromFile_validInput_validOutput() throws IOException {
        // Create the input file
        String inputJson = "{\"a\": 1, \"b\": 0, \"c\": -1}";
        Path inputPath = tempDir.resolve("input.json");
        Files.writeString(inputPath, inputJson, StandardCharsets.UTF_8);

        // Create the expected output file
        String expectedOutput = "1.00,-1.00";
        Path expectedOutputPath = tempDir.resolve("expectedOutput.txt");
        Files.writeString(expectedOutputPath, expectedOutput, StandardCharsets.UTF_8);

        // Solve the quadratic equation and compare the output file with the expected output file
        Path outputPath = tempDir.resolve("output.txt");
        solver.solveFromFile(inputPath, outputPath);
        String output = Files.readString(outputPath, StandardCharsets.UTF_8);
        String expected = Files.readString(expectedOutputPath, StandardCharsets.UTF_8);
        assertEquals(expected, output);
    }

    @Test
    void solveFromString_validInput_validOutput() {
        // Create the input string
        String inputJson = "{\"a\": 1, \"b\": 0, \"c\": -1}";

        // Solve the quadratic equation and compare the output with the expected output
        String output = solver.solveFromString(inputJson);
        String expected = "1.00,-1.00";
        assertEquals(expected, output);
    }

    @Test
    void solveFromFile_invalidInput_invalidOutput() throws IOException {
        // Create the input file
        String inputJson = "{\"a\": 1, \"b\": \"0\", \"c\": -1}";
        Path inputPath = tempDir.resolve("input.json");
        Files.writeString(inputPath, inputJson, StandardCharsets.UTF_8);

        // Solve the quadratic equation and compare the output file with the expected output file
        Path outputPath = tempDir.resolve("output.txt");
        solver.solveFromFile(inputPath, outputPath);
        String output = Files.readString(outputPath, StandardCharsets.UTF_8);
        String expected = "Invalid input format";
        assertEquals(expected, output);
    }

    @Test
    void solveFromString_invalidInput_invalidOutput() {
        // Create the input string
        String inputJson = "{\"a\": 1, \"b\": \"0\", \"c\": -1}\"";

        // Solve the quadratic equation and compare the output with the expected output
        String output = solver.solveFromString(inputJson);
        String expected = "Invalid input format";
        assertEquals(expected, output);
    }
}

