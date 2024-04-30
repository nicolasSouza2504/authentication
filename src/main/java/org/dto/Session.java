package org.dto;

import lombok.Data;

@Data
public class Session {

    private String authToken;
    private String userName;

    public Session(String authToken, String userName) {
        this.authToken = authToken;
        this.userName = userName;
    }

}
