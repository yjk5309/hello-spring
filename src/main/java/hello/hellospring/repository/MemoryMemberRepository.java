package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // 간단한 예제이기 때문에 아이디 설정을 그냥 1씩 늘리는 식으로 설정
        store.put(member.getId(), member); //지금은 id를 제외하곤 name만 있지만 그 외에도 다른 정보가 들어갈 수 있어서 Member타입으로 설정
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // 객체의 값들중 모두 돌려보면서
                .filter(member -> member.getName().equals(name)) //필터안의
                // 조건(member를 받아서 받은 멤버 객체의 name이 findByName 메소드 호출시 매개변수로 받은 name과 같은지를 체크)이 만족하면
                .findAny(); //리턴
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
