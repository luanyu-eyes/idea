package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Rooms {
    private Integer roomsId;
    private String roomsName;
    private Integer roomsFloor;
    private Integer roomsLayer;
    private Integer roomsNum;
    private String roomsType;
    private String present;
}
