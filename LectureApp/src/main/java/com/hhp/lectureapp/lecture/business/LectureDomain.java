package com.hhp.lectureapp.lecture.business;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class LectureDomain {
    private long id;

    private int userLimit;

    private int userCount;

    private boolean isFull;

    private LocalDateTime openedAt;

    private LocalDateTime createdAt;


    public LectureDomain(Long id, int userLimit, int userCount, boolean isFull, LocalDateTime openedAt, LocalDateTime createdAt) {
        this.id = id;
        this.userLimit = userLimit;
        this.userCount = userCount;
        this.isFull = isFull;
        this.openedAt = openedAt;
        this.createdAt = createdAt;
    }
}
