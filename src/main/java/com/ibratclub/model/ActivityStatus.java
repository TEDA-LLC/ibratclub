package com.ibratclub.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ibratclub.model.enums.ActiveTypes;
import javax.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author Malikov Azizjon  *  15.01.2023  *  18:46   *  IbratClub
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class ActivityStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private ActiveTypes firstCase;
    private ActiveTypes secondCase;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime period = LocalDateTime.now();
    @ManyToOne
    private User client;

}
