package twitter.web;

import twitter.db.AuthorRepository;

import javax.persistence.EntityManager;


public class LoginManager {

    EntityManager entityManager;

    public LoginManager(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    public boolean isValid(String login, String password) {
        AuthorRepository authorRepository = new AuthorRepository(entityManager);
        return authorRepository.findAll().anyMatch(author -> author.getName().equals(login) && author.getPassword().equals(password));
    }

}
