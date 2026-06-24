package co.istad.roth.ecommerce.features.category;

import co.istad.roth.ecommerce.features.category.dto.CategoryResponse;
import co.istad.roth.ecommerce.features.category.dto.CreateCategoryRequest;

public interface CategoryService {

    CategoryResponse createNew(CreateCategoryRequest createCategoryRequest);

}
