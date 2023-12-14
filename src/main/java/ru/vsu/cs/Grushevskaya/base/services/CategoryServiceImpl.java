package ru.vsu.cs.Grushevskaya.base.services;

import ru.vsu.cs.Grushevskaya.base.models.Category;
import ru.vsu.cs.Grushevskaya.base.repository.CategoryRepositoryMemory;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepositoryMemory categories;
    private static CategoryServiceImpl example;

    private CategoryServiceImpl() {
        categories = CategoryRepositoryMemory.getInstance();
    }

    public static CategoryServiceImpl getExample() {
        if (example == null) {
            example = new CategoryServiceImpl();
        }
        return example;
    }

    @Override
    public void createCategory(Category category) {
        categories.add(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categories.getAll();
    }

    @Override
    public Category getCategoryById(int id) {
        return categories.getById(id);
    }

    @Override
    public void updateCategory(int id, Category newCategory) {
        categories.update(id, newCategory);
    }

    @Override
    public void deleteCategory(int id) {
        categories.delete(id);
    }

}
