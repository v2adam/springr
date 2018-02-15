package com.springr.first.misc;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ChatMessage {

    private String author;

    private String content;

    private Date timeStamp;

    private String avatarColor;

}
