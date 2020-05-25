package com.pedrofactory.monopoly.dto;

import com.pedrofactory.monopoly.entity.User;
import com.pedrofactory.monopoly.enums.OfferStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TradeOfferResponse {
    private Long id;
    private OfferStatus offerStatus;
    private String senderId;
    private String receiverId;
    private List<ItemResponse> senderItems;
    private List<ItemResponse> receiverItems;
}
