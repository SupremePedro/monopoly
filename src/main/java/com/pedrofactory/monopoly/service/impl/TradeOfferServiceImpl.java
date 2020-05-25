package com.pedrofactory.monopoly.service.impl;

import com.pedrofactory.monopoly.dto.TradeOfferRequest;
import com.pedrofactory.monopoly.dto.TradeOfferResponse;
import com.pedrofactory.monopoly.dto.UpdateTradeOffer;
import com.pedrofactory.monopoly.dto.UserResponse;
import com.pedrofactory.monopoly.entity.Inventory;
import com.pedrofactory.monopoly.entity.Item;
import com.pedrofactory.monopoly.enums.OfferStatus;
import com.pedrofactory.monopoly.entity.TradeOffer;
import com.pedrofactory.monopoly.entity.User;
import com.pedrofactory.monopoly.exception.UserNotFoundException;
import com.pedrofactory.monopoly.repository.ItemRepository;
import com.pedrofactory.monopoly.repository.TradeOfferRepository;
import com.pedrofactory.monopoly.repository.UserRepository;
import com.pedrofactory.monopoly.service.TradeOfferService;
import com.pedrofactory.monopoly.utils.mapstruct.TradeOfferMapper;
import com.pedrofactory.monopoly.utils.mapstruct.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TradeOfferServiceImpl implements TradeOfferService {

    final TradeOfferRepository tradeOfferRepository;
    final UserRepository userRepository;
    final ItemRepository itemRepository;

    @Override
    public TradeOfferResponse createTradeOffer(TradeOfferRequest tradeOfferRequest) {
        TradeOffer newTradeOffer = new TradeOffer();
        newTradeOffer.setOfferStatus(OfferStatus.ACTIVE);
        User sender = userRepository.getOne(tradeOfferRequest.getSenderId());
        User receiver = userRepository.getOne(tradeOfferRequest.getReceiverId());
        newTradeOffer.setSender(sender);
        newTradeOffer.setReceiver(receiver);
        List<Item> senderItems = new ArrayList<>();
        tradeOfferRequest.getSenderItems().forEach(itemId -> {
            Item item = itemRepository.getOne(itemId);
            senderItems.add(item);
        });
        newTradeOffer.setSenderItems(senderItems);
        List<Item> receiverItems = new ArrayList<>();
        tradeOfferRequest.getReceiverItems().forEach(itemId -> {
            Item item = itemRepository.getOne(itemId);
            receiverItems.add(item);
        });
        newTradeOffer.setReceiverItems(receiverItems);
        TradeOfferResponse tradeOfferResponse = TradeOfferMapper.INSTANCE.entityToResponse(tradeOfferRepository.save(newTradeOffer));
        return tradeOfferResponse;
    }

    @Override
    public List<TradeOfferResponse> getAllTradeOffers(Integer page, Integer size) {
        List<TradeOffer> tradeOffers = tradeOfferRepository.findAll(PageRequest.of(page, size)).getContent();
        List<TradeOfferResponse> tradeOfferResponses = new ArrayList<>();
        for (TradeOffer tradeOffer : tradeOffers
        ) {
            tradeOfferResponses.add(TradeOfferMapper.INSTANCE.entityToResponse(tradeOffer));
        }
        return tradeOfferResponses;
    }

    @Override
    public TradeOfferResponse getTradeOfferById(Long id) {
        TradeOffer tradeOffer = tradeOfferRepository.getOne(id);
        TradeOfferResponse tradeOfferResponse = TradeOfferMapper.INSTANCE.entityToResponse(tradeOffer);
        return tradeOfferResponse;
    }

    @Override
    public TradeOfferResponse updateTradeOffer(Long id, UpdateTradeOffer tradeOfferUpdateRequest) throws UserNotFoundException {
        TradeOffer tradeOffer = tradeOfferRepository.getOne(id);
        Inventory senderInventory = tradeOffer.getSender().getInventory();
        Inventory receiverInventory = tradeOffer.getReceiver().getInventory();
        if(tradeOfferUpdateRequest.getOfferStatus().equals(OfferStatus.ACCEPTED)){
            tradeOffer.getReceiverItems().forEach(item -> {
               item.setInventory(senderInventory);
               itemRepository.save(item);
            });
            tradeOffer.getSenderItems().forEach(item -> {
                item.setInventory(receiverInventory);
                itemRepository.save(item);
            });
            tradeOffer.setOfferStatus(OfferStatus.ACCEPTED);
        }
        TradeOfferResponse tradeOfferResponse = TradeOfferMapper.INSTANCE.entityToResponse(tradeOfferRepository.save(tradeOffer));
        return tradeOfferResponse;
    }

    @Override
    public void deleteTradeOffer(Long tradeOfferId) {
        tradeOfferRepository.deleteById(tradeOfferId);
    }
}
