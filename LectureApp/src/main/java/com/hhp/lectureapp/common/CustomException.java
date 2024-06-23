package com.hhp.lectureapp.common;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    public CustomException(ErrorCode e) {
        this.statusCode = e.getStatus().value();
        this.msg = e.getMsg();
    }

    private int statusCode;
    private String msg;

    @Override
    public String getMessage() {
        return msg;
    }
}
