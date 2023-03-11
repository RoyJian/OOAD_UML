package OOAD;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainForm {
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
    private JPanel Canvas;

    public static void main(String[] args) {
        JFrame frame = new JFrame("OOAD UML Editor");
        frame.setContentPane(new MainForm().panel1);
        frame.setMinimumSize(new Dimension(800, 500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    private void createUIComponents() throws IOException {
        // TODO: place custom component creation code here


    }

}