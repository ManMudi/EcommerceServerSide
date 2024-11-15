package com.iki.e_commerce.service.serviceImpl;

import com.iki.e_commerce.Dto.BrandDto;
import com.iki.e_commerce.entity.Brand;
import com.iki.e_commerce.repository.BrandRepository;
import com.iki.e_commerce.service.BrandService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BrandServiceImpl implements BrandService {

    private BrandRepository brandRepository;
    private ModelMapper modelMapper;

    @Override
    public BrandDto createBrand(BrandDto brandDto) {
        Brand brand=modelMapper.map(brandDto,Brand.class);
        Brand brand1=brandRepository.save(brand);
        return modelMapper.map(brand1,BrandDto.class);
    }

    @Override
    public BrandDto getBrandById(Long id) {
       Brand brand= brandRepository.findById(id).orElseThrow(() -> new RuntimeException("Brand of that ID Not Available !"));
        return modelMapper.map(brand,BrandDto.class);
    }

    @Override
    public List<BrandDto> getAllBrand() {
        List<Brand> brandList=brandRepository.findAll();
        return brandList.stream().map((brand)->modelMapper.map(brand,BrandDto.class))
                .collect(Collectors.toList());
    }
}
