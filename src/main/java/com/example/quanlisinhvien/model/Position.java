package com.example.quanlisinhvien.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Set;

@Table(name = "tbl_positions")
@Entity
@Data
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Không được trống trường này")
    private String name;

    @Column(name = "is_del_flg")
    private boolean isDelFlg;

    private Long createId;

    private LocalDateTime createAt;

    private Long modifiedId;

    private LocalDateTime modifiedAt;

    @OneToMany(mappedBy = "position")
    private Set<UserPosition> userPositionSet;
}
