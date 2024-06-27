package com.hhp.lectureapp.lecture.business.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class LectureApplicationDomain {

    private long userId;
    private long sessionId;

    private LocalDateTime createdAt;

    public LectureApplicationDomain(long userId, long sessionId, LocalDateTime createdAt) {
        this.userId = userId;
        this.sessionId = sessionId;
        this.createdAt = createdAt;
    }

    public LectureApplicationDomain(long userId, long sessionId) {
        this.userId = userId;
        this.sessionId = sessionId;
    }
}
