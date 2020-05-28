package com.pedrofactory.monopoly.controller;

import com.pedrofactory.monopoly.dto.TradeOfferRequest;
import com.pedrofactory.monopoly.dto.TradeOfferResponse;
import com.pedrofactory.monopoly.dto.UpdateTradeOffer;
import com.pedrofactory.monopoly.entity.TradeOffer;
import com.pedrofactory.monopoly.exception.TradeOfferNotFoundException;
import com.pedrofactory.monopoly.exception.UserNotFoundException;
import com.pedrofactory.monopoly.service.TradeOfferService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tradeoffers")
@AllArgsConstructor
public class TradeOfferController {

    final TradeOfferService tradeOfferService;

    @GetMapping
    public ResponseEntity<Page<TradeOfferResponse>> getAllTradeOffers(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "6") Integer size){
        return new ResponseEntity<>(tradeOfferService.getAllTradeOffers(page,size),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TradeOfferResponse> createTradeOffer(@RequestBody TradeOfferRequest tradeOfferRequest){
        return new ResponseEntity<>(tradeOfferService.createTradeOffer(tradeOfferRequest), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{tradeOfferId}")
    public ResponseEntity<TradeOfferResponse> getById(@PathVariable Long tradeOfferId) throws TradeOfferNotFoundException {
        return new ResponseEntity<>(tradeOfferService.getTradeOfferById(tradeOfferId),HttpStatus.OK);
    }

    @PutMapping(value = "/{tradeOfferId}")
    public ResponseEntity<TradeOfferResponse> updateTradeOffer(@PathVariable Long tradeOfferId,@RequestBody UpdateTradeOffer tradeOfferRequest) throws UserNotFoundException {
        return new ResponseEntity<>(tradeOfferService.updateTradeOffer(tradeOfferId,tradeOfferRequest), HttpStatus.OK);
    }
}
