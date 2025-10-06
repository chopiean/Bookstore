package fi.haagahelia.bookstore.repository;

import fi.haagahelia.bookstore.model.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")

public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepo;

    @Test
    public void createAndFindUser() {
        User u = new User("tester", "pass", "tester@mail.com", "USER");
        userRepo.save(u);
        assertThat(userRepo.findByUsername("tester")).isNotNull();
    }

    @Test
    public void deleteUser() {
        User u = userRepo.save(new User("tempuser", "temp", "temp@mail.com", "USER"));
        userRepo.delete(u);
        assertThat(userRepo.findByUsername("tempuser")).isNull();
    }
}
