package com.ibratclub.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Malikov Azizjon  *  15.01.2023  *  18:19   *  IbratClub
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RequestDTO {

    private Long productId;
    private String name, email, phone;
    private String aboutProduct, category;

}
