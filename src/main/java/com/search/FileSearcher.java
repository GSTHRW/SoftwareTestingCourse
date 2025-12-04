package com.search;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * FileSearcher - A grep-like utility for searching patterns in files
 * 
 * 
 * Hej detta är ett test till labben!
 */
public class FileSearcher {
    
    /**
     * Search for a pattern in a file and return matching lines
     * 
     * @param pattern The pattern to search for
     * @param filePath The path to the file to search in
     * @param ignoreCase Whether to ignore case when matching
     * @return List of matching lines
     * @throws IOException if file cannot be read
     */
    public static List<String> search(String pattern, String filePath, boolean ignoreCase) throws IOException {
        List<String> matchingLines = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (matches(line, pattern, ignoreCase)) {
                    matchingLines.add(line);
                }
            }
        }
        
        return matchingLines;
    }
    
    /**
     * Check if a line matches the pattern
     * 
     * @param line The line to check
     * @param pattern The pattern to match
     * @param ignoreCase Whether to ignore case
     * @return true if the line matches the pattern
     */
    private static boolean matches(String line, String pattern, boolean ignoreCase) {
        if (ignoreCase) {
            return line.toLowerCase().contains(pattern.toLowerCase());
        }
        return line.contains(pattern);
    }
    
    /**
     * Print usage information
     */
    private static void printUsage() {
        System.out.println("Usage: search [-i] <pattern> <file>");
        System.out.println("  -i    Ignore case distinctions in patterns and data");
        System.out.println();
        System.out.println("Example: search cat demo.txt");
        System.out.println("Example: search -i Cat demo.txt");
    }
    
    /**
     * Main method - entry point for the program
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            printUsage();
            System.exit(1);
        }
        
        boolean ignoreCase = false;
        String pattern;
        String filePath;
        
        // Parse command line arguments
        if (args[0].equals("-i")) {
            if (args.length < 3) {
                printUsage();
                System.exit(1);
            }
            ignoreCase = true;
            pattern = args[1];
            filePath = args[2];
        } else {
            pattern = args[0];
            filePath = args[1];
        }
        
        try {
            List<String> matchingLines = search(pattern, filePath, ignoreCase);
            
            if (matchingLines.isEmpty()) {
                // No output if no matches found (grep-like behavior)
                System.exit(0);
            }
            
            for (String line : matchingLines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            System.exit(1);
        }
    }
}
