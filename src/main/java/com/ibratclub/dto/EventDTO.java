package com.ibratclub.dto;

import com.ibratclub.model.Address;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author Malikov Azizjon  *  23.01.2023  *  23:30   *  IbratClub
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EventDTO {

    private String name, description, organizer, contact;

    private LocalDateTime dateTime;

    private Address eventAddress;

    private boolean active = true;

}
