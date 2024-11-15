package com.iki.e_commerce.controller;

import com.iki.e_commerce.Dto.BrandDto;
import com.iki.e_commerce.service.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/brands")
@RestController
@AllArgsConstructor
public class BrandController {

    private BrandService brandService;

    @PostMapping
    ResponseEntity<BrandDto> createBrand(@RequestBody BrandDto brandDto){
        BrandDto brandDto1=brandService.createBrand(brandDto);
        return new ResponseEntity<>(brandDto1,HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    ResponseEntity<BrandDto> getBrandById(@PathVariable Long id){
        BrandDto brandDto=brandService.getBrandById(id);
        return new ResponseEntity<>(brandDto, HttpStatus.OK);
    }
    @GetMapping
    ResponseEntity<List<BrandDto>> getAllBrand(){
        List<BrandDto> brandDto=brandService.getAllBrand();
        return ResponseEntity.ok(brandDto);
    }

}
