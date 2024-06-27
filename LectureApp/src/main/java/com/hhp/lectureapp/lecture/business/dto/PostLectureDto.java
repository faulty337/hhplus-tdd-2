package com.hhp.lectureapp.lecture.business.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostLectureDto {
    private long sessionId;
    private String title;
    private int userCount;

}
