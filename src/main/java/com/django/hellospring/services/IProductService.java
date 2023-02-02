package com.django.hellospring.services;

import com.django.hellospring.dtos.ProductDto;

import java.util.List;
import java.util.UUID;

public interface IProductService {

    ProductDto getProduct(UUID id);
    ProductDto saveProduct(ProductDto productDto);
    List<ProductDto> getProduct();

    void deleteProduct(UUID id);
}
