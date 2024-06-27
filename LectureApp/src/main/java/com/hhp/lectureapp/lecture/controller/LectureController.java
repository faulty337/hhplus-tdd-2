package com.hhp.lectureapp.lecture.controller;


import com.hhp.lectureapp.lecture.business.LectureService;
import com.hhp.lectureapp.lecture.business.dto.GetLectureDto;
import com.hhp.lectureapp.lecture.business.dto.PostLectureDto;
import com.hhp.lectureapp.lecture.business.dto.PostRequestApplyLectureDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lecture")
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;

    @GetMapping
    public ResponseEntity<List<GetLectureDto>> getLectureList() {
        return new ResponseEntity<>(lectureService.getLectureList(), HttpStatus.OK);
    }

    @PostMapping("{lectureId}/apply")
    public ResponseEntity<PostLectureDto> applyLecture(
            @PathVariable long lectureId,
            @RequestBody PostRequestApplyLectureDto request
    ){
        return new ResponseEntity<>(lectureService.applyLecture(lectureId, request.getUserId(), request.getSessionId()), HttpStatus.OK);
    }
}
