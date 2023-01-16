package com.ibratclub.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Malikov Azizjon  *  16.01.2023  *  17:41   *  IbratClub
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductDTO {

    private String nameUz, nameRu, nameEn, descriptionUz, descriptionRu, descriptionEn;

    private Long categoryId;

    private MultipartFile attachment;

    private Double price;

}
