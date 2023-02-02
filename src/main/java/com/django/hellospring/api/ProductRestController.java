package com.django.hellospring.api;

import com.django.hellospring.dtos.ProductDto;
import com.django.hellospring.services.IProductService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "api/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductRestController {

    private final IProductService productService;

    public ProductRestController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDto> getProducts(){
        return productService.getProduct();
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable UUID id){
        return productService.getProduct(id);
    }

    @PostMapping
    public ProductDto save(@Valid @RequestBody ProductDto productDto){
        return productService.saveProduct(productDto);
    }

    @PutMapping("/update/{id}")
    public ProductDto update(@RequestBody ProductDto productDto,@PathVariable UUID id){
        productDto.setId(id);
        return productService.saveProduct(productDto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable UUID id){
        productService.deleteProduct(id);
    }
}
