package twitter.model;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MyEntityManagerFactory {

    public static EntityManagerFactory instance;

    public static synchronized javax.persistence.EntityManagerFactory getInstance() {
        if (instance == null) {
            instance = Persistence.createEntityManagerFactory("test");
        }
        return instance;
    }
}
