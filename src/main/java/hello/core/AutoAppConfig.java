package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration // 코드 열면 @Component 애노테이션이 붙어있다
@ComponentScan(
        // Default : @ComponentScan 이 붙은 클래스의 패키지부터 하위 패키지 까지 조회하여 등록 (권장)
        basePackages = "hello.core.member", //member 패키지 안에 있는 애들만 등록
        basePackageClasses = AutoAppConfig.class, // AutoAppConfig 클래스가 속한 패키지(hello.core)부터 조회하여 등록

        //불필요 한 것은 excludeFilters 를 사용하여 제외시킬 수 있다 (AppConfig, TestConfig 등을 제외)
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
    // 기존의 AppConfig 와는 다르게 @Bean으로 등록한 클래스가 하나도 없다!
}

// 컴포넌트 스캔은 @Component 애노테이션이 붙은 클래스를 스캔해서 스프링 빈으로 등록한다
// 컴포넌트 스캔을 사용하면 @Configuration이 붙은 설정 정보도 자동으로 등록된다 (@Configuration 코드에 @Component가 붙어있어서!)
