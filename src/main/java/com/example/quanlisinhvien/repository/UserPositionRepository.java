package com.example.quanlisinhvien.repository;

import com.example.quanlisinhvien.model.UserPosition;
import com.example.quanlisinhvien.model.embeddable.UserPositionKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPositionRepository extends JpaRepository<UserPosition, UserPositionKey> {
}
