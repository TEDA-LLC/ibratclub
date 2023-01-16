package com.ibratclub.controller;

import com.ibratclub.dto.ApiResponse;
import com.ibratclub.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Malikov Azizjon  *  16.01.2023  *  17:40   *  IbratClub
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;
    @GetMapping
    public ResponseEntity<?> getAll() {
        ApiResponse<List<Product>> response = productService.getAll();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        ApiResponse<Product> response = productService.getOne(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping
    public ResponseEntity<?> add(@ModelAttribute ProductDTO productDTO) {
        ApiResponse<?> response = productService.save(productDTO);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@ModelAttribute ProductDTO productDTO, @PathVariable Long id) {
        ApiResponse<?> response = productService.edit(productDTO, id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ApiResponse<?> response = productService.delete(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/photo/{id}")
    public ResponseEntity<?> getPhoto(@PathVariable Long id){
        return productService.getPhoto(id);
    }

}
