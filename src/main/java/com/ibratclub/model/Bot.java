package com.ibratclub.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * @author Malikov Azizjon  *  16.01.2023  *  23:33   *  IbratClub
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Bot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String token, username;
    @ManyToOne
    private Company company;
    @OneToMany(mappedBy = "bot")
    @ToString.Exclude
    private List<User> userList;

}
