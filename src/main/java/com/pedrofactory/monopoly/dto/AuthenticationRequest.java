package com.pedrofactory.monopoly.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthenticationRequest {

    @Size(min = 2, max = 255)
    private String username;

    @Size(min = 6,max = 255)
    private String password;

}
