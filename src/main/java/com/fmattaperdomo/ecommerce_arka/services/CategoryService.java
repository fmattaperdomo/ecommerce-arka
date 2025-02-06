package com.fmattaperdomo.ecommerce_arka.services;

import com.fmattaperdomo.ecommerce_arka.dtos.CategoryResponse;
import com.fmattaperdomo.ecommerce_arka.entities.Category;

import java.util.List;

public interface CategoryService {
    CategoryResponse getCategories();
    void createCategory(Category category);
    String deleteCategory(Long categoryId);
    Category updateCategory(Category category, Long categoryId);
}
