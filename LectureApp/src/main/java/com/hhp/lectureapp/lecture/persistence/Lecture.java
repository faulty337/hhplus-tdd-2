package com.hhp.lectureapp.lecture.persistence;

import com.hhp.lectureapp.lecture.business.LectureDomain;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private int userLimit;

    private int userCount = 0;


    private boolean isFull = false;

    private LocalDateTime openedAt;

    private LocalDateTime createdAt;

    public LectureDomain toDomain() {
        return new LectureDomain(id, userLimit, userCount, isFull, openedAt, createdAt);
    }

    public Lecture(int userLimit, int userCount, boolean isFull, LocalDateTime openedAt) {
        this.userLimit = userLimit;
        this.userCount = userCount;
        this.isFull = isFull;
        this.openedAt = openedAt;
        this.createdAt = LocalDateTime.now();
    }
}
