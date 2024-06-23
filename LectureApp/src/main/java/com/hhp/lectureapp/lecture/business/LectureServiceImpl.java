package com.hhp.lectureapp.lecture.business;

import com.hhp.lectureapp.lecture.controller.LectureService;
import com.hhp.lectureapp.lecture.controller.dto.GetLectureDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureServiceImpl implements LectureService {

    private final LectureRepository lectureRepository;
    @Override
    public List<GetLectureDto> getLectureList() {
        LocalDateTime now = LocalDateTime.now();
        return lectureRepository.findAllByOpenedAtBeforeAndIsFull(now, false).stream()
                .map(lecture -> new GetLectureDto(
                        lecture.getId(),
                        lecture.getUserCount(),
                        lecture.getUserLimit())
                ).toList();
    }
}
