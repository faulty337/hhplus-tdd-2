package com.hhp.lectureapp.lecture.persistence;

import com.hhp.lectureapp.lecture.business.LectureDomain;
import com.hhp.lectureapp.lecture.business.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class LectureRepositoryImpl implements LectureRepository {

    private final LectureJpaRepository lectureJpaRepository;

    @Override
    public List<LectureDomain> findAllByOpenedAtBeforeAndIsFull(LocalDateTime dateTime, Boolean isFull) {
        return lectureJpaRepository.findAllByOpenedAtBeforeAndIsFull(dateTime, isFull).stream()
                .map(Lecture::toDomain).toList();
    }
}
