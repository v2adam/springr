package com.springr.first.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.springr.first.dto.RandomUser.*;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "random_users")
public class RandomUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long mainKey;


    public String gender;

    public String email;

    public String dob;

    public String registered;

    public String phone;

    public String cell;

    public String nat;



    // name
    public String title;

    public String first;

    public String last;


    // location
    public String street;

    public String city;

    public String state;

    public String postcode;



    // login


    public String username;

    public String password;

    public String salt;

    public String md5;

    public String sha1;

    public String sha256;


    // id

    public String name;

    public String value;


    // picture

    public String large;

    public String medium;

    public String thumbnail;


    public RandomUser() {
    }


}