package com.example.demo.domain;

import java.util.List;
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
public class ConferenceRooms {
    private String roomid;
    private String roomname;
    private String imgpath;
    private String roomtype;
    private int maxnum;

    private List<Equipment> equipment;
}