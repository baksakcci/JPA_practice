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
            em.persist(member); // 이 때 DB에 바로 저장되진 않는다

            Member findmember = em.find(Member.class, 101L);
            Member findmember2 = em.find(Member.class, 101L);

            System.out.println("findmember.id = " + findmember.getId());
            System.out.println("findmember.name = " + findmember.getName());

            System.out.println("result = " + (findmember == findmember2));
            System.out.println("findmember2 = " + findmember2.getId());
            tx.commit(); // 트랜잭션을 커밋하는 순간에 DB에 쿼리가 날아간다.
        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }
}
