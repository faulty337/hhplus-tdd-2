package com.hhp.lectureapp.lecture.business;

import java.util.List;

public interface LectureRepository{
    public List<LectureDomain> findAllByOpenAndNotFull();
}
