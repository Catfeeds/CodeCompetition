package com.isoftstone.pmit.project.hrbp.service;

import com.isoftstone.pmit.project.hrbp.entity.PersonalScoreParameter;
import com.isoftstone.pmit.project.hrbp.entity.StaffScoreInfos;

import java.util.List;
import java.util.Map;

public interface IGradeSheetService {
    StaffScoreInfos getColumnNameService(PersonalScoreParameter parameter);
}
