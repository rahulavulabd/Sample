package main.java;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class QuadraticEquationSolutionImpl implements QuadraticEquationSolutionInterface {

    private final ObjectMapper objectMapper;

    public QuadraticEquationSolutionImpl() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void solveFromFile(Path inputPath, Path outputPath) throws IOException {
        // Read the input file content as a string
        String inputJson = Files.readString(inputPath, StandardCharsets.UTF_8);

        // Parse the input JSON as a JsonNode object
        JsonNode inputNode = objectMapper.readTree(inputJson);

        // Extract the a, b, c values from the input JSON
        double a = inputNode.get("a").asDouble();
        double b = inputNode.get("b").asDouble();
        double c = inputNode.get("c").asDouble();

        // Solve the quadratic equation and format the solutions as a string
        String solutions = solve(a, b, c);

        // Write the solutions string to the output file
        Files.writeString(outputPath, solutions, StandardCharsets.UTF_8);
    }

    @Override
    public String solveFromString(String input) {
        // Parse the input string as a JsonNode object
        JsonNode inputNode;
        try {
            inputNode = objectMapper.readTree(input);
        } catch (IOException e) {
            return "Invalid input format";
        }

        // Extract the a, b, c values from the input JSON
        double a = inputNode.get("a").asDouble();
        double b = inputNode.get("b").asDouble();
        double c = inputNode.get("c").asDouble();

        // Solve the quadratic equation and format the solutions as a string
        return solve(a, b, c);
    }

    private String solve(double a, double b, double c) {
        // Solve the quadratic equation
        double discriminant = b * b - 4 * a * c;
        if (discriminant > 0) {
            double x1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double x2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            return String.format("%.2f,%.2f", x1, x2);
        } else if (discriminant == 0) {
            double x = -b / (2 * a);
            return String.format("%.2f", x);
        } else {
            return "no solution";
        }
    }


}
