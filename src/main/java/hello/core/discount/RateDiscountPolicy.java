package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Primary                          // ==> 여러 빈이 매칭되면 @Primary가 우선권을 가진다
//@Qualifier("mainDiscountPolicy")  // ==> 이름을 직접 지정시 문자열 오타나서 에러나면 잡기 함들다
@MainDiscountPolicy                 // ==> 내가 만든 애노테이션. 오타 시 바로 잡을 수 있다 (컴파일 시 타입 체크)
@Component
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
