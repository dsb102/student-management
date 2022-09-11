package com.example.quanlisinhvien.model.dto;

import com.example.quanlisinhvien.validation.UsernameConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InternshipDTO {
    private Long id;

    @UsernameConstraint
    @Size(min=4, max = 30, message = "Độ dài từ 4 đến 30 kí tự")
    private String username;

    @Size(min = 8, message = "Mật khẩu có độ dài tối thiểu 8 kí tự")
    private String password;

    @Email
    private String email;

    @NotBlank(message = "Không để trống họ tên")
    private String fullname;

    @NotBlank(message = "Không để trống số điện thoại")
    private String phoneNumber;

    private String skype;

    private Enum role;

    private boolean isDelFlg;

    @Past(message = "Ngày sinh phải là quá khứ")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    private Long universityId;

    private int scholastic;

    private String identifyCard;

    private Long mentorId;

    private Long companyCardId;
}
