package sudoku;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.TreeMap;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import sudoku.model.SudokuGame;

public class SudokuCanvas extends JPanel {

    private SudokuGrid grid;
    private int level;
    private int neighbors;
    private double scale;
    private int cellSize;
    private int width;
    private int height;
    private Integer pointedCellNum;
    private Integer choice;
    private JFrame choiceFrame;
    private JFrame mainFrame;
    private Integer xRectangle;
    private Integer yRectangle;

    public SudokuCanvas(JFrame frame, SudokuGrid grid) {
        super();

        mainFrame = frame;
        this.grid = grid;
        scale = 1;
        cellSize = 30;
        level = grid.getLevel();
        neighbors = level * level;
        width = cellSize * neighbors;
        height = cellSize * neighbors;

        setPreferredSize(new Dimension(width + 1, height + 1));
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                mouseMovement(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mouseMovement(e);
            }

            private void mouseMovement(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                xRectangle = (x / cellSize) * cellSize;
                yRectangle = (y / cellSize) * cellSize;

                pointedCellNum = (y / cellSize) * neighbors + x / cellSize;

                repaint();
            }
        });
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame.setEnabled(true);
                mouseClick(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    private void mouseClick(MouseEvent e) {

        if (!grid.getCopyCell(pointedCellNum).getAccess()) {
            mainFrame.setEnabled(true);
        } else if (grid.getCopyCell(pointedCellNum).getValue() == 0) {

            choiceFrame = new JFrame("Enter Value for Cell");

            JPanel panel = new JPanel();
            GroupLayout layout = new GroupLayout(panel);

            JButton[] buttons = new JButton[neighbors];
            for (int i = 0; i < neighbors; i++) {
                final JButton button = new JButton(String.valueOf(i + 1));
                button.setMargin(new Insets(0, 0, 0, 0));
                button.setPreferredSize(new Dimension(cellSize, cellSize));
                buttons[i] = button;
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        choice = Integer.parseInt(button.getText());
                        grid.userInsertValue(pointedCellNum, choice);
                        repaint();
                        mainFrame.setEnabled(true);
                        choiceFrame.setVisible(false);
                        choiceFrame.dispose();
                    }
                });
            }

            panel.setLayout(layout);

            GroupLayout.Group horizontal = layout.createSequentialGroup();
            for (int i = 0; i < level; i++) {
                GroupLayout.Group subGroup = layout.createParallelGroup();
                for (int j = 0; j < level; j++) {
                    subGroup.addComponent(buttons[j * level + i], GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE);
                }
                horizontal.addGroup(subGroup);
            }
            layout.setHorizontalGroup(horizontal);

            GroupLayout.Group vertical = layout.createSequentialGroup();
            for (int i = 0; i < level; i++) {
                GroupLayout.Group subGroup = layout.createParallelGroup();
                for (int j = 0; j < level; j++) {
                    subGroup.addComponent(buttons[i * level + j], GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE);
                }
                vertical.addGroup(subGroup);
            }
            layout.setVerticalGroup(vertical);

            choiceFrame.add(panel);
            choiceFrame.setLocation(e.getXOnScreen() - (level / 2) * cellSize, e.getYOnScreen() - (level / 2) * cellSize);
            choiceFrame.pack();
            choiceFrame.setResizable(false);
            choiceFrame.setVisible(true);
        } else {
            int responce = JOptionPane.showConfirmDialog(mainFrame,
                    "Erase value?", "Confirm erase", JOptionPane.YES_NO_OPTION);
            if (responce == JOptionPane.YES_OPTION) {
                grid.userEraseValue(pointedCellNum);
                repaint();
            }
            mainFrame.setEnabled(true);
        }
    }

    public void setScale(double newScale) {

        if (newScale <= 0.5) {
            scale = 0.5;
        } else if (newScale > 0.5 && newScale <= 0.75) {
            scale = 0.75;
        } else if (newScale > 0.75 && newScale <= 1) {
            scale = 1;
        } else if (newScale > 1 && newScale <= 1.5) {
            scale = 1.5;
        } else if (newScale > 1.5) {
            scale = 2;
        }

        cellSize = (int) (cellSize * scale);
        width = cellSize * neighbors;
        height = cellSize * neighbors;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < neighbors + 1; i++) {
            g.drawLine(i * width / neighbors, 0, i * width / neighbors, height);
            g.drawLine(0, i * height / neighbors, width, i * height / neighbors);
        }
        for (int i = 0; i < level + 1; i++) {
            g.drawLine(i * width / level - 1, 0, i * width / level - 1, height);
            g.drawLine(0, i * height / level - 1, width, i * height / level - 1);
            g.drawLine(i * width / level + 1, 0, i * width / level + 1, height);
            g.drawLine(0, i * height / level + 1, width, i * height / level + 1);
        }

        if (grid != null) {

            Font font = new Font(null, 0, (int) (cellSize * 0.65));
            g.setFont(font);
            TreeMap<Integer, SudokuCell> map = grid.getCopyGridMap();

            for (int i = 0; i < neighbors * neighbors; i++) {

                int value = map.get(i).getValue();
                if (value != 0) {
                    boolean access = grid.getAccess().get(i);
                    char[] data = new char[2];
                    data[0] = Character.forDigit(value, 10);
                    if (value > 9) {
                        data[0] = Character.forDigit(value / 10, 10);
                        data[1] = Character.forDigit(value % 10, 10);
                    }

                    if (access) {
                        g.setColor(Color.BLUE);
                    } else {
                        g.setColor(Color.BLACK);
                    }

                    int length;
                    int horizontalShift;
                    if (value < 10) {
                        length = 1;
                        horizontalShift = (width / (neighbors * 3));
                    } else {
                        length = 2;
                        horizontalShift = (int) (4 * scale);
                    }
                    g.drawChars(data, 0, length, (i % neighbors) * (width / neighbors) + horizontalShift,
                            (i / neighbors) * (height / neighbors) + (height / ((int) (neighbors * (1.3)))));
                }
            }
        }

    }
}
