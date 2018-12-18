package com.isoftstone.pmit.project.hrbp.service;

import org.springframework.web.multipart.MultipartFile;

public interface IImageService {


    String saveLifeImage(MultipartFile file, String employeeID);

    String saveDocumentImage(MultipartFile file, String employeeID);
}
