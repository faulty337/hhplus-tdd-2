package com.hhp.lectureapp.user.business;

import java.util.Optional;

public interface UserRepository{

    Optional<UserDomain> findById(long id);
}
