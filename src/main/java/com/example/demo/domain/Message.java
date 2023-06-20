package com.example.demo.domain;

import java.sql.Date;

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
public class Message {
    private Integer messageId;
    private String content;
    private Date messageTime;
    private Integer userId;
    private String messageType;
}
