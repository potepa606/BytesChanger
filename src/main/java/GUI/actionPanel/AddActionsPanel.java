package GUI.actionPanel;

import Controller.ReaderByte;
import Controller.WriterByte;
import Model.CharBajt;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import static GUI.CenterPanel.*;
import static GUI.setPanel.AddBytesPanel.*;

public class AddActionsPanel extends JPanel {

    public  static  JTextArea textAreaInfo;
    private String subByteString = "";
    private JButton changeButt;
    private Component[] components;

    public AddActionsPanel() {
        setLayout(new MigLayout("wrap 2 ","[]110[]","[][][][]"));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));


        JButton checkButt = new JButton("Znajdź ");
        checkButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder istniejceSubByte = new StringBuilder("Ciąg: ");
                ArrayList<CharBajt> listCharBytes = Collections.list(modelListBytes.elements());
                wirtersBytesList.clear();
                subByteString="";

                for (CharBajt subBaj : listCharBytes) {
                    subByteString += subBaj.getBajt()+";";
                    istniejceSubByte.append(subBaj.getCharr()+ "");
                }

                //System.out.println(subByteString);
                istniejceSubByte.append("\nIstnieje w plikach: \n\n");

                WriterByte writerByte =null;
                for (ReaderByte readerByteFile : foundedFilesByte) {
                    writerByte = new WriterByte(readerByteFile.getPath(),readerByteFile.getByteFile());
                    boolean isExistSubByte =  writerByte.isExistByteInFile(subByteString);
                    if(isExistSubByte){
                        String[] path = writerByte.getPath().split("\\\\");
                        istniejceSubByte.append(path[path.length-1]+ "\n");
                        wirtersBytesList.add(writerByte);
                    }
                }
                if(modelListBytes.size() != 0 ){
                    if(wirtersBytesList.size() != 0) {
                        textAreaInfo.setText(istniejceSubByte.toString());
                        changeButt.setEnabled(true);
                        clearAllList();
                    }else {
                        textAreaInfo.setText("Podany ciąg nie istnieje w żadnym pliku");
                        changeButt.setEnabled(false);
                    }
                }else {
                    //textAreaInfo.setText("");
                    JOptionPane.showMessageDialog(null, "Ciąg do znalezienia jest pusty!");
                    changeButt.setEnabled(false);
                    clearAllList();
                }

            }
        });
        add(checkButt);

        JPanel infoPanel = new JPanel(new MigLayout(" center","[][]","[][]"));
        infoPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        infoPanel.setPreferredSize(new Dimension(200,300));
        infoPanel.add(new JLabel("Info"),"center, wrap");
        add(infoPanel, "span 0 3");

        textAreaInfo = new JTextArea();
        textAreaInfo.setLineWrap(true);
        textAreaInfo.setWrapStyleWord(true);
        textAreaInfo.setEditable(false);
        textAreaInfo.setBackground(new Color(0xEEEEEE));
        //textAreaInfo.setPreferredSize(new Dimension(200,300));
        JScrollPane areaScrollPane = new JScrollPane(textAreaInfo);
        areaScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(200,300));
        infoPanel.add(areaScrollPane);


        changeButt = new JButton("Zamień");
        changeButt.setEnabled(false);
        changeButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(modelListBytes.size() != 0){
                    ArrayList<CharBajt> listCharBytes = Collections.list(modelListBytes.elements());
                    String newSubByteString="";

                    for (CharBajt subBaj : listCharBytes) {
                        newSubByteString += subBaj.getBajt()+";";
                    }

                    for (WriterByte writerByte : wirtersBytesList) {
                        boolean isDoneReplace = writerByte.changeSubByte(newSubByteString);
                        if(!isDoneReplace)
                            JOptionPane.showMessageDialog(null, "Nie udalo sie zamienic w pliku: \n"+ writerByte.getPath());

                    }
                    reloadFiles();
                    textAreaInfo.setText("Sukces, zamiana przeszła pomyślnie!");

                    changeButt.setEnabled(false);
                }else {
                    JOptionPane.showMessageDialog(null, "Ciąg do zamiany jest pusty!");
                }
                clearAllList();
            }
        });
        add(changeButt, "wrap, top");


        components = getComponents();
        ON_ActionsPanel(false);
    }


    public void ON_ActionsPanel(boolean on_off){
        for (Component component : components) {
            component.setEnabled(on_off);
        }
        realod();
    }

    private void realod(){
        validate();
        repaint();
    }


    private void reloadFiles(){
        ArrayList<String> plikiDOtestu = checkingPath.getSelectFiles(); // C:\Users\48513\Desktop\testfolder\ads.txt\abctest
        foundedFilesByte.clear();
        ReaderByte readerByte = null;
        for (String path : plikiDOtestu) {
            readerByte = new ReaderByte(path);
            foundedFilesByte.add(readerByte);
        }
    }


    public JButton getChangeButt() {
        return changeButt;
    }
}
