package Gongbok_BE.Gongbok.review.dto;


public class SubjectResponse {
    private Long id;
    private String subjectName;

    public SubjectResponse(Long id, String subjectName) {
        this.id = id;
        this.subjectName = subjectName;
    }

    public Long getId() {
        return id;
    }

    public String getSubjectName() {
        return subjectName;
    }
}
