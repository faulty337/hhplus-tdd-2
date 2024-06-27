package com.hhp.lectureapp.unitTest;


import com.hhp.lectureapp.common.CustomException;
import com.hhp.lectureapp.common.ErrorCode;
import com.hhp.lectureapp.lecture.business.LectureApplicationRepository;
import com.hhp.lectureapp.lecture.business.LectureRepository;
import com.hhp.lectureapp.lecture.business.domain.LectureApplicationDomain;
import com.hhp.lectureapp.lecture.business.LectureServiceImpl;
import com.hhp.lectureapp.lecture.business.domain.LectureDomain;
import com.hhp.lectureapp.lecture.business.domain.LectureSessionDomain;
import com.hhp.lectureapp.lecture.business.dto.PostLectureDto;
import com.hhp.lectureapp.lecture.persistence.*;
import com.hhp.lectureapp.lecture.business.domain.UserDomain;
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

//    @Test
//    @DisplayName("getLectureList() - 정상 동작")
//    public void getLectureListTest() {
//        List<LectureDomain> lectureDomainList = new ArrayList<>();
//        int size = 5;
//        for(long i = 0; i < size; i++){
//            lectureDomainList.add(new LectureDomain(i, 30, 20, false, LocalDateTime.now().minusDays(1L), LocalDateTime.now().minusDays(2L)));
//        }
//        given(lectureRepository.findAllByOpenedAtBeforeAndIsFull(any(LocalDateTime.class), any(Boolean.class))).willReturn(lectureDomainList);
//
//        List<GetLectureDto> response = lectureService.getLectureList();
//
//        assertEquals(response.size(), size);

//    }

//    @Test
//    @DisplayName("getLectureList() - 정상 동작")
//    public void getLectureListTest() {
//        long userId = 1;
//        long sessionId = 3;
//        long lectureId = 4;
//
//        long listSize = 5;
//
//        LectureDomain lecture = new LectureDomain(lectureId, "title", LocalDateTime.now());
//        UserDomain user = new UserDomain(userId);
//        LectureApplicationId lectureApplicationId = new LectureApplicationId(userId, sessionId);
//        LectureApplicationDomain lectureApplication = new LectureApplicationDomain(userId, sessionId);
//        List<LectureApplicationDomain> lectureApplicationDomainList = new ArrayList<>();
//
//        for(int i = 1; i <= listSize; i++) {
//            lectureApplicationDomainList.add(new LectureApplicationDomain(i, sessionId));
//        }
//
//
//        given(userRepository.findById(userId)).willReturn(Optional.of(user));
//        given(lectureSessionRepository.findByIdAndLectureId(sessionId, lectureId)).willReturn(Optional.of(new LectureSessionDomain(sessionId, lectureId, 30, lectureApplicationDomainList.size(), false, LocalDateTime.now(), LocalDateTime.now())));
//        given(lectureRepository.findById(lectureId)).willReturn(Optional.of(lecture));
//        given(lectureApplicationRepository.findById(lectureApplicationId)).willReturn(Optional.of(lectureApplication));
//        given(lectureApplicationRepository.findByIdLectureSessionId(sessionId)).willReturn(lectureApplicationDomainList);
//
//
//        PostLectureDto response = lectureService.applyLecture(lectureId, userId, sessionId);
//
//
//        assertEquals(response.getSessionId(), sessionId);
//        assertEquals(response.getTitle(), lecture.getTitle());
//        assertEquals(response.getUserCount(), lectureApplicationDomainList.size());
////        List<GetLectureDto> response = lectureService.getLectureList();
////
////        assertEquals(response.size(), size);
//
//    }


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
        given(userRepository.findById(any(Long.class))).willReturn(Optional.of(new UserDomain(1)));
        given(lectureSessionRepository.findByIdAndLectureId(any(Long.class), any(Long.class))).willReturn(Optional.of(new LectureSessionDomain()));
        given(lectureRepository.findById(any(Long.class))).willReturn(Optional.empty());

        CustomException exception = assertThrows(CustomException.class, () -> {
            lectureService.applyLecture(1, 1, 1);
        });

        assertEquals(ErrorCode.NOT_FOUND_LECTURE_ID.getMsg(), exception.getMsg());

    }

    @Test
    @DisplayName("applyLecture - 중복 예외 테스트")
    public void applyLectureDuplicateUserTest() {
        given(userRepository.findById(any(Long.class))).willReturn(Optional.of(new UserDomain(1)));
        given(lectureRepository.findById(any(Long.class))).willReturn(Optional.of(new LectureDomain(1, "test", LocalDateTime.now())));
        given(lectureSessionRepository.findByIdAndLectureId(any(Long.class), any(Long.class))).willReturn(Optional.of(new LectureSessionDomain()));
        given(lectureApplicationRepository.existsByUserIdAndLectureId(any(Long.class), any(Long.class))).willReturn(true);

        CustomException exception = assertThrows(CustomException.class, () -> {
            lectureService.applyLecture(1, 1, 1);
        });

        assertEquals(ErrorCode.DUPLICATE_USER_APPLICATION.getMsg(), exception.getMsg());

    }

}
