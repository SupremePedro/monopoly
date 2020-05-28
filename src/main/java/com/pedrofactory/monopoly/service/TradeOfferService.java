package com.pedrofactory.monopoly.service;

import com.pedrofactory.monopoly.dto.*;
import com.pedrofactory.monopoly.entity.TradeOffer;
import com.pedrofactory.monopoly.exception.TradeOfferNotFoundException;
import com.pedrofactory.monopoly.exception.UserNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TradeOfferService {
    TradeOfferResponse createTradeOffer(TradeOfferRequest tradeOfferRequest);
    Page<TradeOfferResponse> getAllTradeOffers(Integer page, Integer size);
    TradeOfferResponse getTradeOfferById(Long id) throws TradeOfferNotFoundException;
    TradeOfferResponse updateTradeOffer(Long id, UpdateTradeOffer tradeOfferUpdateRequest) throws UserNotFoundException;
    void deleteTradeOffer(Long tradeOfferId);
}
