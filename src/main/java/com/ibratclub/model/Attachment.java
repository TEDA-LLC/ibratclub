package com.ibratclub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

/**
 * @author Malikov Azizjon  *  16.01.2023  *  17:57   *  IbratClub
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private Timestamp timestamp;
    @Column(nullable = false)
    @JsonIgnore
    private byte[] bytes;
    //    @Column(nullable = false)
    private String originalName, contentType;
    private Long size;

}
