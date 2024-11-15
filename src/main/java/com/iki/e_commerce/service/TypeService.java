package com.iki.e_commerce.service;

import com.iki.e_commerce.Dto.TypeDto;

import java.util.List;

public interface TypeService {
    TypeDto createType(TypeDto typeDto);
    TypeDto getTypeId(Long id);
    List<TypeDto> getAllType();

}
