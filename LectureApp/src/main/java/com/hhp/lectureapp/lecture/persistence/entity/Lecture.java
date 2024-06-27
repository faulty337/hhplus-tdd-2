package com.hhp.lectureapp.lecture.persistence.entity;

import com.hhp.lectureapp.common.BaseEntity;
import com.hhp.lectureapp.lecture.business.domain.LectureDomain;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Lecture extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    public LectureDomain toDomain(){
        return new LectureDomain(this.id, this.title, this.getCreatedAt());
    }

}
