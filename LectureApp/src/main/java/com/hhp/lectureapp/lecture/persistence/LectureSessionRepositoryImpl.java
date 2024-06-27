package com.hhp.lectureapp.lecture.persistence;

import com.hhp.lectureapp.lecture.business.domain.LectureSessionDomain;
import com.hhp.lectureapp.lecture.business.LectureSessionRepository;
import com.hhp.lectureapp.lecture.persistence.entity.LectureSession;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LectureSessionRepositoryImpl implements LectureSessionRepository {

    private final LectureSessionJpaRepository lectureSessionJpaRepository;

    @Override
    public Optional<LectureSessionDomain> findByIdAndLectureIdWithLock(long id, long lectureId) {
        return lectureSessionJpaRepository.findByIdAndLectureIdWithLock(id, lectureId).map(LectureSession::toDomain);
    }

    @Override
    public LectureSessionDomain update(LectureSessionDomain lectureSession) {
        return lectureSessionJpaRepository.save(new LectureSession(
                lectureSession.getId(),
                lectureSession.getLectureId(),
                lectureSession.getApplicationLimit(),
                lectureSession.getCurrentApplications(),
                lectureSession.isFull(),
                lectureSession.getOpenedAt(),
                lectureSession.getCreatedAt()
        )).toDomain();
    }

    @Override
    public List<LectureSession> findByIdNotInAndOpened(List<Long> sessionIdList) {
        return lectureSessionJpaRepository.findByIdNotInAndOpenedAtBefore(sessionIdList, LocalDateTime.now());
    }

}
