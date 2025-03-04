package com.fmattaperdomo.ecommerce_arka.repositories;

import com.fmattaperdomo.ecommerce_arka.Data;
import com.fmattaperdomo.ecommerce_arka.entities.Product;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        product1 = Data.createproduct001();
        product2 = Data.createproduct002();
    }

    // JUnit test for save product operation
    //@DisplayName("JUnit test for save product operation")
    @Test
    public void givenProductObject_whenSave_thenReturnSavedProduct(){
        Product savedProduct = productRepository.save(product1);
        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getProductId()).isGreaterThan(0);
    }

    // JUnit test for get all products operation
    @DisplayName("JUnit test for get all products operation")
    @Test
    public void givenProductsList_whenFindAll_thenProductsList(){
        productRepository.save(product1);
        productRepository.save(product2);

        List<Product> productList = productRepository.findAll();

        assertThat(productList).isNotNull();
        assertThat(productList.size()).isEqualTo(2);
    }

    // JUnit test for get employee by id operation
    @DisplayName("JUnit test for get product by id operation")
    @Test
    public void givenProductObject_whenFindById_thenReturnProductObject(){
        productRepository.save(product1);
        Product productDB = productRepository.findById(product1.getProductId()).get();
        assertThat(productDB).isNotNull();
    }

    // JUnit test for get product by email operation
    @DisplayName("JUnit test for get product by email operation")
    @Test
    public void givenProductName_whenFindByProductName_thenReturnProductObject(){
        productRepository.save(product1);
        Product productDB = productRepository.findByProductName(product1.getProductName()).get();
        assertThat(productDB).isNotNull();
    }

    // JUnit test for update product operation
    @DisplayName("JUnit test for update product operation")
    @Test
    public void givenProductObject_whenUpdateProduct_thenReturnUpdatedProduct(){
        productRepository.save(product1);
        Product savedProduct = productRepository.findById(product1.getProductId()).get();
        savedProduct.setProductName("name 11");
        savedProduct.setDescription("description 11");
        Product updatedProduct =  productRepository.save(savedProduct);
        assertThat(updatedProduct.getProductName()).isEqualTo("name 11");
        assertThat(updatedProduct.getDescription()).isEqualTo("description 11");
    }

    // JUnit test for delete product operation
    @DisplayName("JUnit test for delete product operation")
    @Test
    public void givenProductObject_whenDelete_thenRemoveProduct(){
        productRepository.save(product1);
        productRepository.deleteById(product1.getProductId());
        Optional<Product> productOptional = productRepository.findById(product1.getProductId());
        assertThat(productOptional).isEmpty();
    }

    // JUnit test for custom query using JPQL with index
    @DisplayName("JUnit test for custom query using JPQL with index")
    @Test
    public void givenProductNameAndDescription_whenFindByJPQL_thenReturnProductObject(){
        productRepository.save(product1);
        String productName = "product 1";
        String description = "description 1";
        Product savedProduct = productRepository.findByJPQL(productName, description);
        assertThat(savedProduct).isNotNull();
    }

    // JUnit test for custom query using JPQL with Named params
    @DisplayName("JUnit test for custom query using JPQL with Named params")
    @Test
    public void givenProductNameAndDescription_whenFindByJPQLNamedParams_thenReturnProductObject(){
        productRepository.save(product1);
        String productName = "product 1";
        String description = "description 1";
        Product savedProduct = productRepository.findByJPQLNamedParams(productName, description);
        assertThat(savedProduct).isNotNull();
    }

    // JUnit test for custom query using native SQL with index
    @DisplayName("JUnit test for custom query using native SQL with index")
    @Test
    public void givenProductNameAndDescription_whenFindByNativeSQL_thenReturnProductObject(){
        productRepository.save(product1);
        Product savedProduct = productRepository.findByNativeSQL(product1.getProductName(), product1.getDescription());
        assertThat(savedProduct).isNotNull();
    }

    // JUnit test for custom query using native SQL with named params
    @DisplayName("JUnit test for custom query using native SQL with named params")
    @Test
    public void givenProductNameAndDescription_whenFindByNativeSQLNamedParams_thenReturnProductObject(){
        productRepository.save(product1);
        Product savedProduct = productRepository.findByNativeSQLNamed(product1.getProductName(), product1.getDescription());
        assertThat(savedProduct).isNotNull();
    }

}
