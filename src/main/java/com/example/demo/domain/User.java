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
public class User {

    private int id;
    private String userName;
    private String passWord;
    private String realName;
    private String phone;
    private String email;
}
