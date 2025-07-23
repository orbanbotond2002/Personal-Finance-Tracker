package com.OrbanBotond.Personal_Finance_Tracker.mappers;

import com.OrbanBotond.Personal_Finance_Tracker.dto.CategoryRequestDTO;
import com.OrbanBotond.Personal_Finance_Tracker.dto.CategoryResponseDTO;
import com.OrbanBotond.Personal_Finance_Tracker.entities.Category;

public class CategoryMapper {

    public static Category newCategory(CategoryRequestDTO dto) {
        Category category = new Category();
        return toEntity(category, dto);
    }

    public static Category toEntity(Category category, CategoryRequestDTO dto) {
        category.setName(dto.getName());
        return category;
    }

    public static CategoryResponseDTO toDTO(Category category) {
        CategoryResponseDTO dto = new CategoryResponseDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }
}
