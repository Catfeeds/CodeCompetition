package com.isoftstone.pmit.project.hrbp.entity;

/**
 * 用户管理分页排序处理类
 * @author lf
 */
public class EmpAndPageInfo {
    private PageInfo pageInfo;
    private EmpInformationResult empInformationResult;

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public EmpInformationResult getEmpInformationResult() {
        return empInformationResult;
    }

    public void setEmpInformationResult(EmpInformationResult empInformationResult) {
        this.empInformationResult = empInformationResult;
    }
}
