package fi.haagahelia.bookstore.repository;

import fi.haagahelia.bookstore.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
