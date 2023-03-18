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
            // 영속성 컨텍스트 안에 있는 Member field 참조 주소 반환
            Member member = em.find(Member.class, "150L");
            member.setName("ABCDE");
            // em.persist(member);가 필요없을까?
            // Entity와 Snapshot을 비교하는 과정이 곧 수정되었는지 확인하는 과정이 됨
            System.out.println("=========");

            tx.commit(); // 트랜잭션을 커밋하는 순간에 DB에 쿼리가 날아간다.
        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }
}
