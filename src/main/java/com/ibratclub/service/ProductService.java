package com.ibratclub.service;

import com.ibratclub.dto.ApiResponse;
import com.ibratclub.dto.ProductDTO;
import com.ibratclub.model.Category;
import com.ibratclub.model.Product;
import com.ibratclub.repository.CategoryRepository;
import com.ibratclub.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

/**
 * @author Malikov Azizjon  *  16.01.2023  *  17:45   *  IbratClub
 */
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final AttachmentRepository attachmentRepository;

    public ApiResponse<List<Product>> getAll() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            return ApiResponse.<List<Product>>builder().
                    message("Not Found").
                    status(204).
                    success(false).
                    build();
        } else {
            return ApiResponse.<List<Product>>builder().
                    message("Here").
                    status(200).
                    success(true).
                    data(products).
                    build();
        }
    }

    public ApiResponse<Product> getOne(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            return ApiResponse.<Product>builder().
                    message("Not Found").
                    status(204).
                    success(false).
                    build();
        } else {
            return ApiResponse.<Product>builder().
                    message("Here").
                    status(200).
                    success(true).
                    data(optionalProduct.get()).
                    build();
        }
    }

    @SneakyThrows
    public ApiResponse<?> save(ProductDTO productDTO) {
        Optional<Category> categoryOptional = categoryRepository.findById(productDTO.getCategoryId());
        if (categoryOptional.isEmpty()) {
            return ApiResponse.builder().
                    message("Category not found").
                    status(204).
                    success(false).
                    build();
        }
        Product product = new Product();
        if (productDTO.getAttachment() != null) {
            MultipartFile photo = productDTO.getAttachment();
            Attachment attachment = new Attachment();
            attachment.setBytes(photo.getBytes());
            attachment.setOriginalName(photo.getOriginalFilename());
            attachment.setContentType(photo.getContentType());
            attachment.setSize(photo.getSize());
            product.setAttachment(attachment);
        }
        product.setCategory(categoryOptional.get());
        product.setPrice(productDTO.getPrice());
        product.setNameRu(productDTO.getNameRu());
        product.setNameUz(productDTO.getNameUz());
        product.setNameEn(productDTO.getNameEn());
        product.setDescriptionRu(productDTO.getDescriptionRu());
        product.setDescriptionUz(productDTO.getDescriptionUz());
        product.setDescriptionEn(productDTO.getDescriptionEn());

        productRepository.save(product);

        return ApiResponse.builder().
                message("Saved").
                status(200).
                success(true).
                build();
    }

    @SneakyThrows
    public ApiResponse<?> edit(ProductDTO productDTO, Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        Optional<Category> optionalCategory = categoryRepository.findById(productDTO.getCategoryId());
        if (optionalProduct.isEmpty()) {
            return ApiResponse.builder().
                    message("Product Not Found").
                    status(204).
                    success(false).
                    build();
        }
        if (optionalCategory.isEmpty()) {
            return ApiResponse.builder().
                    message("Category Not Found").
                    status(204).
                    success(false).
                    build();
        }
        Product product = optionalProduct.get();
        if (productDTO.getAttachment() != null) {
            MultipartFile photo = productDTO.getAttachment();
            Attachment attachment = new Attachment();
            if (product.getAttachment() != null)
                attachment = product.getAttachment();
            attachment.setBytes(photo.getBytes());
            attachment.setOriginalName(photo.getOriginalFilename());
            attachment.setContentType(photo.getContentType());
            attachment.setSize(photo.getSize());
            product.setAttachment(attachment);
        }
        product.setNameUz(productDTO.getNameUz());
        product.setNameRu(productDTO.getNameRu());
        product.setNameEn(productDTO.getNameEn());
        product.setDescriptionUz(productDTO.getNameUz());
        product.setDescriptionRu(productDTO.getDescriptionRu());
        product.setDescriptionEn(productDTO.getDescriptionEn());
        product.setPrice(productDTO.getPrice());
        product.setCategory(optionalCategory.get());


        productRepository.save(product);

        return ApiResponse.builder().
                message("Edited !").
                status(200).
                success(true).
                build();
    }

    public ApiResponse<?> delete(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            return ApiResponse.builder().
                    message("Product Not Found").
                    status(204).
                    success(false).
                    build();
        }
        productRepository.deleteById(id);
        return ApiResponse.builder().
                message("Deleted").
                status(201).
                success(true).
                build();
    }

    public ResponseEntity<?> getPhoto(Long id) {
        Optional<Attachment> attachmentOptional = attachmentRepository.findById(id);
        if (attachmentOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Photo not found!!!");
        }
        Attachment attachment = attachmentOptional.get();
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(attachment.getContentType()))
                .contentLength(attachment.getSize())
                .body(attachment.getBytes());
    }

}