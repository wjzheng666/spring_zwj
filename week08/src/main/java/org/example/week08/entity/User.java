package org.example.week08.entity;


import lombok.Data;

import java.io.Serializable;


@Data
public class User implements Serializable {
    private String name;
    private Integer age;
    private String email;
    private Address address;
}