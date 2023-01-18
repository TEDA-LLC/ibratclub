package com.ibratclub.model;

import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

/**
 * @author Malikov Azizjon  *  15.01.2023  *  21:57   *  IbratClub
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @UpdateTimestamp
    private LocalDate changeDate;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Region region;

    public District(String name, LocalDate changeDate, Region region) {
        this.name = name;
        this.changeDate = changeDate;
        this.region = region;
    }

    public District(String name, Region region) {
        this.name = name;
        this.region = region;
    }

}
