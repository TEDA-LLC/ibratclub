package com.ibratclub.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author Malikov Azizjon  *  16.01.2023  *  18:02   *  IbratClub
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class SiteHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ipAddress, about;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime = LocalDateTime.now();

    @ManyToOne
    private User user;

}
