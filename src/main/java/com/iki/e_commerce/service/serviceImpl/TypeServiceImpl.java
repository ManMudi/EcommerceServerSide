package com.iki.e_commerce.service.serviceImpl;

import com.iki.e_commerce.Dto.BrandDto;
import com.iki.e_commerce.Dto.TypeDto;
import com.iki.e_commerce.entity.Type;
import com.iki.e_commerce.repository.TypeRepository;
import com.iki.e_commerce.service.TypeService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.apache.commons.logging.Log;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TypeServiceImpl implements TypeService {

    private TypeRepository typeRepository;
    private ModelMapper modelMapper;

    @Override
    public TypeDto createType(TypeDto typeDto) {
        Type type=modelMapper.map(typeDto,Type.class);
        Type type1=typeRepository.save(type);
        return modelMapper.map(type1,TypeDto.class);
    }

    @Override
    public TypeDto getTypeId(Long id) {
        Type type =typeRepository.findById(id).orElseThrow(() -> new RuntimeException("Product Of that Type is Not Available !"));
        return modelMapper.map(type,TypeDto.class);
    }

    @Override
    public List<TypeDto> getAllType() {
        List<Type> types=typeRepository.findAll();
        return types.stream().map((type)->modelMapper.map(type, TypeDto.class))
                .collect(Collectors.toList());
    }
}
