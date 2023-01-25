package com.ibratclub.dto;

import com.ibratclub.model.BankInfo;
import com.ibratclub.model.enums.ActiveTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Malikov Azizjon  *  25.01.2023  *  13:00   *  IbratClub
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyDTO {

    private Long memberOrganizationId;
    private String name, stirNumber;
    private ActiveTypes activeType;
    private BankInfo bankInfo;
    private MultipartFile attachment;

    private Long directorId;

    private AddressDTO addressDTO;

}
