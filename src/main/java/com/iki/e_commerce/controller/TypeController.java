package com.iki.e_commerce.controller;

import com.iki.e_commerce.Dto.TypeDto;
import com.iki.e_commerce.service.TypeService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("types")
@RestController
@AllArgsConstructor
public class TypeController {

    private TypeService typeService;

    @PostMapping
    ResponseEntity<TypeDto> createType(@RequestBody TypeDto typeDto){
        TypeDto typeDto1=typeService.createType(typeDto);
        return new ResponseEntity<>(typeDto1, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    ResponseEntity<TypeDto> getTypeById(@PathVariable Long id){
        TypeDto typeDto=typeService.getTypeId(id);
        return ResponseEntity.ok(typeDto);
    }

    @GetMapping
    ResponseEntity<List<TypeDto>> getAllType(){
        List<TypeDto> typeDto=typeService.getAllType();
        return ResponseEntity.ok(typeDto);
    }
}
