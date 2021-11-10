package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore(); // 하나의 테스트 끝날 때마다 저장된 데이터 삭제!(순서가 상관없어짐)
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("summer");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        // Assertions.assertEquals(member, result); // 둘이 같은 지 알아볼 수 있음 jUNIT
        assertThat(member).isEqualTo(result); // assertj 순서:member가 result랑 똑같은 지 물어보는 것

    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("yjk");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("yjk2");
        repository.save(member2);

        Member result = repository.findByName("yjk").get(); // member1을

        assertThat(result).isEqualTo(member1); //
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("yjk");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("yjk2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);

    }
}
