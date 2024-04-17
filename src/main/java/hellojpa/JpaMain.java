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

            Member member1 = new Member();
            member1.setUsername("hello");

            em.persist(member1);

            em.flush();
            em.clear();

//            Member findMember = em.find(Member.class, member.getId());

            Member m1 = em.find(Member.class, member1.getId());
            System.out.println("m1 = " + m1.getClass());

            Member reference = em.getReference(Member.class, member1.getId());
            System.out.println("reference = " + reference.getClass());

            // JPA에서 == 비교가 한 영속성 컨텍스트안에서 가져온 거고, pk가 같다면 true를 반환한다.
            // JPA는 한 트랜잭션 안에서 같은 거에 대해선 보장을 해준다.
            // 이미 영속성 컨텍스트안에 있으면 프록시가 아니라 실제 엔티티를 반환해준다.
            System.out.println("a == a " + (m1 == reference));
//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.username = " + findMember.getUsername());


            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    private static void printMember(Member member) {
        System.out.println("member = " + member.getUsername());
    }

    private static void printMemberAndTeam(Member member) {
        String username = member.getUsername();
        System.out.println("username = " + username);

        Team team = member.getTeam();
        System.out.println("team = "+ team);
    }
}
