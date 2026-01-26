package com.managment_system.hms.controller;

import com.managment_system.hms.entity.Guest;
import com.managment_system.hms.service.GuestService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
// 1)needs to create dto for request and response (with proper validation)
// 2) then we will create exception handing
// 3) in future we will use Pageable (spring data) instead of list
// 4) we will use Springdoc OpenAPI (Swagger)

@RestController
@RequestMapping("api/v1/guests")
public class GuestController {

    private static final Logger log = LoggerFactory.getLogger(GuestController.class);
    public GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @PostMapping
    public ResponseEntity<Guest> addGuest(@Valid @RequestBody Guest guest) {
        log.info("Creating guest with data: {}", guest);

        Guest savedGuest = guestService.saveGuest(guest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedGuest.getId())
                .toUri();

        log.info("Guest created successfully with id {}", savedGuest.getId());

        return ResponseEntity.created(location).body(savedGuest);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Guest>> showGuest() {
        log.info("Getting all the users ");

        List<Guest> allGuest = guestService.guestList();

        log.info("All users : {}", allGuest);
        return ResponseEntity.ok(allGuest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Guest> findGuestById(@PathVariable Long id) {
        log.info("Get user/guest by id {}", id);

        Guest findGuest = guestService.guestById(id);

        log.info("Response : {}", findGuest);
        return ResponseEntity.ok(findGuest);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGuestById(@PathVariable Long id, @Valid @RequestBody Guest guest) {
        log.info("Request for UPDATE : ID {} User :{}", id, guest);

        Guest existingGuest = guestService.guestUpdateById(id, guest);

        log.info("Response for UPDATE : ID {} User :{}", id, existingGuest);
        return ResponseEntity.ok(existingGuest);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateGuestPartiallyById(@PathVariable Long id, @RequestBody Guest guest) {
        log.info("Request for Partially UPDATE : ID {} User :{}", id, guest);

        Guest existingGuest = guestService.updateGuestByPatch(id, guest);

        log.info("Request for Partially UPDATE : ID {} User :{}", id, existingGuest);
        return ResponseEntity.ok(existingGuest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeGuest(@PathVariable Long id) {
        log.warn("DELETE /guest/{} called", id);

        guestService.deleteGuest(id);

        log.info("Guest {} deleted successfully", id);
        return ResponseEntity.noContent().build();
    }

}
