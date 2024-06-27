package com.hhp.lectureapp.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    NOT_FOUND_USER_ID(HttpStatus.NOT_FOUND, "userId를 찾을 수 없습니다."),
    NOT_FOUND_LECTURE_ID(HttpStatus.NOT_FOUND, "lectureId를 찾을 수 없습니다."),
    NOT_FOUND_LECTURE(HttpStatus.NOT_FOUND, "강의를 찾을 수 없습니다."),
    FULL_LECTURE(HttpStatus.BAD_REQUEST, "강의 인원이 가득찼습니다."),
    DUPLICATE_USER_APPLICATION(HttpStatus.BAD_REQUEST, "이미 신청된 강의입니다.");


    private HttpStatus status;
    private String msg;

    ErrorCode(HttpStatus status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
