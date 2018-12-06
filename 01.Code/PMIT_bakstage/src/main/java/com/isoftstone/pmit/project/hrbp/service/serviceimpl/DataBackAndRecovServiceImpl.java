package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.isoftstone.pmit.project.hrbp.entity.DataBackUpInfo;
import com.isoftstone.pmit.project.hrbp.entity.ExaminationAffair;
import com.isoftstone.pmit.project.hrbp.mapper.DataBackAndRecovMapper;
import com.isoftstone.pmit.project.hrbp.service.IDataBackAndRecovService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@Configuration
public class DataBackAndRecovServiceImpl implements IDataBackAndRecovService {

    @Value("${spring.datasource.druid.master.url}")
    public  String host;

    @Value("${spring.datasource.druid.master.username}")
    public String username;


    @Value("${spring.datasource.druid.master.password}")
    public String password;

    @Value("${spring.datasource.druid.slave.url}")
    public  String slavehost;


    @Value("${spring.datasource.druid.slave.username}")
    public String slaveusername;


    @Value("${spring.datasource.druid.slave.password}")
    public String slavepassword;

    @Autowired
    private DataBackAndRecovMapper dataBackAndRecovMapper;

    @Override
    public  boolean backup( String savePath, String fileName, String databaseName) {

        fileName +=".sql";
        File saveFile = new File(savePath);
        if (!saveFile.exists()) {
            saveFile.mkdirs();
        }
        if (!savePath.endsWith(File.separator)) {
            savePath = savePath + File.separator;
        }
        String hostIP = getHostIP(host);
        //拼接命令行的命令
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mysqldump").append(" --opt").append(" -h").append(hostIP);
        stringBuilder.append(" --user=").append(username).append(" --password=").append(password)
                .append(" --lock-all-tables=true");
        stringBuilder.append(" --result-file=").append(savePath + fileName).append(" --default-character-set=utf8 ")
                .append(databaseName);
        try {
            //调用外部执行exe文件的javaAPI
            Process process = Runtime.getRuntime().exec(stringBuilder.toString());
            if (process.waitFor() == 0) {// 0 表示线程正常终止。
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean recover(String filepath,String database) {

        String slavehostIP = getHostIP(slavehost);
        /*StringBuilder createDatabase = new StringBuilder();
        createDatabase.append("mysqladmin -h").append(slavehostIP).append(" -u").append(slaveusername)
                .append(" -p").append(slavepassword).append(" create ").append(database);*/

        StringBuilder recoverDatabase = new StringBuilder();
        recoverDatabase.append("mysql -h").append(slavehostIP).append(" -u").append(slaveusername).append(" -p")
                .append(slavepassword).append(" ").append(database).append(" < ").append(filepath);
        String[] cmd = { "cmd", "/c", recoverDatabase.toString() };
        try {
            //Runtime.getRuntime().exec(createDatabase.toString());
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void saveBackUpInfo(DataBackUpInfo dataBackUpInfo){
        dataBackAndRecovMapper.saveBackUpInfo(dataBackUpInfo);
    }

    @Override
    public PageInfo<DataBackUpInfo> findAllDataInfoList(int pageNum, int pageSize){

        List<DataBackUpInfo> dataInfoList = dataBackAndRecovMapper.findAllDataInfoList();
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<DataBackUpInfo> dataPageInfoList = new PageInfo<>(dataInfoList);
        dataPageInfoList.setTotal(dataInfoList.size());
        System.out.println(dataInfoList.size());
        return dataPageInfoList;
    }

    //获取配置文件中的ip，如果没有则返回默认值localhost
    private String getHostIP(String host){
        if(null != host && !"".equals(host)){
            String[] s =  host.split(":");
            return s[2].substring(2);
        }
        return "localhost";
    }
}
