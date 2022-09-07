package com.example.quanlisinhvien.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_company_cards")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "companyCard")
    private Internship internship;

    private boolean usingFlg;

    @Column(name = "is_del_flg")
    private boolean isDelFlg;

    private Long createId;

    private LocalDateTime createAt;

    private Long modifiedId;

    private LocalDateTime modifiedAt;
}
