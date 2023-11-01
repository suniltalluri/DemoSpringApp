package com.Security.Security.payload;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthResponse {
    private String token;
    private String tokenType ="Bearer";

    public JwtAuthResponse(String token) {
        super();
        this.token = token;
    }
}
