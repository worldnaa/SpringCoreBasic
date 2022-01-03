package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {

//      AppConfig appConfig = new AppConfig();
//      MemberService memberService = appConfig.memberService();

/////////////////////// 위 코드를 스프링을 사용할 경우 아래처럼 변경 ///////////////////////

        // AppConfig.class 에서 @Bean 표시가 있는 객체들을 스프링 컨테이너에서 생성하여 관리한다
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        // 스프링 컨테이너를 통해 memberService() 찾아오기
        // getBean("찾아올 객체명", 반환 타입)
        MemberService memberService = ac.getBean("memberService", MemberService.class);

//      MemberService memberService = new MemberServiceImpl();

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}

















