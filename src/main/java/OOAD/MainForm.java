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

    public MainForm() {
        useCaseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvasArea.SetMode(Canvas.modeType.CreateUseCase);

            }
        });
        canvasArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int depth =  canvasArea.paintList.size();
                CanvasItem item = canvasArea.getMode().generator(depth);
                item.setStartPoint(e.getX(),e.getY());
                canvasArea.repaint();
                canvasArea.paintList.add(item); // ToDo
            }
        });
        canvasArea.addMouseListener(new MouseAdapter() {
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