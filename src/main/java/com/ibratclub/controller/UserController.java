package com.ibratclub.controller;

import com.ibratclub.dto.ApiResponse;
import com.ibratclub.dto.UserDTO;
import com.ibratclub.model.User;
import com.ibratclub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") int page){
        ApiResponse<Page<User>> response = userService.getAll(page);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        ApiResponse<User> response = userService.getOne(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody UserDTO dto, @RequestParam(required = false) Long eventId){
        ApiResponse<User> response = userService.add(dto, eventId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody UserDTO dto){
        ApiResponse<User> response = userService.edit(id, dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/getUserByQR")
    public ResponseEntity<?> getUserByQrCode(@RequestParam String code){
        ApiResponse<User> response = userService.getByQrCode(code);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
