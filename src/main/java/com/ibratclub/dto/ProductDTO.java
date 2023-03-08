package com.ibratclub.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

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
    private AddressDTO address;
    private MultipartFile attachment;
//    @JsonFormat(pattern="yyyy-MM-ddTHH:mm")
    private String from, to;
    private Double price;
    private List<Long> speakersId;

}
