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

            Member refMember = em.getReference(Member.class, member1.getId());
            System.out.println("refMember = " + refMember.getClass()); //Proxy

            em.detach(refMember); // 영속성 컨텍스트 refMember 관리 안한다.
//            em.close(); //영속성 컨텍스트 종료

            refMember.getUsername(); // 실제 DB에 쿼리가 나가면서 프록시가 조회가 된다.
            System.out.println("refMember = " + refMember.getUsername());

//          영속성 detach, close, clear 를 하면 could not initialize proxy 예외가 뜬다


//            Member findMember = em.find(Member.class, member1.getId());
//            System.out.println("findMember = " + findMember.getClass()); //Member? Proxy?

            // JPA에서 == 비교가 한 영속성 컨텍스트안에서 가져온 거고, pk가 같다면 true를 반환한다.
            // JPA는 한 트랜잭션 안에서 같은 거에 대해선 보장을 해준다.
            // 이미 영속성 컨텍스트안에 있으면 프록시가 아니라 실제 엔티티를 반환해준다.
//            System.out.println("refMember == findMember : " + (refMember == findMember));
//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.username = " + findMember.getUsername());


            tx.commit();
        } catch (Exception e){
            tx.rollback();
            e.printStackTrace();
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
