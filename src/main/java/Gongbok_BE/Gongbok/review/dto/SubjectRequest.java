package Gongbok_BE.Gongbok.review.dto;

public class SubjectRequest {

    private String subjectName;
    private long subjectId;

    public String getSubjectName() {
        return subjectName;
    }

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

}