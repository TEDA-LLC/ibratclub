package com.ibratclub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @ToString.Exclude
    private Bot bot;

    @ManyToOne
    @ToString.Exclude
    private Site site;
    @OneToMany(mappedBy = "department")
    @ToString.Exclude
    private List<User> clientList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private Company company;
    @OneToMany(mappedBy = "department")
    @ToString.Exclude
    private List<Category> categories;

    @OneToMany(mappedBy = "department")
    @ToString.Exclude
    private List<Vacancy> vacancies;
    @Column(nullable = true)
    private boolean active = true;
}
