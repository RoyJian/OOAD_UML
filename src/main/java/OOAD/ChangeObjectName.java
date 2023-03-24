package OOAD;

import OOAD.Utils.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class  ChangeObjNameMenuListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        BasicObject basicObject;
        if (Canvas.selectBag.size() != 1)
            return;
        try {
            basicObject = (BasicObject)Canvas.selectBag.get(0);
        } catch (ClassCastException ignore ){ return; }
        String name = JOptionPane.showInputDialog("Object Name",basicObject.getName());
        basicObject.setName(name);
    }
}
