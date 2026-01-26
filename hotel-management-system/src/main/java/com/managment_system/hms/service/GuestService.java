package com.managment_system.hms.service;

import com.managment_system.hms.entity.Guest;
import com.managment_system.hms.repository.GuestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class GuestService {

    private final GuestRepository guestRepository;

    private static final Logger log = LoggerFactory.getLogger(GuestService.class);

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

    public Guest guestUpdateById(Long id, Guest guest){
        return guestRepository.findById(id)
                .map(existingGuest ->{
                    existingGuest.setCompleteName(guest.getCompleteName());
                    existingGuest.setPhoneNumber(guest.getPhoneNumber());
                    existingGuest.setEmail(guest.getEmail());
                    existingGuest.setGender(guest.getGender());

                    return guestRepository.save(existingGuest);
                })
                .orElseThrow(()->new RuntimeException("Guest not found" + id));
    }

    public void deleteGuest(Long id){
        if((id==null) || (!guestRepository.existsById(id))){
            throw new RuntimeException("ID did not exist " + id);
        }
         guestRepository.deleteById(id);

    }

    public Guest updateGuestByPatch(Long id, Guest guest){
        Guest existingGuest = guestRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("ID not found "+ id));
        if(guest.getCompleteName()!=null){
            existingGuest.setCompleteName(guest.getCompleteName());
        }
        if(guest.getPhoneNumber()!=null){
            existingGuest.setPhoneNumber(guest.getPhoneNumber());
        }
        if(guest.getEmail()!=null){
            existingGuest.setEmail(guest.getEmail());
        }
        if(guest.getGender()!=null){
            existingGuest.setGender(guest.getGender());
        }
        return guestRepository.save(existingGuest);
    }
}
