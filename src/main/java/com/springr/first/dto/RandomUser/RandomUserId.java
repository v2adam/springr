package com.springr.first.dto.RandomUser;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RandomUserId {


    @JsonProperty("name")
    public String name;

    @JsonProperty("value")
    public String value;


    @Override
    public String toString() {
        return "RandomUserId{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
