package GUI.setPanel;

import Controller.DataConverter;
import GUI.setPanel.ActionsAdapters.ActionsAdapterASCII;
import GUI.setPanel.ActionsAdapters.ActionsAdapterBin;
import GUI.setPanel.ActionsAdapters.ActionsAdapterByte;
import GUI.setPanel.ActionsAdapters.ActionsAdapterJlist;
import Model.CharBajt;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class AddBytesPanel extends JPanel {

    public static DefaultListModel<CharBajt> modelListBytes = new DefaultListModel();
    public static DefaultListModel modeLlistChars = new DefaultListModel();
    public static void clearAllList(){
        modelListBytes.clear();
        modeLlistChars.clear();
    }

    JRadioButton decRadioButt, binRadioButt,asciiRadioButt;
    ButtonGroup bg = new ButtonGroup();

    static public JFormattedTextField decFiled, binFiled;
    static public JLabel signAscii;
    static public JComboBox<String> comboBoxASCII = new JComboBox<>();
    static public boolean isComboBox = false;

    static Map<Integer, String> mapListAscii;
    static {
        mapListAscii = new HashMap<>();
        for (int i=32; i<256;i++){
            mapListAscii.put(i,((char)i)+"");
        }
    }

    private Component[] components;


    public AddBytesPanel(){
        setLayout(new MigLayout("wrap 4, center ", "[][][][]","[][]10[][]"));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        decRadioButt = new JRadioButton("Dec (od -128 do 127)");
        decRadioButt.setSelected(true);
        decRadioButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { setEditableComponents(decRadioButt); }});
        binRadioButt = new JRadioButton("Bin");
        binRadioButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { setEditableComponents(binRadioButt); }});
        asciiRadioButt = new JRadioButton("ASCII");
        asciiRadioButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { setEditableComponents(asciiRadioButt); }});
        add(decRadioButt); bg.add(decRadioButt);
        add(binRadioButt); bg.add(binRadioButt);
        add(asciiRadioButt); bg.add(asciiRadioButt);

        add(new JLabel("Znak"), "center");


        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Byte.class);
        //formatter.setAllowsInvalid(false);
        decFiled = new JFormattedTextField(formatter);
        decFiled.setPreferredSize(new Dimension(40, 25));
        decFiled.addKeyListener(new ActionsAdapterByte(decFiled));
        add(decFiled);






        MaskFormatter binaryMask = null; //each # represents one valid character input
        try {
            binaryMask = new MaskFormatter("########");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        binaryMask.setValidCharacters("01");
        binFiled = new JFormattedTextField(binaryMask);
        binFiled.setEditable(false);
        binFiled.setPreferredSize(new Dimension(150, 25));
        binFiled.addKeyListener(new ActionsAdapterBin(binFiled));
        add(binFiled);



        String[] values = mapListAscii.values().toArray(new String[0]);
        comboBoxASCII.setModel(new DefaultComboBoxModel<String>(values));
        comboBoxASCII.setSelectedIndex(0);
        comboBoxASCII.setEnabled(false);
        comboBoxASCII.addActionListener(new ActionsAdapterASCII(comboBoxASCII));

        JScrollPane jScrollPane = new JScrollPane(comboBoxASCII);
        jScrollPane.setPreferredSize(new Dimension(90, 28) );
        add(jScrollPane, "center");

        signAscii = new JLabel();
        add(signAscii, "center, wrap");
        add(new JLabel("Ciąg bajtów"), "span 4, center ");



        JList<CharBajt> charListByte = new JList<>(modelListBytes);
        charListByte.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        charListByte.setVisibleRowCount(5);
        charListByte.addMouseListener( new ActionsAdapterJlist(charListByte));
        JScrollPane jScrollPane1 = new JScrollPane(charListByte);
        jScrollPane1.setBounds(0,0,125,80);

        JPanel tempPane1 = new JPanel(null);
        tempPane1.setPreferredSize(new Dimension(130,80));
        tempPane1.add(jScrollPane1);
        add(tempPane1, "center");

////////////////////////////////////////////////////////////////////////////////////
        JList<String> charList = new JList<>(modeLlistChars);
        charList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        charList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        charList.setVisibleRowCount(1);
        charList.setFixedCellWidth(14);
        charList.addMouseListener( new ActionsAdapterJlist(charList));
        JScrollPane jScrollPane2 = new JScrollPane(charList);
        jScrollPane2.setBounds(3,23,173,40);

        JPanel tempPane2 = new JPanel(null);
        tempPane2.setPreferredSize(new Dimension(180,80));
        tempPane2.add(jScrollPane2);
        add(tempPane2, "span 2, center");

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sing = signAscii.getText();
                if(sing.length() != 0)
                    addBajtToModelSearch(decFiled.getText(), binFiled.getText(),sing.charAt(0));
                realod();
            }
        });
        add(addButton, "center");
        components = getComponents();
        ON_AddBytesPanel(false);
    }

    public void realod(){
        validate();
        repaint();
    }

    private void addBajtToModelSearch(String bajt, String bin, char charr){
        byte byteVal = Byte.parseByte(bajt);
        byte byteValFromBin =0;
        try{
            byteValFromBin = Byte.parseByte(bin.replaceAll("\\s+", ""), 2);
        }catch (NumberFormatException ex){
            System.out.println("Wyjatek, przekroczony zakres, liczba ujemna");
            byteValFromBin = DataConverter.convertBinTOByteSigned(bin.toCharArray());
        }
        byte byteValFromChar = (byte)  charr;

       CharBajt bajcik = null;
        if(byteVal == byteValFromBin && byteValFromBin == byteValFromChar){
            bajcik = new CharBajt(byteVal,bin,charr);
            modelListBytes.addElement(bajcik);
            modeLlistChars.addElement(bajcik.getNameChar());
        }
    }

    private void setEditableComponents(JRadioButton radioButton){
        if(radioButton == decRadioButt){
            decFiled.setEditable(true);
            binFiled.setEditable(false);
            comboBoxASCII.setEnabled(false);
            isComboBox = false;
        }else if(radioButton == binRadioButt){
            binFiled.setEditable(true);
            decFiled.setEditable(false);
            comboBoxASCII.setEnabled(false);
            isComboBox = false;
        }else if(radioButton == asciiRadioButt){
            binFiled.setEditable(false);
            decFiled.setEditable(false);
            comboBoxASCII.setEnabled(true);
            isComboBox = true;
        }
    }

    public void ON_AddBytesPanel(boolean on_off){
        for (Component component : components) {
            component.setEnabled(on_off);
        }
        realod();
    }



}