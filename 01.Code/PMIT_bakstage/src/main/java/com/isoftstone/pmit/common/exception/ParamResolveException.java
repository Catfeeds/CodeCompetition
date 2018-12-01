package com.isoftstone.pmit.common.exception;

public class ParamResolveException extends RuntimeException {

    private String paramName;

    public ParamResolveException() {
    }

    public ParamResolveException(String paramName) {
        super("Requird Param  [" + paramName + "]  Don't Exist in RequestBody");
        this.paramName = paramName;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void printStackTrace() {
        System.out.println(getMessage());
        super.printStackTrace();
    }
}
