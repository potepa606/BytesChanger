package GUI;

import javax.swing.*;

public class Window extends JFrame {

    public Window(){
        setSize(500, 600);
        setLocation(400, 100);
        setTitle("QBS App");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false); //nie można zmieniać wielkości okna

        Panel panel = new Panel();
        add(panel);
    }

}
