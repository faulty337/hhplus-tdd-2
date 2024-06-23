package com.hhp.lectureapp.lecture.business;

import com.hhp.lectureapp.lecture.controller.dto.GetLectureDto;
import com.hhp.lectureapp.lecture.persistence.LectureRepository;
import com.hhp.lectureapp.common.CustomException;
import com.hhp.lectureapp.common.ErrorCode;
import com.hhp.lectureapp.lecture.controller.dto.PostLectureDto;
import com.hhp.lectureapp.user.business.UserDomain;
import com.hhp.lectureapp.user.business.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureServiceImpl implements LectureService {
    private final UserRepository userRepository;
    private final LectureRepository lectureRepository;

    @Override
    public List<GetLectureDto> getLectureList() {
        LocalDateTime now = LocalDateTime.now();
        return lectureRepository.findAllByOpenedAtBeforeAndIsFull(now, false).stream()
                .map(lecture -> new GetLectureDto(
                        lecture.getId(),
                        lecture.getUserCount(),
                        lecture.getUserLimit())
                ).toList();
    }

    @Override
    public PostLectureDto applyLecture(long lectureId, long userId) {
        UserDomain user = userRepository.findById(userId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_USER_ID)
        );
        LectureDomain lecture = lectureRepository.findById(lectureId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_LECTURE_ID)
        );


        return null;
    }
}
