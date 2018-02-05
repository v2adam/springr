package com.springr.first.dto.RandomUser;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RandomUserLogin {


    @JsonProperty("username")
    public String username;

    @JsonProperty("password")
    public String password;

    @JsonProperty("salt")
    public String salt;

    @JsonProperty("md5")
    public String md5;

    @JsonProperty("sha1")
    public String sha1;

    @JsonProperty("sha256")
    public String sha256;


    @Override
    public String toString() {
        return "RandomUserLogin{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", md5='" + md5 + '\'' +
                ", sha1='" + sha1 + '\'' +
                ", sha256='" + sha256 + '\'' +
                '}';
    }
}
