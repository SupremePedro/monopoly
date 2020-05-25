package com.pedrofactory.monopoly.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class InventoryRequest {
    private Long ownerId;
    private List<String> items;
}
