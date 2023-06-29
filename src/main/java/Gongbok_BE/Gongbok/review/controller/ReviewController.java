package Gongbok_BE.Gongbok.review.controller;

import Gongbok_BE.Gongbok.review.domain.Subject;
import Gongbok_BE.Gongbok.review.domain.Week;
import Gongbok_BE.Gongbok.review.dto.SubjectRequest;
import Gongbok_BE.Gongbok.review.dto.SubjectResponse;
import Gongbok_BE.Gongbok.review.repository.WeekRepository;
import Gongbok_BE.Gongbok.review.service.ContentService;
import Gongbok_BE.Gongbok.review.service.SubjectService;
import Gongbok_BE.Gongbok.review.service.WeekService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/review")
public class ReviewController {

    private SubjectService subjectService;
    private SubjectRequest subjectRequest;
    private WeekService weekService;
    private WeekRepository weekRepository;
    private ContentService contentService;
    private Subject subject;

    public ReviewController(SubjectService subjectService, WeekService weekService, WeekRepository weekRepository, ContentService contentService) {
        this.subjectService = subjectService;
        this.weekService = weekService;
        this.weekRepository = weekRepository;
        this.contentService = contentService;
    }

    @GetMapping("/subject/{id}")
    public ResponseEntity<SubjectResponse> getSubject(@PathVariable Long id) {
        Subject subject = subjectService.getSubjectById(id);
        if (subject == null) {
            return ResponseEntity.notFound().build();
        }

        SubjectResponse response = new SubjectResponse(subject.getSubjectId(), subject.getSubjectName());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/subjects")
    public ResponseEntity<List<SubjectResponse>> getAllSubjects() {
        List<Subject> subjects = subjectService.getAllSubjects();
        List<SubjectResponse> responses = new ArrayList<>();

        for (Subject subject : subjects) {
            SubjectResponse response = new SubjectResponse(subject.getSubjectId(), subject.getSubjectName());
            responses.add(response);
        }

        return ResponseEntity.ok(responses);
    }

    @PostMapping("/subject")
    public ResponseEntity<String> saveSubject(@RequestBody SubjectRequest request) {
        String subjectName = request.getSubjectName();
        System.out.println(subjectName);

        subjectService.saveSubject(subjectName);

        return ResponseEntity.ok("Subject saved");

    }

    @PostMapping("subject/{subjectId}/week")
    public ResponseEntity<String> createNextWeek(@PathVariable long subjectId){ //SubjectRequest subjectRequest) {
        // SubjectRequest로부터 subjectId를 추출하여 Subject 객체를 가져옴
        //long subjectId = subjectRequest.getSubjectId();
        //weekRequest.setWeekId(subjectId);
        Subject subject = subjectService.getSubjectById(subjectId);

        if (subject == null) {
            return ResponseEntity.notFound().build();
        }

        Week week = weekService.createNextWeek(subject);

        //WeekResponse response = new WeekResponse(week.getWeekNum());
        return ResponseEntity.ok("weekNum saved: " + week.getWeekNum());
    }

    /*@GetMapping("/week/dummy")
    public ResponseEntity<WeekResponse> getWeekNum(@PathVariable int num) {
        Week week = weekService.getWeekByNum(num);
        if (week == null) {
            return ResponseEntity.notFound().build();
        }

        WeekResponse response = new WeekResponse(week.getWeekNum());
        return ResponseEntity.ok(response);
    }*/

    @GetMapping("subject/{subjectId}/week")
    public ResponseEntity<List<Integer>> getWeekNum(@PathVariable Long subjectId) {
        Subject subject = subjectService.getSubjectById(subjectId);
        if (subject == null) {
            return ResponseEntity.notFound().build();
        }

        //WeekRepository weekRepository;
        List<Integer> weekNums = weekRepository.findAllWeekNumbersBySubject(subject);
        return ResponseEntity.ok(weekNums);
    }

    /*@PatchMapping("/content/{id}")
    public ResponseEntity<String> updateContent(@PathVariable Long id, @RequestBody String content) {
        Week week = weekService.getWeekById(id);
        Content existingContent = contentService.getContentById(id);
        if (existingContent == null) {
            return ResponseEntity.notFound().build();
        }

        existingContent.setContent(content);
        contentService.saveContent(existingContent);

        return ResponseEntity.ok("Content updated");
    }*/

    @PatchMapping("subject/{subjectId}/week/{weekNum}/content")
    public ResponseEntity<String> addContentToWeek(@PathVariable long subjectId, @PathVariable int weekNum, @RequestBody String contentText) {
        /*Week week = weekService.getWeekById(weekNum);
        if (week == null) {
            return ResponseEntity.notFound().build();
        }*/

        Subject subject = subjectService.getSubjectById(subjectId);
        if (subject == null) {
            return ResponseEntity.notFound().build();
        }

        Week week = weekService.getWeekBySubjectAndWeekNum(subject, weekNum);
        if (week == null) {
            return ResponseEntity.notFound().build();
        }

        week.setSubject(subject);

        //weekService.addContentToCurrentWeek(contentText);
        //return ResponseEntity.ok("Content added to Week successfully");

        String addedContent = weekService.addContentToCurrentWeek(subjectId, weekNum, contentText);
        return ResponseEntity.ok(addedContent + "\nSubject: " + subject.getSubjectName() + " , weekNum: " + weekNum);
    }
}
