package com.ibratclub.controller;

import com.ibratclub.dto.ApiResponse;
import com.ibratclub.model.Company;
import com.ibratclub.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Malikov Azizjon  *  25.01.2023  *  13:05   *  IbratClub
 */

@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
//    @GetMapping
//    public ResponseEntity<?> getAllByCompany(@RequestParam(defaultValue = "0") int page, @RequestParam Boolean active) {
//        ApiResponse<Page<Company>> response = companyService.getAll(page, active);
//        return ResponseEntity.status(response.getStatus()).body(response);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        ApiResponse<Company> response = companyService.getOne(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllLIst(){
        ApiResponse<List<Company>> response = companyService.getAllList();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<?> getByUser(@RequestParam String phone){
        ApiResponse<List<Company>> response = companyService.getByUser(phone);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @GetMapping("/photo")
    public ResponseEntity<?> getPhoto(@RequestParam Long id){
        return companyService.getPhoto(id);
    }

//    @PostMapping
//    public ResponseEntity<?> add(@RequestBody CompanyDTO dto) {
//        ApiResponse<?> response = companyService.add(dto);
//        return ResponseEntity.status(response.getStatus()).body(response);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody CompanyDTO dto) {
//        ApiResponse<?> response = companyService.edit(id, dto);
//        return ResponseEntity.status(response.getStatus()).body(response);
//    }
//
//    @GetMapping("/me")
//    public ResponseEntity<?> getMe(@RequestParam Long id) {
//        Optional<Company> companyOptional = companyRepository.findById(id);
//        if (companyOptional.isEmpty()) {
//            return ResponseEntity.badRequest().body(ApiResponse.builder()
//                    .success(false)
//                    .message("Not authorization")
//                    .build());
//        }
//        return ResponseEntity.ok().body(companyOptional.get());
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> delete(@PathVariable Long id) {
//        ApiResponse<?> response = companyService.delete(id);
//        return ResponseEntity.status(response.getStatus()).body(response);
//    }

}
