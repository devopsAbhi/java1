package java

import spock.lang.Specification
import java.pojo.Customer

import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory

/**
 * Created by deven.phillips on 2/7/16.
 */
class DbExampleSpec extends Specification {

    EntityManagerFactory emf;
    EntityManager em;

    def setup() {
        emf = Mock(EntityManagerFactory.class);
        em = Mock(EntityManager.class);
        emf.createEntityManager() >> em;
    }

    def "Selecting a customer record by ID should only return a single record"() {

        given: "A valid JPA entity manager factory AND a valid customer ID"
        def exampleCustomer = new Customer().familyName("Phillips").givenName("Deven").id(1L);
        em.find(Customer.class, 1L) >> exampleCustomer
        def db = new DbExample(emf);

        when: "The customer ID is used to request the customer"
        def result = db.getCustomer(1L);

        then: "The customer record MUST match the expected values"
        exampleCustomer.equals(result);
    }

    def "If a customer does not exist, the response should be null"() {
        given: "A customer ID which does not exist in the database and a VALID EntityManagerFactory"
        em.find(Customer.class, 31337L) >> { throw new IllegalStateException("TEST MESSAGE") }
        def db = new DbExample(emf);

        when: "A non-existent customer is requested"
        def result = db.getCustomer(31337L)

        then: "A null should be returned"
        result == null
    }
}