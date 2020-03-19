package GUI;

import Controller.CheckingPath;
import Controller.ReaderByte;
import Controller.WriterByte;
import GUI.actionPanel.AddActionsPanel;
import GUI.setPanel.AddBytesPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static GUI.actionPanel.AddActionsPanel.textAreaInfo;

public class CenterPanel extends JPanel {

    public static ArrayList<ReaderByte>  foundedFilesByte = new ArrayList<>();
    public static ArrayList<WriterByte>  wirtersBytesList = new ArrayList<>();


    JButton checkPathButton = new JButton("Sprawdź");
    JTextField statusPath = new JTextField();
    public static CheckingPath checkingPath = new CheckingPath();

    public static AddBytesPanel addBytesPanel;
    public static AddActionsPanel addActionsPanel;




    public CenterPanel(NorthPanel northPanel){

//        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER);
        setLayout(null);

        checkPathButton.setBounds(200,10,100,20);
        add(checkPathButton);

        // Status Path fild
        statusPath.setBounds(45,50,400,20);
        statusPath.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        statusPath.setEditable(false);
        add(statusPath);

        addBytesPanel = new AddBytesPanel();
        addBytesPanel.setBounds(45,80,400,185);
        add(addBytesPanel);

        addActionsPanel = new AddActionsPanel();
        addActionsPanel.setBounds(45,270,400,210);
        add(addActionsPanel);





        checkPathButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkingPath.getSelectFiles().clear();
                String textPath = northPanel.getPathFiled().getText();
                String extension = northPanel.getExtFiled().getText();
                boolean isExistPath = checkingPath.checkPathExist(textPath);
                boolean isFile = checkingPath.getTmpDir().isFile();
                if(isExistPath && !isFile) {
                    checkingPath.addSubFoldersToList(textPath,extension);

                    ArrayList<String> plikiDOtestu = checkingPath.getSelectFiles(); // C:\Users\48513\Desktop\testfolder\ads.txt\abctest
                    foundedFilesByte.clear();
                    ReaderByte readerByte = null;
                    StringBuilder foundedFiles = new StringBuilder("Znalezione Pliki: \n\n");
                    for (String path : plikiDOtestu) {
                        readerByte = new ReaderByte(path);
                        foundedFilesByte.add(readerByte);
                        String[] plikName = path.split("\\\\");
                        foundedFiles.append(plikName[plikName.length-1] + "\n");
                    }
                    textAreaInfo.setText(foundedFiles.toString());





//                    WriterByte writerByte = new WriterByte(plikDOtestu, readerByte.getByteFile());
                    statusPath.setText("Lokalizacja" + " -- OK ! \t Znaleziono: " + checkingPath.getSelectFiles().size() + " file/s o rozszerzeniu: " + extension);

                   if(checkingPath.getSelectFiles().size() != 0){
                       addBytesPanel.ON_AddBytesPanel(true);
                       addActionsPanel.ON_ActionsPanel(true);

                       addActionsPanel.getChangeButt().setEnabled(false);
                   }else{
                       addBytesPanel.ON_AddBytesPanel(false);
                       addActionsPanel.ON_ActionsPanel(false);

                   }





                }
                else{
                    statusPath.setText("Taka ścieżka nie istnieje !");
                    addBytesPanel.ON_AddBytesPanel(false);
                    addActionsPanel.ON_ActionsPanel(false);
                }


                System.out.println("\n ----------bytes -------------");
                for (ReaderByte s : foundedFilesByte) {
                    System.out.println(s.getPath());
                    byte[] bytesFile = s.getByteFile();
                    for (byte b : bytesFile) {
                        System.out.print(b+" ");
                    }
                    System.out.println("");
                }


            }
        });





    }
}
