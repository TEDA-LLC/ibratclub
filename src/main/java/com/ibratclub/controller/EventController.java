package com.ibratclub.controller;

import com.ibratclub.dto.ApiResponse;
import com.ibratclub.model.User;
import com.ibratclub.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Malikov Azizjon  *  23.01.2023  *  23:51   *  IbratClub
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/event")
public class EventController {

    private final EventService eventService;

    @GetMapping
    public ResponseEntity<?> getByEvent(@RequestParam(defaultValue = "0") int page, @RequestParam Long id){
        ApiResponse<Page<User>> response = eventService.getByEvent(id, page);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
//    @GetMapping
//    public ResponseEntity<?> getAll() {
//        ApiResponse<List<Event>> response = eventService.getAll();
//        return ResponseEntity.status(response.getStatus()).body(response);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getOne(@PathVariable Long id) {
//        ApiResponse<Event> response = eventService.getOne(id);
//        return ResponseEntity.status(response.getStatus()).body(response);
//    }
//
//    @PostMapping
//    public ResponseEntity<?> add(@ModelAttribute EventDTO eventDTO) {
//        ApiResponse<?> response = eventService.add(eventDTO);
//        return ResponseEntity.status(response.getStatus()).body(response);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<?> edit(@ModelAttribute EventDTO eventDTO, @PathVariable Long id) {
//        ApiResponse<?> response = eventService.edit(eventDTO, id);
//        return ResponseEntity.status(response.getStatus()).body(response);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> delete(@PathVariable Long id) {
//        ApiResponse<?> response = eventService.delete(id);
//        return ResponseEntity.status(response.getStatus()).body(response);
//    }

}