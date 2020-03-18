package GUI.setPanel.ActionsAdapters;

import Controller.DataConverter;
import GUI.CenterPanel;
import GUI.setPanel.AddBytesPanel;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static GUI.setPanel.AddBytesPanel.*;

public class ActionsAdapterBin extends KeyAdapter{

    JFormattedTextField binFiled;

    public ActionsAdapterBin(JFormattedTextField binFiled) {
        this.binFiled = binFiled;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        String value = binFiled.getText();
        byte byteVal = 0 ;
        if(!value.equals("        ")) {
            try{
                byteVal = Byte.parseByte(value.replaceAll("\\s+", ""), 2);
            }catch (NumberFormatException ex){
                System.out.println("Wyjatek, przekroczony zakres, liczba ujemna");
                byteVal = DataConverter.convertBinTOByteSigned(value.toCharArray());
                System.out.println(byteVal);
            }
            signAscii.setText(String.valueOf((char) (byteVal & 0xFF)));
            decFiled.setValue(byteVal);
        }else {
            decFiled.setValue(null);
        }
        AddBytesPanel.comboBoxASCII.setSelectedItem( byteVal > 32 || byteVal < 0  ?  (char) (byteVal & 0xFF)+"" : " ");

        CenterPanel.addBytesPanel.realod();
    }


}
