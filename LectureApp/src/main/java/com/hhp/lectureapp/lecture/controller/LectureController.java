package com.hhp.lectureapp.lecture.controller;


import com.hhp.lectureapp.lecture.business.LectureService;
import com.hhp.lectureapp.lecture.business.dto.GetLectureDto;
import com.hhp.lectureapp.lecture.business.dto.PostLectureDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lectures")
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;

    @GetMapping
    public ResponseEntity<List<GetLectureDto>> getLectureList() {
        return new ResponseEntity<>(lectureService.getLectureList(), HttpStatus.OK);
    }

    @PostMapping("/{lectureId}/apply")
    public ResponseEntity<PostLectureDto> applyLecture(
            @PathVariable long lectureId,
            @RequestBody long userId
    ){
        PostLectureDto postLectureDto = lectureService.applyLecture(lectureId, userId);
        return null;
    }
}
