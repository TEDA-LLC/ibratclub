package com.ibratclub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.*;

import java.util.List;

/**
 * @author Malikov Azizjon  *  16.01.2023  *  18:11   *  IbratClub
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Avatar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private List<Attachment> photos;
    @OneToOne
    private User user;
    private String personal;
    private String  aboutWork;
    private String hobby;

}
