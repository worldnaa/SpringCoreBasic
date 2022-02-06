package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.context.annotation.ComponentScan.*;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);

        BeanA beanA = ac.getBean("beanA", BeanA.class);
        assertThat(beanA).isNotNull();

        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("beanB", BeanB.class));
//      assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("beanA", BeanA.class));
    }

    // includeFilters 에 MyIncludeComponent 애노테이션을 추가해서 BeanA가 스프링 빈에 등록된다
    // excludeFilters 에 MyExcludeComponent 애노테이션을 추가해서 BeanB는 스프링 빈에 등록되지않는다
    @Configuration
    @ComponentScan(                //type = FilterType.ANNOTATION : 기본값이라 생략 가능
            includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class))
    static  class  ComponentFilterAppConfig {
    }

    // BeanA도 빼고 싶으면 다음과 같이 추가
//    @Configuration
//    @ComponentScan(
//            includeFilters = { @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),},
//            excludeFilters = { @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class),
//                               @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = BeanA.class)})
//    static  class  ComponentFilterAppConfig {
//    }
}

// FilterType 의 5가지 옵션
// 1) ANNOTATION : 기본값, 애노테이션을 인식해서 동작한다
// 2) ASSIGNABLE_TYPE : 지정한 타입과 자식타입을 인식해서 동작한다
// 3) ASPECTJ : AspectJ 패턴사용
// 4) REGEX : 정규표현식
// 5) CUSTOM : TypeFilter 라는 인터페이스를 구현해서 처리

// @Component 면 충분하기 때문에, includeFilters 를 사용할 일은 거의 없다
// excludeFilters 는 여러가지 이유로 간혹 사용할 때가 있지만 많지는 않다