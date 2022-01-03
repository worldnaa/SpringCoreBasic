package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach //테스트 실행 전에 무조건 실행되는 코드
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

//    MemberService memberService = new MemberServiceImpl();

    @Test
    void join() {

        //given (~~ 것 들이 주어졌을 때)
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when (~~ 이렇게 했을 때)
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then (~~ 이렇게 된다 / 검증)
        //Assertions : org.assertj.core.api 선택
        //아래 코드 뜻 : member 가 findMember 랑 똑같은지 비교해서 성공하면 초록색이 뜬다
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
