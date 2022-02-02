package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    @Bean
    public DiscountPolicy discountPolicy() {
        System.out.println("call AppConfig.discountPolicy");
//      return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
// @Configuration : AppConfig.class 에 설정을 구성 한다는 의미
// @Bean : 스프링 컨테이너에 스프링 빈으로 등록 됨 (메서드명으로 등록됨)

// 스프링컨테이너는 @Configuration이 붙은 AppConfig를 설정(구성) 정보로사용한다.
// 여기서 @Bean 이라 적힌 메서드를 모두 호출해서 반환된 객체를 스프링컨테이너에 등록한다.
// 이렇게 스프링컨테이너에 등록된 객체를 '스프링빈' 이라 한다.
// 스프링빈은 @Bean이 붙은 메서드의 명을 스프링빈의 이름으로사용한다. (ex. memberService, orderService)

// 생성자 주입 : 생성자를 통해서 객체가 들어가는 기법
// 어딘가에서 AppConfig를 통해 메서드를 실행하면, 생성자를 실행하며 인자로 구현체의 클래스를 넘겨준다