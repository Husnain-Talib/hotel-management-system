package com.managment_system.hms.controller;

import com.managment_system.hms.entity.Room;
import com.managment_system.hms.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Tells Spring this class handles REST API requests
@RequestMapping("/api/v1/rooms") // Base URL. All endpoints here start with /api/rooms
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public ResponseEntity<Room> createRoom(@Valid @RequestBody Room room) {
        Room savedRoom = roomService.saveRoom(room);
        return ResponseEntity.ok(savedRoom);
    }

    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoom(@PathVariable Long id) {
        Room room = roomService.getRoomById(id);
        return ResponseEntity.ok(room);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @RequestBody Room room){
        Room room1 = roomService.updateRoomById(id, room);
        return ResponseEntity.ok(room1);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Room> updateRoomUsingPatch(@PathVariable Long id, @RequestBody Room room){
        Room room1 = roomService.updateRoomUsingPatch(id, room);
        return ResponseEntity.ok(room1);
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable Long id){
        roomService.deleteRoomById(id);
    }
}