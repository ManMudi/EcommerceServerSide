package com.iki.e_commerce.service.serviceImpl;

import com.iki.e_commerce.Dto.ProductDto;
import com.iki.e_commerce.entity.Brand;
import com.iki.e_commerce.entity.Product;
import com.iki.e_commerce.entity.Type;
import com.iki.e_commerce.repository.BrandRepository;
import com.iki.e_commerce.repository.ProductRepository;
import com.iki.e_commerce.repository.TypeRepository;
import com.iki.e_commerce.response.ProductRes;
import com.iki.e_commerce.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private BrandRepository brandRepository;
    private TypeRepository typeRepository;
    private ProductRepository productRepository;
    private ModelMapper modelMapper;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product=modelMapper.map(productDto,Product.class);
        Brand brand=brandRepository.findById(productDto.getBrandId()).orElseThrow(()-> new RuntimeException("Brand of that ID Not Available !"));
        Type type=typeRepository.findById(productDto.getTypeId()).orElseThrow(()-> new RuntimeException("Type of that ID Not Available !"));
        product.setType(type);
        product.setBrand(brand);
        Product product1=productRepository.save(product);
        return modelMapper.map(product1,ProductDto.class);
    }

    @Override
    public ProductRes getProductById(Long id) {
        Product product=productRepository.findById(id).orElseThrow(()->new RuntimeException("Product of that ID Not Available !"));
        return convertProductToResponse(product);

    }

    @Override
    public Page<ProductRes> getAllProduct(Pageable pageable,Long brandId,Long typeId,String keyword) {
        Specification<Product> spec=Specification.where(null);{
            if(brandId!=null){
                spec=spec.and(((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("brand").get("id"),brandId)));
            }
            if(typeId!=null){
                spec=spec.and(((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("type").get("id"),typeId)));
            }
            if(keyword!=null && !keyword.isEmpty()){
                spec=spec.and(((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"),"%"+keyword+"%")));
            }
        }
        return productRepository.findAll(spec,pageable).map(this::convertProductToResponse);

    }

    private ProductRes convertProductToResponse(Product product) {
        return ProductRes.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .pictureUrl(product.getPictureUrl())
                .productBrand(product.getBrand().getName())
                .productType(product.getType().getName())
                .build();

    }

}
