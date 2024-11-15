package com.iki.e_commerce.service;

import com.iki.e_commerce.Dto.BrandDto;

import java.util.List;

public interface BrandService {
    BrandDto createBrand(BrandDto brandDto);
    BrandDto getBrandById(Long id);
    List<BrandDto> getAllBrand();
}
