/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package createconversioncdsfile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rutger
 */
public class FileReaderTest {
    
    public FileReaderTest() {
    }
    /**
     * Test of nextLine method, of class FileReader.
     * @throws java.lang.Exception
     */
    @Test
    public void testNextLine() throws Exception {
        System.out.println("nextLine");
        String current = new java.io.File( "." ).getCanonicalPath();
        Path path = Paths.get(current + "\\test\\createconversioncdsfile\\testFiles\\testfile.txt");
        FileReader instance = new FileReader(path);
        Boolean expResult = true;
        Boolean result = instance.nextLine();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLine method, of class FileReader.
     * @throws java.io.IOException
     */
    @Test
    public void testGetLine() throws IOException {
        System.out.println("getLine");
        String current = new java.io.File( "." ).getCanonicalPath();
        Path path = Paths.get(current + "\\test\\createconversioncdsfile\\testFiles\\testfile.txt");
        FileReader instance = new FileReader(path);
        instance.nextLine();
        String expResult = "Hallo";
        String result = instance.getLine();
        assertEquals(expResult, result);
    }
    
}
