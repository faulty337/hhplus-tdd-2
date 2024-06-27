package com.hhp.lectureapp.lecture.persistence;

import com.hhp.lectureapp.lecture.business.domain.LectureSessionDomain;
import com.hhp.lectureapp.lecture.business.LectureSessionRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class LectureSessionRepositoryImpl implements LectureSessionRepository {

    LectureSessionJpaRepository lectureSessionJpaRepository;

    @Override
    public Optional<LectureSessionDomain> findByIdAndLectureId(long id, long lectureId) {
        return lectureSessionJpaRepository.findByIdAndLectureId(id, lectureId).map(LectureSession::toDomain);
    }

}
