package com.hhp.lectureapp.lecture.business;

import java.time.LocalDateTime;
import java.util.List;

public interface LectureRepository{
    List<LectureDomain> findAllByOpenedAtBeforeAndIsFull(LocalDateTime dateTime, Boolean isFull);
}
