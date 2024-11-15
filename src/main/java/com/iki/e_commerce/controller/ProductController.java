package com.iki.e_commerce.controller;

import com.iki.e_commerce.Dto.ProductDto;
import com.iki.e_commerce.response.ProductRes;
import com.iki.e_commerce.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("products")
@RestController
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @PostMapping
    ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){
        ProductDto productDto1=productService.createProduct(productDto);
        return new ResponseEntity<>(productDto1, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    ResponseEntity<ProductRes> getProduct(@PathVariable Long id){
       ProductRes productRes=productService.getProductById(id) ;
       return ResponseEntity.ok(productRes);
    }

    @GetMapping
    ResponseEntity<Page<ProductRes>> getAllProduct(
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "size",defaultValue = "10") int size,
            @RequestParam(name = "keyword",required = false) String keyword,
            @RequestParam(name = "brandId",required = false) Long brandId,
            @RequestParam(name = "typeId",required = false) Long typeId,
            @RequestParam(name = "sort",defaultValue = "name") String sort,
            @RequestParam(name = "order",defaultValue = "asc") String order
    ){
        Sort.Direction direction=order.equalsIgnoreCase("desc") ? Sort.Direction.DESC:Sort.Direction.ASC;
        Sort sorting=Sort.by(direction,sort);
        Pageable pageable= PageRequest.of(page,size,sorting);
        Page<ProductRes> productRes=productService.getAllProduct(pageable,brandId,typeId,keyword);
        return new ResponseEntity<>(productRes,HttpStatus.OK);
    }
}
