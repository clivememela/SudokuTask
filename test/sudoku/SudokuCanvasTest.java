/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.awt.Graphics;
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
public class SudokuCanvasTest {

    public SudokuCanvasTest() {
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
     * Test of setScale method, of class SudokuCanvas.
     */
    @Test
    public void testSetScale() {
        System.out.println("setScale");
        double newScale = 0.0;
        SudokuCanvas instance = null;
        instance.setScale(newScale);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of paintComponent method, of class SudokuCanvas.
     */
    @Test
    public void testPaintComponent() {
        System.out.println("paintComponent");
        Graphics g = null;
        SudokuCanvas instance = null;
        instance.paintComponent(g);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
