package com.isoftstone.pmit.project.hrbp.mapper;

import com.isoftstone.pmit.project.hrbp.entity.PersonalStyle;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ImageMapper {

    boolean insertLifeImagePath(PersonalStyle personalStyle);

    void insertNoEmployLifeImage(PersonalStyle personalStyle);
    void insetNoEmployDocmentImage(PersonalStyle personalStyle);
    List<String> checkPersonalIsNo();
    boolean insertDocumentImagePath(PersonalStyle personalStyle);

}
