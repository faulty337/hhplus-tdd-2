package com.hhp.lectureapp.lecture.business;


import com.hhp.lectureapp.lecture.business.domain.LectureApplicationDomain;
import com.hhp.lectureapp.lecture.persistence.LectureApplicationId;

import java.util.List;
import java.util.Optional;

public interface LectureApplicationRepository {
    LectureApplicationDomain save(LectureApplicationDomain lectureApplication);
    Optional<LectureApplicationDomain> findById(LectureApplicationId id);

    List<LectureApplicationDomain> findByIdLectureSessionId(long sessionId);

    boolean existsByUserIdAndLectureId(long sessionId, long userId);

    List<LectureApplicationDomain> findAllByIdUserId(long userId);
}
