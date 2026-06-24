package co.istad.roth.ecommerce.features.category;

import co.istad.roth.ecommerce.features.category.dto.CategoryResponse;
import co.istad.roth.ecommerce.features.category.dto.CreateCategoryRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

//    public CategoryServiceImpl(CategoryRepository categoryRepository) {
//        this.categoryRepository = categoryRepository;
//    }

    @Override
    public CategoryResponse createNew(CreateCategoryRequest createCategoryRequest) {
        log.info("createNew {}", createCategoryRequest);

        // Validate category name
        boolean isExisting = categoryRepository
                .existsByName(createCategoryRequest.name());
        if (isExisting)
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Category has already been used"
            );

        Category parentCategory = null;

        // Validate parent category
        if (createCategoryRequest.parentCategoryId() != null) {
            parentCategory = categoryRepository
                    .findById(createCategoryRequest.parentCategoryId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Parent category has not been found"
                    ));
        }

        Category category = categoryMapper
                .mapCreateCategoryRequestToCategory(createCategoryRequest);

        // System generated data
        category.setIsDeleted(false);
        category.setParentCategory(parentCategory);

        // Insert if primary key is null
        // Update if primary key has value
        category = categoryRepository.save(category);

        return categoryMapper.mapCategoryToCategoryResponse(category);
    }

}
