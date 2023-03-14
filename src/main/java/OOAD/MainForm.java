package OOAD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainForm  {
    private JPanel panel1;
    private JMenuBar menuBar1;
    private JMenu fileMenu;
    private JMenu editMenu;
    private JButton selectBtn;
    private JButton associationBtn;
    private JButton generalizationBtn;
    private JButton compositionBtn;
    private JButton classBtn;
    private JButton useCaseBtn;
    public Canvas canvasArea;
    public static Mode mode;

    public MainForm() {
        mode = Mode.Select;
        canvasArea.setLayout(null);
        canvasArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                mode.perform(canvasArea, new Point(e.getX(),e.getY()));
                canvasArea.repaint();
            }
        });
        classBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mode = Mode.CreateClass;
            }
        });
        useCaseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mode = Mode.CreateUseCase;
            }
        });

        selectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mode = Mode.Select;
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("OOAD UML Editor");
        frame.setContentPane(new MainForm().panel1);
        frame.setMinimumSize(new Dimension(800, 500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}