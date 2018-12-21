package com.isoftstone.pmit.project.hrbp.mapper;

import java.util.List;
import java.util.Map;

import com.isoftstone.pmit.project.hrbp.entity.PersonalAffairdimension;
import com.isoftstone.pmit.project.hrbp.entity.PersonalHistoryInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PersonalCenterMapper {

	List<Map<String, Object>> getPendingSolved(Map<String, Object> paramMap);

	List<Map<String, Object>> getDimensionByAffair(Map<String, Object> paramMap);

	List<Map<String, Object>> queryHistorySolved(Map<String, Object> paramMap);

	List<Map<String,Object>> getStaffAffair(Map<String,Object> pendingSolvedMap);

	void saveEvaluation(PersonalAffairdimension personalAffairdimension);

    void updateIsSolved(PersonalAffairdimension personalAffairdimension);
}
