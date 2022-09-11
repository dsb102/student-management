package com.example.quanlisinhvien.model.embeddable;

import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class MentorInternshipKey implements Serializable {

    @Column(name = "mentor_id")
    Long mentorId;

    @Column(name = "internship_id")
    Long internshipId;
}
