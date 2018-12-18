package com.isoftstone.pmit.project.hrbp.controller;


import com.isoftstone.pmit.common.util.AjaxResult;
import com.isoftstone.pmit.project.hrbp.service.IImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/hrbp/image")
@Api(value = "个人图片展示", tags = {"个人图片展示"})
public class ImageController {

    @Autowired
    private IImageService imageService;

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
