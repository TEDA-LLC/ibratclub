package com.ibratclub.service;

import com.ibratclub.dto.ApiResponse;
import com.ibratclub.model.Product;
import com.ibratclub.model.User;
import com.ibratclub.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Malikov Azizjon  *  23.01.2023  *  23:36   *  IbratClub
 */
@Service
@RequiredArgsConstructor
public class EventService {
    @Value("${page.size}")
    private int size;
    private final ProductRepository productRepository;

    public ApiResponse<Page<User>> getByEvent(Long id, int page) {
        Optional<Product> productOptional = productRepository.findById(id);
//        if (productOptional.isEmpty() || productOptional.get().getCategory().get)
    }


//    private final EventRepository eventRepository;

//    public ApiResponse<List<Event>> getAll() {
//        List<Event> events = eventRepository.findAll();
//        if (events.isEmpty()) {
//            return ApiResponse.<List<Event>>builder().
//                    message("Not Found").
//                    status(400).
//                    success(false).
//                    build();
//        } else {
//            return ApiResponse.<List<Event>>builder().
//                    message("Here").
//                    status(200).
//                    success(true).
//                    data(events).
//                    build();
//        }
//    }
//
//    public ApiResponse<Event> getOne(Long id) {
//        Optional<Event> optionalEvent = eventRepository.findById(id);
//        if (optionalEvent.isEmpty()) {
//            return ApiResponse.<Event>builder().
//                    message("Not Found").
//                    status(204).
//                    success(false).
//                    build();
//        } else {
//            return ApiResponse.<Event>builder().
//                    message("Here").
//                    status(200).
//                    success(true).
//                    data(optionalEvent.get()).
//                    build();
//        }
//    }
//
//    //    @SneakyThrows
//    public ApiResponse<?> add(EventDTO eventDTO) {
////        Optional<Event> vacancyOptional = eventRepository.findById(eventDTO.getId());
////        if (vacancyOptional.isEmpty()) {
////            return ApiResponse.builder().
////                    message("Category not found").
////                    status(400).
////                    success(false).
////                    build();
////        }
//
////        MultipartFile photo = productDTO.getPhoto();
////        Attachment attachment = new Attachment();
////        attachment.setBytes(photo.getBytes());
////        attachment.setOriginalName(photo.getOriginalFilename());
//
//        Event event = new Event();
//        event.setName(eventDTO.getName());
//        event.setDescription(eventDTO.getDescription());
//        event.setOrganizer(eventDTO.getOrganizer());
//        event.setContact(eventDTO.getContact());
//        event.setDateTime(eventDTO.getDateTime());
//        event.setEventAddress(eventDTO.getEventAddress());
//        event.setActive(eventDTO.isActive());
//
//        eventRepository.save(event);
//
//        return ApiResponse.builder().
//                message("Saved").
//                status(200).
//                success(true).
//                build();
//    }
//
//    @SneakyThrows
//    public ApiResponse<?> edit(EventDTO eventDTO, Long id) {
//        Optional<Event> optionalEvent = eventRepository.findById(id);
////        Optional<Event> optionalCategory = categoryRepository.findById(productDTO.getCategoryId());
//        if (optionalEvent.isEmpty()) {
//            return ApiResponse.builder().
//                    message("Event Not Found").
//                    status(204).
//                    success(false).
//                    build();
//        }
////        if (optionalEvent.isEmpty()) {
////            return ApiResponse.builder().
////                    message("Category Not Found").
////                    status(204).
////                    success(false).
////                    build();
////        }
//
////        MultipartFile photo = productDTO.getPhoto();
////        Attachment attachment = new Attachment();
////        attachment.setBytes(photo.getBytes());
////        attachment.setOriginalName(photo.getOriginalFilename());
//
//        Event event = optionalEvent.get();
//        event.setName(eventDTO.getName());
//        event.setDescription(eventDTO.getDescription());
//        event.setOrganizer(eventDTO.getOrganizer());
//        event.setContact(eventDTO.getContact());
//        event.setDateTime(eventDTO.getDateTime());
//        event.setEventAddress(eventDTO.getEventAddress());
//        event.setActive(eventDTO.isActive());
//        eventRepository.save(event);
//
//        return ApiResponse.builder().
//                message("Edited !").
//                status(200).
//                success(true).
//                build();
//    }
//
//    public ApiResponse<?> delete(Long id) {
//        Optional<Event> optionalEvent = eventRepository.findById(id);
//        if (optionalEvent.isEmpty()) {
//            return ApiResponse.builder().
//                    message("Event Not Found").
//                    status(204).
//                    success(false).
//                    build();
//        }
//        eventRepository.deleteById(id);
//        return ApiResponse.builder().
//                message("Deleted").
//                status(201).
//                success(true).
//                build();
//    }

}
