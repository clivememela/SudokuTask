/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import sudoku.controller.ButtonsController;
import sudoku.enums.ActionEnum;

/**
 *
 * @author cmemela
 */
public class ButtonPanel extends JPanel implements Observer {

    JButton btnNew, btnCheck, btnExit;
    JCheckBox cbHelp;

    public ButtonPanel() {
        JPanel pnlAlign = new JPanel();
        pnlAlign.setLayout(new BoxLayout(pnlAlign, BoxLayout.PAGE_AXIS));
        add(pnlAlign, BorderLayout.NORTH);

        JPanel pnlOptions = new JPanel(new FlowLayout(FlowLayout.LEADING));
        pnlOptions.setBorder(BorderFactory.createTitledBorder(" Options "));
        pnlAlign.add(pnlOptions);

        btnNew = new JButton("New");
        btnNew.setFocusable(false);
        pnlOptions.add(btnNew);

        btnCheck = new JButton("Check");
        btnCheck.setFocusable(false);
        pnlOptions.add(btnCheck);

        btnExit = new JButton("Exit");
        btnExit.setFocusable(false);
        pnlOptions.add(btnExit);

        cbHelp = new JCheckBox("Help on", true);
        cbHelp.setFocusable(false);
        pnlOptions.add(cbHelp);

    }

    public void setController(ButtonsController buttonsController) {
        btnNew.addActionListener(buttonsController);
        btnCheck.addActionListener(buttonsController);
        btnExit.addActionListener(buttonsController);
        cbHelp.addActionListener(buttonsController);
    }

    @Override
    public void update(Observable o, Object o1) {
    }
}
