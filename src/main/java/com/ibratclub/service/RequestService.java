package com.ibratclub.service;

import com.ibratclub.dto.ApiResponse;
import com.ibratclub.model.Product;
import com.ibratclub.model.Request;
import com.ibratclub.model.User;
import com.ibratclub.repository.ProductRepository;
import com.ibratclub.repository.RequestRepository;
import com.ibratclub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RequestService {
    @Value("${company.department.id}")
    private Long departmentId;

    private final RequestRepository requestRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public List<Request> getProductUsersToExel(Long id) {
        return requestRepository.findAllByProduct_Id(id);
    }


    public ApiResponse<?> getProductUsers(Long id) {
        List<Request> allByProductId = requestRepository.findAllByProduct_Id(id);
        if (allByProductId == null) {
            return ApiResponse.builder()
                    .message(" Users not found ")
                    .status(404)
                    .success(false)
                    .build();
        }
        return ApiResponse.builder()
                .message(" Here !")
                .status(200)
                .success(true)
                .data(allByProductId)
                .build();
    }


    public ApiResponse<?> edit(Long eventId, String qrcode) {
        Optional<Product> productOptional = productRepository.findById(eventId);
        if (productOptional.isEmpty() || !productOptional.get().getCategory().getDepartment().getId().equals(departmentId)) {
            return ApiResponse.builder()
                    .message("Event not found ")
                    .status(400)
                    .success(false)
                    .build();
        }
        Optional<User> userOptional = userRepository.findByDepartment_IdAndQrcode(departmentId, UUID.fromString(qrcode));
        if (userOptional.isEmpty()) {
            return ApiResponse.builder()
                    .message("User not found ")
                    .status(400)
                    .success(false)
                    .build();
        }
        List<Request> requestList = requestRepository.findAllByProductAndUser(productOptional.get(), userOptional.get());
        if (requestList.isEmpty()) {
            return ApiResponse.builder()
                    .message("User is not registered")
                    .status(400)
                    .success(false)
                    .build();
        }
        Request request = requestList.get(0);
        if (request.getArrivalTime() != null) {
            return ApiResponse.builder()
                    .message("User is registered")
                    .status(200)
                    .success(true)
                    .data(request.getUser())
                    .build();
        }
        request.setArrivalTime(LocalDateTime.now());
        request.setView(true);
        requestRepository.save(request);
        return ApiResponse.builder()
                .message("Success ")
                .status(200)
                .success(true)
                .data(request.getUser())
                .build();
    }
}
