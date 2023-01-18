package com.ibratclub.model;

import javax.persistence.*;
import lombok.*;

/**
 * @author Malikov Azizjon  *  15.01.2023  *  18:47   *  IbratClub
 */

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private District district;

    private String streetHome;

//    private Double latitude, longitude;

    public Address(District district, String streetHome) {
        this.district = district;
        this.streetHome = streetHome;
    }

}
