/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cmemela
 */
public class SudokuCellTest {

    public SudokuCellTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getRowPosition method, of class SudokuCell.
     */
    @Test
    public void testGetRowPosition() {
        System.out.println("getRowPosition");
        SudokuCell instance = null;
        int expResult = 0;
        int result = instance.getRowPosition();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColumnPosition method, of class SudokuCell.
     */
    @Test
    public void testGetColumnPosition() {
        System.out.println("getColumnPosition");
        SudokuCell instance = null;
        int expResult = 0;
        int result = instance.getColumnPosition();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSqrPosition method, of class SudokuCell.
     */
    @Test
    public void testGetSqrPosition() {
        System.out.println("getSqrPosition");
        SudokuCell instance = null;
        int expResult = 0;
        int result = instance.getSqrPosition();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class SudokuCell.
     */
    @Test
    public void testClone() {
        System.out.println("clone");
        SudokuCell instance = null;
        SudokuCell expResult = null;
        SudokuCell result = instance.clone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
