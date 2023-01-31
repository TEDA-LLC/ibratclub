package com.ibratclub.dto;

import lombok.*;

/**
 * @author Malikov Azizjon  *  25.01.2023  *  12:59   *  IbratClub
 */

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class AddressDTO {

    private Long districtId;
    private Double latitude, longitude;
    private String streetHome;

}
