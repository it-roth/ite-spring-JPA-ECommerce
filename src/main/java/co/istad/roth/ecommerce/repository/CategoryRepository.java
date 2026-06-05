package co.istad.roth.ecommerce.repository;

import co.istad.roth.ecommerce.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {

}

