package GUI;


import javax.swing.*;
import java.awt.*;

public class NorthPanel extends JPanel {

    private JTextField pathFiled = new JTextField(40);
    private JTextField extFiled = new JTextField(5);


    public NorthPanel() {
        setLayout(new FlowLayout());
        add(new JLabel("Wpisz ścieżkę do foledru oraz rozszerzenie (bez kropki)"));

        pathFiled.setText("C:\\Users\\{user_name}\\Desktop\\testfolder");
        pathFiled.selectAll();
        add(pathFiled);
        add(new JLabel("."));

        extFiled.setText("txt");
        extFiled.selectAll();
        add(extFiled);
    }

    public JTextField getPathFiled() {
        return pathFiled;
    }

    public JTextField getExtFiled() {
        return extFiled;
    }
}
