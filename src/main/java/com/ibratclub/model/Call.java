package com.ibratclub.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Malikov Azizjon  *  16.01.2023  *  23:34   *  IbratClub
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Call {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User client;

    @ManyToOne
    private Employee employee;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime = LocalDateTime.now();

    private LocalDate nextConnectionDate;

    private String description;

    @OneToMany
    @ToString.Exclude
    private List<ReviewCategory> ReviewCategory;

    private boolean success;

}
