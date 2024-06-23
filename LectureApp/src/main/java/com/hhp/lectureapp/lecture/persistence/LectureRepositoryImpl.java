package com.hhp.lectureapp.lecture.persistence;

import com.hhp.lectureapp.lecture.business.LectureDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LectureRepositoryImpl implements LectureRepository {

    private final LectureJpaRepository lectureJpaRepository;

    @Override
    public List<LectureDomain> findAllByOpenedAtBeforeAndIsFull(LocalDateTime dateTime, Boolean isFull) {
        return lectureJpaRepository.findAllByOpenedAtBeforeAndIsFull(dateTime, isFull).stream()
                .map(Lecture::toDomain).toList();
    }

    @Override
    public Optional<LectureDomain> findById(long lectureId) {
        return lectureJpaRepository.findById(lectureId).map(Lecture::toDomain);
    }
}
