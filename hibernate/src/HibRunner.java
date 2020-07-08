import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.*;
import java.util.*;

public class HibRunner {
    public static void main(String[] args) {
        Customer customer1 = new Customer(0.5, "Super firma", "Krotka", "Gdansk", "32-123");
        Customer customer2 = new Customer(0.6, "Grynn", "Dluga", "Nysa", "32-456");
        Customer customer3 = new Customer(0.6, "Tortex", "Biala", "Zgorzelsk", "32-789");

        Supplier supplier1 = new Supplier("831239-1237810", "Drutex", "Mokra", "Lodz", "32-743");
        Supplier supplier2 = new Supplier("1293-12389", "ASD", "Czerwona", "Wlosienica", "32-937");
        Supplier supplier3 = new Supplier("17283-343912", "FKKO", "Czarna", "Osiek", "32-867");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("KrzysztofHardekJPA");
        EntityManager em = emf.createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();

        em.persist(supplier1);
        em.persist(supplier2);
        em.persist(supplier3);
        em.persist(customer1);
        em.persist(customer2);
        em.persist(customer3);

        etx.commit();
        em.close();
    }
}
