package com.isoftstone.pmit.project.hrbp.util;

import java.io.*;

public class ReadFile {

    public static void  main(String[] args){
        String path ="D://eclipse.zip";
        readFile(path);
    }

    public static void readFile(String path){
        File file = null;
        if (path != null){
             file = new File(path);
        }else {
            return;
        }
        InputStream inputStream = null;
        try  {
            inputStream = new FileInputStream(file);
            int len = 0;
            byte[] var =new byte[1024];
            while ((len= inputStream.read(var)) != -1){
                String ss = new String(var,0,len);
                System.out.print("ssssssssssss"+ss);
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
