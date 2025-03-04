package com.fmattaperdomo.ecommerce_arka.repositories;

import com.fmattaperdomo.ecommerce_arka.entities.Category;
import com.fmattaperdomo.ecommerce_arka.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByCategoryOrderByPriceAsc(Category category, Pageable pageDetails);
    Optional<Product> findById(Long id);
    Optional<Product> findByProductName(String productName);
    Page<Product> findByProductNameLikeIgnoreCase(String keyword, Pageable pageDetails);

    @Query("select e from Product e where e.productName = ?1 and e.description = ?2")
    Product findByJPQL(String productName, String description);

    @Query("select e from Product e where e.productName =:productName and e.description =:description")
    Product findByJPQLNamedParams(@Param("productName") String productName, @Param("description") String description);

    @Query(value = "select * from products e where e.productName =?1 and e.description =?2", nativeQuery = true)
    Product findByNativeSQL(String productName, String description);

    @Query(value = "select * from products e where e.productName =:productName and e.description =:description",
            nativeQuery = true)
    Product findByNativeSQLNamed(@Param("productName") String productName, @Param("description") String description);
}
