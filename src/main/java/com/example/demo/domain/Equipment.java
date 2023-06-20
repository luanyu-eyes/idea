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
public class Equipment {
    private Integer roomsId;
    private String monitor;
    private String WIFI;
    private String megaphone;
    private String screen;
}