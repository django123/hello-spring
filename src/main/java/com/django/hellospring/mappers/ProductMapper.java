package com.django.hellospring.mappers;

import com.django.hellospring.dtos.ProductDto;
import com.django.hellospring.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ProductMapper {
    ProductDto productToDto(Product product);
    Product productDtoMapToProduct(ProductDto productDto);

    List<ProductDto> mapListToDto(List<Product> productList);
    List<Product> mapProductList(List<ProductDto> productDtos);
}
