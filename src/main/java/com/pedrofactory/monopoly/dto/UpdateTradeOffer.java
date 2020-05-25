package com.pedrofactory.monopoly.dto;

import com.pedrofactory.monopoly.enums.OfferStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTradeOffer {
    private Long id;
    private OfferStatus offerStatus;
}
