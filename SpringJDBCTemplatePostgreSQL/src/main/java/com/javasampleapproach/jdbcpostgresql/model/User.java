package com.javasampleapproach.jdbcpostgresql.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class User {
 
    private Long id;

    private String name;


    private String email;

    private String imageUrl;


    private Boolean emailVerified = false;

    @JsonIgnore
    private String password;

    private AuthProvider provider;

    private String providerId;

    // Getters and Setters (Omitted for brevity)
}