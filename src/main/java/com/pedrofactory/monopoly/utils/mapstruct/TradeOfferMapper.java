package com.pedrofactory.monopoly.utils.mapstruct;

import com.pedrofactory.monopoly.dto.ItemResponse;
import com.pedrofactory.monopoly.dto.TradeOfferResponse;
import com.pedrofactory.monopoly.entity.Item;
import com.pedrofactory.monopoly.entity.TradeOffer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TradeOfferMapper {
    TradeOfferMapper INSTANCE = Mappers.getMapper(TradeOfferMapper.class);

    @Mapping(source = "sender.id", target = "senderId")
    @Mapping(source = "receiver.id", target = "receiverId")
    TradeOfferResponse entityToResponse(TradeOffer tradeOffer);

    @Mapping(source = "id",target = "itemId")
    @Mapping(source = "inventory.id", target = "inventoryId")
    ItemResponse itemToResponse(Item item);


}
