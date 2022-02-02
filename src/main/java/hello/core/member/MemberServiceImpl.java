package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{
//  private final MemberRepository memberRepository = new MemoryMemberRepository(); //DIP 위반 (구현체에도 의존)
    private final MemberRepository memberRepository; //DIP 준수 (인터페이스에만 의존)

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
// final일 경우 '=' 혹은 '생성자' 를 통해 반드시 값이 할당되어야 한다
// @Component : MemberServiceImpl을 스프링 빈으로 등록한다
// @Autowired : 자동으로 의존관계 주입해준다. ac.getBean(MemberRepository.class)과 비슷하다