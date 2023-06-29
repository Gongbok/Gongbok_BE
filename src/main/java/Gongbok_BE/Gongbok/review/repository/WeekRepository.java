package Gongbok_BE.Gongbok.review.repository;

import Gongbok_BE.Gongbok.review.domain.Subject;
import Gongbok_BE.Gongbok.review.domain.Week;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WeekRepository extends JpaRepository<Week, Integer> {
    Week findBySubjectAndWeekNum(Subject subject, int weekNum);

    @Query("SELECT COALESCE(MAX(w.weekNum), 0) FROM Week w WHERE w.subject = :subject")
    int findLastWeekNumberBySubject(@Param("subject") Subject subject);

    @Query("SELECT w.weekNum FROM Week w WHERE w.subject = :subject")
    List<Integer> findAllWeekNumbersBySubject(@Param("subject") Subject subject);
}