package com.ibratclub.controller;

import com.ibratclub.dto.ApiResponse;
import com.ibratclub.dto.RequestDTO;
import com.ibratclub.dto.ReviewDTO;
import com.ibratclub.model.Country;
import com.ibratclub.model.District;
import com.ibratclub.model.Product;
import com.ibratclub.model.Region;
import com.ibratclub.model.Request;
import com.ibratclub.model.Review;
import com.ibratclub.model.SiteHistory;
import com.ibratclub.model.User;
import com.ibratclub.service.AddressService;
import com.ibratclub.service.ProductService;
import com.ibratclub.service.SiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
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
    private final ProductService productService;
    private final AddressService addressService;
    @GetMapping("/request")
    public ResponseEntity<?> getRequest() {
        ApiResponse<List<Request>> response = siteService.getRequest();
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @GetMapping("/getQrCodeByUser")
    public ResponseEntity<?> getQrCodeByUser(@RequestParam Long userId, HttpServletResponse response){
        return siteService.getQrCodeByUser(userId, response);
    }
    @GetMapping("/district")
    public ResponseEntity<?> getAllDistrict(@RequestParam(required = false) Long regionId){
        ApiResponse<List<District>> response = addressService.getAllDistricts(regionId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @GetMapping("/region")
    public ResponseEntity<?> getAllRegion(@RequestParam(required = false) Long countryId){
        ApiResponse<List<Region>> response = addressService.getAllRegions(countryId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @GetMapping("/country")
    public ResponseEntity<?> getAllCountry(){
        ApiResponse<List<Country>> response = addressService.getAllCountry();
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
    @PostMapping("/request")
    public ResponseEntity<?> addRequest(@ModelAttribute RequestDTO dto) {
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
        ApiResponse<List<Review>> response = siteService.getReviewForUsers();
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

    @GetMapping("/getQrCode")
    public ResponseEntity<?> getQrCode(@RequestParam Long requestId, HttpServletResponse response){
        return siteService.getQrCode(requestId, response);
    }
    @GetMapping("/product")
    public ResponseEntity<?> getAll() {
        ApiResponse<List<Product>> response = productService.getAll();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        ApiResponse<Product> response = productService.getOne(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PatchMapping("/request")
    public ResponseEntity<?> editRequest(@RequestParam Long id){
        ApiResponse<?> response = siteService.editRequest(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @GetMapping("/photo/{id}")
    public ResponseEntity<?> getPhoto(@PathVariable Long id){
        return productService.getPhoto(id);
    }
    @PatchMapping("/editphoto")
    public ResponseEntity<?> editPhoto(@ModelAttribute MultipartFile photo){
        ApiResponse<?> response = siteService.editPhoto(photo);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
