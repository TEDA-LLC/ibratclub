package com.ibratclub.model;

import com.ibratclub.model.enums.MessageType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Builder
public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @ManyToOne
    private Bot bot;
    @ManyToOne
    private User user;
    @ManyToOne
    private Request request;
    @Enumerated(EnumType.STRING)
    private MessageType messageType;
    @Builder.Default
    private LocalDateTime sendTime = LocalDateTime.now();
    private LocalDateTime acceptTime;
    private boolean accept;
}
