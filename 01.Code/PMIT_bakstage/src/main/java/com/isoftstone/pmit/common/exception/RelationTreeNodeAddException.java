package com.isoftstone.pmit.common.exception;

public class RelationTreeNodeAddException extends RuntimeException {

    private String message;

    public RelationTreeNodeAddException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
