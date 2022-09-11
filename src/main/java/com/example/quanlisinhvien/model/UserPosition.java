package com.example.quanlisinhvien.model;

import com.example.quanlisinhvien.model.embeddable.UserPositionKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_user_positions")
public class UserPosition {
    @EmbeddedId
    private UserPositionKey id = new UserPositionKey();

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("positionId")
    @JoinColumn(name = "position_id")
    private Position position;

    @Column(name = "is_del_flg")
    boolean isDelFlg;

    private Long createId;

    private LocalDateTime createAt;

    private Long modifiedId;

    private LocalDateTime modifiedAt;
}
