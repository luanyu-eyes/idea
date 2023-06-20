package com.example.demo.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Test<T> {
    private Integer code;
    private String msg;
    private Integer count;
    private List<T> data;
}
