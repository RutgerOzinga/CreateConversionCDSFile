/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filterongenes;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.cli.ParseException;

/**
 *
 * @author Rutger
 */
public class FilterOnGenes {
    /**
     * the path to the proteinfile.
     */
    private Path proteinFile;
    /**
     * path to the transcriptfile
     */
    private Path transcriptFile;
    /**
     * path to the cds file.
     */
    private Path cdsFile;
    /**
     * path to the conversion file
     */
    private Path conversionFile;
    /**
     * location for the new file
     */
    private String newFile;
    /**
     * the id of the current fasta entry.
     */
    private String ID = "";
    /**
     * the sequence of the current fasta entry.
     */
    private String sequence = "";
    /**
     * a hashmap containing the genes that are in the conversion file.
     */
    private HashMap geneList = new HashMap();
    /**
     * a hashmap containing the gene id as key and a CDS_Entry Object as value.
     */
    private HashMap<String, CDS_Entry> cdsMap = new HashMap();
    /**
     * longest protein sequence.
     */
    private int longestProt;
    /**
     * longest transcript sequence.
     */
    private int longestTrans;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException, IOException {
        FilterOnGenes filterGenes = new FilterOnGenes();
        filterGenes.run(args);
    }
    /**
     * runs the methods in this project.
     * @param args
     * @throws ParseException
     * @throws IOException 
     */
    public void run(String[] args) throws ParseException, IOException {
        ParseCLI parse = new ParseCLI(args);
        proteinFile = parse.getProtPath();
        transcriptFile = parse.getTransPath();
        cdsFile = parse.getCdsPath();
        conversionFile = parse.getConversionFilePath();
        newFile = parse.getNewFilePath();
        CreateGeneList createGeneList = new CreateGeneList();
        createGeneList.readFile(conversionFile);
        geneList = createGeneList.getGeneList();
        readProteinFasta();
        readTranscriptFasta();
        readCDSFasta();
        CreateOutPutFile();
    }
    /**
     *reads the CDS file and for each line and creates a cds object with the id and the sequence of the cds.
     * after this it checks if the gene id is in the list of ids that have a conversion rate.
     * when it does adds the gene id and the cds object to a hashmap.
     */
    private void readCDSFasta() throws ParseException, IOException {
        Pattern re = Pattern.compile("(?<=gene:)ENSG\\d+");
        FileWriter writer = new FileWriter();
        FileReader reader = new FileReader(cdsFile);
        Charset charset = Charset.forName("US-ASCII");
        String line;
        while (reader.nextLine()) {
            line = reader.getLine();
            if (line.contains(">")) {
                if (ID.isEmpty() == false & sequence.isEmpty() == false) {
                    CDS_Entry cdsEntry = new CDS_Entry(ID, sequence);
                    Matcher m = re.matcher(ID);
                    m.find();
                    if (geneList.containsKey(m.group())) {
                        if (!cdsMap.containsKey(m.group())) {
                            cdsMap.put(m.group(), cdsEntry);
                        } else {
                            if (cdsMap.get(m.group()).getLength() < sequence.length()) {
                                cdsMap.put(m.group(), cdsEntry);
                            }
                        }
                    }
                }
                ID = line;
                sequence = "";

            } else {
                sequence += line.trim();
            }
        }
        CDS_Entry cdsEntry = new CDS_Entry(ID, sequence);
        Matcher m = re.matcher(ID);
        m.find();
        if (geneList.containsKey(m.group())) {
            if (cdsMap.containsKey(m.group())) {
                cdsMap.put(m.group(), cdsEntry);
            } else {
                if (cdsMap.get(m.group()).getLength() < sequence.length()) {
                    cdsMap.put(m.group(), cdsEntry);
                }
            }
        }

    }
     /**
     * reads the transcript file and saves the longest line.
     * @throws ParseException
     * @throws IOException 
     */
    private void readTranscriptFasta() throws ParseException, IOException {
        Pattern re = Pattern.compile("(?<=gene:)ENSG\\d+");
        FileReader reader = new FileReader(transcriptFile);
        Charset charset = Charset.forName("US-ASCII");
        String line;
        while (reader.nextLine()) {
            line = reader.getLine();
            if (line.contains(">")) {
                if (ID.isEmpty() == false & sequence.isEmpty() == false) {
                    Matcher m = re.matcher(ID);
                    m.find();
                    // if the id is in the geneList.
                    if (geneList.containsKey(m.group())) {
                        //if the length of the sequence is longer that the current longest sequence.
                        if (sequence.length() > longestTrans) {
                            longestTrans = sequence.length();
                        }
                    }
                }
                ID = line;
                sequence = "";

            } else {
                sequence += line.trim();
            }
        }
        //last check for the remaining data.
        if (geneList.containsKey(ID)) {
            if (sequence.length() > longestTrans) {
                longestTrans = sequence.length();
            }
        }
        ID = "";
        sequence = "";
    }
     /**
     * reads the protein file and saves the longest line.
     * @throws ParseException
     * @throws IOException 
     */
    private void readProteinFasta() throws ParseException, IOException {
        Pattern re = Pattern.compile("(?<=gene:)ENSG\\d+");
        FileWriter writer = new FileWriter();
        FileReader reader = new FileReader(proteinFile);
        Charset charset = Charset.forName("US-ASCII");
        String line;
        while (reader.nextLine()) {
            line = reader.getLine();
            if (line.contains(">")) {
                if (ID.isEmpty() == false & sequence.isEmpty() == false) {
                    Matcher m = re.matcher(ID);
                    m.find();
                    //if the id is contained in the geneList.
                    if (geneList.containsKey(m.group())) {
                        // if the sequence length is longer than the current longest sequence.
                        if (sequence.length() > longestProt) {
                            longestProt = sequence.length();
                        }
                    }
                }
                ID = line;
                sequence = "";

            } else {
                sequence += line.trim();
            }
        }
        if (geneList.containsKey(ID)) {
            if (sequence.length() > longestProt) {
                longestProt = sequence.length();
            }
        }
        ID = "";
        sequence = "";
    }
    /**
     * creates a output file containing 
     * @throws IOException 
     */
    private void CreateOutPutFile() throws IOException {
        FileWriter write =  new FileWriter();
        String protID = "";
        Pattern re = Pattern.compile(">ENS[A-Z]+\\d+.\\d+");
        CalculateConversion calc = new CalculateConversion();
        write.OpenFile(newFile);
        //for gene in geneList.
        for (Object gene : geneList.keySet()) {
            Matcher m = re.matcher(cdsMap.get(gene.toString()).getID());
            //match the pattern against id in the cdsMap.
            if (m.find()) {
                protID = m.group();
            }
            //calculates the conversion factor with the longest transcript and protein, and a list containing the CPM log values of
            // the transcript and the protein.
            double conversionFactor = calc.calculateConversion(longestTrans, longestProt, (List) geneList.get(gene));
            String outputLine = protID + " gene:" + gene + "\t" + conversionFactor + "\t" + cdsMap.get(gene.toString()).getSequence();
            write.writeLine(outputLine);
        }
    }
}
