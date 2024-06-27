package com.hhp.lectureapp.lecture.persistence;

import com.hhp.lectureapp.lecture.business.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LectureRepositoryImpl implements LectureRepository {

    private final LectureJpaRepository lectureJpaRepository;

//    @Override
//    public List<LectureDomain> findAllByOpenedAtBeforeAndIsFull(LocalDateTime dateTime, Boolean isFull) {
//        return null;
//    }
//
//    @Override
//    public Optional<LectureDomain> findById(long lectureId) {
//        return null;
//    }
}
