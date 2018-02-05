package com.springr.first.dto.RandomUser;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RandomUserPicture {

    @JsonProperty("large")
    public String large;

    @JsonProperty("medium")
    public String medium;

    @JsonProperty("thumbnail")
    public String thumbnail;

    @Override
    public String toString() {
        return "RandomUserPicture{" +
                "large='" + large + '\'' +
                ", medium='" + medium + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                '}';
    }
}
