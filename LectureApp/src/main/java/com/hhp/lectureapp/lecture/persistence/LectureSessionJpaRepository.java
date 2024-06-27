package com.hhp.lectureapp.lecture.persistence;

import com.hhp.lectureapp.lecture.persistence.entity.LectureSession;
import io.lettuce.core.dynamic.annotation.Param;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LectureSessionJpaRepository extends JpaRepository<LectureSession, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT ls FROM LectureSession ls WHERE ls.id = :sessionId AND ls.lectureId = :lectureId")
    Optional<LectureSession> findByIdAndLectureIdWithLock(@Param("sessionId") Long sessionId, @Param("lectureId") Long lectureId);

    List<LectureSession> findByIdNotInAndOpenedAtBefore(List<Long> sessionIds, LocalDateTime now);

}
