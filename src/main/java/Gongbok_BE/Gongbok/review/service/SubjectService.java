package Gongbok_BE.Gongbok.review.service;


import Gongbok_BE.Gongbok.review.domain.Subject;
import Gongbok_BE.Gongbok.review.repository.SubjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class SubjectService {

    private SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id).orElse(null);
    }

    public Subject saveSubject(String subjectName) {
        Subject subject = new Subject();
        subject.setSubjectName(subjectName);
        return subjectRepository.save(subject);
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }
}
