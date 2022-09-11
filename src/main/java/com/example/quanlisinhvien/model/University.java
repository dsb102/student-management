package com.example.quanlisinhvien.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_universitys")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(name = "is_del_flg")
    private boolean isDelFlg;

    private Long createId;

    private LocalDateTime createAt;

    private Long modifiedId;

    private LocalDateTime modifiedAt;
}
