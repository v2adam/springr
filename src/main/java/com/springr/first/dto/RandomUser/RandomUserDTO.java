package com.springr.first.dto.RandomUser;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RandomUserDTO {

    public Long idField;

    @JsonProperty("gender")
    public String gender;

    @JsonProperty("email")
    public String email;

    @JsonProperty("dob")
    public String dob;

    @JsonProperty("registered")
    public String registered;

    @JsonProperty("phone")
    public String phone;

    @JsonProperty("cell")
    public String cell;

    @JsonProperty("nat")
    public String nat;

    @JsonProperty("name")
    public RandomUserName name;

    @JsonProperty("location")
    public RandomUserLocation location;

    @JsonProperty("login")
    public RandomUserLogin login;

    @JsonProperty("id")
    public RandomUserId id;

    @JsonProperty("picture")
    public RandomUserPicture picture;


    @Override
    public String toString() {
        return "RandomUserDTO{" +
                "idField=" + idField +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", dob='" + dob + '\'' +
                ", registered='" + registered + '\'' +
                ", phone='" + phone + '\'' +
                ", cell='" + cell + '\'' +
                ", nat='" + nat + '\'' +
                ", name=" + name +
                ", location=" + location +
                ", login=" + login +
                ", id=" + id +
                ", picture=" + picture +
                '}';
    }
}
