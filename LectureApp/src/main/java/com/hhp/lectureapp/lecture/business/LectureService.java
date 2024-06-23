package com.hhp.lectureapp.lecture.business;

import com.hhp.lectureapp.lecture.controller.dto.GetLectureDto;
import com.hhp.lectureapp.lecture.controller.dto.PostLectureDto;

import java.util.List;

public interface LectureService {
    List<GetLectureDto> getLectureList();

    PostLectureDto applyLecture(long lectureId, long userId);
}
