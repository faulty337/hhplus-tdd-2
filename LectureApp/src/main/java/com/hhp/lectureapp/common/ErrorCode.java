package com.hhp.lectureapp.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    NOT_FOUND_USER_ID(HttpStatus.NOT_FOUND, "userId를 찾을 수 없습니다."),
    NOT_FOUND_LECTURE_ID(HttpStatus.NOT_FOUND, "lectureId를 찾을 수 없습니다.");


    private HttpStatus status;
    private String msg;

    ErrorCode(HttpStatus status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
