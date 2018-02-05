package com.springr.first.dto.RandomUser;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RandomUserName {


    @JsonProperty("title")
    public String title;

    @JsonProperty("first")
    public String first;

    @JsonProperty("last")
    public String last;


    @Override
    public String toString() {
        return "RandomUserName{" +
                "title='" + title + '\'' +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                '}';
    }
}
