package com.example.quanlisinhvien.model;

import com.example.quanlisinhvien.model.eenum.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_users")
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long userId;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @JsonBackReference
    private Internship internship;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Mentor mentor;

//    @Column(unique = true)
    protected String username;

    protected String password;

    @Email
    protected String email;

    @Column(name = "fullname")
    protected String fullName;

    @Column(name = "phone_number")
    protected String phoneNumber;

    protected String skype;

    @Enumerated(EnumType.STRING)
    protected Role role;

    @Column(name = "is_del_flg")
    protected boolean isDelFlg;

    protected Long createId;

    protected LocalDateTime createAt;

    protected Long modifiedId;

    protected LocalDateTime modifiedAt;

    @OneToMany(mappedBy = "user")
    private Set<UserPosition> userPositionSet;

    public boolean hasRole(String roleName) {
        return this.role.getAuthority().equals(roleName);
    }
}
