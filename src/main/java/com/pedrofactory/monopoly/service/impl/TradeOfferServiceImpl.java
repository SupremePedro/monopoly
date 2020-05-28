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
import com.pedrofactory.monopoly.exception.TradeOfferNotFoundException;
import com.pedrofactory.monopoly.exception.UserNotFoundException;
import com.pedrofactory.monopoly.repository.ItemRepository;
import com.pedrofactory.monopoly.repository.TradeOfferRepository;
import com.pedrofactory.monopoly.repository.UserRepository;
import com.pedrofactory.monopoly.service.TradeOfferService;
import com.pedrofactory.monopoly.utils.mapstruct.TradeOfferMapper;
import com.pedrofactory.monopoly.utils.mapstruct.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public Page<TradeOfferResponse> getAllTradeOffers(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TradeOffer> tradeOfferPage = tradeOfferRepository.findAll(pageable);
        return new PageImpl<TradeOfferResponse>(tradeOfferPage
                .stream()
                .map(tradeOffer ->
                        TradeOfferMapper.INSTANCE.entityToResponse(tradeOffer))
                .collect(Collectors.toList()), pageable, tradeOfferPage.getTotalElements());
    }

    @Override
    public TradeOfferResponse getTradeOfferById(Long id) throws TradeOfferNotFoundException {
        TradeOffer tradeOffer = tradeOfferRepository.findById(id).orElseThrow(()->new TradeOfferNotFoundException(id.toString()));
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
