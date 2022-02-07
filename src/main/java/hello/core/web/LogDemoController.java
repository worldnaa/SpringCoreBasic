package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

//  private final ObjectProvider<MyLogger> myLoggerProvider; // ObjectProvider 사용
    private final LogDemoService logDemoService;
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody // view 화면 없이, 문자를 그대로 응답 보낼 수 있다
    public String logDemo(HttpServletRequest request) { // HttpServletRequest를 통해서 요청 URL을 받았다

//      MyLogger myLogger = myLoggerProvider.getObject(); // ObjectProvider 사용

        String requestURL = request.getRequestURL().toString(); // requestURL = http://localhost:8080/log-demo
        myLogger.setRequestURL(requestURL); // 받은 requestURL 값을 myLogger에 저장해둔다

        System.out.println("myLogger = " + myLogger.getClass());
        // 결과 : myLogger = class hello.core.common.MyLogger$$EnhancerBySpringCGLIB$$7b31ced9
        // CGLIB 라는 라이브러리로 내 클래스를 상속받은 가짜 프록시 객체를 만들어서 주입한다

        myLogger.log("controller test"); // 컨트롤러에서 controller test 라는 로그를 남긴다
        logDemoService.logic("testId");
        return "OK";
    }
}
