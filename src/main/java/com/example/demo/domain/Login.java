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
public class Login {
    private String token;
}
