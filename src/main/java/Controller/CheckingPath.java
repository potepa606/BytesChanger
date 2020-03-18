package Controller;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CheckingPath {

    File tmpDir;
//    ArrayList<String> subFoldersList = new ArrayList<>();
    ArrayList<String> selectFiles = new ArrayList<>();



    public CheckingPath(){
    }

    public boolean checkPathExist(String path){
        tmpDir = new File(path);
        boolean isExist = tmpDir.exists();
        return isExist;
    }

    public File getTmpDir() {
        return tmpDir;
    }

    public void  addSubFoldersToList(String path, String ext){
        File subFolder = new File(path);
//        subFoldersList.add(path);
        for (String object :subFolder.list()) {
            String subPath = path+"\\"+object;
            File fileObject = new File(subPath);

            if(fileObject.isFile() && object.endsWith("."+ext)){
                selectFiles.add(subPath);

            } else if(fileObject.isDirectory()){
                addSubFoldersToList(subPath,ext);
            }
        }
    }
    public ArrayList<String> getSelectFiles() {
        return selectFiles;
    }
}
