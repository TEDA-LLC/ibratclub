package com.ibratclub.service;

import com.ibratclub.dto.ApiResponse;
import com.ibratclub.model.Product;
import com.ibratclub.model.QrCode;
import com.ibratclub.repository.ProductRepository;
import com.ibratclub.repository.QrCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Malikov Azizjon  *  23.01.2023  *  23:36   *  IbratClub
 */
@Service
@RequiredArgsConstructor
public class EventService {
    @Value("${page.size}")
    private int size;
    @Value("${telegram.bot.id}")
    private Long botId;
    private final ProductRepository productRepository;
    private final QrCodeRepository qrCodeRepository;

    public ApiResponse<List<QrCode>> getByEvent(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty() || !productOptional.get().getCategory().getBot().getId().equals(botId)){
            return ApiResponse.<List<QrCode>>builder().
                    message("Not found!!!").
                    status(400).
                    success(false).
                    build();
        }
        List<QrCode> allByProduct = qrCodeRepository.findAllByProduct(productOptional.get());
        return ApiResponse.<List<QrCode>>builder().
                message("Here!!!").
                status(200).
                success(true).
                data(allByProduct).
                build();
    }

    public ApiResponse<QrCode> getOne(String id) {
        Optional<QrCode> codeOptional = qrCodeRepository.findById(UUID.fromString(id));
        if (codeOptional.isEmpty() || !codeOptional.get().getProduct().getCategory().getBot().getId().equals(botId)){
            return ApiResponse.<QrCode>builder().
                    message("Not found!!!").
                    status(400).
                    success(false).
                    build();
        }
        return ApiResponse.<QrCode>builder().
                message("Here!!!").
                status(200).
                success(true).
                data(codeOptional.get()).
                build();
    }

    public ApiResponse<?> edit(String id) {
        Optional<QrCode> codeOptional = qrCodeRepository.findById(UUID.fromString(id));
        if (codeOptional.isEmpty() || !codeOptional.get().getProduct().getCategory().getBot().getId().equals(botId)){
            return ApiResponse.<QrCode>builder().
                    message("Not found!!!").
                    status(400).
                    success(false).
                    build();
        }
        QrCode qrCode = codeOptional.get();
        qrCode.setArrivalTime(LocalDateTime.now());
        QrCode saveQrCode = qrCodeRepository.save(qrCode);
        return ApiResponse.builder().
                message("Success").
                success(true).
                status(200).
                data(saveQrCode).
                build();
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
