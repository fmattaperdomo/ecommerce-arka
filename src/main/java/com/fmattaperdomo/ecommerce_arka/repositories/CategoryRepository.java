package com.fmattaperdomo.ecommerce_arka.repositories;

import com.fmattaperdomo.ecommerce_arka.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByCategoryName(String categoryName);
    Category findByCategoryId(Long categoryId);
}
