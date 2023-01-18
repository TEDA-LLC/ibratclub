package com.ibratclub.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.*;

/**
 * @author Malikov Azizjon  *  16.01.2023  *  18:34   *  IbratClub
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@ToString
public class BankInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String branch;

    private Integer mfo;

    private Integer accountNumber;

    private String currency;

}
