package hellojpa;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            Member member = new Member();
            member.setUsername("user1");
            member.setCreatedBy("Kim");
            member.setCreatedDate(LocalDateTime.now());


            em.persist(member);

            em.flush(); //영속성 컨텍스트의 변경 내용을 DB에 반영
            em.clear(); //영속성 컨텍스트 정리


            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
