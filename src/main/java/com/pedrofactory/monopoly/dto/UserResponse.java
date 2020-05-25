package com.pedrofactory.monopoly.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;


@Data
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String email;
    private String nickname;
    private List<FriendResponse> friends;
}
