package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*; // static으로 import 하는 것이 좋다
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다") // junit5 부터 지원하는 기능
    void vip_o() {
        //given - 임의의 Member 만들기
        Member member = new Member(1L, "memberVIP", Grade.VIP);
        //when
        int discount = discountPolicy.discount(member, 10000);
        //then
        assertThat(discount).isEqualTo(1000);
    }

    @Test //실패 Test를 만드는 것도 매우 중요하다!
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다")
    void vip_x() {
        //given - 임의의 Member 만들기
        Member member = new Member(2L, "memberBASIC", Grade.BASIC);
        //when
        int discount = discountPolicy.discount(member, 10000);
        //then
        assertThat(discount).isEqualTo(0);
    }

    // 발생한 에러
//    org.opentest4j.AssertionFailedError:
//    expected: 1000  ==> 기대한건 1000원인데
//    but was: 0      ==> 결과는 0원이다
//    Expected :1000
//    Actual   :0
}








