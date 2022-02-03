package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        //모두 같은 인스턴스를 참고하고 있다
        System.out.println("memberService -> memberRepository = " + memberService.getMemberRepository()); //MemoryMemberRepository@3ecd267f
        System.out.println("orderService  -> memberRepository = " + orderService.getMemberRepository());  //MemoryMemberRepository@3ecd267f
        System.out.println("memberRepository = " + memberRepository);                                     //MemoryMemberRepository@3ecd267f

        //모두 같은 인스턴스를 참고하고 있다
        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        //AppConfig도 스프링 빈으로 등록된다
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());
        // 결과 : bean = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$80684140
    }
}
// 사실 AnnotationConfigApplicationContext에 파라미터로 넘긴 값은 스프링 빈으로 등록된다. 그래서 AppConfig도 스프링 빈이 된다.
// 클래스명에 xxxCGLIB가 붙은 것은 내가 만든 클래스가 아니라 스프링이 CGLIB 라는 바이트코드 조작 라이브러리를 사용해서
// AppConfig 클래스를 상속받은 임의의 다른 클래스를 만들고, 그 다른 클래스를 스프링 빈으로 등록한 것이다.