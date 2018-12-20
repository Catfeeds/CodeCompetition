package com.isoftstone.pmit.system.exceloperation.mapper;

import com.isoftstone.pmit.project.hrbp.entity.ScoreTransaction;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ExcelTransactionMapper {

    List<ScoreTransaction> getTransaction();

    int insertTransaction(List<ScoreTransaction> newTransInsert);

    int updateTransaction(List<ScoreTransaction> newTransUpdate);
}
