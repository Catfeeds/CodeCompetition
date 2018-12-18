package com.isoftstone.pmit.project.hrbp.service;

import com.github.pagehelper.PageInfo;
import com.isoftstone.pmit.project.hrbp.entity.*;

import java.util.List;
import java.util.Map;

public interface IGradeSheetService {
    StaffScoreInfos getColumnNameService(PersonalScoreParameter parameter);

    /**获取所有人的成绩*/
    PageInfo<PersonalScore> getAllPersonalScores(PersonalScoreParam param);

    boolean deletePersonalScores(PersonalScore personalScore);

    List<PersonalTranAndDimeScore> getPersonalTransactionInfo(PersonalScore personalScore);

    boolean updatePersonalTraningScores(PersonalScore parameter);

}
