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
            Member member1 = new Member(150L, "A");
            Member member2 = new Member(160L, "B");

            em.persist(member1);
            em.persist(member2);
            System.out.println("==============");
            // 선 이후로 SQL이 나감.

            tx.commit(); // 트랜잭션을 커밋하는 순간에 DB에 쿼리가 날아간다.
        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }
}
