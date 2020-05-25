package com.pedrofactory.monopoly.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class InventoryResponse {
     private Long ownerId;
     private List<ItemResponse> items;
}
