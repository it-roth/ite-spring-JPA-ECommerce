package co.istad.roth.ecommerce.mapper;

import co.istad.roth.ecommerce.domain.Category;
import co.istad.roth.ecommerce.dto.CategoryResponse;
import co.istad.roth.ecommerce.dto.CreateCategoryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    //Return type = Target
    //Parameter = Source
    Category mapCreateCategoryRequestToCategory(CreateCategoryRequest createCategoryRequest);
    CategoryResponse mapCategoryToCategoryResponse(Category category);

}
