package com.pedrofactory.monopoly.dto;

import com.pedrofactory.monopoly.annotation.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@Data
public class UserRegisterRequest {
    @Email
    private String email;
    @Size(min = 3,max = 20)
    private String nickname;
    @ValidPassword
    private String password;
    private List<String> friends;
}
