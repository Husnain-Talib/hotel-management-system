package com.managment_system.hms.controller;

import com.managment_system.hms.entity.Guest;
import com.managment_system.hms.service.GuestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guest")
public class GuestController {
    public GuestService guestService;

    public GuestController(GuestService guestService){
        this.guestService=guestService;
    }
    @PostMapping
    public ResponseEntity<Guest> addGuest(@RequestBody Guest guest){
        Guest addGuest = guestService.saveGuest(guest);
        return ResponseEntity.ok(addGuest);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Guest>> showGuest(){
        List<Guest> allGuest = guestService.guestList();
        return ResponseEntity.ok(allGuest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Guest>  findGuestById(@PathVariable Long id){
        Guest findGuest = guestService.guestById(id);
        return ResponseEntity.ok(findGuest);

    }
}
