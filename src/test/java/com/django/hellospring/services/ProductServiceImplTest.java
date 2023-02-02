package com.django.hellospring.services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.django.hellospring.dtos.ProductDto;
import com.django.hellospring.entities.Product;
import com.django.hellospring.mappers.ProductMapper;
import com.django.hellospring.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.util.UUID;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProductServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ProductServiceImplTest {
    @MockBean
    private ProductMapper productMapper;

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    /**
     * Method under test: {@link ProductServiceImpl#getProduct()}
     */
    @Test
    void testGetProduct() {
        when(productRepository.findAll()).thenReturn(new ArrayList<>());
        ArrayList<ProductDto> productDtoList = new ArrayList<>();
        when(productMapper.mapListToDto((List<Product>) any())).thenReturn(productDtoList);
        List<ProductDto> actualProduct = productServiceImpl.getProduct();
        assertSame(productDtoList, actualProduct);
        assertTrue(actualProduct.isEmpty());
        verify(productRepository).findAll();
        verify(productMapper).mapListToDto((List<Product>) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#getProduct(UUID)}
     */
    @Test
    void testGetProduct2() {
        when(productRepository.findById((UUID) any())).thenReturn(Optional.of(new Product()));
        ProductDto productDto = new ProductDto();
        when(productMapper.productToDto((Product) any())).thenReturn(productDto);
        assertSame(productDto, productServiceImpl.getProduct(UUID.randomUUID()));
        verify(productRepository).findById((UUID) any());
        verify(productMapper).productToDto((Product) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#getProduct(UUID)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetProduct3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:143)
        //       at com.django.hellospring.services.ProductServiceImpl.getProduct(ProductServiceImpl.java:27)
        //   See https://diff.blue/R013 to resolve this issue.

        when(productRepository.findById((UUID) any())).thenReturn(Optional.empty());
        when(productMapper.productToDto((Product) any())).thenReturn(new ProductDto());
        productServiceImpl.getProduct(UUID.randomUUID());
    }

    /**
     * Method under test: {@link ProductServiceImpl#saveProduct(ProductDto)}
     */
    @Test
    void testSaveProduct() {
        when(productRepository.save((Product) any())).thenReturn(new Product());
        ProductDto productDto = new ProductDto();
        when(productMapper.productToDto((Product) any())).thenReturn(productDto);
        when(productMapper.productDtoMapToProduct((ProductDto) any())).thenReturn(new Product());
        assertSame(productDto, productServiceImpl.saveProduct(new ProductDto()));
        verify(productRepository).save((Product) any());
        verify(productMapper).productToDto((Product) any());
        verify(productMapper).productDtoMapToProduct((ProductDto) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#deleteProduct(UUID)}
     */
    @Test
    void testDeleteProduct() {
        doNothing().when(productRepository).deleteById((UUID) any());
        productServiceImpl.deleteProduct(UUID.randomUUID());
        verify(productRepository).deleteById((UUID) any());
    }
}

