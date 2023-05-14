package com.kyaa.mockito.data.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddBookRequest {
    private String name;
    private String datePublished;
    private BigDecimal price;
    private Integer quantity;
}
