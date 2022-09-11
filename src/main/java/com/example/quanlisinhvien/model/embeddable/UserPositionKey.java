package com.example.quanlisinhvien.model.embeddable;

import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class UserPositionKey implements Serializable {

    @Column(name = "user_id")
    Long userId;

    @Column(name = "position_id")
    Long positionId;
}
