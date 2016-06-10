/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package createconversioncdsfile;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Opens and reads the file sets the next line when next line is called
 * and also checks if the line is not empty. 
 * Gives the next line when get line is called.
 * @author Rutger
 */
public class FileReader {
    /**
     * the reader instance
     */
    private BufferedReader reader;
    /**
     * the current line of the file.
     */
    private String line;
    /**
     * opens the file and saves it as reader.
     * @param filePath path to the file that needs to be opened
     * @throws IOException an exception
     */
    public FileReader(Path filePath) throws IOException {
        Charset charset = Charset.forName("US-ASCII");
        reader = Files.newBufferedReader(filePath, charset);
    }
    /**
     * sets the next line and returns if it is empty or not.
     * @return boolean based on if line is empty.
     * @throws IOException an exception.
     */
    public Boolean nextLine() throws IOException {
        if ((line = reader.readLine()) != null) {
            return true;
        } else {
            reader.close();
            return false;

        }

    }
    /**
     * gets the value of line and returns it.
     * @return 
     */
    public String getLine() {
        return line;
    }
}
