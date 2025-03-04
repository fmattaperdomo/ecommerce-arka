package com.fmattaperdomo.ecommerce_arka.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fmattaperdomo.ecommerce_arka.Data;
import com.fmattaperdomo.ecommerce_arka.configurations.AppConstants;
import com.fmattaperdomo.ecommerce_arka.dtos.ProductDto;
import com.fmattaperdomo.ecommerce_arka.dtos.ProductResponse;
import com.fmattaperdomo.ecommerce_arka.entities.Product;
import com.fmattaperdomo.ecommerce_arka.security.jwt.JwtUtils;
import com.fmattaperdomo.ecommerce_arka.security.services.UserDetailsServiceImpl;
import com.fmattaperdomo.ecommerce_arka.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest(controllers = ProductController.class,
        excludeAutoConfiguration = {SecurityAutoConfiguration.class})

class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ModelMapper modelMapper;
    @MockBean
    ProductService productService;
    @MockBean
    JwtUtils    jwtUtils;
    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    private Product product;
    private List<Product> products;


    @BeforeEach
    void setUp() {
        product = Data.createproduct001();
        products = Arrays.asList(Data.createproduct001(), Data.createproduct002());
    }

    @Test
    @DisplayName("Product can be created")
    void addProduct() throws Exception{
        ProductDto productDto = new ModelMapper().map(product, ProductDto.class);
        when(productService.addProduct(anyLong(), any(ProductDto.class))).thenReturn(productDto);


        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/admin/categories/1/product")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(product));

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String responseBodyAsString = mvcResult.getResponse().getContentAsString();
        ProductDto createdProduct = new ObjectMapper()
                .readValue(responseBodyAsString, ProductDto.class);

        Assertions.assertEquals(product.getProductName(),
                createdProduct.getProductName(), "The returned product name is most likely incorrect");

        Assertions.assertEquals(product.getPrice(),
                createdProduct.getPrice(), "The returned product price is incorrect");

        Assertions.assertEquals(product.getDescription(),
                createdProduct.getDescription(), "The returned Product description is incorrect");

        Assertions.assertFalse(createdProduct.getProductName().isEmpty(), "userId should not be empty");
    }

    @Test
    @DisplayName("Product can be updated")
    void updateProduct() throws Exception{
        ProductDto productDto = new ModelMapper().map(product, ProductDto.class);
        when(productService.updateProduct(anyLong(), any(ProductDto.class))).thenReturn(productDto);


        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/admin/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(product));

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String responseBodyAsString = mvcResult.getResponse().getContentAsString();
        ProductDto updatedProduct = new ObjectMapper()
                .readValue(responseBodyAsString, ProductDto.class);

        Assertions.assertEquals(product.getProductName(),
                updatedProduct.getProductName(), "The returned product name is most likely incorrect");

        Assertions.assertEquals(product.getPrice(),
                updatedProduct.getPrice(), "The returned product price is incorrect");

        Assertions.assertEquals(product.getDescription(),
                updatedProduct.getDescription(), "The returned Product description is incorrect");

        Assertions.assertFalse(updatedProduct.getProductName().isEmpty(), "userId should not be empty");
    }


    @Test
    @DisplayName("Product can be deleted")
    void deleteProduct() throws Exception{
        ProductDto productDto = new ModelMapper().map(product, ProductDto.class);
        when(productService.deleteProduct(anyLong())).thenReturn(productDto);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/admin/products/1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Product can be show")
    void getAllProducts() throws Exception {
        //List<ProductDto> productDtos = products.stream()
        //        .map(product -> modelMapper.map(product, ProductDto.class))
        //        .toList();
        ProductDto productDto = new ModelMapper().map(product, ProductDto.class);
        List<ProductDto> productDtos = List.of(productDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/public/products"))
                .andExpect(status().isOk());
    }

}
