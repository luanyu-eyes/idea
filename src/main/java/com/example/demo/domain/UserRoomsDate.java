package com.example.demo.domain;

import java.sql.Date;
import java.sql.Time;

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
public class UserRoomsDate {
    private Integer timeId;
    private Integer roomsId;
    private Integer userId;
    private Time startTime;
    private Time endTime;
    private Date startDate;
    private Date endDate;
}
