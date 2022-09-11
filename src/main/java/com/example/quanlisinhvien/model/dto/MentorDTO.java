package com.example.quanlisinhvien.model.dto;

import com.example.quanlisinhvien.validation.UsernameConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MentorDTO {
    private Long id;

    @Column(unique = true)
    @UsernameConstraint
    @Size(min=4, max = 30, message = "Độ dài từ 4 đến 30 kí tự")
    private String username;

    @Size(min = 8, message = "Mật khẩu có độ dài tối thiểu 8 kí tự")
    private String password;

    @Email
    private String email;

    private boolean isDelFlg;

    @NotBlank
    private String fullname;

    @NotBlank
    private String phoneNumber;

    private String skype;

    private Enum role;

    @NotNull
    private int maxInternship;
}
