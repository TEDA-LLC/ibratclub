package com.ibratclub.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

/**
 * @author Malikov Azizjon  *  16.01.2023  *  17:49   *  IbratClub
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(unique = true)
    private String nameUz;

//    @Column(unique = true)
    private String nameRu;

//    @Column(unique = true)
    private String nameEn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private Department department;

}
