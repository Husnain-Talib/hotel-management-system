package com.managment_system.hms.service;

import com.managment_system.hms.entity.Guest;
import com.managment_system.hms.repository.GuestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestService {

    private final GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository){
        this.guestRepository=guestRepository;
    }

    public Guest saveGuest(Guest guest){
        return guestRepository.save(guest);
    }

    public List<Guest> guestList(){
        return guestRepository.findAll();
    }

    public Guest guestById(Long id){
        return guestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + id));
    }
}
