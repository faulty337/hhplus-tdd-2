package com.hhp.lectureapp.lecture.persistence;

import com.hhp.lectureapp.lecture.persistence.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LectureJpaRepository extends JpaRepository<Lecture, Long> {
//    public List<Lecture> findAllByOpenedAtBeforeAndIsFull(LocalDateTime opened_at, boolean full);

    Optional<Lecture> findById(long id);
}
