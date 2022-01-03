package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach //테스트 실행 전에 무조건 실행되는 코드
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

//    MemberService memberService = new MemberServiceImpl();
//    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder() {
        Long memberId = 1L;
        //1. 멤버 객체에 아이디, 이름, 등급을 담는다
        Member member = new Member(memberId, "memberA", Grade.VIP);
        //2. 회원정보가 담긴 멤버 객체를 memberService.join()에게 넘기며, 회원가입을 한다
        memberService.join(member);

        //3. 아이디, 아이템명, 아이템가격을 넘겨주며 만든 주문서를 Order 객체에 담는다
        Order order = orderService.createOrder(memberId, "itemA", 10000);
        //4. Order 객체에 담긴 할인금액이 1000원이 맞는지 확인한다
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);



    }
}
