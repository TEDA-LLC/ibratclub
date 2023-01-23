package com.ibratclub.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Malikov Azizjon  *  23.01.2023  *  23:20   *  IbratClub
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String organizer;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime dateTime;

    private String contact;

    @ManyToOne
    private Address eventAddress;

    private boolean active;

}
