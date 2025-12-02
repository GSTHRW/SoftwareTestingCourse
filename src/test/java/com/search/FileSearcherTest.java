package com.search;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit tests for FileSearcher
 */
public class FileSearcherTest {
    
    private File testFile;
    
    @Before
    public void setUp() throws IOException {
        // Create a temporary test file
        testFile = File.createTempFile("test", ".txt");
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write("The cat sat on the mat.\n");
            writer.write("A dog barked at the cat.\n");
            writer.write("Cats are independent animals.\n");
            writer.write("The CAT climbed the tree.\n");
            writer.write("Dogs and cats can be friends.\n");
            writer.write("This line has no matches.\n");
        }
    }
    
    @After
    public void tearDown() {
        if (testFile != null && testFile.exists()) {
            testFile.delete();
        }
    }
    
    @Test
    public void testSearchCaseSensitive() throws IOException {
        List<String> results = FileSearcher.search("cat", testFile.getAbsolutePath(), false);
        
        assertEquals(3, results.size());
        assertTrue(results.contains("The cat sat on the mat."));
        assertTrue(results.contains("A dog barked at the cat."));
        assertTrue(results.contains("Dogs and cats can be friends."));
    }
    
    @Test
    public void testSearchCaseInsensitive() throws IOException {
        List<String> results = FileSearcher.search("cat", testFile.getAbsolutePath(), true);
        
        assertEquals(5, results.size());
        assertTrue(results.contains("The cat sat on the mat."));
        assertTrue(results.contains("A dog barked at the cat."));
        assertTrue(results.contains("Cats are independent animals."));
        assertTrue(results.contains("The CAT climbed the tree."));
        assertTrue(results.contains("Dogs and cats can be friends."));
    }
    
    @Test
    public void testSearchNoMatches() throws IOException {
        List<String> results = FileSearcher.search("elephant", testFile.getAbsolutePath(), false);
        
        assertEquals(0, results.size());
    }
    
    @Test
    public void testSearchPartialMatch() throws IOException {
        List<String> results = FileSearcher.search("dog", testFile.getAbsolutePath(), false);
        
        assertEquals(1, results.size());
        assertTrue(results.contains("A dog barked at the cat."));
    }
    
    @Test
    public void testSearchCaseInsensitiveDifferentCases() throws IOException {
        List<String> results = FileSearcher.search("CAT", testFile.getAbsolutePath(), true);
        
        assertEquals(5, results.size());
    }
    
    @Test(expected = IOException.class)
    public void testSearchFileNotFound() throws IOException {
        FileSearcher.search("test", "/nonexistent/file.txt", false);
    }
}
