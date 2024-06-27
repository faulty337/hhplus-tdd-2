package com.hhp.lectureapp.e2e;


import com.hhp.lectureapp.lecture.persistence.Lecture;
import com.hhp.lectureapp.lecture.persistence.LectureJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class LectureTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LectureJpaRepository lectureJpaRepository;

    private final List<Lecture> lectureList = new ArrayList<>();
    @BeforeEach
    public void setUp() throws Exception {

//        lectureList.add(new Lecture(30, 0, false, LocalDateTime.now().minusDays(1L), LocalDateTime.now().minusDays(1L).plusHours(1)));
//        lectureList.add(new Lecture(20, 0, false, LocalDateTime.now().minusDays(1L)));
//        lectureList.add(new Lecture(40, 40, true, LocalDateTime.now().minusDays(1L)));
//        lectureList.add(new Lecture(40, 0, false, LocalDateTime.now().plusDays(1L)));
//        lectureList.add(new Lecture(40, 40, false, LocalDateTime.now().plusDays(1L)));
//        lectureList.add(new Lecture(40, 30, false, LocalDateTime.now().minusDays(1L)));
        lectureJpaRepository.saveAll(lectureList);

    }


    @Test
    @DisplayName("강의 목록 조회 테스트 - 정상 동작")
    public void getAllOpenLectures() throws Exception {

//        int successSize = lectureList.stream().map(Lecture::toDomain).filter(lecture -> !lecture.isFull() && LocalDateTime.now().isAfter(lecture.getOpenedAt())).toList().size();
//        mockMvc.perform(get("/lectures"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.size()").value(successSize));
    }

}
