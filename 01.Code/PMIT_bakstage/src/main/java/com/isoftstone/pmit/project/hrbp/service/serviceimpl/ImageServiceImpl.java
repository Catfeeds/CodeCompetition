package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.isoftstone.pmit.project.hrbp.entity.PersonalStyle;
import com.isoftstone.pmit.project.hrbp.mapper.ImageMapper;
import com.isoftstone.pmit.project.hrbp.service.IImageService;
import com.isoftstone.pmit.project.hrbp.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class ImageServiceImpl implements IImageService {

    @Autowired
    private ImageMapper imageMapper;

    @Override
    public String saveLifeImage(MultipartFile file, String employeeID) {
        String lifefilePath = null;
        if (!file.isEmpty()) {
            try {
                InputStream inputStream = file.getInputStream();
                // 文件保存路径
                lifefilePath = "src/main/resources/image/life/"+ file.getOriginalFilename();
                ImageUtil.readBin2Image(inputStream,lifefilePath);
                PersonalStyle personalStyle = new PersonalStyle();
                personalStyle.setEmployeeID(employeeID);
                personalStyle.setPhotosOfLife(lifefilePath);
                List<String> strings = imageMapper.checkPersonalIsNo();
                if (!strings.contains(employeeID)){
                    imageMapper.insertNoEmployLifeImage(personalStyle);
                }
                imageMapper.insertLifeImagePath(personalStyle);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return lifefilePath;
    }

    @Override
    public String saveDocumentImage(MultipartFile file, String employeeID) {
        String docFilePath = null;
        if (!file.isEmpty()) {
            try {
                InputStream inputStream = file.getInputStream();
                docFilePath = "src/main/resources/image/document/"+file.getOriginalFilename();
                ImageUtil.readBin2Image(inputStream,docFilePath);
                PersonalStyle personalStyle = new PersonalStyle();
                personalStyle.setEmployeeID(employeeID);
                personalStyle.setDocumentPhoto(docFilePath);
                List<String> strings = imageMapper.checkPersonalIsNo();
                if (!strings.contains(employeeID)){
                    imageMapper.insetNoEmployDocmentImage(personalStyle);
                }
                 imageMapper.insertDocumentImagePath(personalStyle);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return docFilePath;
    }

}
