package com.example.quanlisinhvien.model;

import com.example.quanlisinhvien.model.embeddable.MentorInternshipKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_mentor_internships")
public class MentorInternship {
    @EmbeddedId
    private MentorInternshipKey id = new MentorInternshipKey();

    @ManyToOne
    @MapsId("mentorId")
    @JoinColumn(name = "mentor_id")
    private Mentor mentor;

    @ManyToOne
    @MapsId("internshipId")
    @JoinColumn(name = "internship_id")
    private Internship internship;

    @Column(name = "position_id")
    private Long positionId;

    @Column(name = "is_del_flg")
    private boolean isDelFlg;

    private Long createId;

    private LocalDateTime createAt;

    private Long modifiedId;

    private LocalDateTime modifiedAt;
}
