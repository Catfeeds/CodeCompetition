package com.isoftstone.pmit.project.hrbp.util;



import java.io.*;

public class ImageUtil {
    // 读取本地图片获取输入流
    public static FileInputStream readImage(String path) throws IOException {
        return  new FileInputStream(new File(path));
    }
    //读取表中图片获取输出流
    public static void readBin2Image(InputStream inputStream,String targetPath){
        File file = new File(targetPath);
        String path = targetPath.substring(0,targetPath.indexOf("\\"));
        if (!file.exists()){
            new File(path).mkdir();
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = inputStream.read(buf)) != -1){
                fileOutputStream.write(buf,0,len);
            }
            fileOutputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null != fileOutputStream){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
