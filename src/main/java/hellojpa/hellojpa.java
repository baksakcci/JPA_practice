package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class hellojpa {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 비영속
            Member member = new Member();
            member.setId(101L);
            member.setName("JPA2");

            // 영속
            em.persist(member);

            Member findmember = em.find(Member.class, 101L);

            System.out.println("findmember.id = " + findmember.getId());
            System.out.println("findmember.name = " + findmember.getName());
            tx.commit();
        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }
}
