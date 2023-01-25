package com.ibratclub.model;

import javax.persistence.*;
import lombok.*;

/**
 * @author Malikov Azizjon  *  16.01.2023  *  17:42   *  IbratClub
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "text")
    private String nameUz, nameRu, nameEn, descriptionUz, descriptionRu, descriptionEn;

    @ManyToOne
    private Category category;

    @OneToOne(cascade = CascadeType.ALL)
    private Attachment attachment;

    private Double price;

    private Integer minimumTerm;

    private String executionInterval;

}
