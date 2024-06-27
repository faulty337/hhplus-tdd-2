package com.hhp.lectureapp.lecture.business;

import com.hhp.lectureapp.lecture.business.domain.LectureApplicationDomain;
import com.hhp.lectureapp.lecture.business.domain.LectureDomain;
import com.hhp.lectureapp.lecture.business.domain.LectureSessionDomain;
import com.hhp.lectureapp.lecture.business.domain.UserDomain;
import com.hhp.lectureapp.lecture.business.dto.GetLectureDto;
import com.hhp.lectureapp.common.CustomException;
import com.hhp.lectureapp.common.ErrorCode;
import com.hhp.lectureapp.lecture.business.dto.PostLectureDto;
import com.hhp.lectureapp.lecture.persistence.entity.LectureSession;
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
    public List<GetLectureDto> getLectureList(long userId) {
        userRepository.findById(userId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_USER_ID)
        );
        List<Long> sessionId = lectureApplicationRepository.findAllByIdUserId(userId).stream().map(LectureApplicationDomain::getSessionId).toList();


        return lectureSessionRepository.findByIdNotInAndOpened(sessionId).stream().map(session -> new GetLectureDto(session.getId(), session.getCurrentApplications(), session.getApplicationLimit())).toList();
    }


    //해당 부분 유효성 체크, 로직으로 인해 너무 길어지는데 간단한 userId나 lectureId에 대한 유효성 체크를 따로 함수로 만들어야 하는지..
    //함수로 만들게 되면 사실 findById 라는 함수가 이미 하고 있는 것을 한겹 감싸는 형식이 되어 조금 애매합니다.
    @Transactional
    @Override
    public PostLectureDto applyLecture(long lectureId, long userId, long sessionId) {
        userRepository.findById(userId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_USER_ID)
        );

        LectureDomain lecture = lectureRepository.findById(lectureId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_LECTURE_ID)
        );

        LectureSessionDomain lectureSession = lectureSessionRepository.findByIdAndLectureIdWithLock(sessionId, lectureId).orElseThrow(
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

    @Override
    public Boolean isApplication(long userId, long sessionId) {
        return lectureApplicationRepository.existsByUserIdAndLectureId(userId, sessionId);
    }

}
