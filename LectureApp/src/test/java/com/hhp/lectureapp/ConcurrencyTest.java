package com.hhp.lectureapp;

import com.hhp.lectureapp.e2e.LectureTest;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;


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

    private static final Logger logger = Logger.getLogger(LectureTest.class.getName());

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
                CompletableFuture.runAsync(() -> safeApplyLecture(lecture.getId(), user1.getId(), lectureSession.getId())),
                CompletableFuture.runAsync(() -> safeApplyLecture(lecture.getId(), user2.getId(), lectureSession.getId())),
                CompletableFuture.runAsync(() -> safeApplyLecture(lecture.getId(), user3.getId(), lectureSession.getId())),
                CompletableFuture.runAsync(() -> safeApplyLecture(lecture.getId(), user4.getId(), lectureSession.getId()))
        ).join();

        LectureSession session = lectureSessionRepository.findById(lectureSession.getId()).get();
        assertFalse(session.isFull());
        assertEquals(session.getCurrentApplications(), 4);
    }
    @Test
    public void applyDuplicateConcurrencyTest(){
        LocalDateTime now = LocalDateTime.now();
        Lecture lecture = new Lecture(1L, "test");
        lectureJpaRepository.save(lecture);

        Users user1 = new Users(1L);
        userJpaRepository.save(user1);

        LectureSession lectureSession = new LectureSession(1L, lecture.getId(), 30, 0, false, now.minusHours(1), now.minusDays(1));
        lectureSessionRepository.save(lectureSession);

        CompletableFuture.allOf(
                CompletableFuture.runAsync(() -> safeApplyLecture(lecture.getId(), user1.getId(), lectureSession.getId())),
                CompletableFuture.runAsync(() -> safeApplyLecture(lecture.getId(), user1.getId(), lectureSession.getId())),
                CompletableFuture.runAsync(() -> safeApplyLecture(lecture.getId(), user1.getId(), lectureSession.getId())),
                CompletableFuture.runAsync(() -> safeApplyLecture(lecture.getId(), user1.getId(), lectureSession.getId()))
        ).join();

        LectureSession session = lectureSessionRepository.findById(lectureSession.getId()).get();
        assertFalse(session.isFull());
        assertEquals(1, session.getCurrentApplications());
    }

    @Test
    public void applyConcurrencyFullTest(){
        LocalDateTime now = LocalDateTime.now();
        Lecture lecture = new Lecture(1L, "test");
        lectureJpaRepository.save(lecture);

        Users user1 = new Users(1L);
        Users user2 = new Users(2L);
        Users user3 = new Users(3L);
        Users user4 = new Users(4L);
        Users user5 = new Users(5L);
        userJpaRepository.saveAll(List.of(user1, user2, user3, user4, user5));

        LectureSession lectureSession = new LectureSession(1L, lecture.getId(), 4, 0, false, now.minusHours(1), now.minusDays(1));
        lectureSessionRepository.save(lectureSession);

        CompletableFuture.allOf(
                CompletableFuture.runAsync(() -> safeApplyLecture(lecture.getId(), user1.getId(), lectureSession.getId())),
                CompletableFuture.runAsync(() -> safeApplyLecture(lecture.getId(), user2.getId(), lectureSession.getId())),
                CompletableFuture.runAsync(() -> safeApplyLecture(lecture.getId(), user3.getId(), lectureSession.getId())),
                CompletableFuture.runAsync(() -> safeApplyLecture(lecture.getId(), user4.getId(), lectureSession.getId())),
                CompletableFuture.runAsync(() -> safeApplyLecture(lecture.getId(), user5.getId(), lectureSession.getId()))
        ).join();

        LectureSession session = lectureSessionRepository.findById(lectureSession.getId()).get();
        assertTrue(session.isFull());
        assertEquals(session.getCurrentApplications(), 4);
    }

    private void safeApplyLecture(long lectureId, long userId, long sessionId) {
        try {
            lectureService.applyLecture(lectureId, userId, sessionId);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Exception during applyLecture: " + e.getMessage());
        }
    }




}
