package com.iki.e_commerce.response;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRes {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String pictureUrl;
    private String productBrand;
    private String productType;
}
