package com.hhp.lectureapp.lecture.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LectureSessionJpaRepository extends JpaRepository<LectureSession, Long> {
    Optional<LectureSession> findByIdAndLectureId(long id, long lectureId);
}
