package com.hhp.lectureapp.lecture.persistence;

import com.hhp.lectureapp.lecture.persistence.entity.LectureSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LectureSessionJpaRepository extends JpaRepository<LectureSession, Long> {
    Optional<LectureSession> findByIdAndLectureId(long id, long lectureId);
}
