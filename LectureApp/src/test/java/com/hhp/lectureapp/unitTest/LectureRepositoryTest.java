package com.hhp.lectureapp.unitTest;

import com.hhp.lectureapp.lecture.business.LectureApplicationDomain;
import com.hhp.lectureapp.lecture.business.LectureApplicationRepository;
import com.hhp.lectureapp.lecture.persistence.LectureApplicationId;
import com.hhp.lectureapp.lecture.persistence.LectureApplicationJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LectureRepositoryTest {

    @Autowired
    private LectureApplicationRepository lectureApplicationRepository;
    @Autowired
    private LectureApplicationJpaRepository lectureApplicationJpaRepository;


    @Test
    public void lectureApplicationSaveTest(){
        long userId = 3;
        long sessionId = 5;
        LectureApplicationDomain lectureApplicationDomainSave = lectureApplicationRepository.save(new LectureApplicationDomain(new LectureApplicationId(userId, sessionId)));

        LectureApplicationDomain lectureApplicationDomain = lectureApplicationRepository.findById(new LectureApplicationId(userId, sessionId)).get();



        assertEquals(lectureApplicationDomainSave.getId().getUserId(), userId);
        assertEquals(lectureApplicationDomainSave.getId().getLectureSessionId(), sessionId);

        assertEquals(lectureApplicationDomain.getId().getUserId(), userId);
        assertEquals(lectureApplicationDomain.getId().getLectureSessionId(), sessionId);


    }
}
