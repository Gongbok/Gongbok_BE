package Gongbok_BE.Gongbok.member.repository;

import Gongbok_BE.Gongbok.member.domain.StarHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StarHistoryRepository extends JpaRepository<StarHistory, Long> {
    List<StarHistory> findByMember_Id(Long memberId);
}