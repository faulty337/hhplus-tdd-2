package com.hhp.lectureapp.lecture.persistence;

import com.hhp.lectureapp.lecture.persistence.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<Users, Long> {
}
