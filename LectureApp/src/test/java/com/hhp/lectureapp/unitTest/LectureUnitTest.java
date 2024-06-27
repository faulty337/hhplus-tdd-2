package com.hhp.lectureapp.unitTest;


import com.hhp.lectureapp.common.CustomException;
import com.hhp.lectureapp.common.ErrorCode;
import com.hhp.lectureapp.lecture.business.LectureApplicationRepository;
import com.hhp.lectureapp.lecture.business.LectureRepository;
import com.hhp.lectureapp.lecture.business.domain.LectureApplicationDomain;
import com.hhp.lectureapp.lecture.business.LectureServiceImpl;
import com.hhp.lectureapp.lecture.business.domain.LectureDomain;
import com.hhp.lectureapp.lecture.business.domain.LectureSessionDomain;
import com.hhp.lectureapp.lecture.business.dto.GetLectureDto;
import com.hhp.lectureapp.lecture.business.dto.PostLectureDto;
import com.hhp.lectureapp.lecture.persistence.*;
import com.hhp.lectureapp.lecture.business.domain.UserDomain;
import com.hhp.lectureapp.lecture.persistence.entity.LectureSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class LectureUnitTest {
    @Mock
    private LectureSessionRepositoryImpl lectureSessionRepository;

    @Mock
    private LectureApplicationRepositoryImpl lectureApplicationRepository;

    @Mock
    private UserRepositoryImpl userRepository;

    @Mock
    private LectureRepositoryImpl lectureRepository;

    @InjectMocks
    private LectureServiceImpl lectureService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Mock 객체 초기화
    }

    @Test
    @DisplayName("getLectureList - 정상 동작")
    public void getLectureListTest() {
        long userId = 1;
        int applicationSize = 13;
        int sessionSize = 5;
        LocalDateTime now = LocalDateTime.now();
        List<LectureApplicationDomain> lectureApplicationDomainList = new ArrayList<>();
        List<Long> sessionIdList = new ArrayList<>();
        for(long i = 1; i <= applicationSize; i++) {
            lectureApplicationDomainList.add(new LectureApplicationDomain(userId, i));
            sessionIdList.add(i);
        }

        List<LectureSession> lectureSessionList = new ArrayList<>();
        for(int i = applicationSize+1; i < sessionSize + applicationSize + 1; i++){
            lectureSessionList.add(new LectureSession(i, i, 20, 0, false, now.minusHours(1), now.minusDays(1)));
        }

        given(userRepository.findById(userId)).willReturn(Optional.of(new UserDomain(userId, now)));
        given(lectureApplicationRepository.findAllByIdUserId(userId)).willReturn(lectureApplicationDomainList);
        given(lectureSessionRepository.findByIdNotInAndOpened(sessionIdList)).willReturn(lectureSessionList);

        List<GetLectureDto> response = lectureService.getLectureList(userId);

        assertEquals(response.size(), sessionSize);

    }

    @Test
    @DisplayName("getLectureList - not found userId 예외 테스트")
    public void getLectureListNotFoundUserIdTest() {

        given(userRepository.findById(any(Long.class))).willReturn(Optional.empty());

        List<GetLectureDto> response = lectureService.getLectureList(1);
        CustomException exception = assertThrows(CustomException.class, () -> {
            lectureService.getLectureList(1);
        });

        assertEquals(ErrorCode.NOT_FOUND_USER_ID.getMsg(), exception.getMsg());

    }

    @Test
    @DisplayName("applyLecture - not found userId 예외 테스트")
    public void applyLectureNotFoundUserIdTest() {
        given(userRepository.findById(any(Long.class))).willReturn(Optional.empty());

        CustomException exception = assertThrows(CustomException.class, () -> {
            lectureService.applyLecture(1, 1, 1);
        });

        assertEquals(ErrorCode.NOT_FOUND_USER_ID.getMsg(), exception.getMsg());
    }

    @Test
    @DisplayName("applyLecture - not found lectureId 예외 테스트")
    public void applyLectureNotFoundLectureIdTest() {
        given(userRepository.findById(any(Long.class))).willReturn(Optional.of(new UserDomain(1, LocalDateTime.now())));
        given(lectureSessionRepository.findByIdAndLectureIdWithLock(any(Long.class), any(Long.class))).willReturn(Optional.of(new LectureSessionDomain()));
        given(lectureRepository.findById(any(Long.class))).willReturn(Optional.empty());

        CustomException exception = assertThrows(CustomException.class, () -> {
            lectureService.applyLecture(1, 1, 1);
        });

        assertEquals(ErrorCode.NOT_FOUND_LECTURE_ID.getMsg(), exception.getMsg());

    }

    @Test
    @DisplayName("applyLecture - 중복 예외 테스트")
    public void applyLectureDuplicateUserTest() {
        given(userRepository.findById(any(Long.class))).willReturn(Optional.of(new UserDomain(1, LocalDateTime.now())));
        given(lectureRepository.findById(any(Long.class))).willReturn(Optional.of(new LectureDomain(1, "test", LocalDateTime.now())));
        given(lectureSessionRepository.findByIdAndLectureIdWithLock(any(Long.class), any(Long.class))).willReturn(Optional.of(new LectureSessionDomain()));
        given(lectureApplicationRepository.existsByUserIdAndLectureId(any(Long.class), any(Long.class))).willReturn(true);

        CustomException exception = assertThrows(CustomException.class, () -> {
            lectureService.applyLecture(1, 1, 1);
        });

        assertEquals(ErrorCode.DUPLICATE_USER_APPLICATION.getMsg(), exception.getMsg());

    }

}
