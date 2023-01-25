package com.ibratclub.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Company company;
    @OneToMany
    @ToString.Exclude
    private List<Employee> authorizedEmployees;
    @OneToMany(mappedBy = "bot")
    @ToString.Exclude
    private List<User> userList;
    @OneToMany(mappedBy = "bot")
    @ToString.Exclude
    private List<Category> categories;

    @OneToMany(mappedBy = "bot")
    @ToString.Exclude
    private List<Vacancy> vacancies;
    @Column(nullable = false)
    private boolean active = true;

    @ManyToOne
    private Attachment logo;
}
