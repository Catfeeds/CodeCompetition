package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.isoftstone.pmit.project.hrbp.entity.PersonalPhoto;
import com.isoftstone.pmit.project.hrbp.entity.PersonalStyle;
import com.isoftstone.pmit.project.hrbp.mapper.ImageMapper;
import com.isoftstone.pmit.project.hrbp.service.IImageService;
import com.isoftstone.pmit.project.hrbp.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.InputStream;

@Service
public class ImageServiceImpl implements IImageService {

    @Autowired
    private ImageMapper imageMapper;
    public  void readImage2DB( MultipartFile file){
        if (!file.isEmpty()){
            BASE64Encoder encoder = new BASE64Encoder();
            try {
                //通过base64来转换图片
                String image = encoder.encode(file.getBytes());
//               Blob blob = new SerialBlob(image.getBytes("UTF-8"));
//                PersonalPhoto personalPhoto = new PersonalPhoto();
//                personalPhoto.setPhotoDocument(image);
//                personalPhoto.setEmployeeID("10008611");
//                imageMapper.insertImage(personalPhoto);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public PersonalStyle getPersonalImage(String employeeID) {
        PersonalStyle personalImage = imageMapper.getPersonalImage(employeeID);
        return personalImage;
    }

    @Override
    public boolean saveLifeImage(MultipartFile file, String employeeID) {
        boolean path = false;
        if (!file.isEmpty()) {
            try {
                InputStream inputStream = file.getInputStream();
                // 文件保存路径
                String lifefilePath = "src/main/resources/image/life/"+ file.getOriginalFilename();
                ImageUtil.readBin2Image(inputStream,lifefilePath);
                PersonalStyle personalStyle = new PersonalStyle();
                personalStyle.setEmployeeID(employeeID);
                personalStyle.setPhotosOfLife(lifefilePath);
                path = imageMapper.insertLifeImagePath(personalStyle);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return path;
    }

    @Override
    public boolean saveDocumentImage(MultipartFile file, String employeeID) {

        boolean document = false;
        if (!file.isEmpty()) {
            try {
                InputStream inputStream = file.getInputStream();
                String docFilePath = "src/main/resources/image/document/"+file.getOriginalFilename();
                ImageUtil.readBin2Image(inputStream,docFilePath);
                PersonalStyle personalStyle = new PersonalStyle();
                personalStyle.setEmployeeID(employeeID);
                personalStyle.setDocumentPhoto(docFilePath);
                document = imageMapper.insertDocumentImagePath(personalStyle);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return document;
    }

}
