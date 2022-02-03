package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@RequiredArgsConstructor
@Component
public class OrderServiceImpl implements OrderService {
//  private final MemberRepository memberRepository = new MemoryMemberRepository(); //DIP 위반 (구현체에도 의존)
//  private final DiscountPolicy discountPolicy = new FixDiscountPolicy();          //DIP 위반 (구현체에도 의존)
//  private final DiscountPolicy discountPolicy = new RateDiscountPolicy();         //DIP 위반 (구현체에도 의존)

    private final MemberRepository memberRepository; //DIP 준수 (인터페이스에만 의존)
    private final DiscountPolicy discountPolicy;     //DIP 준수 (인터페이스에만 의존)

    @Autowired //@Autowired를 사용하면 생성자에서 여러 의존관계도 한번에 주입 받을 수 있다
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice); //SRP(단일책임원칙) 준수
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 싱글톤 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
// final일 경우 '=' 혹은 '생성자' 를 통해 반드시 값이 할당되어야 한다
// @Component : MemberServiceImpl을 스프링 빈으로 등록한다

// @Autowired : 자동으로 의존관계 주입해준다. ac.getBean(MemberRepository.class)과 비슷하다
// 이전 AppConfig에서는 @Bean으로 직접 설정정보를 작성했고, 의존관계도직접 명시했다
// 이제는 이런 설정정보 자체가 없기 때문에, 의존관계 주입도 이 클래스 안에서 해결해야 한다

// @RequiredArgsConstructor : final 붙은 애들의 DI를 수행하고, 생성자를 만들어준다 (많이 쓰임)