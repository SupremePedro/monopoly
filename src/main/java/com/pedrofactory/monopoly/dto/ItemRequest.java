package com.pedrofactory.monopoly.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ItemRequest {
    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private Long inventoryId;
}
