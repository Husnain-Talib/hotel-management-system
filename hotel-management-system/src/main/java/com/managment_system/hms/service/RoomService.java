package com.managment_system.hms.service;

import com.managment_system.hms.entity.Room;
import com.managment_system.hms.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + id));
    }

    public Room updateRoomById(Long id, Room room){
        Room existingRoom = roomRepository.findById(id).orElseThrow(()-> new RuntimeException("Room ID did not match to any Room"));

            existingRoom.setRoomNumber(room.getRoomNumber());
            existingRoom.setType(room.getType());
            existingRoom.setPricePerNight(room.getPricePerNight());
            roomRepository.save(existingRoom);

        return existingRoom;
    }

    public Room updateRoomUsingPatch(Long id, Room room){
        Room existingRoom = roomRepository.findById(id).orElseThrow(()-> new RuntimeException("ID not found"));

        if (room.getRoomNumber() != null) {
            existingRoom.setRoomNumber(room.getRoomNumber());
        }
        if (room.getType() != null) {
            existingRoom.setType(room.getType());
        }
        if (room.getPricePerNight() != null) {
            existingRoom.setPricePerNight(room.getPricePerNight());
        }

        return roomRepository.save(existingRoom);

    }

    public void deleteRoomById(Long id){
        if(!roomRepository.existsById(id)){
            throw new RuntimeException("ID did not found {}" + id);
        }
      roomRepository.deleteById(id);
    }
}
