package com.isoftstone.pmit.project.hrbp.service;

import com.github.pagehelper.PageInfo;
import com.isoftstone.pmit.project.hrbp.entity.DataBackUpInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.List;

public interface IDataBackAndRecovService {


    /**
     * 输出备份文件到制定目录
     * @param savePath
     * @param fileName
     * @param databaseName
     * @return
     */
    public boolean backup(String savePath, String fileName,
                                   String databaseName);

    /**
     * 从本地获。sql文件，备份到指定的数据库
     * @param filepath
     * @param database
     * @return
     */
    public boolean recover(String filepath,String database);
    /**
     * 保存用户数据信息
     * @param dataBackUpInfo
     */
    void saveBackUpInfo(DataBackUpInfo dataBackUpInfo);

    /**
     * 查询备份数据列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<DataBackUpInfo> findAllDataInfoList(int pageNum, int pageSize);

}
