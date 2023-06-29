package Gongbok_BE.Gongbok.review.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "week")
public class Week {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weekId")
    private int id;

    @Column(name = "weekNum")
    private int weekNum;

    public void setWeekNum(int number) {
        this.weekNum = number;
        System.out.println("weekNumber = " + number);
    }

    public int getWeekNum() {
        return weekNum;
    }

    //public void setWeekId(long id) { this.id = id; }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subjectId")
    private Subject subject;

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @OneToOne(mappedBy = "week", cascade = CascadeType.ALL, orphanRemoval = true)
    private Content content;

    public void setContent(Content content) {
        if (content == null) {
            if (this.content != null) {
                this.content.setWeek(null);
            }
        } else {
            content.setWeek(this);
        }
        this.content = content;
    }
}
