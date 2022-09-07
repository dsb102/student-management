package com.example.quanlisinhvien.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_mentors")
@Getter
@Setter
@Builder
public class Mentor{
    @Id
    private Long mentorId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "mentor", cascade = CascadeType.ALL)
    private Set<Reviews> reviewsSet;

    @NotNull
    private int maxInternship;

    private boolean isActiveFlg;
}
