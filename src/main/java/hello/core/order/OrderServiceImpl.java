package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {
    // final일 경우 = or 생성자 를 통해 반드시 값이 할당되어야 한다!
    // 1) 아래 코드는 추상화에도 의존하고 구체화에도 의존하고 있다 (DIP 위반)
    // private final MemberRepository memberRepository = new MemoryMemberRepository(); //회원을 찾기위해 필요
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();  //할인정책을 찾기위해 필요
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); //할인정책을 찾기위해 필요

    // 2) 아래 코드는 추상화에만(인터페이스) 의존하고 있다 (DIP 수행)
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {//AppConfig에서 orderService() 실행시 실행됨
        //매개변수로 받은 memberRepository, discountPolicy 값을
        //private final MemberRepository memberRepository; 와 DiscountPolicy discountPolicy; 에 할당한다
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        //단일체계원칙이 잘 설계되었다
        //OrderServiceImpl 은 할인정책이 바껴도 알 필요가 없다
        //할인정책이 변경될 경우 DiscountPolicy 에서만 수정한다
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
