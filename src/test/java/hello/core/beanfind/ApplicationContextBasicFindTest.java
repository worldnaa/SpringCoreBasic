package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        @Test
        @DisplayName("빈 이름으로 조회")
        void findBeanByName() {
            MemberService memberService = ac.getBean("memberService", MemberService.class);
            assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        }

        @Test
        @DisplayName("이름없이 타입으로만 조회")
        void findBeanByType() {
            MemberService memberService = ac.getBean(MemberService.class);
            assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        }

        @Test
        @DisplayName("구체 타입으로 조회")
        void findBeanByName2() {
            MemberServiceImpl memberServiceImpl = ac.getBean("memberService", MemberServiceImpl.class);
            assertThat(memberServiceImpl).isInstanceOf(MemberServiceImpl.class);
        }

        @Test
        @DisplayName("빈 이름으로 조회X")
        void findBeanByNameX() {
//          MemberService xxxx = ac.getBean("xxxx", MemberService.class);
//          () -> 의 오른쪽 로직을 실행하면, 왼쪽 예외가 터져야 한다
            assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("xxxx", MemberService.class));
        }
}
// ac.getBean() : 빈 이름으로 빈 객체(인스턴스)를 조회한다.
// ac.getBean("찾아올 객체명", 반환 타입) => 반환 타입 미지정 시, Object 반환