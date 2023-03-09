package main.java;

import java.io.IOException;
import java.nio.file.Path;

public interface QuadraticEquationSolutionInterface {


    void solveFromFile(Path inputPath, Path outputPath) throws IOException;

    String solveFromString(String input);

}
