package fi.haagahelia.bookstore.repository;

import fi.haagahelia.bookstore.model.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")

public class CategoryRepositoryTest {

    @Autowired
    private CategoryJpaRepository categoryRepo;

    @Test
    void testCreateAndDeleteCategory() {
        Category category = new Category("Science");
        category = categoryRepo.save(category);  

        assertThat(category.getId()).isNotNull();

        categoryRepo.delete(category); 
        assertThat(categoryRepo.findById(category.getId())).isEmpty();
    }
}
