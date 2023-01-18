package com.ibratclub.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ibratclub.model.enums.RegisteredType;
import com.ibratclub.model.enums.RequestType;
import javax.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author Malikov Azizjon  *  15.01.2023  *  18:18   *  IbratClub
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String aboutProduct, category;
    private boolean view = false;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime = LocalDateTime.now();
    @ManyToOne
    private Product product;
    @Enumerated(EnumType.STRING)
    private RequestType requestStatusType;
    @ManyToOne
    private User user;
    @Enumerated(EnumType.STRING)
    private RegisteredType registeredType;
    @ManyToOne
    private Employee employee;

}
