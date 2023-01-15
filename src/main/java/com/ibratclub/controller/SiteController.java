package com.ibratclub.controller;

import com.ibratclub.dto.ApiResponse;
import com.ibratclub.dto.RequestDTO;
import com.ibratclub.model.Request;
import com.ibratclub.model.User;
import com.ibratclub.service.SiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Malikov Azizjon  *  15.01.2023  *  18:04   *  IbratClub
 */
@RestController
@RequestMapping("/api/site")
@RequiredArgsConstructor
public class SiteController {

    private final SiteService siteService;

    @GetMapping("/request")
    public ResponseEntity<?> getRequest() {
        ApiResponse<List<Request>> response = siteService.getRequest();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam(required = false) String email, @RequestParam(required = false) String phone){
        ApiResponse<User> response = siteService.login(email, phone);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/history")
    public ResponseEntity<?> getHistory() {
        ApiResponse<List<SiteHistory>> response = siteService.getSiteHistory();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody RequestDTO dto) {
        ApiResponse<?> response = siteService.add(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/history")
    public ResponseEntity<?> main(@RequestBody SiteHistory history, @RequestParam(required = false) String phone, @RequestParam String email) {
        ApiResponse<?> response = siteService.historyWriter(history, phone, email);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/review")
    public ResponseEntity<?> addReview(@RequestBody ReviewDTO dto) {
        ApiResponse<?> response = siteService.addReview(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PatchMapping("/review")
    public ResponseEntity<?> editStatusReview(@RequestParam Long id) {
        ApiResponse<?> response = siteService.editStatusReview(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/review/{id}")
    public ResponseEntity<?> getReview(@PathVariable Long id) {
        ApiResponse<Review> response = siteService.getReview(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/review")
    public ResponseEntity<?> getReviews(@RequestParam Boolean active) {
        ApiResponse<List<Review>> response = siteService.getReviews(active);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/reviewusers")
    public ResponseEntity<?> getReviewforUsers(){
        ApiResponse<List<Review>> response = siteService.getReviewforUsers();
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @GetMapping("/requestsByUser/{id}")
    public ResponseEntity<?> getOrdersByUser(@PathVariable Long id){
        ApiResponse<Map<String, List<Request>>> response = siteService.getRequestsByUser(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/request/{id}")
    public ResponseEntity<?> sendMessage(@PathVariable Long id){
        ApiResponse<?> response = siteService.sendMessage(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
