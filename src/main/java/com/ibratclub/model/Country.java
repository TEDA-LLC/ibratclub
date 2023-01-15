package com.ibratclub.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author Malikov Azizjon  *  15.01.2023  *  21:59   *  IbratClub
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String shortName;

    public Country(String name, String shortName) {
        this.name = name;
        this.shortName = shortName;
    }

}
