package com.ibratclub.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Malikov Azizjon  *  25.01.2023  *  12:30   *  IbratClub
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Employee employee;
    private LocalDateTime dateTime = LocalDateTime.now();
    private String beforeUser;
    private boolean view = false;
    @ManyToOne
    private Company company;

}
