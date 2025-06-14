/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.util.LinkedList;
import java.util.TreeMap;
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
public class SudokuGridTest {

    public SudokuGridTest() {
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
     * Test of initialValues method, of class SudokuGrid.
     */
    @Test
    public void testInitialValues() {
        System.out.println("initialValues");
        int n = 0;
        SudokuGrid instance = new SudokuGrid();
        boolean expResult = false;
        boolean result = instance.initialValues(n);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of userInsertValue method, of class SudokuGrid.
     */
    @Test
    public void testUserInsertValue() {
        System.out.println("userInsertValue");
        int rowN = 0;
        int value = 0;
        SudokuGrid instance = new SudokuGrid();
        boolean expResult = false;
        boolean result = instance.userInsertValue(rowN, value);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of userEraseValue method, of class SudokuGrid.
     */
    @Test
    public void testUserEraseValue() {
        System.out.println("userEraseValue");
        int rowN = 0;
        SudokuGrid instance = new SudokuGrid();
        boolean expResult = false;
        boolean result = instance.userEraseValue(rowN);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printGrid method, of class SudokuGrid.
     */
    @Test
    public void testPrintGrid() {
        System.out.println("printGrid");
        SudokuGrid instance = new SudokuGrid();
        instance.printGrid();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLevel method, of class SudokuGrid.
     */
    @Test
    public void testGetLevel() {
        System.out.println("getLevel");
        SudokuGrid instance = new SudokuGrid();
        int expResult = 0;
        int result = instance.getLevel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNegativeCells method, of class SudokuGrid.
     */
    @Test
    public void testGetNegativeCells() {
        System.out.println("getNegativeCells");
        SudokuGrid instance = new SudokuGrid();
        int expResult = 0;
        int result = instance.getNegativeCells();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValues method, of class SudokuGrid.
     */
    @Test
    public void testGetValues() {
        System.out.println("getValues");
        SudokuGrid instance = new SudokuGrid();
        LinkedList expResult = null;
        LinkedList result = instance.getValues();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAccess method, of class SudokuGrid.
     */
    @Test
    public void testGetAccess() {
        System.out.println("getAccess");
        SudokuGrid instance = new SudokuGrid();
        LinkedList expResult = null;
        LinkedList result = instance.getAccess();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class SudokuGrid.
     */
    @Test
    public void testClone() {
        System.out.println("clone");
        SudokuGrid instance = new SudokuGrid();
        SudokuGrid expResult = null;
        SudokuGrid result = instance.clone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCopyGridMap method, of class SudokuGrid.
     */
    @Test
    public void testGetCopyGridMap() {
        System.out.println("getCopyGridMap");
        SudokuGrid instance = new SudokuGrid();
        TreeMap expResult = null;
        TreeMap result = instance.getCopyGridMap();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCopyCell method, of class SudokuGrid.
     */
    @Test
    public void testGetCopyCell() {
        System.out.println("getCopyCell");
        int row = 0;
        SudokuGrid instance = new SudokuGrid();
        SudokuCell expResult = null;
        SudokuCell result = instance.getCopyCell(row);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of randomCellErase method, of class SudokuGrid.
     */
    @Test
    public void testRandomCellErase() {
        System.out.println("randomCellErase");
        int n = 0;
        SudokuGrid instance = new SudokuGrid();
        instance.randomCellErase(n);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of confirmInitialValues method, of class SudokuGrid.
     */
    @Test
    public void testConfirmInitialValues() {
        System.out.println("confirmInitialValues");
        SudokuGrid instance = new SudokuGrid();
        instance.confirmInitialValues();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
