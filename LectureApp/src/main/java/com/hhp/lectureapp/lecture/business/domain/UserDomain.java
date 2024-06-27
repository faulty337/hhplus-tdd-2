package com.hhp.lectureapp.lecture.business.domain;

import com.hhp.lectureapp.common.BaseEntity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserDomain {
    private long id;

    private LocalDateTime createdAt;

    public UserDomain(long id, LocalDateTime createdAt) {
        this.id = id;
        this.createdAt = createdAt;
    }
}
