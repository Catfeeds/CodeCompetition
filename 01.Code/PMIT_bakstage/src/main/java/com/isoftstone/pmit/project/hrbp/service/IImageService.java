package com.isoftstone.pmit.project.hrbp.service;

import org.springframework.web.multipart.MultipartFile;

public interface IImageService {


    boolean saveLifeImage(MultipartFile file, String employeeID);

    boolean saveDocumentImage(MultipartFile file, String employeeID);
}
