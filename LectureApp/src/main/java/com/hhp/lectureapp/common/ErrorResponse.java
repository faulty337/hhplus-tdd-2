package com.hhp.lectureapp.common;

public record ErrorResponse (
        int statusCode,
        String msg
) {
}
