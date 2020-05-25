package com.pedrofactory.monopoly.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@Data
public class UserRequest {

    @Email
    private String email;
    @Size(max = 20,min = 3)
    private String nickname;

    private List<String> friends;

}
