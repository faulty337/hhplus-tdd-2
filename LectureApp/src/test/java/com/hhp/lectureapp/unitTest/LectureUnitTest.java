package com.hhp.lectureapp.unitTest;


import com.hhp.lectureapp.common.CustomException;
import com.hhp.lectureapp.common.ErrorCode;
import com.hhp.lectureapp.lecture.business.LectureDomain;
import com.hhp.lectureapp.lecture.business.LectureServiceImpl;
import com.hhp.lectureapp.lecture.controller.dto.GetLectureDto;
import com.hhp.lectureapp.lecture.persistence.LectureRepositoryImpl;
import com.hhp.lectureapp.user.business.UserDomain;
import com.hhp.lectureapp.user.persistence.UserRepositoryImpl;
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
    private LectureRepositoryImpl lectureRepository;

    @InjectMocks
    private LectureServiceImpl lectureService;

    @Mock
    private UserRepositoryImpl userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Mock 객체 초기화
    }

    @Test
    @DisplayName("getLectureList() - 정상 동작")
    public void getLectureListTest() {
        List<LectureDomain> lectureDomainList = new ArrayList<>();
        int size = 5;
        for(long i = 0; i < size; i++){
            lectureDomainList.add(new LectureDomain(i, 30, 20, false, LocalDateTime.now().minusDays(1L), LocalDateTime.now().minusDays(2L)));
        }
        given(lectureRepository.findAllByOpenedAtBeforeAndIsFull(any(LocalDateTime.class), any(Boolean.class))).willReturn(lectureDomainList);

        List<GetLectureDto> response = lectureService.getLectureList();

        assertEquals(response.size(), size);

    }


    @Test
    @DisplayName("applyLecture - not found userId 예외 테스트")
    public void applyLectureNotFoundUserIdTest() {
        given(userRepository.findById(any(Long.class))).willReturn(Optional.empty());

        CustomException exception = assertThrows(CustomException.class, () -> {
            lectureService.applyLecture(1, 1);
        });

        assertEquals(ErrorCode.NOT_FOUND_USER_ID.getMsg(), exception.getMsg());
    }

    @Test
    @DisplayName("applyLecture - not found lectureId 예외 테스트")
    public void applyLectureNotFoundLectureIdTest() {
        given(userRepository.findById(any(Long.class))).willReturn(Optional.of(new UserDomain(1, LocalDateTime.now())));
        given(lectureRepository.findById(any(Long.class))).willReturn(Optional.empty());

        CustomException exception = assertThrows(CustomException.class, () -> {
            lectureService.applyLecture(1, 1);
        });

        assertEquals(ErrorCode.NOT_FOUND_LECTURE_ID.getMsg(), exception.getMsg());

    }




}
