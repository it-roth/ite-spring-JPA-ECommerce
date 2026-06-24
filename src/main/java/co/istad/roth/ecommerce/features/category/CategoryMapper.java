package co.istad.roth.ecommerce.features.category;

import co.istad.roth.ecommerce.features.category.dto.CategoryResponse;
import co.istad.roth.ecommerce.features.category.dto.CreateCategoryRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    // Return type = Target
    // Parameter = Source
    Category mapCreateCategoryRequestToCategory(CreateCategoryRequest createCategoryRequest);

    CategoryResponse mapCategoryToCategoryResponse(Category category);

}
