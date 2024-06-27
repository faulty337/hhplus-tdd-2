package com.hhp.lectureapp.lecture.persistence.entity;

import com.hhp.lectureapp.common.BaseEntity;
import com.hhp.lectureapp.lecture.business.domain.UserDomain;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Users extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public UserDomain toDomain(){
        return new UserDomain(this.id);
    }
}
