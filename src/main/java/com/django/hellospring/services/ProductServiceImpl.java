package com.django.hellospring.services;

import com.django.hellospring.dtos.ProductDto;
import com.django.hellospring.entities.Product;
import com.django.hellospring.mappers.ProductMapper;
import com.django.hellospring.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ProductServiceImpl implements IProductService{

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDto getProduct(UUID id) {
        return productMapper.productToDto(productRepository.findById(id).get());
    }

    @Override
    public ProductDto saveProduct(ProductDto productDto) {
        Product product = productMapper.productDtoMapToProduct(productDto);
        Product saveProduct = productRepository.save(product);
        return  productMapper.productToDto(saveProduct);
    }


    @Override
    public List<ProductDto> getProduct() {

        return productMapper.mapListToDto(productRepository.findAll());
    }


    @Override
    public void deleteProduct(UUID id) {
       productRepository.deleteById(id);
    }
}
