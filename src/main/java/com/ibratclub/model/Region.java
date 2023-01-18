package com.ibratclub.model;

import javax.persistence.*;
import lombok.*;

/**
 * @author Malikov Azizjon  *  15.01.2023  *  21:58   *  IbratClub
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Country country;

    public Region(String name, String description, Country country) {
        this.name = name;
        this.description = description;
        this.country = country;
    }

}
