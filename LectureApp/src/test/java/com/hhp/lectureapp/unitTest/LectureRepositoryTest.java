package com.hhp.lectureapp.unitTest;

import com.hhp.lectureapp.lecture.business.LectureSessionRepository;
import com.hhp.lectureapp.lecture.business.domain.LectureApplicationDomain;
import com.hhp.lectureapp.lecture.business.LectureApplicationRepository;
import com.hhp.lectureapp.lecture.business.domain.LectureSessionDomain;
import com.hhp.lectureapp.lecture.persistence.LectureApplicationId;
import com.hhp.lectureapp.lecture.persistence.LectureApplicationJpaRepository;
import com.hhp.lectureapp.lecture.persistence.LectureSessionJpaRepository;
import com.hhp.lectureapp.lecture.persistence.entity.LectureApplication;
import com.hhp.lectureapp.lecture.persistence.entity.LectureSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LectureRepositoryTest {

    @Autowired
    private LectureApplicationRepository lectureApplicationRepository;
    @Autowired
    private LectureApplicationJpaRepository lectureApplicationJpaRepository;
    @Autowired
    private LectureSessionJpaRepository lectureSessionJpaRepository;
    @Autowired
    private LectureSessionRepository lectureSessionRepository;


    @Test
    public void lectureApplicationSaveTest(){
        long userId = 3;
        long sessionId = 5;
        LectureApplicationDomain lectureApplicationDomainSave = lectureApplicationRepository.save(new LectureApplicationDomain(userId, sessionId));

        LectureApplicationDomain lectureApplicationDomain = lectureApplicationRepository.findById(new LectureApplicationId(userId, sessionId)).get();



        assertEquals(lectureApplicationDomainSave.getUserId(), userId);
        assertEquals(lectureApplicationDomainSave.getSessionId(), sessionId);

        assertEquals(lectureApplicationDomain.getUserId(), userId);
        assertEquals(lectureApplicationDomain.getSessionId(), sessionId);
    }

    @Test
    public void getLectureApplicationListTest(){
        long sessionId1 = 5;
        long sessionId1Size = 7;
        long sessionId2 = 3;
        long sessionId2Size = 5;
        List<LectureApplication> list = new ArrayList<>();

        for(long i = 0; i < sessionId1Size; i++){
            list.add(new LectureApplication(new LectureApplicationId(i, sessionId1)));
        }

        for(long i = 0; i < sessionId2Size; i++){
            list.add(new LectureApplication(new LectureApplicationId(i, sessionId2)));
        }
        lectureApplicationJpaRepository.saveAll(list);

        List<LectureApplicationDomain> lectureApplicationDomainList1 =  lectureApplicationRepository.findByIdLectureSessionId(sessionId1);
        List<LectureApplicationDomain> lectureApplicationDomainList2 =  lectureApplicationRepository.findByIdLectureSessionId(sessionId2);

        assertEquals(lectureApplicationDomainList1.size(), sessionId1Size);
        assertEquals(lectureApplicationDomainList2.size(), sessionId2Size);

    }

    @Test
    public void updateLectureSessionTest(){
        int limit = 30;
        LectureSession lectureSession = new LectureSession(1, 1, limit, limit-1, false, LocalDateTime.now(), LocalDateTime.now());
        lectureSessionJpaRepository.save(lectureSession);

        LectureSessionDomain sessionDomain = lectureSession.toDomain();
        sessionDomain.applyUser();
        LectureSessionDomain updateSessionDomain = lectureSessionRepository.update(sessionDomain);

        assertEquals(updateSessionDomain.getCurrentApplications(), limit);
        assertTrue(updateSessionDomain.isFull());

    }
}
