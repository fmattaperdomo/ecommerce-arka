package com.fmattaperdomo.ecommerce_arka.services;

import com.fmattaperdomo.ecommerce_arka.dtos.CategoryDto;
import com.fmattaperdomo.ecommerce_arka.dtos.CategoryResponse;
import com.fmattaperdomo.ecommerce_arka.entities.Category;

import java.util.List;

public interface CategoryService {
    CategoryResponse getCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto deleteCategory(Long categoryId);
    CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);
}
