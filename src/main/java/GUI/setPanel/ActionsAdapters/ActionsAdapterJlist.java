package GUI.setPanel.ActionsAdapters;

import Model.CharBajt;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static GUI.setPanel.AddBytesPanel.modeLlistChars;
import static GUI.setPanel.AddBytesPanel.modelListBytes;

public class ActionsAdapterJlist implements MouseListener {

    JList lista;

    public ActionsAdapterJlist(JList lista) {
        this.lista = lista;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int index = lista.getSelectedIndex();
        if(e.getClickCount() == 2 && index >= 0){
           // CharBajt bajcik = (CharBajt) lista.getSelectedValue();
            //System.out.println(bajcik.getNameChar());
            modelListBytes.remove(index);
            modeLlistChars.remove(index);
        }
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
}
