package com.hhp.lectureapp.lecture.business;

import com.hhp.lectureapp.lecture.controller.LectureService;
import com.hhp.lectureapp.lecture.controller.dto.GetLectureDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectureServiceImpl implements LectureService {

    @Override
    public List<GetLectureDto> getLectureList() {
        return List.of();
    }
}
