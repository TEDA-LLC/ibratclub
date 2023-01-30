package com.ibratclub.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

/**
 * @author Malikov Azizjon  *  15.01.2023  *  18:03   *  IbratClub
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ApiResponse<T> {

    private String message;
    private boolean success;
    @JsonIgnore
    private int status;
    private T data;

}
