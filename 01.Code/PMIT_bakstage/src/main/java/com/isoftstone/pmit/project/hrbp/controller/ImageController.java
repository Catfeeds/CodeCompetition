package com.isoftstone.pmit.project.hrbp.controller;


import com.isoftstone.pmit.common.util.AjaxResult;
import com.isoftstone.pmit.project.hrbp.entity.PersonalPhoto;
import com.isoftstone.pmit.project.hrbp.entity.PersonalStyle;
import com.isoftstone.pmit.project.hrbp.service.IImageService;
import com.isoftstone.pmit.project.hrbp.util.ImageUtil;
import com.mysql.jdbc.Blob;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.sql.SQLException;
import java.util.Base64;

@RestController
@RequestMapping(value = "/hrbp/image")
@Api(value = "个人图片展示", tags = {"个人图片展示"})
public class ImageController {

    @Autowired
    private IImageService imageService;

    @RequestMapping(value = "/insetImage", method = { RequestMethod.POST })
    @ApiOperation(value="将图片存入数据库", notes="将图片存入数据库")
    public String readImageInDB(@RequestBody MultipartFile file,String employeeID){
        try{
             imageService.readImage2DB(file);
        }catch (Exception e){
            AjaxResult.returnToMessage(false,e.getMessage());
        }
        return AjaxResult.returnToMessage(true,"insert Image Success");
    }

    @RequestMapping(value = "/getImage", method = { RequestMethod.POST })
    @ApiOperation(value="将图片从数据库中取出", notes="将图片从数据库取出")
    public boolean upload(@RequestParam String employeeID) {
//        PersonalStyle personalImage = imageService.getPersonalImage(employeeID);
       /* byte[] decode = (String) personalImage.getPhotosOfLife();
        if (imgString == null){
            return false;
        }
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decode = decoder.decode(imgString);
        for (int i = 0; i < decode.length; i++) {
            if (decode[i] < 0) {
                decode[i] += 256;
            }
        }*/
//        File file = new File("C:\\Users\\73466\\Desktop\\文件夹\\image\\20181214152726.jpg");
      /*  if (!file.exists()){
            try {
                file.createNewFile();
                OutputStream out = new FileOutputStream(file);
                out.write(decode);
                out.flush();
                out.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
*/


        /*byte[] decodeBuffer = null;
        try {
            String data = new String(bytes, "UTF-8");
            BASE64Decoder decoder = new BASE64Decoder();
            decodeBuffer = decoder.decodeBuffer(data);
            for (int i = 0; i < decodeBuffer.length; i++) {
                if (decodeBuffer[i] < 0) {
                    decodeBuffer[i] += 256;
                }
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return true;
    }


    @RequestMapping(value = "/insertLifeImagePath", method = { RequestMethod.POST })
    @ApiOperation(value="将个人生活照骗路径保存到数据库", notes="将图片路径保存到数据库")
    public String saveLifeImage(@RequestBody MultipartFile file, String employeeID) {
        boolean result = false;
        try{
             result = imageService.saveLifeImage(file,employeeID);
        }catch (Exception e){
            AjaxResult.returnToMessage(false,e.getMessage());
        }
        return AjaxResult.returnToMessage(result,"insert Image Success");
    }

    @RequestMapping(value = "/insertDocumentImagePath", method = { RequestMethod.POST })
    @ApiOperation(value="将个人证件照路径保存到数据库", notes="将个人证件照路径保存到数据库")
    public String saveDocumentImage(@RequestBody MultipartFile file, String employeeID) {
        boolean result = false;
        try{
            result = imageService.saveDocumentImage(file,employeeID);
        }catch (Exception e){
            AjaxResult.returnToMessage(false,e.getMessage());
        }
        return AjaxResult.returnToMessage(result,"insert Image Success");
    }



}
