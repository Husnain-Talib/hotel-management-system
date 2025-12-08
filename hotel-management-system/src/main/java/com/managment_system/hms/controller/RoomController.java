package com.managment_system.hms.controller;

import com.managment_system.hms.entity.Room;
import com.managment_system.hms.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Tells Spring this class handles REST API requests
@RequestMapping("/api/rooms") // Base URL. All endpoints here start with /api/rooms
public class RoomController {

    private final RoomService roomService;

    // Constructor Injection
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    // 1. Add a new Room
    // POST http://localhost:8080/api/rooms
    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        Room savedRoom = roomService.saveRoom(room);
        return ResponseEntity.ok(savedRoom);
    }

    // 2. Get all Rooms
    // GET http://localhost:8080/api/rooms
    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }

    // 3. Get Room by ID
    // GET http://localhost:8080/api/rooms/1
    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoom(@PathVariable Long id) {
        Room room = roomService.getRoomById(id);
        return ResponseEntity.ok(room);
    }
}