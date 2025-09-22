package fi.haagahelia.bookstore.repository;

import fi.haagahelia.bookstore.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "categories")  // REST path will be /api/categories
public interface CategoryJpaRepository extends JpaRepository<Category, Long> {
}
