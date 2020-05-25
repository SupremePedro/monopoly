package com.pedrofactory.monopoly.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class TradeOfferRequest {
    @NotNull
    private Long senderId;
    @NotNull
    private Long receiverId;
    private List<Long> senderItems;
    private List<Long> receiverItems;
}
