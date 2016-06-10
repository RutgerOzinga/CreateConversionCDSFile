/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package createconversioncdsfile;

import createconversioncdsfile.CDS_Entry;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rutger
 */
public class CDS_EntryTest {
    
    public CDS_EntryTest() {
    }
    private final String id = "test_id1";
    private final String sequence = "ATCGCTAGCAGACATGCA";
    private final CDS_Entry testCDS = new CDS_Entry(id, sequence);
    /**
     * Test of getID method, of class CDS_Entry.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        
        String expResult = "test_id1";
        String result = testCDS.getID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSequence method, of class CDS_Entry.
     */
    @Test
    public void testGetSequence() {
        System.out.println("getSequence");
        String expResult = "ATCGCTAGCAGACATGCA";
        String result = testCDS.getSequence();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLength method, of class CDS_Entry.
     */
    @Test
    public void testGetLength() {
        System.out.println("getLength");
        int expResult = 18;
        int result = testCDS.getLength();
        assertEquals(expResult, result);
    }
    
}
