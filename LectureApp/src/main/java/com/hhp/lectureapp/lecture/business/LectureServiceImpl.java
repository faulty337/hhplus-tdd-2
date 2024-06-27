package com.hhp.lectureapp.lecture.business;

import com.hhp.lectureapp.lecture.business.domain.LectureSessionDomain;
import com.hhp.lectureapp.lecture.business.dto.GetLectureDto;
import com.hhp.lectureapp.common.CustomException;
import com.hhp.lectureapp.common.ErrorCode;
import com.hhp.lectureapp.lecture.business.dto.PostLectureDto;
import com.hhp.lectureapp.lecture.persistence.LectureApplication;
import com.hhp.lectureapp.lecture.persistence.LectureApplicationId;
import com.hhp.lectureapp.user.business.UserDomain;
import com.hhp.lectureapp.user.business.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureServiceImpl implements LectureService {
    private final UserRepository userRepository;
    private final LectureSessionRepository lectureSessionRepository;
    private final LectureApplicationRepository lectureApplicationRepository;
    private final LectureRepository lectureRepository;

    @Override
    public List<GetLectureDto> getLectureList() {
//        LocalDateTime now = LocalDateTime.now();
//        return lectureRepository.findAllByOpenedAtBeforeAndIsFull(now, false).stream()
//                .map(lecture -> new GetLectureDto(
//                        lecture.getId(),
//                        lecture.getUserCount(),
//                        lecture.getUserLimit())
//                ).toList();
        return null;
    }

    @Override
    public PostLectureDto applyLecture(long lectureId, long userId, long sessionId) {
        UserDomain user = userRepository.findById(userId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_USER_ID)
        );

        LectureSessionDomain lectureSession = lectureSessionRepository.findByIdAndLectureId(sessionId, lectureId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_LECTURE)
        );

        LectureApplicationDomain lectureApplication = new LectureApplicationDomain(new LectureApplicationId(userId, sessionId));




        return null;
    }
}
