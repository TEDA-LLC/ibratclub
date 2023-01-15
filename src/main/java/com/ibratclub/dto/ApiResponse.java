package com.ibratclub.dto;

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
    private int status;
    private T data;

}
