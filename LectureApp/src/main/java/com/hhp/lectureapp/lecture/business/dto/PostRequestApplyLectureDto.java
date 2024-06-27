package com.hhp.lectureapp.lecture.business.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestApplyLectureDto {
    private long userId;
    private long sessionId;
}
