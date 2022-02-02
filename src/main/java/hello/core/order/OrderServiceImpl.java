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

    @Autowired
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

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
// final일 경우 '=' 혹은 '생성자' 를 통해 반드시 값이 할당되어야 한다
// @Component : MemberServiceImpl을 스프링 빈으로 등록한다
// @Autowired : 자동으로 의존관계 주입해준다. ac.getBean(MemberRepository.class)과 비슷하다
// @RequiredArgsConstructor : final 붙은 애들의 DI를 수행하고, 생성자를 만들어준다 (많이 쓰임)