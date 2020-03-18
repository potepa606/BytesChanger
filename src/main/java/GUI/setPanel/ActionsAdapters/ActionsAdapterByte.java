package GUI.setPanel.ActionsAdapters;

import GUI.CenterPanel;
import GUI.setPanel.AddBytesPanel;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ActionsAdapterByte extends KeyAdapter{

    JFormattedTextField decFiled;
    NumberFormatter formatter;

    public ActionsAdapterByte(JFormattedTextField decFiled) {
        this.decFiled = decFiled;
        formatter = (NumberFormatter) decFiled.getFormatter();
    }

    public void keyPressed(KeyEvent ke) {
        String value = decFiled.getText();
        int len = value.length();
        if (ke.getKeyCode() == KeyEvent.VK_BACK_SPACE || (len == 0 && ke.getKeyChar() == '-')){
            formatter.setAllowsInvalid(true);
        } else if(ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9'){
            formatter.setAllowsInvalid(false);
        }
        else {
            formatter.setAllowsInvalid(false);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        String value = decFiled.getText();
        byte byteVal = 0;
        if(!value.equals("") && !value.equals("-")) {
            byteVal = Byte.parseByte(value);

            String byteToBin = String.format("%8s", Integer.toBinaryString(byteVal & 0xFF)).replace(' ', '0');
            AddBytesPanel.binFiled.setValue(byteToBin);
            AddBytesPanel.signAscii.setText(String.valueOf((char) (byteVal & 0xFF)));
        }else {
            AddBytesPanel.binFiled.setValue(null);
            AddBytesPanel.signAscii.setText("");
        }

      //  System.out.println(byteVal);
        AddBytesPanel.comboBoxASCII.setSelectedItem( byteVal > 32 || byteVal <0  ?  (char) (byteVal & 0xFF)+"" : " ");
        CenterPanel.addBytesPanel.realod();
    }


}
