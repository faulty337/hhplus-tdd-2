package com.hhp.lectureapp.lecture.persistence;

import com.hhp.lectureapp.lecture.business.LectureApplicationDomain;
import com.hhp.lectureapp.lecture.business.LectureApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LectureApplicationRepositoryImpl implements LectureApplicationRepository {

    private final LectureApplicationJpaRepository lectureApplicationJpaRepository;

    @Override
    public LectureApplicationDomain save(LectureApplicationDomain lectureApplication) {
        return lectureApplicationJpaRepository.save(new LectureApplication(lectureApplication.getId())).toDomain();
    }

    @Override
    public Optional<LectureApplicationDomain> findById(LectureApplicationId id) {
        return lectureApplicationJpaRepository.findById(id).map(LectureApplication::toDomain);
    }


}
