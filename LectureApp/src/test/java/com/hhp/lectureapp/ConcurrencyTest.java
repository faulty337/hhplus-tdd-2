package com.hhp.lectureapp;

import com.hhp.lectureapp.lecture.business.LectureService;
import com.hhp.lectureapp.lecture.persistence.LectureJpaRepository;
import com.hhp.lectureapp.lecture.persistence.LectureSessionJpaRepository;
import com.hhp.lectureapp.lecture.persistence.UserJpaRepository;
import com.hhp.lectureapp.lecture.persistence.entity.Lecture;
import com.hhp.lectureapp.lecture.persistence.entity.LectureSession;
import com.hhp.lectureapp.lecture.persistence.entity.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


@SpringBootTest
public class ConcurrencyTest {

    @Autowired
    LectureService lectureService;

    @Autowired
    private LectureJpaRepository lectureJpaRepository;
    @Autowired
    private LectureSessionJpaRepository lectureSessionRepository;
    @Autowired
    private UserJpaRepository userJpaRepository;

    @Test
    public void applyConcurrencyTest(){
        LocalDateTime now = LocalDateTime.now();
        Lecture lecture = new Lecture(1L, "test");
        lectureJpaRepository.save(lecture);

        Users user1 = new Users(1L);
        Users user2 = new Users(2L);
        Users user3 = new Users(3L);
        Users user4 = new Users(4L);
        Users user5 = new Users(5L);
        userJpaRepository.saveAll(List.of(user1, user2, user3, user4, user5));

        LectureSession lectureSession = new LectureSession(1L, lecture.getId(), 30, 0, false, now.minusHours(1), now.minusDays(1));
        lectureSessionRepository.save(lectureSession);

        CompletableFuture.allOf(
                CompletableFuture.runAsync(() -> lectureService.applyLecture(lecture.getId(), user1.getId(), lectureSession.getId())),
                CompletableFuture.runAsync(() -> lectureService.applyLecture(lecture.getId(), user2.getId(), lectureSession.getId())),
                CompletableFuture.runAsync(() -> lectureService.applyLecture(lecture.getId(), user3.getId(), lectureSession.getId())),
                CompletableFuture.runAsync(() -> lectureService.applyLecture(lecture.getId(), user4.getId(), lectureSession.getId()))
        ).join();

        LectureSession session = lectureSessionRepository.findById(lectureSession.getId()).get();
        assertFalse(session.isFull());
        assertEquals(session.getCurrentApplications(), 4);
    }




}