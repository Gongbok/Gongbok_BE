package Gongbok_BE.Gongbok.review.service;

import Gongbok_BE.Gongbok.review.domain.Content;
import Gongbok_BE.Gongbok.review.domain.Subject;
import Gongbok_BE.Gongbok.review.domain.Week;
import Gongbok_BE.Gongbok.review.repository.ContentRepository;
import Gongbok_BE.Gongbok.review.repository.SubjectRepository;
import Gongbok_BE.Gongbok.review.repository.WeekRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class WeekService {
    //private Week week;
    private WeekRepository weekRepository;
    private Week temporaryWeek;
    private ContentRepository contentRepository;
    private SubjectRepository subjectRepository;

    public WeekService(SubjectRepository subjectRepository, WeekRepository weekRepository, ContentRepository contentRepository) {
        //this.week = week;
        this.weekRepository = weekRepository;
        this.contentRepository = contentRepository;
        this.subjectRepository = subjectRepository;
    }

    public Week createNextWeek(Subject subject) {
        Week week = new Week();
        week.setSubject(subject);
        //week.setWeekId(subjetId);
        //week.setWeekNum(subject);

        // 현재 과목(subject)의 마지막 차시 번호 조회
        int lastWeekNumber = weekRepository.findLastWeekNumberBySubject(subject);
        System.out.println("lastWeekNumber = " + lastWeekNumber);

        // 다음 차시 번호 설정
        int nextWeekNumber = lastWeekNumber + 1;
        System.out.println("nextWeekNumber = " + nextWeekNumber);
        week.setWeekNum(nextWeekNumber);
        temporaryWeek = week; // 임시 Week 저장

        return weekRepository.save(week);

    }

    public String addContentToCurrentWeek(long subjectId, int weekNum, String contentText) {
        Subject subject = subjectRepository.findById(subjectId).orElse(null);
        if (subject == null) {
            throw new IllegalArgumentException("Invalid subjectId: " + subjectId);
        }

        Week week = weekRepository.findBySubjectAndWeekNum(subject, weekNum);
        if (week == null) {
            throw new IllegalArgumentException("Invalid weekNum: " + weekNum);
        }

        Content content = new Content();
        content.setContent(contentText);
        contentRepository.save(content);
        System.out.println(contentText);
        week.setContent(content);

        weekRepository.save(week); // 실제 Week 저장
        //week = null; // 선택된 Week 초기화

        return content.getContent(); // 저장된 Content의 내용 반환
    }

    public Week getWeekBySubjectAndWeekNum(Subject subject, int weekNum) {
        return weekRepository.findBySubjectAndWeekNum(subject, weekNum);
    }

    public Week getWeekById(int weekId) {
        return weekRepository.findById(weekId).orElse(null);
    }
}







