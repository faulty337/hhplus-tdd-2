package com.hhp.lectureapp.lecture.business.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LectureSessionDomain {
    private long id;

    private long lectureId;

    private int applicationLimit;

    private int currentApplications = 0;

    private boolean isFull = false;

    private LocalDateTime openedAt;

    private LocalDateTime createdAt;

    public void applyUser() {
        currentApplications++;
        if(currentApplications == applicationLimit) {
            isFull = true;
        }
    }
}
