package com.hhp.lectureapp.lecture.business.domain;

import com.hhp.lectureapp.common.BaseEntity;
import com.hhp.lectureapp.lecture.persistence.LectureApplicationId;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class LectureApplicationDomain extends BaseEntity {

    private long userId;
    private long sessionId;

    public LectureApplicationDomain(long userId, long sessionId, LocalDateTime createdAt) {
        super(createdAt);
        this.userId = userId;
        this.sessionId = sessionId;
    }

    public LectureApplicationDomain(long userId, long sessionId) {
        this.userId = userId;
        this.sessionId = sessionId;
    }
}
