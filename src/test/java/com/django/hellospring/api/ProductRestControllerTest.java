package com.django.hellospring.api;


import static org.mockito.Mockito.*;


import com.django.hellospring.dtos.ProductDto;
import com.django.hellospring.repositories.ProductRepository;
import com.django.hellospring.services.IProductService;
import com.django.hellospring.services.ProductServiceImpl;

import java.util.ArrayList;
import java.util.List;





import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ProductRestController.class, ProductServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ProductRestControllerTest {

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private IProductService productService;
    @Autowired
    private ProductRestController productRestController;

    @Test
    void testGetProducts() throws Exception {
        ProductDto product = new ProductDto();
        product.setName("Tomate");
        product.setPrice(14.00);
        product.setQuantity(4);
        List<ProductDto> products = new ArrayList<>();
        products.add(product);
        when(productService.getProduct()).thenReturn(products);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/products");
        ResultActions actions = MockMvcBuilders.standaloneSetup(productRestController)
                .build().perform(requestBuilder);
        actions.andExpect(MockMvcResultMatchers.status().isOk());

    }






}

