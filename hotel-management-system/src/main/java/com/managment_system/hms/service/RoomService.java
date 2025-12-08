package com.managment_system.hms.service;

import com.managment_system.hms.entity.Room;
import com.managment_system.hms.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    // Constructor Injection (Best Practice over @Autowired)
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    // 1. Save a new room
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    // 2. Get all rooms
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    // 3. Get room by ID
    public Room getRoomById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + id));
    }
}
