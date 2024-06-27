package com.hhp.lectureapp.lecture.business.domain;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class LectureSessionDomain {
    private long id;

    private long lectureId;

    private int applicationLimit;

    private int currentApplications = 0;

    private boolean isFull = false;

    private LocalDateTime openedAt;

    private LocalDateTime createdAt;

}
