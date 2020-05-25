package com.pedrofactory.monopoly.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.pedrofactory.monopoly.enums.OfferStatus;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TradeOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private OfferStatus offerStatus;

    @ManyToOne
    private User sender;

    @ManyToOne
    private User receiver;

    @OneToMany
    private List<Item> senderItems;

    @OneToMany
    private List<Item> receiverItems;

}
