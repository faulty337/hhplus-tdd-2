package com.hhp.lectureapp.lecture.controller.dto;

public record GetLectureDto (
        long lectureId,
        int userCount,
        int userLimit
){
}
