import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI.Window window = new GUI.Window();
                window.setVisible(true);
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });






    }


}




