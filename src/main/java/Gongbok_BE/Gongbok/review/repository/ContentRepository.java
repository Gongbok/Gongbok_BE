package Gongbok_BE.Gongbok.review.repository;

import Gongbok_BE.Gongbok.review.domain.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Content, Long> {
    // 추가적인 메서드 선언 및 쿼리 작성
}