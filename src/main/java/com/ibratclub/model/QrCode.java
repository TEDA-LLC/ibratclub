//package com.ibratclub.model;
//
//import lombok.*;
//import org.hibernate.annotations.GenericGenerator;
//import org.hibernate.annotations.Parameter;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//import java.util.UUID;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@ToString
//@Entity
//@Builder
//public class QrCode {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    private User user;
//
//    @ManyToOne
//    private Product product;
//
//    private LocalDateTime registeredTime = LocalDateTime.now();
//    private LocalDateTime arrivalTime;
//    @ManyToOne
//    private Company company;
//
//    @ManyToOne
//    @JoinColumn(unique = true)
//    private Request request;
//
//}
