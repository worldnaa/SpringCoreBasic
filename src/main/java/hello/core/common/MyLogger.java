package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

// public class MyLogger : 로그를 출력하기 위한 MyLogger 클래스
// 이제 이 빈은 HTTP 요청 당 하나씩 생성되고, HTTP 요청이 끝나는 시점에 소멸된다

@Component
// proxyMode : MyLogger의 가짜 프록시 클래스를 만들어두고 HTTP request와 상관없이 가짜 프록시 클래스를 다른 빈에 미리 주입해둘 수 있다
// 적용 대상이 인터페이스가 아닌 클래스면 TARGET_CLASS를 선택
// 적용 대상이 인터페이스면 INTERFACES를 선택
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS) //값이 하나면 value 생략 가능
public class MyLogger {

    private String uuid;
    private String requestURL;

    // requestURL은 이 빈이 생성되는 시점에는 알 수 없으므로, 외부에서 setter로 입력받는다
    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "]" + message);
    }

    // 이 빈이 생성되는 시점에 자동으로 @PostConstruct 초기화 메서드를 사용해서 uuid를 생성해서 저장해둔다
    @PostConstruct
    public void init() {
        // 이 빈은 HTTP 요청 당 하나씩 생성되므로, uuid를 저장해두면 다른 HTTP 요청과 구분할 수 있다
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create: " + this);
    }

    // 이 빈이 소멸되는 시점에 @PreDestroy를 사용해서 종료 메시지를 남긴다
    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close: " + this);
    }
}
