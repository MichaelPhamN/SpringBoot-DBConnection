package com.example.jdbc.model;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Account implements Serializable {
    private Integer id;
    private String email;
    private String password;
}
