package com.hhp.lectureapp.user.persistence;

import com.hhp.lectureapp.user.business.UserDomain;
import com.hhp.lectureapp.user.business.UserRepository;
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
