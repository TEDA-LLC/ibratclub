package com.ibratclub.controller;

import com.ibratclub.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sms")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(messageService.getAll());
    }

    @GetMapping("/status")
    public ResponseEntity<?> getAllByStatus(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size,
                                            @RequestParam int status, @RequestParam String employeePhone) {
        return ResponseEntity.ok(messageService.getAllByStatus(page, size, status, employeePhone));
    }

    @PutMapping
    public ResponseEntity<?> edit(@RequestBody List<Integer> list) {
        return ResponseEntity.ok(messageService.editStatus(list));
    }

    @GetMapping("/{phone}")
    public ResponseEntity<?> getAllByEmployee(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size,
                                              @PathVariable String phone, @RequestParam(required = false, defaultValue = "0") int flag) {
        return ResponseEntity.ok(messageService.getAllByEmployee(phone, flag, page, size));
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<?> getOne(@PathVariable Long id){
//        ApiResponse<Message> response =  messageService.getOne(id);
//        return ResponseEntity.status(response.getStatus()).body(response);
//    }
//
//    @PostMapping
//    public ResponseEntity<?> add(@RequestBody MessageDTO dto){
//        ApiResponse<Message> response =  messageService.add(dto);
//        return ResponseEntity.status(response.getStatus()).body(response);
//    }
//    @PutMapping("/{id}")
//    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody MessageDTO dto){
//        ApiResponse<Message> response =  messageService.edit( id,dto);
//        return ResponseEntity.status(response.getStatus()).body(response);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> delete(@PathVariable Long id){
//        ApiResponse<?> response =  messageService.delete(id);
//        return ResponseEntity.status(response.getStatus()).body(response);
//    }

}
