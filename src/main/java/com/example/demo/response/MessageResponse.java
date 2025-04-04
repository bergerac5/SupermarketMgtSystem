package com.example.demo.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageResponse {
    private String message;

    public MessageResponse() {
    }

    public MessageResponse(String message) {
        this.message = message;
    }

}
