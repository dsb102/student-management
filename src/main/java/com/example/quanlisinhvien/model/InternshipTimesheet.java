package com.example.quanlisinhvien.model;

import com.example.quanlisinhvien.model.eenum.Time;
import com.example.quanlisinhvien.validation.TodayOrFuture;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_internship_timesheets")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InternshipTimesheet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "internship_id", nullable = false)
    private Internship internship;

    @Column(name = "working_day")
    @TodayOrFuture
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate workingDay;

    @Enumerated(EnumType.STRING)
    private Time time;

    @Column(name = "is_del_flg")
    private boolean isDelFlg;

    private Long createId;

    private LocalDateTime createAt;

    private Long modifiedId;

    private LocalDateTime modifiedAt;
}
