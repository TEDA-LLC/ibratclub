package com.ibratclub.controller;

import com.ibratclub.dto.ApiResponse;
import com.ibratclub.dto.VacancyDTO;
import com.ibratclub.model.Vacancy;
import com.ibratclub.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Malikov Azizjon  *  16.01.2023  *  17:39   *  IbratClub
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vacancy")
public class VacancyController {

    private final VacancyService vacancyService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        ApiResponse<List<Vacancy>> response = vacancyService.getAll();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody VacancyDTO vacancyDTO) {
        ApiResponse<?> response = vacancyService.add(vacancyDTO);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        ApiResponse<Vacancy> response = vacancyService.getOne(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@RequestBody VacancyDTO vacancyDTO, @PathVariable Long id){
        ApiResponse<?> response = vacancyService.edit(vacancyDTO,id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        ApiResponse<?> response = vacancyService.delete(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
