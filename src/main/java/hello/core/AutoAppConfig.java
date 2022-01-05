package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // Default : @ComponentScan 이 붙은 클래스의 패키지부터 하위 패키지 까지 조회하여 등록 (권장)
        basePackages = "hello.core.member", //member 패키지 안에 있는 애들만 등록
        basePackageClasses = AutoAppConfig.class, // AutoAppConfig 클래스가 속한 패키지(hello.core)부터 조회하여 등록
        //불필요 한 것은 excludeFilters 를 사용하여 제외시킬 수 있다
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}
