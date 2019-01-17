package twitter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import twitter.db.AuthorRepository;
import twitter.model.Author;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AuthorRepositoryTest {
    EntityManager entityManager;

    @BeforeEach
    void start() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");
        this.entityManager = factory.createEntityManager();
    }


    @DisplayName("should add 3 new author into database")
    @Test
    void test() throws Exception {
        //when
        Stream<Author> authorStream = Stream.of(
                new Author("admin", "admin"),
                new Author("admin1", "admin1"),
                new Author("admin2", "admin2"));
        AuthorRepository authorRepository = new AuthorRepository(entityManager);
        authorRepository.populate();

        Stream<Author> all = authorRepository.findAll();
        assertThat(all.count()).isEqualTo(authorStream.count());
    }
}
