/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filterongenes;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Rutger
 */
public class CreateGeneList {
    /**
     * a hashmap containing the gene id as key and the CPM log values of the transcript and the protein as as list
     * as a value.
     */
    private final HashMap geneMap = new HashMap();
    /**
     * the index of the transcript value.
     */
    private int transcriptIndex;
    /**
     * the index of the protein value.
     */
    private int proteinIndex;
    /**
     * a boolean telling if it is the first line or not.
     */
    private boolean firstLine = true;
    /**
     * path to the conversion file.
     */
    private Path conversionPath;
    
    /**
     * reads the file and sets the index then adds the ID and a list of the protein
     * and transcript values as a value in the hashmap.
     * @param conversionFile path to the conversion file.
     */
    public void readFile(Path conversionFile) {
        Path file = conversionFile;
        Charset charset = Charset.forName("US-ASCII");
        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    setIndex(line);
                    firstLine = false;
                } else {
                    String[] splittedLine = line.split("\t");
                    List logList = Arrays.asList(splittedLine[transcriptIndex],splittedLine[proteinIndex]);
                    geneMap.put(splittedLine[0], logList);
                }

            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }
    /**
     * searches for the index of the protein and transcript values.
     * @param header the first line of the conversion file.
     */
    private void setIndex(String header) {
        int count = 0;
        String[] splitted = header.split("\t");
        for (String item : splitted) {
            switch (item) {
                case "t_logCPM":
                    transcriptIndex = count;
                    count++;
                    break;
                case "p_logCPM":
                    proteinIndex = count;
                    count++;
                    break;
                default:
                    count++;
                    break;
            }
        }
    }
    /**
     * gets the genemap.
     * @return geneMap
     */
    public HashMap getGeneList() {
        return geneMap;
    }
}
