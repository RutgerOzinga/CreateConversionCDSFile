/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package createconversioncdsfile;

import createconversioncdsfile.CalculateConversion;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
public class CalculateConversionTest {
    
    public CalculateConversionTest() {
    }
    
    /**
     * Test of calculateConversion method, of class CalculateConversion.
     */
    @Test
    public void testCalculateConversion() {
        System.out.println("calculateConversion");
        double transcriptMax = 1000.0;
        double proteinMax = 1000.0;
        List logList = Arrays.asList(7.0,11.0);
        CalculateConversion instance = new CalculateConversion();
        double expResult = 4.0;
        double result = instance.calculateConversion(transcriptMax, proteinMax, logList);
        assertEquals(expResult, result, 0.0);
    }
    
}
