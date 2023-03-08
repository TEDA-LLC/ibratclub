package com.ibratclub.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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
    private String nameUz, nameRu, nameEn, descriptionUz, descriptionRu, descriptionEn, textUz, textRu, textEn, agreeTextUz, agreeTextEn, agreeTextRu;

    @ManyToOne
    private Category category;

    @OneToOne(cascade = CascadeType.ALL)
    private Attachment attachment;

    private Double price;

    private Integer minimumTerm;
//    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime fromDate, toDate;
    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;

    private String executionInterval;
    @Builder.Default
    @Column(nullable = true)
    private boolean active = true;

    @OneToMany
    @ToString.Exclude
    private List<User> speakers;

}
