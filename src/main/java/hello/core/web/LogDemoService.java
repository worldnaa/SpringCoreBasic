package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService { // 비즈니스 로직이 있는 서비스 계층에서도 로그를 출력해보자

//  private final ObjectProvider<MyLogger> myLoggerProvider; // ObjectProvider 사용
    private final MyLogger myLogger; // 그냥 사용 시 오류 (프록시 사용하면 OK)

    public void logic(String id) {
//      MyLogger myLogger = myLoggerProvider.getObject(); // ObjectProvider 사용
        myLogger.log("service id = " + id);
    }
}
