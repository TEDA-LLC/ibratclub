package com.ibratclub.model;

import com.ibratclub.model.enums.PaymentType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * @author Malikov Azizjon  *  16.01.2023  *  23:31   *  IbratClub
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Payment { //TODO tolovlar

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer paymentNumber;
    private LocalDate paymentDate;
    private Double price;
    private boolean active;
    @ManyToOne
    private Order order;
    @ManyToOne
    private Employee receiver;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

}
