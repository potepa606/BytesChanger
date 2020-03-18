package Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class ReaderByte {


    byte[] byteFile;
    String path;

    public ReaderByte(String path) {
        this.path = path;
        File file = new File(path);
        try {
            System.out.println(path);
            byteFile = Files.readAllBytes(file.toPath());
//            for (byte b : byteFile) {
//                System.out.print(b + " ");
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPath() {
        return path;
    }

    public byte[] getByteFile() {
        return this.byteFile;
    }
}
