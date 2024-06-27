package com.hhp.lectureapp.lecture.persistence;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class LectureApplicationId implements Serializable {

    private Long userId;
    private Long lectureSessionId;

}