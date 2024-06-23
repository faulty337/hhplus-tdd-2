package com.hhp.lectureapp.lecture.persistence;

import com.hhp.lectureapp.lecture.business.LectureDomain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LectureRepository{
    List<LectureDomain> findAllByOpenedAtBeforeAndIsFull(LocalDateTime dateTime, Boolean isFull);

    Optional<LectureDomain> findById(long lectureId);
}
