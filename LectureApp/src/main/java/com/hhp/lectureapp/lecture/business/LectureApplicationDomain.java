package com.hhp.lectureapp.lecture.business;

import com.hhp.lectureapp.common.BaseEntity;
import com.hhp.lectureapp.lecture.persistence.LectureApplicationId;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class LectureApplicationDomain extends BaseEntity {

    private LectureApplicationId id;

    public LectureApplicationDomain(LectureApplicationId id, LocalDateTime createdAt) {
        super(createdAt);
        this.id = id;
    }
}
