package java;

import java.pojo.Customer;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;

/**
 *
 */
public class DbExample {

    private EntityManagerFactory emf;

    @Inject
    public DbExample(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public Customer getCustomer(long id) throws IllegalStateException {
        try {
            Customer result = emf.createEntityManager().find(Customer.class, id);
            return result;
        } catch (IllegalStateException ise) {
            return null;
        }
    }
}
