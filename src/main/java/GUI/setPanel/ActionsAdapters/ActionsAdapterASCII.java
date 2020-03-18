package GUI.setPanel.ActionsAdapters;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static GUI.setPanel.AddBytesPanel.*;

public class ActionsAdapterASCII implements ActionListener {
    JComboBox comboBoxASCII;

    public ActionsAdapterASCII(JComboBox comboBoxASCII) {
        this.comboBoxASCII = comboBoxASCII;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = (String) comboBoxASCII.getSelectedItem();
        char valChar = s.charAt(0);
        if(!s.equals(" ") && isComboBox) {

            String byteToBin = String.format("%8s", Integer.toBinaryString((byte) valChar & 0xFF)).replace(' ', '0');
            binFiled.setValue(byteToBin);

            decFiled.setValue((byte) valChar);
            signAscii.setText(String.valueOf(valChar));
        }else if(isComboBox){
            decFiled.setValue(null);
            binFiled.setValue(null);
            signAscii.setText("");
        }
    }


}
