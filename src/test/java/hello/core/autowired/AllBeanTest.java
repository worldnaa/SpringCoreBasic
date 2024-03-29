package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AllBeanTest {

    @Test
    void findAllBean() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        assertThat(discountService).isInstanceOf(DiscountService.class);

        Member member = new Member(1L, "userA", Grade.VIP);

        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");
        assertThat(discountPrice).isEqualTo(1000);

        int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");
        assertThat(rateDiscountPrice).isEqualTo(2000);
    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        // DiscountService는 Map으로 모든 DiscountPolicy를 주입 받는다
        // 이때 fixDiscountPolicy, rateDiscountPolicy가 주입 된다
        // Map<String, DiscountPolicy>: map의 키에 스프링빈의 이름을 넣어주고, 그 값으로 DiscountPolicy 타입으로 조회한 모든 스프링빈을 담아준다
        // List<DiscountPolicy>: DiscountPolicy 타입으로 조회한 모든 스프링빈을 담아준다
        // 만약 해당하는 타입의 스프링빈이 없으면, 빈 컬렉션이나 Map을 주입한다
        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
         // policyMap = {fixDiscountPolicy=hello.core.discount.FixDiscountPolicy@7fc6de5b, rateDiscountPolicy=hello.core.discount.RateDiscountPolicy@21baa903}
         // policies = [hello.core.discount.FixDiscountPolicy@7fc6de5b, hello.core.discount.RateDiscountPolicy@21baa903]
        }

        // discountCode로 "fixDiscountPolicy"가 넘어오면 map에서 fixDiscountPolicy 스프링빈을 찾아서 실행
        // discountCode로 "rateDiscountPolicy"가 넘어오면 map에서 rateDiscountPolicy 스프링빈을 찾아서 실행
        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member, price);
        }
    }
}
