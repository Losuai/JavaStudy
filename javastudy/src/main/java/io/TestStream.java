package io;

import java.io.*;
import java.util.Arrays;

public class TestStream {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\ASUS\\Desktop\\test\\1.txt");
        splitFile(file, 8 * 1024);
    }
    public static void splitFile(File srcFile,  int size){
        if (srcFile.length() == 0)
            throw new RuntimeException("文件长度为0");
        byte[] fileContent = new byte[(int) srcFile.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(srcFile);
            fileInputStream.read(fileContent);
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int fileSize = 0;
        if (fileContent.length/size == 0){
            fileSize = fileContent.length/size;
        }else {
            fileSize = fileContent.length/size + 1;
        }
        for (int i=0; i<fileSize; i++){
            String eachFileName = i + "-" + srcFile.getName() ;
            File eachFile = new File(srcFile.getParent(), eachFileName);
            byte[] eachContent;
            if (i != fileSize-1)
                eachContent = Arrays.copyOfRange(fileContent, size*i, size*(i+1));
            else
                eachContent = Arrays.copyOfRange(fileContent, size*i, fileContent.length);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(eachFile);
                fileOutputStream.write(eachContent);
                fileOutputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
