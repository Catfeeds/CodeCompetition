package com.isoftstone.pmit.system.staff.entity;

import java.io.Serializable;

/**
 * @author yihur
 * @date 2018/11/2
 * 员工角色表
 */
public class StaffRole implements Serializable {

    /** 员工工号 */
    private Integer staffISSTNum;
    /** 岗位ID */
    private Integer postId;

    public Integer getStaffISSTNum() {
        return staffISSTNum;
    }

    public void setStaffISSTNum(Integer staffISSTNum) {
        this.staffISSTNum = staffISSTNum;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }
}
