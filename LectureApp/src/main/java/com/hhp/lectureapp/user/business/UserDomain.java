package com.hhp.lectureapp.user.business;

import java.time.LocalDateTime;

public class UserDomain {
    private long id;

    private LocalDateTime createdAt;


    public UserDomain(long id, LocalDateTime createdAt) {
        this.id = id;
        this.createdAt = createdAt;
    }
}
