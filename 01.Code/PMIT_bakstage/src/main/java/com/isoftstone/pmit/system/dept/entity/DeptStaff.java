package com.isoftstone.pmit.system.dept.entity;

/**
 * @author yihur
 * @date 2018/11/2
 * 员工部门对应表
 */
public class DeptStaff {

    /** 员工工号 */
    private Integer staffISSTNum;

    /** 部门ID */
    private Integer deptId;

    public Integer getStaffISSTNum() {
        return staffISSTNum;
    }

    public void setStaffISSTNum(Integer staffISSTNum) {
        this.staffISSTNum = staffISSTNum;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
}
