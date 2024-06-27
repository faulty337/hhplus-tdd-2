package com.hhp.lectureapp.lecture.persistence;

import com.hhp.lectureapp.lecture.business.domain.LectureApplicationDomain;
import com.hhp.lectureapp.lecture.business.LectureApplicationRepository;
import com.hhp.lectureapp.lecture.persistence.entity.LectureApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LectureApplicationRepositoryImpl implements LectureApplicationRepository {

    private final LectureApplicationJpaRepository lectureApplicationJpaRepository;

    @Override
    public LectureApplicationDomain save(LectureApplicationDomain lectureApplication) {
        return lectureApplicationJpaRepository.save(new LectureApplication(
                new LectureApplicationId(
                        lectureApplication.getUserId(),
                        lectureApplication.getSessionId()
                )
        )).toDomain();
    }

    @Override
    public Optional<LectureApplicationDomain> findById(LectureApplicationId id) {
        return lectureApplicationJpaRepository.findById(id).map(LectureApplication::toDomain);
    }

    @Override
    public List<LectureApplicationDomain> findByIdLectureSessionId(long sessionId) {
        return lectureApplicationJpaRepository.findByIdLectureSessionId(sessionId).stream().map(LectureApplication::toDomain).toList();
    }

    @Override
    public boolean existsByUserIdAndLectureId(long lectureId, long userId) {
        return lectureApplicationJpaRepository.existsByIdLectureSessionIdAndIdUserId(lectureId, userId);
    }


}
