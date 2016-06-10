/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package createconversioncdsfile;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Rutger
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({createconversioncdsfile.CreateGeneListTest.class, createconversioncdsfile.FileReaderTest.class, createconversioncdsfile.CalculateConversionTest.class, createconversioncdsfile.CDS_EntryTest.class, createconversioncdsfile.FileWriterTest.class})
public class CreateConversionCDSFileSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
