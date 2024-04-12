package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            Movie movie = new Movie();
            movie.setDirector("김봉원");
            movie.setActor("이소라");
            movie.setName("바람과함께사라지다");
            movie.setPrice(10000);

            em.persist(movie);

            em.flush(); //영속성 컨텍스트의 변경 내용을 DB에 반영
            em.clear(); //영속성 컨텍스트 정리

            Movie findMovie = em.find(Movie.class, movie.getId());
            System.out.println("findMovie = " + findMovie);

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
