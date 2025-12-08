package com.managment_system.hms.repository;

import com.managment_system.hms.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    // JpaRepository gives us methods like save(), findAll(), delete(), etc., for free!

    // We can also define custom queries by naming methods correctly:
    Optional<Room> findByRoomNumber(String roomNumber);
}
