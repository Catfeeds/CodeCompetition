package com.isoftstone.pmit.project.hrbp.service;

import com.isoftstone.pmit.project.hrbp.entity.PersonalStyle;
import org.springframework.web.multipart.MultipartFile;

public interface IImageService {
    void readImage2DB( MultipartFile file);

    PersonalStyle getPersonalImage(String employeeID);

    boolean saveLifeImage(MultipartFile file, String employeeID);

    boolean saveDocumentImage(MultipartFile file, String employeeID);
}
