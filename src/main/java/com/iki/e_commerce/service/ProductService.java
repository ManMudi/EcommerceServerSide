package com.iki.e_commerce.service;

import com.iki.e_commerce.Dto.ProductDto;
import com.iki.e_commerce.response.ProductRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);
    ProductRes getProductById(Long id);
    Page<ProductRes> getAllProduct(Pageable pageable ,Long brandId,Long typeId,String keyword);
}
