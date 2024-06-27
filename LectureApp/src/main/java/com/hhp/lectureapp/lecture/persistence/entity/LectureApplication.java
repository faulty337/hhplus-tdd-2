package com.hhp.lectureapp.lecture.persistence.entity;

import com.hhp.lectureapp.common.BaseEntity;
import com.hhp.lectureapp.lecture.business.domain.LectureApplicationDomain;
import com.hhp.lectureapp.lecture.persistence.LectureApplicationId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class LectureApplication extends BaseEntity {
    @EmbeddedId
    private LectureApplicationId id;

    private LocalDateTime createdAt;

    public LectureApplication(LectureApplicationId id) {
        this.id = id;
    }

    public LectureApplicationDomain toDomain() {
        return new LectureApplicationDomain(this.id.getUserId(), this.id.getLectureSessionId(), createdAt);
    }

}
