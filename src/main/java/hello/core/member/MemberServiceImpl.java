package hello.core.member;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberServiceImpl implements MemberService{
//  private final MemberRepository memberRepository = new MemoryMemberRepository(); //DIP 위반 (구현체에도 의존)
    private final MemberRepository memberRepository; //DIP 준수 (인터페이스에만 의존)

    // @RequiredArgsConstructor 사용 시, 아래 코드 생략가능
//    @Autowired
//    public MemberServiceImpl(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 싱글톤 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
// @RequiredArgsConstructor : final 붙은 애들의 DI를 수행하고, 생성자를 만들어준다 (많이 쓰임)
// @Component : MemberServiceImpl을 스프링 빈으로 등록한다

// final일 경우 '=' 혹은 '생성자' 를 통해 반드시 값이 할당되어야 한다 (생성자 주입만 final 사용가능)

// @Autowired : 자동으로 의존관계 주입해준다. ac.getBean(MemberRepository.class)과 비슷하다
// @Autowired : 생성자가 딱 1개만 있으면 @Autowired를 생략해도 자동 주입된다
// @Autowired : @Autowired를 사용하면 생성자에서 여러 의존관계도 한번에 주입 받을 수 있다

// 이전 AppConfig에서는 @Bean으로 직접 설정정보를 작성했고, 의존관계도직접 명시했다
// 이제는 이런 설정정보 자체가 없기 때문에, 의존관계 주입도 이 클래스 안에서 해결해야 한다