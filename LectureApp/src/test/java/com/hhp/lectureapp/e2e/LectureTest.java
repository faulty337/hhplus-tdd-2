package com.hhp.lectureapp.e2e;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhp.lectureapp.common.ErrorCode;
import com.hhp.lectureapp.lecture.business.LectureApplicationRepository;
import com.hhp.lectureapp.lecture.business.LectureSessionRepository;
import com.hhp.lectureapp.lecture.business.UserRepository;
import com.hhp.lectureapp.lecture.business.domain.LectureApplicationDomain;
import com.hhp.lectureapp.lecture.business.domain.LectureDomain;
import com.hhp.lectureapp.lecture.business.dto.PostRequestApplyLectureDto;
import com.hhp.lectureapp.lecture.persistence.*;
import com.hhp.lectureapp.lecture.persistence.entity.Lecture;
import com.hhp.lectureapp.lecture.persistence.entity.LectureApplication;
import com.hhp.lectureapp.lecture.persistence.entity.LectureSession;
import com.hhp.lectureapp.lecture.persistence.entity.Users;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class LectureTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private LectureJpaRepository lectureJpaRepository;
    @Autowired
    private LectureSessionJpaRepository lectureSessionRepository;
    @Autowired
    private LectureApplicationJpaRepository lectureApplicationRepository;
    @Autowired
    private UserJpaRepository userRepository;


    @BeforeEach
    public void setUp() {
        lectureApplicationRepository.deleteAll();
        lectureSessionRepository.deleteAll();
        lectureJpaRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("강의 목록 조회 테스트 - 정상 동작")
    public void getAllOpenLecturesTest() throws Exception {
        long sessionSize = 15;
        long applicationSize = 7;
        long userId = 1;
        LocalDateTime now = LocalDateTime.now();

        userRepository.save(new Users(userId));
        List<LectureApplication> lectureApplicationDomainList = new ArrayList<>();
        for(long i = 1; i <= applicationSize; i++) {
            lectureApplicationDomainList.add(new LectureApplication(new LectureApplicationId(userId, i)));
        }


        lectureApplicationRepository.saveAll(lectureApplicationDomainList);



        List<LectureSession> lectureSessionList = new ArrayList<>();
        for(long i = 1; i <= sessionSize; i++){
            lectureSessionList.add(new LectureSession(i, i, 20, 0, false, now.minusHours(1), now.minusDays(1)));
        }

        lectureSessionRepository.saveAll(lectureSessionList);

        mockMvc.perform(get("/lecture").param("userId", String.valueOf(userId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(sessionSize - applicationSize));
    }

    @Test
    @DisplayName("강의 목록 조회 테스트 - 유저 X 테스트")
    public void getAllOpenLectureNotFoundUserTest() throws Exception {
        long userId = 1;

        mockMvc.perform(get("/lecture").param("userId", String.valueOf(userId)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.msg").value(ErrorCode.NOT_FOUND_USER_ID.getMsg()));
    }

    @Test
    @DisplayName("강의 신청 테스트 - 정상 동작")
    public void applyLectureTest() throws Exception {

        LocalDateTime now = LocalDateTime.now();
        Lecture lecture = new Lecture(1L, "test");
        lectureJpaRepository.save(lecture);
        Users user = new Users(1L);
        userRepository.save(user);
        LectureSession lectureSession = new LectureSession(1L, lecture.getId(), 30, 0, false, now.minusHours(1), now.minusDays(1));
        lectureSessionRepository.save(lectureSession);

//        LectureApplication lectureApplication = new LectureApplication(new LectureApplicationId(user.getId(), lectureSession.getId()));
//        lectureApplicationRepository.save(lectureApplication);

        PostRequestApplyLectureDto request = new PostRequestApplyLectureDto(user.getId(), lectureSession.getId());
        mockMvc.perform(post("/lecture/{lectureId}/apply", lecture.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.sessionId").value(lectureSession.getId()))
            .andExpect(jsonPath("$.title").value(lecture.getTitle()))
            .andExpect(jsonPath("$.userCount").value(1));

    }

    @Test
    @DisplayName("강의 신청 테스트 - 중복 유저 예외 테스트")
    public void applyDuplicateExceptionTest() throws Exception {

        LocalDateTime now = LocalDateTime.now();
        Lecture lecture = new Lecture(1L, "test");
        lectureJpaRepository.save(lecture);
        Users user = new Users(1L);
        userRepository.save(user);
        LectureSession lectureSession = new LectureSession(1L, lecture.getId(), 30, 0, false, now.minusHours(1), now.minusDays(1));
        lectureSessionRepository.save(lectureSession);

        LectureApplication lectureApplication = new LectureApplication(new LectureApplicationId(user.getId(), lectureSession.getId()));
        lectureApplicationRepository.save(lectureApplication);

        PostRequestApplyLectureDto request = new PostRequestApplyLectureDto(user.getId(), lectureSession.getId());
        mockMvc.perform(post("/lecture/{lectureId}/apply", lecture.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
            )
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.msg").value(ErrorCode.DUPLICATE_USER_APPLICATION.getMsg()));

    }

    @Test
    @DisplayName("강의 신청 확인- 신청 성공 동작")
    public void isApplicationSuccessTest() throws Exception {
        long userId = 1;
        long sessionId = 1;

        LectureApplication lectureApplication = new LectureApplication(new LectureApplicationId(userId, sessionId));
        lectureApplicationRepository.save(lectureApplication);

        mockMvc.perform(get("/lecture/application/{userId}", userId)
                .param("sessionId", String.valueOf(sessionId)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    @DisplayName("강의 신청 확인- 신청 실패 동작")
    public void isApplicationFailTest() throws Exception {
        long userId = 1;
        long sessionId = 1;

        mockMvc.perform(get("/lecture/application/{userId}", userId)
                        .param("sessionId", String.valueOf(sessionId)))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }



}
