package com.hhp.lectureapp.lecture.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface LectureJpaRepository extends JpaRepository<Lecture, Long> {
//    public List<Lecture> findAllByOpenedAtBeforeAndIsFull(LocalDateTime opened_at, boolean full);

}
