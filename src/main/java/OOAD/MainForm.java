package OOAD;

import OOAD.Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainForm {
    private JPanel panel1;
    private JMenuBar menuBar1;
    private JMenu fileMenu;
    private JMenu editMenu;
    private JMenuItem groupMenuItem;
    private JMenuItem unGroupMenuItem;
    private JMenuItem changeNameMenuItem;
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
        groupMenuItem = new JMenuItem("Group");
        unGroupMenuItem = new JMenuItem("Ungroup");
        changeNameMenuItem = new JMenuItem("Change Object Name");
        editMenu.add(groupMenuItem);
        editMenu.add(unGroupMenuItem);
        editMenu.add(changeNameMenuItem);
        canvasArea.setLayout(null);
        classBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mode = Mode.CreateClass;
                mode.buttonPerform();
            }
        });
        useCaseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mode = Mode.CreateUseCase;
                mode.buttonPerform();
            }
        });

        selectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mode = Mode.Select;
                mode.buttonPerform();
            }
        });
        associationBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mode = Mode.CreateAssociationLine;
                mode.buttonPerform();
            }
        });
        generalizationBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mode = Mode.CreateGeneralizationLine;
                mode.buttonPerform();
            }
        });
        compositionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mode = Mode.CreateCompositionLine;
                mode.buttonPerform();
            }
        });
        groupMenuItem.addActionListener(new GroupMenuListener());
        unGroupMenuItem.addActionListener(new UnGroupMenuListener());
        changeNameMenuItem.addActionListener(new ChangeObjNameMenuListener());
        setGroupEnable(false);
        setChangeNameMenuEnable(false);
        Utils.setMain(this);
    }

    public void setGroupEnable(Boolean bool) {
        groupMenuItem.setEnabled(bool);
        unGroupMenuItem.setEnabled(bool);
    }

    public void setChangeNameMenuEnable(Boolean bool) {
        changeNameMenuItem.setEnabled(bool);
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