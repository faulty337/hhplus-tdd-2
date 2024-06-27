package com.hhp.lectureapp.lecture.business.dto;

public record GetLectureDto (
        long lectureId,
        int userCount,
        int userLimit
){
}
