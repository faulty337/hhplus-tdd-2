package com.hhp.lectureapp.lecture.business;

import com.hhp.lectureapp.lecture.controller.dto.GetLectureDto;

import java.util.List;

public interface LectureService {
    public List<GetLectureDto> getLectureList();
}
