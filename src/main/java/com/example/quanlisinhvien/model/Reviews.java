package com.example.quanlisinhvien.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_reviews")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int type;

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private Mentor mentor;

    @ManyToOne
    @JoinColumn(name = "internship_id")
    private Internship internship;

    @Min(0)
    @Max(5)
    private int rankReview;

    private String content;

    private boolean isDelFlg;

    private Long createdId;

    private LocalDateTime createAt;

    private Long modifiedId;

    private LocalDateTime modifiedAt;
}