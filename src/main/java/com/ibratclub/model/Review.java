package com.ibratclub.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author Malikov Azizjon  *  16.01.2023  *  17:28   *  IbratClub
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Review", indexes = {
        @Index(name = "idx_review_active", columnList = "confirmation")
})
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @ManyToOne
    private User user;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime = LocalDateTime.now();
    private boolean confirmation = false;

}
