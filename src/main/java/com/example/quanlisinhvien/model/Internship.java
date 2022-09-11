package com.example.quanlisinhvien.model;

import com.example.quanlisinhvien.model.eenum.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name="tbl_internships")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Internship{

    @Id
    private Long internshipId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @OneToOne
    @JoinColumn(name = "university_id", referencedColumnName = "id")
    private University university;

    @OneToMany(mappedBy = "internship", cascade = CascadeType.ALL)
    private Set<Reviews> reviewsSet;

    @OneToMany(mappedBy = "internship", cascade = CascadeType.ALL)
    private Set<InternshipTimesheet> internshipTimesheets;

    private int scholastic;

    @Column(name = "identify_card")
    private Long identifyCard;

    @Column(name = "mentor_id")
    private Long mentorId;

    @OneToOne
    @JoinColumn(name = "company_card_id", referencedColumnName = "id")
    private CompanyCard companyCard;

    private Long positionId;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Min(0)
    @Max(4)
    private int level;

    private String memo;
}
