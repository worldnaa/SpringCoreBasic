package hello.core.autowired;

import hello.core.member.Member;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {

        @Autowired(required = false)
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }
    }

    // 생성자 주입을 사용하면 다음처럼 주입 데이터를 누락 했을 때 컴파일 오류가 발생한다
    // 그리고 IDE에서 바로 어떤 값을 필수로 주입해야 하는지 알 수 있다
//    @Test
//    void createOrder() {
//        OrderServiceImpl orderService = new OrderServiceImpl();
//        orderService.createOrder(1L, "itemA", 10000);
//    }
}
// 자동 주입 대상을 옵션으로 처리하는 방법
// 1) @Autowired(required=false) : 자동 주입할 대상이 없으면 수정자 메서드 자체가 호출 안 됨 
// 2) org.springframework.lang.@Nullable : 자동 주입할 대상이 없으면 null이 입력 됨 
// 3) Optional<>: 자동 주입할 대상이 없으면 Optional.empty가 입력 됨