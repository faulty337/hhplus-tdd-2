package com.hhp.lectureapp.lecture.business;

import com.hhp.lectureapp.lecture.business.domain.LectureSessionDomain;

import java.util.Optional;


public interface LectureSessionRepository {
    Optional<LectureSessionDomain> findByIdAndLectureId(long id, long lectureId);

    LectureSessionDomain update(LectureSessionDomain lectureSession);
}
