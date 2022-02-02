package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
// junit5 부터는 public 생략 가능
class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) { // iter + Tab : 리스트나 배열이 있으면 for문 자동완성
            Object bean = ac.getBean(beanDefinitionName);       // 타입을 지정 안 했기 때문에 Object가 꺼내진다
            System.out.println("name = " + beanDefinitionName + " / object = " + bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {

            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + " / object = " + bean);
            }
        }
    }
}
// ac.getBeanDefinition()      : 스프링에 등록된 빈에 대한 메타 데이터 정보들을 추출한다.
// ac.getBeanDefinitionNames() : 스프링에 등록된 모든 빈 이름을 조회한다.

// ac.getBean() : 빈 이름으로 빈 객체(인스턴스)를 조회한다.
// ac.getBean("찾아올 객체명", 반환 타입) => 반환 타입 미지정 시, Object 반환)

// 스프링이 내부에서 사용하는 빈은 getRole()로 구분할 수 있다.
// Role ROLE_APPLICATION : 직접 등록한 애플리케이션 빈
// Role ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈