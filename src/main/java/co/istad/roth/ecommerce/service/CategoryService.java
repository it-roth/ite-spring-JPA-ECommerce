package co.istad.roth.ecommerce.service;

import co.istad.roth.ecommerce.domain.Category;
import co.istad.roth.ecommerce.dto.CategoryResponse;
import co.istad.roth.ecommerce.dto.CreateCategoryRequest;
import co.istad.roth.ecommerce.dto.UpdateCategoryRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    Page<CategoryResponse> getAllCategories(Integer pageNumber, Integer pageSize);
    CategoryResponse getCategoryById(Integer Id);
    List<CategoryResponse> getSubCategories(Integer categoryId);

    CategoryResponse createNew(CreateCategoryRequest  createCategoryRequest);


    public void hardDeleteById(Integer id);
    void softDeleteById(Integer id);

    CategoryResponse updateCategoryById(Integer id, UpdateCategoryRequest updateCategoryRequest);

}
