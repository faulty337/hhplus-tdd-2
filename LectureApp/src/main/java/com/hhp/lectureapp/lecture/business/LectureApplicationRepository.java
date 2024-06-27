package com.hhp.lectureapp.lecture.business;


import com.hhp.lectureapp.lecture.persistence.LectureApplicationId;

import java.util.Optional;

public interface LectureApplicationRepository {
    LectureApplicationDomain save(LectureApplicationDomain lectureApplication);
    Optional<LectureApplicationDomain> findById(LectureApplicationId id);
}
