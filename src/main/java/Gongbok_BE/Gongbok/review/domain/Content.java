package Gongbok_BE.Gongbok.review.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "content")
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contentId")
    private Long id;

    private String content;

    public void setContent(String content) {
        this.content = content;
        //System.out.println("weekNumber = " + number);
    }

    public String getContent() {
        return content;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "weekNum")
    private Week week;

    public void setWeek(Week week) {
        this.week = week;
    }
}
