package com.acs560.freelancing.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageRequest {
    String text;
    String media;
//    Long senderId;
//    Long receiverId;

    Long bidId;
    Long jobId;


}
