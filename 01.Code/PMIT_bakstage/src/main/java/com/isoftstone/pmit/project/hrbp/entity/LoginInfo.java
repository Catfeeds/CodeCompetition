package com.isoftstone.pmit.project.hrbp.entity;

import com.isoftstone.pmit.project.hrbp.util.IConstants;

import java.io.Serializable;

public class LoginInfo implements Serializable {

    // 状态码:0是成功 1是失败
    private int status;

    // 异常或其他主要提示信息
    private String message;

    // 返回的具体数据
    private Object data;

    public LoginInfo() {
        status = IConstants.RESULT_INT_ERROR;
        message = "";
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JsonBean [status=" + status + ", message=" + message
                + ", jsonData=" + String.valueOf(data) + "]";
    }
}