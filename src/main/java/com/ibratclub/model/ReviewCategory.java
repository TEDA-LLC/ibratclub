package com.ibratclub.model;

import javax.persistence.*;
import lombok.*;

/**
 * @author Malikov Azizjon  *  16.01.2023  *  17:33   *  IbratClub
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class ReviewCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private boolean active = true;

}
