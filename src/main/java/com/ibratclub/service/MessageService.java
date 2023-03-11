package com.ibratclub.service;

import com.ibratclub.dto.ApiResponse;
import com.ibratclub.model.Message;
import com.ibratclub.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;


    public ApiResponse<List<Message>> getAll() {
        List<Message> list = messageRepository.findAll();
        return ApiResponse.<List<Message>>builder().
                status(200).
                success(true).
                message("Here").
                data(list).
                build();
    }

//    public ApiResponse<Message> getOne(Long id) {
//        Optional<Message> messageOptional = messageRepository.findById(id);
//        if (messageOptional.isEmpty()) {
//            return ApiResponse.<Message>builder().
//                    status(400).
//                    success(false).
//                    message("Not found").
//                    build();
//        }
//        return ApiResponse.<Message>builder().
//                status(200).
//                success(true).
//                message("Here").
//                data(messageOptional.get()).
//                build();
//    }

//    public ApiResponse<Message> add(MessageDTO dto) {
//        Message message = new Message();
//        message.se(dto.getText());
//        message.setPhone(dto.getPhone());
//        try {
//            message.setStatusType(StatusType.valueOf(dto.getStatusType()));
//        }catch (Exception e){
//            return ApiResponse.<Message>builder().
//                    status(400).
//                    success(false).
//                    message("This enum type not supported").
//                    build();
//        }
//
//        Message save = messageRepository.save(message);
//        return ApiResponse.<Message>builder().
//                status(201).
//                success(true).
//                message("Saved").
//                data(save).
//                build();
//    }
//
//    public ApiResponse<Message> edit(Long id, MessageDTO dto) {
//        Optional<Message> messageOptional = messageRepository.findById(id);
//        if (messageOptional.isEmpty()) {
//            return ApiResponse.<Message>builder().
//                    status(400).
//                    success(false).
//                    message("Not found").
//                    build();
//        }
//        Message message = messageOptional.get();
//        message.setText(dto.getText());
//        message.setPhone(dto.getPhone());
//        try {
//            message.setStatusType(StatusType.valueOf(dto.getStatusType()));
//        }catch (Exception e){
//            return ApiResponse.<Message>builder().
//                    status(400).
//                    success(false).
//                    message("This enum type not supported").
//                    build();
//        }
//
//        Message save = messageRepository.save(message);
//        return ApiResponse.<Message>builder().
//                status(201).
//                success(true).
//                message("Saved").
//                data(save).
//                build();
//    }
//
//    public ApiResponse<?> delete(Long id) {
//        Optional<Message> messageOptional = messageRepository.findById(id);
//        if (messageOptional.isEmpty()) {
//            return ApiResponse.<Message>builder().
//                    status(400).
//                    success(false).
//                    message("Not found").
//                    build();
//        }
//        messageRepository.deleteById(id);
//        return ApiResponse.builder().
//                status(200).
//                success(true).
//                message("Deleted").
//                build();
//    }

    public ApiResponse<Page<Message>> getAllByStatus(int page, int size, int status, String employeePhone) {

        ApiResponse<Page<Message>> response = new ApiResponse<>();
        if (status > 3 || status < 0) {
            response.setMessage("Flag not supported!!!");
            response.setStatus(400);
            response.setSuccess(false);
            return response;
        }
        if (!employeePhone.startsWith("+")){
            employeePhone = "+" + employeePhone;
        }
        Page<Message> list = messageRepository.findAllByFlagAndEmployeePhone(status, employeePhone, PageRequest.of(page, size));
        LocalDateTime dateTime = LocalDateTime.now();
        for (Message message : list.getContent()) {
            if (message.getFlag() != 3) {
                message.setFlag(2);
                message.setSana(dateTime);
            }
        }
        messageRepository.saveAll(list.getContent());

        response.setMessage("Here!!!");
        response.setStatus(200);
        response.setSuccess(true);
        response.setData(list);
        return response;
    }


    public boolean editStatus(List<Integer> list) {
        try {
            List<Message> messageList = messageRepository.findAllById(list);
            for (Message message : messageList) {
                message.setFlag(3);
            }
            messageRepository.saveAll(messageList);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public ApiResponse<Page<Message>> getAllByEmployee(String phone, int flag, int page, int size) {
        Page<Message> messages;
        ApiResponse<Page<Message>> response = new ApiResponse<>();
        if (flag > 3 || flag < 0) {
            response.setMessage("Flag not supported!!!");
            response.setStatus(400);
            response.setSuccess(false);
            return response;
        }
        if (!phone.startsWith("+")){
            phone = "+" + phone;
        }
        if (flag == 0) {
            messages = messageRepository.findAllByEmployeePhone(phone, PageRequest.of(page, size));
        } else {
            messages = messageRepository.findAllByFlagAndEmployeePhone(flag, phone, PageRequest.of(page, size));
        }
        response.setMessage("Here!!!");
        response.setStatus(200);
        response.setSuccess(true);
        response.setData(messages);
        return response;
    }
}
