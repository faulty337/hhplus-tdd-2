package com.hhp.lectureapp.lecture.controller;


import com.hhp.lectureapp.lecture.controller.dto.GetLectureDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lectures")
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;

    @GetMapping
    public ResponseEntity<List<GetLectureDto>> getLectureList() {
        return null;
    }
}
