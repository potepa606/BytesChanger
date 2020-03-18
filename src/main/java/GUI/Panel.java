package GUI;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;

public class Panel extends JPanel {

    private NorthPanel northPanel = new NorthPanel();
    private CenterPanel centerPanel = new CenterPanel(northPanel);



    public Panel(){
        setLayout(new BorderLayout());
        add(northPanel, BorderLayout.NORTH);
        northPanel.setPreferredSize(new Dimension(500, 60));
        northPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        centerPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
        add(centerPanel, BorderLayout.CENTER);
    }


}
