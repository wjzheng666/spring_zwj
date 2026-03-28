package com.example.week04.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Data
@Component
@ConfigurationProperties(prefix = "team")
public class Team {
    private String leader;
    private Integer age;
    private String email;
    private String phone;
    private LocalDate createTime;
}