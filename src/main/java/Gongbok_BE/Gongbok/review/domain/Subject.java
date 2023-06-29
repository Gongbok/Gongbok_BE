package Gongbok_BE.Gongbok.review.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "subjectId")
    private Long id;

    //@Column(name = "subjectName")
    private String subjectName;

    public Long getSubjectId() {
        return id;
    }

    public void setSubjectName(String name) {
        this.subjectName = name;
    }

    public String getSubjectName() {
        return subjectName;
    }

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Week> weeks = new ArrayList<>();
}


