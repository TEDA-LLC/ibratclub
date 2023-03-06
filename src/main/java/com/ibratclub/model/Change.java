package com.ibratclub.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Change {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String columnName, tableName, oldData, newData;
    @ManyToOne
    private Employee employee;
    private LocalDateTime dateTime;
}
