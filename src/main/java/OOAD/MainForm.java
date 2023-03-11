package OOAD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

    public MainForm() {
        useCaseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvasArea.paintList.add(new BlueSquare(61,61));
            }
        });
        canvasArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CanvasItem item = canvasArea.paintList.get(canvasArea.paintList.size()-1);
                item.setStartPoint(e.getX(),e.getY());
                canvasArea.repaint();
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