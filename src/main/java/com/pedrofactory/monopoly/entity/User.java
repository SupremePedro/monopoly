package com.pedrofactory.monopoly.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String nickname;

    @OneToOne(mappedBy = "owner",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Inventory inventory;

    @OneToMany
    private List<TradeOffer> tradeOffers;

    @OneToMany
    private List<User> friends;

    @ManyToMany(mappedBy = "users",fetch = FetchType.EAGER)
    private List<Role> roles;


}
