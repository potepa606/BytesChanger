package Controller;

import java.io.*;
import java.util.ArrayList;

public class WriterByte {

    String byteTOWrite = "106;97;106;107;111;"; // jajko - do zapisu
    String byteToRead = "107;117;114;119;97;"; // kurwa - do odczytu
    ArrayList<String> tabByteList = new ArrayList<>();
    String stringTabByte = "" ;
    String path;
    String subByte = "";

    public WriterByte(String path, byte[] tabByte) {
        this.path = path;
        for(byte b: tabByte)
            stringTabByte += (b+";");


//        String newStringTabByte = stringTabByte.replace(byteToRead, byteTOWrite);
//        String[] nSTB = newStringTabByte.split(";");
//        byte[] newByteTab = new byte[nSTB.length];
//        for (int i=0; i<nSTB.length;i++) {
//            newByteTab[i] = Byte.parseByte(nSTB[i]);
//        }
//
//        try {
//            FileOutputStream fos = new FileOutputStream(path);
//            fos.write(newByteTab);
//            fos.close();
//        }catch (IOException e){
//        }
//

//        byte[] byteToWiteTAB = new byte[tabofByteToWrite.length];
//        for(int i=0; i<tabofByteToWrite.length; i++){
//            byteToWiteTAB[i] = Byte.parseByte(tabofByteToWrite[i]);
//            System.out.print(byteToWiteTAB[i] +" ");
//        }
//        System.out.println("");
//        byte[] byteToReadTAB = new byte[tabofByteToRead.length];
//        for(int i=0; i<tabofByteToRead.length; i++){
//            byteToReadTAB[i] = Byte.parseByte(tabofByteToRead[i]);
//            System.out.print(byteToReadTAB[i] +" ");
//        }

    }


    public String getPath() {
        return path;
    }

    public boolean isExistByteInFile(String subByteString){

        boolean istnieje = false;
        if(!stringTabByte.equals("")){
//            int indexOfFirst = stringTabByte.indexOf(subByteString);
            istnieje  = stringTabByte.contains(subByteString);
            if(istnieje && !subByteString.equals("")) {
                subByte = subByteString;
                return true;
            }
        }
        return false;
    }

    public boolean changeSubByte(String newSubString){
        String allBytes = this.stringTabByte;
        String newStringTabByte = (";"+allBytes).replace(";"+subByte, ";"+newSubString);

        String test = newStringTabByte.substring(1,newStringTabByte.length()-1);

        String[] allByteTab = test.split(";");

        byte[] newByteTab = new byte[allByteTab.length];

        System.out.println("tablica bajtow po parsowaniu");
        for (int i =0 ; i<newByteTab.length;i++){
            newByteTab[i] = Byte.parseByte(allByteTab[i]);
            System.out.print( newByteTab[i]+" ");
        }
        try {
            FileOutputStream fos = new FileOutputStream(path);
            fos.write(newByteTab);
            fos.flush();
            fos.close();
            return true;
        }catch (IOException e){
            System.out.println("nie udalo sie zapisac do pliku");
            return false;
        }


    }

}
