package com.hhp.lectureapp.lecture.persistence;

import com.hhp.lectureapp.lecture.business.UserRepository;
import com.hhp.lectureapp.lecture.business.domain.UserDomain;
import com.hhp.lectureapp.lecture.persistence.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    @Override
    public Optional<UserDomain> findById(long id) {
        return userJpaRepository.findById(id).map(Users::toDomain);
    }
}
