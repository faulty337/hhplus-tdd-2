package com.hhp.lectureapp.lecture.business;

import com.hhp.lectureapp.lecture.business.domain.LectureApplicationDomain;
import com.hhp.lectureapp.lecture.business.domain.LectureDomain;
import com.hhp.lectureapp.lecture.business.domain.LectureSessionDomain;
import com.hhp.lectureapp.lecture.business.dto.GetLectureDto;
import com.hhp.lectureapp.common.CustomException;
import com.hhp.lectureapp.common.ErrorCode;
import com.hhp.lectureapp.lecture.business.dto.PostLectureDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        return null;
    }

    @Transactional
    @Override
    public PostLectureDto applyLecture(long lectureId, long userId, long sessionId) {
        userRepository.findById(userId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_USER_ID)
        );

        LectureDomain lecture = lectureRepository.findById(lectureId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_LECTURE_ID)
        );

        LectureSessionDomain lectureSession = lectureSessionRepository.findByIdAndLectureId(sessionId, lectureId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_LECTURE)
        );

        if(lectureSession.isFull()){
            throw new CustomException(ErrorCode.FULL_LECTURE);
        }

        if(lectureApplicationRepository.existsByUserIdAndLectureId(sessionId, userId)){
            throw new CustomException(ErrorCode.DUPLICATE_USER_APPLICATION);
        };

        LectureApplicationDomain lectureApplication = new LectureApplicationDomain(userId, sessionId);

        lectureApplicationRepository.save(lectureApplication);

        lectureSession.applyUser();

        lectureSessionRepository.update(lectureSession);

        return new PostLectureDto(lectureSession.getId(), lecture.getTitle(), 1);
    }
}
