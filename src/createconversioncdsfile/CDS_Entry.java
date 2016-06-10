/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package createconversioncdsfile;

/**
 *
 * @author Rutger
 */
public final class CDS_Entry {
    /**
     * the id of the cds entry.
     */
    private String ID;
    /**
     * the sequence of the cds entry.
     */
    private String sequence;
    /**
     * the length of the cds entry.
     */
    private int length;
    /**
     * creates a CDS_Entry object.
     * @param ID
     * @param sequence 
     */
    public CDS_Entry(String ID, String sequence) {
        setID(ID);
        setSequence(sequence);
        setLength(sequence.length());
    }
    /**
     * gets the ID
     * @return 
     */
    public String getID() {
        return ID;
    }
    /**
     * sets the ID 
     * @param newID the new id for the CDS object.
     */
    public void setID(String newID) {
        this.ID = newID;
    }
    /**
     * gets the seqeunce of the CDS_Entry.
     * @return the sequence as string.
     */
    public String getSequence() {
        return sequence;
    }
    /**
     * sets the sequence for the CDS_Entry object.
     * @param newSequence the new sequence.
     */
    public void setSequence(String newSequence) {
        this.sequence = newSequence;
    }
    /**
     * gets the length of the CDS_Entry sequence.
     * @return the seqeunce as int.
     */
    public int getLength() {
        return length;
    }
    /**
     * sets the length for the CDS_Entry.
     * @param newLength 
     */
    public void setLength(int newLength) {
        this.length = newLength;
    }
    
    
    
}
