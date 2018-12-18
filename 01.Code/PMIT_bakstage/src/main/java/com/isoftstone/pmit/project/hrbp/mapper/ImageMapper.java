package com.isoftstone.pmit.project.hrbp.mapper;

import com.isoftstone.pmit.project.hrbp.entity.PersonalStyle;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ImageMapper {

    boolean insertLifeImagePath(PersonalStyle personalStyle);

    boolean insertDocumentImagePath(PersonalStyle personalStyle);

}
