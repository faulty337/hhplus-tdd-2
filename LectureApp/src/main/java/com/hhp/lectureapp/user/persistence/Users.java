package com.hhp.lectureapp.user.persistence;

import com.hhp.lectureapp.user.business.UserDomain;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime createdAt;

    public UserDomain toDomain(){
        return new UserDomain(this.id, this.createdAt);
    }
}
