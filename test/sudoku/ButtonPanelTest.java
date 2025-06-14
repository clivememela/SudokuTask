/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.util.Observable;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sudoku.controller.ButtonsController;

/**
 *
 * @author cmemela
 */
public class ButtonPanelTest {

    public ButtonPanelTest() {
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
     * Test of setController method, of class ButtonPanel.
     */
    @Test
    public void testSetController() {
        System.out.println("setController");
        ButtonsController buttonsController = null;
        ButtonPanel instance = new ButtonPanel();
        instance.setController(buttonsController);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class ButtonPanel.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Observable o = null;
        Object o1 = null;
        ButtonPanel instance = new ButtonPanel();
        instance.update(o, o1);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
