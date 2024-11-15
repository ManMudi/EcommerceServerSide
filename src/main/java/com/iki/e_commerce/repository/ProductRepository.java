package com.iki.e_commerce.repository;

import com.iki.e_commerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Page<Product> findAll(Specification<Product> specification, Pageable pageable);
    Specification<Product> searchByNameContaining(String keyword);
    Specification<Product> findByBrandId(Long brandId);
    Specification<Product> findByTypeId(Long typeId);
    Specification<Product> findByBrandIdAndTypeId(Long brandId,Long typeId);
}
