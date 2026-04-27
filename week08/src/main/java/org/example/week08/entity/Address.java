package org.example.week08.entity;

import lombok.Data;
import java.io.Serializable;

@Data
public class Address implements Serializable{
    private String city;
    private String street;
    private String zipCode;
}