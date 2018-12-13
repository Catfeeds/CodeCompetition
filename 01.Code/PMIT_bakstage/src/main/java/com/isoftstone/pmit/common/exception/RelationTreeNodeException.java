package com.isoftstone.pmit.common.exception;

public class RelationTreeNodeException extends RuntimeException {

    private String message;

    public RelationTreeNodeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
