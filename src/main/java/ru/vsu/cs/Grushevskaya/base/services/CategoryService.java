package ru.vsu.cs.Grushevskaya.base.services;

import ru.vsu.cs.Grushevskaya.base.models.Category;

import java.util.List;

public interface CategoryService {
    void createCategory(Category category);
    List<Category> getAllCategories();
    Category getCategoryById(int id);
    void updateCategory(int id, Category newCategory);
    void deleteCategory(int id);
}
