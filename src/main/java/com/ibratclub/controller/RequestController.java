package com.ibratclub.controller;


import com.ibratclub.dto.ApiResponse;
import com.ibratclub.model.Request;
import com.ibratclub.service.RequestService;
import com.ibratclub.service.UserExcelExporter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/request")
public class RequestController {
    private final RequestService requestService;

    @GetMapping("/export/excel/{id}")
    public void exportToExcel(@PathVariable Long id, HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        List<Request> requestList = requestService.getProductUsersToExel(id);
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        UserExcelExporter excelExporter = new UserExcelExporter(requestList);
        excelExporter.export(response);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity exportToExcel1(@PathVariable Long id, HttpServletResponse response) throws IOException {
        ApiResponse<?> productUsers = requestService.getProductUsers(id);
        return ResponseEntity.status(productUsers.getStatus()).body(productUsers);
    }

    @PatchMapping("/{eventId}")
    public ResponseEntity<?> edit(@PathVariable Long eventId, @RequestParam String qrcode){
        ApiResponse<?> response = requestService.edit(eventId, qrcode);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
