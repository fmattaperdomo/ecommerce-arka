package com.fmattaperdomo.ecommerce_arka.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {
    private Long cartItemId;
    private CartDto cart;
    private ProductDto productDTO;
    private Integer quantity;
    private Double discount;
    private Double productPrice;
}

