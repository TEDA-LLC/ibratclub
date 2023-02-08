package com.ibratclub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name, link;
    @Column(columnDefinition = "text")
    private String description;

    private LocalDateTime dateTime = LocalDateTime.now();

    @ManyToOne
    @JsonIgnore
    private Company company;

}
