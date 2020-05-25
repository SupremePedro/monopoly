package com.pedrofactory.monopoly.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemResponse {
    private Long itemId;
    private String name;
    private String description;
    private Long inventoryId;
}
