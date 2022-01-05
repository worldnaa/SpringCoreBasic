package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryMemberRepository implements MemberRepository {
    // 회원정보가 Map 형태로 저장되는 store 변수
    private static Map<Long, Member> store = new HashMap<>();

    @Override // 회원정보를 저장하는 Method
    public void save(Member member) {
        // 매개변수로 받은 member를 store에 담는다
        // key는 member의 id를 기준으로, value는 member 객체를 담는다
        // member에는 id, name, grade 가 담겨있다
        store.put(member.getId(), member);
    }

    @Override // 회원정보를 찾는 Method
    public Member findById(Long memberId) {
        // 매개변수로 받은 memberId를 key값으로 하여 store에 있는 회원정보 반환
        return store.get(memberId);
    }
}
