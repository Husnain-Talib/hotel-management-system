package com.managment_system.hms.controller;

import com.managment_system.hms.entity.Guest;
import com.managment_system.hms.service.GuestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/guest")
public class GuestController {
    public GuestService guestService;
    private static final Logger log = LoggerFactory.getLogger(GuestController.class);

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @PostMapping
    public ResponseEntity<Guest> addGuest(@RequestBody Guest guest) {
        log.info("POST /guest called with data: {}", guest);

        Guest savedGuest = guestService.saveGuest(guest);

        log.info("Guest created successfully with id {}", savedGuest.getId());
        return ResponseEntity.ok(savedGuest);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Guest>> showGuest() {
        List<Guest> allGuest = guestService.guestList();
        return ResponseEntity.ok(allGuest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Guest> findGuestById(@PathVariable Long id) {
        Guest findGuest = guestService.guestById(id);
        return ResponseEntity.ok(findGuest);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGuestById(@PathVariable Long id, @RequestBody Guest guest) {
        Guest guest1 = guestService.guestUpdateById(id, guest);
        return ResponseEntity.ok(guest1);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateGuestPartiallyById(@PathVariable Long id, @RequestBody Guest guest) {

        Guest guest1 = guestService.updateGuestByPatch(id, guest);
        return ResponseEntity.ok(guest1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeGuest(@PathVariable Long id) {
        log.warn("DELETE /guest/{} called", id);

        guestService.deleteGuest(id);

        log.info("Guest {} deleted successfully", id);
        return ResponseEntity.noContent().build();
    }

}
