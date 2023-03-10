package com.ibratclub.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {
    private String email, phone, company, howKnow, fullName, workType;
    private Long countryId, regionId;
}
