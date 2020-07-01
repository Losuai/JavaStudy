package io;

import java.io.File;

public class TestFile {
   static long max = 0;
   static long min = Integer.MAX_VALUE;
   static File maxFile = null;
   static File minFile = null;

    public static void findFiles(File file){
        if (file.isFile()){
            if (file.length() > max){
                max = file.length();
                maxFile = file;
            }
            if (file.length() < min && file.    length()!= 0){
                min = file.length();
                minFile = file;
            }
            return;
        }
        if (file.isDirectory()){
            File[] files = file.listFiles();
            if (files!=null)
                for (File f: files){
                    findFiles(f);
                }
        }
    }
    public static void main(String[] args) {
        File file  = new File("C:\\windows");
        findFiles(file);
        System.out.println("max:" + maxFile.getAbsoluteFile()+ " length:"+ max);
        System.out.println("min:" + minFile.getAbsoluteFile()+ " length:"+ min);

    }
}
