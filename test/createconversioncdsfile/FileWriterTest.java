/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package createconversioncdsfile;

import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rutger
 */
public class FileWriterTest {

    public FileWriterTest() {
    }

    /**
     * Test of writeLine method, of class FileWriter.
     */
    @Test
    public void testWriteLine() throws Exception {
        System.out.println("writeLine");
        String current = new java.io.File(".").getCanonicalPath();
        String newPath = current + "\\test\\createconversioncdsfile\\testFiles\\testfile2.txt";
        FileWriter instance = new FileWriter();
        instance.OpenFile(newPath);
        String line = "zeehond";
        instance.writeLine(line);
        instance.CloseFile();
        FileReader read = new FileReader(Paths.get(newPath));
        read.nextLine();
        String result = read.getLine();
        String expectedResult = "zeehond";
        assertEquals(expectedResult, result);
        Files.delete(Paths.get(newPath));
    }
}