/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.util.LinkedList;
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
public class CellTest {

    public CellTest() {
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
     * Test of setValue method, of class Cell.
     */
    @Test
    public void testSetValue() {
        System.out.println("setValue");
        int value = 0;
        Cell instance = null;
        boolean expResult = false;
        boolean result = instance.setValue(value);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eraseValue method, of class Cell.
     */
    @Test
    public void testEraseValue() {
        System.out.println("eraseValue");
        Cell instance = null;
        instance.eraseValue();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValue method, of class Cell.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        Cell instance = null;
        int expResult = 0;
        int result = instance.getValue();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAccess method, of class Cell.
     */
    @Test
    public void testSetAccess() {
        System.out.println("setAccess");
        boolean acs = false;
        Cell instance = null;
        instance.setAccess(acs);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAccess method, of class Cell.
     */
    @Test
    public void testGetAccess() {
        System.out.println("getAccess");
        Cell instance = null;
        boolean expResult = false;
        boolean result = instance.getAccess();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of excludeFromPossibles method, of class Cell.
     */
    @Test
    public void testExcludeFromPossibles() {
        System.out.println("excludeFromPossibles");
        int concurent = 0;
        Cell instance = null;
        instance.excludeFromPossibles(concurent);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPossibleValues method, of class Cell.
     */
    @Test
    public void testGetPossibleValues() {
        System.out.println("getPossibleValues");
        Cell instance = null;
        LinkedList expResult = null;
        LinkedList result = instance.getPossibleValues();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addToPossibleValues method, of class Cell.
     */
    @Test
    public void testAddToPossibleValues() {
        System.out.println("addToPossibleValues");
        int newPossible = 0;
        Cell instance = null;
        boolean expResult = false;
        boolean result = instance.addToPossibleValues(newPossible);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compareTo method, of class Cell.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Cell c = null;
        Cell instance = null;
        int expResult = 0;
        int result = instance.compareTo(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class CellImpl extends Cell {

        public CellImpl() {
            super(0);
        }
    }
}
