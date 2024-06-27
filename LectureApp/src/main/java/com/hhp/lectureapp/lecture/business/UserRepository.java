package com.hhp.lectureapp.lecture.business;

import com.hhp.lectureapp.lecture.business.domain.UserDomain;

import java.util.Optional;

public interface UserRepository{

    Optional<UserDomain> findById(long id);
}
