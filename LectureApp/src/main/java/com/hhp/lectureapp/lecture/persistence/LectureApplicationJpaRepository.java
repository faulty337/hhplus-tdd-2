package com.hhp.lectureapp.lecture.persistence;

import com.hhp.lectureapp.lecture.persistence.entity.LectureApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureApplicationJpaRepository extends JpaRepository<LectureApplication, LectureApplicationId> {


    List<LectureApplication> findByIdLectureSessionId(Long lectureSessionId);

    boolean existsByIdLectureSessionIdAndIdUserId(Long lectureSessionId, Long userId);

    List<LectureApplication> findAllByIdUserId(Long userId);
}
