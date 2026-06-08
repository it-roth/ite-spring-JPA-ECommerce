package co.istad.roth.ecommerce.controller;

import co.istad.roth.ecommerce.domain.Category;
import co.istad.roth.ecommerce.dto.CategoryResponse;
import co.istad.roth.ecommerce.dto.CreateCategoryRequest;
import co.istad.roth.ecommerce.dto.UpdateCategoryRequest;
import co.istad.roth.ecommerce.repository.CategoryRepository;
import co.istad.roth.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    //GetData
    @GetMapping
    public Page<CategoryResponse> getAllCategories(@RequestParam(defaultValue = "0") Integer pageNumber, @RequestParam(defaultValue = "25") Integer pageSize) {
        return categoryService.getAllCategories(pageNumber, pageSize);
    }

    @GetMapping("/{id}")
    public CategoryResponse getCategoryById(@PathVariable Integer id) {
        return categoryService.getCategoryById(id);
    }

    @GetMapping("/{parentCategoryId}/subcategories")
    public List<CategoryResponse> getAllSubCategories(@PathVariable Integer parentCategoryId){
        return categoryService.getSubCategories(parentCategoryId);
    }

    //DeleteData
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void hardDeleteCategory(@PathVariable Integer id) {
        categoryService.hardDeleteById(id);
    }

//    @PutMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void softDeleteCategory(@PathVariable Integer id,@RequestBody CreateCategoryRequest createCategoryRequest){
//        categoryService.softDeleteById(id);
//    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void softDeleteCategory(@PathVariable Integer id) {
        categoryService.softDeleteById(id);
    }

    //CreateData
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CategoryResponse createNew(@Valid @RequestBody CreateCategoryRequest createCategoryRequest){
        return categoryService.createNew(createCategoryRequest);
    }

    //UpdateData
    @PatchMapping("/{id}")
    public CategoryResponse updateCategoryById(@PathVariable Integer id, @RequestBody UpdateCategoryRequest updateCategoryRequest){
        return  categoryService.updateCategoryById(id,updateCategoryRequest);
    }
}
