package co.istad.roth.ecommerce.features.product;

import co.istad.roth.ecommerce.features.product.dto.CreateProductRequest;
import co.istad.roth.ecommerce.features.product.dto.ProductResponse;
import org.springframework.data.domain.Page;

public interface ProductService {


    Page<ProductResponse> findAll(int pageNumber, int pageSize);

    ProductResponse createNew(CreateProductRequest createProductRequest);

}
