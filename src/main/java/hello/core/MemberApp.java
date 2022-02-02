package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
//      MemberService memberService = new MemberServiceImpl();

//      AppConfig appConfig = new AppConfig();
//      MemberService memberService = appConfig.memberService();

        ///////////////////////////////// 스프링 사용 시 /////////////////////////////////
        //스프링 컨테이너 생성 (ApplicationContext:인터페이스 / AnnotationConfigApplicationContext:구현체)
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        ///////////////////////////////////////////////////////////////////////////////

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
// ApplicationContext 를 '스프링컨테이너' 라 한다.
// 기존에는 개발자가 AppConfig를 사용해서 직접 객체를 생성하고 DI를 했지만 이제부터는 스프링 컨테이너를 통해서 사용한다.
// 스프링컨테이너는 @Configuration이 붙은 AppConfig를 설정(구성) 정보로사용한다.
// 여기서 @Bean 이라 적힌 메서드를 모두 호출해서 반환된 객체를 스프링컨테이너에 등록한다.
// 이렇게 스프링컨테이너에 등록된 객체를 '스프링빈' 이라 한다.
// 스프링빈은 @Bean이 붙은 메서드의 명을 스프링빈의 이름으로사용한다. (ex. memberService, orderService)
// 이전에는 개발자가 필요한 객체를 AppConfig를 사용해서 직접 조회 했지만, 이제부턴 스프링 컨테이너를 통해서 필요한 스프링빈(객체)를 찾아야한다.
// 스프링빈은 applicationContext.getBean() 메서드를 사용해서 찾을 수 있다.

// AppConfig.class 에서 @Bean 표시가 있는 객체들을 스프링 컨테이너에서 생성하여 관리한다
// 스프링 컨테이너를 통해 memberService() 찾아오기 : getBean("찾아올 객체명", 반환 타입)











