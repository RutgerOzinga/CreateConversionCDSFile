/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package createconversioncdsfile;

import java.nio.file.Path;
import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rutger
 */
public class CreateGeneListTest {
    
    public CreateGeneListTest() {
    }

    @Test
    public void testSetIndex() {
        System.out.println("setIndex");
        String header = "GeneID	GeneName	t_logCPM	p_logCPM";
        CreateGeneList instance = new CreateGeneList();
        instance.setIndex(header);
        int result = instance.getProteinIndex();
        int expectedResult = 3;
        assertEquals(expectedResult, result);
    }
}
