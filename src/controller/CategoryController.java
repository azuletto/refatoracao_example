package src.controller;

import src.model.Category;
import java.util.ArrayList;
import java.util.List;

public class CategoryController {
    private List<Category> categories = new ArrayList<>();

    public void addCategory(Category category) {
        categories.add(category);
    }

    public void editCategory(int id, Category newCategory) {
        for (Category category : categories) {
            if (category.getId() == id) {
                category.setName(newCategory.getName());
                break;
            }
        }
    }

    public void deleteCategory(int id) {
        categories.removeIf(category -> category.getId() == id);
    }

    public Category getCategory(int id) {
        for (Category category : categories) {
            if (category.getId() == id) {
                return category;
            }
        }
        return null;
    }

    public List<Category> getAllCategories() {
        return categories;
    }
}
