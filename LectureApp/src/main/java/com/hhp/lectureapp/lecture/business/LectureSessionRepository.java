package com.hhp.lectureapp.lecture.business;

import com.hhp.lectureapp.lecture.business.domain.LectureSessionDomain;
import com.hhp.lectureapp.lecture.persistence.entity.LectureSession;

import java.util.List;
import java.util.Optional;


public interface LectureSessionRepository {
    Optional<LectureSessionDomain> findByIdAndLectureIdWithLock(long id, long lectureId);

    LectureSessionDomain update(LectureSessionDomain lectureSession);

    List<LectureSession> findByIdNotInAndOpened(List<Long> sessionIdList);
}
