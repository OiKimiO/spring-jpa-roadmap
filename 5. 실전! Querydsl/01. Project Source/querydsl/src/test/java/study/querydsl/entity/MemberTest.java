package study.querydsl.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@SpringBootTest
@Transactional
@Commit
class MemberTest {

    @Autowired
    EntityManager em;

    @Test
    public void testEntity(){
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member user1 = new Member("user1", 10, teamA);
        Member user2 = new Member("user2", 20, teamA);

        Member user3 = new Member("user3", 30, teamB);
        Member user4 = new Member("user4", 40, teamB);

        em.persist(user1);
        em.persist(user2);
        em.persist(user3);
        em.persist(user4);

        em.flush();
        em.clear();

        List<Member> selectMFromMemberM = em.createQuery("select m from Member m",Member.class).getResultList();
        for (Member o : selectMFromMemberM) {
            System.out.println("o = " + o);
        }

    }
}